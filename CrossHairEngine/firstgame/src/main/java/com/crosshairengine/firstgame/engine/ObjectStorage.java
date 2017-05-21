package com.crosshairengine.firstgame.engine;

import com.crosshairengine.firstgame.engine.Abstract_classes.CDrawable;
import com.crosshairengine.firstgame.engine.Interfaces.Updatable;

import java.util.ArrayList;

/**
 * Created by NikolaRancic on 5/21/2017.
 */

public class ObjectStorage {
    private static ObjectStorage _instance;

    private ObjectStorage()
    {
        // 5 is just a number (shouldn't be here)
        m_alDrawableObjects = new ArrayList<ArrayList<CDrawable>>(5);
    }

    public static ObjectStorage getInstance()
    {
        if (_instance == null)
            _instance = new ObjectStorage();
        return _instance;
    }

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
        m_alDrawableObjects = null;
    }

    public ArrayList<ArrayList<CDrawable>> getAllDrawableObjects()
    {
        return m_alDrawableObjects;
    }

    // part for storing updatable objects
    // arraylist is probably not the best solution
    // but since right now we dont know what it is
    // we will go with this
    //
    private ArrayList<Updatable> m_alUpdatableObjects;

    public void addUpdatableObject(Updatable upObj)
    {
        m_alUpdatableObjects.add(upObj);
    }

    public void clearUpdatableObjectStorage()
    {
        m_alUpdatableObjects = null;
    }

    public ArrayList<Updatable> getAllUpdateableObjects()
    {
        return m_alUpdatableObjects;
    }

}
