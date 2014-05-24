import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.media.*;

public class Reproductor extends Ambiente {
	Player player;
	GameCanvas gameCanvas;

	public Reproductor(URL archivo, GameCanvas gameCanvas) {
		try {
			player = Manager.createRealizedPlayer(archivo);
		} catch (CannotRealizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoPlayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		player.getVisualComponent();
		this.gameCanvas = gameCanvas;
		gameCanvas.add(player.getVisualComponent());
	}

	public PiezaJuego getFoco() {
		return null;
	}

	public void actualizar() {
		if (player.getDuration().getNanoseconds() >= player.getTimeBase()
				.getNanoseconds()) {
			gameCanvas.remove(player.getVisualComponent());
			player.close();
			avanzar = true;
		}
	}

	@Override
	public void dibujarse(Graphics g, GameCanvas gameCanvas) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
			avanzar = true;
			gameCanvas.remove(player.getVisualComponent());
			player.close();
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
}