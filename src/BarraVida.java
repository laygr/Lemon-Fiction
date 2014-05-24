import java.awt.Color;
import java.awt.Graphics;

/**
 * 
 * @author Lay
 */
public class BarraVida extends PiezaJuego {
	Vivo vivo;
	double porcentaje;

	public BarraVida(Vivo vivo) {
		this.vivo = vivo;
	}

	public void setPorcentaje(double porcentaje) {
		this.porcentaje = porcentaje;
	}

	public void actualizar() {
		this.setZ(vivo.getZ());
		getEstadoActual().setAnchura(
				(double) vivo.getAnchura() / Ambiente.TILE_LADO);
		getEstadoActual().setAltura(
				(double) vivo.getAnchura() / 5 / Ambiente.TILE_LADO);
		this.setX(this.vivo.getX());
		this.setY(this.vivo.getY() - this.getAltura());
		setPorcentaje(vivo.vida / vivo.vidaMax);
	}

	@Override
	public void dibujarse(Graphics g, GameCanvas gameCanvas) {
		actualizar();
		if (porcentaje == 1) {
			return;
		}
		Camara camara = gameCanvas.camara;
		int x;
		int y;
		int anchura;
		int anchura2;
		int altura;

		x = camara.getX(this);
		y = camara.getY(this);
		anchura = camara.getAnchura(this);
		anchura2 = (int) (anchura * porcentaje);
		altura = camara.getAltura(this);

		// Dibuja el fondo;
		g.setColor(Color.RED);
		g.fillRect(x, y, anchura, altura);
		g.setColor(Color.green);
		g.fillRect(x, y, anchura2, altura);
		g.setColor(Color.black);
		g.drawRect(x, y, anchura, altura);
	}

}
