package com.video.application;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import com.video.domain.User;
import com.video.domain.Video;

public class UserController {
	List<User> usersList = new ArrayList<User>();
	
	public User createUser() {
		Scanner sc = new Scanner(System.in);
		String name, surname, password;
		List<Video> videos = new ArrayList<Video>();
			
		System.out.println("Introduzca su nombre");
		name = sc.nextLine();
		System.out.println("Introduzca su apellido");
		surname = sc.nextLine();
		System.out.println("Introduzca una contraseña");
		password = sc.nextLine();
		Date regisDate = new Date();
		
		User user = new User(name, surname, password, regisDate, videos);
		usersList.add(user);
		
		return user;
	} 
	
	public User chooseUser() {
	Scanner sc = new Scanner (System.in);
	int i = 1, opt;	User user; String response;

		if (usersList.isEmpty()) {
			System.out.println("No existen usuarios. ¿Desea crear uno nuevo? (si/no).");
			response = sc.nextLine().toLowerCase();
			
			if (response.charAt(0) == 's') {
			user = createUser();
			} else {
				user = null;
			}
			
		} else {
			for (User e : usersList) {
				System.out.println(i + " " + e.toString());
				i++;
			} 	
			
			System.out.println("Introduzca el número de usuario");	
			opt = sc.nextInt() - 1;
			user = usersList.get(opt);
		}

	    return user;
	
	}
	
	
	public String viewUserList() {
	String lista = ""; int i = 1;
	
		for (User e : usersList) {
			lista += i + " " + e.toString() + "\n";
			i++;
		} 
		
		return lista;
	}
	
	
	public void addUser(User user) {
		usersList.add(user);
	}
	
	public List getUsersList() {
		return usersList;
	}
	
}
