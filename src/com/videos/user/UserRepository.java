package com.videos.user;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {

	List<User> user_list = new ArrayList<User>();

	
	// Método para añadir un usuario al repositorio de users
	public void addUser (User user) {
		
		user_list.add(user);
		System.out.println("---> USUARIO CREADO con id: " +user.getId()+ "\n");

	}
	
	
	
	// GETTERS
	
	public String getInfoUsers() throws Exception{
		
		if(user_list.size()==0) throw new Exception();
	
		String list_users = "";
		
		for (int i=0; i<user_list.size(); i++) {
			
			list_users += user_list.get(i).getNameSurname() + " --> Id: " +user_list.get(i).getId() + " --> Num videos: " +user_list.get(i).getNumVideos() + " --> Fecha Registro: " +user_list.get(i).getDate()+ "\n"; 
		}
		
		
		return list_users;
	
	}
	
	
	public User getUser (int id) {
		
		int index_ret=0;
		
		for(int i=0; i<user_list.size(); i++) {
			
			if(user_list.get(i).getId() == id) {
				
				index_ret = i;
			}
			
		}
		
		return user_list.get(index_ret);

	}
	
	
	public int getSize() {
		return user_list.size();
	}
	
}
