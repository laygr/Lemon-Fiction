import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

/**
 * An illustration of the use of AlphaComposite to make partially transparent
 * drawings.
 */
public class RectanguloTransparente extends JPanel {

	private int posX; // posicion en x del rectangulo
	private int posY; // posicion en y del rectangulo
	private int width; // ancho del rectangulo
	private int height; // ancho del rectangulo

	/**
	 * Metodo constructor usado para crear el objeto
	 * 
	 * @param posX
	 *            es la <code>posicion en x</code> del objeto.
	 * @param posY
	 *            es la <code>posicion en y</code> del objeto.
	 * @param image
	 *            es la <code>imagen</code> del objeto.
	 */
	public RectanguloTransparente(int posX, int posY, int width, int height) {
		this.posX = posX;
		this.posY = posY;
		this.height = height;
		this.width = width;
	}

	/**
	 * Metodo usado para crear el efecto de transparencia
	 * 
	 * @param alpha
	 *            es un numero que indica <code>el nivel de transparencia</code>
	 */
	private AlphaComposite makeComposite(float alpha) {
		int type = AlphaComposite.SRC_OVER; // se asigna el tipo SRC_OVER,
											// "Porter-Duff Source Over Destination rule. The source is composited over the destination."
		return (AlphaComposite.getInstance(type, alpha));
	}

	/**
	 * Metodo usado para dibujar al rectangulo
	 * 
	 * @param g2d
	 *            es el <code>objeto grafico</code> que se plasma
	 * @param alpha
	 *            es la <code>posicion en y</code> del rectangulo
	 */
	private void drawSquares(Graphics2D g2d, float alpha) {
		Composite originalComposite = g2d.getComposite();
		g2d.setComposite(makeComposite(alpha));
		g2d.setPaint(Color.gray); // se asigna el color
		Rectangle rectanguloSelector = new Rectangle(posX, posY, width, height); // se
																					// crea
																					// un
																					// rectangulo
																					// para
																					// llenar
		g2d.fill(rectanguloSelector); // se llena el rectangulo
		g2d.setComposite(originalComposite);
	}

	/**
	 * Metodo para pintar los graficos con su transparencia correspondiente
	 * 
	 * @param g
	 *            es el <code>objeto grafico</code> que se pintara
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		drawSquares(g2d, 0.3F); // se dibuja el objeto g2d con una transparencia
								// de 0.3
	}

	/**
	 * Metodo modificador usado para cambiar la posicion en y del objeto
	 * 
	 * @param posX
	 *            es la <code>posicion en x</code> del objeto.
	 */
	public void setPosY(int posY) {
		this.posY = posY;
	}
}
