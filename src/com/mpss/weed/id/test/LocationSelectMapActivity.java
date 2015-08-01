package com.mpss.weed.id.test;

import java.util.ArrayList;
import java.util.List;

//import com.example.pushpindrag.MapMarkerActivity;
//import com.example.pushpindrag.MapMarkerActivity.SitesOverlay;
//import com.example.pushpindrag.MapToGetLocationActivity;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.MapView.LayoutParams;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;
import com.google.android.maps.Projection;
import com.mpss.weed.id.R;
import com.mpss.weed.id.common.RegisterActivity;
import com.mpss.weed.id.utils.MapOverLay;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Paint.Style;
import android.graphics.drawable.Drawable;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Message;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
 
public class LocationSelectMapActivity extends MapActivity 
{    
	MapView mapView; 
    MapController mc;
    GeoPoint p;
    Button useLocation,maptype;
    String latitude;
    String longitude;
    private static final int latitudeE6 = 37985339;
    private static final int longitudeE6 = 23716735;
    private LocationManager locationManager;
    
    Geocoder geocoder;
    Location location;
    LocationListener locationListener;
    CountDownTimer locationtimer;
    private MyLocationOverlay me=null;

    
   /* class MapOverlay extends com.google.android.maps.Overlay
    {
        @Override
        public boolean draw(Canvas canvas, MapView mapView, 
                boolean shadow, long when) 
                {
                    super.draw(canvas, mapView, shadow);                   
         
                    //---translate the GeoPoint to screen pixels---
                    Point screenPts = new Point();
                    mapView.getProjection().toPixels(p, screenPts);
         
                    
                    Paint circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);  
                    circlePaint.setColor(Color.BLUE);  
                    circlePaint.setStyle(Style.STROKE);
                    circlePaint.setStrokeWidth(2);
                    canvas.drawCircle(screenPts.x, screenPts.y, 130, circlePaint);
                    //---add the marker---
                    Bitmap bmp = BitmapFactory.decodeResource(
                        getResources(), R.drawable.pushpin_graphict);            
                    canvas.drawBitmap(bmp, screenPts.x, screenPts.y-50, null);         
                    return true;
                }

		@Override
		public boolean onTouchEvent(MotionEvent event, MapView mapView) {
			// TODO Auto-generated method stub
			//return super.onTouchEvent(event, mapView);
			  //---when user lifts his finger---
            if (event.getAction() == 1) {                
                p = mapView.getProjection().fromPixels(
                    (int) event.getX(),
                    (int) event.getY());
                    Toast.makeText(getBaseContext(), 
                        p.getLatitudeE6() / 1E6 + "," + 
                        p.getLongitudeE6() /1E6 , 
                        Toast.LENGTH_SHORT).show();
            }                            
            return false;
		}
        
            } 

 */
     /*   @Override
        public boolean onTouchEvent(MotionEvent event, MapView mapView) 
        {   
            //---when user lifts his finger---
            if (event.getAction() == 1) {                
                GeoPoint p = mapView.getProjection().fromPixels(
                    (int) event.getX(),
                    (int) event.getY());
                    Toast.makeText(getBaseContext(), 
                        p.getLatitudeE6() / 1E6 + "," + 
                        p.getLongitudeE6() /1E6 , 
                        Toast.LENGTH_SHORT).show();
            }                            
            return false;
        }       
    }*/ 

