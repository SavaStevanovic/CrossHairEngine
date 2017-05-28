package com.crosshairengine.firstgame.wolf_lair;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;

import com.crosshairengine.firstgame.engine.Abstract_classes.Field;
import com.crosshairengine.firstgame.engine.Abstract_classes.Tile;

/**
 * Created by CrossHairEngine team on 5/1/2017.
 */

public class BattleField extends Field {
    private static final int y = 9;
    private static final int x = 15;

    public BattleField(Context context) {
        super(context, y, x);
        for (int i = 0; i < y; i++)
            for (int j = 0; j < x; j++) {
                this.field.add(this.tile_factory.getTile(Tile_Factory.Tile_type.GRASS, i, j));
            }
        userPlayer = player_factory.getPlayer(Player_Factory.Player_type.FRIEND, this.getXVal() / 2, this.getYVal() / 2);
    }


    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawColor(Color.BLACK);
        for (Player player : players) {
            player.drawPlayer(canvas);
        }
        for (Tile tile : field) {
            tile.drawTile(canvas);
        }
    }


    @Override
    public void setElem(int i, int val) {
        field.set(i, tile_factory.getTile(Tile_Factory.Tile_type.values()[val], i / x, i % x));
    }

    @Override
    public void setUserPlayer(int player_type, int x, int y) {
        userPlayer = player_factory.getPlayer(Player_Factory.Player_type.values()[player_type], x, y);
    }

    @Override
    public void addPlayer(int player_type, int x, int y) {
        players.add(player_factory.getPlayer(Player_Factory.Player_type.values()[player_type], x + this.getXVal() / 2, y + this.getYVal() / 2));
    }
}
