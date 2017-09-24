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

    public MapStateObject(String sActionName, int timeActionProgressBeforeObjectCreation) {
        super();
        this.sActionName = sActionName;
        double percentDone = TimeALL() / 500.0;
        this.timeActionProgressBeforeObjectCreation = timeActionProgressBeforeObjectCreation;

        this.leftOffSet = horizontalOffsetMove.get(this.sActionName) * (int)(percentDone * Tile_Factory_Base.getTileWidth());
        if (sActionName.equals("left")) this.leftOffSet -= Tile_Factory_Base.getTileWidth();

        this.topOffSet = verticalOffsetMove.get(this.sActionName) * (int)(percentDone * Tile_Factory_Base.getTileHeight());
        if (sActionName.equals("up")) this.topOffSet -= Tile_Factory_Base.getTileHeight();
    }

    @Override
    public void Invalidate(){

        double percentDone = TimeALL() / 500.0;
        Log.i("State INVALIDATE:", "TimeALL/500 = " + percentDone);
        if (percentDone > 1) percentDone = 1;

        this.leftOffSet = horizontalOffsetMove.get(this.sActionName) * (int)(percentDone * Tile_Factory_Base.getTileWidth());
        if (sActionName.equals("left")) this.leftOffSet -= Tile_Factory_Base.getTileWidth();

        this.topOffSet = verticalOffsetMove.get(this.sActionName) * (int)(percentDone * Tile_Factory_Base.getTileHeight());
        if (sActionName.equals("up")) this.topOffSet -= Tile_Factory_Base.getTileHeight();
    }
}
