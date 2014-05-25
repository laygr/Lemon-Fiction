package com.laygr;/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *Clase que define le comportamiento de un enemigo tipo cuchillo
 * 
 * @author Lay
 */
public class Cuchillo extends Enemigo {
	public Cuchillo() {
		super();
		ataque = 1;
		vidaMax = 1;
		vida = vidaMax;
	}

	/**
	 * Llama la actualizacion de la super clase y luego actualiza todo lo
	 * relacionado con esta clase.
	 * 
	 * @param juego
	 */

	@Override
	public void actualizar(Juego juego) {
		super.actualizar(juego);
		actuar(juego); // La inteligencia
		checaGolpeLimon(juego.limon); // Que pasa cuando toca al limon
	}

	/**
	 * Metodo que simula la inteligencia del cuchillo
	 * 
	 * @param juego
	 */
	public void actuar(Juego juego) {
		// si esta muy lejos
		// distX es la distancia del cuchillo al limon. Es de la clase Enemigo
		if (Math.abs(distX) > 200) {
			actuarCuandoLejos(juego);
		} else {
			actuarCuandoCerca();
		}
	}

	/**
	 * Metodo sobre como debe actuar el cuchillo cuando esta lejos del limon.
	 * Solo hace que el cuchillo ande alrededor del punto en x en el que fue
	 * creado
	 */
	private void actuarCuandoLejos(Juego juego) {
		int distXAInicio; // Distancia de la posicion actual al punto de inicio
		distXAInicio = this.getX() - xInicial - juego.getX(); // xInicial es una
																// variable que
		// hereda de la clase plano
		if (distXAInicio > 300) {
			moverAIzquierda();
		} else if (distXAInicio < -300) {
			moverADerecha();
		}
	}

	/**
	 * Metodo sobre como debe actuar el cuchillo cuando esta cerca del limon
	 */
	private void actuarCuandoCerca() {
		if (distY > 0) {
			teclado.s = false;
		} else {
			teclado.s = true;
		}
		// Si el limon esta muy cerca
		if (Math.abs(distX) < Ambiente.TILE_LADO * 3) {
			// Ataca
			teclado.espacio = true;
		} else {
			teclado.espacio = false;
		}
		if (distX > 0) { // Si el limon esta del lado derecho
			moverADerecha();
		} else {
			moverAIzquierda();
		}
	}

	/**
	 * Metodo que checa si el limon ha sido atacado
	 * 
	 * @param limon
	 */
	public void checaGolpeLimon(Limon limon) {
		// Si el estado de este objeto no es atacando
		if (this.estadoEnemigo != EstadoEnemigo.ATACANDO) {
			// termina el metodo
			return;
		}
		if (this.getEspacioOcupado().intersects(limon.getEspacioOcupado())) {
			limon.danar(this.ataque);
		}
	}

}
