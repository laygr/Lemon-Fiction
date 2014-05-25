package com.laygr;/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *Esta clase sera la encargada de crear todos los objetos que trabajen con
 * imagenes para mantener el resto del codigo ordenado
 * 
 */
public class ResourceManager {
	public static final int MENU = 0;
	public static Animation cuchilloParado = crearAnimation("Cuchillo/Parado/");
	public static Animation cuchilloCaminando = crearAnimation("Cuchillo/Caminando/");
	public static Animation cuchilloCorriendo = crearAnimation("Cuchillo/Corriendo/");
	public static Animation cuchilloMuriendo = crearAnimation("Cuchillo/Muriendo/");
	public static Animation cuchilloAtacando = crearAnimation("Cuchillo/Atacando/");
	public static Animation mondadientesParado = crearAnimation("Mondadientes/Parado/");
	public static Animation mondadientesCaminando = crearAnimation("Mondadientes/Caminando/");
	public static Animation mondadientesCorriendo = crearAnimation("Mondadientes/Corriendo/");
	public static Animation mondadientesMuriendo = crearAnimation("Mondadientes/Muriendo/");
	public static Animation mondadientesAtacando = crearAnimation("Mondadientes/Atacando/");
	public static Animation tacoParado = crearAnimation("Taco/Parado/");
	public static Animation tacoCaminando = crearAnimation("Taco/Caminando/");
	public static Animation tacoCorriendo = crearAnimation("Taco/Corriendo/");
	public static Animation tacoMuriendo = crearAnimation("Taco/Muriendo/");
	public static Animation tacoAtacando = crearAnimation("Taco/Atacando/");
	public static Animation limonParado = crearAnimation("Limon/Parado/");
	public static Animation limonCaminando = crearAnimation("Limon/Caminando");
	public static Animation limonCorriendo = crearAnimation("Limon/Corriendo");
	public static Animation limonMuriendo = crearAnimation("Limon/Muriendo");
	public static Animation limonLanzandoSemilla = crearAnimation("Limon/LanzandoSemilla");
	public static Animation limonLanzandoJugo = crearAnimation("Limon/LanzandoJugo");
	static URL urlVerduras = ManejaArchivo.getURL("Imagenes/Fondos/General/verduras.gif");
	static URL urlLicuadora = ManejaArchivo.getURL("Imagenes/Fondos/General/licuadora.gif");
	static Fondo verduras = new Fondo(Toolkit.getDefaultToolkit().createImage(
			urlVerduras), Ambiente.TILE_LADO * 15,
			Ambiente.TILE_LADO * 8, Ambiente.TILE_LADO * -99);
	static Fondo licuadora = new Fondo(Toolkit.getDefaultToolkit().createImage(
			urlLicuadora), Ambiente.TILE_LADO * 5,
			Ambiente.TILE_LADO * 10, Ambiente.TILE_LADO * -92);
	
	public static BarraVida crearBarraVida(Vivo vivo) {
		BarraVida barraVida = new BarraVida(vivo);
		URL url = ResourceManager.class
				.getResource("Recursos/Imagenes/BarraVida/Normal/0.png");
		Image normalImage = Toolkit.getDefaultToolkit().getImage(url);
		Estado normal = new Estado("NORMAL", normalImage);
		normal.setAnchura(vivo.getAnchura() / Ambiente.TILE_LADO);
		normal.setAltura(normal.getAnchura() / Ambiente.TILE_LADO / 5);
		barraVida.anadirEstados(normal);
		barraVida.setEstado("NORMAL");
		return barraVida;
	}

