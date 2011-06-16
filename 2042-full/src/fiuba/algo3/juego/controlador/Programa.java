package fiuba.algo3.juego.controlador;
import fiuba.algo3.juego.modelo.Algo42;
import fiuba.algo3.juego.modelo.Plano;
import fiuba.algo3.juego.modelo.Punto;
import fiuba.algo3.juego.modelo.excepciones.AreaInvalidaError;
import fiuba.algo3.juego.vista.VistaAlgo;
import fiuba.algo3.juego.vista.VistaPlano;
import fiuba.algo3.juego.vista.VentanaPrincipal;
import fiuba.algo3.titiritero.ControladorJuego;

public class Programa {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Plano plano=new Plano(500,500);
		
		ControladorJuego controlador=new ControladorJuego(true);
		Algo42 algo=null;
		Punto posAlgo= new Punto(200,200);
		try {
			algo = new Algo42(posAlgo,plano);
		} catch (AreaInvalidaError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		VentanaPrincipal ventana=new VentanaPrincipal(controlador,algo);
		VistaPlano vistaPlano=new VistaPlano();
		controlador.setSuperficieDeDibujo(ventana.getSuperficieDeDibujo());
		ventana.setVisible(true);
		vistaPlano.setPosicionable(plano);
		VistaAlgo algoVista=new VistaAlgo();
		algoVista.setPosicionable(algo);
		controlador.agregarDibujable(vistaPlano);
		controlador.agregarDibujable(algoVista);
		controlador.agregarObjetoVivo(algo);
		controlador.setIntervaloSimulacion(20);
		controlador.comenzarJuego();
	}	
}
