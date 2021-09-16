package com.video.application;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.JOptionPane;
import com.video.domain.Tag;
import com.video.domain.User;
import com.video.domain.Video;
import com.video.view.VideoPlayer;

public class VideoController {

	static List<Video> videosList = new ArrayList<Video>();
	
	public static Video createVideo(User user) {
		Scanner sc = new Scanner(System.in);
		String url, title, tagString; int duration;
		List<Tag> taglist = new ArrayList<Tag>();
		String response; int i = 1;
	
		System.out.println("Introduzca la URL de su video");
		url = sc.nextLine();
		System.out.println("Introduzca el título de su video");
		title = sc.nextLine();
		System.out.println("Introduzca la duración del video en segundos");
		duration = sc.nextInt();
		sc.nextLine();
		
		System.out.println("¿Quiere añadir tags a su video?");
		response = sc.nextLine();
		response.toLowerCase();
		
		while (response.charAt(0) == 's') {
			System.out.println("Introduzca el tag " + (i) + ":");
			tagString = sc.nextLine();
			Tag tag = new Tag(tagString);
			taglist.add(tag);
			i++;
			System.out.println("¿Desea añadir más tags?");
			response = sc.nextLine();
		}
		
        
		Video video = new Video(url, title, taglist, duration);
		
		user.addVideo(video);
		videosList.add(video);
		
		return video;
	}
	
	public static String seeVideosList(User user) {
		String list = "";
		List<Video> videos = user.getVideos();
		
		if (user.getVideos().isEmpty()) {
			System.out.println("El usuario no tiene videos.");
		}
		
		for (Video e : videos) {
			list += e.toString() + "\n";
		}

		return list;
		
	}
	
	public static String seeVideos() {
		String list = "";

		if (videosList.isEmpty()) {
			list = "No hay videos registrados.";
		}
		
		for (Video e : videosList) {
			list += e.toString() + "\n";
		}

		return list;
	}
	
	public static Video chooseVideo() {
		Scanner sc = new Scanner (System.in);
		int i = 1, opt; Video video = null;

			if (videosList.isEmpty()) {
				System.out.println("No existen videos.");				
			} else {
				for (Video e : videosList) {
					System.out.println(i + e.toString());
					i++;
				} 	
				
				System.out.println("Introduzca el número de video");	
				opt = sc.nextInt() - 1;
				video = videosList.get(opt);
			}

		    return video;
	}
	

	public static void videoPlayer(Video video) {
		int duration = video.getDuration();
		String vidTitle = video.getTitle();
		String vidStatus = video.getStateVid();
		
		if (vidStatus.equalsIgnoreCase("PUBLIC")) {
			VideoPlayer videoplayer = new VideoPlayer(duration, vidTitle);
		} else {
			//No funciona JOptionPane.showMessageDialog(null, "MENSAJE");
			System.out.println("El estado del video es: " + vidStatus + ". Espere unos minutos.");
		}
	
		
	}
	
	
	
	
	
}
