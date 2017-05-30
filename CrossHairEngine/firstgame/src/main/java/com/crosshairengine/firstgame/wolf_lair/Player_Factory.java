package com.crosshairengine.firstgame.wolf_lair;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.crosshairengine.firstgame.R;
import com.crosshairengine.firstgame.wolf_lair.Players.Player;
import com.crosshairengine.firstgame.wolf_lair.Players.Player_enemy;
import com.crosshairengine.firstgame.wolf_lair.Players.Player_friendly;
import com.crosshairengine.firstgame.wolf_lair.Settings.PhoneSettings;

import java.util.HashMap;

/**
 * Created by CrossHairEngine team on 5/9/2017.
 */

public class Player_Factory {
    private static Player_Factory _instance;
    public static Player_Factory getInstance(Context context, int height_count, int weight_count)
    {
        if (_instance == null)
            _instance = new Player_Factory(context, height_count, weight_count);
        return _instance;
    }

    // Use this when you are sure that _instance is initialize!
    //
    public static Player_Factory getInstance()
    {
        return _instance;
    }


    private HashMap<Integer, Bitmap> bitmap_originals;
    private int height;
    private int weight;
    private int height_count;
    private int weight_count;

    public static enum Player_type {
        FRIEND(R.drawable.player_friendly),
        ENEMY(R.drawable.player_enemy);

        private int value;

        private Player_type(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }
    }

    private Player_Factory(Context context, int height_count, int weight_count) {
        this.height_count = height_count;
        this.weight_count = weight_count;
        bitmap_originals = new HashMap<Integer, Bitmap>();
        height = PhoneSettings.getInstance().getHeight();
        weight = PhoneSettings.getInstance().getWeight();
        for (Player_type tile_type : Player_type.values()) {
            Bitmap bmp = BitmapFactory.decodeResource(context.getResources(), tile_type.getValue());
            bmp = Bitmap.createScaledBitmap(bmp, weight / weight_count - 1, height / height_count - 1, false);
            bitmap_originals.put(tile_type.getValue(), bmp);
        }

    }

    public Player getPlayer(int player_type, int x, int y) {
        Player_type key = Player_Factory.Player_type.values()[player_type];
        Bitmap bmp = bitmap_originals.get(key.getValue());
        int posX = x * height / height_count;
        int posY = y * weight / weight_count;
        Player player = null;
        switch (key) {
            case FRIEND:
                player = new Player_friendly(bmp, posX, posY);
                break;
            case ENEMY:
                player = new Player_enemy(bmp, posX, posY);
                break;
        }
        return player;
    }
}
