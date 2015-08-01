package com.mpss.weed.id.common;

import android.app.Application;

public class SessionApp extends Application{

	private static String loggeduser;
	private static String usertype;

	@Override
	public void onCreate() {
	    super.onCreate();
	    loggeduser="";
	    usertype="";
	}

	public static String getUserLoggedUserID() {
	    return loggeduser;
	}

	public static void setLoggedUserID(String loggeduser) {
		SessionApp.loggeduser = loggeduser;
	}

	public static String getUserType() {
	    return usertype;
	}

	public static void setUserType(String usertype) {
		SessionApp.usertype = usertype;
	}

}
