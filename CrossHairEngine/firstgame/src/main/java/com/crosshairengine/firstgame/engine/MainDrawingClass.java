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
    private static MainDrawingClass _instance = null;
    private MainDrawingClass(Context context)
    {
        super(context);
    }


    public static MainDrawingClass getInstance(Context context){
        if (_instance == null)
            _instance = new MainDrawingClass(context);
        return _instance;
    }

    public void DrawAll(Canvas canvas, ArrayList<ArrayList<CDrawable>> alDrawableObjects)
    {
        for(ArrayList<CDrawable> iteratorList :alDrawableObjects)
            for(CDrawable iteratorDrawable : iteratorList)
                iteratorDrawable.Draw(canvas);
    }

}
