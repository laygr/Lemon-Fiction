package com.laygr;/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *Clase que define le comportamiento de un enemigo tipo cuchillo
 * 
 * @author Lay
 */
public class Mondadientes extends Enemigo {

	Cronometro ultimoDisparoPalo;

	public Mondadientes() {
		super();
		this.lentitud = 1800;
		vidaMax = 3;
		vida = vidaMax;
		fInternaX *= 3.5;
		ultimoDisparoPalo = new Cronometro();
		ultimoDisparoPalo.empezar();

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
		ultimoDisparoPalo.anadirTiempoTranscurrido(GameMain.UPDATE_PERIOD);
		actuar(juego); // La inteligencia
		actualizarAtaques();
	}

	/**
	 * Metodo que simula la inteligencia del taco
	 * 
	 * @param juego
	 */
	public void actuar(Juego juego) {
		// Si el limon esta muy cerca
		if (Math.abs(distX) < Ambiente.TILE_LADO * 14) {
			teclado.espacio = true;
			teclado.s = true;
		} else {
			teclado.espacio = false;
			teclado.s = false;
		}
		// si esta muy lejos
		// distX es la distancia del cuchillo al limon. Es de la clase Enemigo
		if (Math.abs(distX) > Ambiente.TILE_LADO*15) {
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
		if (distX > 0) { // Si el limon esta del lado derecho
			moverAIzquierda();
		} else {
			moverADerecha();
		}
		if(Math.abs(distX) > Ambiente.TILE_LADO *8){
			if (distX < 0) { // Si el limon esta del lado derecho
				moverAIzquierda();
			} else {
				moverADerecha();
			}
		}
	}

	public Poder setPalo(Poder palo) {
		palo.setY(this.getY() + this.getAltura() * 2 / 3);
		palo.setZ(this.getZ());
		if (this.direccion == Direccion.IZQUIERDA) {
			palo.setX(-this.getX());
			palo.direccion = Direccion.DERECHA;
		} else {
			palo.vX = palo.vX * -1;
			palo.direccion = Direccion.IZQUIERDA;
			palo.setX(this.getX());
		}
		return palo;
	}

	public void actualizarAtaques() {
		if (teclado.espacio) {
			if (ultimoDisparoPalo.contadorDeMilisegundos >= lentitud) {
				ultimoDisparoPalo.contadorDeMilisegundos = 0;
				Poder palo = ResourceManager.crearPoder('P');
				palo = setPalo(palo);
				poderes.add(palo);
			}
		}
	}
}
