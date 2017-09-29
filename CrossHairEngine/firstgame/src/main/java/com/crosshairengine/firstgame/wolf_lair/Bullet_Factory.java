package com.crosshairengine.firstgame.wolf_lair;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.crosshairengine.firstgame.R;
import com.crosshairengine.firstgame.wolf_lair.Settings.PhoneSettings;
import com.crosshairengine.firstgame.wolf_lair.TileFactories.Tile_Factory_Base;

import java.util.HashMap;

/**
 * Created by NikolaRancic on 9/29/2017.
 */

public class Bullet_Factory {
    private static Bullet_Factory _instance;
    public static Bullet_Factory getInstance(Context context, int height_count, int weight_count)
    {
        if (_instance == null)
            _instance = new Bullet_Factory(context, height_count, weight_count);
        return _instance;
    }

    // Use this when you are sure that _instance is initialize!
    //
    public static Bullet_Factory getInstance()
    {
        return _instance;
    }


    private HashMap<Integer, Bitmap> bitmap_originals;
    private int height;
    private int weight;
    private int height_count;
    private int weight_count;

    public static enum Bullet_type {
        BASIC(R.drawable.bullet000);

        private int value;

        private Bullet_type(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }
    }

    private Bullet_Factory(Context context, int height_count, int weight_count) {
        this.height_count = height_count;
        this.weight_count = weight_count;
        bitmap_originals = new HashMap<Integer, Bitmap>();
        height = PhoneSettings.getInstance().getHeight();
        weight = PhoneSettings.getInstance().getWeight();
        for (Bullet_type tile_type : Bullet_type.values()) {
            Bitmap bmp = BitmapFactory.decodeResource(context.getResources(), tile_type.getValue());
            bmp = Bitmap.createScaledBitmap(bmp, weight / weight_count - 1, height / height_count - 1, false);
            bitmap_originals.put(tile_type.getValue(), bmp);
        }

    }

    public Bullet getBullet(int bullet_type, int x, int y) {
        Bullet_type key = Bullet_Factory.Bullet_type.values()[bullet_type];
        Bitmap bmp = bitmap_originals.get(key.getValue());
        int posX = x * Tile_Factory_Base.getTileWidth();
        int posY = y * Tile_Factory_Base.getTileHeight();
        Bullet bullet = null;
        switch (key) {
            case BASIC:
                bullet = new Bullet(bmp, posX, posY);
                break;
        }
        return bullet;
    }

}
