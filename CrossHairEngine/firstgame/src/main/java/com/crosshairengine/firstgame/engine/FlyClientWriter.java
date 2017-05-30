package com.crosshairengine.firstgame.engine;


import android.os.AsyncTask;

import com.crosshairengine.firstgame.engine.Commands.Command;
import com.google.gson.JsonObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by CrossHairEngine team on 5/3/2017.
 */

public class FlyClientWriter extends AsyncTask<Void, Void, Void> {

    Socket socket;
    DataOutputStream out;
    Command command;

    public FlyClientWriter(Command command, Socket socket) {
        this.command = command;
        this.socket = socket;
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            out = new DataOutputStream(socket.getOutputStream());
            JsonObject json = new JsonObject();
            if (command == Command.Fire)
            {
                json.addProperty("action", "fire");
            }else{
                json.addProperty("action", "move");
                json.addProperty("direction", command.toString());
            }
            out.writeUTF(json.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}