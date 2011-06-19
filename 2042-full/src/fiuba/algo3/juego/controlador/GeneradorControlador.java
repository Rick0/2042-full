package fiuba.algo3.juego.controlador;

import fiuba.algo3.juego.modelo.Algo42;
import fiuba.algo3.juego.modelo.Plano;
import fiuba.algo3.juego.modelo.Punto;
import fiuba.algo3.juego.modelo.excepciones.AreaInvalidaError;
import fiuba.algo3.juego.programa.GeneradorFlotas;
import fiuba.algo3.juego.vista.VentanaPrincipal;
import fiuba.algo3.juego.vista.VistaAlgo42;
import fiuba.algo3.juego.vista.VistaPlano;
import fiuba.algo3.titiritero.ControladorJuego;

public class GeneradorControlador {
	
	Plano plano;
	
	public GeneradorControlador(Plano planoObtenido){
		plano= planoObtenido;
	}
	
	
	
	public ControladorJuego generarControlador(){

		ControladorJuego controlador = new ControladorJuego(true);
		Algo42 algo42 = null;
		Punto posAlgo = new Punto(200,200);
		try {
			algo42 = new Algo42(posAlgo,plano);
		} catch (AreaInvalidaError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		VistaPlano vistaPlano = new VistaPlano();
		controlador.agregarDibujable(vistaPlano);
		GeneradorFlotas generador= new GeneradorFlotas(plano, controlador);
		generador.generarFlota1();
		
		VentanaPrincipal ventana = new VentanaPrincipal(controlador,algo42);
		controlador.setSuperficieDeDibujo(ventana.getSuperficieDeDibujo());
		ventana.setVisible(true);

		vistaPlano.setPosicionable(plano);
		VistaAlgo42 vistaAlgo42 = new VistaAlgo42();
		vistaAlgo42.setPosicionable(algo42);

		controlador.agregarDibujable(vistaAlgo42);
		controlador.agregarObjetoVivo(algo42);
		controlador.agregarObjetoVivo(plano);
		controlador.agregarObjetoVivo(generador);
		controlador.setIntervaloSimulacion(20);	
		
		return controlador;
	}
		
}