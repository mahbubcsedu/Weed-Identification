package com.mpss.weed.id.farmer;

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
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class FarmerLoginActivity extends Activity {
	Context context = this;

	Button signIn, register, about;
	EditText username, password;

	String firstName, lastName, farmerID, error;

	ProgressDialog progress;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		progress = new ProgressDialog(context);
		TextView t = (TextView) findViewById(R.id.title);
		t.setText("Farmer Weed App");
        
		signIn = (Button) findViewById(R.id.signin);
		register = (Button) findViewById(R.id.register);
		about = (Button) findViewById(R.id.about);
		username = (EditText) findViewById(R.id.username);
		password = (EditText) findViewById(R.id.password);
		

		signIn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
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
				Intent intent = new Intent(context,
						FarmerRegisterActivity.class);
				startActivity(intent);
			}
		});

		//
		// about.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// AlertDialog.Builder builder = new AlertDialog.Builder(context);
		// builder.setMessage(
		// "This will explain what this application is all about!")
		// .setCancelable(false)
		// .setPositiveButton("Dismiss",
		// new DialogInterface.OnClickListener() {
		// public void onClick(DialogInterface dialog,
		// int id) {
		// dialog.cancel();
		// }
		// });
		// AlertDialog alert = builder.create();
		// alert.show();
		// }
		// });
	}

	public void attemptLogin(String user, String fpassword) {
		progress.setMessage("Signing in...");
		progress.setCancelable(false);
		progress.show();
		String temp = "http://mpss.csce.uark.edu/~mweathers/weedapp/loginfarmer.php?code=54m3xuzm97z30rdfsloegjizvzgga12bshptv59o";

		HttpPost post = new HttpPost(temp);

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

	public void finishLoginAttempt(Boolean successful) {
		if (successful) {
			progress.dismiss();
			// Toast.makeText(context, "SUCCESS", Toast.LENGTH_SHORT).show();
			Intent intent = new Intent(context, FarmerHomeActivity.class);
			intent.putExtra("farmerID", farmerID);
			startActivity(intent);
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
					farmerID = json.getString("farmer_id");
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
}