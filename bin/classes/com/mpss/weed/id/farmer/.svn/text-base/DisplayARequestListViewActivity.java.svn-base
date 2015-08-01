package com.mpss.weed.id.farmer;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import com.mpss.weed.id.R;
import com.mpss.weed.id.common.LoginActivity;
import com.mpss.weed.id.common.Request;
import com.mpss.weed.id.common.RequestList;
import com.mpss.weed.id.common.SessionApp;
import com.mpss.weed.id.utils.DownloadImageTask;
import com.mpss.weed.id.utils.ImageUtils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class DisplayARequestListViewActivity extends Activity {
	Context context = this;
	Request request;
	
	Button showResponse;
	ImageView requestImage;
	Bitmap requestImageBitmap;
	ProgressDialog progress;

	String userID;
	TextView txtComments;
    
    Button btnPlay;
	private Button buttonPlayStop;
    private MediaPlayer mediaPlayer;
    private SeekBar seekBar;
    private final Handler handler = new Handler();
	

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		 userID=SessionApp.getUserLoggedUserID();
			if(userID==null)
			{
				startActivity(new Intent(context, LoginActivity.class));
			}
		setContentView(R.layout.display_arequest);
		showResponse=(Button) findViewById(R.id.showResponse);
		request = getIntent().getParcelableExtra("request");
		//userID=getIntent().getStringExtra("userID");
		requestImage = (ImageView) findViewById(R.id.requestimage);
		//requestImage.setTag("http://mpss.csce.uark.edu/smsdb/weed_app/weedapp/Image_Capture/"
		//		+ request.getWeedPhotoId() + ".jpg");
		
		requestImage.setTag(getString(R.string.requestimage_url)+ request.getWeedPhotoId() + ".jpg");
				
		ImageUtils iu=new ImageUtils();
		requestImage.setImageBitmap(iu.downloadImage(requestImage.getTag().toString()));
		//progress = new ProgressDialog(context);
		//progress.setMessage("Loading weed database...");
		//progress.setCancelable(false);
		//progress.show();

		new DownloadImageTask().execute(requestImage);
		txtComments = (TextView) findViewById(R.id.txtReqComments);
		txtComments.setText(request.getNotes());
		LoadAudio(); 
		
		
		
		showResponse.setOnClickListener(new OnClickListener() 
        {
        	@Override 
        	public void onClick(View v) 
        	{
        		Intent intent = new Intent(context, FarmerResponseActivity.class);
    			intent.putExtra("identification_id", request.getIdentificationId());
    			//intent.putExtra("expertID", expertID);
    			startActivity(intent);
        		}
        	});
		
	}
	
	
	/*public Bitmap downloadImage(String fileUrl) {
		Bitmap image = null;
		URL myFileUrl = null;
		try {
			myFileUrl = new URL(fileUrl);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			HttpURLConnection conn = (HttpURLConnection) myFileUrl
					.openConnection();
			conn.setDoInput(true);
			conn.connect();
			InputStream is = conn.getInputStream();
			image = BitmapFactory.decodeStream(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		return image;
	}*/
	 private void LoadAudio() {
	        buttonPlayStop = (Button) findViewById(R.id.btnPlayStop);
	        buttonPlayStop.setOnClickListener(new OnClickListener() 
	        {
	        	@Override 
	        	public void onClick(View v) {buttonClick();}});

	        //mediaPlayer = MediaPlayer.create(this, R.raw.testsong_20_sec); 

	        mediaPlayer = new MediaPlayer();
	        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
	        try {
	        	mediaPlayer.setDataSource(request.getVoice_url());
				
	        	//mediaPlayer.setDataSource("http://mpss.csce.uark.edu/smsdb/weed_app/weedapp/Audio_Capture/request76.mp4"
				  //      );
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