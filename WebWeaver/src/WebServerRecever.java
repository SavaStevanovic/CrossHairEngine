import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.awt.List;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Array;
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
	HashSet<DataOutputStream> recivers;

	public WebServerRecever() throws IOException {
		gh = new GameHandler();
		gson = new Gson();
		jsonParser = new JsonParser();
		serverSocket = new ServerSocket(portNumber);
		recivers = new HashSet<DataOutputStream>();

	}

	public void run() {
		Socket socket = null;
		while (true) {
			System.out.println("weiting");
			MessageHandler mh;
			try {
				mh = new MessageHandler(serverSocket.accept(), this);
				mh.start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}