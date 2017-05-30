package com.crosshairengine.firstgame.engine;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

import com.crosshairengine.firstgame.engine.Abstract_classes.CDrawable;

import java.util.ArrayList;

/**
 * Created by NikolaRancic on 5/21/2017.
 * Drawing level means order of drawing on screen
 * array with index 0 means that elements in that array are drawn first
 * elements in array with index 1 are drawn afterwards and so on
 *
 * ***BAD you always need to give context when getInstance so that is bad design,
 *  so watch out for now!
 */

public class MainDrawingClass extends View {

    //**********************************
    // part for storing cdrawable objects
    // not best solution because it depends on order of how maindrawingclass
    // is drawing objects
    //
    private ArrayList<ArrayList<CDrawable>> m_alDrawableObjects;

    public void addCDrawableArrayList(int drawingLevel, ArrayList<CDrawable> al)
    {
        m_alDrawableObjects.add(drawingLevel, al);
    }

    public void clearCDrawableObjectStorage()
    {
        m_alDrawableObjects = new ArrayList<ArrayList<CDrawable>>(5);
    }

    public ArrayList<ArrayList<CDrawable>> getAllDrawableObjects()
    {
        return m_alDrawableObjects;
    }
    //**********************************

    private static MainDrawingClass _instance = null;
    private MainDrawingClass(Context context)
    {
        super(context);
        m_alDrawableObjects = new ArrayList<ArrayList<CDrawable>>(5);
    }


    public static MainDrawingClass getInstance(Context context){
        if (_instance == null)
            _instance = new MainDrawingClass(context);
        return _instance;
    }

    public void DrawAll(Canvas canvas)
    {
        for(ArrayList<CDrawable> iteratorList :m_alDrawableObjects)
            for(CDrawable iteratorDrawable : iteratorList)
                iteratorDrawable.Draw(canvas);
    }

    @Override
    public void onDraw(Canvas canvas) {
        DrawAll(canvas);
    }

}
