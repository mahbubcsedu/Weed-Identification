package com.mpss.weed.id.utils;


import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;



public class GetCurrentGPSLocation {


	//private EditText editTextShowLocation;
	//private Button buttonGetLocation;
	//private ProgressBar progress;
	private LocationManager locManager;
	private LocationListener locListener = new MyLocationListener();
	Location currLoc;
	private boolean gps_enabled = false;
	private boolean network_enabled = false;
	Context mContext;
	private String currentLoc;

	
	public GetCurrentGPSLocation(Context context)
	{
		mContext=context;
		currentLoc=null;
		locManager = (LocationManager)mContext.getSystemService(Context.LOCATION_SERVICE);	
		SearchginLocation();
	}
	
	
	public Location getLocation()
	{			
		return currLoc;
	 	//return currentLoc;	  
	}
	/** Called when the activity is first created. */

	/* @Override

	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		editTextShowLocation = (EditText) findViewById(R.id.editTextShowLocation);
		progress = (ProgressBar) findViewById(R.id.progressBar1);
		progress.setVisibility(View.GONE);
		buttonGetLocation = (Button) findViewById(R.id.buttonGetLocation);
		buttonGetLocation.setOnClickListener(this);
		locManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
		
	}
*/


	public void SearchginLocation() 
	{
		//progress.setVisibility(View.VISIBLE);
		// exceptions will be thrown if provider is not permitted.
		try {
			gps_enabled = locManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
		} catch (Exception ex) {
		}

		try {
			network_enabled = locManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
		} catch (Exception ex) {
		}



		// don't start listeners if no provider is enabled

		if (!gps_enabled &!network_enabled) {
			
			System.out.println("Sorry, location is not determined. Please enable location providers");
			
		}



		if (gps_enabled) {
			locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locListener);
		}
		if (network_enabled) {
			locManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locListener);
		}
	}



	class MyLocationListener implements LocationListener {

		@Override

		public void onLocationChanged(Location location) {
			if (location != null) {
				// This needs to stop getting the location data and save the battery power.
				locManager.removeUpdates(locListener);
				String longitude = "" +  location.getLongitude();
				String latitude = "" + location.getLatitude();
				String altitiude = "Altitiude: " + location.getAltitude();
				String accuracy = "Accuracy: " + location.getAccuracy();
				String time = "Time: " + location.getTime();
				//currentLoc=longitude + "," + latitude + "," + altitiude + "," + accuracy + "," + time;
				currentLoc=longitude + "," + latitude;
				currLoc=location;
				//progress.setVisibility(View.GONE);
			}
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
}