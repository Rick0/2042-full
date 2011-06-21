package fiuba.algo3.juego.vista;

import fiuba.algo3.juego.modelo.*;
//import fiuba.algo3.titiritero.*;
import fiuba.algo3.titiritero.vista.*;


/* Devuelve una vista dependiendo del objeto ubicable que se le pase */
public class FactoryVista {

	public FactoryVista() {
	}

	public Imagen devolverVista(ObjetoUbicable unObjeto) {
	
		Imagen nuevaVista = null;
	//	String objetoClass = unObjeto.getClass().toString();



		return nuevaVista;
	}


	// Version anterior
/*	public Imagen devolverVista(ObjetoUbicable unObjeto) {
	
		Imagen nuevaVista = null;
		String objetoClass = unObjeto.getClass().toString();

		switch (objetoClass) {

			// Naves no operables

			case "class fiuba.algo3.juego.modelo.Avioneta": nuevaVista = new VistaAvioneta(); break;

			case "class fiuba.algo3.juego.modelo.Bombardero": nuevaVista = new VistaBombardero(); break;

			case "class fiuba.algo3.juego.modelo.Caza": nuevaVista = new VistaCaza(); break;

			case "class fiuba.algo3.juego.modelo.Civil": nuevaVista = new VistaCivil(); break;

			case "class fiuba.algo3.juego.modelo.Explorador": nuevaVista = new VistaExplorador(); break;

			case "class fiuba.algo3.juego.modelo.Helicoptero": nuevaVista = new VistaHelicoptero(); break;

			// Items

			case "class fiuba.algo3.juego.modelo.ArmaAbandonada": nuevaVista = new VistaArmaAbandonada(); break;

			case "class fiuba.algo3.juego.modelo.TanqueEnergia": nuevaVista = new VistaTanqueEnergia(); break;

			// Armas

			case "class fiuba.algo3.juego.modelo.Laser": if (unObjeto.origenAlgo42()) { nuevaVista = new VistaLaserHaciaArriba(); break; }
														 else { nuevaVista = new VistaLaserHaciaAbajo(); break; }

			case "class fiuba.algo3.juego.modelo.Cohete": if (unObjeto.origenAlgo42()) { nuevaVista = new VistaCoheteHaciaArriba(); break; }
														  else { nuevaVista = new VistaCoheteHaciaAbajo(); break; }

			case "class fiuba.algo3.juego.modelo.TorpedoSimple": nuevaVista = new VistaTorpedoSimple(); break;

			case "class fiuba.algo3.juego.modelo.TorpedoRastreador": if (unObjeto.origenAlgo42()) { nuevaVista = new VistaTorpedoRastreadorHaciaArriba(); break; }
																	 else { nuevaVista = new VistaTorpedoRastreadorHaciaAbajo(); break; }

			case "class fiuba.algo3.juego.modelo.TorpedoAdaptable": nuevaVista = new VistaTorpedoAdaptable(); break;

			// Si no es ninguna de las anteriores... no deberia entrar por aca

			default: break;
		}

		return nuevaVista;
	}*/




















}