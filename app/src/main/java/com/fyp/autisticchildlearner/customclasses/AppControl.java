package com.fyp.autisticchildlearner.customclasses;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.widget.Toast;

import com.fyp.autisticchildlearner.R;
import com.fyp.autisticchildlearner.utils.Utils;

import java.util.Locale;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;


public class AppControl extends Application {
    Context context;
    public static TextToSpeech textToSpeech;
    @Override
    public void onCreate() {
        super.onCreate();
        context=this;
        new Utils(context);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("Fon/OhWhale.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
        textToSpeech = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    textToSpeech.setLanguage(Locale.UK);
//                    textToSpeech.setLanguage(Locale.forLanguageTag(""));
                }
            }
        });
    }


}
