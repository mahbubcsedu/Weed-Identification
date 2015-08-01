package com.mpss.weed.id.farmer;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.mpss.weed.id.R;
import com.mpss.weed.id.common.LoginActivity;
import com.mpss.weed.id.common.RequestList;
import com.mpss.weed.id.common.SessionApp;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

public class RequestDetailsActivity extends Activity implements MediaPlayer.OnCompletionListener{
	Context context = this;
	RequestList request;
	
	Button showResponse;
	ImageView requestImage;
	Bitmap requestImageBitmap;
	ProgressDialog progress;

	String userID;
	TextView txtComments;
    
    Button btnPlay;
    private MediaPlayer mediaPlayer;
	private SeekBar seekBar;
	private ImageButton play;
	private ImageButton pause;
	private ImageButton stop;
    
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
		setContentView(R.layout.requestdetails);
		showResponse=(Button) findViewById(R.id.showResponse);
		request = getIntent().getParcelableExtra("request");
		
		/*Display display = getWindowManager().getDefaultDisplay();
		int height=display.getHeight()*2/5;
		int width=display.getWidth()-10;
		
		LinearLayout.LayoutParams vp =    new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		requestImage.setLayoutParams(vp); 
		requestImage.getLayoutParams().height = height;
		requestImage.getLayoutParams().width  = width;
		*/
		
		//userID=getIntent().getStringExtra("userID");
		requestImage = (ImageView) findViewById(R.id.requestimage);
		//requestImage.setTag("http://mpss.csce.uark.edu/smsdb/weed_app/weedapp/Image_Capture/"
		//		+ request.getWeedPhotoId() + ".jpg");
		
		requestImage.setTag(getString(R.string.requestimage_url)+ request.getWeedPhotoId() + ".jpg");
				
		//ImageUtils iu=new ImageUtils();
		//requestImage.setScaleType(ScaleType.MATRIX);
		//requestImage.setImageResource(iu.downloadImage(requestImage.getTag().toString()));
		//requestImage.setImageBitmap(iu.downloadImage(requestImage.getTag().toString()));
		//progress = new ProgressDialog(context);
		//progress.setMessage("Loading weed database...");
		//progress.setCancelable(false);
		//progress.show();

		new DownloadImageTask().execute(requestImage);
		
		txtComments = (TextView) findViewById(R.id.txtReqComments);
		txtComments.setText(request.getNotes());
		initAudioSetup(); 
		
		
		
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
	
	
	private class DownloadImageTask extends AsyncTask<ImageView, Void, Bitmap> {

		ImageView imageView = null;

		@Override
		protected Bitmap doInBackground(ImageView... imageViews) {
			this.imageView = imageViews[0];
			return download_Image((String) imageView.getTag());
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			imageView.setImageBitmap(result);
			
			/*float imagewidth=result.getWidth();
			float imagehight=result.getHeight();
			float scaleration=imagehight/imagewidth;
			
			DisplayMetrics dm = new DisplayMetrics();
	        getWindowManager().getDefaultDisplay().getMetrics(dm);
	        int scrwidth=dm.widthPixels;
	        int scrhight=dm.heightPixels;
	        
	        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) imageView.getLayoutParams(); 
	        params.width = scrwidth;
	       // params.height = (int) (scrwidth*scaleration);
	        params.height = (int) (scrwidth*scaleration);
	        imageView.setLayoutParams(params);
	        imageView.setAdjustViewBounds(true);*/
			
			
		}

		private Bitmap download_Image(String url) {
			return downloadImage(url);
		}

		public Bitmap downloadImage(String fileUrl) {
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
		}
	}
	 /*private void LoadAudio() {
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
	    */
	
