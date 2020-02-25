package main;

import java.applet.Applet;
import java.applet.AudioClip;

public class Sound {

	private AudioClip clip;
	public static final Sound musicBackground = new Sound("/themesongpasteldeflango.wav");
	public static final Sound swallow = new Sound("/swallow.wav");
	private Sound(String name) {
		try {
			clip = Applet.newAudioClip(Sound.class.getResource(name));
		}catch(Throwable e) {}
			
	}
	
	public void play() {
		try {
			new Thread() {
				public void run() {
					clip.play();
				}
			}.start();
		}catch(Throwable e) {}	
	}
	
	public void loop() {
		try {
			new Thread() {
				public void run() {
					clip.loop();
				}
			}.start();
		}catch(Throwable e) {}	
	}
	
	public void stop() {
		try {
			new Thread() {
				public void run() {
					clip.stop();
				}
			}.start();
		}catch(Throwable e) {}	
	}
}
