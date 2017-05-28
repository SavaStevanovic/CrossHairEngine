package com.crosshairengine.firstgame.engine;

import android.graphics.Canvas;

import com.crosshairengine.firstgame.engine.Abstract_classes.CDrawable;
import com.crosshairengine.firstgame.engine.Abstract_classes.Tile;

import java.util.ArrayList;

/**
 * Created by NikolaRancic on 5/21/2017.
 * this is client's map (it's more a as a composite for tiles)
 */

public class Map extends CDrawable {
    private ArrayList<Tile> m_alTiles;
    private int width;
    private int height;

    public Map()
    {
        super();
        m_alTiles = new ArrayList<>();
    }

    public Map(ArrayList<Tile> al)
    {
        m_alTiles = al;
    }

    public Tile getTileOnPosition(int fromLeft,int fromTop)
    {
        int positionInArray = fromTop * width + fromLeft;
        return m_alTiles.get(positionInArray);
    }

    public void Draw(Canvas canvas)
    {
        for (Tile tile : m_alTiles)
            tile.Draw(canvas);
    }

}
