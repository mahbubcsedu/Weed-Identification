package com.mpss.weed.id.common;

public class WeedIdentification  {

	String weed_id;
	String ranking;

	public WeedIdentification() {
	}

	public WeedIdentification(String weed_id,String ranking) {
       try {
    	   
			this.weed_id=weed_id;
			this.ranking=ranking;
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}