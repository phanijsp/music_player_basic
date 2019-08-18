package com.example.music_player_basic;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton playpause, stop;
    TextView song1, song2, status;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
        initlisteners();
    }

    public void initialize() {
        song1 = (TextView) findViewById(R.id.textView3);
        song2 = (TextView) findViewById(R.id.textView4);
        status = (TextView) findViewById(R.id.nowplaying);
        playpause = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        stop = (FloatingActionButton) findViewById(R.id.stop);
    }

    public void initlisteners() {
        song1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playsong(song1.getText().toString());
            }
        });
        song2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playsong(song2.getText().toString());
            }
        });
        playpause.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View view) {
                if (mediaPlayer == null) {
                    playsong("Imagine Dragons - Thunder");
                    stop.setVisibility(View.VISIBLE);
                } else {
                    if (!mediaPlayer.isPlaying()) {
                        Toast.makeText(getApplicationContext(), "Playing", Toast.LENGTH_SHORT).show();
                        mediaPlayer.start();
                        stop.setVisibility(View.VISIBLE);
                        playpause.setImageResource(R.drawable.ic_pause_black_24dp);
                    } else {
                        Toast.makeText(getApplicationContext(), "Paused", Toast.LENGTH_SHORT).show();
                        mediaPlayer.pause();
                        playpause.setImageResource(R.drawable.ic_play_arrow_black_24dp);

                    }
                }

            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View view) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                    mediaPlayer.release();
                    mediaPlayer = null;
                    stop.setVisibility(View.GONE);
                    status.setText("");
                    playpause.setImageResource(R.drawable.ic_play_arrow_black_24dp);
                }
            }
        });
    }

    public void playsong(String songname) {
        playpause.setImageResource(R.drawable.ic_pause_black_24dp);
        if (songname.equals("We Will Rock You")) {
            try {
                mediaPlayer.stop();
                mediaPlayer.release();
            } catch (Exception e) {
            }
            mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.wewillrockyou);
            mediaPlayer.start();
            status.setText("Now PLaying\nWe Will Rock You");
        }
        if (songname.equals("Imagine Dragons - Thunder")) {
            try {
                mediaPlayer.stop();
                mediaPlayer.release();
            } catch (Exception e) {
            }
            mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.thunder);
            mediaPlayer.start();
            status.setText("Now PLaying\nImagine Dragons - Thunder");
        }
    }
}
