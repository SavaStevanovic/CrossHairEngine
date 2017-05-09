package com.crosshairengine.firstgame.wolf_lair.Players;

import android.graphics.Bitmap;

import com.crosshairengine.firstgame.engine.Abstract_classes.Player;

/**
 * Created by Sava on 5/9/2017.
 */

public class Player_friendly extends Player {
    public Player_friendly(Bitmap bitmap) {
        super(bitmap);
    }

    public Player_friendly(Bitmap bitmap, int x, int y) {
        super(bitmap, x, y);
    }
}
