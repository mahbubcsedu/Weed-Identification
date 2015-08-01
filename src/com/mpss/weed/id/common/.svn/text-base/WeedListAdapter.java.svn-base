 package com.mpss.weed.id.common;

import java.util.ArrayList;

import com.mpss.weed.id.R;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class WeedListAdapter extends BaseAdapter {
	Context context;
	ArrayList<Weed> weeds;

	public WeedListAdapter(Context context, ArrayList<Weed> weeds) {
		this.context = context;
		this.weeds = weeds;
	}

	@Override
	public int getCount() {
		return weeds.size();
	}

	@Override
	public Object getItem(int position) {
		return weeds.get(position);
	}

	@Override
	public long getItemId(int id) {
		return id;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
	
		Weed w = (Weed) getItem(position);

		View parentView = convertView;
		if (parentView == null) {
			LayoutInflater vi = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			parentView = vi.inflate(R.layout.weed_list_item, null);
		}
	
		ImageView weedImage = (ImageView) parentView.findViewById(R.id.icon);
		if (w.getImage() != null) {
			weedImage.setImageBitmap(w.getImage());
		} else {
			weedImage.setImageResource(R.drawable.ic_launcher);
		}
		
		TextView weedName = (TextView) parentView.findViewById(R.id.text1);
		//weedName.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
		weedName.setText(w.getCommonName());
		//ImageView arrow = (ImageView) parentView.findViewById(R.id.wdb_listarrow);
		//arrow.setImageResource(R.drawable.ic_list_more);
		return parentView;
	}
}
