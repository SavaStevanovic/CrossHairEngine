package com.crosshairengine.firstgame.engine;


import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.crosshairengine.firstgame.engine.Abstract_classes.Field;
import com.crosshairengine.firstgame.wolf_lair.Settings.PhoneSettings;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

import static android.R.attr.direction;
import static android.R.attr.handle;
import static android.R.transition.move;

/**
 * Created by Sava on 5/3/2017.
 */

public class FlyClient extends AsyncTask<Void, Void, JSONObject> {
    public static enum Direction {UP, DOWN, LEFT, RIGHT}

    String hostName = "192.168.0.101";
    int portNumber = 4321;
    Socket socket;
    DataOutputStream out;
    private DataInputStream in;
    Direction direction;
    Field field;

    public FlyClient(Direction direction) {
        this.direction = direction;
    }

        @Override
        protected JSONObject doInBackground (Void...params){
            try {
                socket = new Socket();
                socket.connect(new InetSocketAddress(hostName, portNumber), 5000);
                out = new DataOutputStream(socket.getOutputStream());
                in = new DataInputStream(socket.getInputStream());
            } catch (Exception e) {
                e.printStackTrace();
            }
            JSONObject json = new JSONObject();
            try {
                json.put("action", "move");
                json.put("direction", direction);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {
                out.writeUTF("SavaClient");
                JSONObject js = new JSONObject();
                String s = in.readUTF();
                js.put("response", s);
                return js;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return new JSONObject();
        }

        @Override
        protected void onPostExecute (JSONObject json){
            try {
                if (json.get("action") == "move") {
                    handleMove(json.get("direction"), json.getJSONArray("params"));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    private void handleMove(Object direction, JSONArray params) {
        Direction dir = (Direction) direction;
        switch (dir) {
            case UP:
                break;
        }
    }
}
