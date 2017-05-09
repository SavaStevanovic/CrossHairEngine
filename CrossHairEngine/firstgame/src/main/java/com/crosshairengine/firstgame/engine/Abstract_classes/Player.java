package com.crosshairengine.firstgame.engine.Abstract_classes;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import static android.R.attr.bitmap;

/**
 * Created by CrossHairEngine team on 4/27/2017.
 */

//Should be User controllable object
//
public abstract class Player {
    protected Bitmap bitmap;
    protected int x, y;

    public Player(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Player(Bitmap bitmap, int x, int y) {
        this.bitmap = bitmap;
        this.x = x;
        this.y = y;
    }

    public Player setX(int x) {
        this.x = x;
        return this;
    }

    public Player setY(int y) {
        this.y = y;
        return this;
    }

    public void drawTile(Canvas canvas) {
        canvas.drawBitmap(bitmap, y, x, null);
    }
}
