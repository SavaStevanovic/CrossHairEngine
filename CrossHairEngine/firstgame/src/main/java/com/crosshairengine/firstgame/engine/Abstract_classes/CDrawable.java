package com.crosshairengine.firstgame.engine.Abstract_classes;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by NikolaRancic on 5/21/2017.
 * Abstract so that it doesn't get instantiated
 */

public abstract class CDrawable {
    protected Bitmap m_bSprate;
    protected int leftPx;
    protected int topPx;

    public CDrawable(){}

    public CDrawable(Bitmap bitmap,int leftPx, int topPx)
    {
        m_bSprate = bitmap;
        this.topPx = topPx;
        this.leftPx = leftPx;
    }

    public void Draw(Canvas canvas, int leftOffSet, int topOffSet)
    {
        canvas.drawBitmap(m_bSprate, leftPx + leftOffSet, topPx + topOffSet, null);
    }

    public void Draw(Canvas canvas)
    {
        Draw(canvas, 0, 0);
    }
}
