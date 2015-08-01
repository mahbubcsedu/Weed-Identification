package com.mpss.weed.id.utils;

import com.mpss.weed.id.R;
import com.mpss.weed.id.common.RegisterActivity;
import com.mpss.weed.id.common.RequestList;
import com.mpss.weed.id.expert.ExpertDiagnoseRequestActivity;
import com.mpss.weed.id.farmer.FarmerNewRequestActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

/** Class Must extends with Dialog */
/** Implement onClickListener to dismiss dialog when OK Button is pressed */
public class InstructionAcitivity extends Activity implements OnClickListener {
	Button okButton;
    CheckBox dontShow;
    TextView tv_instruction;
    String instructionstring,key;
    String instructionType;
    SharedPreferences prefs;
    
    Context parentcontext;
	public void onCreate(Bundle savedInstanceState)  {
		super.onCreate(savedInstanceState);
		//key=this.getString(R.string.instructionKeyReg);
		//instructionstring=this.getString(R.string.instruction_reg);
		this.key=getIntent().getStringExtra("key");
		this.instructionstring=getIntent().getStringExtra("instruction");
		this.instructionType=getIntent().getStringExtra("instructionType");
		//this.key=key;
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
			AlertDialog builder2 = new AlertDialog.Builder(this)
            .setTitle("Confirmation")
            .setMessage("Are you sure about disabling instruction from next use")
            .setPositiveButton("Submit", new Dialog.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    // Mark this version as read.
                	
        				SharedPreferences.Editor editor = prefs.edit();
                        editor.putBoolean(InstructionAcitivity.this.key, true);
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
		
			Intent intent;
			if(this.instructionType.equals("reg")){
				intent= new Intent(this,RegisterActivity.class);
				startActivity(intent);
			}
			else if(this.instructionType.equals("ESub")){
				intent= new Intent(this,ExpertDiagnoseRequestActivity.class);
				RequestList reqeusts=getIntent().getParcelableExtra("request");
				intent.putExtra("request", reqeusts);
				startActivity(intent);	
			}
			else if(this.instructionType.equals("FReq")){
				intent=new Intent(this, FarmerNewRequestActivity.class);
  				//intent.putExtra("userID", userID);
  				startActivity(intent);
			}
			
	        
			//Intent intent = new Intent(context,
			//				GridViewActivity.class);
			
			//AbsoluteLayout abl=(AbsoluteLayout)findViewById(R.id.ablogin);
			//Log.d("screensize","value height"+abl.getHeight());
			 //EditText username2 = (EditText) findViewById(R.id.username);
		    // username2.getLocationInWindow(loc);
		    // Log.i("screensize", loc[0]+"yvalue"+loc[1]);
						
			//dismiss();
		}
	}

}
