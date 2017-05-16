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

    public Field(Context context, int x, int y) {
        super(context);
        tile_factory = new Tile_Factory(context, x, y);
        player_factory = new Player_Factory(context, x, y);
        field = new Vector<Tile>();
        players = new Vector<Player>();
        this.x = x;
        this.y = y;
    }

    public abstract void setElem(int i, int val);

    public int getYVal() {
        return y;
    }

    public int getXVal() {
        return x;
    }

    public abstract void setUserPlayer(int player_type, int x, int y);

    public abstract void addPlayer(int player_type, int x, int y);

    public void clearPlayers(){ players.clear();}
}
