package fiuba.algo3.juego.vista;


import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

import fiuba.algo3.juego.modelo.Arma;
import fiuba.algo3.titiritero.ControladorJuego;

public class AsignadorImagenesArmas {
	
	ControladorJuego controlador;

	public AsignadorImagenesArmas(ControladorJuego c){
		controlador= c;
	}
	
	/*Recibe una lista de armas, y las introduce en el controlador
	 * como objetos ubicables y dibujables, si es que estan en su
	 * primer turno
	 */
	public void asignarImagenes( ArrayList<Arma> lista){
		Iterator<Arma> iteradorArmas = lista.iterator();
		while(iteradorArmas.hasNext()) {
			try{
				Arma elemento = iteradorArmas.next(); 
			    if(elemento.primerTurno){
			    	VistaLaserHaciaAbajo vistaLaser= new VistaLaserHaciaAbajo();
					vistaLaser.setPosicionable(elemento);
					controlador.agregarDibujable(vistaLaser);
					controlador.agregarObjetoVivo(elemento);
					elemento.setPrimerTurno(false);
			    }
			}
			catch(ConcurrentModificationException e){
				return;
			}
		}
	}
}
