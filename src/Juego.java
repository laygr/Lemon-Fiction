import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * 
 * @author Lay
 */
public class Juego extends Ambiente {

	private RectanguloTransparente fondoStatus = new RectanguloTransparente(60,
			20, 200, 100);
	int nivel;
	protected Limon limon;
	ArrayList<Enemigo> enemigos;
	ArrayList<Plataforma> plataformas;
	ArrayList<Poder> poderes;
	Plano foco;
	char[][] mapa;

	public Juego() {
		super();
		this.foco = limon;
		this.enemigos = new ArrayList<Enemigo>(0);
		this.plataformas = new ArrayList<Plataforma>(0);
		this.poderes = new ArrayList<Poder>(0);
		this.cancion = null;
	}

	/**
	 * Coloca el juego y todos sus componentes en z
	 * 
	 * @param z
	 *            Donde en z deben colocarse;
	 */
	@Override
	public void setZ(int z) {
		super.setZ(z);
		for (int i = 0; i < plataformas.size(); i++) {
			plataformas.get(i).setZ(z);
		}
		for (int i = 0; i < enemigos.size(); i++) {
			enemigos.get(i).setZ(z);
		}
		this.limon.setZ(z);
	}

	/**
	 * Mueve el juego en x y mueve a todos sus componentes lo que se movio el
	 * juego en x
	 * 
	 * @param x
	 *            Cuanto debe de moverse en x
	 */
	@Override
	public void setX(int x) {
		super.setX(x);
		for (int i = 0; i < plataformas.size(); i++) {
			plataformas.get(i).xInicial+= x;
			plataformas.get(i).setX(x + plataformas.get(i).getX());
		}
		for (int i = 0; i < enemigos.size(); i++) {
			enemigos.get(i).xInicial+= x;
			enemigos.get(i).setX(x + enemigos.get(i).getX());
		}
		this.limon.xInicial+= x;
		this.limon.setX(x + limon.getX());
	}

	/**
	 * Mueve el juego en y y mueve a todos sus componentes lo que se movio el
	 * juego en y
	 * 
	 * @param y
	 *            Cuanto debe de moverse en y
	 */
	@Override
	public void setY(int y) {
		super.setY(y);
		for (int i = 0; i < plataformas.size(); i++) {
			plataformas.get(i).setY(y + plataformas.get(i).getY());
		}
		for (int i = 0; i < enemigos.size(); i++) {
			enemigos.get(i).setY(y + enemigos.get(i).getY());
		}
		this.limon.setY(y + limon.getY());
	}

	public ObjetoReal getFoco() {
		return limon;
	}

	public void setLimon(Limon limon) {
		this.limon = limon;
		this.limon.setZ(this.getZ());
	}

	public void actualizar() {
		if (enemigos.size() == 0) {
			avanzar = true;
		}
		while (limon.poderes.size() != 0) {
			poderes.add(limon.poderes.get(0));
			limon.poderes.remove(0);
		}
		for (int i = 0; i < enemigos.size(); i++) {
			while (enemigos.get(i).poderes.size() != 0) {
				poderes.add(enemigos.get(i).poderes.get(0));
				enemigos.get(i).poderes.remove(0);
			}
		}
		limon.actualizar(this);
		for (int i = 0; i < enemigos.size(); i++) {
			enemigos.get(i).actualizar(this);
			if (enemigos.get(i).destruirse) {
				enemigos.remove(i);
				i--;
			}
		}
		for (int i = 0; i < poderes.size(); i++) {
			poderes.get(i).actualizar(this);
			if (poderes.get(i).destruirse) {
				poderes.remove(i);
				i--;
			}
		}
	}

	public void keyPressed(KeyEvent e) {
		this.limon.keyPressed(e);
		if (e.getKeyCode() == KeyEvent.VK_P) {
			GameMain.pause = !GameMain.pause;
		}
	}

	public void keyReleased(KeyEvent e) {
		this.limon.keyReleased(e);
	}

	public void keyTyped(KeyEvent e) {
	}

	public void dibujarse(Graphics g, GameCanvas gameCanvas) {
		fondoStatus.paintComponent(g);
		for (int i = 0; i < plataformas.size(); i++) {
			if (gameCanvas.camara.checaVisible(plataformas.get(i))) {
				plataformas.get(i).dibujarse(g, gameCanvas);
			}
		}
		for (int i = 0; i < enemigos.size(); i++) {
			if (gameCanvas.camara.checaVisible(enemigos.get(i))) {
				enemigos.get(i).dibujarse(g, gameCanvas);
			}
		}
		for (int i = 0; i < poderes.size(); i++) {
			if (gameCanvas.camara.checaVisible(poderes.get(i))) {
				poderes.get(i).dibujarse(g, gameCanvas);
			} else if (gameCanvas.camara.getFueraDeCuadro(poderes.get(i)) > 1000) {

				poderes.get(i).destruirse = true;
			}
		}
		limon.dibujarse(g, gameCanvas);
		if(GameMain.pause){
			g.setColor(Color.black);
			g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
			g.drawString("-PAUSADO-", GameMain.ANCHURA/2-100, GameMain.ALTURA/2);
		}
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		g.setColor(Color.blue);
		g.drawString("Vidas       X " + GameMain.datosJuego.vidas, 100, 50);
		g.setColor(Color.red);
		g.drawString("Semillas   X " + GameMain.datosJuego.semillas, 100, 80);
		g.setColor(Color.orange);
		g.drawString("Enemigos X " + this.enemigos.size(), 100, 110);
	}
}
