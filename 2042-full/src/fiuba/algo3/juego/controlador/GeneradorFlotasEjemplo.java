package fiuba.algo3.juego.controlador;

import fiuba.algo3.juego.modelo.Bombardero;
import fiuba.algo3.juego.modelo.Plano;
import fiuba.algo3.juego.modelo.*;
import fiuba.algo3.juego.modelo.excepciones.NaveDestruidaError;
import fiuba.algo3.juego.modelo.excepciones.SuperposicionNavesError;
import fiuba.algo3.juego.vista.VistaBombardero;
import fiuba.algo3.juego.vista.*;
import fiuba.algo3.titiritero.ObjetoVivo;


/* Clase encargada de generar las flotas del juego
 * tendra distintos metodos que crearan distintos tipos de flotas
 * al crearlas, no solo instanciara las naves
 * sino que ademas se encargara de crear sus vistas y pasarselas
 * al controlador como objetos posicionables y vivos
 */
public class GeneradorFlotasEjemplo implements ObjetoVivo {

	Plano plano;
	ControladorJuegoAlgo42full controlador;
	boolean flotaVacia;


	public GeneradorFlotasEjemplo(Plano planoJuego, ControladorJuegoAlgo42full control) {
		controlador = control;
		plano = planoJuego;
		flotaVacia = false;
	}
	
	/* Genera una flota de tipo1, que contiene tres helicopteros y tres bombarderos */
	public void generarFlota1() {

		int n = 0;
		int posicionHeli = 100;
		int posicionBomb = 50;

		while(n<3) {
			n = n + 1;
			Punto posHeli = new Punto(posicionHeli,550);
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
			Punto posBombardero = new Punto(posicionBomb,300);
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
			
			VistaHelicoptero vistaHeli = new VistaHelicoptero();
			vistaHeli.setPosicionable(unHeli);
			VistaBombardero vistaBombardero = new VistaBombardero();
			vistaBombardero.setPosicionable(unBombardero);
			controlador.agregarDibujable(vistaHeli);
			controlador.agregarDibujable(vistaBombardero);
			controlador.agregarObjetoVivo(unBombardero);
			controlador.agregarObjetoVivo(unHeli);
			posicionHeli = posicionHeli+100;
			posicionBomb = posicionBomb+100;
		}


		Punto posExplo = new Punto(250,400);
		Explorador unExplo = null;
		try {
			unExplo = new Explorador(posExplo,60,plano);
		} catch (SuperposicionNavesError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NaveDestruidaError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		VistaExplorador vistaExplo = new VistaExplorador();
		vistaExplo.setPosicionable(unExplo);
		controlador.agregarDibujable(vistaExplo);
		controlador.agregarObjetoVivo(unExplo);


		Punto posCaza = new Punto(400,450);
		Caza unCaza = null;
		try {
			unCaza = new Caza(posCaza,plano);
		} catch (SuperposicionNavesError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NaveDestruidaError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		VistaCaza vistaCaza = new VistaCaza();
		vistaCaza.setPosicionable(unCaza);
		controlador.agregarDibujable(vistaCaza);
		controlador.agregarObjetoVivo(unCaza);


		Punto posAvioneta = new Punto(450,550);
		Avioneta unaAvioneta = null;
		try {
			unaAvioneta = new Avioneta(posAvioneta,plano);
		} catch (SuperposicionNavesError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NaveDestruidaError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		VistaAvioneta vistaAvioneta = new VistaAvioneta();
		vistaAvioneta.setPosicionable(unaAvioneta);
		controlador.agregarDibujable(vistaAvioneta);
		controlador.agregarObjetoVivo(unaAvioneta);
	}

	public void vivir(){
		if ((plano.devolverListaNaves()).isEmpty()) {
			flotaVacia = true;
		}
	}

}

