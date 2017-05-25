import java.net.ServerSocket;
import java.net.Socket;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class MessageHandler extends Thread {

	protected Socket socket;
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
		} catch (IOException e) {
			e.printStackTrace();
		}
		player=gameHandler.getPlayer(this);
		while (true) {
			try {
				JsonObject messageFromClient = this.jsonParser.parse(in.readUTF()).getAsJsonObject();
				System.out.println(messageFromClient.toString());
				player.jsonHandle(messageFromClient);
			} catch (IOException e) {
				e.printStackTrace();
				gameHandler.leaveGame(this);
				try {
					socket.close();
					return;
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}

	}

	protected void sendMessage(String message) {
		try {
			System.out.println(message);
			out.writeUTF(message);
		} catch (IOException e) {
			e.printStackTrace();
			gameHandler.leaveGame(this);
			try {
				socket.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
}
