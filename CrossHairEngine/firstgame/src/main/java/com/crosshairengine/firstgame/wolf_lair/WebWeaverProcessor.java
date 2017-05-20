package com.crosshairengine.firstgame.wolf_lair;

import android.graphics.Rect;

import com.crosshairengine.firstgame.engine.Abstract_classes.Field;
import com.crosshairengine.firstgame.engine.Interfaces.WebProcess;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Created by CrossHairEngine team on 5/16/2017.
 */

public class WebWeaverProcessor implements WebProcess {
    private JsonParser jsonParser;
    private Field field;

    public WebWeaverProcessor(Field field) {
        this.jsonParser = new JsonParser();
        this.field = field;
    }

    @Override
    public void process(String webResponse) {
        JsonObject result = jsonParser.parse(webResponse).getAsJsonObject();
        String[] stringArray = result.get("Tiles").getAsString().split(",");
        for (int i = 0; i < stringArray.length; i++) {
            field.setElem(i, Integer.parseInt(stringArray[i]));
        }
        field.clearPlayers();
        JsonObject jsonPlayer = result.getAsJsonObject("Player");
        int x = jsonPlayer.get("x").getAsInt();
        int y = jsonPlayer.get("y").getAsInt();
        JsonArray playerArray = result.get("AllPlayers").getAsJsonArray();
        for (JsonElement playerJson : playerArray) {
            JsonObject jsonEnemyPlayer = playerJson.getAsJsonObject();
            field.addPlayer(1, jsonEnemyPlayer.get("x").getAsInt() - x, jsonEnemyPlayer.get("y").getAsInt() - y);
        }

            field.postInvalidate();
    }
}
