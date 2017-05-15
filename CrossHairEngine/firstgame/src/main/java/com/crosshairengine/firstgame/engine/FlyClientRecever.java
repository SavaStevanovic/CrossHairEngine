package com.crosshairengine.firstgame.engine;

import android.os.AsyncTask;

import com.crosshairengine.firstgame.engine.Abstract_classes.Field;
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
 * Created by Sava on 5/10/2017.
 */

public class FlyClientRecever extends Thread {

    String hostName = "192.168.0.103";
    int portNumber = 4321;
    Socket socket;
    DataOutputStream out;
    private DataInputStream in;
    Field field;
    JsonParser jsonParser;

    public FlyClientRecever(Field field,Socket socket) {
        this.field = field;
        jsonParser = new JsonParser();
        this.socket=socket;
    }

    protected void onPostExecute(JsonObject result) {
        int x, y;
        String[] stringArray = result.get("Tiles").getAsString().split(",");
        for (int i = 0; i < stringArray.length; i++) {
            field.setElem(i / field.getYVal(), i % field.getYVal(), Integer.parseInt(stringArray[i]));
        }
        field.players.clear();
        JsonObject jsonPlayer = result.getAsJsonObject("Player");
        x = jsonPlayer.get("x").getAsInt();
        y = jsonPlayer.get("y").getAsInt();
        JsonArray playerArray = result.get("AllPlayers").getAsJsonArray();
        for (JsonElement playerJson : playerArray) {
            JsonObject jsonEnemyPlayer = playerJson.getAsJsonObject();
            field.addPlayer(1, jsonEnemyPlayer.get("x").getAsInt() - x, jsonEnemyPlayer.get("y").getAsInt() - y);
        }
        field.postInvalidate();
    }

    @Override
    public void run() {
        while (true) {
            try {
                in = new DataInputStream(socket.getInputStream());

                String weaverResponse = null;

                weaverResponse = in.readUTF();

                JsonElement ret = jsonParser.parse(weaverResponse);
                onPostExecute(ret.getAsJsonObject());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

