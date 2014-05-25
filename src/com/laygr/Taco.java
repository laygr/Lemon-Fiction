package com.laygr;/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *Clase que define le comportamiento de un enemigo tipo cuchillo
 * 
 * @author Lay
 */
public class Taco extends Enemigo {

	Cronometro ultimoDisparoCarne;

	public Taco() {
		super();
		ataque=.5;
		vidaMax = 10;
		vida = vidaMax;
		ultimoDisparoCarne = new Cronometro();
		ultimoDisparoCarne.empezar();

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
		ultimoDisparoCarne.anadirTiempoTranscurrido(GameMain.UPDATE_PERIOD);
		actuar(juego); // La inteligencia
		actualizarAtaques();
		checaGolpeLimon(juego.limon);
	}
	
	/**
	 * Metodo que checa si el limon ha sido atacado
	 * 
	 * @param limon
	 */
	public void checaGolpeLimon(Limon limon) {
		if (this.getEspacioOcupado().intersects(limon.getEspacioOcupado())) {
			limon.danar(this.ataque);
		}
	}

	/**
	 * Metodo que simula la inteligencia del taco
	 * 
	 * @param juego
	 */
	public void actuar(Juego juego) {
		// si esta muy lejos
		// distX es la distancia del taco al limon. Es de la clase Enemigo
		if (Math.abs(distX) > 700) {
			actuarCuandoLejos();
		} else {
			actuarCuandoCerca();
		}
	}

	/**
	 * Metodo sobre como debe actuar el taco cuando esta lejos del limon. Solo
	 * hace que el taco ande alrededor del punto en x en el que fue creado
	 */
	private void actuarCuandoLejos() {
		int distXAInicio; // Distancia de la posicion actual al punto de inicio
		distXAInicio = this.getX() - xInicial; // xInicial es una variable que
												// hereda de la clase plano
		if (Math.abs(distXAInicio) > 100) {
			if (direccion == Direccion.DERECHA) {
				if (distXAInicio > 0) {
					cambiarDireccion();
				}
			} else {
				if (distXAInicio < 0) {
					cambiarDireccion();
				}
			}
		}
	}

	/**
	 * Metodo sobre como debe actuar el taco cuando esta cerca del limon
	 */
	private void actuarCuandoCerca() {
		// Si el limon esta muy cerca
		teclado.espacio = true;
		if (distX > 0) { // Si el limon esta del lado derecho
			moverADerecha();
		} else {
			moverAIzquierda();
		}
	}

	public Poder setCarne(Poder carne) {
		carne.setY(this.getY() + this.getAltura() / 2);
		carne.setZ(this.getZ());
		if (this.direccion == Direccion.IZQUIERDA) {
			carne.setX(this.getX() - carne.getAnchura());
			carne.vX = carne.vX * -1;
			carne.direccion = Direccion.IZQUIERDA;
		} else {
			carne.setX(this.getX() + this.getAnchura());
		}
		return carne;
	}

	public void actualizarAtaques() {
		if (teclado.espacio) {
			if (ultimoDisparoCarne.contadorDeMilisegundos >= lentitud * 2) {
				ultimoDisparoCarne.contadorDeMilisegundos = 0;
				Poder carne = ResourceManager.crearPoder('C');
				carne = setCarne(carne);
				poderes.add(carne);
			}
		} else {
			ultimoDisparoCarne.contadorDeMilisegundos = lentitud * 2;
		}
	}
}
