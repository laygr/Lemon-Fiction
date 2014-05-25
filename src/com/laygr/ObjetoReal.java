package com.laygr;/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

/**
 * Objetos "reales" de una aplicacion. Implementa carácterísticas fisicas como
 * gravedad, masa, velocidad en x y y, etc.
 * 
 * @author Lay
 */
public class ObjetoReal extends PiezaJuego implements Movible {

	Direccion direccion = Direccion.DERECHA;
	boolean destruirse; // Indica si se debe de eliminar este objeto, ya sea por
						// una colision o por que murio

	public enum Direccion {
		DERECHA, IZQUIERDA;
	}

	double gravedad;
	protected double fY;
	protected double fX;
	double vY; // Velocidad en Y
	double vX; // Velocidad en X
	double vM; // Velocidad maxima
	double masa;
	protected Plataforma suelo;

	/**
	 *Crea un objeto real de posicion, tamano y espacio ocupado iguales a 0.
	 */
	public ObjetoReal() {
		super();
		this.suelo = null;
		this.gravedad = 0;
		this.fX = 0;
		this.fY = 0;
		this.masa = 0;
		vM = Ambiente.TILE_LADO - 1; // Velocidad Maxima
		vY = 0;
		vX = 0;
		estados = new ArrayList<Estado>(0);
		sonidos = new ArrayList<MP3>(0);
	}

	public void actualizar(Juego juego) {
		super.actualizar(juego);
		actualizarEstados(GameMain.UPDATE_PERIOD);
		suelo = getSuelo(juego.plataformas);
		this.vX = getVX();
		this.vY = getVY();
		moverEnX((int) this.vX, juego);
		moverEnY((int) this.vY, juego);
	}

	public Plataforma getSuelo(ArrayList<Plataforma> plataformas) {
		Plataforma plat;
		for (int i = 0; i < plataformas.size(); i++) {
			plat = plataformas.get(i);
			if (this.getY() + this.getAltura() == plat.getY()) {
				if (this.getX() + this.getAnchura() > plat.getX()
						&& this.getX() < plat.getX() + plat.getAnchura()) {
					return plat;
				}
			}
		}
		return null;
	}

	public double getFriccion() {
		if (suelo == null) {
			return 0;
		}
		return masa * gravedad * suelo.miu * (vX > 0 ? -1 : vX < 0 ? 1 : 0);
	}

	public double getFTY() {
		return fY;
	}

	public double getFTX() {
		double ftx;
		if (fX == 0) {
			return getFriccion();
		}
		double theta = Math.atan((fX - getFriccion()) / vM);
		ftx = (vM - Math.abs(vX)) * Math.tan(theta) + getFriccion();
		if (ftx / this.vX < 0) {
			ftx *= 3;
		}
		return ftx;
	}

	public double getAY() {
		return (getFTY() / masa) + (suelo == null ? gravedad : 0);
	}

	public double getAX() {
		return getFTX();
	}

	public double getVX() {
		double vXAux = getAX() / GameMain.FDT + this.vX;
		if (Math.abs(vXAux) < 1 && fX == 0) {
			return 0;
		}
		return vXAux;
	}

	public double getVY() {
		double vYAux = getAY() / GameMain.FDT + this.vY;
		return vYAux;
	}

	/**
	 * Método que mueve al objeto en Y
	 * 
	 * @param pixeles
	 *            numero de pixeles a mover, ya sean positivo o negativos
	 */
	public void moverEnY(int pixeles, Juego juego) {
		if (pixeles == 0) {
			return;
		}
		int posicionInicial = this.getY();
		this.setY(posicionInicial + pixeles);
		Plataforma colision = getColision(juego.plataformas);
		if (colision != null) {
			if (colision.getY() >= posicionInicial + this.getAltura()) {
				this.setY(colision.getY() - this.getAltura());
			} else {
				this.setY(colision.getY() + this.getAltura());
			}
			this.vY = 0;
		}
	}

	/**
	 * Método que mueve al objeto en X
	 * 
	 * @param pixeles
	 *            numero de pixeles a mover, ya sean positivo o negativos
	 */
	public void moverEnX(int pixeles, Juego juego) {
		if (pixeles == 0) {
			return;
		}
		int posicionInicial = this.getX();
		this.setX(posicionInicial + pixeles);
		Plataforma colision = getColision(juego.plataformas);
		if (colision != null) {
			if (colision.getX() >= posicionInicial + this.getAnchura()) {
				this.setX(colision.getX() - this.getAnchura());
			} else {
				this.setX(colision.getX() + colision.getAltura());
			}
			this.vX = 0;
		}

	}

	/**
	 * Detecta colisiones de este objeto con alguna plataforma de las del
	 * parametro
	 * 
	 * @param plataformas
	 *            Lista de plataformas
	 * @return regresa la plataforma con la que colisiona o null.
	 */
	public Plataforma getColision(ArrayList<Plataforma> plataformas) {
		Plataforma pAux;
		Rectangle interseccion;
		for (int i = 0; i < plataformas.size(); i++) {
			pAux = plataformas.get(i);
			if (this.getEspacioOcupado().intersects(pAux.getEspacioOcupado())) {
				interseccion = this.getEspacioOcupado().intersection(
						pAux.getEspacioOcupado());
				if (interseccion.height < this.getAltura() + pAux.getAltura()
						|| interseccion.width < this.getAnchura()
								+ pAux.getAnchura()) {
					return pAux;
				}
			}
		}
		return null;
	}

	/**
	 * Método que regresa un Rectanglo que representa el espcaio ocupado por el
	 * objeto
	 * 
	 * @return Rectangulo
	 */
	public Rectangle getEspacioOcupado() {
		Rectangle espacioOcupado = new Rectangle();
		espacioOcupado.width = getAnchura();
		espacioOcupado.height = getAltura();
		espacioOcupado.setLocation(new Point(getX(), getY()));
		return espacioOcupado;
	}

	@Override
	public void dibujarse(Graphics g, GameCanvas gameCanvas) {
		Camara camara; // Camara con la cual se calculara la perspectiva
		int xP; // la posicion en x calculando la perspectiva
		int yP; // la posicion en y calculando la perspectiva
		int anchuraP; // la anchura calculando la perspectiva
		int alturaP; // la altura calculando la perspectiva

		camara = gameCanvas.camara;
		xP = camara.getX(this);
		yP = camara.getY(this);
		anchuraP = camara.getAnchura(this);
		alturaP = camara.getAltura(this);

		if (direccion == Direccion.IZQUIERDA) {
			anchuraP *= -1;
			xP -= anchuraP;
		}
		g.drawImage(this.getImage(), xP, yP, anchuraP, alturaP, gameCanvas);
		if (this.getClass() == Vivo.class) {
			((Vivo) this).barraVida.dibujarse(g, gameCanvas);
		}
	}
}
