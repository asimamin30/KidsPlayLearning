package com.fyp.autisticchildlearner.activity;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.fyp.autisticchildlearner.UserCred.ProfileActiviy;
import com.fyp.autisticchildlearner.catemodel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;
import com.fyp.autisticchildlearner.R;
import com.fyp.autisticchildlearner.adapter.HomeAdapter;
import com.fyp.autisticchildlearner.customclasses.Constant;
import com.fyp.autisticchildlearner.database.DatabaseHelper;
import com.fyp.autisticchildlearner.interfaces.CallbackListener;
import com.fyp.autisticchildlearner.utils.Utils;

import java.util.ArrayList;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity implements CallbackListener {

    Context context;
    DatabaseHelper databaseHelper;
    private ArrayList<catemodel> model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

                Utils.setPref(MainActivity.this, Constant.AD_TYPE_FB_GOOGLE, Constant.AD_TYPE_FACEBOOK_GOOGLE);
                Utils.setPref(MainActivity.this, Constant.FB_BANNER, Constant.FB_BANNER_ID);
                Utils.setPref(MainActivity.this, Constant.FB_INTERSTITIAL, Constant.FB_INTERSTITIAL_ID);
                Utils.setPref(MainActivity.this, Constant.GOOGLE_BANNER, Constant.GOOGLE_BANNER_ID);
                Utils.setPref(MainActivity.this, Constant.GOOGLE_INTERSTITIAL, Constant.GOOGLE_INTERSTITIAL_ID);
                Utils.setPref(MainActivity.this, Constant.STATUS_ENABLE_DISABLE, Constant.ENABLE_DISABLE);

                setAppAdId(Constant.GOOGLE_ADMOB_APP_ID);
            } else {
                Utils.setPref(MainActivity.this, Constant.STATUS_ENABLE_DISABLE, Constant.ENABLE_DISABLE);
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

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("LearningData");
        reference.orderByChild("LearningData")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        //clear list before adding
                        for(DataSnapshot ds: snapshot.getChildren())
                        {
//                            model.clear();
                            catemodel modelShop = ds.getValue(catemodel.class);
//                            model.add(modelShop);

                            if (modelShop.getHomedata().equals("home")  ){
                                arrOfCategory = new int[]{R.drawable.card_one, R.drawable.card_three, R.drawable.card_four,R.drawable.quiz,R.drawable.card_two,R.drawable.u};
                                setRvAdapter();
                            }


                        }
                        //setup adapter

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(getApplicationContext(),""+error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });



    }


    public void onClickSetting(View view){
        Intent intent= new Intent(MainActivity.this,SettingActivity.class);
        startActivityForResult(intent,111);
    }

    HomeAdapter homeAdapter;
    RecyclerView rvCategory;
    private void setRvAdapter() {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
        rvCategory.setLayoutManager(linearLayoutManager);
        homeAdapter=new HomeAdapter(context,arrOfCategory);
        rvCategory.setAdapter(homeAdapter);
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

    public void onClickProfile(View view) {

        Intent intent = getIntent();
        if(intent!=null){
            String str_username = intent.getStringExtra("str_username");

        Intent i= new Intent(MainActivity.this, ProfileActiviy.class);
        i.putExtra("str_username",str_username);
        startActivity(i);

    }
    }
}
