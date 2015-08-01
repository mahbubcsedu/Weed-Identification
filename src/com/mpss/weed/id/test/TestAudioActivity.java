package com.mpss.weed.id.test;

import java.io.IOException;

import com.mpss.weed.id.R;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.SeekBar;

public class TestAudioActivity extends Activity{

	Button btnPlay;
	private Button buttonPlayStop;
    private MediaPlayer mediaPlayer;
    private SeekBar seekBar;

    private final Handler handler = new Handler();

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_audio);	
		//btnPlay = (Button) findViewById(R.id.btnPlayStop);
		initViews(); 
		/*btnPlay.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
				    MediaPlayer player = new MediaPlayer();
				    player.setAudioStreamType(AudioManager.STREAM_MUSIC);
				    player.setDataSource("http://mpss.csce.uark.edu/smsdb/weed_app/weedapp/Audio_Capture/request76.mp4"
				            );
				    player.prepare();
				                player.start();

				} catch (Exception e) {
				    // TODO: handle exception
				}
			}
			
		});*/
	}

	
	
	
	
	// This method set the setOnClickListener and method for it (buttonClick())
    private void initViews() {
        buttonPlayStop = (Button) findViewById(R.id.btnPlayStop);
        buttonPlayStop.setOnClickListener(new OnClickListener() {@Override public void onClick(View v) {buttonClick();}});

        //mediaPlayer = MediaPlayer.create(this, R.raw.testsong_20_sec); 

        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
			mediaPlayer.setDataSource("http://mpss.csce.uark.edu/smsdb/weed_app/weedapp/Audio_Capture/request76.mp4"
			        );
			mediaPlayer.prepare();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
        seekBar = (SeekBar) findViewById(R.id.SeekBar01);
        seekBar.setMax(mediaPlayer.getDuration());
        seekBar.setOnTouchListener(new OnTouchListener() {      	

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub
			seekChange(v);
			
			return false;
		}
		});

    }

    public void startPlayProgressUpdater() {
    	seekBar.setProgress(mediaPlayer.getCurrentPosition());

		if (mediaPlayer.isPlaying()) {
			Runnable notification = new Runnable() {
		        public void run() {
		        	startPlayProgressUpdater();
				}
		    };
		    handler.postDelayed(notification,1000);
    	}else{
    		mediaPlayer.pause();
    		buttonPlayStop.setText(getString(R.string.play_str));
    		seekBar.setProgress(0);
    	}
    } 

    // This is event handler thumb moving event
    private void seekChange(View v){
    	if(mediaPlayer.isPlaying()){
	    	SeekBar sb = (SeekBar)v;
			mediaPlayer.seekTo(sb.getProgress());
		}
    }

    // This is event handler for buttonClick event
    private void buttonClick(){
        if (buttonPlayStop.getText() == getString(R.string.play_str)) {
            buttonPlayStop.setText(getString(R.string.pause_str));
            try{
            	mediaPlayer.start();
                startPlayProgressUpdater();
            }catch (IllegalStateException e) {
            	mediaPlayer.pause();
            }
        }else {
            buttonPlayStop.setText(getString(R.string.play_str));
            mediaPlayer.pause();
        }
    }
}
