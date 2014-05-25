package com.laygr;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class ArchivoFilter extends FileFilter {

	@Override
	public boolean accept(File archivo) {
		if (getExtension(archivo).equals(".sav") || archivo.isDirectory()) {
			return true;
		}
		return false;
	}

	public static String getExtension(File archivo) {
		return archivo.getPath().substring(archivo.getPath().length() - 4);
	}

	@Override
	public String getDescription() {
		return "Datos de juego LemonFiction";
	}

}
