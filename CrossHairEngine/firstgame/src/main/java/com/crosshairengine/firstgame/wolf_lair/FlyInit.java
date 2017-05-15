package com.crosshairengine.firstgame.wolf_lair;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by Sava on 5/15/2017.
 */

class FlyInit extends Thread {
    String hostName = "192.168.0.103";
    int portNumber = 4321;
    Socket socket;

    public FlyInit( Socket socket) {
    }

    @Override
    public void run() {
        socket=new Socket();
        try {
            socket.connect(new InetSocketAddress(hostName, portNumber), 5000);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Socket getSocket(){
        return socket;
    }

}
