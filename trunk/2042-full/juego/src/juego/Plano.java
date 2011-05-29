package juego;

import java.util.List;
import java.util.ArrayList;
import excepciones.*;


public class Plano {
	/*Maneja el escenario del nivel, contiene listas con las naves, items
y armas en juego y se encarga de operar sobre ellas e iterarlas.*/
	
	int ancho;
	int altura;
	Algo42 algo42;
	ArrayList<Arma> listaArmas = new ArrayList<Arma>();
	ArrayList<Nave> listaNaves = new ArrayList<Nave>();
	ArrayList<Item> listaItems = new ArrayList<Item>();
	ArrayList<NaveNoOperable> listaNavesDestruidas = new ArrayList<NaveNoOperable>();
	ArrayList<Item> listaItemsUsados = new ArrayList<Item>();
	ArrayList<Arma> listaArmasUsadas = listaArmas = new ArrayList<Arma>();
	Nivel nivel;
	
	public Plano(int dimensionX,int dimensionY ) {
		/*Constructor del plano, recibe sus dimensiones
		 * Crea sus listas de armas, naves e items, que se
		 * posicionaran en el plano */
			ancho=dimensionX;
			altura=dimensionY;
			
		}
	public int devolverAltura(){
		/*Devuelve la altura del plano*/

		return altura;
	}
	
	public int devolverAncho(){
		/*Devuelve el ancho del plano*/

		return ancho;
	}
	
	public void introducirAlgo(Algo42 algo){
		/*Introduce un algo42 al plano*/

		algo42=algo;
	}
	
	public void agregarNaveEliminada(Nave nave) {
	//Agrega una referencia a la nave destruida
		listaNavesDestruidas.add((NaveNoOperable) nave);
		
	}
	
	public void agregarArmaUsada(Arma arma) throws ArmaNoUsadaError {
		
		//Agrega un arma a la lista de armas usadas
		
		if ( !arma.estadoUsado() ) {
			throw new ArmaNoUsadaError("Esta municion no fue usada");
		}
		
		this.listaArmasUsadas.add( arma );
	}


	public Algo42 algo42() {
		return algo42;
	}

	public void agregarArma(Arma arma) throws ArmaUsadaError {
		//Agrega una municion al plano.

		
		if ( arma.estadoUsado() ){
			throw new ArmaUsadaError("Esta municion ya fue usada");
		}
		this.listaArmas.add( arma);
		
	}
	
	public void agregarNave(Nave unaNave) {
		//Agrega una nave no operable a la lista de naves
		if ( unaNave.estadoActualDestruida() ) {
			new NaveDestruidaError("La nave esta destruida");
		}
		this.listaNaves.add( unaNave );
		
	}
	
	public void agregarItem(Item item) throws ItemUsadoError {
		// Agrega un item al area de Juego
		if (item.usado() ){
			throw new ItemUsadoError("Se trata de agregar al mapa un item usado");
		}
		this.listaItems.add(item);
	}
	
	public List<Arma> devolverListaArmas() {
		// uso para el programador
		return listaArmas;
		
	}
	
	public ArrayList<NaveNoOperable> devolverListaNavesEliminades() {
		return this.listaNavesDestruidas;
	}

}