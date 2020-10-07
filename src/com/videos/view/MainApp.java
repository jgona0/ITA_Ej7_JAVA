package com.videos.view;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.videos.user.*;

public class MainApp {
	
	
	private static UserRepository user_list = new UserRepository();
	
	
	public static void main(String[] args) {
		
	Scanner action_input = new Scanner(System.in);
	int accion=0;

		//Menu de acciones	
		while(true){
			
			// Gestionamos el error si en vez de poner un número, pones otra cosa
			while (true) {
				
				System.out.println("Que quieres hacer: \n 1 - Crear un usuario \n 2 - Crear un video \n 3 - Ver la lista de usuarios \n 4 - Ver la lista de videos de un usuario \n 5 - Salir");
				
				try {
				
					accion =action_input.nextInt();
					break;
				
				}catch (InputMismatchException e){
					action_input.next();
					System.out.println ("--> ERROR: Tienes que poner un número para seleccionar que acción quieres realizar");
				}	
			}
			
			
			switch (accion){

				// Caso 1, creamos un usuario: pedimos toda la info y añadimos el usuario a la lista de usuarios
				case 1:
					
					//llamamos al metodo de cración y gestionamos la excepción si algún campo que pone el usuario está vacío
					try{
					
						createUser();
					
					}catch (Exception e) {
						
						System.out.println("---> ERROR: No se ha creado el usuario, los campos no pueden estar vacios");
					}
					
					break;
				
					
				// Caso 2, creamos un video y lo asignamos al usuario que toque	
				case 2:
					
					Scanner user_input = new Scanner(System.in);
					int input_id;
					System.out.println("A que usuario pertenece el video (id)");
					
					
					// evaluamos la entrada del usuario, si no es numérica salimos al bucle principal de nuevo
					try {
					
						input_id = user_input.nextInt();	
					
					}catch (InputMismatchException e){
					
						System.out.println ("--> ERROR: El id de usuario debe ser numérico, no se puede crear el video \n");
						break;
	
					}
					
					// si existe el user que el usuario ha dado , realizamos la acción
					if (userExist(input_id)==true) {
					
						List<String> video_tags = new ArrayList<String>();
						
						System.out.println("Introduce el nombre del video");
						String input_video_name = user_input.next();
						
						System.out.println("Introduce la url del video");
						String input_url_video = user_input.next();
						
						
						//pedimos todos los tags hasta que el usuario escriba "salir"
						boolean salir_tags = false;
						int num_tag = 1;
						
						while (salir_tags != true) {
							
							System.out.println("Introduce el tag nº" +num_tag+ "(Escribe 'salir' para dejar de insertar tags)");
							String input_tag = user_input.next();
							
							if(input_tag.equalsIgnoreCase("salir")) {
							
								break;
						
							}else {
							
								video_tags.add(input_tag);
								num_tag++;
							}
							
						}
						
						//creamos el video en el user que toca y gestionamos la excepción de que el nombre o la URL estén vacías
						try {
							
							user_list.getUser(input_id).createVideo(input_url_video, input_video_name, video_tags);	
						
						}catch (Exception e) {
							
							System.out.println("---> EEROR: No se puede crear un video con nombre o URL vacío");
							
						}
						

					// si el user no existe, imprimimos el error por pantalla		
					} else {
						
						System.out.println("---> ERROR: El usuario no existe, no se puede crear el video");
					}
					
					break;
										
				
					
				// Caso 3, sacamos la lista de usuarios y capturamos la excepción si no hay users creados
				case 3: 
					
					try {
					
						System.out.println("Esta es la lista de usuarios: \n" +user_list.getInfoUsers());
					
					}catch (Exception e) {
					
						System.out.println("--> AVISO: No hay usuarios");
					
					}	
					
					break;
				
	
				// Caso 4, sacamos la lista de videos de un usuario
				case 4:				
					
					Scanner user_input4 = new Scanner(System.in);
					int input4_id;
					
					System.out.println("De que usuario quieres sacar la lista de videos (id)");
					
					
					// evaluamos la entrada del usuario, si no es numérica salimos al bucle de nuevo
					try {
						
						input4_id = user_input4.nextInt();	
					
					}catch (InputMismatchException e){
					
						System.out.println ("--> ERROR: El id de usuario debe ser numérico \n");
						break;
	
					}

					
					// si el user existe ejecutamos la acción pero capturamos la excepción de si la lista de videos del user está vacía
					if(userExist(input4_id) == true) {
						
						System.out.println("La lista de videos del usuario " + input4_id+ " es:");
						
						try {
						
							System.out.println(user_list.getUser(input4_id).getVideoList());
						
						}catch (Exception e) {
					
						System.out.println ("--> LISTA VACIA \n");
						
						}
					
					// si no existe el user, imprimimos el error por pantalla
					}else {
						
						System.out.println("---> ERROR: El usuario no existe, no se puede mostrar la lista de videos");
					}
					
					break;
	
				
				// Caso 5, cerramos recurso Scanner y salimos del programa	
				case 5:
					
					action_input.close();
					System.exit(0);
					
				// Si el usuario no pone ninguna de las opciones del 1 al 5, imprimimos el error por pantalla
				default:
					
					System.out.println("La opción no es correcta, elige una opcón del 1 al 5");
					break;

							
			}	
			
		}
	
	}
	
	
	
	//MËTODOS ESTÄTICOS USADOS EN EL MAIN
	
	//metodo de creación de un usuario pidiendo los datos necesarios. Imprime un error si se falla en el id y retorna excepción si algún campo está vacío 
	static void createUser() throws Exception {
		
		Scanner user_input = new Scanner(System.in);
		
		try{
			
			System.out.println("Introduce el nombre");
			String input_name = user_input.nextLine();
			if(input_name.equals("")) throw new Exception();
			
			System.out.println("Introduce el apellido");
			String input_surname = user_input.nextLine();
			if(input_surname.equals("")) throw new Exception();
			
			System.out.println("Introduce el password");
			String input_pass = user_input.nextLine();
			if(input_pass.equals("")) throw new Exception();
			
			
			user_list.addUser(new User(input_name, input_surname, input_pass));	
		
		}catch (InputMismatchException e){
			
			System.out.println("---> ERROR: El id del user debe ser un número");
		}
		
	}
	
	
	// método boolean que retorna true si un user existe y false si no existe
	static boolean userExist(int id) {
	
		boolean result = false;
		
		try {
			user_list.getUser(id).getId();
			result= true;
	
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		
		return result;
	}

}

