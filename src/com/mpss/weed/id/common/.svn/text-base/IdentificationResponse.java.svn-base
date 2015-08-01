package com.mpss.weed.id.common;

import org.json.JSONException;
import org.json.JSONObject;

public class IdentificationResponse  {

	String identification_id;

	public IdentificationResponse() {
	}

	public IdentificationResponse(JSONObject identification) {
			try {
				identification_id=identification.getString("identification_id");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
	}

	public String getIdentification_id() {
		return identification_id;
	}

	public void setIdentification_id(String identification_id) {
		this.identification_id = identification_id;
	}
	
}