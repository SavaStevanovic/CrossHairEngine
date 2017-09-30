package com.crosshairengine.firstgame.wolf_lair.StateObjects;

import android.util.Log;

import com.crosshairengine.firstgame.engine.Abstract_classes.StateObject;
import com.crosshairengine.firstgame.wolf_lair.TileFactories.Tile_Factory_Base;

import java.util.HashMap;

/**
 * Created by NikolaRancic on 9/24/2017.
 */

public class MapStateObject extends StateObject {

    private static HashMap<String, Integer> horizontalOffsetMove = new HashMap<String, Integer>();
    private static HashMap<String, Integer> verticalOffsetMove = new HashMap<String, Integer>();

    static {
        // horizontal movement initialization
        horizontalOffsetMove.put("left", 1);
        horizontalOffsetMove.put("up",0);
        horizontalOffsetMove.put("right",-1);
        horizontalOffsetMove.put("down",0);
        horizontalOffsetMove.put("center",0);

        // vertical movement initialization
        verticalOffsetMove.put("left", 0);
        verticalOffsetMove.put("up", 1);
        verticalOffsetMove.put("right",0);
        verticalOffsetMove.put("down",-1);
        verticalOffsetMove.put("center",0);
    }

    private boolean factionState;

    public MapStateObject(String sDirectionName, int timeActionProgressBeforeObjectCreation, int timeLengthOfAction, boolean factionState) {
        super(null, sDirectionName, timeActionProgressBeforeObjectCreation, timeLengthOfAction);

        double percentDone = TimeALL() / (double) this.timeLengthOfAction;

        this.leftOffSet = horizontalOffsetMove.get(this.sDirectionName) * (int)(percentDone * Tile_Factory_Base.getTileWidth());
        if (sDirectionName.equals("left")) this.leftOffSet -= Tile_Factory_Base.getTileWidth();

        this.topOffSet = verticalOffsetMove.get(this.sDirectionName) * (int)(percentDone * Tile_Factory_Base.getTileHeight());
        if (sDirectionName.equals("up")) this.topOffSet -= Tile_Factory_Base.getTileHeight();

        this.factionState =  factionState;
    }

    @Override
    public void Invalidate(){

        double percentDone = TimeALL() / (double) this.timeLengthOfAction;
        Log.i("State INVALIDATE:", "TimeALL/this.timeLengthOfAction = " + percentDone);
        if (percentDone > 1) {
            percentDone = 1;
        }

        // offset for moving map fields when moving
        //
        this.leftOffSet = horizontalOffsetMove.get(this.sDirectionName) * (int) (percentDone * Tile_Factory_Base.getTileWidth());
        if (sDirectionName.equals("left")) this.leftOffSet -= Tile_Factory_Base.getTileWidth();

        this.topOffSet = verticalOffsetMove.get(this.sDirectionName) * (int) (percentDone * Tile_Factory_Base.getTileHeight());
        if (sDirectionName.equals("up")) this.topOffSet -= Tile_Factory_Base.getTileHeight();

        // offset for handling bullet firing
        // factionState = true than it is bullet scenario
        //
        if (sDirectionName.equals("left") && factionState) this.leftOffSet -= Tile_Factory_Base.getTileWidth();
        if (sDirectionName.equals("right") && factionState) this.leftOffSet += Tile_Factory_Base.getTileWidth();
        if (sDirectionName.equals("up") && factionState) this.topOffSet -= Tile_Factory_Base.getTileHeight();
        if (sDirectionName.equals("down") && factionState) this.topOffSet += Tile_Factory_Base.getTileHeight();
    }
}
