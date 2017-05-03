package com.crosshairengine.firstgame.engine;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by Sava on 5/3/2017.
 */

public class WebWeaverConnection implements Runnable {
    String hostName = "192.168.0.1";
    int portNumber = 4321;
    PrintWriter out;
    BufferedReader in;
    BufferedReader stdIn;

    public WebWeaverConnection() {
    }

    @Override
    public void run() {
        Socket echoSocket = null;
        try {
            echoSocket = new Socket();
            echoSocket.connect(new InetSocketAddress(hostName, portNumber), 5000);
            out = new PrintWriter(echoSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
            stdIn = new BufferedReader(new InputStreamReader(System.in));
        } catch (Exception e) {
            e.printStackTrace();
        }

        String userInput;
        while ((userInput = "bzzzzzzzzzzz") != null) {
            out.println(userInput);
            try {
                System.out.println("echo: " + in.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


