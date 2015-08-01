/***
 * Copyright (c) 2011 readyState Software Ltd
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

package com.mpss.weed.id.map.customized;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.widget.Toast;

import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;
import com.mpss.weed.id.common.RequestList;
import com.mpss.weed.id.farmer.RequestDetailsActivity;
import com.mpss.weed.id.map.BalloonItemizedOverlay;
import com.mpss.weed.id.map.BalloonOverlayView;

//import com.readystatesoftware.mapviewballoons.BalloonItemizedOverlay;
//import com.readystatesoftware.mapviewballoons.BalloonOverlayView;

public class CustomItemizedOverlay<Item extends OverlayItem> extends BalloonItemizedOverlay<CustomOverlayItem> {

	private ArrayList<CustomOverlayItem> m_overlays = new ArrayList<CustomOverlayItem>();
	private Context c;
	private ArrayList<RequestList> m_requests=new ArrayList<RequestList>();
	
	public CustomItemizedOverlay(Drawable defaultMarker, MapView mapView) {
		super(boundCenter(defaultMarker), mapView);
		c = mapView.getContext();
	}

	
	public void addRequest(RequestList req)
	{
		m_requests.add(req);
	}
	
	public void addOverlay(CustomOverlayItem overlay) {
	    m_overlays.add(overlay);
	    populate();
	}

	@Override
	protected CustomOverlayItem createItem(int i) {
		return m_overlays.get(i);
	}

	@Override
	public int size() {
		return m_overlays.size();
	}

	@Override
	protected boolean onBalloonTap(int index, CustomOverlayItem item) {
		Intent disReq=new Intent(c,RequestDetailsActivity.class);
		disReq.putExtra("request", m_requests.get(index));
		
		c.startActivity(disReq);
		//disReq.putExtra("expertID", expertID);
		//Toast.makeText(c, "onBalloonTap for overlay index " +index+"Title"+item.getTitle()+m_requests.get(index).getNotes(),
		//		Toast.LENGTH_LONG).show();
		return true;
	}

	@Override
	protected BalloonOverlayView<CustomOverlayItem> createBalloonOverlayView() {
		// use our custom balloon view with our custom overlay item type:
		return new CustomBalloonOverlayView<CustomOverlayItem>(getMapView().getContext(), getBalloonBottomOffset());
	}

}
