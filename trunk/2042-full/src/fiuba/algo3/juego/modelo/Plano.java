package fiuba.algo3.juego.modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import fiuba.algo3.titiritero.Posicionable;
import fiuba.algo3.juego.modelo.excepciones.AlgoSeAtacaASiMismoError;
import fiuba.algo3.juego.modelo.excepciones.ArmaNoUsadaError;
import fiuba.algo3.juego.modelo.excepciones.ArmaUsadaError;
import fiuba.algo3.juego.modelo.excepciones.AtaqueEntreNavesNoOperables;
import fiuba.algo3.juego.modelo.excepciones.ItemNoUsadoError;
import fiuba.algo3.juego.modelo.excepciones.ItemUsadoError;
import fiuba.algo3.juego.modelo.excepciones.NaveDestruidaError;
import fiuba.algo3.juego.modelo.excepciones.NaveNoDestruidaError;


/* Maneja el escenario del nivel, contiene listas con las naves, items
 * y armas en juego y se encarga de operar sobre ellas e iterarlas
 */
public class Plano implements Posicionable {

	int ancho;
	int altura;
	Algo42 algo42;
	ArrayList<Arma> listaArmas = new ArrayList<Arma>();
	ArrayList<NaveNoOperable> listaNaves = new ArrayList<NaveNoOperable>();
	ArrayList<Item> listaItems = new ArrayList<Item>();
	ArrayList<NaveNoOperable> listaNavesDestruidas = new ArrayList<NaveNoOperable>();
	ArrayList<Item> listaItemsUsados = new ArrayList<Item>();
	ArrayList<Arma> listaArmasUsadas = new ArrayList<Arma>();
	Nivel nivel = new Nivel();


	/* Constructor del plano, recibe sus dimensiones
	 * Crea sus listas de armas, naves e items, que se posicionaran en el plano
	 */
	public Plano(int dimensionX,int dimensionY) {
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
	public void introducirAlgo42(Algo42 algo) {
		algo42=algo;
	}

	/* Agrega una referencia a la nave destruida */
	public void agregarNaveEliminada(Nave nave) throws NaveNoDestruidaError {

		if(!(nave.estaDestruida)) {
			throw new NaveNoDestruidaError("La nave aun no esta destruida");
		}
		listaNavesDestruidas.add((NaveNoOperable) nave);
	}

	/* Agrega un arma a la lista de armas usadas */
	public void agregarArmaUsada(Arma arma) throws ArmaNoUsadaError {

		if ( !arma.fueUsado() ) {
			throw new ArmaNoUsadaError("Esta municion no fue usada");
		}
		this.listaArmasUsadas.add( arma );
	}

	public Algo42 getAlgo42() {
		return algo42;
	}

	/* Agrega una municion al plano */
	public void agregarArma(Arma arma) throws ArmaUsadaError {

		if ( arma.fueUsado() ){
			throw new ArmaUsadaError("Esta municion ya fue usada");
		}
		this.listaArmas.add( arma);
	}

	/* Agrega una nave no operable a la lista de naves */
	public void agregarNave(NaveNoOperable unaNave) throws NaveDestruidaError {
		
		if ( unaNave.estadoActualDestruida() ) {
			throw new NaveDestruidaError("La nave esta destruida");
		}
		this.listaNaves.add( unaNave );
	}

	/* Agrega un item al area de Juego */
	public void agregarItem(Item item) throws ItemUsadoError {
		
		if (item.fueUsado() ){
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

	/*Agrega un item del juego a la lista de items usados.*/
	public void agregarItemUsado(Item item) {

			if(!(item.fueUsado())){
				throw new ItemNoUsadoError();
			}
			listaItemsUsados.add(item);
		
	}
	
	/*Devuelve el nivel actual*/
	public int devolverNivel(){
		return (nivel.devolverNumeroNivel());
	}
	
	/*En cada turno, se debe invocar este metodo para revisar 
	 * las posiciones de las naves, las armas y los items, para 
	 * ver si hay alguna accion que realizar. Devuelve la lista 
	 * de naves que el algo42 elimino*/
	public void revisarEventos() throws AlgoSeAtacaASiMismoError, AtaqueEntreNavesNoOperables, ArmaNoUsadaError{

		Iterator<Item> iteradorItem = listaItems.iterator();
		Iterator<Arma> iteradorArmas = listaArmas.iterator();
		Iterator<NaveNoOperable> iteradorNaveEnemiga = listaNaves.iterator();

		while(iteradorItem.hasNext()) {
		    Item elemento = iteradorItem.next(); 
		    elemento.vivir();
		}

		while(iteradorArmas.hasNext()) {
		    Arma elemento = iteradorArmas.next(); 
		    elemento.vivir();
		}

		while(iteradorNaveEnemiga.hasNext()) {
		    NaveNoOperable elemento = iteradorNaveEnemiga.next(); 
		    elemento.vivir();
		}

		//Eliminacion de naves, armas e items que ya no estan vigentes en el area de juego.
		Iterator<Arma> iteradorArmasUsadas = listaArmasUsadas.iterator();
		Iterator<Item> iteradorItemsUsados = listaItemsUsados.iterator();
		Iterator<NaveNoOperable> iteradorNavesDestruidas = listaNavesDestruidas.iterator();

		while(iteradorItemsUsados.hasNext()) {
			Item elemento = iteradorItemsUsados.next(); 
			listaItems.remove(elemento);
		}
		while(iteradorNavesDestruidas.hasNext()) {
			NaveNoOperable elemento = iteradorNavesDestruidas.next(); 
			listaNaves.remove(elemento);
		}
		while(iteradorArmasUsadas.hasNext()) {
			Arma elemento = iteradorArmasUsadas.next(); 
			listaArmas.remove(elemento);
		}
		nivel.actuarCon(listaNavesDestruidas);
	}


	/* Metodos para implementar la interfaz Posicionable
	 * @see fiuba.algo3.titiritero.Posicionable#getX()
	 * @see fiuba.algo3.titiritero.Posicionable#getY()
	 */
	@Override
	public int getX() {
		return 0;
	}

	@Override
	public int getY() {
		return 0;
	}

}
