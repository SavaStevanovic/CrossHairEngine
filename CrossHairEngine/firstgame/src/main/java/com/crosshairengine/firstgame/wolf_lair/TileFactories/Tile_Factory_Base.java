package com.crosshairengine.firstgame.wolf_lair.TileFactories;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.crosshairengine.firstgame.R;
import com.crosshairengine.firstgame.engine.Abstract_classes.Tile;
import com.crosshairengine.firstgame.wolf_lair.Settings.PhoneSettings;
import com.crosshairengine.firstgame.wolf_lair.Tiles.Tile_grass;
import com.crosshairengine.firstgame.wolf_lair.Tiles.Tile_stone;

import java.util.HashMap;

import static android.graphics.BitmapFactory.decodeResource;

/**
 * Created by CrossHairEngine team on 5/2/2017.
 */

public abstract class Tile_Factory_Base {

    private static int tileWidth;
    private static int tileHeight;

    private int offsetForTesting = -1;

    protected HashMap<Integer, Bitmap> bitmap_originals;
    protected int heightPx;
    protected int widthPx;
    protected int height_count;
    protected int width_count;

    public static enum Tile_type {
        GRASS(R.drawable.tile_grass ),
        STONE(R.drawable.tile_stone );

        private int value;

        private Tile_type(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }
    }

    protected Tile_Factory_Base(Context context, int height_count, int weight_count) {

        //initialize object attributes
        this.height_count = height_count;
        this.width_count = weight_count;
        this.bitmap_originals = new HashMap<Integer, Bitmap>();
        this.heightPx = PhoneSettings.getInstance().getHeight();
        this.widthPx = PhoneSettings.getInstance().getWeight();

        // static tile width and height
        tileWidth = widthPx / weight_count;
        tileHeight = heightPx / height_count;

        for (Tile_type tile_type: Tile_type.values()) {
            Bitmap bmp = BitmapFactory.decodeResource(context.getResources(), tile_type.getValue());
            bmp=Bitmap.createScaledBitmap(bmp,  tileWidth + offsetForTesting, tileHeight + offsetForTesting, false);
            bitmap_originals.put(tile_type.getValue(), bmp);
        }

    }

    public static int getTileWidth() {
        return tileWidth;
    }

    public static int getTileHeight() {
        return tileHeight;
    }

    public abstract Tile getTile(int val, int i);

}
