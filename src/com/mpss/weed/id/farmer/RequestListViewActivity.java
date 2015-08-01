package com.mpss.weed.id.farmer;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mpss.weed.id.R;
import com.mpss.weed.id.common.LoginActivity;
import com.mpss.weed.id.common.Request;
import com.mpss.weed.id.common.RequestList;
import com.mpss.weed.id.common.RequestListAdapter;
import com.mpss.weed.id.common.RequestListExtendedAdapter;
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
import android.widget.TextView;



public class RequestListViewActivity extends ListActivity {
	Context context = this;
	ArrayList<RequestList> requests = new ArrayList<RequestList>();
	RequestListExtendedAdapter adapter;
	ProgressDialog progress;
	String expertID;
	TextView tv;
	private DefaultHttpClient mHttpClient;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.diagnose_list);
		//tv=(TextView)findViewById(R.id.textDiagnoseTitle);
		//tv.setText("REQUESTS");
		expertID=SessionApp.getUserLoggedUserID();
		if(expertID==null)
		{
			startActivity(new Intent(context, LoginActivity.class));
		}        
		adapter = new RequestListExtendedAdapter(context, requests);
		setListAdapter(adapter);		
		progress = new ProgressDialog(context);
		progress.setMessage("Loading requests...");
		progress.setCancelable(false);
		progress.show();
		
		ListView lv=getListView();
        lv.setTextFilterEnabled(true);
        
		new GetRequestListTask().execute((Void[])null);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {

		RequestList r = (RequestList) l.getAdapter().getItem(position);
		Intent intent = new Intent(context, RequestDetailsActivity.class);
		intent.putExtra("request", r);
		intent.putExtra("expertID", expertID);
		startActivity(intent);

		super.onListItemClick(l, v, position, id);
	}
	
	private class GetRequestListTask extends AsyncTask<Void, Void, Boolean> {
		@Override
		protected Boolean doInBackground(Void... voids) {
			//String temp = "http://mpss.csce.uark.edu/smsdb/weed_app/weedapp/requestdiagnose.php";
			
			 HttpParams params = new BasicHttpParams();
			   params.setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
			    mHttpClient = new DefaultHttpClient(params);
			    String str = "***";
			    String requestlistmap_url=getString(R.string.requestlistmap_url);
				//String temp = "http://mpss.csce.uark.edu/smsdb/weed_app/weedapp/farmer_requestlist.php";
				//HttpPost httppost = new HttpPost(requestlistmap_url);
		        MultipartEntity multipartEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
		        try {
					multipartEntity.addPart("userID", new StringBody(SessionApp.getUserLoggedUserID()));
				
		       
				
				HttpClient hc = new DefaultHttpClient();
				HttpPost httppost = new HttpPost(requestlistmap_url);
			    httppost.setEntity(multipartEntity);
		         //mHttpClient.execute(httppost, new PhotoUploadResponseHandler());
			     HttpResponse response=mHttpClient.execute(httppost);
			    // String the_string_response = convertResponseToString(response);
				
				HttpResponse rp = hc.execute(httppost);
					if (rp.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
						str = EntityUtils.toString(response.getEntity());
				} catch (IOException e) 
				{
					e.printStackTrace();
				}
			
			JSONObject json;
			try {
				json = new JSONObject(str);
				if (json.getString("error").equals("")) {
					JSONArray array = json.getJSONArray("requests");
					for (int i = 0; i < array.length(); i++) {
						requests.add(new RequestList(array.getJSONObject(i)));
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