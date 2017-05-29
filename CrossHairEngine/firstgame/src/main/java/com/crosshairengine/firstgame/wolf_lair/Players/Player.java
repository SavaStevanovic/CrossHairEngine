package com.crosshairengine.firstgame.wolf_lair.Players;

import android.graphics.Bitmap;

import com.crosshairengine.firstgame.engine.Abstract_classes.CDrawable;

/**
 * Created by NikolaRancic on 5/28/2017.
 */

public abstract class Player extends CDrawable {
    public Player(Bitmap bitmap, int left, int top)
    {
        super(bitmap, left, top);
    }

}
