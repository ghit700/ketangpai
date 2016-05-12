package com.ketangpai.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;

import com.ketangpai.nan.ketangpai.R;

/**
 * Created by nan on 2016/3/9.
 */
public class SplashAcitvity extends AppCompatActivity {

    Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        mContext = this;


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                String account = getSharedPreferences("user", 0).getString("account", "");
                String password = getSharedPreferences("user", 0).getString("password", "");

                if (!account.equals("") && !password.equals("")) {
                    Log.i(LoginActivity.TAG, "account=" + account + "  password=" + password);
                    startActivity(new Intent(mContext, MainActivity.class));
                } else {
                    startActivity(new Intent(mContext, LoginActivity.class));
                }

                finish();
            }
        }, 1500);


    }
}
