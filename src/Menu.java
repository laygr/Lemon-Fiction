import java.awt.event.KeyEvent;
import java.awt.image.ImageObserver;
import java.net.URL;
import java.awt.*;

/**
 * 
 * @author ever
 */
public class Menu extends Ambiente {
	
	private GameCanvas gameCanvas;
	private URL url_menu = ManejaArchivo.getURL("Imagenes/Fondos/Menu/fondo.gif"); // imagen del
	// menu
	private Image imagen_menu = Toolkit.getDefaultToolkit().getImage(url_menu); // se
	// obtiene
	// la
	// imagen
	// de
	// la
	// URL
	private Fondo fondo = new Fondo(imagen_menu, GameMain.ANCHURA,
			GameMain.ALTURA - 35, 0); // se le asigna al fondo la imagen del menu
	private RectanguloTransparente rectanguloSelector = new RectanguloTransparente(
			415, 215, 360, 75); // se crea un objeto de la clase
	// RectanguloTransparente para seleccionar las
	// opciones del menu
	private Graphics g2d;

	public Menu(GameCanvas gameCanvas) {
		cancion = null;
		this.gameCanvas = gameCanvas;
		 estadoActual = Estados.EMPEZAR_JUEGO;
	}

	Plano foco = new PiezaJuego(); // se crea un foco

	public static enum Estados { // se crean los estados

		EMPEZAR_JUEGO, CARGAR_JUEGO, VER_INSTRUCCIONES, SALIR;
	}

	// se carga el juego con la opcion de empezar juego seleccionada
	public static Estados estadoActual;

	public void gameKeyPressed(int keyCode) {
		switch (keyCode) {
		case KeyEvent.VK_UP: // en caso de que la tecla sea la flecha de arriba
			switch (estadoActual) { // revisa el estado y lo actualiza si es
			// necesario
			case EMPEZAR_JUEGO: // si el estado es EMPEZAR_JUEGO
				estadoActual = Estados.SALIR; // se cambia a SALIR
				rectanguloSelector.setPosY(430); // y el rectanguloSelector se
				// cambia a la posicion en Y
				// de 430 para seleccionar
				// la opcion de salir
				break;
			case CARGAR_JUEGO: // si el estado es CARGAR_JUEGO
				estadoActual = Estados.EMPEZAR_JUEGO; // se cambia al estado
				// EMPEZAR_JUEGO
				rectanguloSelector.setPosY(215); // y el rectanguloSelector se
				// cambia a la posicion en Y
				// de 215 para seleccionar
				// la opcion de empezar
				break;
			case VER_INSTRUCCIONES: // si el estado es VER_INSTRUCCIONES
				estadoActual = Estados.CARGAR_JUEGO; // se cambia al estado
				// CARGAR_JUEGO
				rectanguloSelector.setPosY(285); // y el rectanguloSelector se
				// cambia a la posicion en Y
				// de 285 para seleccionar
				// la opcion de cargar
				break;
			case SALIR: // si el estado es SALIR
				estadoActual = Estados.VER_INSTRUCCIONES; // se cambia al estado
				// VER_INSTRUCCIONES
				rectanguloSelector.setPosY(360); // y el rectanguloSelector se
				// cambia a la posicion en Y
				// de 360 para seleccionar
				// la opcion de ver
				// instrucciones
			}
			break;
		case KeyEvent.VK_DOWN:// en caso de que la tecla sea la flecha de abajo
			switch (estadoActual) {
			case EMPEZAR_JUEGO: // si el estado es EMPEZAR_JUEGO
				estadoActual = Estados.CARGAR_JUEGO; // se cambia al estado
				// CARGAR_JUEGO
				rectanguloSelector.setPosY(285); // y el rectanguloSelector se
				// cambia a la posicion en Y
				// de 285 para seleccionar
				// la opcion de cargar
				break;
			case CARGAR_JUEGO: // si el estado es CARGAR_JUEGO
				estadoActual = Estados.VER_INSTRUCCIONES; // se cambia al estado
				// VER_INSTRUCCIONES
				rectanguloSelector.setPosY(360); // y el rectanguloSelector se
				// cambia a la posicion en Y
				// de 360 para seleccionar
				// la opcion de ver
				// instrucciones
				break;
			case VER_INSTRUCCIONES: // si el estado es VER_INSTRUCCIONES
				estadoActual = Estados.SALIR; // se cambia al estado SALIR
				rectanguloSelector.setPosY(430); // y el rectanguloSelector se
				// cambia a la posicion en Y
				// de 430 para seleccionar
				// la opcion de salir
				break;
			case SALIR: // si el estado es SALIR
				estadoActual = Estados.EMPEZAR_JUEGO; // se cambia al estado
				// EMPEZAR_JUEGO
				rectanguloSelector.setPosY(215); // y el rectanguloSelector se
				// cambia a la posicion en Y
				// de 215 para seleccionar
				// la opcion de empezar
				break;
			}
			break;
		case KeyEvent.VK_ENTER: // en caso de que la tecla sea la tecla enter
			switch (estadoActual) {
			case EMPEZAR_JUEGO:
				this.siguiente = 1;
				avanzar = true;
				break;
			case CARGAR_JUEGO:
				DatosJuego datosJuego= GameMain.manejaArchivo.load(gameCanvas);
				if(datosJuego == null){
					return;
				}
				GameMain.datosJuego = datosJuego;
				this.siguiente = GameMain.datosJuego.nivel;
				avanzar = true;
				break;
			case VER_INSTRUCCIONES:
				this.siguiente = -1;
				avanzar = true;
				break;
			case SALIR:
				System.exit(0);
				break;
			default:
				break;
			}
			break;
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
		fondo.dibujarse(g, gameCanvas); // se dibuja el fondo
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
