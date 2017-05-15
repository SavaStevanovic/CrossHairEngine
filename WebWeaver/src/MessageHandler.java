import java.net.ServerSocket;
import java.net.Socket;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class MessageHandler extends Thread {
	private String name;
	private Socket socket;
	private DataInputStream in;
	private DataOutputStream out;
	private JsonParser jsonParser;
	private WebServerRecever recever;

	public MessageHandler(Socket socket, WebServerRecever webServerRecever) {
		this.socket = socket;
		this.jsonParser = new JsonParser();
		this.recever = webServerRecever;
	}

	@Override
	public void run() {
		try {
			in = new DataInputStream(socket.getInputStream());
			out = new DataOutputStream(socket.getOutputStream());
			recever.recivers.add(out);
			while (true) {
				JsonObject messageFromClient = this.jsonParser.parse(in.readUTF()).getAsJsonObject();
				System.out.println(messageFromClient.toString());
				recever.gh.jsonHandle(out, messageFromClient);
				for (DataOutputStream writer : recever.recivers) {
					System.out.println(recever.gh.PlayerInfo(writer).toString());
					writer.writeUTF(recever.gh.PlayerInfo(writer).toString());
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
