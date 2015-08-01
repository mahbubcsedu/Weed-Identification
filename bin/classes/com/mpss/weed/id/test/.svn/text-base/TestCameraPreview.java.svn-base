package com.mpss.weed.id.test;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Button;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.mpss.weed.id.R;

import android.app.Activity;
//import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.ShutterCallback;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class TestCameraPreview extends Activity  {
	private static final String TAG = "CameraDemo";
	Camera camera;
	Preview preview;
	Button buttonClick;
	ImageView imgTakenPhoto;
	Bitmap bitmap;
	String strImagePath="",strAudioPath="",imageheight="",imagewidth="";

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.testcamerapreview);

		preview = new Preview(this);
		((FrameLayout) findViewById(R.id.preview)).addView(preview);
		imgTakenPhoto = (ImageView) findViewById(R.id.imageView1);
		buttonClick = (Button) findViewById(R.id.buttonClick);
		buttonClick.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				preview.camera.takePicture(shutterCallback, rawCallback,
						jpegCallback);
			}
		});

		Log.d(TAG, "onCreate'd");
	}

	ShutterCallback shutterCallback = new ShutterCallback() {
		public void onShutter() {
			Log.d(TAG, "onShutter'd");
		}
	};

	/** Handles data for raw picture */
	PictureCallback rawCallback = new PictureCallback() {
		public void onPictureTaken(byte[] data, Camera camera) {
			Log.d(TAG, "onPictureTaken - raw");
		}

		@Override
		public void onPictureTaken(byte[] arg0, android.hardware.Camera arg1) {
			// TODO Auto-generated method stub
			
		}
	};

	/** Handles data for jpeg picture */
	PictureCallback jpegCallback = new PictureCallback() {
		public void onPictureTaken(byte[] data, Camera camera) {
			FileOutputStream outStream = null;
			try {
				// write to local sandbox file system
				// outStream =
				// CameraDemo.this.openFileOutput(String.format("%d.jpg",
				// System.currentTimeMillis()), 0);
				// Or write to sdcard
				outStream = new FileOutputStream(String.format(
						"/sdcard/%d.jpg", System.currentTimeMillis()));
				outStream.write(data);
				outStream.close();
				//Bitmap thumbnail = (Bitmap) data;  
				Bitmap thumbnail = BitmapFactory.decodeByteArray(data , 0, data .length);
				bitmap=thumbnail;
				imageheight=Integer.toString(bitmap.getHeight());
				imagewidth=Integer.toString(bitmap.getWidth());
				Log.i("TAG",imageheight);
				Log.i("TAG",imagewidth);
				imgTakenPhoto.setImageBitmap(thumbnail);  
				
				
				Log.d(TAG, "onPictureTaken - wrote bytes: " + data.length);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
			}
			Log.d(TAG, "onPictureTaken - jpeg");
		}

		@Override
		public void onPictureTaken(byte[] arg0, android.hardware.Camera arg1) {
			// TODO Auto-generated method stub
			
		}
	};

}
