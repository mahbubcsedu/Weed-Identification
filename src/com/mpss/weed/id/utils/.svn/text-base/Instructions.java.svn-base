package com.mpss.weed.id.utils;

import com.mpss.weed.id.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class Instructions {

	private String EULA_PREFIX = "eula_";
	private Activity mActivity; 
	
	public Instructions(Activity context) {
		mActivity = context; 
	}
	
	private PackageInfo getPackageInfo() {
        PackageInfo pi = null;
        try {
             pi = mActivity.getPackageManager().getPackageInfo(mActivity.getPackageName(), PackageManager.GET_ACTIVITIES);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return pi; 
    }

     public void show(Context context) {
        PackageInfo versionInfo = getPackageInfo();       
        
       
      

        // the eulaKey changes every time you increment the version number in the AndroidManifest.xml
		final String eulaKey = EULA_PREFIX + versionInfo.versionCode;
        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(mActivity);
        boolean hasBeenShown = prefs.getBoolean(eulaKey, false);
        if(hasBeenShown == false){

        	// Show the Eula
            //String title = mActivity.getString(R.string.app_name) + " v" + versionInfo.versionName;
        	String title="Instructions";
            
            //Includes the updates as well so users know what changed. 
            String message = mActivity.getString(R.string.instruction_reg) ;

            AlertDialog.Builder builder = new AlertDialog.Builder(mActivity)
                    .setTitle(title)
                    .setMessage(message)
                    .setPositiveButton("Don't Show Next Time", new Dialog.OnClickListener() {

                    	
                    	 
                    	 
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            // Mark this version as read.
                        	 AlertDialog builder2 = new AlertDialog.Builder(mActivity)
                             .setTitle("Confirmation")
                             .setMessage("Are you sure about disabling instruction from next use")
                             .setPositiveButton("agree", new Dialog.OnClickListener() {

                                 @Override
                                 public void onClick(DialogInterface dialogInterface, int i) {
                                     // Mark this version as read.
                                     SharedPreferences.Editor editor = prefs.edit();
                                     editor.putBoolean(eulaKey, true);
                                     editor.commit();
                                     dialogInterface.dismiss();
                                 }
                             })
                             .setNegativeButton("Cancel", new Dialog.OnClickListener() {

         						@Override
         						public void onClick(DialogInterface dialog, int which) {
         							// Close the activity as they have declined the EULA
         							
         						}
                             	
                             })
                             .show();
                            /*SharedPreferences.Editor editor = prefs.edit();
                            editor.putBoolean(eulaKey, true);
                            editor.commit();
                            dialogInterface.dismiss();*/
                        }
                    })
                    .setNegativeButton("Dismiss", new Dialog.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// Close the activity as they have declined the EULA
							mActivity.finish(); 
						}
                    	
                    });
            builder.create().show();
        }
    }
	
}
