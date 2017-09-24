package com.crosshairengine.firstgame.wolf_lair.TileFactories;


import android.content.Context;
/**
 * Created by NikolaRancic on 9/24/2017.
 */

public final class Tile_Factory_Abstract_Factory {

    private static Tile_Factory_Abstract_Factory _instance;

    private Tile_Factory_DownUp m_tfDownUp;
    private Tile_Factory_LeftRight m_tfLeftRight;

    public static Tile_Factory_Abstract_Factory Initialize(Context context, int height_count, int weight_count )
    {
        if (_instance == null)
            _instance = new Tile_Factory_Abstract_Factory(context, height_count, weight_count);
        return _instance;
    }

    private Tile_Factory_Abstract_Factory(Context context, int height_count, int weight_count){
        m_tfDownUp = new Tile_Factory_DownUp(context, height_count, weight_count);
        m_tfLeftRight = new Tile_Factory_LeftRight(context, height_count, weight_count);
    }

    public static Tile_Factory_Base GetFactory(String sDirection){
        switch(sDirection)
        {
            case "down":
                return _instance.m_tfDownUp;
            case "left":
                return _instance.m_tfLeftRight;
            case "up":
                return _instance.m_tfDownUp;
            case "right":
                return _instance.m_tfLeftRight;
            case "center":
                return _instance.m_tfDownUp;
        }
        return null;
    }
}
