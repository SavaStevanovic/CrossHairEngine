package com.crosshairengine.firstgame.wolf_lair;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.crosshairengine.firstgame.R;
import com.crosshairengine.firstgame.engine.FlyClient;
import com.crosshairengine.firstgame.engine.WebWeaverConnection;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    BattleField main;
    //Server server;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        WebWeaverConnection ww = new WebWeaverConnection();
        //ww.run();
        super.onCreate(savedInstanceState);
        main = new BattleField(this);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.content_main);
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.overlay);
        layout.addView(main, 0);
        Button buttonOne = (Button) findViewById(R.id.ImageButton01);
        buttonOne.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                 new FlyClient().execute();
            }
        });
    }
}
