package com.crosshairengine.firstgame.engine;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by NikolaRancic on 5/21/2017.
 */

public class GameEngine {
    private long m_lTickLength;
    private boolean m_bRunTheGame;


    public MainDrawingClass m_MainDrawClass;

    public GameEngine(Context mainActivity)
    {
        m_MainDrawClass = MainDrawingClass.getInstance(mainActivity);
        m_lTickLength = (1000)/60;
        m_bRunTheGame = true;
    }

    public void StartGame() 
    {
        long processedTime = GetCurrentTimeInMillis();
        while (m_bRunTheGame)
        {
            DrawFrame();
            while ((processedTime + m_lTickLength) < GetCurrentTimeInMillis())
            {
                UpdateGame();
                processedTime +=m_lTickLength;
            }

        }
    }

    public void UpdateGame()
    {

    }

    public void DrawFrame()
    {

    }

    private long GetCurrentTimeInMillis()
    {
        return System.currentTimeMillis();
    }
}
