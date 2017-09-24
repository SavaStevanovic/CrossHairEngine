package com.crosshairengine.firstgame.engine.Abstract_classes;

import android.graphics.Bitmap;
import java.util.Date;

/**
 * Created by NikolaRancic on 9/24/2017.
 */

public class StateObject {
    protected Bitmap m_bitmap;

    protected String sActionName;
    protected int timeActionProgressBeforeObjectCreation;
    protected long timeCreationOfObject;

    protected int leftOffSet;
    protected int topOffSet;

    public StateObject() {
        this.m_bitmap = null;

        this.sActionName = null;
        this.timeActionProgressBeforeObjectCreation = 0;
        this.timeCreationOfObject = new Date().getTime();

        this.leftOffSet = 0;
        this.topOffSet = 0;
    }

    public void Invalidate(){}

    public int TimeALL()
    {
        return timeActionProgressBeforeObjectCreation + (int)(new Date().getTime() - timeCreationOfObject);
    }

    public StateObject(Bitmap bitmap)
    {
        this.m_bitmap = bitmap;
    }

    public Bitmap getM_bitmap() {

        return m_bitmap;
    }

    public int getLeftOffSet() {
        return leftOffSet;
    }

    public int getTopOffSet() {
        return topOffSet;
    }
}
