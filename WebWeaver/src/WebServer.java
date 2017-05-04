import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServer {
	int portNumber = 4321;
	ServerSocket serverSocket;
	public WebServer() throws IOException{ 
		Socket socket = null;
		   DataInputStream in = null;
		   DataOutputStream out = null;
		   serverSocket = new ServerSocket(portNumber);
		   while(true){
			   System.out.println("weiting");
			   socket = serverSocket.accept();
			     in = new DataInputStream(
			       socket.getInputStream());
			     out = new DataOutputStream(
			       socket.getOutputStream());

			   System.out.println("...");
		   String messageFromClient = in.readUTF();
		   System.out.println(messageFromClient);
		   out.writeUTF(messageFromClient + "SavaServer");
		   System.out.println("sent");
		   }
	}
			 
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Start");
		try {
			WebServer ws=new WebServer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Finish");
	}

}
