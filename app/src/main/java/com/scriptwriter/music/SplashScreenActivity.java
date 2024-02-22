package com.scriptwriter.music;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import com.scriptnetwork.library.MainActivity;
import androidx.appcompat.app.AppCompatActivity;

@SuppressLint("CustomSplashScreen")
public class SplashScreenActivity extends AppCompatActivity {

    private int currentApiVersion;
    String Fullscreen;
    String Active = "ENABLED";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Fullscreen = this.getResources().getString(R.string.fullscreen_status);

        if (Active.equalsIgnoreCase(Fullscreen)) {
            this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            currentApiVersion = android.os.Build.VERSION.SDK_INT;
            final int flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                    View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            if (currentApiVersion >= Build.VERSION_CODES.KITKAT) {
                getWindow().getDecorView().setSystemUiVisibility(flags);
                final View decorView = getWindow().getDecorView();
                decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
                    @Override
                    public void onSystemUiVisibilityChange(int visibility) {
                        if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
                            decorView.setSystemUiVisibility(flags);
                        }
                    }
                });
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
            finish();
        }
        else {
            new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
                finish();
            }
        }, 3000);
        }
    }

    public void onWindowFocusChanged(boolean hasFocus) {
        if (Active.equalsIgnoreCase(Fullscreen)) {
            super.onWindowFocusChanged(hasFocus);
            if (currentApiVersion >= Build.VERSION_CODES.KITKAT && hasFocus) {
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
            }
        }
    }
}