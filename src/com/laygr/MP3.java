package com.laygr; /*************************************************************************
 *  Compilation:  javac -classpath .:jl1.0.jar MP3.java         (OS X)
 *                javac -classpath .;jl1.0.jar MP3.java         (Windows)
 *  Execution:    java -classpath .:jl1.0.jar MP3 filename.mp3  (OS X / Linux)
 *                java -classpath .;jl1.0.jar MP3 filename.mp3  (Windows)
 *  
 *  Plays an MP3 file using the JLayer MP3 library.
 *
 *  Reference:  http://www.javazoom.net/javalayer/sources.html
 *
 *
 *  To execute, get the file jl1.0.jar from the website above or from
 *
 *      http://www.cs.princeton.edu/introcs/24inout/jl1.0.jar
 *
 *  and put it in your working directory with this file MP3.java.
 *
 *************************************************************************/

import java.io.BufferedInputStream;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

public class MP3 {
	private String filename;
	private Player player;
	private boolean loop;
	String nombre;
	private boolean on = true;

	// constructor that takes the name of an MP3 file
	public MP3(String nombre, boolean loop, String filename) {
		this.nombre = nombre;
		this.filename = filename;
		this.loop = loop;
	}

	public String getNombre() {
		return nombre;
	}

	public void close() {
		if (player != null)
			player.close();
	}

	// play the MP3 file to the sound card
	public void play() {
		if (!on) {
			return;
		}
		try {
			FileInputStream fis = new FileInputStream(ManejaArchivo.getPath(filename));
			BufferedInputStream bis = new BufferedInputStream(fis);
			player = new Player(bis);
		} catch (Exception e) {
			System.out.println(e);
		}
		// run in new thread to play in background
		new Thread() {
			public void run() {
				try {
					player.play();
					if (player.isComplete() && loop) {
						play();
					}
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		}.start();
	}

}
