package com.crosshairengine.firstgame.engine;

import com.crosshairengine.firstgame.engine.Interfaces.WebProcess;

import java.io.DataInputStream;
import java.net.Socket;
import java.util.concurrent.Semaphore;

/**
 * Created by CrossHairEngine team on 5/10/2017.
 */

public class FlyClientReceiver extends Thread {

    private Semaphore semProcessServerMessage;
    private Socket socket;
    private DataInputStream in;

    private WebProcess processor;

    public FlyClientReceiver(Socket socket, WebProcess proccesor, Semaphore semProcessServerMessage) {
        this.socket = socket;
        this.processor = proccesor;
        this.semProcessServerMessage = semProcessServerMessage;
    }

    @Override
    public void run() {
        while (true) {
            try {
                in = new DataInputStream(socket.getInputStream());
                String weaverResponse = in.readUTF();
                try {
                    semProcessServerMessage.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                processor.process(weaverResponse);
                semProcessServerMessage.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

