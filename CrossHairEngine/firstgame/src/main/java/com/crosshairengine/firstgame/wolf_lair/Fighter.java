package com.crosshairengine.firstgame.wolf_lair;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.crosshairengine.firstgame.engine.Abstract_classes.Player;

import static android.R.attr.bitmap;

/**
 * Created by CrossHairEngine team on 5/9/2017.
 */

public class Fighter extends Player {


    public Fighter(Bitmap bitmap) {
        super(bitmap);
    }

    public Fighter(Bitmap bitmap, int x, int y) {
        super(bitmap, x, y);
    }
}
