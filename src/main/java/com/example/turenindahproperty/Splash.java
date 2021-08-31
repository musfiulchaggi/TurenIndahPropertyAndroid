package com.example.turenindahproperty;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Window;
public class Splash extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
        Thread timer = new Thread()
        {
            public void run()
            {
                try {
                    sleep(4000);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    finish();
                    Intent m = new Intent(Splash.this, MainActivity.class);
                    startActivityForResult(m,0);
                }
            }
        };
        timer.start();
    }
}