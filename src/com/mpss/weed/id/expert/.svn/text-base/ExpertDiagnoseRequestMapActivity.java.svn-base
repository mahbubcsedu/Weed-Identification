package com.mpss.weed.id.expert;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mpss.weed.id.R;
import com.mpss.weed.id.common.LoginActivity;
import com.mpss.weed.id.common.Request;
import com.mpss.weed.id.common.RequestList;
import com.mpss.weed.id.common.SessionApp;
import com.mpss.weed.id.common.Weed;
import com.mpss.weed.id.zoom.ImageZoomActivity;
import com.mpss.weed.id.utils.ActionItem;
import com.mpss.weed.id.utils.DownloadImageTask;
import com.mpss.weed.id.utils.QuickAction;
import com.mpss.weed.id.utils.UploadtoServer;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;

public class ExpertDiagnoseRequestMapActivity extends Activity {
	Context context = this;
	RequestList request;
	ArrayList<Weed> weeds = new ArrayList<Weed>();
	ArrayList<Weed> weeds_selected = new ArrayList<Weed>();
	int[] ranking=new int[3];

	WeedGalleryAdapter adapter;
	Button proceed;

	ImageView requestImage;
	Gallery gallery;

	Bitmap requestImageBitmap;
	ProgressDialog progress;

	String expertID;
	int tapped_position;


	private static final int RANK_1 = 1;
	private static final int RANK_2 = 2;
	private static final int RANK_3 = 3;
	//private static final int RANK_4= 4;
	//private static final int RANK_5= 5;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
		setContentView(R.layout.diagnose_request);

		request = getIntent().getParcelableExtra("request");
		//expertID=getIntent().getStringExtra("expertID");
		expertID=SessionApp.getUserLoggedUserID();
		if(expertID==null)
		{
			startActivity(new Intent(context, LoginActivity.class));
		}
		requestImage = (ImageView) findViewById(R.id.request_image);
		final QuickAction mQuickAction 	= new QuickAction(this);
		//requestImage.setTag("http://mpss.csce.uark.edu/~ayushs/weedimages/"
		//		+ request.getWeedPhotoId() + ".jpg");
		//requestImage.setTag("http://mpss.csce.uark.edu/smsdb/weed_app/weedapp/Image_Capture/"
		//		+ request.getWeedPhotoId() + ".jpg");
		requestImage.setTag(getString(R.string.requestimage_url)
						+ request.getWeedPhotoId() + ".jpg");
		progress = new ProgressDialog(context);
		progress.setMessage("Loading weed database...");
		progress.setCancelable(false);
		progress.show();

		new DownloadImageTask().execute(requestImage);

		new GetWeedListTask().execute((Void[])null);

		gallery = (Gallery) findViewById(R.id.diagnose_gallery);
		adapter = new WeedGalleryAdapter(this,
				android.R.layout.simple_gallery_item, weeds);
		gallery.setAdapter(adapter);

