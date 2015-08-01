package com.mpss.weed.id.common;

import java.util.ArrayList;

import com.mpss.weed.id.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;

public class ResponseListAdapter extends BaseAdapter {
	Context context;
	ArrayList<Response> response;

	public ResponseListAdapter() {
	}

	public ResponseListAdapter(Context context, ArrayList<Response> response) {
		this.context = context;
		this.response = response;
	}

	@Override
	public int getCount() {
		return response.size();
	}

	@Override
	public Object getItem(int position) {
		return response.get(position);
	}

	@Override
	public long getItemId(int id) {
		return id;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		Response r = (Response) getItem(position);
		
		View parentView = convertView;
		
		if (parentView == null) {
			LayoutInflater vi = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			parentView = vi.inflate(R.layout.request_list_item, null);
		}

		
		TextView requestName = (TextView) parentView
				.findViewById(R.id.text1);
		//requestName.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		
		
		
		//parentView.setBackgroundResource(R.drawable.lbg);
		
		ImageView colorimage=(ImageView) parentView
				.findViewById(R.id.colortype);
		ImageView image = (ImageView) parentView
				.findViewById(R.id.icon);
		requestName.setText("Response No." + r.response.toString());
		image.setBackgroundResource(R.drawable.ic_list_more);
		/*if (r.newId) {
			//image.setBackgroundResource(R.drawable.marked_checkbox);
			colorimage.setBackgroundResource(R.drawable.greent);
		} else {
			//image.setBackgroundResource(R.drawable.empty_checkbox);
			colorimage.setBackgroundResource(R.drawable.redt);
		}*/
		/*ImageView image = (ImageView) parentView
				.findViewById(android.R.id.icon);

		if (r.newId) {
			image.setBackgroundResource(R.drawable.marked_checkbox);
		} else {
			image.setBackgroundResource(R.drawable.empty_checkbox);
		}*/

		return parentView;
	}
}