package com.crosshairengine.firstgame.wolf_lair.Objects;

import android.graphics.Canvas;

import com.crosshairengine.firstgame.engine.AbstractMap;
import com.crosshairengine.firstgame.engine.Abstract_classes.StateObject;
import com.crosshairengine.firstgame.engine.Abstract_classes.Tile;
import com.crosshairengine.firstgame.wolf_lair.StateObjects.MapStateObject;

import java.util.Date;

/**
 * Created by NikolaRancic on 9/24/2017.
 */

public class Map extends AbstractMap {
    public StateObject m_state;

    public Map(String sDirection,int timeSinceBeginingOfTheAction, int width, int height)
    {
        super(sDirection, width, height);
        m_state = new MapStateObject(sDirection, timeSinceBeginingOfTheAction);
    }

    @Override
    public void Draw(Canvas canvas)
    {
        m_state.Invalidate();
        for (Tile tile : m_alTiles)
            tile.Draw(canvas, m_state.getLeftOffSet(), m_state.getTopOffSet());
    }
}
