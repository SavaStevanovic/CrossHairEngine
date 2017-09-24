package com.crosshairengine.firstgame.wolf_lair.Objects;

import android.graphics.Canvas;

import com.crosshairengine.firstgame.engine.AbstractMap;
import com.crosshairengine.firstgame.engine.Abstract_classes.StateObject;
import com.crosshairengine.firstgame.engine.Abstract_classes.Tile;
import com.crosshairengine.firstgame.wolf_lair.StateObjects.MapStateObject;
import com.crosshairengine.firstgame.wolf_lair.TileFactories.Tile_Factory_Abstract_Factory;

import java.util.Date;

/**
 * Created by NikolaRancic on 9/24/2017.
 */

public class Map extends AbstractMap {
    public StateObject m_state;

    public Map(String sDirection,
               int timeSinceBeginingOfTheAction,
               String[] stringArray,
               int width,
               int height)
    {
        super(sDirection, width, height);

        for (int i = 0; i < stringArray.length; i++) {
            //this is bad we use twice i, i will change this
            //
            this.setTile(Tile_Factory_Abstract_Factory.GetFactory(sDirection).getTile(Integer.parseInt(stringArray[i]), i), i);
        }
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
