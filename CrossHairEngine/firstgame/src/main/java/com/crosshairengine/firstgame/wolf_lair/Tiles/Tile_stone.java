package com.crosshairengine.firstgame.wolf_lair.Tiles;

import android.graphics.Bitmap;

import com.crosshairengine.firstgame.engine.Abstract_classes.Tile;

/**
 * Created by CrossHairEngine team on 5/7/2017.
 */

public class Tile_stone extends Tile {
    public Tile_stone(Bitmap bitmap, int leftPx, int topPx)
    {
        super(bitmap, leftPx, topPx);
    }

    public Tile_stone(Bitmap bitmap) {
        super(bitmap);
    }
}
