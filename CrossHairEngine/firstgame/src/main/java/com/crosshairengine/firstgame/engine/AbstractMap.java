package com.crosshairengine.firstgame.engine;

import android.graphics.Canvas;

import com.crosshairengine.firstgame.engine.Abstract_classes.CDrawable;
import com.crosshairengine.firstgame.engine.Abstract_classes.Tile;

import java.util.ArrayList;

/**
 * Created by NikolaRancic on 5/21/2017.
 * this is client's map (it's more a as a composite for tiles)
 */

public abstract class AbstractMap extends CDrawable {
    protected ArrayList<Tile> m_alTiles;
    protected int width;
    protected int height;

    public AbstractMap(String sDirection, int width, int height)
    {
        super();

        this.width = width;
        this.height = height;

        if (sDirection.equals("left") || sDirection.equals("right"))
        {
            this.width = this.width + 1;
        }
        else if (sDirection.equals("down") || sDirection.equals("up"))
        {
            this.height = this.height + 1;
        }

        m_alTiles = new ArrayList<Tile>(width * height);
        for(int i = 0; i< width * height; i++)
            m_alTiles.add(null);
    }

    public AbstractMap(ArrayList<Tile> al)
    {
        m_alTiles = al;
    }

    public void setTile(Tile tile, int position)
    {
        m_alTiles.set(position, tile);
    }

    public Tile getTileOnPosition(int fromLeft,int fromTop)
    {
        int positionInArray = fromTop * width + fromLeft;
        return m_alTiles.get(positionInArray);
    }

    public void Draw(Canvas canvas, int leftOffSet, int topOffSet)
    {
        for (Tile tile : m_alTiles)
            tile.Draw(canvas, leftOffSet, topOffSet);
    }

}
