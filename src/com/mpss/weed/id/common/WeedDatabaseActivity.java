package com.mpss.weed.id.common;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
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
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;
import android.widget.Toast;

public class WeedDatabaseActivity extends ListActivity implements OnScrollListener {
	Context context = this;
	ArrayList<Weed> weeds = new ArrayList<Weed>();
	WeedListAdapter adapter;
    String userID;
	ProgressDialog progress;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
		 userID=SessionApp.getUserLoggedUserID();
			if(userID==null)
			{
				startActivity(new Intent(context, LoginActivity.class));
			}
		setContentView(R.layout.weed_database);

		new GetWeedListTask().execute();
		adapter = new WeedListAdapter(this, weeds);
		setListAdapter(adapter);
		getListView().setOnScrollListener(this);
		
		progress = new ProgressDialog(context);
		progress.setMessage("Loading the database...");
		progress.setCancelable(false);
		progress.show();
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {

		Weed w = (Weed) l.getAdapter().getItem(position);

		Intent intent = new Intent(context, WeedInformationActivity.class);
		intent.putExtra("weed", w);
		startActivity(intent);

		super.onListItemClick(l, v, position, id);
	}

	private class GetWeedListTask extends AsyncTask<Void, Void, Boolean> {
		@Override
		protected Boolean doInBackground(Void... voids) {
			//String temp = "http://mpss.csce.uark.edu/~mweathers/weedapp/requestweeddb.php";
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
			
			
			startDownloadingImages();
		}
	}

	public void startDownloadingImages() {
		Weed[] w = new Weed[10];
		for (int i = 0; i < 10; i++) {
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
			progress.dismiss();
			adapter.notifyDataSetChanged();
		}
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//		for (int i = firstVisibleItem; i < firstVisibleItem + visibleItemCount; i++) {
//			if (weeds.get(i).getImage() == null) {
//				new DownloadImagesTask().execute(weeds.get(i));
//			}
//		}
		
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub
		if (scrollState == SCROLL_STATE_IDLE) {
			for (int i = view.getFirstVisiblePosition(); i <= view.getLastVisiblePosition(); i++) {
				if (weeds.get(i).getImage() == null) {
					new DownloadImagesTask().execute(weeds.get(i));
				}
			}
			Log.v("TAG", "HERE");
		}
	}
}