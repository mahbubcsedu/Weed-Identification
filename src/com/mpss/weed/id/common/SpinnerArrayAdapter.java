package com.mpss.weed.id.common;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class SpinnerArrayAdapter extends ArrayAdapter<String>{	   

	        public SpinnerArrayAdapter(Context context, int textViewResourceId) {
	            super(context, textViewResourceId); 
	        }

	        @Override
	        public View getView(int position, View convertView, ViewGroup parent) {
	            View view = super.getView(position, convertView, parent);
	            int color = getColorFromName(getItem(position));
	            view.setBackgroundColor(color);
	            return view;
	        }   
	    }

