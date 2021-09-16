package com.video.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import java.util.Date;
import com.video.application.UserController;
import com.video.application.VideoController;
import com.video.domain.User;
import com.video.domain.Video;

public class VideoApp {

	public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);
	int option; boolean fin = false; Date date1 = new Date();
    User user1 = null;
	UserController usController = new UserController();
	List<Video> videos = new ArrayList<Video>();
    User user = new User("Valeria", "Lavin", "password1", date1, videos);
    usController.addUser(user);
   		

		do {
			System.out.println("Introduzca una opción: 1.- Crear Usuario 2.- Ver Usuarios 3.- Menú videos 0.- Salir del menú");
			option = sc.nextInt();
		switch (option) {
			case 0 : 
				System.out.println("Adiós!");
				fin = true;
				break;
			case 1:
				user1 = usController.createUser();
				
				break;
			case 2:
				String list = usController.viewUserList();
				System.out.println(list);
				break;
				
			case 3:
				menuVideos(usController);
				break;

		    default:
		    	System.out.println("opción no válida");
	    }
		
		} while (!fin);
			

   } 

public static void menuVideos(UserController usController) {
Scanner sc = new Scanner(System.in);	
VideoController controller = new VideoController();
int option; boolean fin = false; User user; Video video;
String message = null;


		do {
			System.out.println("Introduzca una opción: 1.- Crear Video 2.- Ver todos los videos 3.- Ver videos de usuario  4.- Reproducir video 0.- Salir del menú");
			option = sc.nextInt();
		switch (option) {
			case 0 : 
				System.out.println("Adiós!");
				fin = true;
				break;
			case 1:
				user = usController.chooseUser();
				if (user != null) {
				video = controller.createVideo(user);
				}
				break;
			case 2:
				String videos = controller.seeVideos();
				System.out.println(videos);
				break;
			case 3:
				user = usController.chooseUser();
				//cabe posibilidad de que no se obtenga usuario. 
				if (user != null) {
				controller.seeVideosList(user);
				}
				break;
			case 4:
				 video = controller.chooseVideo();
				 if (video != null) {
				 controller.videoPlayer(video);
				 }
				break;
		    default:
		    	System.out.println("opción no válida");
		}
		
		} while (!fin);
			

}	
	
	

}