	public static Enemigo crearEnemigo(char tipo) {
		Enemigo enemigo = null;
		String clase = "";
		Animation paradoAnimation = null;
		Animation caminandoAnimation = null;
		Animation corriendoAnimation = null;
		Animation muriendoAnimation = null;
		Animation atacandoAnimation = null;

		double paradoAn = 0;
		double paradoAl = 0;
		double caminandoAn = 0;
		double caminandoAl = 0;
		double corriendoAn = 0;
		double corriendoAl = 0;
		double muriendoAn = 0;
		double muriendoAl = 0;
		double muertoAn = 0;
		double muertoAl = 0;
		double atacandoAn = 0;
		double atacandoAl = 0;

		switch (tipo) {
		case 'T':
			clase = "Taco";
			enemigo = new Taco();
			enemigo.masa = 4;

			paradoAn = 2;
			paradoAl = 1.5;
			caminandoAn = 2;
			caminandoAl = 1.5;
			corriendoAn = 2;
			corriendoAl = 1.5;
			muriendoAn = 2;
			muriendoAl = 1.5;
			muertoAn = 2;
			muertoAl = 1.5;
			atacandoAn = 2;
			atacandoAl = 1.5;
			paradoAnimation = tacoParado;
			caminandoAnimation = tacoCaminando;
			corriendoAnimation = tacoCorriendo;
			muriendoAnimation = tacoMuriendo;
			atacandoAnimation = tacoAtacando;
			break;
		case 'C':
			clase = "Cuchillo";
			enemigo = new Cuchillo();
			enemigo.masa = .4;
			paradoAn = 1.2;
			paradoAl = 1.2;
			caminandoAn = 1.5;
			caminandoAl = 1.2;
			corriendoAn = 1.5;
			corriendoAl = 1.2;
			muriendoAn = 1.5;
			muriendoAl = 1.2;
			muertoAn = 1.5;
			muertoAl = 1.2;
			atacandoAn = 1.5;
			atacandoAl = 1.2;
			paradoAnimation = cuchilloParado;
			caminandoAnimation = cuchilloCaminando;
			corriendoAnimation = cuchilloCorriendo;
			muriendoAnimation = cuchilloMuriendo;
			atacandoAnimation = cuchilloAtacando;
			break;
		case 'M':
			clase = "Mondadientes";
			enemigo = new Mondadientes();
			enemigo.masa = 1;

			paradoAn = 1.25;
			paradoAl = 2;
			caminandoAn = 1.25;
			caminandoAl = 2;
			corriendoAn = 1.25;
			corriendoAl = 2;
			muriendoAn = 1.25;
			muriendoAl = 2;
			muertoAn = 1.25;
			muertoAl = 2;
			atacandoAn = 2;
			atacandoAl = 1.5;
			paradoAnimation = mondadientesParado;
			caminandoAnimation = mondadientesCaminando;
			corriendoAnimation = mondadientesCorriendo;
			muriendoAnimation = mondadientesMuriendo;
			atacandoAnimation = mondadientesAtacando;

		}

		// PARADO
		// Crea estado parado
		Estado parado = new Estado("PARADO", paradoAnimation);
		parado.setAltura(paradoAl);
		parado.setAnchura(paradoAn);
		// CAMINANDO
		// Crear animacion caminando
		// Crea estado caminando
		Estado caminando = new Estado("CAMINANDO", caminandoAnimation);
		caminando.setAltura(caminandoAl);
		caminando.setAnchura(caminandoAn);
		// CORRIENDO
		// Crear animacion corriendo
		// Crea estado corriendo
		Estado corriendo = new Estado("CORRIENDO", corriendoAnimation);
		corriendo.setAltura(corriendoAl);
		corriendo.setAnchura(corriendoAn);

		// MURIENDO
		// Crea estado muriendo
		Estado muriendo = new Estado("MURIENDO", muriendoAnimation);
		muriendo.setAltura(muriendoAl);
		muriendo.setAnchura(muriendoAn);

		// MUERTO
		// Crear animacion muerto
		Animation muertoAnimation = crearAnimation(clase + "/Muerto/");
		// Crea estado caminando
		Estado muerto = new Estado("MUERTO", muertoAnimation);
		muerto.setAltura(muertoAl);
		muerto.setAnchura(muertoAn);

		// ATACANDO
		// Crear animacion muerto
		// Crea estado caminando
		Estado atacando = new Estado("ATACANDO", atacandoAnimation);
		atacando.setAltura(atacandoAl);
		atacando.setAnchura(atacandoAn);

		enemigo.anadirEstados(parado, caminando, corriendo, muriendo, muerto,
				atacando);
		enemigo.setEstado("PARADO");

		enemigo.gravedad = 3000 / GameMain.FDT;

		MP3 pisado = new MP3("PISADO", false, "Sonidos/SFX/Pisado.mp3");
		enemigo.anadirSonido(pisado);
		return enemigo;
	}