	// This method set the setOnClickListener and method for it (buttonClick())
		private void initAudioSetup() {
			play=(ImageButton)findViewById(R.id.btn_media_play);
			pause=(ImageButton)findViewById(R.id.btn_media_pause);
			stop=(ImageButton)findViewById(R.id.btn_media_stop);

			play.setOnClickListener(new View.OnClickListener() {
				public void onClick(View view) {
					play();
					play.setImageResource(R.drawable.ic_media_play_disable);
					pause.setImageResource(R.drawable.btn_media_pause);
					stop.setImageResource(R.drawable.btn_media_stop);
					
				}
			});

			pause.setOnClickListener(new View.OnClickListener() {
				public void onClick(View view) {
					pause();
					play.setImageResource(R.drawable.btn_media_play);
					pause.setImageResource(R.drawable.ic_media_pause_disable);
					stop.setImageResource(R.drawable.btn_media_stop);
				}
			});

			stop.setOnClickListener(new View.OnClickListener() {
				public void onClick(View view) {
					stop();
					play.setImageResource(R.drawable.btn_media_play);
					
					pause.setImageResource(R.drawable.ic_media_pause_disable);
					stop.setImageResource(R.drawable.ic_media_stop_disable);
				}
			});

			setup();



			/*buttonPlayStop = (Button) findViewById(R.id.btnPlayStop);
			buttonPlayStop.setOnClickListener(new OnClickListener() 
			{
				@Override 
				public void onClick(View v) 
				{
					buttonClick();
				}
			});
	*/
			//mediaPlayer = MediaPlayer.create(this, R.raw.testsong_20_sec); 

			

		}

		public void onCompletion(MediaPlayer mp) {
			stop();
		}

		private void play() {
			mediaPlayer.start();
			startPlayProgressUpdater();
			play.setEnabled(false);
			pause.setEnabled(true);
			stop.setEnabled(true);
		}

		private void stop() {
			mediaPlayer.stop();
			pause.setEnabled(false);
			stop.setEnabled(false);

			try {
				mediaPlayer.prepare();
				mediaPlayer.seekTo(0);
				seekBar.setProgress(0);
				play.setEnabled(true);
			}
			catch (Throwable t) {
				exceptionDialogue(t);
			}
		}

		private void pause() {
			mediaPlayer.pause();
			seekBar.setProgress(mediaPlayer.getCurrentPosition());
			play.setEnabled(true);
			pause.setEnabled(false);
			stop.setEnabled(true);
		}

		/* private void loadClip() {
		    try {
		      mp=MediaPlayer.create(this, R.raw.clip);
		      mp.setOnCompletionListener(this);
		    }
		    catch (Throwable t) {
		      goBlooey(t);
		    }
		  }*/

		private void setup() {
			// loadClip();
			boolean mediaPresent=true;
			mediaPlayer = new MediaPlayer();
			mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
			try {
				
				mediaPlayer.setDataSource(request.getVoice_url());
				
				mediaPlayer.setOnCompletionListener(this);
				//mediaPlayer.setDataSource("http://mpss.csce.uark.edu/smsdb/weed_app/weedapp/Audio_Capture/request76.mp4"
				//      );
				mediaPlayer.prepare();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				exceptionDialogue(e);
				e.printStackTrace();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				mediaPlayer.release();
				mediaPresent=false;
				play.setEnabled(false);
				pause.setEnabled(false);
				stop.setEnabled(false);
				//e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				mediaPlayer.release();
				mediaPresent=false;
				
				//e.printStackTrace();
			}


			
			if(mediaPresent){
			
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
			
			play.setEnabled(true);
			pause.setEnabled(false);
			stop.setEnabled(false);
			}
			else
			{
				
				play.setEnabled(false);
				pause.setEnabled(false);
				stop.setEnabled(false);
			}
		}

		private void exceptionDialogue(Throwable t) {
			AlertDialog.Builder builder=new AlertDialog.Builder(this);

			builder
			.setTitle("Exception!")
			.setMessage(t.toString())
			.setPositiveButton("OK", null)
			.show();
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
			}
			/*
			else{
				mediaPlayer.pause();
				buttonPlayStop.setText(getString(R.string.play_str));
				seekBar.setProgress(0);
			}*/
		} 

		// This is event handler thumb moving event
		private void seekChange(View v){
			if(mediaPlayer.isPlaying()){
				SeekBar sb = (SeekBar)v;
				mediaPlayer.seekTo(sb.getProgress());
			}
		}
	
	
	
	
	
}