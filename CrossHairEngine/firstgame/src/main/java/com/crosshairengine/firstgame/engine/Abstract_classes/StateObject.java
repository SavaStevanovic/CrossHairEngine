package com.crosshairengine.firstgame.engine.Abstract_classes;

import android.graphics.Bitmap;
import java.util.Date;

/**
 * Created by NikolaRancic on 9/24/2017.
 */

public abstract class StateObject {
    protected Bitmap m_bitmap;

    protected String sDirectionName;
    protected int timeActionProgressBeforeObjectCreation;
    protected int timeLengthOfAction;
    protected long timeCreationOfObject;

    protected int leftOffSet;
    protected int topOffSet;

    /**
     *  base constructor for StateObject
     * @param bitmap
     * @param sDirectionName
     * @param timeActionProgressBeforeObjectCreation
     * @param timeLengthOfAction
     * @param leftOffSet
     * @param topOffSet
     */
    public StateObject(Bitmap bitmap,
                       String sDirectionName,
                       int timeActionProgressBeforeObjectCreation,
                       int timeLengthOfAction,
                       int leftOffSet,
                       int topOffSet) {
        this.m_bitmap = bitmap;

        this.sDirectionName = sDirectionName;
        this.timeActionProgressBeforeObjectCreation = timeActionProgressBeforeObjectCreation;
        this.timeLengthOfAction = timeLengthOfAction;
        this.timeCreationOfObject = new Date().getTime();

        this.leftOffSet = leftOffSet;
        this.topOffSet = topOffSet;
    }

    public StateObject() {
        this(null /*bitmap*/,
                null/*sDirectionName*/,
                0 /*timeActionProgressBeforeObjectCreation*/,
                0 /*timeLengthOfAction*/,
                0 /*leftOffSet*/,
                0 /*topOffSet*/);
    }

    public StateObject(Bitmap bitmap, String sDirectionName, int timeActionProgressBeforeObjectCreation, int timeLengthOfAction) {
        this(bitmap,
                sDirectionName,
                timeActionProgressBeforeObjectCreation,
                timeLengthOfAction,
                0 /*leftOffSet*/,
                0 /*topOffSet*/);
    }

    public void Invalidate(){}

    public int TimeALL()
    {
        return timeActionProgressBeforeObjectCreation + (int)(new Date().getTime() - timeCreationOfObject);
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
