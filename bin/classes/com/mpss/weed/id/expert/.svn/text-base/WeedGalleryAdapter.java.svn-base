package com.mpss.weed.id.expert;

import java.util.ArrayList;

import com.mpss.weed.id.R;
import com.mpss.weed.id.common.Weed;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class WeedGalleryAdapter extends BaseAdapter {
	private ArrayList<Weed> weeds;
	private Activity activity;

	public WeedGalleryAdapter(Activity a, int textViewResourceId, ArrayList<Weed> weeds) {
		this.weeds = weeds;
		activity = a;
	}
	
	@Override
	public int getCount() {
		return weeds.size();
	}

	@Override
	public Weed getItem(int position) {
		return weeds.get(position);
	}

	@Override
	public long getItemId(int id) {
		return id;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		if (v == null) {		
			LayoutInflater vi = 
				(LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.gallery_item, null);
		}
		
		TextView text = (TextView) v.findViewById(R.id.gallery_text);
		text.setText(weeds.get(position).getCommonName());
		
		ImageView image = (ImageView) v.findViewById(R.id.gallery_image);
		image.setImageBitmap(weeds.get(position).getImage());
		
		return v;
	}
}