package com.mpss.weed.id.farmer;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mpss.weed.id.R;
import com.mpss.weed.id.common.Weed;
import com.mpss.weed.id.expert.WeedGalleryAdapter;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class ResponseWeedsActivity extends Activity implements MediaPlayer.OnCompletionListener{
	Context context = this;
	String response_id,expert_comments,expert_speechID;
	ArrayList<Weed> weeds = new ArrayList<Weed>();
	TextView txtComments;
	TextView commonname,latin_name ,weed_type,life_cycle,season,site ,professional_control,homeowner_control; 
	//ArrayList<Weed> weeds_selected = new ArrayList<Weed>();
	//int[] ranking=new int[3];

	WeedGalleryAdapter adapter;
	Button proceed;

	ImageView requestImage;
	Gallery gallery;

	Bitmap requestImageBitmap;
	ProgressDialog progress;

	String expertID;
	int tapped_position;

	Button btnPlay;	
	private MediaPlayer mediaPlayer;
	private SeekBar seekBar;
	private ImageButton play;
	private ImageButton pause;
	private ImageButton stop;
	//private MediaPlayer mp;
	private final Handler handler = new Handler();
	/*private static final int RANK_1 = 1;
	private static final int RANK_2 = 2;
	private static final int RANK_3 = 3;
	private static final int RANK_4= 4;
	private static final int RANK_5= 5;
	 */
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.responsedetails);

		response_id = getIntent().getStringExtra("response_id");
		expert_comments=getIntent().getStringExtra("expert_comments");
		expert_speechID=getIntent().getStringExtra("expert_speechID");
		//requestImage = (ImageView) findViewById(R.id.request_image);
		////final QuickAction mQuickAction 	= new QuickAction(this);
		//requestImage.setTag("http://mpss.csce.uark.edu/~ayushs/weedimages/"
		//		+ request.getWeedPhotoId() + ".jpg");
		//requestImage.setTag("http://mpss.csce.uark.edu/smsdb/weed_app/weedapp/Image_Capture/"
		//				+ request.getWeedPhotoId() + ".jpg");
		progress = new ProgressDialog(context);
		progress.setMessage("Loading weed database...");
		progress.setCancelable(false);
		progress.show();

		//new DownloadImageTask().execute(requestImage);

		new GetWeedListTask().execute((Void[])null);

		gallery = (Gallery) findViewById(R.id.result_gallery);
		adapter = new WeedGalleryAdapter(this,
				android.R.layout.simple_gallery_item, weeds);
		gallery.setAdapter(adapter);

		gallery.setCallbackDuringFling(true);
		gallery.setOnItemSelectedListener(new OnItemSelectedListener() {
	            @Override
	            public void onItemSelected(AdapterView<?> arg0, View arg1,
	                    int position, long arg3) {
	            	UpdateWeedInformation(position);
	               // Toast.makeText(context, "SELECTING "+position, Toast.LENGTH_SHORT).show();             
	            }

	            @Override
	            public void onNothingSelected(AdapterView<?> arg0) {
	                Toast.makeText(context, "NOTHING", Toast.LENGTH_SHORT).show();             
	            }
	        });
		
		/*gallery.setOnFocusChangeListener(new OnFocusChangeListener(){

			@Override
			public void onFocusChange(View v, boolean arg1) {
				// TODO Auto-generated method stub
				
			}
			
		});*/
		gallery.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView parent, View v, int position,
					long id) {
				/*Toast.makeText(getApplicationContext(), "" + position,
						Toast.LENGTH_SHORT).show();
				Toast.makeText(getApplicationContext(), "" + weeds.get(position).getId(),
						Toast.LENGTH_SHORT).show();*/
				Toast toast=Toast.makeText(getApplicationContext(), "Rank #" + weeds.get(position).getRank(),
						Toast.LENGTH_SHORT);
				toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);

				toast.show();
				//tapped_position=position;
				//mQuickAction.show(v);

			}
		});
		txtComments = (TextView) findViewById(R.id.txtExpertComments);
		txtComments.setText(expert_comments);
		initAudioSetup(); 

	}

	protected void UpdateWeedInformation(int position){
		commonname = (TextView) findViewById(R.id.common_name);
		 latin_name = (TextView) findViewById(R.id.latin_name);
		 weed_type = (TextView) findViewById(R.id.weed_type);
		 life_cycle = (TextView) findViewById(R.id.life_cycle);
		 season = (TextView) findViewById(R.id.season);
		 site = (TextView) findViewById(R.id.site);
		 professional_control = (TextView) findViewById(R.id.professional_control);
		 homeowner_control = (TextView) findViewById(R.id.homeowner_control);
		 
		 commonname.setText(weeds.get(position).getCommonName());
		 latin_name.setText(weeds.get(position).getLatinName());
		 weed_type.setText(weeds.get(position).getWeedType());
		 life_cycle.setText(weeds.get(position).getLifeCycle());
		 season.setText(weeds.get(position).getSeason());
		 site.setText(weeds.get(position).getSite());
		 professional_control.setText(weeds.get(position).getProfessionalControl());
		 homeowner_control.setText(weeds.get(position).getHomeownerControl());
		
		
	}
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
		boolean mediaPresent=true;
		mediaPlayer = new MediaPlayer();
		mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
		try {
			mediaPlayer.setDataSource(expert_speechID);
			
			mediaPlayer.setOnCompletionListener(this);
			//mediaPlayer.setDataSource("http://mpss.csce.uark.edu/smsdb/weed_app/weedapp/Audio_Capture/request76.mp4"
			//      );
			mediaPlayer.prepare();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			mediaPlayer.release();
			mediaPresent=false;
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			mediaPlayer.release();
			mediaPresent=false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			mediaPlayer.release();
			mediaPresent=false;
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
		else{
			
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

	/*
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

	private class GetWeedListTask extends AsyncTask<Void, Void, Boolean> {
		@Override
		protected Boolean doInBackground(Void... voids) {
			//String temp = "http://mpss.csce.uark.edu/smsdb/weed_app/weedapp/requestresultfor_aresponse.php";
			String responsedetail_url=getString(R.string.responsedetail_url);
			HttpClient hc = new DefaultHttpClient();
			HttpPost post = new HttpPost(responsedetail_url);

			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
			nameValuePairs.add(new BasicNameValuePair("expert_response_id", response_id));
			//nameValuePairs.add(new BasicNameValuePair("profile_id", profile_id));

			String str = "***";
			try {
				post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
				HttpResponse rp = hc.execute(post);

				if (rp.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
					str = EntityUtils.toString(rp.getEntity());
			} catch (IOException e) {
				e.printStackTrace();
			}

			JSONObject json;
			try {
				json = new JSONObject(str);
				if (json.getString("error").equals("")) {
					JSONArray array = json.getJSONArray("weeds");

					for (int i = 0; i < array.length(); i++) {
						//Weed weedresponse=new Weed();
						//weedresponse.setResponse(true);
						weeds.add(new Weed(array.getJSONObject(i),true));
						// new DownloadImageTask().execute(weeds.get(i));
					}
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				Log.v("YO", e.toString());
				return Boolean.FALSE;
			}
			return Boolean.TRUE;
		}

		@Override
		protected void onPostExecute(Boolean result) {
			adapter.notifyDataSetChanged();
			progress.dismiss();

			startDownloadingImages();
		}
	}

	public void startDownloadingImages() {
		int size = weeds.size();
		Weed[] w = new Weed[size];
		for (int i = 0; i < size; i++) {
			w[i] = weeds.get(i);
		}
		new DownloadImagesTask().execute(w);
	}

	private class DownloadImagesTask extends AsyncTask<Weed, Void, Void> {
		@Override
		protected Void doInBackground(Weed... weeds) {

			for (Weed w : weeds) {
				w.setImage(downloadImage(w.getImageUrl()));
				publishProgress((Void[])null);
			}
			return null;
		}

		@Override
		protected void onProgressUpdate(Void... values) {
			adapter.notifyDataSetChanged();
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
		@Override
		protected void onPostExecute(Void result) {
			adapter.notifyDataSetChanged();
		}
	}	
}