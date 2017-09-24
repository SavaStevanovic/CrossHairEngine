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
    private static Tile_Factory _instance;

    public static Tile_Factory getInstance(Context context, int height_count, int weight_count)
    {
        if (_instance == null)
            _instance = new Tile_Factory(context, height_count, weight_count);
        return _instance;
    }

    //you use this when you are sure that _instance is not null!
    //
    public static Tile_Factory getInstance()
    {
        return _instance;
    }

    private HashMap<Integer, Bitmap> bitmap_originals;
    private int heightPx;
    private int widthPx;
    private int height_count;
    private int width_count;

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

    private Tile_Factory(Context context, int height_count, int weight_count) {
        this.height_count = height_count;
        this.width_count = weight_count;
        bitmap_originals = new HashMap<Integer, Bitmap>();
        heightPx = PhoneSettings.getInstance().getHeight();
        widthPx = PhoneSettings.getInstance().getWeight();
        for (Tile_type tile_type: Tile_type.values()) {
            Bitmap bmp = BitmapFactory.decodeResource(context.getResources(), tile_type.getValue());
            bmp=Bitmap.createScaledBitmap(bmp,  widthPx / weight_count-1, heightPx / height_count - 1, false);
            bitmap_originals.put(tile_type.getValue(), bmp);
        }

    }

    public Tile getTile(int val, int i) {
        Tile_type key = Tile_Factory.Tile_type.values()[val];
        int left = i % width_count;
        int top = i / width_count;

        Bitmap bmp = bitmap_originals.get(key.getValue());
        int leftPx = left * widthPx / width_count;
        int topPx = top * heightPx / height_count;
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
