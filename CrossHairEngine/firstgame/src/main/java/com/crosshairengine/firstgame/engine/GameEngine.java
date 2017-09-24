package com.crosshairengine.firstgame.engine;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.crosshairengine.firstgame.wolf_lair.Players.Player;
import com.crosshairengine.firstgame.wolf_lair.WebWeaverProcessor;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

/**
 * Created by NikolaRancic on 5/21/2017.
 */

public class GameEngine extends  Thread{

    private long m_lTickLength;
    private boolean m_bRunTheGame;
    public MainDrawingClass m_MainDrawClass;
    public WebWeaverProcessor m_wwp;
    //not should be done like this, will change
    //
    public ArrayList<Player> m_alPlayer;

    public GameEngine(Context mainActivity, Semaphore semProcessServerMessage)
    {
        m_MainDrawClass = MainDrawingClass.getInstance(mainActivity, semProcessServerMessage);
        m_lTickLength = 100;
        m_bRunTheGame = true;
        m_alPlayer = new ArrayList<Player>();
    }

    @Override
    public void run() {
        long processedTime = GetCurrentTimeInMillis();
        while (m_bRunTheGame)
        {
            m_MainDrawClass.postInvalidate();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
        }
        //DrawFrame();
//            while ((processedTime + m_lTickLength) < GetCurrentTimeInMillis())
//            {
//                UpdateGame();
//                processedTime +=m_lTickLength;
//            }

        }
    }

    public void UpdateGame()
    {

    }

    public void DrawFrame()
    {
       //synchronized (m_MainDrawClass) {
            m_MainDrawClass.postInvalidate();
       //}
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
