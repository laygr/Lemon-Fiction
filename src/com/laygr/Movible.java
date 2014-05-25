package com.laygr;/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * 
 *Interfaz para hacer movibles a los objetos
 * 
 * @author Lay
 */
public interface Movible {

	double getVY();

	double getVX();

	double getAX();

	double getAY();

	void moverEnX(int pixeles, Juego juego);

	void moverEnY(int pixeles, Juego juego);

}
