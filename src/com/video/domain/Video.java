package com.video.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Video {
	
	enum State{STOPPED,PAUSED,PLAYING};
	enum UploadState {NULL,UPLOADING, VERIFYING, PUBLIC};
	
	protected String url;
	protected String title;
	protected List<Tag> taglist = new ArrayList<Tag>();
	protected Date uploadDate;
	protected int vidMinute;
	protected String name;
	protected State state;
	protected UploadState uploadState;
	protected int duration;
	protected int actualTime;
	
		
	public Video (String url, String title, List taglist, int duration) {
		this.url                = url;
		this.title              = title;
		this.taglist            = taglist;
		this.duration           = duration;
		this.uploadDate         = new Date();
		LocalDateTime getMinute = LocalDateTime.now();
		this.vidMinute          = getMinute.getMinute();
		this.uploadState        = uploadState;
		
	}
	
	public int getVidMinute () {
		return vidMinute;
	}
	
	public int getDuration() {
		return duration;
	}

	public String getStateVid() {
	LocalDateTime now = LocalDateTime.now();
	int minute = now.getMinute();
	int dif = minute - this.vidMinute; String name = null;
		
		if (dif <= 1) {
		    this.uploadState =  uploadState.UPLOADING;
		    name = "UPLOADING...";
		} else if (dif > 1 && dif < 3) {
			this.uploadState = uploadState.VERIFYING;
			name = "VERIFYING...";
		} else if (dif > 3) {
			this.uploadState = uploadState.PUBLIC;
			name = "PUBLIC";
		}
	
		return name;
	}
	
	public String getTitle() {
		return title;
	}
	
	


	public String toString() {
		return "Video status: " + getStateVid() + " [url=" + url + ", title=" + title + ", taglist=" + taglist + 
				", uploadDate=" + uploadDate + ", duration(s)= " + duration + "]";
	}

}
