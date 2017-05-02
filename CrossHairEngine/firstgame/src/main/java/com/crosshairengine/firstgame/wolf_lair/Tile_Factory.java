package com.crosshairengine.firstgame.wolf_lair;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.crosshairengine.firstgame.R;
import com.crosshairengine.firstgame.engine.Abstract_classes.Tile;
import com.crosshairengine.firstgame.wolf_lair.Settings.PhoneSettings;
import com.crosshairengine.firstgame.wolf_lair.Tiles.Tile_grass;

import java.util.HashMap;

import static android.R.attr.type;
import static android.graphics.BitmapFactory.decodeResource;

/**
 * Created by Sava on 5/2/2017.
 */

public class Tile_Factory {
    protected HashMap<Integer, Bitmap> bitmap_originals;
    int height;
    int weight;
    int height_count;
    int weight_count;

    public static enum Tile_type {
        GRASS(R.drawable.tile_grass);

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
            Bitmap bmp_grass = BitmapFactory.decodeResource(context.getResources(), tile_type.getValue());
            bmp_grass=Bitmap.createScaledBitmap(bmp_grass, height / height_count-1, weight / weight_count-1, false);
            bitmap_originals.put(tile_type.getValue(), bmp_grass);
        }

    }

    public Tile getTile(Tile_type key, int x, int y) {
        Bitmap bmp = bitmap_originals.get(key.getValue());
        int posX = x * height / height_count;
        int posY = y * weight / weight_count;
        Tile tile=null;
        switch (key) {
            case GRASS: tile= new Tile_grass(bmp, posX, posY);
        }
        return tile;
    }
}
