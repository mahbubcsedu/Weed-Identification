/*
 * Copyright (c) 2010, Sony Ericsson Mobile Communication AB. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, 
 * are permitted provided that the following conditions are met:
 *
 *    * Redistributions of source code must retain the above copyright notice, this 
 *      list of conditions and the following disclaimer.
 *    * Redistributions in binary form must reproduce the above copyright notice,
 *      this list of conditions and the following disclaimer in the documentation
 *      and/or other materials provided with the distribution.
 *    * Neither the name of the Sony Ericsson Mobile Communication AB nor the names
 *      of its contributors may be used to endorse or promote products derived from
 *      this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED 
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
 * BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.mpss.weed.id.zoom;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import com.mpss.weed.id.R;
import com.mpss.weed.id.utils.DownloadImageTask;
import com.mpss.weed.id.zoom.ImageZoomView;
import com.mpss.weed.id.zoom.SimpleZoomListener;
import com.mpss.weed.id.zoom.ZoomState;
import com.mpss.weed.id.zoom.SimpleZoomListener.ControlType;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Activity for zoom tutorial 1
 */
public class ImageZoomActivity extends Activity {

    /** Constant used as menu item id for setting zoom control type */
    private static final int MENU_ID_ZOOM = 0;

    /** Constant used as menu item id for setting pan control type */
    private static final int MENU_ID_PAN = 1;

    /** Constant used as menu item id for resetting zoom state */
    private static final int MENU_ID_RESET = 2;

    /** Image zoom view */
    private ImageZoomView mZoomView;

    /** Zoom state */
    private ZoomState mZoomState;

    /** Decoded bitmap image */
    private Bitmap mBitmap;
    Button zoom;
    
    private String image_url;

    /** On touch listener for zoom view */
    private SimpleZoomListener mZoomListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.zoomview);

        zoom=(Button)findViewById(R.id.btnZoom);
        mZoomState = new ZoomState();
        image_url=getIntent().getStringExtra("imageURL");
        //mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pushpin_graphict);
       // mBitmap = loadBitmap(image_url);

        mZoomListener = new SimpleZoomListener();
        mZoomListener.setZoomState(mZoomState);

        mZoomView = (ImageZoomView)findViewById(R.id.zoomview);
        mZoomView.setZoomState(mZoomState);
        mZoomView.setTag(image_url);
       // mZoomView.setImage(mBitmap);
        new DownloadImageTask().execute(mZoomView);
        mZoomView.setOnTouchListener(mZoomListener);

       // image_url=getIntent().getStringExtra("imageURL");
        
        resetZoomState();
        
        
        zoom.setOnClickListener(new OnClickListener(){

    		@Override
    		public void onClick(View v) {
    			try{
    			// TODO Auto-generated method stub
    			Intent intent= getIntent();
                // String msg = intent.getStringExtra("location");
                 //msg += ", Added at Third";
                 
                 intent.putExtra("zoomEnd", "zoom view finished");
                // setResult(RESULT_OK, intent);
                 setResult(Activity.RESULT_OK, intent);
                 
                 finish();
    			}catch(Exception e)
    			{
    				Log.e("error",e.getMessage());
    			}
    		}
        	
        	
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

       // mBitmap.recycle();
        mZoomView.setOnTouchListener(null);
        mZoomState.deleteObservers();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE, MENU_ID_ZOOM, 0, R.string.menu_zoom);
        menu.add(Menu.NONE, MENU_ID_PAN, 1, R.string.menu_pan);
        menu.add(Menu.NONE, MENU_ID_RESET, 2, R.string.menu_reset);
        return super.onCreateOptionsMenu(menu);
    }
    
    
    
    
    
   /* public Bitmap loadBitmap(String url)
    {
        Bitmap bm = null;
        InputStream is = null;
        BufferedInputStream bis = null;
        try 
        {
            URLConnection conn = new URL(url).openConnection();
            conn.connect();
            is = conn.getInputStream();
            bis = new BufferedInputStream(is, 8192);
            bm = BitmapFactory.decodeStream(bis);
        }
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        finally {
            if (bis != null) 
            {
                try 
                {
                    bis.close();
                }
                catch (IOException e) 
                {
                    e.printStackTrace();
                }
            }
            if (is != null) 
            {
                try 
                {
                    is.close();
                }
                catch (IOException e) 
                {
                    e.printStackTrace();
                }
            }
        }
        return bm;
    }
    */
    
    public class DownloadImageTask extends AsyncTask<ImageZoomView, Void, Bitmap> {

    	ImageZoomView imageView = null;

    	@Override
    	protected Bitmap doInBackground(ImageZoomView... imageViews) {
    		this.imageView = imageViews[0];
    		return download_Image((String) imageView.getTag());
    	}

    	@Override
    	protected void onPostExecute(Bitmap result) {
    		imageView.setImage(result);
    	}

    	private Bitmap download_Image(String url) {
    		return downloadImage(url);
    	}

    	public Bitmap downloadImage(String fileUrl) {
    		Bitmap image = null;
    		URL myFileUrl = null;
    		try {
    			myFileUrl = new URL(fileUrl);
    		} catch (MalformedURLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		try {
    			HttpURLConnection conn = (HttpURLConnection) myFileUrl
    					.openConnection();
    			conn.setDoInput(true);
    			conn.connect();
    			InputStream is = conn.getInputStream();

    			image = BitmapFactory.decodeStream(is);

    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}

    		return image;
    	}
    }
    
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MENU_ID_ZOOM:
                mZoomListener.setControlType(ControlType.ZOOM);
                break;

            case MENU_ID_PAN:
                mZoomListener.setControlType(ControlType.PAN);
                break;

            case MENU_ID_RESET:
                resetZoomState();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Reset zoom state and notify observers
     */
    private void resetZoomState() {
        mZoomState.setPanX(0.5f);
        mZoomState.setPanY(0.5f);
        mZoomState.setZoom(1f);
        mZoomState.notifyObservers();
    }

}
