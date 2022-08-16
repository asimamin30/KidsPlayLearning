package com.fyp.autisticchildlearner.activity.video;

import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fyp.autisticchildlearner.R;
import com.fyp.autisticchildlearner.adapter.VideoListAdapter;
import com.fyp.autisticchildlearner.database.DatabaseHelper;
import com.fyp.autisticchildlearner.model.ModelVideo;
import com.fyp.autisticchildlearner.utils.Utils;

import java.util.ArrayList;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ListVideoActivity extends AppCompatActivity {

    Context context;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_video);
        context=this;
        initDefine();
    }



    private void initDefine() {
        rvVideoList=findViewById(R.id.rvVideoList);
        TextView txtTitleSubHome=findViewById(R.id.txtTitleSubHome);
        Intent intent=getIntent();
        txtTitleSubHome.setText(intent.getStringExtra("Category"));
        setRvVideoListAdapter();
    }

    RecyclerView rvVideoList;
    ArrayList<ModelVideo> arrOfVideoList;
    VideoListAdapter videoListAdapter;
    private void setRvVideoListAdapter() {
        databaseHelper=new DatabaseHelper(context);
        arrOfVideoList=databaseHelper.getVideoDetails();
        StaggeredGridLayoutManager staggeredGridLayoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        rvVideoList.setLayoutManager(staggeredGridLayoutManager);
        videoListAdapter=new VideoListAdapter(context,arrOfVideoList);
        rvVideoList.setAdapter(videoListAdapter);
    }

    public void onClickBack(View view) {
        finish();
    }
}