	public boolean onKeyDown(int keyCode, KeyEvent event) 
    {
        MapController mc = mapView.getController(); 
        switch (keyCode) 
        {
            case KeyEvent.KEYCODE_3:
                mc.zoomIn();
               // return(true);
                break;
            case KeyEvent.KEYCODE_1:
                mc.zoomOut();
               // return(true);
                break;
            case KeyEvent.KEYCODE_S:
                    mapView.setSatellite(!mapView.isSatellite());
                    break;
                    //
                    //return(true);
                  
            case KeyEvent.KEYCODE_Z:
                    mapView.displayZoomControls(true);
                    break;
                    // return(true);
        }
          return(super.onKeyDown(keyCode, event));
       // return super.onKeyDown(keyCode, event);
    }    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_location);
        latitude=getIntent().getStringExtra("latitude");
        longitude=getIntent().getStringExtra("longitude");
        
        mapView = (MapView) findViewById(R.id.mapView);
        mapView.setBuiltInZoomControls(true);
        
        useLocation = (Button) findViewById(R.id.useLocation);
       
        maptype=(Button) findViewById(R.id.se)
        
        
        
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (locationManager == null) {
            Toast.makeText(LocationSelectMapActivity.this,
                    "Location Manager Not Available", Toast.LENGTH_SHORT)
                    .show();
            return;
        }
        location = locationManager
                .getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (location == null)
            location = locationManager
                    .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        if (location != null) {
            double lat = location.getLatitude();
            double lng = location.getLongitude();
            latitude=Double.toString(lat);
            longitude=Double.toString(lng);
            Toast.makeText(LocationSelectMapActivity.this,
                    "Location Are" + lat + ":" + lng, Toast.LENGTH_SHORT)
                    .show();
            p = new GeoPoint((int) (lat * 1E6), (int) (lng * 1E6));
           // CenterLocatio(p);
            //mapController.animateTo(point, new Message());
            //mapOverlay.setPointToDraw(point);
            //List<Overlay> listOfOverlays = mapView.getOverlays();
            //listOfOverlays.clear();
            //listOfOverlays.add(mapOverlay);
            
        }
        locationListener = new LocationListener() {
            @Override
            public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
            }

            @Override
            public void onProviderEnabled(String arg0) {
            }

            @Override
            public void onProviderDisabled(String arg0) {
            }

            @Override
            public void onLocationChanged(Location l) {
                location = l;
                locationManager.removeUpdates(this);
                if (l.getLatitude() == 0 || l.getLongitude() == 0) {
                } else {
                    double lat = l.getLatitude();
                    double lng = l.getLongitude();
                    latitude=Double.toString(lat);
                    longitude=Double.toString(lng);
                    Toast.makeText(LocationSelectMapActivity.this,
                            "Location Are" + lat + ":" + lng,
                            Toast.LENGTH_SHORT).show();
                }
            }
        };
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
            locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER, 1000, 10f, locationListener);
        locationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER, 1000, 10f, locationListener);
        locationtimer = new CountDownTimer(30000, 5000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (location != null)
                    locationtimer.cancel();
            }

            @Override
            public void onFinish() {
                if (location == null) {
                }
            }
        };
        locationtimer.start();        
        
        
        if(latitude==null||longitude==null)
        {
        	latitude="";
        	longitude="";
        }
        
        
        useLocation.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent= getIntent();
	            // String msg = intent.getStringExtra("location");
	             //msg += ", Added at Third";
	             
	             intent.putExtra("location", p.getLatitudeE6() / 1E6 + "," + 
	                        p.getLongitudeE6() /1E6);
	             setResult(RESULT_OK, intent);
	             
	             finish();
			}
        	
        	
        });
        /*MapView mapView = (MapView) findViewById(R.id.mapView);    
        mapView.setBuiltInZoomControls(true);
        
        
        List<Overlay> mapOverlays = mapView.getOverlays();
        Drawable drawable = this.getResources().getDrawable(R.drawable.pushpin);
        MapOverLay itemizedoverlay = new MapOverLay(drawable);
   
	    GeoPoint point = new GeoPoint(19240000,-99120000);
		OverlayItem overlayitem = new OverlayItem(point, "Hola, Mundo!", "I'm in Mexico City!");
		GeoPoint point2 = new GeoPoint(37300000, 127300000);
		OverlayItem overlayitem2 = new OverlayItem(point2, "Sesang, Annyeong!", "I'm in Seoul!");
			
		itemizedoverlay.addOverlay(overlayitem);
		itemizedoverlay.addOverlay(overlayitem2);
		mapOverlays.add(itemizedoverlay);*/
        
        
       // LinearLayout zoomLayout = (LinearLayout)findViewById(R.id.zoom);  
        View zoomView = mapView.getZoomControls(); 
 
        //zoomLayout.addView(zoomView,new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)); 
        
        mapView.displayZoomControls(true);
        
        mc = mapView.getController();
        //String latitude=getIntent().getStringExtra("latitude");
       // String longitude=getIntent().getStringExtra("longitude");
        if(latitude.equals("") || longitude.equals("")||latitude==null||longitude==null){
        String coordinates[] = {"1.352566007", "103.78921587"};
        latitude="34.352566007";
        longitude="-92.78921587";
        }
        double lat = Double.parseDouble(latitude);
        double lng = Double.parseDouble(longitude);
 
        p = new GeoPoint(
            (int) (lat * 1E6), 
            (int) (lng * 1E6));
 
        
        
        
      /*  MapOverlay mapOverlay = new MapOverlay();
        List<Overlay> listOfOverlays = mapView.getOverlays();
        listOfOverlays.clear();
        listOfOverlays.add(mapOverlay);        
 */
        Drawable marker=getResources().getDrawable(R.drawable.marker);
        
        marker.setBounds(0, 0, marker.getIntrinsicWidth(),marker.getIntrinsicHeight());
         
        mapView.getOverlays().add(new SitesOverlay(marker));
         
        me=new MyLocationOverlay(this, mapView);
        
        mapView.getOverlays().add(me);
        
        mapView.invalidate();
        
        mc.animateTo(p);
        mc.setZoom(16);
       // mc.animateTo(p);
        //mc.setZoom(17); 
 
        
        //---Add a location marker---
