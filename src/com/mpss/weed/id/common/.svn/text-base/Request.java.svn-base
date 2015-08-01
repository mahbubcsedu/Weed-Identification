package com.mpss.weed.id.common;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Parcel;
import android.os.Parcelable;

public class Request implements Parcelable {

	String identificationId;
	String weedPhotoId;
	String userId;
	String weedId;
	String expertId;
	String idRanking;
	String requestSentDate;
	String idSentDate;
	String voice_url;
	String notes;
	boolean newReq;
	boolean newId;

	public Request() {
	}

	public Request(JSONObject request) {

		try {
			identificationId = request.getString("identification_id");
			weedPhotoId = request.getString("weed_photo_id");
			userId = request.getString("user_id");
			weedId = request.getString("weed_id");
			expertId = request.getString("expert_id");
			idRanking = request.getString("id_ranking");
			requestSentDate = request.getString("request_sent_date");
			idSentDate = request.getString("id_sent_date");
			voice_url = request.getString("voice_url");
			notes = request.getString("farmer_comments");

			if (request.getString("new_req").equals("1")) {
				newReq = true;
			} else {
				newReq = false;
			}

			if (request.getString("new_id").equals("1")) {
				newId = true;
			} else {
				newId = false;
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getIdentificationId() {
		return identificationId;
	}

	public void setIdentificationId(String identificationId) {
		this.identificationId = identificationId;
	}

	public String getWeedPhotoId() {
		return weedPhotoId;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getVoice_url() {
		return voice_url;
	}

	public void setVoice_url(String voice_url) {
		this.voice_url = voice_url;
	}

	public void setWeedPhotoId(String weedPhotoId) {
		this.weedPhotoId = weedPhotoId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getWeedId() {
		return weedId;
	}

	public void setWeedId(String weedId) {
		this.weedId = weedId;
	}

	public String getExpertId() {
		return expertId;
	}

	public void setExpertId(String expertId) {
		this.expertId = expertId;
	}

	public String getIdRanking() {
		return idRanking;
	}

	public void setIdRanking(String idRanking) {
		this.idRanking = idRanking;
	}

	public String getRequestSentDate() {
		return requestSentDate;
	}

	public void setRequestSentDate(String requestSentDate) {
		this.requestSentDate = requestSentDate;
	}

	public String getIdSentDate() {
		return idSentDate;
	}

	public void setIdSentDate(String idSentDate) {
		this.idSentDate = idSentDate;
	}

	public boolean isNewReq() {
		return newReq;
	}

	public void setNewReq(boolean newReq) {
		this.newReq = newReq;
	}

	public boolean isNewId() {
		return newId;
	}

	public void setNewId(boolean newId) {
		this.newId = newId;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel out, int flags) {
		out.writeString(identificationId);
		out.writeString(weedPhotoId);
		out.writeString(userId);
		out.writeString(weedId);
		out.writeString(expertId);
		out.writeString(idRanking);
		out.writeString(requestSentDate);
		out.writeString(idSentDate);
		out.writeString(voice_url);
		out.writeString(notes);
		out.writeByte((byte) (newReq ? 1 : 0));
		out.writeByte((byte) (newId ? 1 : 0));
	}

	public static final Parcelable.Creator<Request> CREATOR = new Parcelable.Creator<Request>() {
		public Request createFromParcel(Parcel in) {
			return new Request(in);
		}

		public Request[] newArray(int size) {
			return new Request[size];
		}
	};

	private Request(Parcel in) {
		identificationId = in.readString();
		weedPhotoId = in.readString();
		userId = in.readString();
		weedId = in.readString();
		expertId = in.readString();
		idRanking = in.readString();		
		requestSentDate = in.readString();
		idSentDate = in.readString();
		voice_url=in.readString();
		notes=in.readString();
		newReq = in.readByte() == 1;
		newId = in.readByte() == 1;
	}
}