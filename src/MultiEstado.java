/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Image;

/**
 *Esta interfaz es para las clases que puedan tener diferentes "estados". Como
 * por emjeplo: brincando, corriendo, etc.
 * 
 * @author Lay
 */
public interface MultiEstado {
	void anadirEstados(Estado... estados);

	void setEstado(String estado);

	int getAnchura();

	int getAltura();

	Image getImage();
}
