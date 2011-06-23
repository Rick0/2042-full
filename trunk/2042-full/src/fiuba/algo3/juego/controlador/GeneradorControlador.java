package fiuba.algo3.juego.controlador;

import fiuba.algo3.juego.modelo.Algo42;
import fiuba.algo3.juego.modelo.Plano;
import fiuba.algo3.juego.modelo.Punto;
import fiuba.algo3.juego.modelo.excepciones.AreaInvalidaError;
import fiuba.algo3.juego.vista.PuntoEntero;
import fiuba.algo3.juego.vista.TextoPuntosYNivel;
import fiuba.algo3.juego.vista.TextoVidaEInventario;
import fiuba.algo3.juego.vista.TextoVidas;
import fiuba.algo3.juego.vista.VentanaPrincipal;
import fiuba.algo3.juego.vista.VistaAlgo42;
import fiuba.algo3.juego.vista.VistaPlano;
import fiuba.algo3.juego.vista.VistaVidaTexto;


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

		VentanaPrincipal ventana = new VentanaPrincipal(controlador,algo42);
		controlador.setSuperficieDeDibujo(ventana.getSuperficieDeDibujo());
		ventana.setVisible(true);

		VistaAlgo42 vistaAlgo42 = new VistaAlgo42();
		vistaAlgo42.setPosicionable(algo42);

		TextoVidaEInventario textoEnergia = new TextoVidaEInventario("Energia:"+"100", algo42);
		VistaVidaTexto energia = new VistaVidaTexto(textoEnergia);
		energia.setPosicionable(new PuntoEntero(75,560));
		
		TextoPuntosYNivel textoPuntosYNivel = new TextoPuntosYNivel("Nivel: 1 Puntos: 0", plano.devolverNivel() );
		VistaVidaTexto vistaTextoPuntosYNivel = new VistaVidaTexto(textoPuntosYNivel);
		vistaTextoPuntosYNivel.setPosicionable(new PuntoEntero(220,20));
		TextoVidas textoVidas = new TextoVidas("Vidas: 3",plano);
		VistaVidaTexto vistaVidas = new VistaVidaTexto(textoVidas);
		vistaVidas.setPosicionable(new PuntoEntero(130,20));
		
		controlador.agregarDibujable(energia);
		controlador.agregarDibujable(vistaVidas);
		controlador.agregarDibujable(vistaAlgo42);
		controlador.agregarDibujable(vistaTextoPuntosYNivel);
		controlador.agregarObjetoVivo(textoPuntosYNivel);
		controlador.agregarObjetoVivo(algo42);
		controlador.agregarObjetoVivo(textoEnergia);
		controlador.agregarObjetoVivo(textoVidas);
		controlador.agregarObjetoVivo(plano);
		controlador.setIntervaloSimulacion(20);	
		
		return controlador;
	}		
}