	public static Limon crearLimon() {
		Limon limon = new Limon();
		MP3 muriendoSound = new MP3("MURIENDO", false, "Sonidos/Limon/Muriendo/0.mp3");
		limon.anadirSonido(muriendoSound);
		/*--Crear Estados CAMINANDO, CORRIENDO, MURIENDO, MUERTO PARADO,
		 *DISPARANDO_JUGO, DISPARANDO_SEMILLA--
		 */

		// CAMINANDO
		// Crear animacion caminando
		Animation caminandoAnimation = crearAnimation("Limon/Caminando/");
		// Crea estado caminando
		Estado caminando = new Estado("CAMINANDO", caminandoAnimation);
		caminando.setAnchura(1);
		caminando.setAltura(1);

		// CORRIENDO
		// Crea animacion corriendo
		Animation corriendoAnimation = crearAnimation("Limon/Corriendo/");
		// Crea estado corriendo
		Estado corriendo = new Estado("CORRIENDO", corriendoAnimation);
		corriendo.setAnchura(1);
		corriendo.setAltura(1);

		// MURIENDO
		// Crea animacion muriendo
		Animation muriendoAnimation = crearAnimation("Limon/Muriendo/");
		// Crea estado muriendo
		Estado muriendo = new Estado("MURIENDO", muriendoAnimation);
		muriendo.setAnchura(1.3);
		muriendo.setAltura(1.3);

		// MUERTO
		// Crea animacion muerto
		Animation muertoAnimation = crearAnimation("Limon/Muerto/");
		// Crea estado muerto
		Estado muerto = new Estado("MUERTO", muertoAnimation);
		muerto.setAnchura(1.3);
		muerto.setAltura(1.3);

		// PARADO
		// Crea animacion parado
		Animation paradoAnimation = crearAnimation("Limon/Parado/");
		// Crea estado parado
		Estado parado = new Estado("PARADO", paradoAnimation);
		parado.setAnchura(1);
		parado.setAltura(1);

		// DISPARANDO_JUGO
		// Crea animacion disparandoJugo
		Animation disparandoJugoAnimation = crearAnimation("Limon/DisparandoJugo/");
		// Crea estado disparandoJugo
		Estado disparandoJugo = new Estado("DISPARANDO_JUGO",
				disparandoJugoAnimation);
		disparandoJugo.setAnchura(1);
		disparandoJugo.setAltura(1);

		// DISPARANDO_SEMILLA
		// Crea animacion disparandoSemilla
		Animation disparandoSemillaAnimation = crearAnimation("Limon/DisparandoSemilla/");
		// Crea estado disparandoSemilla
		Estado disparandoSemilla = new Estado("DISPARANDO_SEMILLA",
				disparandoSemillaAnimation);
		disparandoSemilla.setAnchura(1);
		disparandoSemilla.setAltura(1);

		limon.anadirEstados(caminando, corriendo, muriendo, muerto, parado,
				disparandoJugo, disparandoSemilla);
		limon.setEstado("PARADO");

		limon.gravedad = 3000 / GameMain.FDT;
		limon.masa = .3;
		limon.vida = 1;

		return limon;
	}

