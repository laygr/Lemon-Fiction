import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * La caracteristica principal de los objetos de esta clase es que tienen
 * estados ademas de sonidos.
 * 
 * @author Lay
 */
public class PiezaJuego extends Plano implements Audible, Dibujable,
		MultiEstado {
	ArrayList<Estado> estados;
	ArrayList<MP3> sonidos;
	String estadoActual;
	boolean visible;

	/**
	 *Metodo constructor
	 */
	public PiezaJuego() {
		super();
		estadoActual = "";
		estados = new ArrayList<Estado>(0);
		sonidos = new ArrayList<MP3>(0);
	}

	/**
	 * Actualiza todo lo que tenga que ver con la clase PiezaJuego de este
	 * objeto
	 * 
	 * @param juego
	 *            Juego en el que este objeto esta
	 */
	public void actualizar(Ambiente ambiente) {
		actualizarEstados(GameMain.UPDATE_PERIOD);
	}

	/**
	 *Se le anade un objeto de la clase estado
	 * 
	 * @param estado
	 *            El estado que se quiere anadir
	 */
	public void anadirEstados(Estado... estados) {
		for (int i = 0; i < estados.length; i++) {
			this.estados.add(estados[i]);
		}
	}

	/**
	 *Se establece en que estado se encuentra el objeto
	 * 
	 * @param estado
	 *            El nombre del estado
	 */
	public void setEstado(String estado) {
		int alturaAux = this.getAltura();
		int anchuraAux = this.getAnchura();
		estadoActual = estado;
		int diferenciaY = this.getAltura() - alturaAux;
		int diferenciaX = this.getAnchura() - anchuraAux;
		this.setY(this.getY() - diferenciaY);
		this.setX(this.getX() - diferenciaX);
	}

	/**
	 * Regresa el estado de este objeto
	 * 
	 * @return Estado que representa actualmente a este objeto
	 */
	public Estado getEstadoActual() {
		for (int i = 0; i < estados.size(); i++) {
			if (estadoActual.equals(estados.get(i).getNombre())) {
				return estados.get(i);
			}
		}
		return null;
	}

	/**
	 * actualiza los estados (sus animaciones)
	 * 
	 * @param tiempoTranscurrido
	 */
	public void actualizarEstados(long tiempoTranscurrido) {
		for (int i = 0; i < estados.size(); i++) {
			estados.get(i).actualizarAnimacion(tiempoTranscurrido);
		}
	}

	/**
	 *Regresa la imagen que reprenta al objeto en funcion del estado en el que
	 * se encuentra
	 * 
	 * @return imagen que representa al objeto en funcion del estado en el que
	 *         se encuentra
	 */
	public Image getImage() {
		return getEstadoActual().getImage();
	}

	/**
	 * regresa la anchura dependiendo del estado en que se encuentre
	 */
	@Override
	public int getAnchura() {
		if (getEstadoActual() == null) {
			return super.getAnchura();
		}
		return getEstadoActual().getAnchura();
	}

	/**
	 * regresa la altura dependiendo del estado en que se encuentre
	 */
	@Override
	public int getAltura() {
		if (getEstadoActual() == null) {
			return super.getAltura();
		}
		return getEstadoActual().getAltura();
	}

	/**
	 * Método que reproduce el sonido indicado
	 * 
	 * @param nombre
	 *            indica el nombre del sonido
	 */
	public void reproducirSonido(String nombre) {
		for (int i = 0; i < sonidos.size(); i++) {
			if (sonidos.get(i).getNombre().equals(nombre)) {
				sonidos.get(i).play();
				break;
			}
		}
	}

	/**
	 * Método que anade un sonido al arreglo de sonidos de este objeto
	 * 
	 * @param sonido
	 *            Sonido a anadir
	 */
	public void anadirSonido(MP3 sonido) {
		sonidos.add(sonido);
	}

	/**
	 * Método que dibuja este objeto
	 * 
	 * @param g
	 *            Graficos donde dibujarse
	 * @param gameCanvas
	 *            donde dibujarse y sacar la camara
	 */
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

		/*
		 * Condiciones para asegurarse que este objeto queda dentro de la vision
		 * de la camara, en caso contrario no se dibuja
		 */
		if (xP + anchuraP > 0 && xP < GameMain.ANCHURA) {
			if (yP + alturaP > 0 && yP < GameMain.ALTURA) {

				g.drawImage(this.getImage(), xP, yP, anchuraP, alturaP,
						gameCanvas);

			}
		}
	}
}