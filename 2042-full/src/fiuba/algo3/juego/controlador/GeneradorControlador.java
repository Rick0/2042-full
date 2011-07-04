package fiuba.algo3.juego.controlador;

import fiuba.algo3.juego.modelo.Algo42;
import fiuba.algo3.juego.modelo.Plano;
import fiuba.algo3.juego.modelo.Punto;
import fiuba.algo3.juego.modelo.excepciones.AreaInvalidaError;
import fiuba.algo3.juego.vista.PuntoEntero;
import fiuba.algo3.juego.vista.TextoEnergiaEInventario;
import fiuba.algo3.juego.vista.TextoPuntosYNivel;
import fiuba.algo3.juego.vista.TextoVidas;
import fiuba.algo3.juego.vista.VentanaPrincipal;
import fiuba.algo3.juego.vista.VistaAlgo42;
import fiuba.algo3.juego.vista.VistaEnergiaEInventario;
import fiuba.algo3.juego.vista.VistaPlano;


public class GeneradorControlador {
	
	Plano plano;
	VentanaPrincipal ventanaDelJuego;


	public GeneradorControlador(Plano planoObtenido) {
		plano = planoObtenido;
	}
	
	
	/* Clase encargada de generar el controlador del juego,
	 * devuelve el controlador conteniendo el algo42 y una primera flota de naves.
	 */
	public ControladorJuegoAlgo42full generarControlador() {

		Algo42 algo42 = null;
		Punto posAlgo = new Punto((int)(this.plano.devolverAncho()/2), (int)(this.plano.devolverAltura()/6));
		try {
			algo42 = new Algo42(posAlgo,plano);
		} catch (AreaInvalidaError e) {
			// No deberia haber creado el algo en una posicion invalida.
			e.printStackTrace();
		}
		plano.introducirAlgo42(algo42);
		
		return this.generarControladorAPartirDePlano();
	}
	
	public ControladorJuegoAlgo42full generarControladorAPartirDePlano() {

		ControladorJuegoAlgo42full controlador = new ControladorJuegoAlgo42full(false, plano);
		Algo42 algo42 = plano.getAlgo42();
		controlador.restaurarPlano();
		
		VistaPlano vistaPlano = new VistaPlano();
		vistaPlano.setPosicionable(plano);
		controlador.agregarDibujable(vistaPlano);
		controlador.agregarObjetoVivo(vistaPlano);

		ventanaDelJuego = new VentanaPrincipal(controlador,algo42);
		controlador.setSuperficieDeDibujo(ventanaDelJuego.getSuperficieDeDibujo());
		ventanaDelJuego.setVisible(true);
		ventanaDelJuego.setAlwaysOnTop(true);
		ventanaDelJuego.setEnabled(true);
		ventanaDelJuego.enableInputMethods(true);
		controlador.setVentanaDelJuego(ventanaDelJuego);

		VistaAlgo42 vistaAlgo42 = new VistaAlgo42();
		vistaAlgo42.setPosicionable(algo42);

		TextoEnergiaEInventario textoEnergiaEInventario = new TextoEnergiaEInventario("Energia:"+"100", algo42);
		VistaEnergiaEInventario energia = new VistaEnergiaEInventario(textoEnergiaEInventario);
		energia.setPosicionable(new PuntoEntero(75,560));
		TextoPuntosYNivel textoPuntosYNivel = new TextoPuntosYNivel("Nivel: 1 Puntos: 0", plano.devolverNivel() );
		VistaEnergiaEInventario vistaTextoPuntosYNivel = new VistaEnergiaEInventario(textoPuntosYNivel);
		vistaTextoPuntosYNivel.setPosicionable(new PuntoEntero(220,20));
		TextoVidas textoVidas = new TextoVidas("Vidas: 3", plano);
		VistaEnergiaEInventario vistaCantidadVidas = new VistaEnergiaEInventario(textoVidas);
		vistaCantidadVidas.setPosicionable(new PuntoEntero(130,20));

		controlador.agregarDibujable(energia);
		controlador.agregarDibujable(vistaCantidadVidas);
		controlador.agregarDibujable(vistaAlgo42);
		controlador.agregarDibujable(vistaTextoPuntosYNivel);
		controlador.agregarObjetoVivo(textoPuntosYNivel);
		controlador.agregarObjetoVivo(algo42);
		controlador.agregarObjetoVivo(textoEnergiaEInventario);
		controlador.agregarObjetoVivo(textoVidas);
		controlador.agregarObjetoVivo(plano);

		controlador.setIntervaloSimulacion(20);	
		
		return controlador;
	}

	public VentanaPrincipal devolverVentanaDelJuego() {
		return ventanaDelJuego;
	}

}
