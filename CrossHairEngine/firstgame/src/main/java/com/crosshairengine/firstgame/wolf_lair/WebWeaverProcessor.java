package com.crosshairengine.firstgame.wolf_lair;

import android.util.Log;

import com.crosshairengine.firstgame.engine.Abstract_classes.CDrawable;
import com.crosshairengine.firstgame.engine.GameEngine;
import com.crosshairengine.firstgame.engine.Interfaces.WebProcess;
import com.crosshairengine.firstgame.wolf_lair.Players.Player;
import com.crosshairengine.firstgame.wolf_lair.Settings.Constants;
import com.crosshairengine.firstgame.wolf_lair.Objects.Map;
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
    public GameEngine m_gameEngine;
    private Integer number=0;

    public WebWeaverProcessor(GameEngine m_gameEngine) {
        this.jsonParser = new JsonParser();
        this.m_gameEngine = m_gameEngine;
    }

    @Override
    public void process(String webResponse) {
        Player_Factory player_factory = Player_Factory.getInstance();
        Bullet_Factory bullet_facotry = Bullet_Factory.getInstance();
        JsonObject result = jsonParser.parse(webResponse).getAsJsonObject();

        //Initialize map
        String sDirection = result.getAsJsonObject("Player").get("direction").getAsString();
        int iStartTime = result.getAsJsonObject("Player").get("startTime").getAsInt();
        int iActionTime = result.getAsJsonObject("Player").get("moveTime").getAsInt();
        String[] sTilesArray = result.get("Tiles").getAsString().split(",");
        Map newMap = new Map(sDirection,
                sTilesArray,
                iStartTime,
                iActionTime,
                Constants.width,
                Constants.height);

        m_gameEngine.clearPlayers();
        m_gameEngine.m_MainDrawClass.clearCDrawableObjectStorage();

        // set cordinates for main player
        //
        JsonObject jsonPlayer = result.getAsJsonObject("Player");
        int x = jsonPlayer.get("x").getAsInt() - Constants.height/2;
        int y = jsonPlayer.get("y").getAsInt() - Constants.width/2;

        JsonArray allObjectsArray = result.get("AllPlayers").getAsJsonArray();
        // set bullets
        //
        ArrayList<CDrawable> bulletsObjectsArray = new ArrayList<CDrawable>();
        for (JsonElement objectElement : allObjectsArray) {
            JsonObject objectElemJson = objectElement.getAsJsonObject();
            if (objectElemJson.get("type").getAsString().equals("bullet")){
                bulletsObjectsArray.add(bullet_facotry.getBullet(0, objectElemJson.get("y").getAsInt() - y, objectElemJson.get("x").getAsInt() - x));
            }
        }

        // set all players
        //
        for (JsonElement objectElement : allObjectsArray) {
            JsonObject objectElemJson = objectElement.getAsJsonObject();
            if (objectElemJson.get("type").getAsString().equals("player")){
                m_gameEngine.addPlayer(player_factory.getPlayer(1, objectElemJson.get("y").getAsInt() - y, objectElemJson.get("x").getAsInt() - x));
            }
        }

        // DRAWING
        //
        ArrayList<CDrawable> alCDrawable = new ArrayList<CDrawable>();
        alCDrawable.add(newMap);
        m_gameEngine.m_MainDrawClass.addCDrawableArrayList(0, alCDrawable);

        alCDrawable = new ArrayList<CDrawable>();
        for (Player player : m_gameEngine.m_alPlayer)
            alCDrawable.add(player);
        m_gameEngine.m_MainDrawClass.addCDrawableArrayList(1, alCDrawable);

        m_gameEngine.m_MainDrawClass.addCDrawableArrayList(2, bulletsObjectsArray);

        number++;
        Log.i("process", "Iteration number: " + number.toString());
    }
}
