package com.video.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User {

	protected String name;
	protected String surname;
	protected String password; //podría pasar a tipo password. 
	protected Date regisDate;
	protected  List<Video> videos = new ArrayList<Video>();
	
	public User (String name, String surname, String password, Date regisDate, List videos) {
		this.name      = name;
		this.surname   = surname;
		this.password  = password;
		this.regisDate = regisDate;
		this.videos    = videos;
	}
	
	
	public void addVideo(Video video) {
		videos.add(video);	
	}
	
	public List getVideos() {
		return videos;
	}


	@Override
	public String toString() {
		return "User [name=" + name + ", surname=" + surname + ", password=" + password + ", regisDate=" + regisDate
				+ "\n" + "videos=" + videos + "]";
	}

	
	
}
