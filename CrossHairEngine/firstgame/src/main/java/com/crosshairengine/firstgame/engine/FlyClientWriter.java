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
 * Created by CrossHairEngine team on 5/3/2017.
 */

public class FlyClientWriter extends AsyncTask<Void, Void, Void> {

    Socket socket;
    DataOutputStream out;
    Direction direction;

    public FlyClientWriter(Direction direction, Socket socket) {
        this.direction = direction;
        this.socket = socket;
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            out = new DataOutputStream(socket.getOutputStream());
            JsonObject json = new JsonObject();
            json.addProperty("action", "move");
            json.addProperty("direction", direction.toString());
            out.writeUTF(json.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}