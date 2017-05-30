package com.crosshairengine.firstgame.engine.Abstract_classes;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.Vector;

/**
 * Created by CrossHairEngine team on 5/1/2017.
 */

// Tile is static should be part of the Map
// Tile is needed, so it get to be defined in engine
//
public abstract class Tile extends CDrawable {

    public Tile(Bitmap bitmap) {
        this.m_bSprate = bitmap;
    }

    public Tile(Bitmap bitmap, int leftPx, int topPx) {
        super(bitmap, leftPx, topPx);
    }
}
