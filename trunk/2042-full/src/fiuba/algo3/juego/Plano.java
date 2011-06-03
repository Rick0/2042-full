package juego;
import java.util.List;
import java.util.ArrayList;

import juego.excepciones.*;


/* Maneja el escenario del nivel, contiene listas con las naves, items
 * y armas en juego y se encarga de operar sobre ellas e iterarlas
 */
public class Plano {

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


	/* Constructor del plano, recibe sus dimensiones
	 * Crea sus listas de armas, naves e items, que se posicionaran en el plano
	 */
	public Plano(int dimensionX,int dimensionY ) {
		ancho=dimensionX;
		altura=dimensionY;
	}

	/* Devuelve la altura del plano */
	public int devolverAltura() {
		return altura;
	}

	/* Devuelve el ancho del plano */
	public int devolverAncho() {
		return ancho;
	}

	/* Introduce un algo42 al plano */
	public void introducirAlgo(Algo42 algo) {
		algo42=algo;
	}

	/* Agrega una referencia a la nave destruida */
	public void agregarNaveEliminada(Nave nave) throws NaveNoDestruidaError {

		if(!(nave.destruida )){
			throw new NaveNoDestruidaError("La nave aun no esta destruida");
		}
		listaNavesDestruidas.add((NaveNoOperable) nave);
	}

	/* Agrega un arma a la lista de armas usadas */
	public void agregarArmaUsada(Arma arma) throws ArmaNoUsadaError {

		if ( !arma.estadoUsado() ) {
			throw new ArmaNoUsadaError("Esta municion no fue usada");
		}
		this.listaArmasUsadas.add( arma );
	}

	public Algo42 algo42() {
		return algo42;
	}

	/* Agrega una municion al plano */
	public void agregarArma(Arma arma) throws ArmaUsadaError {

		if ( arma.estadoUsado() ){
			throw new ArmaUsadaError("Esta municion ya fue usada");
		}
		this.listaArmas.add( arma);
	}

	/* Agrega una nave no operable a la lista de naves */
	public void agregarNave(Nave unaNave) throws NaveDestruidaError {
		
		if ( unaNave.estadoActualDestruida() ) {
			throw new NaveDestruidaError("La nave esta destruida");
		}
		this.listaNaves.add( unaNave );
	}

	/* Agrega un item al area de Juego */
	public void agregarItem(Item item) throws ItemUsadoError {
		
		if (item.usado() ){
			throw new ItemUsadoError("Se trata de agregar al mapa un item usado");
		}
		this.listaItems.add(item);
	}

	/* Uso para el programador */
	public List<Arma> devolverListaArmas() {
		return listaArmas;
	}

	public ArrayList<NaveNoOperable> devolverListaNavesEliminades() {
		return this.listaNavesDestruidas;
	}

}
