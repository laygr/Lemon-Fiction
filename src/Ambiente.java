import java.awt.Graphics;
import java.awt.event.KeyListener;

/**
 * Esta clase representa un ambiente en donde estaran diferentes tipos de
 * objetos por ejemplo, en la clase Juego que hereda de amiebnte, dichos objetos
 * son: Limon, Enemigos, plataformas, etc. En la clase menú, dichos objetos son
 * botones.
 * 
 * @author Lay
 */
public abstract class Ambiente extends Plano implements KeyListener {
	MP3 cancion;
	boolean avanzar;
	int siguiente;
	final static int TILE_LADO = GameMain.ANCHURA / 15;

	public Ambiente() {
		super();
	}

	public abstract void actualizar();

	/**
	 * Metodo que regresa el foco de este ambiente. El foco es el elemento
	 * principal. En el caso del juego seria el limon. En el menu seria null.
	 * Sirve para que la camara sepa a quien seguir.
	 * 
	 * @return
	 */
	public abstract Plano getFoco();

	public abstract void dibujarse(Graphics g, GameCanvas gameCanvas);
}
