package com.mpss.weed.id.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import com.mpss.weed.id.R;

import android.graphics.Bitmap;
import android.location.Location;
import android.provider.ContactsContract.Settings;
import android.util.Log;

public class UploadtoServer {

	    InputStream inputStream;
	    //private String urlNewRequest = "http://mpss.csce.uark.edu/smsdb/weed_app/weedapp/upload_request.php";
	    //private String urlExpertResponse = "http://mpss.csce.uark.edu/smsdb/weed_app/weedapp/expert_response.php";
	    //private String urlSaveToDB = "http://mpss.csce.uark.edu/smsdb/weed_app/weedapp/savetodb.php";
	    private String upload_url =""; 
	    //private String urlExpertResponse = "http://mpss.csce.uark.edu/smsdb/weed_app/weedapp/expert_response.php";
	    //private String urlSaveToDB = "http://mpss.csce.uark.edu/smsdb/weed_app/weedapp/savetodb.php";
		private DefaultHttpClient mHttpClient;


	    public UploadtoServer(String url) {
	    	upload_url=url;
	        HttpParams params = new BasicHttpParams();
	        params.setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
	        mHttpClient = new DefaultHttpClient(params);
	    }


	    public void uploadUserPhoto(Bitmap image, String strAudioPath, String comments,String userID,String imageheight,String imagewidth,Location currLocation) {

	        try {      
	        	
	        	
	        	Bitmap bitmap=image;
	        	File faudio;
	        	//String longitude=currLocation.substring(0, currLocation.indexOf(",")),latitude=currLocation.substring(currLocation.indexOf(",")+1,currLocation.length());
	            ByteArrayOutputStream stream = new ByteArrayOutputStream();		
	            bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream); //compress to which format you want.		
	            byte [] byte_arr = stream.toByteArray();		
	            String image_str = Base64.encodeBytes(byte_arr);
	            //String audiofile=strAudioPath.substring(strAudioPath.length()-3);
	           // if(audiofile.equals("mp4")||audiofile.equals("3gp")){
	            faudio=new File(strAudioPath);
	           // }
	            //else faudio=null;
	            
	            HttpPost httppost = new HttpPost(upload_url);

	            MultipartEntity multipartEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
                //ContentBody cbf=new StringBody("Title");
	            //multipartEntity.addPart("Title", cbf);//new StringBody("Title"));
	            multipartEntity.addPart("comments", new StringBody(comments));
	            multipartEntity.addPart("userid", new StringBody(userID));
	            multipartEntity.addPart("sensordata1", new StringBody("sensordata1"));
	            multipartEntity.addPart("sensordata2", new StringBody("sensordata2"));
	            multipartEntity.addPart("sensordata3", new StringBody("sensordata3"));
	            //multipartEntity.addPart("Description", new StringBody(Settings.SHARE.TEXT));
	            multipartEntity.addPart("image", new StringBody(image_str));
	            multipartEntity.addPart("imageheight", new StringBody(imageheight));
	            multipartEntity.addPart("imagewidth", new StringBody(imagewidth));
	            multipartEntity.addPart("latitude", new StringBody(Double.toString(currLocation.getLatitude())));
	            multipartEntity.addPart("longitude", new StringBody(Double.toString(currLocation.getLongitude())));
	            if(strAudioPath!=""){
	            multipartEntity.addPart("audio", new FileBody(faudio));
	            }
	            //multipartEntity.addPart("file3", new FileBody(image3));
	            httppost.setEntity(multipartEntity);

	            //mHttpClient.execute(httppost, new PhotoUploadResponseHandler());
                HttpResponse response=mHttpClient.execute(httppost);
                String the_string_response = convertResponseToString(response);
                System.out.println(the_string_response);
	        } catch (Exception e) {
	            Log.e(UploadtoServer.class.getName(), e.getLocalizedMessage(), e);
	        }
	    }

	    private class PhotoUploadResponseHandler implements ResponseHandler {

	        @Override
	        public Object handleResponse(HttpResponse response)
	                throws ClientProtocolException, IOException {

	            HttpEntity r_entity = response.getEntity();
	            String responseString = EntityUtils.toString(r_entity);
	            Log.d("UPLOAD", responseString);
	            System.out.println(responseString);

	            return null;
	        }

	    }
	    public String convertResponseToString(HttpResponse response) throws IllegalStateException, IOException{
			
			 
			
            String res = "";		
            StringBuffer buffer = new StringBuffer();		
            inputStream = response.getEntity().getContent();		
            int contentLength = (int) response.getEntity().getContentLength(); //getting content length…..		
            //Toast.makeText(UploadImage.this, "contentLength : " + contentLength, Toast.LENGTH_LONG).show();		
            if (contentLength < 0){		
            }		
            else{

                   byte[] data = new byte[512];		
                   int len = 0;		
                   try		
                   {		
                       while (-1 != (len = inputStream.read(data)) )		
                       {		
                           buffer.append(new String(data, 0, len)); //converting to string and appending  to stringbuffer…..		
                       }		
                   }		
                   catch (IOException e)		
                   {		
                       e.printStackTrace();
		                    }		
                   try		
                   {		
                       inputStream.close(); // closing the stream…..		
                   }		
                   catch (IOException e)		
                   {		
                       e.printStackTrace();		
                   }		
                   res = buffer.toString();     // converting stringbuffer to string…..				 
                  //Toast.makeText(UploadImage.this, "Result : " + res, Toast.LENGTH_LONG).show();		
                   //System.out.println("Response => " +  EntityUtils.toString(response.getEntity()));

            }		
            return res;		
       }

	    
	    public void UploadExpertResponseInfo(String identificationID,int imageID1,int imageID2,int imageID3,int rankID1,int rankID2,int rankID3, String strAudioPath, String comments,String expertID) {

	    	
	    	 //String image1="",image2="",image3="",rank1="",rank2="",rank3="";
	        try {
//	        	Bitmap bitmap=image;
//	            ByteArrayOutputStream stream = new ByteArrayOutputStream();		
//	            bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream); //compress to which format you want.		
//	            byte [] byte_arr = stream.toByteArray();		
//	            String image_str = Base64.encodeBytes(byte_arr);
	            File faudio;
	            /*if(imageID1==0){
	            	image1="";
	            	rank1="";
	            }
	            if(imageID1==0){
	            	image2="";
	            	rank2="";
	            }
	            if(imageID1==0){
	            	image3="";
	            	rank3="";
	            }*/
	            
	            HttpPost httppost = new HttpPost(upload_url);
	            //String audiofile=strAudioPath.substring(strAudioPath.length()-3);
	            //if(audiofile.equals("mp4")||audiofile.equals("3gp")){
	            faudio=new File(strAudioPath);
	            
	            MultipartEntity multipartEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
                //ContentBody cbf=new StringBody("Title");
	            //multipartEntity.addPart("Title", cbf);//new StringBody("Title"));
	            multipartEntity.addPart("comments", new StringBody(comments));
	            multipartEntity.addPart("expertID", new StringBody(expertID));
	            multipartEntity.addPart("imageID1", new StringBody(Integer.toString(imageID1)));
	            multipartEntity.addPart("imageID2", new StringBody(Integer.toString(imageID2)));
	            multipartEntity.addPart("imageID3", new StringBody(Integer.toString(imageID3)));
	            multipartEntity.addPart("rankID1", new StringBody(Integer.toString(rankID1)));
	            multipartEntity.addPart("rankID2", new StringBody(Integer.toString(rankID2)));
	            multipartEntity.addPart("rankID3", new StringBody(Integer.toString(rankID3)));
	            //multipartEntity.addPart("imageID1", new StringBody(Integer.toString(imageID1)));
	            multipartEntity.addPart("identificationID", new StringBody(identificationID));	
	            if(strAudioPath != ""){
	            multipartEntity.addPart("audio", new FileBody(faudio));
	            }
	            //multipartEntity.addPart("file3", new FileBody(image3));
	            httppost.setEntity(multipartEntity);

	            //mHttpClient.execute(httppost, new PhotoUploadResponseHandler());
                HttpResponse response=mHttpClient.execute(httppost);
                String the_string_response = convertResponseToString(response);
                System.out.println(the_string_response);
	        } catch (Exception e) {
	            Log.e(UploadtoServer.class.getName(), e.getLocalizedMessage(), e);
	        }
	    }
	    
	    
	    public void UploadforSaveToDB(String imageurl) {

	    	
	    
	        try {

	        	HttpPost httppost = new HttpPost(upload_url);
	            MultipartEntity multipartEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
               //ContentBody cbf=new StringBody("Title");
	            //multipartEntity.addPart("Title", cbf);//new StringBody("Title"));
	            multipartEntity.addPart("image", new StringBody(imageurl));
	            
	            httppost.setEntity(multipartEntity);

	            //mHttpClient.execute(httppost, new PhotoUploadResponseHandler());
               HttpResponse response=mHttpClient.execute(httppost);
               String the_string_response = convertResponseToString(response);
               System.out.println(the_string_response);
	        } catch (Exception e) {
	            Log.e(UploadtoServer.class.getName(), e.getLocalizedMessage(), e);
	        }
	    }
	
}
