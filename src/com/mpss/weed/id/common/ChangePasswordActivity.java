package com.mpss.weed.id.common;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.mpss.weed.id.expert.ExpertHomeActivity;

import com.mpss.weed.id.farmer.FarmerHomeActivity;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ChangePasswordActivity extends Activity implements
		LocationListener {
	Context context = this;
	ProgressDialog progress;
	TextView user, first, last, password, password2,email,uservalue,firstvalue,lastvalue,emailvalue,countyvalue,usertypevalue;
	Button update_profile;
	TextView county,userType;
	TextView user_type;
	String profiletype,userID;

	LocationManager locationManager;
	Location myLocation = null;

	String tempLat = "0.00", tempLong = "0.00", tempRanking = "1";
	SimpleDateFormat memberSinceFormat = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat lastVisitedFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	
	String firstName, lastName, profile_ID, error;

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
			
		setContentView(R.layout.profilepasswordchange);
		progress = new ProgressDialog(context);
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
			locationManager.requestLocationUpdates(
					LocationManager.GPS_PROVIDER, 0, 0, this);
		} else {
			locationManager.requestLocationUpdates(
					LocationManager.NETWORK_PROVIDER, 0, 0, this);
		}

		//user = (TextView) findViewById(R.id.ep_username);
		
		//user.setText(getIntent().getStringExtra("usernamevalue"));
		//user.setEnabled(false);
		
		//first = (TextView) findViewById(R.id.ep_first_name);		
		//first.setText(getIntent().getStringExtra("firstvalue"));
		//first.setEnabled(false);
		
		//last = (TextView) findViewById(R.id.ep_last_name);
		//last.setText(getIntent().getStringExtra("lastvalue"));
		//last.setEnabled(false);
		
		password = (EditText) findViewById(R.id.ep_password);
		password2 = (EditText) findViewById(R.id.ep_password2);
		
		//email = (TextView) findViewById(R.id.ep_edtEmail);
		//email.setText(getIntent().getStringExtra("emailvalue"));
		//email.setEnabled(false);
		
		update_profile = (Button) findViewById(R.id.ep_update);
		//update_profile.setText("Edit");
		update_profile.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (meetsRequirements()) {
					attemptRegister();
				}
			}
		});

		// Spinner spinner = (Spinner) findViewById(R.id.spinner);
	//	county = (android.widget.Spinner) findViewById(R.id.spinner);
		//county = (TextView) findViewById(R.id.ep_county);
		//county.setText(getIntent().getStringExtra("countyvalue"));		
		//county.setEnabled(false);
		
		//user_type = (TextView) findViewById(R.id.ep_pro_type);
		//user_type.setText(getIntent().getStringExtra("usertypevalue"));		
		//user_type.setEnabled(false);
		/*ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.county_array,
				android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		county.setAdapter(adapter);
		county.setSelection(county.getPositionForView(countyvalue));*/
		
		/*userType=(android.widget.Spinner) findViewById(R.id.sp_pro_type);
		userType.setEnabled(false);
		ArrayAdapter<CharSequence> adapter_ut = ArrayAdapter.createFromResource(
				this, R.array.user_type,
				android.R.layout.simple_spinner_item);
		adapter_ut.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		userType.setAdapter(adapter_ut);
		userType.setSelection(userType.getPositionForView(usertypevalue));*/
	}

	public boolean meetsRequirements() {
	/*	if (user.getText().toString().length() < 5) {
			Toast.makeText(context,
					"Usernames must contain at least 5 characters",
					Toast.LENGTH_SHORT).show();
			return false;
		} else if (user.getText().toString().length() > 25) {
			Toast.makeText(context,
					"Usernames must be no longer than 25 characters",
					Toast.LENGTH_SHORT).show();
			return false;
		} else if (first.getText().toString().length() <= 0) {
			Toast.makeText(context, "Please enter a first name",
					Toast.LENGTH_SHORT).show();
			return false;
		} else if (first.getText().toString().length() > 50) {
			Toast.makeText(context,
					"First names must be no longer than 50 characters",
					Toast.LENGTH_SHORT).show();
			return false;
		} else if (last.getText().toString().length() <= 0) {
			Toast.makeText(context, "Please enter a last name",
					Toast.LENGTH_SHORT).show();
			return false;
		} else if (last.getText().toString().length() > 50) {
			Toast.makeText(context,
					"Last names must be no longer than 50 characters",
					Toast.LENGTH_SHORT).show();
			return false;
		} else*/ if (password.getText().toString().length() < 8) {
			Toast.makeText(context,
					"Passwords must contain at least 8 characters",
					Toast.LENGTH_SHORT).show();
			return false;
		} else if (password.getText().toString().length() > 25) {
			Toast.makeText(context,
					"Passwords must be no longer than 25 characters",
					Toast.LENGTH_SHORT).show();
			return false;
		} else if (!password.getText().toString()
				.equals(password2.getText().toString())) {
			Toast.makeText(context, "Passwords must match", Toast.LENGTH_SHORT)
					.show();
			return false;
		}

		return true;
	}

	public void attemptRegister() {
		progress.setMessage("updating account");
		progress.setCancelable(false);
		progress.show();
		//String temp = "http://mpss.csce.uark.edu/smsdb/weed_app/weedapp/editprofile.php?code=54m3xuzm97z30rdfsloegjizvzgga12bshptv59o";
        String profileedit_url=getString(R.string.profileedit_url);
		HttpPost post = new HttpPost(profileedit_url);

		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
		/*nameValuePairs.add(new BasicNameValuePair("username", user.getText()
				.toString()));
		nameValuePairs.add(new BasicNameValuePair("firstname", first.getText()
				.toString()));*/
		nameValuePairs.add(new BasicNameValuePair("profile_id", SessionApp.getUserLoggedUserID()
				.toString()));
		nameValuePairs.add(new BasicNameValuePair("fpassword", password
				.getText().toString()));
		///nameValuePairs.add(new BasicNameValuePair("location", myLocation
			//	.getLatitude() + "," + myLocation.getLongitude()));
		
		/*nameValuePairs.add(new BasicNameValuePair("location", "92.34" + "," + "-123.43"));
		nameValuePairs.add(new BasicNameValuePair("ranking", tempRanking));
		nameValuePairs.add(new BasicNameValuePair("membersince",
				memberSinceFormat.format(new Date())));
		nameValuePairs.add(new BasicNameValuePair("lastvisited",
				lastVisitedFormat.format(new Date())));
		nameValuePairs.add(new BasicNameValuePair("county", county
				.getSelectedItem().toString()));
		nameValuePairs.add(new BasicNameValuePair("email", email
				.getText().toString()));
		if(userType.getSelectedItem().toString().equals("Expert")){
		nameValuePairs.add(new BasicNameValuePair("user_type", "1"));
		}
		else if(userType.getSelectedItem().toString().equals("Farmer"))
		{
			nameValuePairs.add(new BasicNameValuePair("user_type", "2"));
		}*/

		try {
			post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		new RegisterTask().execute(post);
	}

	public void finishRegisterAttempt(Boolean successful) {
		progress.cancel();
		SessionApp.setLoggedUserID(profile_ID);
		profiletype=SessionApp.getUserType();
		if (successful) {
			//Toast.makeText(context, "SUCCESS", Toast.LENGTH_SHORT).show();
			if(profiletype.equals("1")){
				Intent intent = new Intent(context, ExpertHomeActivity.class);
				intent.putExtra("expertID", profile_ID);
				startActivity(intent);
			}else if(profiletype.equals("2")){
				Intent intent = new Intent(context, FarmerHomeActivity.class);
				intent.putExtra("farmerID", profile_ID);
				startActivity(intent);
			}
			
		} else {
			Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
		}
	}

	private class RegisterTask extends AsyncTask<HttpPost, Void, Boolean> {
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
					//firstName = json.getString("first_name");
					//lastName = json.getString("last_name");
					profile_ID = json.getString("profile_id");
					//profiletype=json.getString("profile_type");
					
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
			finishRegisterAttempt(result);
		}
	}

	@Override
	public void onLocationChanged(Location loc) {
		myLocation = loc;
		locationManager.removeUpdates(this);
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}
}