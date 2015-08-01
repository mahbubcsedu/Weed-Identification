package com.mpss.weed.id.common;

import com.mpss.weed.id.R;
import com.mpss.weed.id.zoom.ImageZoomActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class WeedInformationActivity extends Activity {
	Context context = this;
	Weed weed;
	TextView commonName, latinName, weedType, lifeCycle, season, site,
			professionalControl, homeownerControl;
	ImageView image,view;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.weed_information);

		weed = getIntent().getParcelableExtra("weed");

		view = (ImageView) findViewById(R.id.icon);
		//image = (ImageView) findViewById(android.R.id.icon);
		view.setImageBitmap(weed.getImage());
		float imagewidth=weed.getImage().getWidth();
		float imagehight=weed.getImage().getHeight();
		float scaleration=imagehight/imagewidth;
		
		DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int scrwidth=dm.widthPixels;
        int scrhight=dm.heightPixels;
        
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) view.getLayoutParams(); 
        params.width = scrwidth;
        params.height = (int) (scrwidth*scaleration);
        view.setLayoutParams(params);
		
		commonName = (TextView) findViewById(R.id.common_name);
		latinName = (TextView) findViewById(R.id.latin_name);
		weedType = (TextView) findViewById(R.id.weed_type);
		lifeCycle = (TextView) findViewById(R.id.life_cycle);
		season = (TextView) findViewById(R.id.season);
		site = (TextView) findViewById(R.id.site);
		professionalControl = (TextView) findViewById(R.id.professional_control);
		homeownerControl = (TextView) findViewById(R.id.homeowner_control);
		
		
		commonName.setText(weed.getCommonName());
		latinName.setText(weed.getLatinName());
		weedType.setText(weed.getWeedType().toString());
		lifeCycle.setText(weed.getLifeCycle().toString());
		season.setText(weed.getSeason().toString());
		site.setText(weed.getSite());
		professionalControl.setText(weed.getProfessionalControl());
		homeownerControl.setText(weed.getHomeownerControl());
		
		view.setOnClickListener(new OnClickListener() {
           
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ZoomImage();
			}
        });
	}
	
	public void ZoomImage()
	{
		Intent intent = new Intent(this, ImageZoomActivity.class);
		intent.putExtra("imageURL", weed.imageUrl);
		startActivityForResult(intent, 102);        
	}
}