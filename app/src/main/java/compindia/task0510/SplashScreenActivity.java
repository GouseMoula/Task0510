package compindia.task0510;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                proceed();
            }
        }, 3000);

    }

    void proceed() {
        if (!checkRegistrationStatus()) {
            Intent intent = new Intent(SplashScreenActivity.this, RegistrationActivity.class);
            startActivity(intent);
        } else if (!checkLoginStatus()) {
            Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
            startActivity(intent);
        }
        finish();
    }

    boolean checkRegistrationStatus() {
        return MyPrefs.isRegstered(this, MyConsts.REGISTERED_KEY);
    }

    boolean checkLoginStatus() {
        return MyPrefs.isRegstered(this, MyConsts.LOGGED_IN_KEY);
    }

}
