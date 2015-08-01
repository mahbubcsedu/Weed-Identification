package com.mpss.weed.id.common;

import java.util.ArrayList;

import com.mpss.weed.id.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class RequestListAdapter extends BaseAdapter {
	Context context;
	ArrayList<Request> requests;

	public RequestListAdapter() {
	}

	public RequestListAdapter(Context context, ArrayList<Request> requests) {
		this.context = context;
		this.requests = requests;
	}

	@Override
	public int getCount() {
		return requests.size();
	}

	@Override
	public Object getItem(int position) {
		return requests.get(position);
	}

	@Override
	public long getItemId(int id) {
		return id;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		Request r = (Request) getItem(position);

		View parentView = convertView;
		if (parentView == null) {
			LayoutInflater vi = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			parentView = vi.inflate(R.layout.request_list_item, null);
			
		}

		parentView.setBackgroundResource(R.drawable.lbg);
		
		TextView requestName = (TextView) parentView
				.findViewById(R.id.text1);
		requestName.setText("Request No." + r.identificationId);
		//requestName.set
		//requestName.setBackgroundDrawable(R.drawable.lbg);
		//requestName.setBackgroundResource(R.drawable.lbg);

		ImageView colorimage=(ImageView) parentView
				.findViewById(R.id.colortype);
		ImageView image = (ImageView) parentView
				.findViewById(R.id.icon);

		image.setBackgroundResource(R.drawable.arrowr);
		if (r.newId) {
			//image.setBackgroundResource(R.drawable.marked_checkbox);
			colorimage.setBackgroundResource(R.drawable.greent);
		} else {
			//image.setBackgroundResource(R.drawable.empty_checkbox);
			colorimage.setBackgroundResource(R.drawable.redt);
		}

		return parentView;
	}
}