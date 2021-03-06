package com.mpss.weed.id.utils;

import com.mpss.weed.id.R;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

/** Class Must extends with Dialog */
/** Implement onClickListener to dismiss dialog when OK Button is pressed */
public class CustomizeDialog extends Dialog implements OnClickListener {
	Button okButton;
    CheckBox dontShow;
    TextView tv_instruction;
    String instructionstring,key;
    SharedPreferences prefs;
    
    Context parentcontext;
	public CustomizeDialog(Context context,SharedPreferences pref,String instruction,String key) {
		super(context);
		this.prefs=pref;
		this.parentcontext=context;
		this.instructionstring=instruction;
		this.key=key;
		/** 'Window.FEATURE_NO_TITLE' - Used to hide the title */
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		/** Design the dialog in main.xml file */
		setContentView(R.layout.customdialogue);
		dontShow=(CheckBox)findViewById(R.id.skip);
		okButton = (Button) findViewById(R.id.btn_dismiss);
		tv_instruction=(TextView) findViewById(R.id.textinstruction);
		tv_instruction.setText(this.instructionstring);
		okButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		/** When OK Button is clicked, dismiss the dialog */
		if (v == okButton){
			if(dontShow.isChecked()){
			AlertDialog builder2 = new AlertDialog.Builder(this.parentcontext)
            .setTitle("Confirmation")
            .setMessage("Are you sure about disabling instruction from next use")
            .setPositiveButton("Submit", new Dialog.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    // Mark this version as read.
                	
        				SharedPreferences.Editor editor = prefs.edit();
                        editor.putBoolean(CustomizeDialog.this.key, true);
                        editor.commit();
                        //dialogInterface.dismiss();
        			}
                
            })
            .setNegativeButton("Cancel", new Dialog.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					// Close the activity as they have declined the EULA
					
				}
            	
            })
            .show();}			
		
			dismiss();
		}
	}

}
