/***
 * Copyright (c) 2010 readyState Software Ltd
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may obtain
 * a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

package com.mpss.weed.id.map;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;
import com.mpss.weed.id.R;
import com.mpss.weed.id.common.LoginActivity;
//import com.mpss.weed.id.common.Request;
import com.mpss.weed.id.common.RequestList;
import com.mpss.weed.id.common.SessionApp;
//import com.mpss.weed.id.common.RequestListAdapter;
//import com.mpss.weed.id.expert.ExpertDiagnoseActivity.GetRequestListTask;
import com.mpss.weed.id.utils.GetCurrentGPSLocation;

public class RequestListMapView extends MapActivity {

	MapView mapView;
	List<Overlay> mapOverlays;
	Drawable drawable;
	Drawable drawable2;
	//SimpleItemizedOverlay itemizedOverlay;
	SimpleItemizedOverlay itemizedOverlay2,itemizedOverlay1;
	
	//------------
	Context context = this;
	ArrayList<RequestList> requests = new ArrayList<RequestList>();
	//RequestListAdapter adapter;
	ProgressDialog progress;
	String farmerID,userID;
	GeoPoint point;
	GetCurrentGPSLocation currLoc;
	Location CurrentLocation;
	double latitude=36.2700007232,longitude=-92.27089691162;
	private DefaultHttpClient mHttpClient;
	//--------------added
	@Override
    public void onCreate(Bundle savedInstanceState) {
		
        super.onCreate(savedInstanceState);
        setContentView(R.layout.farmerreq_mapview);
        
        userID=SessionApp.getUserLoggedUserID();
		if(userID==null)
		{
			startActivity(new Intent(context, LoginActivity.class));
		}
        
        //farmerID=getIntent().getStringExtra("userID");        
		//adapter = new RequestListAdapter(context, requests);
		//setListAdapter(adapter);		
		progress = new ProgressDialog(context);
		progress.setMessage("Loading requests...");
		//progress.setCancelable(false);
		//progress.show();
		currLoc=new GetCurrentGPSLocation(this.context);
		//ListView lv=getListView();
        //lv.setTextFilterEnabled(true);
        
		//new GetRequestListTask().execute((Void[])null);
        
		RequestDownload();//donwload the requests ........
        mapView = (MapView) findViewById(R.id.req_map);
		mapView.setBuiltInZoomControls(true);
		
		mapOverlays = mapView.getOverlays();
		
		// first overlay
		drawable = getResources().getDrawable(R.drawable.marker);
		drawable2 = getResources().getDrawable(R.drawable.marker2);
		//itemizedOverlay = new SimpleItemizedOverlay(drawable, mapView);
		itemizedOverlay1 = new SimpleItemizedOverlay(drawable, mapView);
		itemizedOverlay2 = new SimpleItemizedOverlay(drawable2, mapView);
		//finding point from the database and if null, then use current location
		CurrentLocation=currLoc.getLocation();
		if(CurrentLocation!=null){
		longitude=CurrentLocation.getLongitude();
		latitude=CurrentLocation.getLatitude();
		}
		
		for(int reqIndex=0;reqIndex<requests.size();reqIndex++)
		{					
		if(requests.get(reqIndex).isNewId())
		{
		/*point = new GeoPoint((int)(Double.parseDouble(requests.get(reqIndex).getLatitude())*1E6),(int)(Double.parseDouble(requests.get(reqIndex).getLongitude())*1E6));
		OverlayItem overlayItem = new OverlayItem(point, "Request#"+(reqIndex+1), 
				"Requested Date:"+requests.get(reqIndex).getRequestSentDate());
		itemizedOverlay.addOverlay(overlayItem);
		itemizedOverlay.addRequest(requests.get(reqIndex));
		*/
			point = new GeoPoint((int)(Double.parseDouble(requests.get(reqIndex).getLatitude())*1E6),(int)(Double.parseDouble(requests.get(reqIndex).getLongitude())*1E6));
			OverlayItem overlayItem = new OverlayItem(point, "Request#"+(reqIndex+1), 
					"Requested Date:"+requests.get(reqIndex).getRequestSentDate());
			itemizedOverlay1.addOverlay(overlayItem);
			itemizedOverlay1.addRequest(requests.get(reqIndex));
		}
		else
		{
			point = new GeoPoint((int)(Double.parseDouble(requests.get(reqIndex).getLatitude())*1E6),(int)(Double.parseDouble(requests.get(reqIndex).getLongitude())*1E6));
			OverlayItem overlayItem = new OverlayItem(point, "Request#"+(reqIndex+1), 
					"Requested Date:"+requests.get(reqIndex).getRequestSentDate());
			itemizedOverlay2.addOverlay(overlayItem);
			itemizedOverlay2.addRequest(requests.get(reqIndex));
		}
		}
		/*GeoPoint point2 = new GeoPoint((int)(36.27090072632*1E6),(int)(-92.27089691162*1E6));
		OverlayItem overlayItem2 = new OverlayItem(point2, "GoldenEye (1995)", 
				"(Interiors Russian defence ministry council chambers in St Petersburg)");		
		itemizedOverlay.addOverlay(overlayItem2);
		*/
		if(itemizedOverlay1!=null)
		mapOverlays.add(itemizedOverlay1);
		
		if(itemizedOverlay2!=null)
			mapOverlays.add(itemizedOverlay2);
		
		/*
		// second overlay
		drawable2 = getResources().getDrawable(R.drawable.marker2);
		itemizedOverlay2 = new SimpleItemizedOverlay(drawable2, mapView);
		
		GeoPoint point3 = new GeoPoint((int)(51.513329*1E6),(int)(-0.08896*1E6));
		OverlayItem overlayItem3 = new OverlayItem(point3, "Sliding Doors (1998)", null);
		itemizedOverlay2.addOverlay(overlayItem3);
		
		GeoPoint point4 = new GeoPoint((int)(51.51738*1E6),(int)(-0.08186*1E6));
		OverlayItem overlayItem4 = new OverlayItem(point4, "Mission: Impossible (1996)", 
				"(Ethan & Jim cafe meeting)");
		itemizedOverlay2.addOverlay(overlayItem4);
		
		mapOverlays.add(itemizedOverlay2);
		*/
		if (savedInstanceState == null) {
			
			final MapController mc = mapView.getController();
			mc.animateTo(point);
			mc.setZoom(16);
			
		} else {
			
			// example restoring focused state of overlays
			int focused;
			/*for(int reqIndex=0;reqIndex<requests.size();reqIndex++)
			{
				focused = savedInstanceState.getInt(requests.get(reqIndex).getIdentificationId(), -1);
				if (focused >= 0) {
					itemizedOverlay.setFocus(itemizedOverlay.getItem(focused));
				}
				
				
				//if (itemizedOverlay.getFocus() != null) outState.putInt(requests.get(reqIndex).getIdentificationId(), itemizedOverlay.getLastFocusedIndex());
			}
			*/
			focused = savedInstanceState.getInt("focused_1", -1);
			if (focused >= 0) 
			{
				itemizedOverlay1.setFocus(itemizedOverlay1.getItem(focused));
			}
			focused = savedInstanceState.getInt("focused_2", -1);
			if (focused >= 0) 
			{
				itemizedOverlay2.setFocus(itemizedOverlay2.getItem(focused));
			}
			
		}
		
    }
	
	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		
		// example saving focused state of overlays
		/*for(int reqIndex=0;reqIndex<requests.size();reqIndex++)
		{
			if (itemizedOverlay.getFocus() != null) outState.putInt(requests.get(reqIndex).getIdentificationId(), itemizedOverlay.getLastFocusedIndex());
		}
		*/
		if (itemizedOverlay1.getFocus() != null) outState.putInt("focused_1", itemizedOverlay1.getLastFocusedIndex());
		if (itemizedOverlay2.getFocus() != null) outState.putInt("focused_2", itemizedOverlay2.getLastFocusedIndex());
		super.onSaveInstanceState(outState);
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, 0, 1, "Remove Overlay");
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == 0) {
			
			// example hiding balloon before removing overlay
			if (itemizedOverlay1.getFocus() != null) {
				itemizedOverlay1.hideBalloon();
			}
			mapOverlays.remove(itemizedOverlay1);
			mapView.invalidate();
			
		}
		return true;
	}
	
	private Boolean RequestDownload()
	{
	  HttpParams params = new BasicHttpParams();
	   params.setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
	    mHttpClient = new DefaultHttpClient(params);
	    String str = "***";
	    String requestlistmap_url=getString(R.string.requestlistmap_url);
		//String temp = "http://mpss.csce.uark.edu/smsdb/weed_app/weedapp/farmer_requestlist.php";
		//HttpPost httppost = new HttpPost(requestlistmap_url);
        MultipartEntity multipartEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
        try {
			multipartEntity.addPart("userID", new StringBody(SessionApp.getUserLoggedUserID()));
		
       
		
		HttpClient hc = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(requestlistmap_url);
	    httppost.setEntity(multipartEntity);
         //mHttpClient.execute(httppost, new PhotoUploadResponseHandler());
	     HttpResponse response=mHttpClient.execute(httppost);
	    // String the_string_response = convertResponseToString(response);
		
		HttpResponse rp = hc.execute(httppost);
			if (rp.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
				str = EntityUtils.toString(response.getEntity());
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		JSONObject json;
		try {
			json = new JSONObject(str);
			if (json.getString("error").equals("")) {
				JSONArray array = json.getJSONArray("requests");
				for (int i = 0; i < array.length(); i++) {
					requests.add(new RequestList(array.getJSONObject(i)));
					// new DownloadImageTask().execute(weeds.get(i));
				}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			Log.v("YO", e.toString()); 
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}
	
	/*private class GetRequestListTask extends AsyncTask<Void, Void, Boolean> {
		@Override
		protected Boolean doInBackground(Void... voids) {
			String temp = "http://mpss.csce.uark.edu/smsdb/weed_app/weedapp/farmer_requestlist.php";
			HttpClient hc = new DefaultHttpClient();
			HttpPost post = new HttpPost(temp);
			
			String str = "***";
			try {
				HttpResponse rp = hc.execute(post);
				if (rp.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
					str = EntityUtils.toString(rp.getEntity());
			} catch (IOException e) 
			{
				e.printStackTrace();
			}
			
			JSONObject json;
			try {
				json = new JSONObject(str);
				if (json.getString("error").equals("")) {
					JSONArray array = json.getJSONArray("requests");
					for (int i = 0; i < array.length(); i++) {
						requests.add(new RequestList(array.getJSONObject(i)));
						// new DownloadImageTask().execute(weeds.get(i));
					}
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				Log.v("YO", e.toString()); 
				return Boolean.FALSE;
			}
			return Boolean.TRUE;
		}

		@Override
		protected void onPostExecute(Boolean result) {
			//adapter.notifyDataSetChanged();
			progress.dismiss();
		}
	}*/
	
}