/*        MapOverlay mapOverlay = new MapOverlay();
        List<Overlay> listOfOverlays = mapView.getOverlays();
        listOfOverlays.clear();
        listOfOverlays.add(mapOverlay);        
 
        mapView.invalidate();*/
    }
 
   /* @Override
    protected boolean isRouteDisplayed() {
        return false;
    }
    */
    
    
    
    @Override
    public void onResume() {
      super.onResume();
       
      me.enableCompass();
    }   
     
    @Override
    public void onPause() {
      super.onPause();
       
      me.disableCompass();
    }   
    @Override
    protected boolean isRouteDisplayed() {
      return(false);
    }
     
   /* @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
      if (keyCode == KeyEvent.KEYCODE_S) {
        map.setSatellite(!map.isSatellite());
        return(true);
      }
      else if (keyCode == KeyEvent.KEYCODE_Z) {
        map.displayZoomControls(true);
        return(true);
      }
       
      return(super.onKeyDown(keyCode, event));
    }
   */
    private GeoPoint getPoint(double lat, double lon) {
      return(new GeoPoint((int)(lat*1000000.0),
                            (int)(lon*1000000.0)));
    }
    
    
    
    
    
    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.map_menu, menu);
		return true;
	}

	/**
	 * Listens for the button to be pressed and initiates logout if clicked
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.street_mode:			
			Toast.makeText(this, "Street view", Toast.LENGTH_SHORT).show();
			mapView.setSatellite(false);	
			return true;
						
		case R.id.satellite_mode:
			 
			mapView.setSatellite(true);
			
			Toast.makeText(this, "Satellite View", Toast.LENGTH_SHORT).show();
			return true;
					
		default:
			return super.onOptionsItemSelected(item);
		}
	}
    
	
	 private class SitesOverlay extends ItemizedOverlay<OverlayItem> {
	      private List<OverlayItem> items=new ArrayList<OverlayItem>();
	      private Drawable marker=null;
	      private OverlayItem inDrag=null;
	      private ImageView dragImage=null;
	      private int xDragImageOffset=0;
	      private int yDragImageOffset=0;
	      private int xDragTouchOffset=0;
	      private int yDragTouchOffset=0;
	       
	      public SitesOverlay(Drawable marker) {
	        super(marker);
	        this.marker=marker;
	         
	        dragImage=(ImageView)findViewById(R.id.drag);
	        xDragImageOffset=dragImage.getDrawable().getIntrinsicWidth()/2;
	        yDragImageOffset=dragImage.getDrawable().getIntrinsicHeight();
	         
	        //items.add
	        items.add(new OverlayItem(p,
	                                  "Current Location",
	                                  "Current Position"));
	        populate();
	      }
	       
	      @Override
	      protected OverlayItem createItem(int i) {
	        return(items.get(i));
	      }
	       
	      @Override
	      public void draw(Canvas canvas, MapView mapView,
	                        boolean shadow) {
	        super.draw(canvas, mapView, shadow);
	        
	        
	        
	        Point point = new Point();

	        Projection projection = mapView.getProjection();
	        projection.toPixels(p, point);
	       // float projectedRadius = projection.metersToEquatorPixels((float)(1.3 * 1609.3d));

	        Paint paint = new Paint();
	        paint.setStyle(Paint.Style.FILL);
	        paint.setARGB(15, 41, 166, 247);
	        paint.setAntiAlias(true);

	        canvas.drawCircle((float)point.x, (float)point.y, 130, paint);
	        //---translate the GeoPoint to screen pixels---
          /*  Point screenPts = new Point();
            mapView.getProjection().toPixels(p, screenPts);
 
            
            Paint circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);  
            circlePaint.setColor(Color.BLUE);  
            circlePaint.setStyle(Style.STROKE);
            circlePaint.setStrokeWidth(2);
            canvas.drawCircle(screenPts.x, screenPts.y, 130, circlePaint);
            */
            //---add the marker---
           // Bitmap bmp = BitmapFactory.decodeResource(
               // getResources(), R.drawable.pushpin_graphict);            
          //  canvas.drawBitmap(bmp, screenPts.x, screenPts.y-50, null);         
           // return true;
	       // boundCenterBottom(marker);
	      }
	       
	      @Override
	      public int size() {
	        return(items.size());
	      }
	       
	      @Override
	      public boolean onTouchEvent(MotionEvent event, MapView mapView1) {
	        final int action=event.getAction();
	        final int x=(int)event.getX();
	        final int y=(int)event.getY();
	        boolean result=false;
	         
	        if (action==MotionEvent.ACTION_DOWN) {
	          for (OverlayItem item : items) {
	            Point pt=new Point(0,0);
	             
	            mapView.getProjection().toPixels(item.getPoint(), pt);
	             
	            if (hitTest(item, marker, x-pt.x, y-pt.y)) {
	              result=true;
	              inDrag=item;
	              items.remove(inDrag);
	              populate();
	   
	              xDragTouchOffset=0;
	              yDragTouchOffset=0;
	               
	              setDragImagePosition(pt.x, pt.y);
	              dragImage.setVisibility(View.VISIBLE);
	   
	              xDragTouchOffset=x-pt.x;
	              yDragTouchOffset=y-pt.y;
	               
	              break;
	            }
	          }
	        }
	        else if (action==MotionEvent.ACTION_MOVE && inDrag!=null) {
	          setDragImagePosition(x, y);
	          result=true;
	        }
	        else if (action==MotionEvent.ACTION_UP && inDrag!=null) {
	          dragImage.setVisibility(View.GONE);
	           
	          p=mapView.getProjection().fromPixels(x-xDragTouchOffset,
	                                                     y-yDragTouchOffset);
	          OverlayItem toDrop=new OverlayItem(p, inDrag.getTitle(),
	                                             inDrag.getSnippet());
	          Toast.makeText(LocationSelectMapActivity.this, p.getLatitudeE6()+" "+p.getLongitudeE6(), Toast.LENGTH_SHORT).show();
	          items.add(toDrop);
	          populate();
	           
	          inDrag=null;
	          result=true;
	        }
	         
	        return(result || super.onTouchEvent(event, mapView));
	      }
	       
	      private void setDragImagePosition(int x, int y) {
	        RelativeLayout.LayoutParams lp=
	          (RelativeLayout.LayoutParams)dragImage.getLayoutParams();
	               
	        lp.setMargins(x-xDragImageOffset-xDragTouchOffset,
	                        y-yDragImageOffset-yDragTouchOffset, 0, 0);
	        dragImage.setLayoutParams(lp);
	      }
	    }
	    /*@Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        getMenuInflater().inflate(R.menu.activity_main, menu);
	        return true;
	    }
	    */
    
}