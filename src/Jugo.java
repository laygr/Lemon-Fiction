/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * 
 * @author Lay
 */
public class Jugo extends Poder {
	static final double DANO_POTENCIAL = 1;
	static final double VX_INICIAL = 8;
	static final double VY_INICIAL = -40;
	static final double GRAVEDAD = 3200 / GameMain.FDT;
	static final double EXTRA_POR_CICLO = .5;

	public Jugo(Class claseObjetivo) {
		super(claseObjetivo);
		this.danoPotencial = DANO_POTENCIAL;
	}

	@Override
	public void actualizar(Juego juego) {
		super.actualizar(juego);
		if (suelo != null || vY == 0) {
			this.danoPotencial = 0;
			this.setEstado("SPLASH");
		}
	}

}
