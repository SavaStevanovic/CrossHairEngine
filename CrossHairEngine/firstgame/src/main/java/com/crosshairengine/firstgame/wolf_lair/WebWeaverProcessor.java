package com.crosshairengine.firstgame.wolf_lair;

import com.crosshairengine.firstgame.engine.Abstract_classes.CDrawable;
import com.crosshairengine.firstgame.engine.GameEngine;
import com.crosshairengine.firstgame.engine.Interfaces.WebProcess;
import com.crosshairengine.firstgame.wolf_lair.Players.Player;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;

/**
 * Created by CrossHairEngine team on 5/16/2017.
 */

public class WebWeaverProcessor implements WebProcess {
    private JsonParser jsonParser;
    private GameEngine m_gameEngine;

    public WebWeaverProcessor(GameEngine m_gameEngine) {
        this.jsonParser = new JsonParser();
        this.m_gameEngine = m_gameEngine;
    }

    @Override
    public void process(String webResponse) {
        Tile_Factory tile_factory = Tile_Factory.getInstance();
        Player_Factory player_factory = Player_Factory.getInstance();
        JsonObject result = jsonParser.parse(webResponse).getAsJsonObject();
        String[] stringArray = result.get("Tiles").getAsString().split(",");
        for (int i = 0; i < stringArray.length; i++)
        {
            //this is bad we use twice i, i will change this
            //
            m_gameEngine.m_mMap.setTile(tile_factory.getTile(Integer.parseInt(stringArray[i]), i),i);
        }
        m_gameEngine.clearPlayers();
        JsonObject jsonPlayer = result.getAsJsonObject("Player");
        int x = jsonPlayer.get("x").getAsInt();
        int y = jsonPlayer.get("y").getAsInt();
        JsonArray playerArray = result.get("AllPlayers").getAsJsonArray();
        for (JsonElement playerJson : playerArray) {
            JsonObject jsonEnemyPlayer = playerJson.getAsJsonObject();
            m_gameEngine.addPlayer(player_factory.getPlayer(1, jsonEnemyPlayer.get("x").getAsInt() - x, jsonEnemyPlayer.get("y").getAsInt() - y));
        }
        ArrayList<CDrawable> alCDrawable = new ArrayList<CDrawable>();
        alCDrawable.add(m_gameEngine.m_mMap);
        m_gameEngine.m_MainDrawClass.addCDrawableArrayList(0,alCDrawable);

        alCDrawable = new ArrayList<CDrawable>();
        for(Player player : m_gameEngine.m_alPlayer)
            alCDrawable.add(player);
        m_gameEngine.m_MainDrawClass.addCDrawableArrayList(1,alCDrawable);

        m_gameEngine.m_MainDrawClass.postInvalidate();
        m_gameEngine.m_MainDrawClass.clearCDrawableObjectStorage();
    }
}
