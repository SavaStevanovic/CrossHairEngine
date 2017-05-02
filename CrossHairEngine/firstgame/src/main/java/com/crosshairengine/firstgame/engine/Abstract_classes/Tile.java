package com.crosshairengine.firstgame.engine.Abstract_classes;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.Vector;

/**
 * Created by CrossHairEngine team on 5/1/2017.
 */

//Should be part of the <Field>
//
public abstract class Tile {
    protected Bitmap bitmap;
    protected int x, y;

    public Tile(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Tile(Bitmap bitmap, int x, int y) {
        this.bitmap = bitmap;
        this.x = x;
        this.y = y;
    }

    public Tile setX(int x) {
        this.x = x;
        return this;
    }

    public Tile setY(int y) {
        this.y = y;
        return this;
    }

    public void drawTile(Canvas canvas) {
        canvas.drawBitmap(bitmap, y, x, null);
    }
}
