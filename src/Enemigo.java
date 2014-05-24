/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * 
 * @author Lay
 */
public abstract class Enemigo extends Vivo {

	EstadoEnemigo estadoEnemigo;
	int distX; // Distancia en x al limon
	int distY; // Distancia en y al limon

	public Enemigo() {
		moverADerecha();
	}

	/**
	 * Estados que todos los enemigos deben de tener
	 */
	public enum EstadoEnemigo {
		PARADO, CAMINANDO, CORRIENDO, ATACANDO
	}

	/**
	 * Actualiza todo lo que concierne a la clase enemigo
	 * 
	 * @param juego
	 *            Juego en el que se encuentra el enemigo
	 */
	@Override
	public void actualizar(Juego juego) {
		super.actualizar(juego);
		actualizarEstadoEnemigo();
		distX = juego.limon.getX() - getX();
		distY = juego.limon.getY() - getY();
	}

	/**
	 * Cambia la direccion en la que camina el enemigo;
	 */
	public void cambiarDireccion() {
		if (direccion == Direccion.DERECHA) {
			moverAIzquierda();
		} else if (direccion == Direccion.IZQUIERDA) {
			moverADerecha();
		}
	}

	/**
	 * Mueve el enemigo a la derecha
	 */
	public void moverADerecha() {
		direccion = Direccion.DERECHA;
		teclado.right = true;
		teclado.left = false;
	}

	/**
	 * Mueve el enemigo a la izquierda
	 */
	public void moverAIzquierda() {
		direccion = Direccion.IZQUIERDA;
		teclado.right = false;
		teclado.left = true;
	}

	/**
	 * se actualiza el estado del enemigo segun su teclado y velocidad en x.
	 */
	public void actualizarEstadoEnemigo() {
		if (estadoVivo != EstadoVivo.VIVO) {
			return;
		}
		if (Math.abs(this.vX) > 0) {
			setEstado("CAMINANDO");
			estadoEnemigo = EstadoEnemigo.CAMINANDO;
		}
		if (this.vX == 0) {
			setEstado("PARADO");
			estadoEnemigo = EstadoEnemigo.PARADO;
		}
		if (this.teclado.espacio) {
			setEstado("ATACANDO");
			estadoEnemigo = EstadoEnemigo.ATACANDO;
		}

	}
}
