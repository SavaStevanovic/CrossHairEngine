package com.crosshairengine.firstgame.wolf_lair;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.crosshairengine.firstgame.R;
import com.crosshairengine.firstgame.engine.Direction;
import com.crosshairengine.firstgame.engine.FlyClientWriter;
import com.crosshairengine.firstgame.engine.FlyClientReceiver;
import com.crosshairengine.firstgame.wolf_lair.Settings.FlyInit;

import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FlyInit fi = new FlyInit();
        Socket socket = fi.getSocket();

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.content_main);
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.overlay);
        BattleField main = new BattleField(this);
        layout.addView(main, 0);

        new FlyClientWriter(Direction.CENTER, socket).execute();

        DirectionButton(socket,R.id.ImageButtonUp, Direction.UP);
        DirectionButton(socket,R.id.ImageButtonDown, Direction.DOWN);
        DirectionButton(socket,R.id.ImageButtonLeft, Direction.LEFT);
        DirectionButton(socket,R.id.ImageButtonRight, Direction.RIGHT);
        new FlyClientReceiver(socket, new WebWeaverProcessor(main)).start();
    }

    private void DirectionButton(final Socket socket, int btnId, final Direction direction) {
        Button buttonUp = (Button) findViewById(btnId);
        buttonUp.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                new FlyClientWriter(direction, socket).execute();
            }
        });
    }
}
