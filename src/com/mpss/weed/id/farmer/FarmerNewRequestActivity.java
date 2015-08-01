package com.mpss.weed.id.farmer;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

import com.mpss.weed.id.common.LoginActivity;
import com.mpss.weed.id.common.SessionApp;
import com.mpss.weed.id.expert.ExpertHomeActivity;
import com.mpss.weed.id.utils.*;
//import com.mpss.weed.id.utils.GetCurrentGPSLocation.MyLocationListener;

import com.mpss.weed.id.R;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.MediaRecorder;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Message;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class FarmerNewRequestActivity extends Activity {
	Context context = this;
	
	ProgressDialog progress;

	protected LocationManager locationManager;

	private String State;
	Bitmap bitmap;
	Button btnTakePhoto;  
	ImageView imgTakenPhoto;  
	EditText txtComments;
	String strImagePath="",strAudioPath="",imageheight="",imagewidth="";
	String userID="",profile_type,error;
	Location currentLocation=null;
	private static final int CAMERA_PIC_REQUEST = 1313;  

	private static final String TAG="Weed_id_app";
	private static final String AUDIO_RECORDER_FILE_EXT_3GP = ".3gp";
	//private static final String AUDIO_RECORDER_FILE_EXT_MP4 = ".mp4";
	private static final String AUDIO_RECORDER_FILE_EXT_MP4 = ".m4a";
	private static final String AUDIO_RECORDER_FOLDER = "AudioRecorder";

	private MediaRecorder recorder = null;
	Chronometer mChronometer;
	private int currentFormat = 0;
	private GetCurrentGPSLocation currLoc;
	
	private int output_formats[] = { MediaRecorder.OutputFormat.MPEG_4, MediaRecorder.OutputFormat.THREE_GPP };
	private String file_exts[] = { AUDIO_RECORDER_FILE_EXT_MP4, AUDIO_RECORDER_FILE_EXT_3GP }; 
	
	private static final long MINIMUM_DISTANCE_CHANGE_FOR_UPDATES = 1; // in Meters
	private static final long MINIMUM_TIME_BETWEEN_UPDATES = 1000; // in Milliseconds
	private boolean gps_enabled = false;
	private boolean network_enabled = false;
	private boolean passive_enabled = false;
	private LocationListener locListener = new MyLocationListener();
	private Location loc;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.add_request);
		//for all case , use this for unauthorize access
		userID=SessionApp.getUserLoggedUserID();
		if(userID==null)
		{
			startActivity(new Intent(context, LoginActivity.class));
		}
		
		progress = new ProgressDialog(context);
		btnTakePhoto = (Button) findViewById(R.id.btnTP);  
		imgTakenPhoto = (ImageView) findViewById(R.id.imageView1);  
		//btnHome = (Button) findViewById(R.id.btn_Home);  

		btnTakePhoto.setOnClickListener(new btnTakePhotoClicker());  
		txtComments=(EditText)findViewById(R.id.txtComments);

		setButtonHandlers();
		enableButtons(false);
		setFormatButtonCaption();

		EditText eTxtComments=(EditText) findViewById(R.id.txtComments); 
		//Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		//startActivityForResult(camera, 100);// capturing picture
		//Bundle extras = getIntent().getExtras();
		//if(extras !=null) 
		//{
		//    userID= extras.getString("userID");
	    // }	
		userID=SessionApp.getUserLoggedUserID();
		Log.i(TAG,userID);
		
		
		
		
		
		Button send = (Button) findViewById(R.id.send);
		send.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//btnHome.setEnabled(false);
				if(loc==null){
					Toast.makeText(FarmerNewRequestActivity.this, "Location can not be detected, you can not add requests", 10).show();
					return;
				}
				else if(State==null){
					if(loc.getLongitude()<=-94.63623 & loc.getLongitude()>=-89.64000 & loc.getLatitude()>=36.49197 & loc.getLatitude()<=32.95337){
						Toast.makeText(FarmerNewRequestActivity.this, "you are currently in "+State +" Currently support only Arkansas", 5).show();	
					}
				}
				else if(!State.toLowerCase().contains("arkansas")){
					 Toast.makeText(FarmerNewRequestActivity.this, "you are currently in "+State +" Currently support only Arkansas", 5).show();
					 return;
					 }
				
				progress.setMessage("Sending Requst...");
				progress.setCancelable(false);
				progress.show();
				currentLocation=currLoc.getLocation();
				
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
		
		/*String key=this.getString(R.string.instructionKeyfAdd);

		//InstructionSetup();
		String instructionstring=this.getString(R.string.instruction_frequest);
		final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        boolean hasBeenShown = prefs.getBoolean(key, false);
        if(hasBeenShown == false){
		CustomizeDialog customizeDialog = new CustomizeDialog(this,prefs,instructionstring,key);
		customizeDialog.show();
        }
        */
        
	}

	
	protected Location showCurrentLocation() {	
	
	 SearchginLocation();
	 
	// Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);		 
	 if (loc != null) {		
		            String message = String.format(		
		                    "Current Location \n Longitude: %1$s \n Latitude: %2$s",		
		                    loc.getLongitude(), loc.getLatitude()		
		            );		
		            //Toast.makeText(this, message,Toast.LENGTH_LONG).show();		
		        }	
		 		return loc;
		    }  
	public void SearchginLocation() 
	{
		//progress.setVisibility(View.VISIBLE);
		// exceptions will be thrown if provider is not permitted.
		try {
			gps_enabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
		} catch (Exception ex) {
		}

		try {
			network_enabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
		} catch (Exception ex) {
		}
		try {
			passive_enabled = locationManager.isProviderEnabled(LocationManager.PASSIVE_PROVIDER);
		} catch (Exception ex) {
		}


		// don't start listeners if no provider is enabled

		if (!gps_enabled &!network_enabled&!passive_enabled) {
			
			System.out.println("Sorry, location is not determined. Please enable location providers");
			
		}



		if (gps_enabled) {
			locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locListener);
			loc=locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		}
		else if (network_enabled) {
			locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locListener);
			loc=locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
		}
		else if (passive_enabled) {
			locationManager.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER, 0, 0, locListener);
			loc=locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
		}
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);		
		         
		     locationManager.requestLocationUpdates(		
		                LocationManager.GPS_PROVIDER,		
		                MINIMUM_TIME_BETWEEN_UPDATES,		
		                MINIMUM_DISTANCE_CHANGE_FOR_UPDATES,		
		                new MyLocationListener()		
		        );

		     currentLocation=showCurrentLocation();
		     if(loc!=null){
		    State=LocationAdress();
		     }
		     else{
		    	 Toast.makeText(this, "No location provider exists-you can not add a reqeust now", 10);
		     }
		//currLoc=new GetCurrentGPSLocation(this);
		//currentLocation=currLoc.getLocation();


		
		
	}
    protected String LocationAdress(){
    	double latitude = currentLocation.getLatitude();
		double longitude = currentLocation.getLongitude();
		String statename=null;
		Geocoder gc = new Geocoder(this, Locale.getDefault());
		
			List<Address> addresses;
			try {
				addresses = gc.getFromLocation(latitude, longitude, 1);
			
			StringBuilder sb = new StringBuilder();
			if (addresses.size() > 0) 
			{
				Address address = addresses.get(0);

				for (int i = 0; i < address.getMaxAddressLineIndex(); i++)
				
					sb.append(address.getAddressLine(i)).append("\n");
					sb.append(address.getLocality()).append("\n");
					sb.append(address.getPostalCode()).append("\n");
					sb.append(address.getCountryName());
					statename=address.getAdminArea().toLowerCase();
					//Toast.makeText(this, address.getLocality(), Toast.LENGTH_SHORT);
			}
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return statename;
    }

	@Override  
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {  
		// TODO Auto-generated method stub  
		super.onActivityResult(requestCode, resultCode, data);  

		if (requestCode == CAMERA_PIC_REQUEST) {  
			try{
			Bitmap thumbnail = (Bitmap) data.getExtras().get("data");  
			
			bitmap=thumbnail;
			imageheight=Integer.toString(bitmap.getHeight());
			imagewidth=Integer.toString(bitmap.getWidth());
			Log.i("TAG",imageheight);
			Log.i("TAG",imagewidth);
			imgTakenPhoto.setImageBitmap(thumbnail); 
			}catch(Exception e){
				//Log.e("camera error",e.getMessage());
				
				imgTakenPhoto.setImageBitmap(null);
				Toast.makeText(context, "No Image Captured, Please Capture again", Toast.LENGTH_SHORT).show();    
				return;
			}
			 
		}  
	}  
	/*private final LocationListener listener = new LocationListener() {

	    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
		public void onLocationChanged(Location location) {
	        // Bypass reverse-geocoding if the Geocoder service is not available on the
	        // device. The isPresent() convenient method is only available on Gingerbread or above.
	        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD && Geocoder.isPresent()) {
	            // Since the geocoding API is synchronous and may take a while.  You don't want to lock
	            // up the UI thread.  Invoking reverse geocoding in an AsyncTask.
	            (new ReverseGeocodingTask(context)).execute(new Location[] {location});
	        }
	    }*/

	
	
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
			//String temp = "http://mpss.csce.uark.edu/~mweathers/weedapp/insertrequest.php?image=";
			
			UploadtoServer ut=new UploadtoServer(getString(R.string.newrequest_url));
			ut.uploadUserPhoto(bitmap, strAudioPath, txtComments.getText().toString(),userID,imageheight,imagewidth,currentLocation);
			
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
		((ImageButton)findViewById(R.id.btnStart)).setOnClickListener(btnClick);
		((ImageButton)findViewById(R.id.btnStop)).setOnClickListener(btnClick);
		//((Button)findViewById(R.id.btnFormat)).setOnClickListener(btnClick);
		((ImageButton)findViewById(R.id.btnReset)).setOnClickListener(btnClick);
		mChronometer = (Chronometer) findViewById(R.id.chronometer);
	}

	private void enableButton(int id,boolean isEnable){
		((ImageButton)findViewById(id)).setEnabled(isEnable);
	}

	private void enableButtons(boolean isRecording) {
		enableButton(R.id.btnStart,!isRecording);
		//enableButton(R.id.btnFormat,!isRecording);
		enableButton(R.id.btnStop,isRecording);
		enableButton(R.id.btnReset,!isRecording);
	}

	private void setFormatButtonCaption(){
		//((Button)findViewById(R.id.btnFormat)).setText(getString(R.string.audio_format) + " (" + file_exts[currentFormat] + ")");
	}

	private String getFilename(){
		String filePath="";
		String filepath = Environment.getExternalStorageDirectory().getPath();
		File file = new File(filepath,AUDIO_RECORDER_FOLDER);

		if(!file.exists()){
			file.mkdirs();
		}

		//can give the unique title of the record
		//filePath=file.getAbsolutePath() + "/" + System.currentTimeMillis() + file_exts[currentFormat];
		filePath=file.getAbsolutePath() + "/" + System.currentTimeMillis() + file_exts[0];
		strAudioPath=filePath;
		return (strAudioPath);
	}

	private void startRecording(){
		((ImageButton)findViewById(R.id.btnStart)).setImageResource(R.drawable.btn_recorder_disable);
		((ImageButton)findViewById(R.id.btnStop)).setImageResource(R.drawable.btn_media_stop_enable);
		((ImageButton)findViewById(R.id.btnReset)).setImageResource(R.drawable.btn_reset_disable);
		recorder = new MediaRecorder();

		recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
		//recorder.setOutputFormat(output_formats[currentFormat]);
		recorder.setOutputFormat(output_formats[0]);
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
		((ImageButton)findViewById(R.id.btnStart)).setImageResource(R.drawable.btn_recorder_enable);
		((ImageButton)findViewById(R.id.btnStop)).setImageResource(R.drawable.btn_media_stop_disable);
		((ImageButton)findViewById(R.id.btnReset)).setImageResource(R.drawable.btn_reset_enable);
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
		((ImageButton)findViewById(R.id.btnStart)).setImageResource(R.drawable.btn_recorder_enable);
		((ImageButton)findViewById(R.id.btnStop)).setImageResource(R.drawable.btn_media_stop_disable);
		((ImageButton)findViewById(R.id.btnReset)).setImageResource(R.drawable.btn_reset_disable);
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
			/*case R.id.btnFormat:{
				displayFormatDialog();                                        
				break;
			}*/
			}
		}
	}; 


	

	// AsyncTask encapsulating the reverse-geocoding API.  Since the geocoder API is blocked,
	// we do not want to invoke it from the UI thread.
	/*private class ReverseGeocodingTask extends AsyncTask<Location, Void, Void> {
	    Context mContext;

	    public ReverseGeocodingTask(Context context) {
	        super();
	        mContext = context;
	    }

	    @Override
	    protected Void doInBackground(Location... params) {
	        Geocoder geocoder = new Geocoder(mContext, Locale.getDefault());

	        Location loc = params[0];
	        List<Address> addresses = null;
	        try {
	            // Call the synchronous getFromLocation() method by passing in the lat/long values.
	            addresses = geocoder.getFromLocation(loc.getLatitude(), loc.getLongitude(), 1);
	        } catch (IOException e) {
	            e.printStackTrace();
	            // Update UI field with the exception.
	            //Message.obtain(mHandler, UPDATE_ADDRESS, e.toString()).sendToTarget();
	        }
	        if (addresses != null && addresses.size() > 0) {
	            Address address = addresses.get(0);
	            // Format the first line of address (if available), city, and country name.
	            String addressText = String.format("%s, %s, %s",
	                    address.getMaxAddressLineIndex() > 0 ? address.getAddressLine(0) : "",
	                    address.getLocality(),
	                    address.getCountryName());
	            // Update the UI via a message handler.
	            //Message.obtain(mHandler, UPDATE_ADDRESS, addressText).sendToTarget();
	            Toast.makeText(mContext, address.getLocality(),Toast.LENGTH_SHORT );
	        }
	        return null;
	    }
	}*/


	private class MyLocationListener implements LocationListener {
		     public void onLocationChanged(Location location) {
		
		            String message = String.format(
		
		                    "New Location \n Longitude: %1$s \n Latitude: %2$s",
		
		                    location.getLongitude(), location.getLatitude()
		
		            );
		
		           // Toast.makeText(FarmerNewRequestActivity.this, message, Toast.LENGTH_LONG).show();
		
		        }
		
		 
		
		        public void onStatusChanged(String s, int i, Bundle b) {
		
		           // Toast.makeText(FarmerNewRequestActivity.this, "Provider status changed",Toast.LENGTH_SHORT).show();
		
		        }
		
		 
		
		        public void onProviderDisabled(String s) {
		
		           // Toast.makeText(FarmerNewRequestActivity.this, "Provider disabled by the user. GPS turned off", Toast.LENGTH_SHORT).show();
		
		        }
		
		 
		
		        public void onProviderEnabled(String s) {
		
		           // Toast.makeText(FarmerNewRequestActivity.this, "Provider enabled by the user. GPS turned on",  Toast.LENGTH_SHORT).show();
		
		        }
		
		 
		
		    }

	
	
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