package com.laygr;

import java.util.ArrayList;

/**
 * Lo que tiene la habilidad de dañar seres vivos (Jugo, semillas,
 * mondadientes)
 * 
 * @author Lay
 */
public class Poder extends ObjetoReal {
	static final String estadoNormal = "NORMAL";

	Cronometro tiempoRestante;
	Class claseObjetivo;
	double danoPotencial;

	public Poder(Class claseObjetivo) {
		this.claseObjetivo = claseObjetivo;
		tiempoRestante = new Cronometro();
		danoPotencial = 0;
	}

	@Override
	public void actualizar(Juego juego) {
		super.actualizar(juego);
		// Checa contacto con algun Vivo del juego
		ArrayList<Vivo> vivos = new ArrayList<Vivo>(0); // Se crea un ArrayList
		vivos.addAll(juego.enemigos); // Se le anaden los enemigos
		vivos.add(juego.limon); // Se le anade el limon
		Vivo[] vivosArray = new Vivo[vivos.size()]; // Se crea un array del
													// tamano de todos los vivos
		vivos.toArray(vivosArray); // Se transforma el ArrayList a array
		Vivo victima = checaContacto(vivosArray); // Se envia el array a checar
													// contacto
		if (victima != null) {
			victima.danar(danoPotencial);
			victima.reproducirSonido(HERIDO);
			this.destruirse = true;
		}
		checaColision(); // Checa si ha chocado contra una paderd
		checaDestruccion();
		tiempoRestante.anadirTiempoTranscurrido(GameMain.UPDATE_PERIOD);
	}

	public void checaDestruccion() {
		if (this.tiempoRestante.getSegundosTrascurridos() >= 1) {
			this.destruirse = true;
		}
	}

	public Vivo checaContacto(Vivo... vivos) {
		for (int i = 0; i < vivos.length; i++) {
			if (claseObjetivo.isAssignableFrom(vivos[i].getClass())) {
				if (vivos[i].getEspacioOcupado().intersects(
						this.getEspacioOcupado())) {
					return vivos[i];
				}
			}
		}
		return null;
	}

	public void checaColision() {
		if (this.vX == 0) {
			this.vY = 0;
			this.gravedad = 0;
			tiempoRestante.empezar();
		}
	}

}
