package fiuba.algo3.juego.controlador;

import fiuba.algo3.juego.modelo.Algo42;
import fiuba.algo3.juego.modelo.Plano;
import fiuba.algo3.juego.modelo.Punto;
import fiuba.algo3.juego.modelo.excepciones.AreaInvalidaError;
import fiuba.algo3.juego.vista.VistaAlgo;
import fiuba.algo3.juego.vista.VistaPlano;
import fiuba.algo3.titiritero.ControladorJuego;
import fiuba.algo3.titiritero.ObjetoVivo;
import fiuba.algo3.titiritero.SuperficieDeDibujo;


public class ejemploAlgo implements ObjetoVivo{

		private ControladorJuego controlador = null;
		private Algo42 algo;
		
		public ejemploAlgo(SuperficieDeDibujo superficieDeDibujo){
			Plano plano = new Plano(400, 400);
			Punto posicion= new Punto(20,30);
			try {
				this.algo = new Algo42(posicion,plano);
			} catch (AreaInvalidaError e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			/*
			 * Luego instancio los objetos del framework: una ventana y el controlador
			 */		
			controlador = new ControladorJuego(true);
			controlador.setSuperficieDeDibujo(superficieDeDibujo);
			/*
			 * Instancio un cuadrado para que actue como vista del plano
			 */
			VistaPlano vistaPlano = new VistaPlano();
			vistaPlano.setPosicionable(plano);
			
			/*
			 * Vista del algo
			 */
			VistaAlgo vistaAlgo = new VistaAlgo();
			vistaAlgo.setPosicionable(this.algo);
			
			/*
			 * agrego el algo a la lista de objetos vivios del controlador
			 * para que este le de vida dentro del gameloop
			 */
			controlador.agregarObjetoVivo(algo);
			controlador.agregarObjetoVivo(this);
			
			/*
			 * Agrego los objetos que actuan como vista a la lista de dibujables del controlador
			 * para que sean repintados al final de cada gameloop
			 */
			ControladorMovimientosAlgo contrAlgo= new ControladorMovimientosAlgo(algo);
			controlador.agregarDibujable(vistaPlano);
			controlador.agregarDibujable(vistaAlgo);	
			controlador.agregarKeyPressObservador(contrAlgo);
			/*
			 * finalmente establezco el intervalo de sleep dentro del gameloop
			 * y comienzo a ejecutar
			 */
			controlador.setIntervaloSimulacion(20);
		}
		
		public void comenzar(){
			controlador.comenzarJuegoAsyn();	
		}
		
		public void detener() {
			controlador.detenerJuego();
		}

		public void vivir(){
		
	}
}