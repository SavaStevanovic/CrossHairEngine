package com.crosshairengine.firstgame.engine;

/**
 * Created by NikolaRancic on 5/21/2017.
 */

public class GameEngine {
    public long m_lTickLength;
    public boolean m_bRunTheGame;
    public MainDrawingClass m_MainDrawClass;
    public GameEngine(long framesPerSecond)
    {
        m_lTickLength = (1000)/framesPerSecond;
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
