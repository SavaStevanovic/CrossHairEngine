package com.crosshairengine.firstgame.engine;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.crosshairengine.firstgame.wolf_lair.Players.Player;

import java.util.ArrayList;

/**
 * Created by NikolaRancic on 5/21/2017.
 */

public class GameEngine {
    private long m_lTickLength;
    private boolean m_bRunTheGame;
    public Map m_mMap;
    public MainDrawingClass m_MainDrawClass;

    //not should be done like this, will change
    //
    public ArrayList<Player> m_alPlayer;

    public GameEngine(Context mainActivity, int width, int height)
    {
        m_mMap = new Map(width, height);
        m_MainDrawClass = MainDrawingClass.getInstance(mainActivity);
        m_lTickLength = (1000)/60;
        m_bRunTheGame = true;
        m_alPlayer = new ArrayList<Player>();
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

    public void clearPlayers()
    {
        m_alPlayer = new ArrayList<Player>();
    }

    public void addPlayer(Player player) {
        m_alPlayer.add(player);
    }

    public void drawPicture() {
        m_MainDrawClass.postInvalidate();
    }
}
