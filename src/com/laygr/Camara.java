package com.laygr;

/**
 * Camara que indicara la posicion y tamanos de los planos que reciba como
 * parametros en sus metodos
 * 
 * @author Lay
 */
public class Camara {

	int anchura; // Anchura del lente
	int altura; // Altura del lente
	int x; // Posicion en x
	int y; // Posicion en y
	int z; // Posicion en z
	int fx; // Posicion en x del foco de la camara con relacion a la posicion de
			// la camara
	int fy; // Posicion en y del foco de la camara
	int fz; // Posicion en z del foco de la camara
	double thetax1; // Angulo de vision en x lado izquierdo
	double thetax2; // Angulo de vision en x, lado dercho
	double thetay1; // Angulo de vision en y
	double thetay2; // Angulo de vision en y
	Plano foco; // Objeto que la camara seguira.
	final double porcentajeDeCamara = .05; // Porcentaje de la camara que el
											// foco debe ocupar

	public Camara(int anchura, int altura) {
		this.anchura = anchura;
		this.altura = altura;
		this.fx = this.anchura / 2;
		this.fy = this.altura / 2;
		this.fz = this.anchura;
		this.x = 0;
		this.y = 0;
		this.z = 0;
		setThetax();
		setThetay();
	}

	/**
	 * Checa si el Plano esta dentro del campo de visibilidad de la camara;
	 * 
	 * @param otroPlano
	 *            Plano al que se le desea hacer la prueba
	 * @return True si si es visible false si no
	 */
	public boolean checaVisible(Plano otroPlano) {
		int planoX = this.getX(otroPlano);
		int planoY = this.getY(otroPlano);
		int planoAltura = this.getAltura(otroPlano);
		int planoAnchura = this.getAnchura(otroPlano);

		if (planoX + planoAnchura >= 0 && planoX <= this.anchura) {
			if (planoY + planoAltura >= 0 && planoY <= this.altura) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Metodo que actualiza la posicion en z de la camara segun la posicion der
	 * foco
	 */
	public void actualizarZ() {
		if (foco == null) {
			z=0;
			return;
		}
		this.z = (int) (foco.getAnchura()
				/ (porcentajeDeCamara * (Math.tan(thetax1) + Math.tan(thetax2)))
				- this.fz + foco.getZ());
	}

	/**
	 * Metodo que actualiza la posicion en x y y de la camara segun la poscion
	 * del foco
	 */
	public void actualizarXY() {
		if (foco == null) {
			x=0;
			y=0;
			return;
		}
		if (foco.getY() + foco.getAltura() / 2 < this.y + this.altura * 2 / 7) {
			this.y = foco.getY() + foco.getAltura() / 2 - this.altura * 2 / 7;
		} else if (foco.getY() + foco.getAltura() / 2 > this.y + this.altura
				* 5 / 7) {
			this.y = foco.getY() + foco.getAltura() / 2 - this.altura * 5 / 7;
		}
		this.x = (foco.getX() + foco.getAnchura() / 2) - this.anchura / 2;

		if (this.x < 0) {
			this.x = 0;
		}
		if (this.y < 0) {
			this.y = 0;
		}
	}

	public void setFoco(Plano foco) {
		this.foco = foco;
	}

	public void setThetax() {
		thetax1 = Math.atan((double) fx / fz);
		thetax2 = Math.atan((double) (anchura - fx) / fz);
	}

	public void setThetay() {
		thetay1 = Math.atan((double) fy / fz);
		thetay2 = Math.atan((double) (altura - fy) / fz);

	}

	public double getFactorDeEncogimientoX(Plano otroPlano) {
		int z = this.z + this.fz - otroPlano.getZ();
		return z * (Math.tan(thetax1) + Math.tan(thetax2)) / this.anchura;
	}

	public double getFactorDeEncogimientoY(Plano otroPlano) {
		int z = this.z + this.fz - otroPlano.getZ();
		return z * (Math.tan(thetay1) + Math.tan(thetay2)) / this.altura;
	}

	/**
	 * regresa la posicion en x que el otro plano debe de tener segun su
	 * posicion y la de la camara
	 * 
	 * @param otroPlano
	 *            El otro plano cuya posicion se debe de calcular
	 * @return posicion en pixeles
	 */
	public int getX(Plano otroPlano) {

		int x1 = this.x + this.fx - otroPlano.getX();
		int z1 = this.z + this.fz - otroPlano.getZ();
		int x0 = this.fz * x1 / z1;

		return this.fx - x0;

	}

	/**
	 * regresa la posicion en y que el otro plano debe de tener segun su
	 * posicion y la de la camara
	 * 
	 * @param otroPlano
	 *            El otro plano cuya posicion se debe de calcular
	 * @return posicion en pixeles
	 */
	public int getY(Plano otroPlano) {
		int y1 = this.y + this.fy - otroPlano.getY();
		int z1 = this.z + this.fz - otroPlano.getZ();
		int y0 = this.fz * y1 / z1;
		return this.fy - y0;
	}

	/**
	 * regresa la anchura del otro plano debe de tener segun su posicion y
	 * tamano y la de la camara
	 * 
	 * @param otroPlano
	 *            El otro plano cuya anchura se debe de calcular
	 * @return anchura en pixeles
	 */
	public int getAnchura(Plano otroPlano) {
		return (int) (otroPlano.getAnchura() / getFactorDeEncogimientoX(otroPlano));
	}

	/**
	 * regresa la altura del otro plano debe de tener segun su posicion y tamano
	 * y la de la camara
	 * 
	 * @param otroPlano
	 *            El otro plano cuya altura se debe de calcular
	 * @return altura en pixeles
	 */
	public int getAltura(Plano otroPlano) {
		return (int) (otroPlano.getAltura() / getFactorDeEncogimientoY(otroPlano));
	}

	public int getFueraDeCuadro(Poder poder) {
		int pixeles = -poder.getX() + this.x;
		pixeles = (poder.getX() - this.x > pixeles) ? poder.getX() - this.x
				: pixeles;
		pixeles = (poder.getY() - this.y > pixeles) ? poder.getY() - this.y
				: pixeles;
		pixeles = (-poder.getY() + this.y > pixeles) ? -poder.getY() + this.y
				: pixeles;
		return pixeles;
	}
}
