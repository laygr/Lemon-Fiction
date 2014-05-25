package com.laygr;

import java.awt.Rectangle;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * 
 * @author Lay
 */
public class Plataforma extends PiezaJuego {
	double miu;
	boolean mortal;

	public Plataforma() {
		miu = 0;
	}

	public Rectangle getEspacioOcupado() {
		Rectangle rect = new Rectangle();
		rect.width = this.getAnchura();
		rect.height = this.getAltura();
		rect.setLocation(this.getX(), this.getY());
		return rect;
	}

	public void actualizar(Juego juego) {
		super.actualizar(juego);
	}

}
