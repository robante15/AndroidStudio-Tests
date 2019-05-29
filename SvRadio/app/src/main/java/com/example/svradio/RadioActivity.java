package com.example.svradio;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;


import java.io.IOException;




public class RadioActivity extends AppCompatActivity {

    private TextView textRadio;
    private MediaPlayer player;


    private String url ;
    String nombre = "";
    protected boolean isPlay = false, isSound=false;
    private ToggleButton buttonStreaming,btnSound;
    ImageView imageSplash,imgplay,imgpause,volumeImageView;
    ToggleButton tgBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio);

        textRadio=(TextView) findViewById(R.id.textRadioNombre);
        //get value from  another activity
        Bundle b = getIntent().getExtras();
        // or other values
        if(b != null)
           nombre = b.getString("nombre");
           url = b.getString("url");
        // Inicializo el objeto MediaPlayer
        Log.d("Radio:",nombre);
        initializeMediaPlayer();
        textRadio.setText(nombre);
        // Inicializando el volumen
        initializeVolume();

        buttonStreaming = (ToggleButton) findViewById(R.id.playPauseButton);
        btnSound= (ToggleButton) findViewById(R.id.btnSound);



        buttonStreaming.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                buttonStreaming.setEnabled(false);
                isPlay = !isPlay;
                if (isPlay) {
                    startPlaying();
                } else {
                    stopPlaying();
                }
            }
        });
        btnSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                btnSound.setEnabled(false);
                isSound = !isSound;
                if (isSound) {
                    startAudio();

                } else {
                    stopAudio();
                }
            }
        });

        // Agregar un floating action click handler para iniciar una nueva Activity
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopPlaying();
                Intent ven=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(ven);
                finish();

            }
        });
    }

    public void startAudio() {
        final AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_MUTE, /* flags= */ 0);
        btnSound.setEnabled(true);
    }
    public void stopAudio() {
        final AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        audioManager.adjustStreamVolume(
                AudioManager.STREAM_MUSIC, AudioManager.ADJUST_UNMUTE, /* flags= */ 0);
        btnSound.setEnabled(true);
    }
    public void stopPlaying() {
        if (player.isPlaying()){
            player.stop();
            player.release();
            initializeMediaPlayer();
            buttonStreaming.setEnabled(true);
          //  imageSplash.setImageResource(R.drawable.microfono);
        }
    }
    private void initializeVolume() {
        try {
            final SeekBar volumeBar = (SeekBar) findViewById(R.id.volumeSeekBar);
            final AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
            final int musicVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
            volumeBar.setMax(audioManager
                    .getStreamMaxVolume(AudioManager.STREAM_MUSIC));
            final SeekBar.OnSeekBarChangeListener eventListener = new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                }

                @Override
                public void onProgressChanged(SeekBar seekBar, int progress,
                                              boolean fromUser) {
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,
                            progress, 0);
                }
            };

            volumeBar.setOnSeekBarChangeListener(eventListener);
        } catch (Exception e) {
            Log.e("MainActivity", e.getMessage());
        }
    }

    private void initializeMediaPlayer() {
        player = new MediaPlayer();

        player.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {

            public void onBufferingUpdate(MediaPlayer mp, int percent) {
                Log.i("Buffering", "" + percent);
            }
        });
    }

    public void startPlaying() {

        try {
            Toast.makeText(getApplicationContext(),
                    "Conectando con la radio, espere unos segundos..."+url,
                    Toast.LENGTH_LONG).show();
            player.reset();
            player.setDataSource(url);
            player.setAudioStreamType(AudioManager.STREAM_MUSIC);

            player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

                public void onPrepared(MediaPlayer mp) {
                    player.start();
                  //  imageSplash.setImageResource(R.drawable.romanos10_17);

                    buttonStreaming.setEnabled(true);
                }
            });

            player.prepareAsync();

        } catch (IllegalArgumentException | SecurityException
                | IllegalStateException | IOException e) {
            Toast.makeText(getApplicationContext(),
                    "Error al conectar con la radio", Toast.LENGTH_LONG).show();
        }
    }

}
