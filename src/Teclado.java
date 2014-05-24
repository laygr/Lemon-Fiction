/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Teclado virtual. Cada tecla es representada por una variable booleana True
 * para presionado, falso para no presionado.
 * 
 * @author Lay
 */
public class Teclado {
	boolean a;
	boolean s;
	boolean d;
	boolean left;
	boolean right;
	boolean up;
	boolean down;
	boolean espacio;

	public Teclado() {

	}

	public void resetear() {
		a = false;
		s = false;
		d = false;
		left = false;
		right = false;
		up = false;
		down = false;
		espacio = false;
	}
}
