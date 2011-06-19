package fiuba.algo3.juego.programa;


import fiuba.algo3.juego.modelo.Bombardero;
import fiuba.algo3.juego.modelo.Helicoptero;
import fiuba.algo3.juego.modelo.Plano;
import fiuba.algo3.juego.modelo.Punto;
import fiuba.algo3.juego.modelo.excepciones.NaveDestruidaError;
import fiuba.algo3.juego.modelo.excepciones.SuperposicionNavesError;
import fiuba.algo3.juego.vista.VistaBombardero;
import fiuba.algo3.juego.vista.VistaHelicoptero;
import fiuba.algo3.titiritero.ControladorJuego;
import fiuba.algo3.titiritero.ObjetoVivo;

/*Clase encargada de generar las flotas del juego
 * tendra distintos metodos que crearan distintos tipos de flotas
 * al crearlas, no solo instanciara las naves
 * sino que ademas se encargara de crear sus vistas y pasarselas
 * al controlador como objetos posicionables y vivos
 */
public class GeneradorFlotas implements ObjetoVivo {
	
	Plano plano;
	ControladorJuego controlador;
	boolean flotaVacia;
	
	public GeneradorFlotas(Plano planoJuego, ControladorJuego control){
		controlador= control;
		plano= planoJuego;
		flotaVacia=false;
	}
	
	/*Genera una flota de tipo1,
	 * que contiene tres helicopteros y
	 * tres bombarderos*/
	public void generarFlota1(){
		int n=0;
		int posicionHeli= 100;
		int posicionBomb= 0;
		while(n<3){
			n=n+1;
			Punto posHeli = new Punto(posicionHeli,400);
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
			Punto posBombardero = new Punto(posicionBomb,100);
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
			posicionHeli=posicionHeli+100;
			posicionBomb=posicionBomb+100;
		}
	}
	
	public void vivir(){
		if ((plano.devolverListaNaves()).isEmpty()){
			flotaVacia=true;
		}
	}
}

