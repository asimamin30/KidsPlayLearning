package com.fyp.autisticchildlearner.AdminAuthen;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.fyp.autisticchildlearner.R;
import com.fyp.autisticchildlearner.activity.HomeActivity;
import com.fyp.autisticchildlearner.activity.video.VideoLearningActivity;
import com.fyp.autisticchildlearner.adapter.HomeAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class AdminHomeAdapter  extends RecyclerView.Adapter<AdminHomeAdapter.ViewHolder> {

    Context context;
    int[] arrOfCategory;
    String Homedata,videoLearning;


    public AdminHomeAdapter(Context context, int[] arrOfCategory) {
        this.context = context;
        this.arrOfCategory = arrOfCategory;
    }

    public int getItemCount() {
        return arrOfCategory.length;
    }


    @NonNull
    public AdminHomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new AdminHomeAdapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.admin_rc_card, viewGroup, false));
    }

    public void onBindViewHolder(@NonNull final AdminHomeAdapter.ViewHolder viewHolder, final int i) {

        Glide.with(context).load(arrOfCategory[i]).apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)).into(viewHolder.imgThumbnail);

        viewHolder.cVHomeCategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (i) {
                    case 0:
//                        Intent intent1 = new Intent(context, HomeActivity.class);
                        Homedata="home";
//                        intent1.putExtra("Type", 1);
//                        context.startActivity(intent1);
                        break;
                    case 1:
//                        Intent intent2 = new Intent(context, VideoLearningActivity.class);
                        videoLearning="videolearning";
//                        context.startActivity(intent2);
                        break;
                    case 2:
//                        Intent intent3 = new Intent(context, HomeActivity.class);
//                        intent3.putExtra("Type", 2);
//                        context.startActivity(intent3);
                        break;
                    case 3:
//                        Intent intent4 = new Intent(context, HomeActivity.class);
//                        intent4.putExtra("Type", 3);
//                        context.startActivity(intent4);
                        break;

                }


                //creating a popup menu
                PopupMenu popup = new PopupMenu(context, viewHolder.cVHomeCategories);
                //inflating menu from xml resource
                popup.inflate(R.menu.menu);
                //adding click listener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.Addtodatabase:
                                OpenDatabaseToSaveData();
                                return true;


                            default:
                                return false;
                        }
                    }
                });
                //displaying the popup
                popup.show();




//

            }
        });
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        CardView cVHomeCategories;
        ImageView imgThumbnail;

        ViewHolder(@NonNull View view) {
            super(view);
            this.cVHomeCategories = view.findViewById(R.id.cVHomeCategories);
            this.imgThumbnail = view.findViewById(R.id.imgThumbnail);
        }
    }
    private void OpenDatabaseToSaveData() {


        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("LearningData");
        HashMap<String, Object> data = new HashMap<>();
        data.put("homedata", Homedata);
        data.put("videoLearning", videoLearning);


        reference.child("LearningData").setValue(data)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(context, "Data Added successfully.", Toast.LENGTH_LONG).show();

                        } else {
                            Toast.makeText(context, "Data Added failed. Please try again.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


}


//        viewHolder.cVHomeCategories.setOnClickListener(new OnClickListener() {
//            public void onClick(View view) {
//                switch (i) {
//                    case null:
//                        StartActivity.this.startActivity(new Intent(StartActivity.this.getApplicationContext(), HomeActivity.class));
//                        return;
//                    case 1:
//                        if (StartActivity.this.isNetworkConnected() != null) {
//                            StartActivity.this.startActivity(new Intent(StartActivity.this.getApplicationContext(), VideoLearningActivity.class));
//                            StartActivity.requestInterstitialAds();
//                            return;
//                        }
//                        Toast.makeText(StartActivity.this.getApplicationContext(), "Turn on internet connection", 1).show();
//                        return;
//                    case 2:
//                        StartActivity.this.startActivity(new Intent(StartActivity.this.getApplicationContext(), ExamMainActivity.class).putExtra("index", 2));
//                        return;
//                    case 3:
//                        StartActivity.this.startActivity(new Intent(StartActivity.this.getApplicationContext(), ExamMainActivity.class).putExtra("index", 3));
//                        return;
//                    default:
//                        return;
//                }
//            }
//        });