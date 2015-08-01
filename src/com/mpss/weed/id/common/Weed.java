package com.mpss.weed.id.common;

import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class Weed implements Parcelable {

	private int id;
	private String commonName;
	private String latinName;
	public String imageUrl;
	private String weedType;
	private String lifeCycle;
	private String season;
	private String site;
	private String professionalControl;
	private String homeownerControl;
	private Bitmap image;
	private String rank;

	boolean isResponse=false;
	public Weed() {
	}

	public Weed(String serverResponse) {
		String[] info = serverResponse.split("_COL_");
		id = Integer.parseInt(info[0]);
		commonName = info[1];
		latinName = info[2];
		weedType = info[3].toUpperCase();
		lifeCycle = info[4].toUpperCase();
		season = info[5].toUpperCase();
		site = info[6];
		professionalControl = info[7];
		homeownerControl = info[8];
	}
	
	public Weed(JSONObject weed) {

		try {
			id = Integer.parseInt(weed.getString("weed_id"));
			commonName = weed.getString("common_name");
			latinName = weed.getString("latin_name");
			weedType = weed.getString("weed_type");
			lifeCycle = weed.getString("life_cycle");
			season = weed.getString("season");
			site = weed.getString("site");			
			professionalControl = weed.getString("prof_control");
			homeownerControl = weed.getString("home_control");
			imageUrl = weed.getString("image_url");
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Weed(JSONObject weed,boolean isRankEnable) {

		try {
			id = Integer.parseInt(weed.getString("weed_id"));
			commonName = weed.getString("common_name");
			latinName = weed.getString("latin_name");
			weedType = weed.getString("weed_type");
			lifeCycle = weed.getString("life_cycle");
			season = weed.getString("season");
			site = weed.getString("site");			
			rank=weed.getString("rank");			
			professionalControl = weed.getString("prof_control");
			homeownerControl = weed.getString("home_control");
			imageUrl = weed.getString("image_url");
			isResponse=isRankEnable;
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public Weed(String commonName, String latinName, String weedType,
			String lifeCycle, String season, String site,
			String professionalControl, String homeownerControl) {
		this.commonName = commonName;
		this.latinName = latinName;
		this.weedType = weedType;
		this.lifeCycle = lifeCycle;
		this.season = season;
		this.site = site;
		this.professionalControl = professionalControl;
		this.homeownerControl = homeownerControl;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public boolean isResponse() {
		return isResponse;
	}

	public void setResponse(boolean isResponse) {
		this.isResponse = isResponse;
	}

	public String getCommonName() {
		return commonName;
	}

	public void setCommonName(String commonName) {
		this.commonName = commonName;
	}

	public String getLatinName() {
		return latinName;
	}

	public void setLatinName(String latinName) {
		this.latinName = latinName;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getWeedType() {
		return weedType;
	}

	public void setWeedType(String weedType) {
		this.weedType = weedType;
	}

	public String getLifeCycle() {
		return lifeCycle;
	}

	public void setLifeCycle(String lifeCycle) {
		this.lifeCycle = lifeCycle;
	}

	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getProfessionalControl() {
		return professionalControl;
	}

	public void setProfessionalControl(String professionalControl) {
		this.professionalControl = professionalControl;
	}

	public String getHomeownerControl() {
		return homeownerControl;
	}

	public void setHomeownerControl(String homeownerControl) {
		this.homeownerControl = homeownerControl;
	}

	public Bitmap getImage() {
		return image;
	}

	public void setImage(Bitmap image) {
		this.image = image;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel out, int flags) {
		out.writeInt(id);
		out.writeString(commonName);
		out.writeString(latinName);
		out.writeString(imageUrl);
		out.writeString((weedType == null) ? "" : weedType.toString());
		out.writeString((lifeCycle == null) ? "" : lifeCycle.toString());
		out.writeString((season == null) ? "" : season.toString());
		out.writeString(site);
		out.writeString(professionalControl);
		out.writeString(homeownerControl);
		if(isResponse){
			out.writeString(rank);
		}
		if (image == null) {
			out.writeString("no");
		} else {
			out.writeString("yes");
			image.writeToParcel(out, 0);
		}
	}

	public static final Parcelable.Creator<Weed> CREATOR = new Parcelable.Creator<Weed>() {
		public Weed createFromParcel(Parcel in) {
			return new Weed(in);
		}

		public Weed[] newArray(int size) {
			return new Weed[size];
		}
	};

	private Weed(Parcel in) {

		id = in.readInt();
		commonName = in.readString();
		latinName = in.readString();
		imageUrl = in.readString();
		weedType = in.readString();
		lifeCycle = in.readString();
		season = in.readString();
		site = in.readString();
		if(isResponse){
		rank=in.readString();
		}
		professionalControl = in.readString();
		homeownerControl = in.readString();
		if (in.readString().equals("yes")) {
			image = Bitmap.CREATOR.createFromParcel(in);
		}
	}
}
