package com.crosshairengine.firstgame.engine;


import android.graphics.Canvas;
import android.os.AsyncTask;

import com.crosshairengine.firstgame.engine.Abstract_classes.Field;
import com.crosshairengine.firstgame.wolf_lair.Player_Factory;
import com.crosshairengine.firstgame.wolf_lair.Players.Player_friendly;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by Sava on 5/3/2017.
 */

public class FlyClient extends AsyncTask<Void, Void, Void> {
    public static enum Direction {
        UP("up"), DOWN("down"), LEFT("left"), RIGHT("right"), CENTER("center");

        private final String name;

        private Direction(String s) {
            name = s;
        }

        public String toString() {
            return this.name;
        }
    }

    String hostName = "192.168.0.103";
    int portNumber = 4321;
    Socket socket;
    DataOutputStream out;
    private DataInputStream in;
    Direction direction;
    JsonParser jsonParser;

    public FlyClient(Direction direction) {
        this.direction = direction;
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            socket = new Socket();
            socket.connect(new InetSocketAddress(hostName, portNumber), 5000);
            out = new DataOutputStream(socket.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
        JsonObject json = new JsonObject();

        json.addProperty("action", "move");
        json.addProperty("direction", direction.toString());

        String weaverResponse = null;
        try {
            out.writeUTF(json.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}