package com.crosshairengine.firstgame.wolf_lair.Settings;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by CrossHairEngine team on 5/15/2017.
 */

public class FlyInit extends Thread {
    private String hostName = "192.168.8.102";
    private int portNumber = 4321;
    private Socket socket;

    public FlyInit() {
    }

    @Override
    public void run() {
        socket = new Socket();
        try {
            socket.connect(new InetSocketAddress(hostName, portNumber), 5000);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Socket getSocket() {
        try {
            this.start();
            this.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return socket;
    }

}
