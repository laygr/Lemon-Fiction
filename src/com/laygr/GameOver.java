package com.laygr;

import java.awt.event.KeyEvent;
import java.awt.*;

/**
 * 
 * @author ever
 */
public class GameOver extends Ambiente {
	private RectanguloTransparente rectanguloSelector = new RectanguloTransparente(
			325, 365, 360, 75); // se crea un objeto de la clase

	// RectanguloTransparente para seleccionar las
	// opciones del menu

	public GameOver() {
		estadoActual = Estados.CONTINUAR;
	}

	public static enum Estados { // se crean los estados
		CONTINUAR, IR_A_MENU;
	}

	// se carga el juego con la opcion de empezar juego seleccionada
	public static Estados estadoActual;

	public void gameKeyPressed(int keyCode) {
		switch (keyCode) {
		case KeyEvent.VK_UP: // en caso de que la tecla sea la flecha de arriba
			switch (estadoActual) { // revisa el estado y lo actualiza si es
			// necesario
			case CONTINUAR: // si el estado es CONTINUAR
				estadoActual = Estados.IR_A_MENU; // se cambia a IR_A_MENU
				rectanguloSelector.setPosY(440); // y el rectanguloSelector se
				// cambia a la posicion en Y
				// de 430 para seleccionar
				// la opcion de salir
				break;
			case IR_A_MENU: // si el estado es IR_A_MENU
				estadoActual = Estados.CONTINUAR; // se cambia al estado
				// CONTINUAR
				rectanguloSelector.setPosY(365); // y el rectanguloSelector se
				// cambia a la posicion en Y
				// de 215 para seleccionar
				// la opcion de empezar
				break;
			}
			break;
		case KeyEvent.VK_DOWN:// en caso de que la tecla sea la flecha de abajo
			switch (estadoActual) { // revisa el estado y lo actualiza si es
			// necesario
			case CONTINUAR: // si el estado es CONTINUAR
				estadoActual = Estados.IR_A_MENU; // se cambia a IR_A_MENU
				rectanguloSelector.setPosY(440); // y el rectanguloSelector se
				// cambia a la posicion en Y
				// de 430 para seleccionar
				// la opcion de salir
				break;
			case IR_A_MENU: // si el estado es IR_A_MENU
				estadoActual = Estados.CONTINUAR; // se cambia al estado
				// CONTINUAR
				rectanguloSelector.setPosY(365); // y el rectanguloSelector se
				// cambia a la posicion en Y
				// de 215 para seleccionar
				// la opcion de empezar
				break;
			}
			break;
		case KeyEvent.VK_ENTER: // en caso de que la tecla sea la tecla enter
			switch (estadoActual) {
			case CONTINUAR:
				int nivel = GameMain.datosJuego.nivel;
				GameMain.datosJuego = new DatosJuego();
				GameMain.datosJuego.nivel = nivel;
				siguiente = nivel;
				avanzar = true;
				break;
			case IR_A_MENU:
				siguiente = ResourceManager.MENU;
				GameMain.datosJuego = new DatosJuego();
				avanzar = true;
				break;
			}
		}
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
		rectanguloSelector.paintComponent(g); // luego el rectangulo selector
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
