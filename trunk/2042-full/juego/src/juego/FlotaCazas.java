package juego;

import java.util.ArrayList;
import java.util.List;

import excepciones.*;

public class FlotaCazas {

	List<Caza> listaCazas, flotasMostradas;
	int posicionXOriginal, posicionYOriginal;
	Plano plano;
	
	public FlotaCazas(int centroX, int centroY, Plano planoDeJuego) {
		//"Crea una flota, cuyo lider (es decir, la nave que esta adelante de todas) se colocara
		//en la posicion x,y. "
			List<Caza> array;
			Caza caza;
			int n=0,i=0;
			array = new ArrayList<Caza>();
			
			this.determinarPlano(planoDeJuego);
			
			while (n < 5) {
				try {
					caza = new Caza(0, n+i, planoDeJuego);
					n = (n+1);
					array.add(caza);
				} catch (SuperposicionNavesError error){ 
				i= (i+1);
				n= (n-1);
				}
			}
			array.get(1).determinarPosicion(centroX, centroY);
			array.get(1).determinarPlano(planoDeJuego);
			this.determinarPosicion(centroX, centroY); 
			this.determinarListaCazas(array);
	
		
	}

	private void determinarListaCazas(List<Caza> array) {
		
		listaCazas = array;
		
	}
	
	public Caza devolverNaveLider() {
	//Devuelve la nave lider de la flota de cazas- es decir, la que se encuentra adelante de todas.
	
		return listaCazas.get(1);
		
	}
	
	private void determinarPosicion(int centroX, int centroY) {
		posicionXOriginal = centroX;
		posicionYOriginal = centroY;
		
	}
	
	public List<Caza> devolverListaCazas() {
		/*Devuelve una lista con todas las naves de la flota; El primer lugar lo ocupara la nave lider,
		el segundo y el tercero las naves que le siguen, el cuarto y el quinto los
		dos ultimos lugares, son las naves que se encuentran mas atras.*/
			return listaCazas;
	}

	private void determinarPlano(Plano planoDeJuego) {
		
		this.plano = planoDeJuego;
		
	}
	
	public void PosicionarYUbicarNuevoCaza() {

	/*Se encarga de analizar la formacion de los cazas, y de ir ubicando nuevos cazas
	en el plano, si es que todas las lineas de cazas no se mostraron aun- .*/

		if (listaCazas.get(5).avanzo3Pasos() ) {
			return;
		}
		if (listaCazas.get(2).avanzo3Pasos() ) {
			listaCazas.get(4).determinarPosicion( posicionXOriginal + 10 , posicionYOriginal );
			listaCazas.get(5).determinarPosicion(posicionXOriginal-10,posicionYOriginal);
			plano.agregarNave(listaCazas.get(4));
			plano.agregarNave(listaCazas.get(5));
			listaCazas.get(4).determinarPlano(plano);
			listaCazas.get(5).determinarPlano(plano);
			return;
		}
		if (listaCazas.get(1).avanzo3Pasos() ) {
			listaCazas.get(3).determinarPosicion(posicionXOriginal + 5, posicionYOriginal );
			listaCazas.get(2).determinarPosicion(posicionXOriginal - 5, posicionYOriginal);
			listaCazas.get(3).determinarPlano(plano);
			listaCazas.get(2).determinarPlano(plano);
			plano.agregarNave(listaCazas.get(2));
			plano.agregarNave(listaCazas.get(3));
		}
	}
	
}
