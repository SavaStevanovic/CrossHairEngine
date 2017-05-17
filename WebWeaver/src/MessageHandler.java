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

	private Socket socket;
	private DataInputStream in;
	private DataOutputStream out;
	private JsonParser jsonParser;
	private GameHandler gameHandler;
	private Player player;

	public MessageHandler(Socket socket, GameHandler gameHandler) {
		this.socket = socket;
		this.jsonParser = new JsonParser();
		this.gameHandler = gameHandler;
	}

	@Override
	public void run() {
		try {
			in = new DataInputStream(socket.getInputStream());
			out = new DataOutputStream(socket.getOutputStream());
			player = gameHandler.getPlayer(out);
			while (true) {
				JsonObject messageFromClient = this.jsonParser.parse(in.readUTF()).getAsJsonObject();
				System.out.println(messageFromClient.toString());
				gameHandler.jsonHandle(out, messageFromClient);
			}
		} catch (Exception e) {
			try {
				socket.close();
				gameHandler.removePlayer(out);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
}
