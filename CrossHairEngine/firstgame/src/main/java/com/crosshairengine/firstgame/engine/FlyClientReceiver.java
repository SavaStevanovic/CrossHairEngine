package com.crosshairengine.firstgame.engine;

import com.crosshairengine.firstgame.engine.Interfaces.WebProcess;

import java.io.DataInputStream;
import java.net.Socket;

/**
 * Created by CrossHairEngine team on 5/10/2017.
 */

public class FlyClientReceiver extends Thread {

    private Socket socket;
    private DataInputStream in;

    private WebProcess processor;

    public FlyClientReceiver(Socket socket, WebProcess proccesor) {
        this.socket = socket;
        this.processor = proccesor;
    }

    @Override
    public void run() {
        while (true) {
            try {
                in = new DataInputStream(socket.getInputStream());
                String weaverResponse = in.readUTF();
                processor.process(weaverResponse);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

