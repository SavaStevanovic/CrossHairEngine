package com.crosshairengine.firstgame.wolf_lair.StateObjects;

import android.graphics.Bitmap;

import com.crosshairengine.firstgame.engine.Abstract_classes.StateObject;
import com.crosshairengine.firstgame.wolf_lair.Settings.Constants;

import java.util.HashMap;

/**
 * Created by NikolaRancic on 9/24/2017.
 */

public class MapStateObject extends StateObject {

    private static HashMap<String, Integer> horizontalOffset = new HashMap<String, Integer>();
    private static HashMap<String, Integer> verticalOffset = new HashMap<String, Integer>();

    static {
        // horizontal initialization
        horizontalOffset.put("left", -1);
        horizontalOffset.put("up",0);
        horizontalOffset.put("right",1);
        horizontalOffset.put("down",0);
        horizontalOffset.put("center",0);

        // vertical initialization
        verticalOffset.put("left", 0);
        verticalOffset.put("up", -1);
        verticalOffset.put("right",0);
        verticalOffset.put("down",1);
        verticalOffset.put("center",0);
    }

    public MapStateObject(String sActionName, int timeActionProgressBeforeObjectCreation) {
        super();
        this.sActionName = sActionName;
        this.timeActionProgressBeforeObjectCreation = timeActionProgressBeforeObjectCreation;
        this.leftOffSet = horizontalOffset.get(this.sActionName) * (TimeALL() / 500) * Constants.width;
        this.topOffSet = verticalOffset.get(this.sActionName) * (TimeALL() / 500) * Constants.height;
    }

    @Override
    public void Invalidate(){
        this.leftOffSet = horizontalOffset.get(this.sActionName) * (TimeALL() / 500) * Constants.width;
        this.topOffSet = verticalOffset.get(this.sActionName) * (TimeALL() / 500) * Constants.height;
    }
}
