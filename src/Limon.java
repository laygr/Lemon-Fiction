import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * 
 * @author Lay
 */
public class Limon extends Vivo implements KeyListener {
	int tiempoDeInmunidad = 2000;
	boolean lanzarJugo;
	int ciclosCargando;
	EstadoLimon estadoLimon;
	Cronometro ultimoDisparoSemilla;
	Cronometro ultimoDisparoJugo;
	Cronometro inmunidad;

	public enum EstadoLimon {

		CORRIENDO, PARADO, MURIENDO, MUERTO, CAMINANDO, DISPARANDO_SEMILLA, DISPARANDO_JUGO;
	}

	public Limon() {
		ataque = 1;
		vidaMax = 1;
		vida = vidaMax;
		ciclosCargando = 0;
		estadoLimon = EstadoLimon.PARADO;
		ultimoDisparoSemilla = new Cronometro();
		ultimoDisparoJugo = new Cronometro();
		inmunidad = new Cronometro();

		ultimoDisparoSemilla.empezar();
		ultimoDisparoJugo.empezar();
		inmunidad.empezar();
	}

	@Override
	public void actualizar(Juego juego) {
		super.actualizar(juego);
		ultimoDisparoSemilla.anadirTiempoTranscurrido(GameMain.UPDATE_PERIOD);
		ultimoDisparoJugo.anadirTiempoTranscurrido(GameMain.UPDATE_PERIOD);
		inmunidad.anadirTiempoTranscurrido(GameMain.UPDATE_PERIOD);
		actualizarEstado();
		actualizarAtaques();
		if (muerto.getMilisegundosTranscurridos() > 2000) {
			muerto.resetear();
			this.estadoVivo = EstadoVivo.VIVO;
			vida = vidaMax;
			inmunidad.contadorDeMilisegundos = 0;
		}
		if(GameMain.datosJuego.vidas ==0){
			juego.siguiente = 5;
			juego.avanzar = true;
		}
	}

	public Poder setSemilla(Poder semilla) {
		semilla.setY(this.getY() + this.getAltura() / 2);
		semilla.setZ(this.getZ());
		if (this.direccion == Direccion.IZQUIERDA) {
			semilla.setX(this.getX() - semilla.getAnchura());
			semilla.vX = semilla.vX * -1;
			semilla.direccion = Direccion.IZQUIERDA;
		} else {
			semilla.setX(this.getX() + this.getAnchura());
		}
		semilla.vX += this.vX;
		return semilla;
	}

	@Override
	public void actualizarDireccion() {
		if (teclado.right) {
			direccion = Direccion.DERECHA;
		} else if (teclado.left) {
			direccion = Direccion.IZQUIERDA;
		} else if (this.suelo != null && !lanzarJugo && teclado.d == false
				&& teclado.espacio == false) {
			if (vX > 0) {
				direccion = Direccion.DERECHA;
			} else if (vX < 0) {
				direccion = Direccion.IZQUIERDA;
			}
		}
	}

	public Poder setJugo(Poder jugo) {
		jugo.setY(this.getY());
		jugo.setZ(this.getZ());
		if (this.direccion == Direccion.IZQUIERDA) {
			jugo.vX = jugo.vX * -1;
			jugo.direccion = Direccion.IZQUIERDA;
		}
		jugo.vX += this.vX;
		jugo.setX(this.getX());
		return jugo;
	}

	public void actualizarAtaques() {
		if (teclado.d) {
			lanzarJugo = true;
			ciclosCargando++;
		} else if (lanzarJugo) {
			lanzarJugo = false;
			Poder jugo = ResourceManager.crearPoder('J');
			jugo = setJugo(jugo);
			if (jugo.vX > 0) {
				jugo.vX += ciclosCargando * Jugo.EXTRA_POR_CICLO;
			} else {
				jugo.vX -= ciclosCargando * Jugo.EXTRA_POR_CICLO;
			}
			ciclosCargando = 0;
			poderes.add(jugo);
		}
		if (teclado.espacio) {
			if (ultimoDisparoSemilla.contadorDeMilisegundos >= lentitud) {
				ultimoDisparoSemilla.contadorDeMilisegundos = 0;
				if (GameMain.datosJuego.semillas > 0) {
					Poder semilla = ResourceManager.crearPoder('S');
					GameMain.datosJuego.semillas--;
					semilla = setSemilla(semilla);
					poderes.add(semilla);
				}
			}
		} else {
			ultimoDisparoSemilla.contadorDeMilisegundos = lentitud;
		}
	}

