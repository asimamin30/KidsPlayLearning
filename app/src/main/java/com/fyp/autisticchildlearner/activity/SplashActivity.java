package com.fyp.autisticchildlearner.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.fyp.autisticchildlearner.R;
import com.fyp.autisticchildlearner.UserCred.LoginActivity;
import com.fyp.autisticchildlearner.customclasses.Constant;
import com.fyp.autisticchildlearner.interfaces.CallbackListener;
import com.fyp.autisticchildlearner.utils.Utils;

public class SplashActivity extends AppCompatActivity implements CallbackListener {
    /*For Internet*/
    @Override
    public void onSuccess() {

    }

    @Override
    public void onCancel() {

    }

    @Override
    public void onRetry() {
        callApi();
    }


    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        context = this;
        callApi();

    }

    public void callApi() {
        if (Utils.isNetworkConnected(this)) {
            successCall();
        } else {
            Utils.openInternetDialog(this, true,this);
        }
    }

    private void successCall() {
        if (Utils.getPref(this, Constant.SPLASH_SCREEN_COUNT, 1) == 1) {
            Log.e("TAG", "successCall::::IFFFFF " + Utils.getPref(this, Constant.SPLASH_SCREEN_COUNT, 1));
            Utils.setPref(this, Constant.SPLASH_SCREEN_COUNT, 2);

            startNextActivity(1000);
        } else {
            Log.e("TAG", "successCall::::ELSEEE " + Utils.getPref(this, Constant.SPLASH_SCREEN_COUNT, 1));
            startNextActivity(1000);
        }
    }

    public void startNextActivity(Integer time) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, time);
    }


}

