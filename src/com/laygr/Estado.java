package com.laygr;/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Image;

/**
 *Un estado indica si un objeto se esta moviendo, saltando, cayendo, etc...
 * 
 * @author Lay
 */
public class Estado {
	private String nombre; // El nombre del estado
	private Image imagen; // la imagen que representa al estado
	private Animation animacion; // la animacion que contiene las imagenes que
									// representan al objeto segun el tiempo
	private int anchura; // Anchura del estado
	private int altura; // Altura del estado

	/**
	 * Crea un estado
	 * 
	 * @param nombre
	 *            nombre del estado
	 */
	private Estado(String nombre) {
		this.nombre = nombre;
		this.anchura = 0;
		this.altura = 0;
	}

	/**
	 * Crea un estado con nombre y una imagen que lo represente
	 * 
	 * @param nombre
	 *            nombre del estado
	 * @param image
	 *            imagen del estado
	 */
	public Estado(String nombre, Image image) { // Metodo constructor
		this(nombre);
		this.imagen = image;
		this.animacion = null;
	}

	/**
	 * Crea un estado con nombre y una animacion que lo represente
	 * 
	 * @param nombre
	 *            nombre del estado
	 * @param animacion
	 *            imagen del estado
	 */
	public Estado(String nombre, Animation animacion) {
		this(nombre);
		this.imagen = null;
		this.animacion = animacion;
	}

	/**
	 * En caso de que la animacion no sea null, se manda a actualizar
	 * 
	 * @param tiempoTranscurrido
	 *            Timepo transcurrido desde la ultima vez que se actualizo la
	 *            animacion
	 */
	public void actualizarAnimacion(long tiempoTranscurrido) {
		if (this.animacion != null) {
			this.animacion.update(tiempoTranscurrido);
		}
	}

	/**
	 * Método que establece la anchura del estado
	 * 
	 * @param anchura
	 *            - anchura en pixeles
	 */
	public void setAnchura(double tiles) {
		this.anchura = (int) (Ambiente.TILE_LADO * tiles);
	}

	/**
	 * Método que establece la altura del estado
	 * 
	 * @param altura
	 *            - altura en pixeles
	 */
	public void setAltura(double tiles) {
		this.altura = (int) (Ambiente.TILE_LADO * tiles);
	}

	/**
	 * regresa la altura de este estado
	 * 
	 * @return Altura
	 */
	public int getAltura() {
		return this.altura;
	}

	/**
	 * regresa la anchura de este estado
	 * 
	 * @return Anchura
	 */
	public int getAnchura() {
		return this.anchura;
	}

	/**
	 * regresa el nombre de este estado
	 * 
	 * @return Nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * regresa la imagen que representa a este estado, dependiendo si tiene
	 * animacion o imagen
	 * 
	 * @return Imagen
	 */
	public Image getImage() {
		if (this.animacion != null) {
			return animacion.getImage();
		}
		return imagen;
	}

}
