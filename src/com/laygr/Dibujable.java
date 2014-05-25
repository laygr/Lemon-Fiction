package com.laygr;/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Graphics;

/**
 * Interfaz para crear objetos que se dibujan
 * 
 * @author Lay
 */
public interface Dibujable {

	void dibujarse(Graphics g, GameCanvas gameCanvas);
}
