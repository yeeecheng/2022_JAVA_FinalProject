package com.plcoding.instagramui.saveplace.mainActivity;

import android.app.Activity;
import android.media.MediaPlayer;
import com.plcoding.instagramui.saveplace.R;



public class Alert {

    MediaPlayer mediaPlay;
    Activity act;

    public Alert(Activity activity){
        act = activity;
        mediaPlay = MediaPlayer.create(act ,R.raw.alert_media);

    }

    public void startMedia(){
        mediaPlay.start();
        mediaPlay.setLooping(true);
    }

    public void stopMedia(){
        mediaPlay.stop();
        mediaPlay.reset();
        mediaPlay = MediaPlayer.create(act, R.raw.alert_media);
    }

    public Boolean isMediaPlaying(){
        return mediaPlay.isPlaying();
    }




}
