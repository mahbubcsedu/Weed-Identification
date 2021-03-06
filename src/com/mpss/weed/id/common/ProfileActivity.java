package com.mpss.weed.id.common;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.mpss.weed.id.R;
import com.mpss.weed.id.farmer.FarmerHomeActivity;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileActivity extends Activity{
	Context context = this;
	String firstName, lastName, farmerID, error,userID,profile_type,county,user_name,email;

	ProgressDialog progress;
	TextView pro_username,pro_lastname,pro_firstname,pro_county,pro_user_type, pro_email;
	Button edit;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		 userID=SessionApp.getUserLoggedUserID();
			if(userID==null)
			{
				startActivity(new Intent(context, LoginActivity.class));
			}
		setContentView(R.layout.profile_display);
		//userID=getIntent().getStringExtra("userID");
		edit = (Button) findViewById(R.id.editProfile);	
		edit.setText(this.getString(R.string.profilepasschange));
		//SessionApp appState = ((SessionApp)getApplicationContext());
	    //String userID = appState.getLoginID();
		userID=SessionApp.getUserLoggedUserID();
		progress = new ProgressDialog(context);
		attempttoGetProfile(userID);
		/*pro_username=(TextView)findViewById(R.id.pro_username);
		pro_username.setText(user_name);
		pro_lastname=(TextView)findViewById(R.id.pro_last_name);
		pro_lastname.setText(lastName);
		pro_firstname=(TextView)findViewById(R.id.pro_first_name);
		pro_firstname.setText(firstName);
		pro_county=(TextView)findViewById(R.id.pro_county);
		pro_county.setText(county);
		pro_user_type=(TextView)findViewById(R.id.pro_userType);
		pro_user_type.setText(profile_type);*/
		
		edit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent=new Intent(context,ChangePasswordActivity.class);
				intent.putExtra("usernamevalue", pro_username.getText());
				intent.putExtra("firstvalue", pro_firstname.getText());
				intent.putExtra("lastvalue", pro_lastname.getText());
				intent.putExtra("emailvalue", pro_email.getText());
				intent.putExtra("countyvalue", pro_county.getText());
				intent.putExtra("usertypevalue", pro_user_type.getText());
				startActivity(intent);
				//intent.putExtra("usernamevalue", pro_username.getText());
			}
		});
	}
	
	
	public void attempttoGetProfile(String userID) {
		progress.setMessage("Loading Profile...");
		progress.setCancelable(false);
		progress.show();
		//String profile = "http://mpss.csce.uark.edu/smsdb/weed_app/weedapp/profile.php?code=54m3xuzm97z30rdfsloegjizvzgga12bshptv59o";
		String profile =getString(R.string.profile_url);
		HttpPost post = new HttpPost(profile);

		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
		nameValuePairs.add(new BasicNameValuePair("profile_id", userID));
		//nameValuePairs.add(new BasicNameValuePair("fpassword", fpassword));

		try {
			post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		new ProfileDownloadTask().execute(post);
	}

	public void finishProfiletoGetAttempt(Boolean successful) {
		if (successful) {
			progress.dismiss();
			//Toast.makeText(context, "Displaying Profile", Toast.LENGTH_SHORT).show();
			pro_username=(TextView)findViewById(R.id.pro_username);
			pro_username.setText(user_name);
			pro_lastname=(TextView)findViewById(R.id.pro_last_name);
			pro_lastname.setText(lastName);
			pro_firstname=(TextView)findViewById(R.id.pro_first_name);
			pro_firstname.setText(firstName);
			pro_county=(TextView)findViewById(R.id.pro_county);
			pro_county.setText(county);
			pro_user_type=(TextView)findViewById(R.id.pro_userType);
			pro_user_type.setText(profile_type);
			pro_email=(TextView)findViewById(R.id.pro_email);
			pro_email.setText(email);
			//Intent intent = new Intent(context, FarmerHomeActivity.class);
			//intent.putExtra("farmerID", farmerID);
			//startActivity(intent);
		} else {
			Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
		}
	}

	private class ProfileDownloadTask extends AsyncTask<HttpPost, Void, Boolean> {
		@Override
		protected Boolean doInBackground(HttpPost... posts) {
			HttpClient hc = new DefaultHttpClient();
			HttpResponse rp = null;
			try {
				rp = hc.execute(posts[0]);
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String response = null;
			if (rp.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
				try {
					response = EntityUtils.toString(rp.getEntity());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			JSONObject json = null;
			try {
				json = new JSONObject(response);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if (json.getString("error").equals("")) {
					firstName = json.getString("first_name");
					lastName = json.getString("last_name");
					user_name=json.getString("user_name");
					email=json.getString("email");
					profile_type = json.getString("profile_type");
					if(profile_type.equals("1")){
						profile_type="Expert";
					}else if(profile_type.equals("2")){
						profile_type="Farmer";
					}
					county = json.getString("county");
					//lastName = json.getString("last_name");
					return Boolean.TRUE;
				} else {
					error = json.getString("error");
					return Boolean.FALSE;
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Boolean result) {
			finishProfiletoGetAttempt(result);
		}
	}
}
