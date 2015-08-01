package com.mpss.weed.id.farmer;


import com.mpss.weed.id.R;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
 
public class GridViewActivity extends Activity {
   @Override
   public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.fhome_g);
    GridView g = (GridView) findViewById(R.id.myGrid);
    g.setAdapter(new ImageAdapter(this));
    g.setOnItemClickListener(new OnItemClickListener() {
           public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
          Toast.makeText(GridViewActivity.this, "" + position, Toast.LENGTH_SHORT).show();
           }
    });
   }
   public class ImageAdapter extends BaseAdapter {
    public ImageAdapter(Context c) {
        mContext = c;
    }
    public int getCount() {
        return mThumbIds.length;
    }
    public Object getItem(int position) {
        return position;
    }
    public long getItemId(int position) {
        return position;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageButton imageView;
        if (convertView == null) {
           imageView = new ImageButton(mContext);
           imageView.setLayoutParams(new GridView.LayoutParams(45, 45));
           imageView.setAdjustViewBounds(false);
           imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
           imageView.setPadding(8, 8, 8, 8);
           
        } else {
           imageView = (ImageButton) convertView;
        }
        
        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }
    private Context mContext;
    private Integer[] mThumbIds = {
        R.drawable.icon, R.drawable.icon,
        R.drawable.icon, R.drawable.icon,
        R.drawable.icon, R.drawable.icon,
        R.drawable.icon, R.drawable.icon,
        R.drawable.icon, R.drawable.icon,
        R.drawable.icon, R.drawable.icon,
        R.drawable.icon, R.drawable.icon,
    };
    }
}