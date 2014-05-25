package com.laygr;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Juego de una nave que destruye asteroides y cometas
 * 
 * @author Lay
 */
public class GameMain extends JFrame implements KeyListener, MouseListener { 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static DatosJuego datosJuego = new DatosJuego();
	GameCanvas gameCanvas;
	static boolean pause;
	// Define constants for the game
	static final int ANCHURA = 1000; // Ancho del canvas
	static final int ALTURA = 600; // Altura del canvas

	static final long UPDATE_PERIOD = 40; // Milisegundos que se duerme el
											// thread
	static final double FDT = 1000 / UPDATE_PERIOD; // Updates por segundo
	static final int CPS = 8; // Cuadros por segundo
	static final int DDC = (int) 1000 / CPS; // duracion de cuadro
	static ManejaArchivo manejaArchivo = new ManejaArchivo();
	/**
	 * Constructor to initialize the UI components and game objects
	 */
	public GameMain() {
		// Initialize the game objects
		this.gameCanvas = new GameCanvas();
		this.actualizarGameCanvas();
		gameInit();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.pack();
		this.setTitle("Lemon Fiction"); // Titulo de la pantalla
		this.setVisible(true);
		gameStart(); // Se inicia el juego
	}

	/**
	 * All the game related codes here Initialize all the game objects, run only
	 * once in the constructor of the main class.
	 */
	public void gameInit() {
		this.setPreferredSize(new Dimension(ANCHURA, ALTURA));
		this.setSize(new Dimension(ANCHURA, ALTURA));
		this.addKeyListener(this);
		this.gameCanvas.setAmbiente(ResourceManager.cargar(0, gameCanvas));
	}

	// To start and re-start the game.
	public void gameStart() {
		// Create a new thread
		Thread gameThread = new Thread() {
			// Override run() to provide the running behavior of this thread.

			@Override
			public void run() {
				gameLoop();
			}
		};
		// Start the thread. start() calls run(), which in turn calls
		// gameLoop().
		gameThread.start();
	}

	// Run the game loop here.
	private void gameLoop() {
		// Regenerate the game objects for a new game
		// ......

		// Game loop
		while (true) {
			actualizar();
			gameCanvas.actualizar();
			// Refresh the display
			repaint();
			try {
				// Provides the necessary delay and also yields control so that
				// other thread can do work.
				Thread.sleep(UPDATE_PERIOD);
			} catch (InterruptedException ex) {
			}
		}
	}

	/**
	 * Update the state and position of all the game objects, detect collisions
	 * and provide responses.
	 */
	public void actualizar() {

	}

	public void actualizarGameCanvas() {
		this.getContentPane().removeAll();
		this.getContentPane().add(this.gameCanvas);
		this.getContentPane().validate();
		this.requestFocus();
		this.setFocusable(true);
	}

	public void keyReleased(KeyEvent e) {
		this.gameCanvas.keyReleased(e);
	}

	public void keyPressed(KeyEvent e) {
		this.gameCanvas.keyPressed(e);
	}

	public void keyTyped(KeyEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseReleased(MouseEvent e) {

	}

	public void mousePressed(MouseEvent e) {

	}

	public void mouseClicked(MouseEvent e) {

	}

	public static void main(String[] args) {
		// Use the event dispatch thread to build the UI for thread-safety.
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new GameMain();
			}
		});
	}
}
