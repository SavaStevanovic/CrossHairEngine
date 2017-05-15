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

public class WebServer {

	public static void main(String[] args) {
		System.out.println("Start");
		try {
			WebServerRecever ws = new WebServerRecever();
			ws.run();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Finish");
	}


}
