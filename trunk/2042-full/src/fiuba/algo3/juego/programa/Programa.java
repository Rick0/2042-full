package fiuba.algo3.juego.programa;

import fiuba.algo3.juego.modelo.*;
import fiuba.algo3.juego.modelo.excepciones.*;
import fiuba.algo3.juego.vista.*;
import fiuba.algo3.titiritero.ControladorJuego;


public class Programa {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Plano plano = new Plano(500,500);
		ControladorJuego controlador = new ControladorJuego(true);
		Algo42 algo42 = null;
		Punto posAlgo = new Punto(200,200);
		try {
			algo42 = new Algo42(posAlgo,plano);
		} catch (AreaInvalidaError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Punto posTanque = new Punto(200,400);
		ArmaAbandonada unTanque = new ArmaAbandonada(posTanque,plano);
		Punto posHeli = new Punto(300,495);
		Helicoptero unHeli = null;
		try {
			unHeli = new Helicoptero(posHeli,plano);
		} catch (SuperposicionNavesError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NaveDestruidaError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Punto posBombardero = new Punto(300,400);
		Bombardero unBombardero = null;
		try {
			unBombardero = new Bombardero(posBombardero, plano);
		} catch (SuperposicionNavesError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NaveDestruidaError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		VentanaPrincipal ventana = new VentanaPrincipal(controlador,algo42);
		VistaPlano vistaPlano = new VistaPlano();
		controlador.setSuperficieDeDibujo(ventana.getSuperficieDeDibujo());
		ventana.setVisible(true);

		vistaPlano.setPosicionable(plano);
		VistaAlgo42 vistaAlgo42 = new VistaAlgo42();
		vistaAlgo42.setPosicionable(algo42);
		VistaArmaAbandonada vistaTanque = new VistaArmaAbandonada();
		vistaTanque.setPosicionable(unTanque);
		VistaHelicoptero vistaHeli = new VistaHelicoptero();
		vistaHeli.setPosicionable(unHeli);
		VistaBombardero vistaBombardero = new VistaBombardero();
		vistaBombardero.setPosicionable(unBombardero);

		controlador.agregarDibujable(vistaPlano);
		controlador.agregarDibujable(vistaAlgo42);
		controlador.agregarDibujable(vistaTanque);
		controlador.agregarDibujable(vistaHeli);
		controlador.agregarDibujable(vistaBombardero);
		controlador.agregarObjetoVivo(algo42);
		controlador.agregarObjetoVivo(unTanque);
		controlador.agregarObjetoVivo(unHeli);
		controlador.agregarObjetoVivo(unBombardero);


		controlador.setIntervaloSimulacion(20);
		controlador.comenzarJuego();
	}

}
