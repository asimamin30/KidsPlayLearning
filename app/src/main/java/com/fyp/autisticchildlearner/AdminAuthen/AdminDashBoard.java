package com.fyp.autisticchildlearner.AdminAuthen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.fyp.autisticchildlearner.R;
import com.fyp.autisticchildlearner.activity.MainActivity;
import com.fyp.autisticchildlearner.activity.SettingActivity;
import com.fyp.autisticchildlearner.adapter.HomeAdapter;
import com.fyp.autisticchildlearner.customclasses.Constant;
import com.fyp.autisticchildlearner.database.DatabaseHelper;
import com.fyp.autisticchildlearner.interfaces.CallbackListener;
import com.fyp.autisticchildlearner.utils.Utils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

public class AdminDashBoard extends AppCompatActivity implements CallbackListener {

    Context context;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dash_board);

        context=this;
        initDefine();
        databaseHelper = new DatabaseHelper(context);
        try {
            databaseHelper.createDataBase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        successCall();
        try {
            //subScribeToFirebaseTopic();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void subScribeToFirebaseTopic() {
        FirebaseMessaging.getInstance().subscribeToTopic("kids_play_topic")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (!task.isSuccessful()) {
                            Log.e("subScribeFirebaseTopic", ": Fail");
                        } else {
                            Log.e("subScribeFirebaseTopic", ": Success");
                        }
                    }
                });

    }


    private void successCall() {
        if (Utils.isNetworkConnected(this)) {
            if (Constant.ENABLE_DISABLE.equals(Constant.ENABLE)) {

                Utils.setPref(AdminDashBoard.this, Constant.AD_TYPE_FB_GOOGLE, Constant.AD_TYPE_FACEBOOK_GOOGLE);
                Utils.setPref(AdminDashBoard.this, Constant.FB_BANNER, Constant.FB_BANNER_ID);
                Utils.setPref(AdminDashBoard.this, Constant.FB_INTERSTITIAL, Constant.FB_INTERSTITIAL_ID);
                Utils.setPref(AdminDashBoard.this, Constant.GOOGLE_BANNER, Constant.GOOGLE_BANNER_ID);
                Utils.setPref(AdminDashBoard.this, Constant.GOOGLE_INTERSTITIAL, Constant.GOOGLE_INTERSTITIAL_ID);
                Utils.setPref(AdminDashBoard.this, Constant.STATUS_ENABLE_DISABLE, Constant.ENABLE_DISABLE);

                setAppAdId(Constant.GOOGLE_ADMOB_APP_ID);
            } else {
                Utils.setPref(AdminDashBoard.this, Constant.STATUS_ENABLE_DISABLE, Constant.ENABLE_DISABLE);
            }
        } else {
            Utils.openInternetDialog(this, true,this);
        }
    }


    public void setAppAdId(String id) {
        try {
            ApplicationInfo applicationInfo = getPackageManager().getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
            Bundle bundle = applicationInfo.metaData;
            String beforeChangeId = bundle.getString("com.google.android.gms.ads.APPLICATION_ID");
            Log.e("TAG", "setAppAdId:BeforeChange:::::  " + beforeChangeId);
            applicationInfo.metaData.putString("com.google.android.gms.ads.APPLICATION_ID", id);
            String AfterChangeId = bundle.getString("com.google.android.gms.ads.APPLICATION_ID");
            Log.e("TAG", "setAppAdId:AfterChange::::  " + AfterChangeId);
        } catch (PackageManager.NameNotFoundException | NullPointerException e) {
            e.printStackTrace();
        }

    }


    public int[] arrOfCategory;
    private void initDefine() {
        rvCategory=findViewById(R.id.rvCategory);
        arrOfCategory = new int[]{R.drawable.card_one, R.drawable.card_two, R.drawable.card_three, R.drawable.card_four};
        setRvAdapter();
    }


    public void onClickSetting(View view){
        Intent intent= new Intent(AdminDashBoard.this, SettingActivity.class);
        startActivityForResult(intent,111);
    }

    AdminHomeAdapter adminHomeAdapter;
    RecyclerView rvCategory;
    private void setRvAdapter() {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
        rvCategory.setLayoutManager(linearLayoutManager);
        adminHomeAdapter=new AdminHomeAdapter(context,arrOfCategory);
        rvCategory.setAdapter(adminHomeAdapter);
    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onCancel() {

    }

    @Override
    public void onRetry() {

    }
}
