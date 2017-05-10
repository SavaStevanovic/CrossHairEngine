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

import static android.R.attr.direction;

public class MainActivity extends AppCompatActivity {
    BattleField main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        main = new BattleField(this);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.content_main);
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.overlay);
        layout.addView(main, 0);
        new FlyClientRecever(main).execute();
        new FlyClient(FlyClient.Direction.CENTER).execute();
        DirectionButton(R.id.ImageButtonUp,FlyClient.Direction.UP);
        DirectionButton(R.id.ImageButtonDown,FlyClient.Direction.DOWN);
        DirectionButton(R.id.ImageButtonLeft,FlyClient.Direction.LEFT);
        DirectionButton(R.id.ImageButtonRight,FlyClient.Direction.RIGHT);
    }

    private void DirectionButton( int btnId, final FlyClient.Direction direction) {
        Button buttonUp = (Button) findViewById(btnId);
        buttonUp.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                new FlyClient(direction).execute();
            }
        });
    }
}
