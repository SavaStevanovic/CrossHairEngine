package com.crosshairengine.firstgame.engine.Abstract_classes;

import android.content.Context;
import android.view.View;

import com.crosshairengine.firstgame.wolf_lair.Tile_Factory;

/**
 * Created by CrossHairEngine team on 4/27/2017.
 */

//Should be central object of the system
//All interactions should be done trough map
//
public abstract class Field extends View {
    protected Tile[][] field;
    protected Tile_Factory tile_factory;
    protected final int x,y;
    public Field(Context context, int x, int y) {
        super(context);
        tile_factory = new Tile_Factory(context, x, y);
        field = new Tile[x][y];
        this.x=x;
        this.y=y;
    }
    public abstract void setElem(int x, int y,int val);

    public int getXVal(){return x;}
    public int getYVal(){return y;}
}
