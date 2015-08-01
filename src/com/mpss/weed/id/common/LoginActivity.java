package com.mpss.weed.id.common;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
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
import com.mpss.weed.id.utils.InstructionAcitivity;
//import com.mpss.weed.id.farmer.GridViewActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.net.ParseException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
	private static final String TAG = "LoginActivity";
	Context context = this;

	Button signIn, register, about,btnReset,credits;
	EditText username, password;
	private double swidth,sheight;
	double height, width, xvalue,yvalue;
	private double yratio,xratio;
    private int[] locuser,locpass,locsignin,locregister;
	String firstName, lastName, expertID, error,user_type,userID;
	int titleHeight, statusHeight,contentViewTop;
	String instructionstring,key;
	ProgressDialog progress;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		 requestWindowFeature(Window.FEATURE_NO_TITLE);
	        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
	            WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.login2_rel);
		 init();
		/* AbsoluteLayout root=(AbsoluteLayout)findViewById(R.id.ablogin);   
		   
	    root.post(new Runnable() {
	        public void run() {
	            Rect rect = new Rect();
	            Window win = getWindow();
	            win.getDecorView().getWindowVisibleDisplayFrame(rect);
	            statusHeight = rect.top;
	            contentViewTop = win.findViewById(Window.ID_ANDROID_CONTENT).getTop();
	            titleHeight = contentViewTop - statusHeight;
	            Log.i("Screensize", "titleHeight = " + titleHeight + " statusHeight = " + statusHeight + " contentViewTop = " + contentViewTop);

	            // CALCULATE THE SIZE OF INNER LAYOUTS
	            init();
	        }
	    });*/
		
		progress = new ProgressDialog(context);
		
		
	}

	public void init()
	{
		/*progress = new ProgressDialog(context);
		locuser=new int[2];
		locpass=new int[2];
		locsignin=new int[2];
		locregister=new int[2];
		
		 DisplayMetrics metrics = new DisplayMetrics();
	     getWindowManager().getDefaultDisplay().getMetrics(metrics);
	    
	     
	     sheight=(double)metrics.heightPixels;
	     swidth=(double)metrics.widthPixels;
	     yratio=((double)sheight/724);
	     xratio=((double)swidth/480);
	     
	     AbsoluteLayout abl=(AbsoluteLayout)findViewById(R.id.ablogin);
	     Log.d("screensize","value height"+abl.getHeight());
	     
	     //user name position
	     username = (EditText) findViewById(R.id.username);
	     username.getLocationInWindow(locuser);
	     this.height= 30*sheight/724+0.5;
	     this.width=274*swidth/480+0.5;
	     this.xvalue= 42*swidth/480+0.5;//27*swidth/480+0.5;
	     //this.yvalue=(loc[1])*sheight/800+0.5;//(166)*sheight/724+0.5+contentViewTop;
	     this.yvalue=sheight*((locuser[1]+contentViewTop)/metrics.density/800);
	     
	     //Log.i("screensize", loc[0]+"yvalue"+loc[1]);
	     
		 username.setLayoutParams(new AbsoluteLayout.LayoutParams((int)(this.width),(int)(this.height) ,(int)(this.xvalue) ,(int)(this.yvalue)) ); 
		//AbsoluteLayout loginlayout=(AbsoluteLayout)findViewById(R.id.ablogin);
		 Log.d("screensize", "user name=height:"+this.height+"width:"+this.width+"xvalu:"+this.xvalue+"yvalue:"+this.yvalue);
		 
		 //password setup setup
		 password = (EditText) findViewById(R.id.password);
		 password.getLocationInWindow(locpass);
		 this.height= 30*sheight/724+0.5;
	     this.width=274*swidth/480+0.5;
	     this.xvalue=27*swidth/480+0.5;
	     //this.yvalue=(237)*sheight/724+0.5+contentViewTop;
	     this.yvalue=sheight*((locpass[1]+contentViewTop)/metrics.density/800);
	     //password.getLocationInWindow(loc);
	    // Log.i("screensize", loc[0]+"yvalue"+loc[1]);
	     password.setLayoutParams(new AbsoluteLayout.LayoutParams((int)(this.width),(int)(this.height) ,(int)(this.xvalue) ,(int)(this.yvalue)) );
	     Log.d("screensize", "password=height:"+this.height+"width:"+this.width+"xvalu:"+this.xvalue+"yvalue:"+this.yvalue);
	     //sign in setup
	     
	     signIn = (Button) findViewById(R.id.signin);
	     signIn.getLocationInWindow(locsignin);
	     this.height= 48*sheight/724+0.5;
	     this.width=158*swidth/480+0.5;
	     this.xvalue= 82*swidth/480+0.5;
	     //this.yvalue=(289)*sheight/724+0.5+contentViewTop;
	     this.yvalue=sheight*((locsignin[1]+contentViewTop)/metrics.density/800);
	     signIn.setLayoutParams(new AbsoluteLayout.LayoutParams((int)(this.width),(int)(this.height) ,(int)(this.xvalue) ,(int)(this.yvalue)) );
	     Log.d("screensize", "login=height:"+this.height+"width:"+this.width+"xvalu:"+this.xvalue+"yvalue:"+this.yvalue);
	     // register button
	     
	     register = (Button) findViewById(R.id.register);
	     this.height= 48*sheight/724+0.5;
	     this.width=137*swidth/480+0.5;
	     this.xvalue=66*swidth/480+0.5;
	     this.yvalue=(352)*sheight/724+0.5+contentViewTop;
	     register.setLayoutParams(new AbsoluteLayout.LayoutParams((int)(this.width),(int)(this.height) ,(int)(this.xvalue) ,(int)(this.yvalue)) );
	     Log.d("screensize", "registrion=height:"+this.height+"width:"+this.width+"xvalu:"+this.xvalue+"yvalue:"+this.yvalue);
	    //reset button
	     btnReset = (Button) findViewById(R.id.btnReset);
	     this.height= Math.ceil(38*sheight/724);
	     this.width=Math.ceil(79*swidth/480);
	     this.xvalue= Math.ceil(213*swidth/480);
	     this.yvalue=(352)*sheight/724+contentViewTop;
	     btnReset.setLayoutParams(new AbsoluteLayout.LayoutParams((int)(this.width),(int)(this.height) ,(int)(this.xvalue) ,(int)(this.yvalue)) );
	     Log.d("screensize", "reset=height:"+this.height+"width:"+this.width+"xvalu:"+this.xvalue+"yvalue:"+this.yvalue);
	    //Credits button setup
	     credits = (Button) findViewById(R.id.credits);
	     this.height= Math.ceil(22*sheight/724);
	     this.width=Math.ceil(90*swidth/480);
	     this.xvalue= Math.ceil(134*swidth/480);
	     this.yvalue=(396)*sheight/724+0.5+contentViewTop;
	     credits.setLayoutParams(new AbsoluteLayout.LayoutParams((int)(this.width),(int)(this.height) ,(int)(this.xvalue) ,(int)(this.yvalue)) );
	     Log.d("screensize", "credit=height:"+this.height+"width:"+this.width+"xvalu:"+this.xvalue+"yvalue:"+this.yvalue);
	     //loginlayout.updateViewLayout(username, new AbsoluteLayout.LayoutParams((int)(35*yratio), (int)(274*xratio), (int)(xratio*24), (int)(166*yratio)));
		TextView t = (TextView) findViewById(R.id.title);
		//t.setText("Weed App");

		
		
		
		//signIn.setLayoutParams(new AbsoluteLayout.LayoutParams((int)(35*yratio), (int)(274*xratio), (int)(xratio*24), (int)(166*yratio)));
		//register = (Button) findViewById(R.id.register);
		
		
		*/
		
		key=this.getString(R.string.instructionKeyReg);
		instructionstring=this.getString(R.string.instruction_reg);
		final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
		
		try{
		username = (EditText) findViewById(R.id.username);
		password = (EditText) findViewById(R.id.password);
		signIn = (Button) findViewById(R.id.signin);
		register = (Button) findViewById(R.id.register);
		btnReset = (Button) findViewById(R.id.btnReset);
		credits = (Button) findViewById(R.id.credits);
		}catch(Exception e){
			Log.e(TAG, e.getMessage());
		}

		signIn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
			//Toast.makeText(context, "Audio", Toast.LENGTH_SHORT).show();			
			
			String user = username.getText().toString();
			String pass = password.getText().toString();

			if (user.length() == 0) {
				Toast.makeText(context, "Please enter a username",
						Toast.LENGTH_SHORT).show();
			} else if (pass.length() == 0) {
				Toast.makeText(context, "Please enter a password",
						Toast.LENGTH_SHORT).show();
			} else {
				attemptLogin(user, pass);
			}
			}
		});

		register.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				

				//InstructionSetup();
				
				Intent intent;
		        boolean hasBeenShown = prefs.getBoolean(key, false);
		        if(hasBeenShown == false){
		        	intent = new Intent(context,
		        			InstructionAcitivity.class);
		        	intent.putExtra("instruction", instructionstring);
					intent.putExtra("key", key);
					intent.putExtra("instructionType", "reg");
				//CustomizeDialog customizeDialog = new CustomizeDialog(this,prefs,instructionstring,key);
				//customizeDialog.show();
		        }
		        else{
				intent = new Intent(context,
						RegisterActivity.class);
				
		        }
				//Intent intent = new Intent(context,
				//				GridViewActivity.class);
				
				//AbsoluteLayout abl=(AbsoluteLayout)findViewById(R.id.ablogin);
				//Log.d("screensize","value height"+abl.getHeight());
				 //EditText username2 = (EditText) findViewById(R.id.username);
			    // username2.getLocationInWindow(loc);
			    // Log.i("screensize", loc[0]+"yvalue"+loc[1]);
				startActivity(intent);				
				//Toast.makeText(context, "login", Toast.LENGTH_SHORT).show();
			}
		});

		btnReset.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 String user1 = username.getText().toString();
					progress.setMessage("Resetting Password...");
					progress.setCancelable(false);
					progress.show();
					//SendEmail();
					attempResetting(user1);
				 
				//Toast.makeText(context, "login", Toast.LENGTH_SHORT).show();

			}

		});
		//
		credits.setOnClickListener(new OnClickListener() {
		//
		@Override
		public void onClick(View v) {
			AlertDialog.Builder builder = new AlertDialog.Builder(context);
			 builder.setMessage(
			 "Credits will goes here in future..................!")
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
			 
			//Toast.makeText(context, "login", Toast.LENGTH_SHORT).show();
		 }
		});
		//Log.d("screensize","value height"+abl.getHeight());
	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		//super.onBackPressed();
		startActivity(new Intent(this, LoginActivity.class));
		return;
	}

	public void attemptLogin(String user, String fpassword) {
		progress.setMessage("Signing in...");
		progress.setCancelable(false);
		progress.show();
		//String login_url = "http://mpss.csce.uark.edu/smsdb/weed_app/weedapp/login.php?code=54m3xuzm97z30rdfsloegjizvzgga12bshptv59o";
		String login_url =getString(R.string.login_url);
		HttpPost post = new HttpPost(login_url);

		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
		nameValuePairs.add(new BasicNameValuePair("username", user));
		nameValuePairs.add(new BasicNameValuePair("fpassword", fpassword));

		try {
			post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		new LoginTask().execute(post);
	}
	public void attempResetting(String user) {
		progress.setMessage("Resetting...");
		progress.setCancelable(false);
		progress.show();
		//String temp = "http://mpss.csce.uark.edu/smsdb/weed_app/weedapp/resetpassword.php";
        String passreset=getString(R.string.passreset_url);
		HttpPost post = new HttpPost(passreset);

		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
		nameValuePairs.add(new BasicNameValuePair("user_name", user));
		//nameValuePairs.add(new BasicNameValuePair("fpassword", fpassword));

		try {
			post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		new ResettingPasswordTask().execute(post);
	}
	
	public void finishLoginAttempt(Boolean successful) {
		progress.dismiss();
		if (successful) {
			SessionApp.setLoggedUserID(userID);//session start very very important
			SessionApp.setUserType(user_type);

			// Toast.makeText(context, "SUCCESS", Toast.LENGTH_SHORT).show();
			if(user_type.equals("1")){

				Intent intent = new Intent(context, ExpertHomeActivity.class);
				//intent.putExtra("expertID", userID);
				startActivity(intent);
			}else if(user_type.equals("2")){
				Intent intent = new Intent(context, FarmerHomeActivity.class);
				//intent.putExtra("farmerID", userID);
				startActivity(intent);
			}
		} else {
			Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
		}
	}
	public void finishResettingAttempt(Boolean successful) {
		progress.dismiss();
		if (successful) {
			 Toast.makeText(context, "SUCCESS", Toast.LENGTH_SHORT).show();
			
		} else {
			Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
		}
	}
	private class LoginTask extends AsyncTask<HttpPost, Void, Boolean> {
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
					userID = json.getString("profile_id");
					user_type = json.getString("profile_type");
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
			finishLoginAttempt(result);
		}
	}

	private class ResettingPasswordTask extends AsyncTask<HttpPost, Void, Boolean> {
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
			finishResettingAttempt(result);
		}
	}
	
	//-----------------this is for optional menu login design.....................
	
	/**
	 * Creates Option Menu from xml file
	 */
	/*
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.login_menu, menu);
		return true;
	}
*/
	
	
	/**
	 * Listens for the button to be pressed and initiates logout if clicked
	 */
	/*
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.login:			
			Toast.makeText(context, "Audio", Toast.LENGTH_SHORT).show();
			
			
			String user = username.getText().toString();
			String pass = password.getText().toString();

			if (user.length() == 0) {
				Toast.makeText(context, "Please enter a username",
						Toast.LENGTH_SHORT).show();
			} else if (pass.length() == 0) {
				Toast.makeText(context, "Please enter a password",
						Toast.LENGTH_SHORT).show();
			} else {
				attemptLogin(user, pass);
			}
			return true;
		case R.id.register:
			
			Intent intent = new Intent(context,
					RegisterActivity.class);
			startActivity(intent);
			 
			 
			
			Toast.makeText(context, "login", Toast.LENGTH_SHORT).show();
			return true;
		case R.id.credits:
			
			AlertDialog.Builder builder = new AlertDialog.Builder(context);
			 builder.setMessage(
			 "This will explain what this application is all about!")
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
			 
			Toast.makeText(context, "login", Toast.LENGTH_SHORT).show();
			return true;
			
          case R.id.reset:
			
        	    String user1 = username.getText().toString();
				progress.setMessage("Resetting Password...");
				progress.setCancelable(false);
				progress.show();
				//SendEmail();
				attempResetting(user1);
			 
			Toast.makeText(context, "login", Toast.LENGTH_SHORT).show();
			return true;
			
		default:
			return super.onOptionsItemSelected(item);
		}
	}
*///-------------------------------End of optinal menu login process-------------------------
}