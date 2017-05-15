package com.crosshairengine.firstgame.wolf_lair;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.crosshairengine.firstgame.R;
import com.crosshairengine.firstgame.engine.FlyClient;
import com.crosshairengine.firstgame.engine.FlyClientRecever;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import static android.R.attr.direction;

public class MainActivity extends AppCompatActivity {
    BattleField main;
    Socket socket;
    String hostName = "192.168.0.103";
    int portNumber = 4321;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        main = new BattleField(this);
        FlyInit fi=new FlyInit(socket);
        fi.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        socket=fi.getSocket();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.content_main);
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.overlay);
        layout.addView(main, 0);

        new FlyClient(FlyClient.Direction.CENTER,socket).execute();

        DirectionButton(R.id.ImageButtonUp,FlyClient.Direction.UP);
        DirectionButton(R.id.ImageButtonDown,FlyClient.Direction.DOWN);
        DirectionButton(R.id.ImageButtonLeft,FlyClient.Direction.LEFT);
        DirectionButton(R.id.ImageButtonRight,FlyClient.Direction.RIGHT);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new FlyClientRecever(main,socket).start();
    }

    private void DirectionButton( int btnId, final FlyClient.Direction direction) {
        Button buttonUp = (Button) findViewById(btnId);
        buttonUp.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                new FlyClient(direction,socket).execute();
            }
        });
    }
}
