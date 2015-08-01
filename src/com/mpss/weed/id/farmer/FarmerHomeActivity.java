package com.mpss.weed.id.farmer;

import com.mpss.weed.id.R;
import com.mpss.weed.id.common.HomeImageAdapter;
import com.mpss.weed.id.common.LoginActivity;
import com.mpss.weed.id.common.ProfileActivity;
import com.mpss.weed.id.common.RequestList;
import com.mpss.weed.id.common.SessionApp;
import com.mpss.weed.id.common.WeedDatabaseActivity;
import com.mpss.weed.id.expert.ExpertDiagnoseRequestActivity;
import com.mpss.weed.id.utils.InstructionAcitivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;

public class FarmerHomeActivity extends Activity {
	private static final String TAG = "FarmerHomeActivity";
	Context context = this;
	Button weedDatabase, requests, profile,response,requestlist;
	String userID="";
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
        		this.getString(R.string.weeidDb), this.getString(R.string.addRequest),
        		this.getString(R.string.requestlist), this.getString(R.string.requestmap),
        		this.getString(R.string.profile), this.getString(R.string.logout),
                   
           };
        Integer []mThumble = {
                R.drawable.ic_dbsave, R.drawable.ic_addreqeust, 
                R.drawable.ic_listview, R.drawable.ic_map, 
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
            		
            	 String key=context.getString(R.string.instructionKeyfAdd);			
           		 String instructionstring=context.getString(R.string.instruction_frequest);           		 
           		 final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
           		 
           		 Intent intent;
                 boolean hasBeenShown = prefs.getBoolean(key, false);
                
                  if(hasBeenShown == false){
                  	intent = new Intent(context,InstructionAcitivity.class);
                  	intent.putExtra("instruction", instructionstring);
           			intent.putExtra("key", key);
           			intent.putExtra("instructionType", "FReq");
           			//intent.putExtra("request", (RequestList) l.getAdapter().getItem(position));
              		
              		startActivity(intent);
           		//CustomizeDialog customizeDialog = new CustomizeDialog(this,prefs,instructionstring,key);
           		//customizeDialog.show();
                  }
                  else{
                	intent=new Intent(context, FarmerNewRequestActivity.class);
      				//intent.putExtra("userID", userID);
      				startActivity(intent);
           		
                  }
            		
            	}
            		else if(position==2){
            			Intent intent=new Intent(context, RequestListViewActivity.class);
        				//intent.putExtra("userID", userID);
        				startActivity(intent );
            		}
            			else if(position==3){
            				Intent intent=new Intent(context, RequestListMapViewActivity.class);
            				//intent.putExtra("userID", userID);
            				startActivity(intent );
            			}
            				else if(position==4){
            					Intent intent=new Intent(context, ProfileActivity.class);
            					//Intent intent=new Intent(context, RequestListMapViewCustom.class);
            					//intent.putExtra("userID", userID);
            					startActivity(intent );
            				}
            					else if(position==5)
            					{
            						SessionApp.setLoggedUserID(null);
            						startActivity(new Intent(context, LoginActivity.class));
            					}
            	//TextView itemText=(TextView)findViewById(view.getId());
                //Toast.makeText(FarmerHomeActivity.this, "" + itemText.getText(), Toast.LENGTH_SHORT).show();
            }

			
        });
		
		/*//requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);//for specific activity, this line together with the following two lines required
		setContentView(R.layout.farmer_home_rel);
		//getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.weedidtitle);
		
		Button logout = (Button) findViewById(R.id.sign_out);
		//Bundle extras = getIntent().getExtras();
		//if(extras !=null) {
		   //userID= extras.getString("farmerID");
		   // Log.i("TAG",userID);
	    // }	
		
		
		profile = (Button) findViewById(R.id.profile);
		response=(Button) findViewById(R.id.create_result);
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
				//this is blocked for temporary basis and mus restore
				Intent intent=new Intent(context, ProfileActivity.class);
				//Intent intent=new Intent(context, RequestListMapViewCustom.class);
				//intent.putExtra("userID", userID);
				startActivity(intent );
			}
		});
       
       response.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(context, RequestListMapViewCustom.class);
				//intent.putExtra("userID", userID);
				startActivity(intent );
				//for temporarily removed , must be restored later when there will be button for map
				Intent intent=new Intent(context, FarmerIdetificationActivity.class);
				intent.putExtra("userID", userID);
				startActivity(intent );
			}
		});
       
       requestlist = (Button) findViewById(R.id.requestlist);
       requestlist.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(context, RequestListViewCustomActivity.class);
				//intent.putExtra("userID", userID);
				startActivity(intent );
				//for temporarily removed , must be restored later when there will be button for map
				Intent intent=new Intent(context, FarmerIdetificationActivity.class);
				intent.putExtra("userID", userID);
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
		
		requests = (Button) findViewById(R.id.create_request);
		requests.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(context, FarmerNewRequestActivity.class);
				//intent.putExtra("userID", userID);
				startActivity(intent);
			}
		});*/
		
	}
	
	@Override
	public void onBackPressed() {
	   return;
	}
	
	
	
}
