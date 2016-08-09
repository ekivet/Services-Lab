package com.example.erickivet.serviceslab.services;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;

/**
 * Created by erickivet on 8/8/16.
 */
public class MediaService extends Service {

    public static final String MUSIC_URL = "http://www.midiworld.com/download/3809";
    private static final String TAG = "MediaService";

    private static MediaPlayer mediaPlayer;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        String url = MUSIC_URL;
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mediaPlayer.setDataSource(url);
        }catch (IOException e){
            e.printStackTrace();
        }
        Log.i(TAG, "MediaService.onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG,"MediaService.onStartCommand");
        Thread mediaThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    mediaPlayer.prepare();
                    Log.i(TAG, "MediaService.run");
                }catch (IOException e){
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }
        });
        mediaThread.start();
        Toast.makeText(getApplicationContext(), "MediaService Started", Toast.LENGTH_SHORT).show();
        //put code here


        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
        mediaPlayer.release();
        mediaPlayer=null;
        Log.i(TAG,"MediaService.onDestroy");
        Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
    }

    public static void playMedia (){
        if (mediaPlayer != null) {
            mediaPlayer.start();
        }
    }

    public static void pauseMedia(){
        if (mediaPlayer != null){
            mediaPlayer.pause();
        }
    }

    public static void stopMedia(){
        if (mediaPlayer != null){
            mediaPlayer.pause();
            mediaPlayer.seekTo(0);
        }
    }
}
