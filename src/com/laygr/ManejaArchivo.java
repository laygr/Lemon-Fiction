package com.laygr;//
//  ManejaArchivo.java
//  
//
//  Created by lay on 07/07/09.
//  Copyright 2009 Tec de Monterrey. All rights reserved.
//

import javax.swing.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class ManejaArchivo implements Serializable {
	private static final long serialVersionUID = 1L;

	private JFileChooser fc = new JFileChooser();

	public ManejaArchivo() {
		fc.setAcceptAllFileFilterUsed(false);
		fc.setFileFilter(new ArchivoFilter());
	}

	public void save(GameCanvas gameCanvas, DatosJuego datos) {
		int guardar = fc.showSaveDialog(gameCanvas);
		if (guardar != JFileChooser.APPROVE_OPTION) {
			return;
		}
		try {
			FileOutputStream fos = new FileOutputStream(fc.getSelectedFile()+".sav");
			ObjectOutputStream out = new ObjectOutputStream(fos);
			out.writeObject(datos);
			out.flush();
			out.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	
	public DatosJuego load(GameCanvas gameCanvas) {
		fc.showOpenDialog(gameCanvas);
		try {
			FileInputStream fis = new FileInputStream(fc.getSelectedFile());
			ObjectInputStream in = new ObjectInputStream(fis);
			DatosJuego datosJuego = (DatosJuego) in.readObject();
			in.close();
			return datosJuego;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	public static String getPath(String archivo){
		return getURL(archivo).getPath();
	}
	public static URL getURL(String archivo){
		String protocol = "file";
		String host = "";
		try {
			return new URL(protocol,host,(new File("Recursos/" + archivo)).getAbsolutePath().replace((char)92, '/').toString());
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
