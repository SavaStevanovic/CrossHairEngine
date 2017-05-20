package com.crosshairengine.firstgame.engine.Abstract_classes;

import android.content.Context;
import android.view.View;

import com.crosshairengine.firstgame.wolf_lair.Player_Factory;
import com.crosshairengine.firstgame.wolf_lair.Tile_Factory;
import com.google.gson.JsonObject;

import java.util.List;
import java.util.Vector;

/**
 * Created by CrossHairEngine team on 4/27/2017.
 */

//Should be central object of the system
//All interactions should be done trough map
//
public abstract class Field extends View {
    protected Vector<Tile> field;
    protected Player userPlayer;
    protected Tile_Factory tile_factory;
    protected Player_Factory player_factory;
    protected Vector<Player> players;
    protected final int x, y;
    protected int offX, offY;

    public Field(Context context, int x, int y) {
        super(context);
        tile_factory = new Tile_Factory(context, x, y);
        player_factory = new Player_Factory(context, x, y);
        field = new Vector<Tile>();
        players = new Vector<Player>();
        this.x = x;
        this.y = y;
        offX = 0;
        offY = 0;
    }

    public abstract void setElem(int i, int val);

    public int getYVal() {
        return y;
    }

    public int getXVal() {
        return x;
    }

    public abstract void setUserPlayer(int playerType, int x, int y);

    public abstract void addPlayer(int playerType, int x, int y);

    public void clearPlayers() {
        players.clear();
    }

    public void offsetField(int x, int y) {
        offX += x;
        offY += y;
    }
}
