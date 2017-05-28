package com.crosshairengine.firstgame.engine.Abstract_classes;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by NikolaRancic on 5/21/2017.
 * Abstract so that it doesn't get instantiated
 */

public abstract class CDrawable {
    protected Bitmap m_bSprate;
    protected int left;
    protected int top;

    public CDrawable(){}

    public CDrawable(Bitmap bitmap,int left, int top)
    {
        m_bSprate = bitmap;
        this.top = top;
        this.left = left;
    }

    public void Draw(Canvas canvas){ canvas.drawBitmap(m_bSprate, left, top, null);}

}
