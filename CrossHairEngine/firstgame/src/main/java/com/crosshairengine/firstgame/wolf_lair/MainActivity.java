package com.crosshairengine.firstgame.wolf_lair;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.crosshairengine.firstgame.R;
import com.crosshairengine.firstgame.engine.Commands.Command;
import com.crosshairengine.firstgame.engine.Direction;
import com.crosshairengine.firstgame.engine.FlyClientWriter;
import com.crosshairengine.firstgame.engine.FlyClientReceiver;
import com.crosshairengine.firstgame.engine.GameEngine;
import com.crosshairengine.firstgame.wolf_lair.Settings.Constants;
import com.crosshairengine.firstgame.wolf_lair.Settings.FlyInit;

import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Initialization();
    }

    private void DirectionButton(final Socket socket, int btnId, final Command command) {
        Button buttonUp = (Button) findViewById(btnId);
        buttonUp.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("DirectionButton", "Button clicked  " + command.toString());
                new FlyClientWriter(command, socket).execute();
            }
        });
    }

    private void Initialization(){
        FlyInit fi = new FlyInit();
        Constants.onlySocket = fi.getSocket();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.content_main);
        GameEngine main = new GameEngine(this);
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.overlay);
        layout.addView(main.m_MainDrawClass, 0);

        new FlyClientWriter(Command.CENTER, Constants.onlySocket).execute();
        DirectionButton(Constants.onlySocket,R.id.ImageButtonUp, Command.MoveUP);
        DirectionButton(Constants.onlySocket,R.id.ImageButtonDown, Command.MoveDOWN);
        DirectionButton(Constants.onlySocket,R.id.ImageButtonLeft, Command.MoveLEFT);
        DirectionButton(Constants.onlySocket,R.id.ImageButtonRight, Command.MoveRIGHT);
        DirectionButton(Constants.onlySocket,R.id.ImageButtonFire, Command.Fire);

        // Constructors
        //
        Player_Factory.getInstance(this,Constants.height, Constants.width);
        Tile_Factory.getInstance(this,Constants.height, Constants.width);

        new FlyClientReceiver(Constants.onlySocket, new WebWeaverProcessor(main)).start();
        main.start();

    }

}
