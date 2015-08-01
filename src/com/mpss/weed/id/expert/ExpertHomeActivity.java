package com.mpss.weed.id.expert;

import com.mpss.weed.id.R;
import com.mpss.weed.id.common.HomeImageAdapter;
import com.mpss.weed.id.common.LoginActivity;
import com.mpss.weed.id.common.ProfileActivity;
import com.mpss.weed.id.common.SessionApp;
import com.mpss.weed.id.common.WeedDatabaseActivity;
import com.mpss.weed.id.farmer.FarmerNewRequestActivity;
import com.mpss.weed.id.farmer.RequestListMapViewActivity;
import com.mpss.weed.id.farmer.RequestListViewActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.AdapterView.OnItemClickListener;

public class ExpertHomeActivity extends Activity {
	Context context = this;
	Button weedDatabase, diagnose, profile,diagnoselist;
    String expertID,userID;
    HomeImageAdapter fimageadapter;
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
		setContentView(R.layout.homegridview);
		
		 GridView gridview = (GridView) findViewById(R.id.gridview);
	        String []mText1 = {
	        		this.getString(R.string.weeidDb), this.getString(R.string.diagnoseMap),
	        		this.getString(R.string.diagnoselist), 
	        		this.getString(R.string.profile), this.getString(R.string.logout),
	                   
	           };
	        Integer []mThumble = {
	                R.drawable.ic_dbsave, R.drawable.ic_map, 
	                R.drawable.ic_listview,  
	                R.drawable.ic_profile, R.drawable.ic_logout, 
	                
	        };
		
	        fimageadapter=new HomeImageAdapter(this,mText1,mThumble);
	        gridview.setAdapter(fimageadapter);
		
	        gridview.setOnItemClickListener(new OnItemClickListener() {
	        	@Override
	            public void onItemClick(AdapterView<?> parent, View view,
	                    int position, long id) {
	            	
	            	if(position==0){
	            		startActivity(new Intent(context,
	    						WeedDatabaseActivity.class));
	            	}
	            	else if(position==1){
	            		Intent intent=new Intent(context, AvailabeRequestListMapActivity.class);
	    				//intent.putExtra("expertID", expertID);
	    				startActivity(intent);
	            	}
	            		else if(position==2){
	            			Intent intent=new Intent(context, AvailableRequestListViewActivity.class);
	        				//intent.putExtra("expertID", expertID);
	        				startActivity(intent);
	            		}
	            			
	            				else if(position==3){
	            					Intent intent=new Intent(context, ProfileActivity.class);
	            					//Intent intent=new Intent(context, RequestListMapViewCustom.class);
	            					//intent.putExtra("userID", userID);
	            					startActivity(intent );
	            				}
	            					else if(position==4)
	            					{
	            						SessionApp.setLoggedUserID(null);
	            						startActivity(new Intent(context, LoginActivity.class));
	            					}
	            	//TextView itemText=(TextView)findViewById(view.getId());
	                //Toast.makeText(FarmerHomeActivity.this, "" + itemText.getText(), Toast.LENGTH_SHORT).show();
	            }

				
	        });
		
		
		/*Button logout = (Button) findViewById(R.id.sign_out);
		profile = (Button) findViewById(R.id.profile);
		diagnoselist = (Button) findViewById(R.id.diagnoselist);
		
		
	//	expertID=getIntent().getStringExtra("expertID");
		userID=SessionApp.getUserLoggedUserID();
		logout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SessionApp.setLoggedUserID(null);
				startActivity(new Intent(context, LoginActivity.class));				
			}
		});
        profile.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(context, ProfileActivity.class);
				intent.putExtra("userID", expertID);
				startActivity(intent );
			}
		});
		weedDatabase = (Button) findViewById(R.id.weed_database);
		weedDatabase.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(context,
						WeedDatabaseActivity.class));
			}
		});
		
		diagnose = (Button) findViewById(R.id.diagnose);
		diagnose.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(context, AvailabeRequestListMapView.class);
				//intent.putExtra("expertID", expertID);
				startActivity(intent);
			}
		});
diagnoselist.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(context, AvailableRequestListViewActivity.class);
				//intent.putExtra("expertID", expertID);
				startActivity(intent);
			}
		});*/
	}
	
	@Override
	public void onBackPressed() {
	   return;
	}
}
