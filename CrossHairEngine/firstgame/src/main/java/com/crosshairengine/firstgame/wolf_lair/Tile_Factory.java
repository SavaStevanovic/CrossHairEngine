package com.crosshairengine.firstgame.wolf_lair;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.crosshairengine.firstgame.R;
import com.crosshairengine.firstgame.engine.Abstract_classes.Tile;
import com.crosshairengine.firstgame.wolf_lair.Settings.PhoneSettings;
import com.crosshairengine.firstgame.wolf_lair.Tiles.Tile_grass;
import com.crosshairengine.firstgame.wolf_lair.Tiles.Tile_stone;

import java.util.HashMap;

import static android.R.attr.id;
import static android.R.attr.type;
import static android.graphics.BitmapFactory.decodeResource;

/**
 * Created by CrossHairEngine team on 5/2/2017.
 */

public class Tile_Factory {
    private HashMap<Integer, Bitmap> bitmap_originals;
    private int height;
    private int weight;
    private int height_count;
    private int weight_count;

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

    public Tile_Factory(Context context, int height_count, int weight_count) {
        this.height_count = height_count;
        this.weight_count = weight_count;
        bitmap_originals = new HashMap<Integer, Bitmap>();
        height = PhoneSettings.getInstance().getHeight();
        weight = PhoneSettings.getInstance().getWeight();
        for (Tile_type tile_type: Tile_type.values()) {
            Bitmap bmp = BitmapFactory.decodeResource(context.getResources(), tile_type.getValue());
            bmp=Bitmap.createScaledBitmap(bmp,  weight / weight_count-1, height / height_count - 1, false);
            bitmap_originals.put(tile_type.getValue(), bmp);
        }

    }

    public Tile getTile(Tile_type key, int x, int y) {
        Bitmap bmp = bitmap_originals.get(key.getValue());
        int posX = x * height / height_count;
        int posY = y * weight / weight_count;
        Tile tile=null;
        switch (key) {
            case GRASS: tile= new Tile_grass(bmp, posX, posY);break;
            case STONE: tile= new Tile_stone(bmp, posX, posY);break;
        }
        return tile;
    }
}
