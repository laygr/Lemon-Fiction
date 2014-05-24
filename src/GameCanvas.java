/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 * Lleva a cabo el dibujado y actualizacion de los fondos, de la camara y del
 * ambiente
 * 
 * @author Lay
 */
public class GameCanvas extends JPanel implements KeyListener {

	Camara camara;
	ArrayList<Fondo> fondos;
	private Ambiente ambiente;
	int dibujar = 0;

	/**
	 * Construye un GameCanvas
	 */
	public GameCanvas() {
		fondos = new ArrayList<Fondo>(0);
		camara = new Camara(GameMain.ANCHURA, GameMain.ALTURA);
	}

	public void setAmbiente(Ambiente ambiente) {
		this.ambiente = ambiente;
		camara.setFoco(ambiente.getFoco());
		camara.actualizarZ();
	}

	/**
	 * Actualiza el game canvas. Primero actuliza el ambiente y despues la
	 * camara
	 */
	public void actualizar() {
		if(GameMain.pause){
			return;
		}
		ambiente.actualizar();
		camara.actualizarXY();
		if (ambiente.avanzar) {
			if (ambiente.cancion != null) {
				ambiente.cancion.close();
			}
			setAmbiente(ResourceManager.cargar(ambiente.siguiente, this));
		}
	}

	/**
	 * Anade un fondo al ArrayList de fondos
	 * 
	 * @param fondo
	 *            Fondo a anadir
	 */
	public void anadirFondo(Fondo fondo) {
		if (fondos.size() == 0) {
			fondos.add(fondo);
			return;
		}
		for (int i = 0; i < fondos.size(); i++) {
			if (fondos.get(i).getZ() > fondo.getZ()) {
				fondos.add(i, fondo);
				return;
			}
		}
		fondos.add(fondo);

	}

	public Ambiente ambiente() {
		return ambiente;
	}

	/**
	 * Maneja los KeyEvents
	 * 
	 * @param e
	 *            KeyEvent escuchado por el KeyListener
	 */
	public void keyReleased(KeyEvent e) {
		this.ambiente.keyReleased(e);
	}

	/**
	 * Maneja los KeyEvents
	 * 
	 * @param e
	 *            KeyEvent escuchado por el KeyListener
	 */
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_G) {
			GameMain.manejaArchivo.save(this, GameMain.datosJuego);
			if(ambiente.getClass() == Juego.class){
			GameMain.pause = !GameMain.pause;
			}
		}
		this.ambiente.keyPressed(e);
	}

	/**
	 * Maneja los KeyEvents
	 * 
	 * @param e
	 *            KeyEvent escuchado por el KeyListener
	 */
	public void keyTyped(KeyEvent e) {
	}

	/**
	 * Dibuja los objetos en este GameCanvas primero los fondos y despues el
	 * ambiente
	 * 
	 * @param g
	 *            Graficos en que dibujar
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// paint background
		for (int i = 0; i < fondos.size(); i++) {
			fondos.get(i).dibujarse(g, this);
		}
		// Draw the game objects
		this.ambiente.dibujarse(g, this);
	}
}