	public void actualizarEstado() {
		if (this.estadoVivo != EstadoVivo.VIVO) {
			return;
		}
		if (Math.abs(this.vX) > 0) {
			setEstado("CAMINANDO");
			estadoLimon = EstadoLimon.CAMINANDO;
		}

		if (Math.abs(this.vX) > 15) {
			setEstado("CORRIENDO");
			estadoLimon = EstadoLimon.CORRIENDO;
		}
		if (this.vX == 0) {
			setEstado("PARADO");
			estadoLimon = EstadoLimon.PARADO;
		}
		if (this.teclado.espacio) {
			setEstado("DISPARANDO_SEMILLA");
			estadoLimon = EstadoLimon.DISPARANDO_SEMILLA;
		}
		if (this.teclado.d) {
			setEstado("DISPARANDO_JUGO");
			estadoLimon = EstadoLimon.DISPARANDO_JUGO;
		}
	}

	/**
	 * Metodo que extiende al metodo danar de vivo. En este caso solo se dana al
	 * limon cuando su tiempo de inmunidad es mayor al tiempoDeInumindad
	 * 
	 * @param ataque
	 */
	@Override
	public void danar(double ataque) {
		if (inmunidad.contadorDeMilisegundos > tiempoDeInmunidad) {
			super.danar(ataque);
		}
	}

	public ArrayList<Point> obtenerTrayectoria(Juego juego) {
		ArrayList<Point> puntos = new ArrayList<Point>(0);
		int loops = 6;
		double vY = Jugo.VY_INICIAL;
		double vX = Jugo.VX_INICIAL + (ciclosCargando * Jugo.EXTRA_POR_CICLO);
		Poder jugo = ResourceManager.crearPoder('J');
		if (direccion == Direccion.IZQUIERDA) {
			vX *= -1;
		}
		jugo.vX += this.vX;
		int xF;
		int yF;
		if (lanzarJugo) {
			while (true) {
				xF = (int) ((vX * loops * GameMain.UPDATE_PERIOD / 1000.0) + this
						.getX());
				yF = (int) ((vY * loops * GameMain.UPDATE_PERIOD / 1000.0)
						+ (1.0 / 2) * (Jugo.GRAVEDAD) * 40 / 1000
						* Math.pow((loops / GameMain.FDT), 2) + this.getY());

				jugo.setX(xF);
				jugo.setY(yF);
				jugo.actualizar((Ambiente) juego);
				puntos.add(new Point(jugo.getX(), jugo.getY()));
				Vivo[] arreglo = new Vivo[0];
				if (((ObjetoReal) jugo).getColision(juego.plataformas) != null) {
					break;
				}
				if (jugo.checaContacto(juego.enemigos.toArray(arreglo)) != null) {
					puntos.get(0).x *= -1;
					break;
				}
				if (loops >= 500) {
					break;
				}
				loops += 20;
			}
		}
		return puntos;
	}

	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			teclado.left = false;
			break;
		case KeyEvent.VK_RIGHT:
			teclado.right = false;
			break;
		case KeyEvent.VK_UP:
			teclado.up = false;
			break;
		case KeyEvent.VK_DOWN:
			teclado.down = false;
			break;
		case KeyEvent.VK_A:
			teclado.a = false;
			break;
		case KeyEvent.VK_S:
			teclado.s = false;
			break;
		case KeyEvent.VK_D:
			teclado.d = false;
			break;
		case KeyEvent.VK_SPACE:
			teclado.espacio = false;
			break;
		}
	}

	public void keyPressed(KeyEvent e) {
		if (estadoVivo != EstadoVivo.VIVO) {
			return;
		}
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			teclado.left = true;
			break;
		case KeyEvent.VK_RIGHT:
			teclado.right = true;
			break;
		case KeyEvent.VK_UP:
			teclado.up = true;
			break;
		case KeyEvent.VK_DOWN:
			teclado.down = true;
			break;
		case KeyEvent.VK_A:
			teclado.a = true;
			break;
		case KeyEvent.VK_S:
			teclado.s = true;
			break;
		case KeyEvent.VK_D:
			teclado.d = true;
			break;
		case KeyEvent.VK_SPACE:
			teclado.espacio = true;
			break;
		}
	}

	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void dibujarse(Graphics g, GameCanvas gameCanvas) {
		super.dibujarse(g, gameCanvas);
		ArrayList<Point> puntos = this.obtenerTrayectoria((Juego) gameCanvas
				.ambiente());
		Plano puntoPlano = new Plano();
		puntoPlano.setZ(gameCanvas.ambiente().getZ());
		if (puntos.size() == 0) {
			return;
		}
		if (puntos.get(0).x < 0) {
			g.setColor(Color.green);
			puntos.get(0).x *= -1;
		} else {
			g.setColor(Color.red);
		}
		for (Point punto : puntos) {
			puntoPlano.setX(punto.x);
			puntoPlano.setY(punto.y);
			g.fillRoundRect(gameCanvas.camara.getX(puntoPlano),
					gameCanvas.camara.getY(puntoPlano), 10, 10, 90, 90);
		}
	}
}
