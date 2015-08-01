package com.mpss.weed.id.utils;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;


public class MapOverLay extends ItemizedOverlay<OverlayItem> {

	Context mContext;
	
	public MapOverLay(Drawable defaultMarker) {  
		super(boundCenterBottom(defaultMarker));
	}
	
	public MapOverLay(Drawable defaultMarker, Context context) {  
		super(defaultMarker);  
		mContext = context;
	}
	
	

	private ArrayList<OverlayItem> mOverlays = new ArrayList<OverlayItem>();

	public void addOverlay(OverlayItem overlay) {    
		mOverlays.add(overlay);    
		populate();
	}
	
	@Override
	protected OverlayItem createItem(int i) {
		return mOverlays.get(i);
	}

	@Override
	public int size() {
		return mOverlays.size();
	}

	@Override
	protected boolean onTap(int index) {  
		OverlayItem item = mOverlays.get(index);  
		AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);  
		dialog.setTitle(item.getTitle());  
		dialog.setMessage(item.getSnippet());  
		dialog.show();  
		return true;
	}
}