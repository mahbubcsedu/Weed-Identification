package com.mpss.weed.id.farmer;

import com.mpss.weed.id.R;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
 
public class FHomeGrid extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homegridview);
 
        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));
 
        gridview.setOnItemClickListener(new OnItemClickListener() {
            
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
                Toast.makeText(FHomeGrid.this, "" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }
     
     
    private class ImageAdapter extends BaseAdapter {
        private Context mContext;
        private LayoutInflater mInflater;
         
        public ImageAdapter(Context c) {
        	
            mContext = c;
            String []mText1 = {
            		mContext.getString(R.string.weeidDb), mContext.getString(R.string.addRequest),
            		mContext.getString(R.string.requestlist), mContext.getString(R.string.requestmap),
            		mContext.getString(R.string.profile), mContext.getString(R.string.logout),
                       
               };
            mText=mText1;
            mInflater = LayoutInflater.from(c);
        }
 
        public int getCount() {
            return mThumbIds.length;
        }
 
        public Object getItem(int position) {
            return null;
        }
 
        public long getItemId(int position) {
            return 0;
        }
 
        // create a new ImageView for each item referenced by the Adapter
        public View getView(int position, View convertView, ViewGroup parent) {
             
            /*//If We want to show only images
            //Try commented code
            //Ref : http://developer.android.com/resources/tutorials/views/hello-gridview.html
            ImageView imageView;
            if (convertView == null) {  // if it's not recycled, initialize some attributes
                imageView = new ImageView(mContext);
                imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setPadding(8, 8, 8, 8);
            } else {
                imageView = (ImageView) convertView;
            }
 
            imageView.setImageResource(mThumbIds[position]);
            return imageView;*/
                         
            ViewHolder holder;
            if (convertView == null) {             
               convertView = mInflater.inflate(R.layout.grid_row_view, null);
               holder = new ViewHolder();
               holder.ImgThumb = (ImageView) convertView.findViewById(R.id.imgThumb);
               holder.ImhText  = (TextView) convertView.findViewById(R.id.imgText);
                
               convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
             
            holder.ImgThumb.setImageResource(mThumbIds[position]);
            holder.ImhText.setText(mText[position]);
                         
            return convertView;        
        } 
 
        public class ViewHolder {
           ImageView ImgThumb;
           TextView ImhText;
        }
 
        // references to our images
        private Integer[] mThumbIds = {
                R.drawable.ic_dbsave, R.drawable.ic_addreqeust, 
                R.drawable.ic_listview, R.drawable.ic_map, 
                R.drawable.ic_profile, R.drawable.ic_logout, 
                
        };
        
        private String[] mText; 
        		 
                
      
    }
}