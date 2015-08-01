package com.mpss.weed.id.utils;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.graphics.Bitmap;
import android.util.Log;

public class Upload {
	InputStream inputStream;
	
	public Upload(){}
	public void doFileUpload(String selectedPath){
        HttpURLConnection conn = null;
        DataOutputStream dos = null;
        DataInputStream inStream = null;
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary =  "*****";
        int bytesRead, bytesAvailable, bufferSize;
        

        byte[] buffer;
        int maxBufferSize = 1*1024*1024;
        String responseFromServer = "";
        String urlString = "http://mpss.csce.uark.edu/smsdb/weed_app/weedapp/audio_upload.php";

        try
        {
         //------------------ CLIENT REQUEST
        FileInputStream fileInputStream = new FileInputStream(new File(selectedPath) );
         // open a URL connection to the Servlet
         URL url = new URL(urlString);
         // Open a HTTP connection to the URL
         conn = (HttpURLConnection) url.openConnection();
         // Allow Inputs
         conn.setDoInput(true);
         // Allow Outputs
         conn.setDoOutput(true);         // Don't use a cached copy.
         conn.setUseCaches(false);         // Use a post method.
         conn.setRequestMethod("POST");
         conn.setRequestProperty("Connection", "Keep-Alive");
         conn.setRequestProperty("Content-Type", "multipart/form-data;boundary="+boundary);
         dos = new DataOutputStream( conn.getOutputStream() );
         dos.writeBytes(twoHyphens + boundary + lineEnd);
         dos.writeBytes("Content-Disposition: form-data; name=\"uploadedfile\";filename=\"" + selectedPath + "\"" + lineEnd);
         dos.writeBytes(lineEnd);         // create a buffer of maximum size
         bytesAvailable = fileInputStream.available();
         bufferSize = Math.min(bytesAvailable, maxBufferSize);
         buffer = new byte[bufferSize];         // read file and write it into form...
         bytesRead = fileInputStream.read(buffer, 0, bufferSize);
         while (bytesRead > 0)
         {
          dos.write(buffer, 0, bufferSize);
          bytesAvailable = fileInputStream.available();
          bufferSize = Math.min(bytesAvailable, maxBufferSize);
          bytesRead = fileInputStream.read(buffer, 0, bufferSize);
         }         // send multipart form data necesssary after file data...
         dos.writeBytes(lineEnd);
         dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);         // close streams
         Log.e("Debug","File is written");
         fileInputStream.close();
         dos.flush();
         dos.close();
        }
        catch (MalformedURLException ex)
        {
             Log.e("Debug", "error: " + ex.getMessage(), ex);
        }
        catch (IOException ioe)
        {
        	Log.e("Debug", "error: " + ioe.getMessage(), ioe);
        }        //------------------ read the SERVER RESPONSE
        try {
              inStream = new DataInputStream ( conn.getInputStream() );
              String str;
              while (( str = inStream.readLine()) != null)
              {
                   Log.e("Debug","Server Response "+str);
              }
              inStream.close();
        }
        catch (IOException ioex){
             Log.e("Debug", "error: " + ioex.getMessage(), ioex);
        }
      }

	public void doImageUpload(Bitmap image_in_bitmap){		
		
		            //Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.icon);        
		            Bitmap bitmap=image_in_bitmap;
		            ByteArrayOutputStream stream = new ByteArrayOutputStream();		
		            bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream); //compress to which format you want.		
		            byte [] byte_arr = stream.toByteArray();		
		            String image_str = Base64.encodeBytes(byte_arr);			            
		            ArrayList<NameValuePair> nameValuePairs = new  ArrayList<NameValuePair>();		
		            nameValuePairs.add(new BasicNameValuePair("image",image_str)); 
		
		            try{		
		                HttpClient httpclient = new DefaultHttpClient();		
		                HttpPost httppost = new HttpPost("http://mpss.csce.uark.edu/smsdb/weed_app/weedapp/image_upload.php");		
		                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));		
		                HttpResponse response = httpclient.execute(httppost);		
		                String the_string_response = convertResponseToString(response);		
		               // Toast.makeText(UploadImage.this, "Response " + the_string_response, Toast.LENGTH_LONG).show();		
		            }catch(Exception e){		
		                  //Toast.makeText(UploadImage.this, "ERROR " + e.getMessage(), Toast.LENGTH_LONG).show();		
		                  System.out.println("Error in http connection "+e.toString());
		
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
	
}
