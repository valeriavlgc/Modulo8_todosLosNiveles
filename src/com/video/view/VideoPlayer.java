package com.video.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.video.domain.Video;

public class VideoPlayer extends JFrame {
	
	JLabel cronometer, vidTitle;
	Screen screen;
	ButtonsDisplay buttonsDisplay;
	Thread t1, t2, t3;
	int vidDuration, vidDurationCopy;
	String vidTitle1;
	
	public VideoPlayer (int vidDuration, String vidTitle1) {
		
		this.vidDuration = vidDuration;
		vidDurationCopy = vidDuration;
		this.vidTitle1 = vidTitle1;
		
		//Para centrar la pantalla en todos los monitores
		Toolkit screenDisplay =  Toolkit.getDefaultToolkit();
		Dimension screenSize = screenDisplay.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		setSize(screenWidth/2, screenHeight/2);
		setLocation(screenWidth/4, screenHeight/4);
		
				
		//Miniatura
		setIconImage(screenDisplay.getImage("src/com/video/view/icon.png"));
		setLayout(new BorderLayout());
		
		//Lamina JButtons
		buttonsDisplay  = new ButtonsDisplay();
		add(buttonsDisplay, BorderLayout.EAST);
	
		//Lamina pantalla y cronómetro.
		screen = new Screen();
	    add(screen, BorderLayout.CENTER);


		setTitle("Video Player");
		setVisible(true);
		//HIDE_ON_CLOSE para poder continuar con el programa
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		//setLayout(null);
		
	}


class Screen extends JPanel {

public Screen() {
	
		
		setBounds(600, 0, 500, 500);
		
		Font cronoFont = new Font("Arial", Font.BOLD, 30);
		Font titleFont = new Font("Arial", Font.BOLD, 20);
		setLayout(null);
		
		
		
		cronometer = new JLabel("00:00", SwingConstants.CENTER);
		cronometer.setBounds(10, 10, 450, 275);
		cronometer.setFont(cronoFont);
		cronometer.setForeground(Color.BLACK);
		cronometer.setBackground(Color.BLUE);
		cronometer.setOpaque(true);
		this.add(cronometer);
		
	
		vidTitle = new JLabel(vidTitle1, SwingConstants.CENTER);
		vidTitle.setFont(titleFont);
		vidTitle.setBounds(-150, 250, 500, 100);
		vidTitle.setForeground(Color.BLACK);
		vidTitle.setOpaque(true);
		this.add(vidTitle);
	
		setVisible(true);
	
		
		}

}

class ButtonsDisplay extends JPanel {
	
    //ImageIcon playIcon = new ImageIcon("src/com/video/view/playIcon.png");
	JButton play = new JButton("PLAY");
	JButton pause = new JButton("PAUSA");
	JButton stop = new JButton("STOP");
	private int i;
	
	public ButtonsDisplay () {
	
		play.setBounds(500,30, 100, 30);	
		
		play.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				play.setEnabled(false);
				pause.setEnabled(true);
				stop.setEnabled(true);
				actionDone(e);

			}
		});
	

		pause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pause.setEnabled(false);
				play.setEnabled(true);
				stop.setEnabled(true);
				actionDone(e);
			}	
			
		});

		stop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stop.setEnabled(false);
				pause.setEnabled(false);
				play.setEnabled(true);
				actionDone(e);
			}	
			
		});
		
		
		add(play);
		add(pause);
        add(stop);
        
        setVisible(true);

	}
	
	
	
	private void actionDone(ActionEvent e) {
	
		Object source = e.getSource();
			
		if (source == play) {
			if (t2 != null) {
			    t2.interrupt();
			}	
			Runnable r = new Cronometer();
			t1 = new Thread(r);
			//diff. between run and start()?
			t1.start();
			
		 } else if (source == pause) {
			 if (t1 != null) {
				 t1.interrupt();
			 } if (t3 != null) {
				 t3.interrupt();
			 }
			Runnable r = new CronoPause();
			t2 = new Thread(r);
			t2.start();
			
	    } else if (source == stop) {
	    	if (t1 != null) {
				t1.interrupt();
	    	} if (t2 != null) {
				t2.interrupt();
	    	}
	    	Runnable r = new CronoStop();
	    	t3 = new Thread(r);
	    	t3.start();
		
		}
		
	}
		
	
	class Cronometer implements Runnable {
	
	  public void run() {

	  int x = 0;
		
		for (x = vidDurationCopy; x >= 0; x--) {
			
			cronometer.setText("PLAYING: " + x + " seconds left.");
			if (x == 0) {
				cronometer.setText("VIDEO FINISHED");
				}
			
		    try {
				Thread.sleep(1000);
				//se adelante un segundo al reanudarlo, por eso le resto 1.
				vidDurationCopy = x - 1;
			   } catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				//break;
				return;
			  }
		 }

	  }

	}
	
	class CronoPause implements Runnable {
		
	  public void run() {
				
		   cronometer.setText("VIDEO ON PAUSE");
				
	   }
	 }
	   
	class CronoStop implements Runnable {

	   public void run() {
			
		   vidDurationCopy = vidDuration;
		   cronometer.setText("VIDEO STOPPED");
			
		}
		   
	  }

}	

}




