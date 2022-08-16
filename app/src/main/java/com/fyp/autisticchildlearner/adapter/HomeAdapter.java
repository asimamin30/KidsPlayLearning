package com.fyp.autisticchildlearner.adapter;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.fyp.autisticchildlearner.DrawingActivity;
import com.fyp.autisticchildlearner.QuizActivity;
import com.fyp.autisticchildlearner.R;
import com.fyp.autisticchildlearner.activity.HomeActivity;
import com.fyp.autisticchildlearner.activity.video.VideoLearningActivity;


public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    Context context;
    int[] arrOfCategory;

    public HomeAdapter(Context context, int[] arrOfCategory) {
        this.context = context;
        this.arrOfCategory = arrOfCategory;
    }

    public int getItemCount() {
        return arrOfCategory.length;
    }


    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.card_list_start, viewGroup, false));
    }

    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {

        Glide.with(context).load(arrOfCategory[i]).apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)).into(viewHolder.imgThumbnail);

        viewHolder.cVHomeCategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (i) {
                    case 0:
                        Intent intent1 = new Intent(context, HomeActivity.class);
                        intent1.putExtra("Type", 1);
                        context.startActivity(intent1);
                        break;
                    case 1:
                        Intent intent3 = new Intent(context, HomeActivity.class);
                        intent3.putExtra("Type", 2);
                        context.startActivity(intent3);

                        break;
                    case 2:
                        Intent intent4 = new Intent(context, HomeActivity.class);
                        intent4.putExtra("Type", 3);
                        context.startActivity(intent4);
                        break;
                    case 3:
                        Intent intent5 = new Intent(context, QuizActivity.class);
                        context.startActivity(intent5);
                        break;

                    case 4:

                        Intent intent2 = new Intent(context, VideoLearningActivity.class);
                        context.startActivity(intent2);

                        break;
                    case 5:
                        Intent intent6 = new Intent(context, DrawingActivity.class);
                        context.startActivity(intent6);
                        break;

                }
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