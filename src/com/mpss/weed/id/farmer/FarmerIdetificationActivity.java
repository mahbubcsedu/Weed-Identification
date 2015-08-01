package com.mpss.weed.id.farmer;

import java.io.IOException;
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
import com.mpss.weed.id.common.IdentificationListAdapter;
import com.mpss.weed.id.common.IdentificationResponse;
import com.mpss.weed.id.common.ResponseListAdapter;
import com.mpss.weed.id.common.SessionApp;


import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

public class FarmerIdetificationActivity extends ListActivity {
	Context context = this;
	ArrayList<IdentificationResponse> identification = new ArrayList<IdentificationResponse>();
	IdentificationListAdapter adapter;
	ProgressDialog progress;
	String profile_id;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.diagnose_list);
        profile_id=SessionApp.getUserLoggedUserID();
        
		adapter = new IdentificationListAdapter(context, identification);
		setListAdapter(adapter);		
		progress = new ProgressDialog(context);
		progress.setMessage("Loading Response...");
		progress.setCancelable(false);
		progress.show();
		
		new GetRequestListTask().execute((Void[])null);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {

		IdentificationResponse ir = (IdentificationResponse) l.getAdapter().getItem(position);
		Intent intent = new Intent(context, FarmerResponseActivity.class);
		intent.putExtra("identification_id", ir.getIdentification_id());
		//intent.putExtra("expertID", expertID);
		startActivity(intent);

		super.onListItemClick(l, v, position, id);
	}
	
	private class GetRequestListTask extends AsyncTask<Void, Void, Boolean> {
		@Override
		protected Boolean doInBackground(Void... voids) {
			String temp = "http://mpss.csce.uark.edu/smsdb//weed_app/weedapp/response_expert.php";

			HttpClient hc = new DefaultHttpClient();
			
			HttpPost post = new HttpPost(temp);

			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
			nameValuePairs.add(new BasicNameValuePair("profile_id", profile_id));


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
					JSONArray array = json.getJSONArray("requests");

					//String identification_id=null,prev_identification=null,expert_response_id=null,prev_expert_response_id=null;
					for (int i = 0; i < array.length(); i++) {
						
						
						identification.add(new IdentificationResponse(array.getJSONObject(i)));
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
		}
	}
}