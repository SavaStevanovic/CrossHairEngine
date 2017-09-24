package com.crosshairengine.firstgame.wolf_lair.TileFactories;

import android.content.Context;
import android.graphics.Bitmap;

import com.crosshairengine.firstgame.engine.Abstract_classes.Tile;
import com.crosshairengine.firstgame.wolf_lair.Tiles.Tile_grass;
import com.crosshairengine.firstgame.wolf_lair.Tiles.Tile_stone;

/**
 * Created by NikolaRancic on 9/24/2017.
 */

public class Tile_Factory_LeftRight extends Tile_Factory_Base {

    protected Tile_Factory_LeftRight(Context context, int height_count, int weight_count) {
        super(context, height_count, weight_count);
    }

    @Override
    public Tile getTile(int val, int i) {
        //offset for width due to width being + 1 than normal size
        int newWidth = width_count + 1;
        Tile_type key = Tile_Factory_Base.Tile_type.values()[val];
        int left = i % newWidth;
        int top = i / newWidth;

        Bitmap bmp = bitmap_originals.get(key.getValue());
        int leftPx = left * Tile_Factory_Base.getTileWidth();
        int topPx = top * Tile_Factory_Base.getTileHeight();
        Tile tile=null;
        switch (key) {
            case GRASS:
                tile= new Tile_grass(bmp, leftPx, topPx);
                break;
            case STONE:
                tile= new Tile_stone(bmp, leftPx, topPx);
                break;
        }
        return tile;
    }
}
