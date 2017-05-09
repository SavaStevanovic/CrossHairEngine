package com.crosshairengine.firstgame.wolf_lair.Players;

import android.graphics.Bitmap;

import com.crosshairengine.firstgame.engine.Abstract_classes.Player;

/**
 * Created by Sava on 5/9/2017.
 */

public class Player_enemy extends Player {
    public Player_enemy(Bitmap bitmap) {
        super(bitmap);
    }

    public Player_enemy(Bitmap bitmap, int x, int y) {
        super(bitmap, x, y);
    }
}
