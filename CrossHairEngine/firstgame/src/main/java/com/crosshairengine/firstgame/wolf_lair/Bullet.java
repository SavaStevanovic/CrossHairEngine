package com.crosshairengine.firstgame.wolf_lair;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.crosshairengine.firstgame.engine.Abstract_classes.CDrawable;
import com.crosshairengine.firstgame.engine.Abstract_classes.StateObject;
import com.crosshairengine.firstgame.wolf_lair.StateObjects.BulletStateObject;

/**
 * Created by NikolaRancic on 9/29/2017.
 */

public class Bullet extends CDrawable {

    public StateObject m_BulletState;

    public Bullet(Bitmap bitmap, int left, int top, String sDirectionName, int timeActionProgressBeforeObjectCreation, int timeLengthOfAction)
    {
        super(bitmap, left, top);

        while(timeActionProgressBeforeObjectCreation > timeLengthOfAction){
            timeActionProgressBeforeObjectCreation = timeActionProgressBeforeObjectCreation - timeLengthOfAction;
        }

        m_BulletState = new BulletStateObject(sDirectionName, timeActionProgressBeforeObjectCreation, timeLengthOfAction);
    }

    @Override
    public void Draw(Canvas canvas)
    {
        m_BulletState.Invalidate();
        super.Draw(canvas, m_BulletState.getLeftOffSet(), m_BulletState.getTopOffSet());
    }
}
