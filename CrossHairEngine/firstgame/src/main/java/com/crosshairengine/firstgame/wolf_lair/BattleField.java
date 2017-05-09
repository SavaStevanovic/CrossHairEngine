package com.crosshairengine.firstgame.wolf_lair;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.view.View;

import com.crosshairengine.firstgame.R;
import com.crosshairengine.firstgame.engine.Abstract_classes.Field;
import com.crosshairengine.firstgame.engine.Abstract_classes.Player;
import com.crosshairengine.firstgame.engine.Abstract_classes.Tile;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import static android.R.attr.y;

/**
 * Created by CrossHairEngine team on 5/1/2017.
 */

public class BattleField extends Field {
    public static int y = 9;
    public static int x = 15;

    public BattleField(Context context) {
        super(context, y, x);
        for (int i = 0; i < y; i++)
            for (int j = 0; j < x; j++) {
                this.field[i][j] = this.tile_factory.getTile(Tile_Factory.Tile_type.GRASS, i, j);
            }
    }


    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawColor(Color.BLACK);
        for (int i = 0; i < y; i++)
            for (int j = 0; j < x; j++) {
                field[i][j].drawTile(canvas);
            }
    }


    @Override
    public void setElem(int x, int y, int val) {
        field[x][y]=tile_factory.getTile(Tile_Factory.Tile_type.values()[val],x,y);
    }

    @Override
    public void addPlayerJson(JsonObject jsonPlayer) {
        Gson gson = new GsonBuilder()
                .registerTypeHierarchyAdapter(Player.class, new  BGsonAdapter())
                .setPrettyPrinting()
                .create();
    }
}
