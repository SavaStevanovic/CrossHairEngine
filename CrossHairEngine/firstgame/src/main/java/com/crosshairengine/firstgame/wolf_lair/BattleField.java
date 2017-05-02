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
import com.crosshairengine.firstgame.engine.Abstract_classes.Tile;

import static android.R.attr.y;

/**
 * Created by Sava on 5/1/2017.
 */

public class BattleField extends Field {
    public static int y = 16;
    public static int x = 9;

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
                //drawTile(canvas, i, j);
            }
    }

    private void drawTile(Canvas canvas, int x, int y) {
        Bitmap tileImage = BitmapFactory.decodeResource(getResources(), R.drawable.tile_grass);
        tileImage = Bitmap.createScaledBitmap(tileImage, 120, 120, false);
        canvas.drawBitmap(tileImage, x, y, null);
    }

}
