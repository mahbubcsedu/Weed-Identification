package com.mpss.weed.id.farmer;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mpss.weed.id.common.LoginActivity;
import com.mpss.weed.id.common.SessionApp;
import com.mpss.weed.id.expert.ExpertHomeActivity;
import com.mpss.weed.id.utils.*;

import com.mpss.weed.id.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.media.MediaRecorder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ImageView.ScaleType;

public class FarmerNewRequest_cprevActivity extends Activity {
	Context context = this;
	
	ProgressDialog progress;

	Bitmap bitmap;
	Button btnTakePhoto;  
	ImageView imgTakenPhoto;  
	EditText txtComments;
	String strImagePath="",strAudioPath="",imageheight="",imagewidth="";
	String userID="",profile_type,error;
	Location currentLocation=null;
	private static final int CAMERA_PIC_REQUEST = 1313;  

	private static final String TAG="NewRequest";
	private static final String AUDIO_RECORDER_FILE_EXT_3GP = ".3gp";
	private static final String AUDIO_RECORDER_FILE_EXT_MP4 = ".mp4";
	private static final String AUDIO_RECORDER_FOLDER = "AudioRecorder";

	private MediaRecorder recorder = null;
	Chronometer mChronometer;
	private int currentFormat = 0;
	private int output_formats[] = { MediaRecorder.OutputFormat.MPEG_4, MediaRecorder.OutputFormat.THREE_GPP };
	private String file_exts[] = { AUDIO_RECORDER_FILE_EXT_MP4, AUDIO_RECORDER_FILE_EXT_3GP }; 

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.add_request_cprev);

		progress = new ProgressDialog(context);
		//btnTakePhoto = (Button) findViewById(R.id.btnTP);  
		//imgTakenPhoto = (ImageView) findViewById(R.id.imageView1);  
		//btnHome = (Button) findViewById(R.id.btn_Home);  

		//btnTakePhoto.setOnClickListener(new btnTakePhotoClicker());  
		txtComments=(EditText)findViewById(R.id.txtComments);

		setButtonHandlers();
		enableButtons(false);
		setFormatButtonCaption();

		EditText eTxtComments=(EditText) findViewById(R.id.txtComments); 
		//Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		//startActivityForResult(camera, 100);// capturing picture
		Bundle extras = getIntent().getExtras();
		if(extras !=null) {
		    userID= extras.getString("userID");
	     }	
		Log.i(TAG,userID);
		Button send = (Button) findViewById(R.id.send);
		GetCurrentGPSLocation currLoc=new GetCurrentGPSLocation(this);
		currentLocation=currLoc.getLocation();
		
		send.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//btnHome.setEnabled(false);
				progress.setMessage("Sending Requst...");
				progress.setCancelable(false);
				progress.show();
				new UploadNewRequestTask().execute((Void[])null);
			}
		});
		/*btnHome.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(context,FarmerHomeActivity.class);
				intent.putExtra("userID", userID);
				startActivity(intent);
			}});*/
	}


	@Override  
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {  
		// TODO Auto-generated method stub  
		super.onActivityResult(requestCode, resultCode, data);  

		if (requestCode == CAMERA_PIC_REQUEST) {  
			Bitmap thumbnail = (Bitmap) data.getExtras().get("data");  
			
			bitmap=thumbnail;
			imageheight=Integer.toString(bitmap.getHeight());
			imagewidth=Integer.toString(bitmap.getWidth());
			Log.i("TAG",imageheight);
			Log.i("TAG",imagewidth);
			imgTakenPhoto.setImageBitmap(thumbnail);  
		}  
	}  

	/*protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 100) {
			Bitmap bitmap = null;
			if (resultCode == RESULT_OK) {
				// Image captured and saved to fileUri specified in the Intent
				bitmap = (Bitmap) data.getExtras().get("data");
				// ImageView image = (ImageView) findViewById(R.id.image);
				// image.setImageBitmap(bitmap);
			} else if (resultCode == RESULT_CANCELED) {
				// User cancelled the image capture
			} else {1
				// Image capture failed, advise user
			}


			//if result is 0 then what should we do
			int width = bitmap.getWidth();
			int height = bitmap.getHeight();

			// calculate the scale - in this case = 2.0f
			float scaleWidth = (float) 2.0;
			float scaleHeight = (float) 2.0;

			// createa matrix for the manipulation
			Matrix matrix = new Matrix();
			// resize the bit map
			matrix.postScale(scaleWidth, scaleHeight);
			// rotate the Bitmap
			matrix.postRotate(270);

			// recreate the new Bitmap
			Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, width,
					height, matrix, true);
			bitmap = resizedBitmap;

			// make a Drawable from Bitmap to allow to set the BitMap
			// to the ImageView, ImageButton or what ever
			BitmapDrawable bmd = new BitmapDrawable(resizedBitmap);

			ImageView image = (ImageView) findViewById(R.id.image);

			// set the Drawable on the ImageView
			image.setImageDrawable(bmd);

			// center the Image
			image.setScaleType(ScaleType.CENTER);
		}
	}*/
	public void finishUploading(Boolean successful) {
		
		profile_type=SessionApp.getUserType();
		if (successful) {
			//Toast.makeText(context, "SUCCESS", Toast.LENGTH_SHORT).show();
			if(profile_type.equals("1")){
				Intent intent = new Intent(context, ExpertHomeActivity.class);
				//intent.putExtra("expertID", profile_ID);
				startActivity(intent);
			}else if(profile_type.equals("2")){
				Intent intent = new Intent(context, FarmerHomeActivity.class);
				//intent.putExtra("farmerID", profile_ID);
				startActivity(intent);
			}
			
		} else {
			progress.cancel();
			Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
		}
	}
	private class UploadNewRequestTask extends AsyncTask<Void, Void, Boolean> {
		@Override
		protected Boolean doInBackground(Void... voids) {
			String temp = "http://mpss.csce.uark.edu/~mweathers/weedapp/insertrequest.php?image=";
			//Upload uload=new Upload();
			//uload.doFileUpload(strAudioPath);
			//uload.doImageUpload(bitmap);
			UploadtoServer ut=new UploadtoServer(getString(R.string.newrequest_url));
			ut.uploadUserPhoto(bitmap, strAudioPath, txtComments.getText().toString(),userID,imageheight,imagewidth,currentLocation);
			//I could not find this link 
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			//            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
			//            Have to upload the image (in some way)...........................
			//			temp += Base64.encodeToString(out.toByteArray(), Base64.DEFAULT);
			System.out.println(temp);
			HttpClient hc = new DefaultHttpClient();
			HttpPost post = new HttpPost(temp);

			String str = "***";
			try {
				HttpResponse rp = hc.execute(post);

				if (rp.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
					str = EntityUtils.toString(rp.getEntity());
			} catch (IOException e) {
				e.printStackTrace();
			}

			System.out.println(str);
			//			JSONObject json;
			//			try {
			//				json = new JSONObject(str);
			//				if (json.getString("error").equals("")) {
			//					JSONArray array = json.getJSONArray("weeds");
			//
			//					for (int i = 0; i < array.length(); i++) {
			//						weeds.add(new Weed(array.getJSONObject(i)));
			//						// new DownloadImageTask().execute(weeds.get(i));
			//					}
			//				}
			//			} catch (JSONException e) {
			//				// TODO Auto-generated catch block
			//				Log.v("YO", e.toString());
			//				return Boolean.FALSE;
			//			}
			//btnHome.setEnabled(true);
			return Boolean.TRUE;
		}

		@Override
		protected void onPostExecute(Boolean result) {
			//			adapter.notifyDataSetChanged();
						progress.dismiss();
			//			
			finishUploading(result);
		}
	}


	private void setButtonHandlers() {
		((Button)findViewById(R.id.btnStart)).setOnClickListener(btnClick);
		((Button)findViewById(R.id.btnStop)).setOnClickListener(btnClick);
		((Button)findViewById(R.id.btnFormat)).setOnClickListener(btnClick);
		((Button)findViewById(R.id.btnReset)).setOnClickListener(btnClick);
		mChronometer = (Chronometer) findViewById(R.id.chronometer);
	}

	private void enableButton(int id,boolean isEnable){
		((Button)findViewById(id)).setEnabled(isEnable);
	}

	private void enableButtons(boolean isRecording) {
		enableButton(R.id.btnStart,!isRecording);
		enableButton(R.id.btnFormat,!isRecording);
		enableButton(R.id.btnStop,isRecording);
		enableButton(R.id.btnReset,!isRecording);
	}

	private void setFormatButtonCaption(){
		((Button)findViewById(R.id.btnFormat)).setText(getString(R.string.audio_format) + " (" + file_exts[currentFormat] + ")");
	}

	private String getFilename(){
		String filePath="";
		String filepath = Environment.getExternalStorageDirectory().getPath();
		File file = new File(filepath,AUDIO_RECORDER_FOLDER);

		if(!file.exists()){
			file.mkdirs();
		}

		//can give the unique title of the record
		filePath=file.getAbsolutePath() + "/" + System.currentTimeMillis() + file_exts[currentFormat];
		strAudioPath=filePath;
		return (strAudioPath);
	}

	private void startRecording(){
		recorder = new MediaRecorder();

		recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
		recorder.setOutputFormat(output_formats[currentFormat]);
		recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
		recorder.setOutputFile(getFilename());


		recorder.setOnErrorListener(errorListener);
		recorder.setOnInfoListener(infoListener);

		try {
			mChronometer.setBase(SystemClock.elapsedRealtime());
			recorder.prepare();
			recorder.start();
			mChronometer.start();

		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void stopRecording(){
		if(null != recorder){
			recorder.stop();
			recorder.reset();
			recorder.release();
			mChronometer.stop();
			recorder = null;
		}
	}

	private void displayFormatDialog(){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		String formats[] = {"MPEG 4", "3GPP"};

		builder.setTitle(getString(R.string.choose_format_title))
		.setSingleChoiceItems(formats, currentFormat, new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				currentFormat = which;
				setFormatButtonCaption();                                        
				dialog.dismiss();
			}
		})
		.show();
	}

	private MediaRecorder.OnErrorListener errorListener = new MediaRecorder.OnErrorListener() {
		@Override
		public void onError(MediaRecorder mr, int what, int extra) {
			AppLog.logString("Error: " + what + ", " + extra);
		}
	};

	private MediaRecorder.OnInfoListener infoListener = new MediaRecorder.OnInfoListener() {
		@Override
		public void onInfo(MediaRecorder mr, int what, int extra) {
			AppLog.logString("Warning: " + what + ", " + extra);
		}
	};
	private void resetRecording(){
		mChronometer.setBase(SystemClock.elapsedRealtime());
		if(null!=recorder){
			recorder.reset();
			recorder=null;

		}
	}
	private View.OnClickListener btnClick = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			switch(v.getId()){
			case R.id.btnStart:{
				AppLog.logString("Start Recording");                                        
				enableButtons(true);
				startRecording();                                                        
				break;
			}
			case R.id.btnStop:{
				AppLog.logString("Stop Recording");                                        
				enableButtons(false);
				stopRecording();                                        
				break;
			}
			case R.id.btnReset:{
				AppLog.logString("Resetting Recording");                                        
				enableButtons(false);
				resetRecording();                                        
				break;
			}
			case R.id.btnFormat:{
				displayFormatDialog();                                        
				break;
			}
			}
		}
	}; 





	class btnTakePhotoClicker implements Button.OnClickListener  
	{  
		@Override  
		public void onClick(View v) {  
			// TODO Auto-generated method stub  

			Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);  
			startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST);  
		}  
	}  
}