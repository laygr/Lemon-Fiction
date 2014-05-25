package com.laygr;/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Graphics;
import java.awt.Image;

/**
 * Un plano con una imagen.
 * 
 * @author Lay
 */
public class Fondo extends Plano {
	Image imagen;

	/**
	 * Crea un fondo
	 * 
	 * @param imagen
	 *            Imagen que representa el fondo.
	 * @param anchura
	 *            anchura del fondo en pixeles
	 * @param altura
	 *            altura del fonde en pixeles
	 * @param z
	 *            distancia del fondo en z
	 */
	public Fondo(Image imagen, int anchura, int altura, int z) {
		super();
		this.imagen = imagen;
		this.setAnchura(anchura);
		this.setAltura(altura);
		this.setZ(z);
	}

	/**
	 * Metodo que dibuja este fondo en el gameCanvas
	 * 
	 * @param g
	 *            graficos en los que se dibuja
	 * @param gameCanvas
	 *            gameCanvas que contiene la camara que se usara para posicionar
	 *            este plano. El gameCavas tambien servira como ImageObserver
	 */
	public void dibujarse(Graphics g, GameCanvas gameCanvas) {
		Camara camara = gameCanvas.camara;

		g.drawImage(imagen, camara.getX(this), camara.getY(this), camara
				.getAnchura(this), camara.getAltura(this), gameCanvas);
	}

}
