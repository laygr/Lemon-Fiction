package com.laygr;

/**
 * 
 * @author Lay
 */
public class Plano {

	private int x; // Posicion en x
	private int y; // Posicion en y
	private int z; // Posicion en z
	private int anchura;
	private int altura;
	int yInicial;// La posicion en y donde la pieza fue colocada por ultima vez con el metodo setY
	int xInicial; // La posicion en x donde la pieza fue colocada por ultima vez con el metodo setX

	public Plano() {
		this.x = 0;
		this.y = 0;
		this.z = 0;
		this.anchura = 0;
		this.altura = 0;
	}

	public void setX(int pixeles) {
		this.x = pixeles;
	}

	public void setY(int pixeles) {
		this.y = pixeles;
	}

	public void setZ(int pixeles) {
		this.z = pixeles;
	}

	public void setAltura(int pixeles) {
		altura = pixeles;
	}

	public void setAnchura(int pixeles) {
		anchura = pixeles;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public int getZ() {
		return this.z;
	}

	public int getAltura() {
		return altura;
	}

	public int getAnchura() {
		return anchura;
	}
}