package com.laygr;

import java.awt.Graphics;
import java.util.ArrayList;

/**
 * Clase cuyos objetos implementan caracteristicas de seres con vida, como la
 * vida restante, fuerza interna en x y y, un teclado virtual para ser
 * controlados.
 * 
 * @author Lay
 */
public class Vivo extends ObjetoReal {

	double vida; // Vida restante
	double vidaMax; // Vida maxima que puede tener
	Cronometro muriendo; // Cronometro que contara el tiempo que lleva muriendo
	Cronometro muerto; // Cronometro que cuenta el tiempo que lleva muerto
	Cronometro ultimoSalto; // Cronometro que contara el tiempo desde que dio el
	// ultimo salto
	double ataque;
	boolean saltando;
	long lentitud; // Milisegundos que se necesitan pasar entre salto y salto
	int midJump; // Cantidad de saltos restantes que se pueden dar en el aire
	int fInternaX; // Fuerza que puede producir en X
	int fInternaY; // Fuerza que puede producir en y
	Teclado teclado; // Teclado virtual con el que se controla
	ArrayList<Poder> poderes; // Poderes creados por este objeto y en espera a
	// ser incorporados por el Juego
	EstadoVivo estadoVivo;
	BarraVida barraVida; // Barra que muestra la vida

	public enum EstadoVivo {

		VIVO, MURIENDO, MUERTO
	}

	/**
	 * Metodo constructor
	 */
	public Vivo() {
		vida = 1;
		lentitud = 500;
		midJump = 0;
		fInternaX = 7;
		fInternaY = -300;
		ultimoSalto = new Cronometro();
		muriendo = new Cronometro();
		muerto = new Cronometro();
		this.teclado = new Teclado();
		ultimoSalto.empezar();
		poderes = new ArrayList<Poder>(0);
		estadoVivo = EstadoVivo.VIVO;
		barraVida = ResourceManager.crearBarraVida(this);
		ataque = 0;
	}

	@Override
	public void actualizar(Juego juego) {
		super.actualizar(juego);
		ultimoSalto.anadirTiempoTranscurrido(GameMain.UPDATE_PERIOD);
		muriendo.anadirTiempoTranscurrido(GameMain.UPDATE_PERIOD);
		muerto.anadirTiempoTranscurrido(GameMain.UPDATE_PERIOD);
		actualizarDireccion();
		actualizarEstadoVivo();
		actualizarMidJump();
		pisar(juego);
		setFX();
		setFY();
		if(suelo!=null){
			if(suelo.mortal){
				this.vida = 0;
				this.setX(xInicial);
				this.setY(yInicial);
			}
		}
	}

	public void actualizarEstadoVivo() {
		if (this.vida <= 0 && estadoVivo == EstadoVivo.VIVO) {
			this.vX = 0;
			this.vY = 0;
			quitarVida();
			teclado.resetear();
			setEstado("MURIENDO");
			this.reproducirSonido("MURIENDO");
			estadoVivo = EstadoVivo.MURIENDO;
			muriendo.empezar();
		}
		if (this.muriendo.contadorDeMilisegundos > 10 * GameMain.UPDATE_PERIOD) {
			this.destruirse = true;
			setEstado("MUERTO");
			estadoVivo = EstadoVivo.MUERTO;
			muerto.empezar();
			muriendo.resetear();
		}
	}

	public void danar(double ataque) {
		this.vida -= ataque;
		if (vida < 0) {
			vida = 0;
		}
	}

	public void actualizarDireccion() {
		if (suelo == null) {
			if (teclado.right) {
				direccion = Direccion.DERECHA;
			} else if (teclado.left) {
				direccion = Direccion.IZQUIERDA;
			}

		} else {

			if (teclado.right && direccion != Direccion.DERECHA) {
				direccion = Direccion.DERECHA;
				this.reproducirSonido("PATINANDO.wav");
			} else if (teclado.left && direccion != Direccion.IZQUIERDA) {
				direccion = Direccion.IZQUIERDA;
				this.reproducirSonido("PATINANDO.wav");
			}
			if (teclado.d == false && teclado.espacio == false) {
				if (vX > 0) {
					direccion = Direccion.DERECHA;
				} else if (vX < 0) {
					direccion = Direccion.IZQUIERDA;
				}
			}
		}
	}

	public void actualizarMidJump() {
		if (this.suelo != null) {
			midJump = 1;
		}
	}

	public void setFX() {
		if (estadoVivo != EstadoVivo.VIVO) {
			this.fX = 0;
			return;
		}
		if (teclado.right) {
			if (suelo != null) {
				this.fX = this.fInternaX;
			} else {
				this.fX = this.fInternaX / 2;
			}
			if (teclado.a) {
				this.fX *= 1.3;
			}
			return;
		}
		if (teclado.left) {
			if (suelo != null) {
				this.fX = -this.fInternaX;
			} else {
				this.fX = -this.fInternaX / 2;
			}
			if (teclado.a) {
				this.fX *= 1.5;
			}
			return;
		}
		this.fX = 0;
	}
	
	public void quitarVida(){
		if(this.getClass() == Limon.class){
			GameMain.datosJuego.vidas --;
		}
	}
	public void setFY() {
		if (vida == 0) {
			this.fY = 0;
			return;
		}
		if (!teclado.s) {
			ultimoSalto.contadorDeMilisegundos = lentitud + 1;
		}
		if (teclado.s || saltando) {
			if (ultimoSalto.contadorDeMilisegundos > lentitud) {
				ultimoSalto.contadorDeMilisegundos = 0;
				saltar();
				saltando = false;
				return;
			}
		}
		this.fY = 0;
	}

	public void saltar() {
		if (midJump > 0 || suelo != null) {
			this.vY = 0;
			if (suelo == null) {
				midJump--;
			}
			this.fY = this.fInternaY;
			if (teclado.a) {
				this.fY *= 1.3;
			}
		}
	}

	public void pisar(Juego juego) {
		Vivo vivo;
		if (this.vY < 0) {
			return;
		}
		for (int i = 0; i < juego.enemigos.size(); i++) {
			vivo = juego.enemigos.get(i);
			if (this.getY() + this.getAltura() < vivo.getY()) {
				if (this.getY() + this.getAltura() - vivo.getY() > -50) {
					if (this.getX() + this.getAnchura() > vivo.getX()
							&& this.getX() < vivo.getX() + vivo.getAnchura()) {
						this.midJump = 1;
						saltando = true;
						vivo.reproducirSonido("PISADO");
						vivo.danar(this.ataque);
						return;
					}
				}
			}
		}
	}

	@Override
	public void dibujarse(Graphics g, GameCanvas gameCanvas) {
		super.dibujarse(g, gameCanvas);
		barraVida.dibujarse(g, gameCanvas);
	}
}
