/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Esta clase es un contador de milisegundos.
 * 
 * @author Lay
 */
public class Cronometro {
	long contadorDeMilisegundos; // Contador
	boolean corriendo; // Bandera que dice si esta corriendo

	/**
	 * Inicializa el cronometro y coloca el contador en cero
	 */
	public Cronometro() {
		contadorDeMilisegundos = 0;
		corriendo = false;
	}

	/**
	 * Añade milisengundos si es que esta corriendo
	 * 
	 * @param milis
	 *            milisegundos a agregar
	 */
	public void anadirTiempoTranscurrido(long milis) {
		if (corriendo) {
			contadorDeMilisegundos += milis;
		}
	}

	/**
	 * Posiciona la bandera de corriendo en true
	 */
	public void empezar() {
		corriendo = true;
	}

	/**
	 * Posiciona la bandera de corriendo en false y se reinicia el contador
	 */
	public void resetear() {
		corriendo = false;
		contadorDeMilisegundos = 0;
	}

	/**
	 * 
	 * @return regresa los milisegundos en el contador
	 */
	public long getMilisegundosTranscurridos() {
		return contadorDeMilisegundos;
	}

	/**
	 * 
	 * @return regresa los minutos en el contador
	 */
	public int getMinutosTrascurridos() {
		return (int) contadorDeMilisegundos / 1000 / 60;
	}

	/**
	 * 
	 * @return regresa los segundos en el contador (Restando los minutos cuando
	 *         se pueda)
	 */
	public int getSegundosTrascurridos() {
		return (int) contadorDeMilisegundos / 1000 % 60;
	}

}
