package com.laygr;/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Interfaz que vuelve a los objetos audibles
 * 
 * @author Lay
 */
public interface Audible {
	static final String HERIDO = "HERIDO";

	void anadirSonido(MP3 sonido); // Añadir sonidos

	void reproducirSonido(String nombreDeSonido); // reprouducir sonidos

}
