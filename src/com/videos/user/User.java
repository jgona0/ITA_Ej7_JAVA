package com.videos.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.videos.vid.Video;

public class User {

	
	// Varaibles de clase
	private String name; 
	private String surname; 
	private String password;
	private int id;
	private static int next_id = 1;
	Date date;
	private List<Video> video_list = new ArrayList<Video>();
	
	
	//Constructor
	public User(String name, String surname, String password) {
		
		this.name = name;
		this.surname = surname;
		this.password = password;
		
		//asignación automática del id, desde el id=1 
		this.id = next_id;
		next_id++;
		
		date = new Date();
	}
	
	
	// Método para crear un video y asignarlo al usuario
	public void createVideo (String url, String title, List<String> tags) {
		
		Video vid;
		
		try {

			vid = new Video (url, title, tags);
			video_list.add(vid);

		} catch (Exception e) {
		
			e.printStackTrace();
		}
		
	}
	
	
	// Método para obtener la lista de videos de un usuario, retorna excepción si la lista está vacía
	public String getVideoList() throws Exception{
		
		if(video_list.size()==0) throw new Exception();
		
		String output="";
		
		for(int i = 0; i<video_list.size(); i++) {
			
			output += video_list.get(i).getInfoVideo()+ video_list.get(i).getTagsVideo()+"\n";
			
		}
		
		return output;
	}
	
	
	// Getters
	public int getNumVideos() {
		return video_list.size();
	}
	
	public int getId() {
		return id;
	}
	
	public String getNameSurname() {
		return name+ " " +surname;
	}
	
	public Date getDate() {
		return date;
	}
}