	public static Poder crearPoder(char tipo) {
		Poder poder;
		String tipoString;
		double anchura;
		double altura;
		switch (tipo) {
		case 'S': // semilla
			poder = new Poder(Enemigo.class);
			tipoString = "Semilla";
			altura = .25;
			anchura = altura * 1.4;
			poder.gravedad = 1;
			poder.vX = 10;
			poder.masa = 20;
			poder.danoPotencial = .5;
			break;
		case 'P': // palillo
			poder = new Poder(Limon.class);
			tipoString = "Palillo";
			altura = .1;
			anchura = 2;
			poder.gravedad = 0;
			poder.danoPotencial = .5;
			poder.vX = 10;
			break;
		case 'J': // Jugo
			poder = new Jugo(Enemigo.class);
			tipoString = "Jugo";
			poder.gravedad = Jugo.GRAVEDAD;
			altura = .8;
			anchura = 1;
			poder.vY = Jugo.VY_INICIAL;
			poder.vX = Jugo.VX_INICIAL;
			Animation splasheandoseAnimation = crearAnimation(tipoString
					+ "/Splasheandose/");
			Estado splasheandose = new Estado("SPLASHEANDOSE",
					splasheandoseAnimation);
			splasheandose.setAltura(altura);
			splasheandose.setAnchura(anchura);

			Animation splashAnimation = crearAnimation(tipoString + "/Splash/");
			Estado splash = new Estado("SPLASH", splashAnimation);
			splash.setAltura(altura);
			splash.setAnchura(anchura);
			poder.anadirEstados(splasheandose, splash);
			poder.masa = 2;
			break;
		case 'C': // Carne
			poder = new Poder(Limon.class);
			tipoString = "Carne";
			poder.gravedad = 1;
			poder.vX = Math.random() * 20;
			poder.vY = Math.random() * 20;
			poder.danoPotencial = .25;
			altura = .6;
			anchura = .6;
			break;
		default:
			poder = null;
			tipoString = "";
			anchura = 0;
			altura = 0;
			break;
		}
		Animation normalAnimation = crearAnimation(tipoString + "/Normal/");
		Estado normal = new Estado(Poder.estadoNormal, normalAnimation);
		normal.setAltura(altura);
		normal.setAnchura(anchura);
		poder.anadirEstados(normal);
		poder.setEstado("NORMAL");
		return poder;
	}

	public static Plataforma crearPlataforma(String tipo) {
		Plataforma plataforma = new Plataforma();

		Animation quietaAnimation = crearAnimation("Plataformas/" + tipo
				+ "/Quieta/");
		Estado quieta = new Estado("QUIETA", quietaAnimation);
		quieta.setAnchura(1);
		quieta.setAltura(1);

		plataforma.anadirEstados(quieta);
		plataforma.setEstado("QUIETA");

		// Dependientes del tipo
		if(tipo.equals("P")){
			plataforma.mortal = true;
		}
		plataforma.miu = .2;
		return plataforma;
	}

