package com.mpss.weed.id.test;

import com.google.android.maps.MapActivity;
import com.mpss.weed.id.R;


import android.app.Activity;
import android.os.Bundle;

public class GoogleMapViewEx1Activity extends MapActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_location);
        
    }

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
}