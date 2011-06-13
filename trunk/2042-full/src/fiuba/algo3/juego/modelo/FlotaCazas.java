package fiuba.algo3.juego.modelo;

import java.util.ArrayList;
import java.util.List;

import fiuba.algo3.juego.modelo.excepciones.NaveDestruidaError;
import fiuba.algo3.juego.modelo.excepciones.SuperposicionNavesError;


public class FlotaCazas {

	List<Caza> listaCazas, flotasMostradas;
	int posicionXOriginal, posicionYOriginal;
	Plano plano;


	/* Crea una flota, cuyo lider (es decir, la nave que esta adelante de todas) se colocara en la posicion x,y */
	public FlotaCazas(int centroX, int centroY, Plano planoDeJuego) throws NaveDestruidaError {
	
		List<Caza> array;
		Caza caza;
		int n=0,i=0;
		array = new ArrayList<Caza>();
		this.determinarPlano(planoDeJuego);
		
		while (n < 5) {
			try {
				Punto posicion= new Punto(0, (n + i));
				caza = new Caza(posicion, planoDeJuego);
				n = ( n + 1 );
				array.add(caza);
			} catch (SuperposicionNavesError error) { 
				i = ( i + 1 );
				n = ( n - 1 );
			}
		}

		Punto punto= new Punto(centroX, centroY);
		array.get(0).determinarPosicion(punto);
		array.get(0).determinarPlano(planoDeJuego);
		this.determinarPosicion(centroX, centroY); 
		this.determinarListaCazas(array);		
	}

	private void determinarListaCazas(List<Caza> array) {
		listaCazas = array;
	}

	/* Devuelve la nave lider de la flota de cazas- es decir, la que se encuentra adelante de todas */
	public Caza devolverNaveLider() {
		return listaCazas.get(0);
	}
	
	private void determinarPosicion(int centroX, int centroY) {
		posicionXOriginal = centroX;
		posicionYOriginal = centroY;
	}

	/* Devuelve una lista con todas las naves de la flota; El primer lugar lo ocupara la nave lider,
	 * el segundo y el tercero las naves que le siguen, el cuarto y el quinto los
	 * dos ultimos lugares, son las naves que se encuentran mas atras
	 */
	public List<Caza> devolverListaCazas() {	
		return listaCazas;
	}

	private void determinarPlano(Plano planoDeJuego) {
		this.plano = planoDeJuego;
	}

	/* Se encarga de analizar la formacion de los cazas, y de ir ubicando nuevos cazas
	 * en el plano, si es que todas las lineas de cazas no se mostraron aun
	 */
	public void PosicionarYUbicarNuevoCaza() throws NaveDestruidaError {

		if (listaCazas.get(4).avanzo3Pasos() ) {
			return;
		}
		if (listaCazas.get(1).avanzo3Pasos() ) {
			Punto punto1=new Punto(posicionXOriginal + 10, posicionYOriginal);
			Punto punto2=new Punto(posicionXOriginal - 10, posicionYOriginal);

			listaCazas.get(3).determinarPosicion( punto1 );
			listaCazas.get(4).determinarPosicion( punto2 );
			plano.agregarNave(listaCazas.get(3));
			plano.agregarNave(listaCazas.get(4));
			listaCazas.get(3).determinarPlano(plano);
			listaCazas.get(4).determinarPlano(plano);
			return;
		}
		if (listaCazas.get(0).avanzo3Pasos() ) {
			Punto puntoa=new Punto(posicionXOriginal + 5, posicionYOriginal);
			Punto puntob=new Punto(posicionXOriginal - 5, posicionYOriginal);
			listaCazas.get(2).determinarPosicion( puntoa );
			listaCazas.get(1).determinarPosicion( puntob);
			listaCazas.get(2).determinarPlano(plano);
			listaCazas.get(1).determinarPlano(plano);
			plano.agregarNave(listaCazas.get(1));
			plano.agregarNave(listaCazas.get(2));
		}
	}

}
