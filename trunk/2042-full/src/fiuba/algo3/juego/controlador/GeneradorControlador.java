package fiuba.algo3.juego.controlador;

import fiuba.algo3.juego.modelo.Algo42;
import fiuba.algo3.juego.modelo.Plano;
import fiuba.algo3.juego.modelo.Punto;
import fiuba.algo3.juego.modelo.excepciones.AreaInvalidaError;
import fiuba.algo3.juego.vista.VentanaPrincipal;
import fiuba.algo3.juego.vista.VistaAlgo42;
import fiuba.algo3.juego.vista.VistaPlano;


public class GeneradorControlador {
	
	Plano plano;


	public GeneradorControlador(Plano planoObtenido) {
		plano = planoObtenido;
	}
	
	
	/* Clase encargada de generar el controlador del juego,
	 * devuelve el controlador conteniendo el algo42 y una primera flota de naves.
	 */
	public ControladorJuegoAlgo42full generarControlador() {

		ControladorJuegoAlgo42full controlador = new ControladorJuegoAlgo42full(false, this.plano);
		Algo42 algo42 = null;
		Punto posAlgo = new Punto(250,150);
		try {
			algo42 = new Algo42(posAlgo,plano);
		} catch (AreaInvalidaError e) {
			// No deberia haber creado el algo en una posicion invalida.
			e.printStackTrace();
		}
		VistaPlano vistaPlano = new VistaPlano();
		vistaPlano.setPosicionable(plano);
		controlador.agregarDibujable(vistaPlano);
		controlador.agregarObjetoVivo(vistaPlano);

	//	GeneradorFlotas generador = new GeneradorFlotas(plano, controlador);
		//Aqui el generador recibe su primer flota.
	//	generador.generarFlota1();

		VentanaPrincipal ventana = new VentanaPrincipal(controlador,algo42);
		controlador.setSuperficieDeDibujo(ventana.getSuperficieDeDibujo());
		ventana.setVisible(true);

		VistaAlgo42 vistaAlgo42 = new VistaAlgo42();
		vistaAlgo42.setPosicionable(algo42);

		controlador.agregarDibujable(vistaAlgo42);
		controlador.agregarObjetoVivo(algo42);
		controlador.agregarObjetoVivo(plano);
	//	controlador.agregarObjetoVivo(generador);
		controlador.setIntervaloSimulacion(20);	
		
		return controlador;
	}		
}