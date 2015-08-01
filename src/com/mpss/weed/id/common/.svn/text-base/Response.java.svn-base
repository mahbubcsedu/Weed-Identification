package com.mpss.weed.id.common;

import org.json.JSONException;
import org.json.JSONObject;

public class Response  {

	String response;
	String expert_speechID;
	String expert_comments;

	public Response() {
	}

	public Response(JSONObject response) {
			try {
				this.response=response.getString("expert_response_id");
				this.expert_comments=response.getString("expert_comments");
				this.expert_speechID=response.getString("expert_speechID");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
	}

	public String getResponse() {
		return response;
	}

	public String getExpert_speechID() {
		return expert_speechID;
	}

	public void setExpert_speechID(String expert_speechID) {
		this.expert_speechID = expert_speechID;
	}

	public String getExpert_comments() {
		return expert_comments;
	}

	public void setExpert_comments(String expert_comments) {
		this.expert_comments = expert_comments;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	
	
}

	/*@Override
	public void writeToParcel(Parcel out, int flags) {
		out.writeString(identificationId);
		out.writeString(weedPhotoId);
		out.writeString(userId);
		out.writeString(weedId);
		out.writeString(expertId);
		out.writeString(idRanking);
		out.writeString(requestSentDate);
		out.writeString(idSentDate);
		out.writeByte((byte) (newReq ? 1 : 0));
		out.writeByte((byte) (newId ? 1 : 0));
	}

	public static final Parcelable.Creator<Response> CREATOR = new Parcelable.Creator<Response>() {
		public Response createFromParcel(Parcel in) {
			return new Response(in);
		}

		public Response[] newArray(int size) {
			return new Response[size];
		}
	};

	private Response(Parcel in) {
		identificationId = in.readString();
		weedPhotoId = in.readString();
		userId = in.readString();
		weedId = in.readString();
		expertId = in.readString();
		idRanking = in.readString();
		requestSentDate = in.readString();
		idSentDate = in.readString();
		newReq = in.readByte() == 1;
		newId = in.readByte() == 1;
	}
}*/