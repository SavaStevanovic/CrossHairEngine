import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import net.sf.json.JSONObject;

public class WebServerRecever implements Runnable {
	GameHandler gh;
	int portNumber = 4321;
	ServerSocket serverSocket;
	Gson gson;
	JsonParser jsonParser;

	public WebServerRecever() throws IOException {
		gh = new GameHandler();
		gson = new Gson();
		jsonParser = new JsonParser();
		serverSocket = new ServerSocket(portNumber);

	}

	public void run() {
		Socket socket = null;
		DataInputStream in = null;
		DataOutputStream out = null;
		while (true) {
			System.out.println("weiting");
			try {
				socket = serverSocket.accept();
			
			// socket.get
			in = new DataInputStream(socket.getInputStream());
			out = new DataOutputStream(socket.getOutputStream());

			System.out.println("...");
			JsonObject messageFromClient = jsonParser.parse(in.readUTF()).getAsJsonObject();
			gh.jsonHandle(socket.getInetAddress(), messageFromClient);
			System.out.println(messageFromClient);
			out.writeUTF(gh.PlayerInfo(socket.getInetAddress()).toString());
			System.out.println(gh.PlayerInfo(socket.getInetAddress()).toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
