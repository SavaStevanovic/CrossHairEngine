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

public class FlyClientRecever extends AsyncTask<Void, Void, JsonObject> {

    String hostName = "192.168.0.103";
    int portNumber = 4322;
    Socket socket;
    DataOutputStream out;
    private DataInputStream in;
    Field field;
    JsonParser jsonParser;

    public FlyClientRecever( Field field) {
        this.field = field;
        jsonParser = new JsonParser();
    }

    @Override
    protected JsonObject doInBackground(Void... params) {
        try {
            socket = new Socket();
            socket.connect(new InetSocketAddress(hostName, portNumber), 5000);
            in = new DataInputStream(socket.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
        String weaverResponse = null;
        try {
            weaverResponse = in.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JsonElement ret = jsonParser.parse(weaverResponse);
        return ret.getAsJsonObject();
    }

    @Override
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
            field.addPlayer(1, jsonEnemyPlayer.get("x").getAsInt()-x, jsonEnemyPlayer.get("y").getAsInt()-y);
        }
        field.invalidate();
        this.execute();
    }
}
