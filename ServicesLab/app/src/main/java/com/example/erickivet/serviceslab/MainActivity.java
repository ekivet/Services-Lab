package com.example.erickivet.serviceslab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.erickivet.serviceslab.services.MediaService;

public class MainActivity extends AppCompatActivity {

    Button play, pause, stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        play = (Button) findViewById(R.id.play);
        pause = (Button) findViewById(R.id.pause);
        stop = (Button) findViewById(R.id.stop);

        Intent intent = new Intent(MainActivity.this, MediaService.class);
        startService(intent);


        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaService.playMedia();
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaService.pauseMedia();
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaService.stopMedia();
            }
        });


    }


}
