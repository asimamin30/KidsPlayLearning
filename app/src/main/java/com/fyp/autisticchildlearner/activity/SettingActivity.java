package com.fyp.autisticchildlearner.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;


import com.fyp.autisticchildlearner.R;
import com.fyp.autisticchildlearner.customclasses.Constant;
import com.fyp.autisticchildlearner.utils.Utils;

import static com.fyp.autisticchildlearner.customclasses.Constant.switchState;

public class SettingActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initDefine();
    }


    Switch soundOnOff;
    private void initDefine() {
        soundOnOff=findViewById(R.id.soundOnOff);
        if(Utils.getPref(Constant.SOUND,true)){
            soundOnOff.setChecked(true);
            switchState=true;
        }else{
            switchState=false;
            soundOnOff.setChecked(false);
        }
        soundOnOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(switchState){
                    switchState=false;
                    soundOnOff.setChecked(false);
                    Utils.setPref(Constant.SOUND,false);
                }else {
                    switchState=true;
                    soundOnOff.setChecked(true);
                    Utils.setPref(Constant.SOUND,true);
                }
            }
        });

    }

    public void onClickBack(View view) {
        finish();
    }

}
