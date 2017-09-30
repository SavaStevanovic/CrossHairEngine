package com.crosshairengine.firstgame.wolf_lair.Players;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.crosshairengine.firstgame.engine.Abstract_classes.CDrawable;
import com.crosshairengine.firstgame.engine.Abstract_classes.StateObject;
import com.crosshairengine.firstgame.wolf_lair.StateObjects.EnemyPlayerStateObject;

/**
 * Created by Sava on 5/9/2017.
 */

public class Player_enemy extends Player {

    public StateObject m_EnemyPlayerState;

    public Player_enemy(Bitmap bitmap, int x, int y, String sDirectionName, int timeActionProgressBeforeObjectCreation, int timeLengthOfAction)
    {
        super(bitmap, x, y);

        m_EnemyPlayerState = new EnemyPlayerStateObject(sDirectionName, timeActionProgressBeforeObjectCreation, timeLengthOfAction);
    }

    @Override
    public void Draw(Canvas canvas)
    {
        m_EnemyPlayerState.Invalidate();
        super.Draw(canvas, m_EnemyPlayerState.getLeftOffSet(), m_EnemyPlayerState.getTopOffSet());
    }
}
