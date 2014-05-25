package com.laygr;

import java.awt.event.KeyEvent;
import java.net.URL;
import java.awt.*;

/**
 * 
 * @author ever
 */
public class Instrucciones extends Ambiente {
	private URL url_instrucciones = ManejaArchivo.getURL("Imagenes/Fondos/Instrucciones/fondo.gif"); // imagen de las instrucciones
	private Image imagen_instrucciones = Toolkit.getDefaultToolkit().getImage(url_instrucciones); // se
	// obtiene
	// la
	// imagen
	// de
	// la
	// URL
	private Fondo fondo = new Fondo(imagen_instrucciones, GameMain.ANCHURA,
			GameMain.ALTURA, 0); // se le asigna al fondo la imagen del menu

	public Instrucciones() {
		cancion = null;
	}

	Plano foco = new PiezaJuego(); // se crea un foco


	public void gameKeyPressed(int keyCode) {
			siguiente = ResourceManager.MENU;
			avanzar = true;
	}

	/**
	 * Metodo utilizado para actualizar el contenido de la pantalla, implemente
	 * el metodo actualizar() de la clase Ambiente
	 */
	public void actualizar() {

	}

	/**
	 * Método que regresa el foco de este ambiente. El foco es el elemento
	 * principal. En el caso del juego seria el limon. En el menu seria null.
	 * Sirve para que la camara sepa a quien seguir.
	 * 
	 * @return
	 */
	public PiezaJuego getFoco() {
		return null; // como es una vision estatica, osea el menu, el foco es
		// nulo ya que es innecesario
	}

	/**
	 * Metodo modificador usado para cambiar la posicion en z del objeto
	 * Overrides: Plano
	 * 
	 * @param z
	 *            es la <code>posicion en z</code> del objeto.
	 */
	@Override
	public void setZ(int z) {
		super.setZ(z); // se asigna Z desde la clase Plano
	}

	public void keyReleased(KeyEvent e) { // metodo abstracto del KeyListener
		// sin usarse
	}

	public void keyPressed(KeyEvent e) { // metodo abstracto del KeyListener
		// para obtener el codigo de las
		// teclas presionadas
		gameKeyPressed(e.getKeyCode());
	}

	public void keyTyped(KeyEvent e) { // metodo abstracto del KeyListener sin
		// usarse
	}

	/**
	 * Metodo usado para plasmar en la pantalla
	 * 
	 * @param g
	 *            es el <code>objeto grafico</code> usado para dibujar.
	 * @param gameCanvas
	 *            es el <code>objeto GameCanvas</code> donde se plasman los
	 *            dibujos
	 */
	public void dibujarse(Graphics g, GameCanvas gameCanvas) { // metodo para
		// plasmar en la
		// pantalla
		fondo.dibujarse(g, gameCanvas); // se dibuja el fondo
	}

	/**
	 * Metodo usado para plasmar en la pantalla
	 * 
	 * @param g
	 *            es el <code>objeto grafico</code> usado para dibujar.
	 */
	public void paintComponent(Graphics g) {
		paintComponent(g); // se pinta g
	}
}
