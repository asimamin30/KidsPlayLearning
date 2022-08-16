package com.fyp.autisticchildlearner;

public class catemodel {


   String homedata,videoLearning;

    public catemodel() {
    }

    public catemodel(String homedata, String videoLearning) {
        this.homedata = homedata;
        this.videoLearning = videoLearning;
    }

    public String getHomedata() {
        return homedata;
    }

    public void setHomedata(String homedata) {
        this.homedata = homedata;
    }

    public String getVideoLearning() {
        return videoLearning;
    }

    public void setVideoLearning(String videoLearning) {
        this.videoLearning = videoLearning;
    }
}
