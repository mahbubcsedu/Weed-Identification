package com.mpss.weed.id.common;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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

import com.mpss.weed.id.map.LocationSelectMapActivity;
import com.mpss.weed.id.utils.CustomizeDialog;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

public class RegisterActivity extends Activity implements LocationListener {
	Context context = this;
	ProgressDialog progress;
	EditText user, first, last, password, password2,email;
	Button register,mapLocation,btnDismiss;
	CheckBox dontShow;
	Spinner county,userType;
	String user_type;
	Double latitude=0.0, longitude=0.0;
    private RelativeLayout topLevelLayout;
    public static final String PREFS_NAME = "MyPrefsFile1";
    public CheckBox dontShowAgain;

	LocationManager locationManager;
	Location myLocation = null;

	String tempLat = "0.00", tempLong = "0.00", tempRanking = "1";
	SimpleDateFormat memberSinceFormat = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat lastVisitedFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	String firstName, lastName, profile_ID, error;

	private static final String EMAIL_PATTERN = "^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$";

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.register);
		progress = new ProgressDialog(context);

		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
			locationManager.requestLocationUpdates(
					LocationManager.GPS_PROVIDER, 0, 0, this);
		} else {
			locationManager.requestLocationUpdates(
					LocationManager.NETWORK_PROVIDER, 0, 0, this);
		}


		user = (EditText) findViewById(R.id.username);
		first = (EditText) findViewById(R.id.first_name);
		last = (EditText) findViewById(R.id.last_name);
		password = (EditText) findViewById(R.id.password);
		password2 = (EditText) findViewById(R.id.password2);
		email = (EditText) findViewById(R.id.edtEmail);
		register = (Button) findViewById(R.id.register);

		mapLocation = (Button) findViewById(R.id.mapLocation);

		mapLocation.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				//if (meetsRequirements() && myLocation != null) {
				mapLocationForRegistration();
				//	}
			}
		});
		register.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v)
			{
				if (meetsRequirements() && myLocation != null) {
					attemptRegister();
				}
			}
		});

		// Spinner spinner = (Spinner) findViewById(R.id.spinner);
		county = (android.widget.Spinner) findViewById(R.id.spinner);

		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.county_array,
				android.R.layout.simple_spinner_item);//android.R.layout.simple_spinner_item
		adapter.setDropDownViewResource(R.layout.spinner);//android.R.layout.simple_spinner_dropdown_item

		county.setAdapter(adapter);
		userType=(android.widget.Spinner) findViewById(R.id.sp_pro_type);
		ArrayAdapter<CharSequence> adapter_ut = ArrayAdapter.createFromResource(
				this, R.array.user_type,
				android.R.layout.simple_spinner_item);
		adapter_ut.setDropDownViewResource(R.layout.spinner);

		userType.setAdapter(adapter_ut);
		
		
	/*	String key=this.getString(R.string.instructionKeyReg);

		//InstructionSetup();
		String instructionstring=this.getString(R.string.instruction_reg);
		final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        boolean hasBeenShown = prefs.getBoolean(key, false);
        if(hasBeenShown == false){
		CustomizeDialog customizeDialog = new CustomizeDialog(this,prefs,instructionstring,key);
		customizeDialog.show();
        }*/
		//new Instructions(this).show(this);
		/*btnDismiss=(Button) findViewById(R.id.btn_dismiss);
		dontShow=(CheckBox)findViewById(R.id.skip);
		topLevelLayout = (RelativeLayout) findViewById(R.id.top_layout);

		if (isFirstTime()) {
			
			topLevelLayout.setVisibility(View.INVISIBLE);
		}*/
		
	}
	public void mapLocationForRegistration(){

		Intent intent = new Intent(this, LocationSelectMapActivity.class);
		Double latitude,longtitude;
		if(myLocation!=null){
			intent.putExtra("latitude", myLocation.getLatitude());
			intent.putExtra("longitude", myLocation.getLongitude());
		}else{
			intent.putExtra("latitude", "");
			intent.putExtra("longitude", "");
		}
		startActivityForResult(intent, 101);

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode==RESULT_OK && requestCode==101){
			String msg = data.getStringExtra("location");
			String [] locvalue=msg.split("[,]");
			latitude=Double.parseDouble(locvalue[0]);
			longitude=Double.parseDouble(locvalue[1]);
			myLocation=new Location(LocationManager.GPS_PROVIDER);
			// Location myTempLocation= new Location();
			//myLocation=new Location();
			if(latitude==null ||longitude==null){
				Toast.makeText(context, "GPS Location failed to  get, select again!", Toast.LENGTH_SHORT).show();
				return;
			}else{
				myLocation.setLatitude(latitude);
				myLocation.setLongitude(longitude);
			}

		}
		// textView.setText(msg);
	}
	public boolean meetsRequirements() {


		try {

			Pattern pattern = Pattern.compile(EMAIL_PATTERN);
			Matcher matcher = pattern.matcher(email.getText().toString());

			if (!matcher.matches()) {

				Toast.makeText(this," Invid email address ",Toast.LENGTH_LONG).show();

			}

		}catch(Exception ex){
		}


		if (user.getText().toString().length() < 5) {
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
		} else if (password.getText().toString().length() < 8) {
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
		/*else if (!county.getSelectedItem().toString().contains("Arkansas")||!county.getSelectedItem().toString().contains("arkansas")) {
			Toast.makeText(context,
					"The service is only limited to the State of Arkansas right now",
					Toast.LENGTH_SHORT).show();
			return false;
		}*/

		return true;
	}

	public void attemptRegister() {
		progress.setMessage("Registering account");
		progress.setCancelable(false);
		progress.show();
		//String temp = "http://mpss.csce.uark.edu/smsdb/weed_app/weedapp/insertprofile.php?code=54m3xuzm97z30rdfsloegjizvzgga12bshptv59o";
		String registration_url=getString(R.string.registration_url);
		HttpPost post = new HttpPost(registration_url);

		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
		nameValuePairs.add(new BasicNameValuePair("username", user.getText()
				.toString()));
		nameValuePairs.add(new BasicNameValuePair("firstname", first.getText()
				.toString()));
		nameValuePairs.add(new BasicNameValuePair("lastname", last.getText()
				.toString()));
		nameValuePairs.add(new BasicNameValuePair("fpassword", password
				.getText().toString()));
		nameValuePairs.add(new BasicNameValuePair("location", myLocation
				.getLatitude() + "," + myLocation.getLongitude()));

		//nameValuePairs.add(new BasicNameValuePair("location", latitude + "," + longitude));

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
		}

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
		if (successful) {
			//Toast.makeText(context, "SUCCESS", Toast.LENGTH_SHORT).show();
			if(user_type.equals("1")){
				Intent intent = new Intent(context, LoginActivity.class);
				intent.putExtra("expertID", profile_ID);
				startActivity(intent);
			}else if(user_type.equals("2")){
				Intent intent = new Intent(context, LoginActivity.class);
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
					firstName = json.getString("first_name");
					lastName = json.getString("last_name");
					profile_ID = json.getString("profile_id");
					user_type=json.getString("profile_type");

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

	private void InstructionSetup(){
		AlertDialog.Builder adb=new AlertDialog.Builder(this);
	    LayoutInflater adbInflater = LayoutInflater.from(this);
	    View eulaLayout = adbInflater.inflate(R.layout.checkbox, null);
	    dontShowAgain = (CheckBox)eulaLayout.findViewById(R.id.skip);
	    adb.setView(eulaLayout);
	    adb.setTitle("Attention");
	    adb.setMessage(Html.fromHtml("This version is going to be depricated soon. <br>" +
				"Get Yahrtzeit2 in case you want to store your own Yahrtzeit dates<br>" +
				"Do you want to get Yahrtzeit2?"));
    	adb.setPositiveButton("Ok", new DialogInterface.OnClickListener() {  
    	      public void onClick(DialogInterface dialog, int which) {
    	    	  String checkBoxResult = "NOT checked";
    	    	  if (dontShowAgain.isChecked())  checkBoxResult = "checked";
    	    	  	SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
    	  	    	SharedPreferences.Editor editor = settings.edit();
    	  			editor.putString("skipMessage", checkBoxResult);	
    	  			// Commit the edits!
    	  			editor.commit();
    	  		  startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("market://details?id=com.mb.yartzeit2")));
    	    	  return;  
    	      } });
    	
    	adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {  
  	      public void onClick(DialogInterface dialog, int which) {
	    	  String checkBoxResult = "NOT checked";
	    	  if (dontShowAgain.isChecked())  checkBoxResult = "checked";
	    	  SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
	  	    	SharedPreferences.Editor editor = settings.edit();
	  			editor.putString("skipMessage", checkBoxResult);	
	  			// Commit the edits!
	  			editor.commit();
	    		  return;  
	      } });
    	SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
	    String skipMessage = settings.getString("skipMessage", "NOT checked");
		if (!skipMessage.equalsIgnoreCase("checked") ) adb.show();
	}
	private boolean isFirstTime()
	{
		final SharedPreferences preferences = getPreferences(MODE_PRIVATE);
		boolean ranBefore = preferences.getBoolean("dontShow", false);
		if (!ranBefore) {

			
			topLevelLayout.setVisibility(View.VISIBLE);
			btnDismiss.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					topLevelLayout.setVisibility(View.INVISIBLE);
					SharedPreferences.Editor editor = preferences.edit();
					if(dontShow.isChecked()){
					editor.putBoolean("dontShow", true);
					}else{
						editor.putBoolean("dontShow", false);
					}
					editor.commit();
					//return false;
				}
				
			});
			return false;
			/*topLevelLayout.setOnTouchListener(new View.OnTouchListener(){



				@Override
				public boolean onTouch(View v, MotionEvent event) {
					topLevelLayout.setVisibility(View.INVISIBLE);
					return false;
				}

			});*/

		}else
		{
		     // DO something
		     SharedPreferences.Editor editor = preferences.edit();
		     editor.putBoolean("dontShow", false);
		     editor.commit();
		}
		return ranBefore;

	}
}