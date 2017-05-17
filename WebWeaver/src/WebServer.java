import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import net.sf.json.JSONObject;

public class WebServer {
	static int portNumber = 4321;

	public static void main(String[] args) {
		System.out.println("Start");
		GameHandler gameHandler=new GameHandler();
		try {
			ServerSocket serverSocket = new ServerSocket(portNumber);
			while (true) {
				try {
					System.out.println("...weiting...");
					MessageHandler mh = new MessageHandler(serverSocket.accept(),gameHandler);
					mh.start();
				} catch (IOException e) {
					e.printStackTrace();
					System.out.println("FINISHED");
				}
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
