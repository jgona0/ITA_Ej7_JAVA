package com.videos.vid;

import java.util.ArrayList;
import java.util.List;

public class Video {

	//Variables de la clase
	private String url; 
	private String title; 
	private List<String> tags = new ArrayList<String>();
	
	
	// CONSTRUCTOR
	public Video (String url, String title, List<String> tags) throws Exception {
		
		if(url.equals(null)) throw new Exception();
		if(title.equals(null)) throw new Exception();
		
		this.url = url;
		this.title = title;
		this.tags = tags;	
	}
	

	// GETTERS
	
	// Retorno de la información del video en String (estilo toString)
	public String getInfoVideo() {
		
		return "-VIDEO:" +title+ "  -URL:" +url;
		
	}
	
	// Retorno de los tags en formato String
	public String getTagsVideo() {
		
		String total_tags = "  -TAGS:";
		
		for (int i=0; i<tags.size(); i++) {
		
			total_tags += tags.get(i) + ",";
		}
		
		return total_tags;
	}
	
	
	

}