	public static Juego crearJuego(int numero) {
		MP3 cancion;
		Juego juego = new Juego();
		juego.nivel = numero;
		char[][] mapa = cargarMapa(numero);
		juego.mapa = mapa;
		Point posAux = new Point();
		boolean creado;
		for (int i = 0; i < mapa.length; i++) {
			for (int i2 = 0; i2 < mapa[i].length; i2++) {
				posAux.x = Ambiente.TILE_LADO * i2;
				posAux.y = Ambiente.TILE_LADO * i;
				PiezaJuego piezaJuego = null;
				creado = true;
				switch (mapa[i][i2]) {
				case 'Q':
					piezaJuego = crearPlataforma("Q");
					juego.plataformas.add((Plataforma) piezaJuego);
					break;
				case 'W':
					piezaJuego = crearPlataforma("W");
					juego.plataformas.add((Plataforma) piezaJuego);
					break;
				case 'E':
					piezaJuego = crearPlataforma("E");
					juego.plataformas.add((Plataforma) piezaJuego);
					break;
				case 'R':
					piezaJuego = crearPlataforma("R");
					juego.plataformas.add((Plataforma) piezaJuego);
					break;
				case 'T':
					piezaJuego = crearPlataforma("T");
					juego.plataformas.add((Plataforma) piezaJuego);
					break;
				case 'Y':
					piezaJuego = crearPlataforma("Y");
					juego.plataformas.add((Plataforma) piezaJuego);
					break;
				case 'U':
					piezaJuego = crearPlataforma("U");
					juego.plataformas.add((Plataforma) piezaJuego);
					break;
				case 'I':
					piezaJuego = crearPlataforma("I");
					juego.plataformas.add((Plataforma) piezaJuego);
					break;
				case 'O':
					piezaJuego = crearPlataforma("O");
					juego.plataformas.add((Plataforma) piezaJuego);
					break;
				case 'P':
					piezaJuego = crearPlataforma("P");
					juego.plataformas.add((Plataforma) piezaJuego);
					break;
				case 'L':
					piezaJuego = crearLimon();
					juego.limon = (Limon) piezaJuego;
					break;
				case '1':
					piezaJuego = (Taco) crearEnemigo('T');
					juego.enemigos.add((Enemigo) piezaJuego);
					break;
				case 'C':
					piezaJuego = (Cuchillo) crearEnemigo('C');
					juego.enemigos.add((Enemigo) piezaJuego);
					break;
				case 'M':
					piezaJuego = (Mondadientes) crearEnemigo('M');
					juego.enemigos.add((Enemigo) piezaJuego);
					break;
				default:
					creado = false;
					break;
				}
				if (creado) {
					piezaJuego.setX(posAux.x);
					piezaJuego.setY(posAux.y);
					piezaJuego.xInicial = posAux.x;
					piezaJuego.yInicial = posAux.y;
				}
			}
		}
		cancion = new MP3("OST", true, "Sonidos/Juego/" + numero
				+ ".mp3");
		juego.cancion = cancion;
		juego.cancion.play();
		return juego;
	}

