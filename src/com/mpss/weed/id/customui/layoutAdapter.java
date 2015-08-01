package com.mpss.weed.id.customui;

import android.widget.BaseAdapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
 
public class layoutAdapter extends BaseAdapter {
    private Context mContext;
    private int gridsize;
    private float mvidth,mheight;
 
    // Keep all Images in array
    public String[] mThumbIds = {
            "Box 1","Box 1","Box 1","Box 1","Box 1","Box 1","Box 1","Box 1","Box 1","Box 1","Box 1","Box 1","Box 1","Box 1","Box 1","Box 1"
    };
 
    // Constructor
    public layoutAdapter(Context c,float mwidth,float mheight){
        mContext = c;
        this.mvidth=mwidth;
        this.mheight=mheight;
    }
 
    @Override
    public int getCount() {
        return mThumbIds.length;
    }
 
    @Override
    public Object getItem(int position) {
        return mThumbIds[position];
    }
 
    @Override
    public long getItemId(int position) {
        return 0;
    }
 
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textview = new TextView(mContext);
        textview.setText(mThumbIds[position]);
        //imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        textview.setLayoutParams(new GridView.LayoutParams(15, 15));
        return textview;
    }
 
}
