package com.mpss.weed.id.expert;

import java.io.IOException;
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
import com.mpss.weed.id.common.RequestListAdapter;
import com.mpss.weed.id.common.RequestListExtendedAdapter;
import com.mpss.weed.id.common.SessionApp;
import com.mpss.weed.id.utils.InstructionAcitivity;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ListView;



public class AvailableRequestListViewActivity extends ListActivity {
	Context context = this;
	ArrayList<RequestList> requests = new ArrayList<RequestList>();
	RequestListExtendedAdapter adapter;
	ProgressDialog progress;
	String expertID;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.diagnose_list);
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

		

		super.onListItemClick(l, v, position, id);
		
		//while tapping, should load the instruction or detail requests
		 String key=this.getString(R.string.instructionKeyESub);			
		 String instructionstring=this.getString(R.string.instruction_exSubmit);
		 
		final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
		Intent intent;
        boolean hasBeenShown = prefs.getBoolean(key, false);
     
       if(hasBeenShown == false){
       	intent = new Intent(this,
       			InstructionAcitivity.class);
       	    intent.putExtra("instruction", instructionstring);
			intent.putExtra("key", key);
			intent.putExtra("instructionType", "ESub");
			intent.putExtra("request", (RequestList) l.getAdapter().getItem(position));
   		
   		startActivity(intent);
		//CustomizeDialog customizeDialog = new CustomizeDialog(this,prefs,instructionstring,key);
		//customizeDialog.show();
       }
       else{
    	RequestList r = (RequestList) l.getAdapter().getItem(position);
   		intent = new Intent(context, ExpertDiagnoseRequestActivity.class);
   		intent.putExtra("request", r);
   		intent.putExtra("expertID", expertID);
   		startActivity(intent);
		
       }
	}
	
	private class GetRequestListTask extends AsyncTask<Void, Void, Boolean> {
		@Override
		protected Boolean doInBackground(Void... voids) {
			//String temp = "http://mpss.csce.uark.edu/smsdb/weed_app/weedapp/requestdiagnose.php";
			String requestdiagnose_url=getString(R.string.requestdiagnosemap_url);
			//String requestdiagnose_url=getString(R.string.requestdiagnose_url);
			
			HttpClient hc = new DefaultHttpClient();
			HttpPost post = new HttpPost(requestdiagnose_url);
			
			String str = "***";
			try {
				HttpResponse rp = hc.execute(post);
				if (rp.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
					str = EntityUtils.toString(rp.getEntity());
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