	public static char[][] cargarMapa(int numero) {
		char[][] mapa = new char[0][0];
		ArrayList<String> lineas = new ArrayList<String>(0);
		Scanner scanner;
		URL url;
		FileInputStream FIS = null;
		url = ManejaArchivo.getURL("Mapas/"+ numero + ".txt");
		try {
			FIS = new FileInputStream(url.getPath());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		scanner = new Scanner(FIS);
		String lineaAux;
		while (scanner.hasNext()) {
			lineaAux = scanner.nextLine();
			if (lineaAux.charAt(0) != '#') {
				lineas.add(lineaAux);
			}
		}
		scanner.close();
		mapa = new char[lineas.size()][0];
		for (int i = 0; i < mapa.length; i++) {
			mapa[i] = lineas.get(i).toCharArray();
		}
		return mapa;
	}

	/**
	 * Crea una animacion.
	 * 
	 * @param nombre
	 *            es la direccion en la que se buscaran las imagenes. Dicha
	 *            direccion es concatenada a la direccion Recursos/Imagenes
	 * @return Animacion que contiene todas las imagenes dentro de la direccion
	 *         especificada
	 */
	public static Animation crearAnimation(String nombre) {
		Animation animacion = new Animation();
		InputStream inputS;
		URL url;
		Image imageAux;
		for (int i = 0; true; i++) {
			inputS = null;
			try {
				inputS = new FileInputStream(ManejaArchivo.getPath("Imagenes/" + nombre + i + ".gif"));
			} catch (FileNotFoundException e) {
				break;
			}
			url = ManejaArchivo.getURL("Imagenes/" + nombre + i + ".gif");
			imageAux = Toolkit.getDefaultToolkit().createImage(url);
			animacion.addFrame(imageAux, GameMain.DDC);
		}
		return animacion;
	}

	public static Ambiente cargar(int i, GameCanvas gameCanvas) {
		Ambiente ambiente = null;
		switch (i) {
		case -1:
			gameCanvas.fondos.clear();
			ambiente = new Instrucciones();
			break;
		case MENU:
			gameCanvas.fondos.clear();
			ambiente = new Menu(gameCanvas);
			break;
		case 1:
			ambiente = ResourceManager.crearJuego(1);
			ambiente.setZ(0);
			ambiente.setX(4000);
			ambiente.setY(Ambiente.TILE_LADO * 30);
			gameCanvas.fondos.clear();
			URL urlFondo = ManejaArchivo.getURL("Imagenes/Fondos/1/fondo.gif");
			URL urlTaquero = ManejaArchivo.getURL("Imagenes/Fondos/1/taquero.gif");
			Fondo fondo = new Fondo(Toolkit.getDefaultToolkit().createImage(
					urlFondo), Ambiente.TILE_LADO * 240,
					Ambiente.TILE_LADO * 80, Ambiente.TILE_LADO * -100);
			Fondo taquero = new Fondo(Toolkit.getDefaultToolkit().createImage(
					urlTaquero), Ambiente.TILE_LADO * 35,
					Ambiente.TILE_LADO * 40, Ambiente.TILE_LADO * -85);
			taquero.setY(Ambiente.TILE_LADO * 25);
			taquero.setX(Ambiente.TILE_LADO * 100);
			verduras.setY(Ambiente.TILE_LADO * 20);
			verduras.setX(Ambiente.TILE_LADO * 84);
			licuadora.setY(Ambiente.TILE_LADO * 30);
			licuadora.setX(Ambiente.TILE_LADO * 42);
			gameCanvas.anadirFondo(fondo);
			gameCanvas.anadirFondo(taquero);
			gameCanvas.anadirFondo(verduras);
			gameCanvas.anadirFondo(licuadora);
			ambiente.siguiente = 2;
			break;
		case 2:
			ambiente = ResourceManager.crearJuego(2);
			ambiente.setZ(0);
			ambiente.setX(4000);
			ambiente.setY(Ambiente.TILE_LADO * 30);
			gameCanvas.fondos.clear();
			urlFondo = ManejaArchivo.getURL("Imagenes/Fondos/2/fondo.gif");
			URL urlItaliano = ManejaArchivo.getURL("Imagenes/Fondos/2/italiano.gif");
			fondo = new Fondo(Toolkit.getDefaultToolkit().createImage(
					urlFondo), Ambiente.TILE_LADO * 240,
					Ambiente.TILE_LADO * 80, Ambiente.TILE_LADO * -100);
			Fondo italiano = new Fondo(Toolkit.getDefaultToolkit().createImage(
					urlItaliano), Ambiente.TILE_LADO * 25,
					Ambiente.TILE_LADO * 50, Ambiente.TILE_LADO * -80);
			italiano.setY(Ambiente.TILE_LADO * 20);
			italiano.setX(Ambiente.TILE_LADO * 110);
			verduras.setY(Ambiente.TILE_LADO * 30);
			verduras.setX(Ambiente.TILE_LADO * 150);
			licuadora.setY(Ambiente.TILE_LADO * 30);
			licuadora.setX(Ambiente.TILE_LADO * 42);
			gameCanvas.anadirFondo(fondo);
			gameCanvas.anadirFondo(italiano);
			gameCanvas.anadirFondo(verduras);
			gameCanvas.anadirFondo(licuadora);
			gameCanvas.anadirFondo(fondo);
			ambiente.siguiente = 0;
			break;
		case 5:
			URL url_menu = ManejaArchivo.getURL("Imagenes/Fondos/GameOver/fondo.gif");
			fondo = new Fondo(Toolkit.getDefaultToolkit().createImage(
					url_menu), GameMain.ANCHURA,
					GameMain.ALTURA, -1);
			gameCanvas.fondos.clear();
			gameCanvas.anadirFondo(fondo);
			ambiente = new GameOver();
			ambiente.setZ(0);
			ambiente.setX(0);
			ambiente.setY(0);
			break;
		}
		return ambiente;
	}
}