		gallery.setOnItemLongClickListener(new OnItemLongClickListener(){

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View v,
					int position, long id) {
				// TODO Auto-generated method stub
				
				Toast.makeText(getApplicationContext(), "" + position,
						Toast.LENGTH_SHORT).show();
				Toast.makeText(getApplicationContext(), "" + weeds.get(position).getId(),
						Toast.LENGTH_SHORT).show();
				tapped_position=position;
				mQuickAction.show(v);
				return false;
			}});
		
		
		gallery.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView parent, View v, int position,
					long id) {
				ZoomImage(weeds.get(position).getImageUrl());			
			}
		});

		proceed = (Button) findViewById(R.id.preview_button);
		proceed.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(weeds_selected.size()<1){
					Toast.makeText(getApplicationContext(), "You have to rank at least one",
							Toast.LENGTH_SHORT).show();
					
				}
				else{
				Weed w = (Weed) gallery.getAdapter().getItem(
						gallery.getSelectedItemPosition());
				Intent intent = new Intent(context,	ExpertVarifyDiagnoseActivity.class);
				intent.putExtra("request", request);
				intent.putExtra("ranking", ranking);
				intent.putExtra("weeds_selected", weeds_selected);
				intent.putExtra("expertID", expertID);
				intent.putExtra("bitmap", requestImageBitmap);
				startActivity(intent);
				}
			}
		});	

		requestImage.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ZoomImage(requestImage.getTag().toString());
			}
			
		});
		
		ActionItem rank1 	= new ActionItem(RANK_1, "RANK", getResources().getDrawable(R.drawable.ic_1));
		ActionItem rank2 	= new ActionItem(RANK_2, "RANK", getResources().getDrawable(R.drawable.ic_2));
		ActionItem rank3 	= new ActionItem(RANK_3, "RANK", getResources().getDrawable(R.drawable.ic_3));
		//ActionItem rank4 	= new ActionItem(RANK_4, "RANK", getResources().getDrawable(R.drawable.ic_4));
		//ActionItem rank5 	= new ActionItem(RANK_5, "RANK", getResources().getDrawable(R.drawable.ic_5));

		//use setSticky(true) to disable QuickAction dialog being dismissed after an item is clicked
		//rank5.setSticky(true);	

		mQuickAction.addActionItem(rank1);
		mQuickAction.addActionItem(rank2);
		mQuickAction.addActionItem(rank3);
		//mQuickAction.addActionItem(rank4);
		//mQuickAction.addActionItem(rank5);

		//setup the action item click listener
		mQuickAction.setOnActionItemClickListener(new QuickAction.OnActionItemClickListener() {
			@Override
			public void onItemClick(QuickAction quickAction, int pos, int actionId) {
				ActionItem actionItem = quickAction.getActionItem(pos);				
				//if (actionId == RANK_1) {
				//	Toast.makeText(getApplicationContext(), "Add item selected", Toast.LENGTH_SHORT).show();
				//} else {
				if(weeds_selected.indexOf(weeds.get(tapped_position))==-1)
				{					   
					if(weeds_selected.size()>=3)
					{
						Toast.makeText(getApplicationContext(), "Maximum selection not more than 3", Toast.LENGTH_SHORT).show();
					}else{					   
						weeds_selected.add(weeds.get(tapped_position));
						ranking[weeds_selected.indexOf(weeds.get(tapped_position))]=actionId;
					}			   

				}else 
				{					   
					weeds_selected.add(weeds_selected.indexOf(weeds.get(tapped_position)), weeds.get(tapped_position));
					ranking[weeds_selected.indexOf(weeds.get(tapped_position))]=actionId;
				}
				//Toast.makeText(getApplicationContext(), actionItem.getTitle() + " selected", Toast.LENGTH_SHORT).show();
				//}
			}
		});

		mQuickAction.setOnDismissListener(new QuickAction.OnDismissListener() {
			@Override
			public void onDismiss() {
				Toast.makeText(getApplicationContext(), "Ups..dismissed", Toast.LENGTH_SHORT).show();
			}
		});
	}

	public void playAudio() {
		//String url = "http://mpss.csce.uark.edu/smsdb/weed_app/weebapp/weedsoundlog/sound1.wav"; // your
		// URL
		boolean isAudioPresent=true;
		String url=request.getVoice_url();																					// here
		MediaPlayer mediaPlayer = new MediaPlayer();
		mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
		try {
			mediaPlayer.setDataSource(url);
			mediaPlayer.prepare(); // might take long! (for buffering, etc)
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			isAudioPresent=false;
			mediaPlayer.release();
			//e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			isAudioPresent=false;
			mediaPlayer.release();
			//e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			isAudioPresent=false;
			mediaPlayer.release();
			//e.printStackTrace();
		}
		if(isAudioPresent)
		mediaPlayer.start();
		
		
	}

	/**
	 * Creates Option Menu from xml file
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.diagnose_menu, menu);
		return true;
	}

	/**
	 * Listens for the button to be pressed and initiates logout if clicked
	 */

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.audio_description:
			playAudio();
			Toast.makeText(context, "Audio", Toast.LENGTH_SHORT).show();
			return true;
			
		case R.id.text_description:
			
			AlertDialog.Builder builder = new AlertDialog.Builder(context);
			 builder.setMessage(request.getNotes())
			 .setCancelable(false)
			 .setPositiveButton("Dismiss",
			 new DialogInterface.OnClickListener() {
			 public void onClick(DialogInterface dialog,
			 int id) {
			 dialog.cancel();
			 }
			 });
			 AlertDialog alert = builder.create();
			 alert.show();
			 
			
			Toast.makeText(context, request.getNotes(), Toast.LENGTH_SHORT).show();
			return true;
		case R.id.gimagezoom:
			ZoomImage(weeds.get(gallery.getSelectedItemPosition()).imageUrl);
			Toast.makeText(context, "zoOm", Toast.LENGTH_SHORT).show();
			return true;
		case R.id.SaveDB:
			SaveToDB();
			Toast.makeText(context, "Saved To Database", Toast.LENGTH_SHORT).show();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}



	public void ZoomImage(String imageUrl)
	{
		Intent intent = new Intent(this, ImageZoomActivity.class);
		intent.putExtra("imageURL",imageUrl);
		startActivityForResult(intent, 102);        
	}

	public void SaveToDB()
	{
		UploadtoServer ut=new UploadtoServer(getString(R.string.savetodb_url));
		ut.UploadforSaveToDB(requestImage.getTag().toString());
		//Intent intent = new Intent(this, TutorialZoomActivity1.class);
		//intent.putExtra("imageURL", weeds.get(gallery.getSelectedItemPosition()).imageUrl);
		//startActivityForResult(intent, 102);        
	}
	private class GetWeedListTask extends AsyncTask<Void, Void, Boolean> {
		@Override
		protected Boolean doInBackground(Void... voids) {
			//String temp = "http://mpss.csce.uark.edu/smsdb/weed_app/weedapp/requestweeddb.php";
            String requestweeddb_url=getString(R.string.requestweeddb_url);
			HttpClient hc = new DefaultHttpClient();
			HttpPost post = new HttpPost(requestweeddb_url);
			String str = "***";

			try {
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
						weeds.add(new Weed(array.getJSONObject(i)));
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
		protected void onProgressUpdate(Void... values) 
		{
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

	/*public class DownloadImageTask extends AsyncTask<ImageView, Void, Bitmap> {

		ImageView imageView = null;

		@Override
		protected Bitmap doInBackground(ImageView... imageViews) {
			this.imageView = imageViews[0];
			return download_Image((String) imageView.getTag());
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			imageView.setImageBitmap(result);
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
	}*/

	// /**
	// * prepares the option menu setting the buttons to be enabled or disabled
	// */
	// @Override
	// public boolean onPrepareOptionsMenu(Menu menu) {
	// menu.getItem(0).setEnabled(false);
	// menu.getItem(1).setEnabled(true);
	// return true;
	// }

	// public class GridAdapter extends BaseAdapter {
	// int mGalleryItemBackground;
	// private Context mContext;
	// ArrayList<Weed> weeds;
	//
	// public GridAdapter(Context c, ArrayList<Weed> weeds) {
	// mContext = c;
	// this.weeds = weeds;
	// }
	//
	// public int getCount() {
	// return weeds.size();
	// }
	//
	// public Object getItem(int position) {
	// return weeds.get(position);
	// }
	//
	// public long getItemId(int position) {
	// return position;
	// }
	//
	// public View getView(int position, View convertView, ViewGroup parent) {
	//
	// View parentView = convertView;
	// if (parentView == null) {
	// LayoutInflater vi = (LayoutInflater) context
	// .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	// parentView = vi.inflate(R.layout.gallery_item, null);
	// }
	//
	// ImageView i = (ImageView) parentView
	// .findViewById(R.id.gallery_image);
	//
	// i.setImageBitmap(weeds.get(position).getImage());
	// // i.setLayoutParams(new Gallery.LayoutParams(
	// // WindowManager.LayoutParams.FILL_PARENT,
	// // WindowManager.LayoutParams.FILL_PARENT));
	// // i.setScaleType(ImageView.ScaleType.FIT_XY);
	// // i.setBackgroundResource(android.R.drawable.alert_dark_frame);
	//
	// TextView t = (TextView) parentView.findViewById(R.id.gallery_text);
	// t.setText(weeds.get(position).getCommonName());
	//
	// return parentView;
	// }
	// }

}