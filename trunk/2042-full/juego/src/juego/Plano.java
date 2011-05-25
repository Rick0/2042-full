package juego;

import java.util.ArrayList;
import excepciones.*;

public class Plano {
	/*Maneja el escenario del nivel, contiene listas con las naves, items
y armas en juego y se encarga de operar sobre ellas e iterarlas.*/
	
	int ancho;
	int altura;
	Algo42 algo42;
	ArrayList<Arma> listaArmas = new ArrayList<Arma>();
	ArrayList<NaveNoOperable> listaNaves = new ArrayList<NaveNoOperable>();
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
	
	public void agregarNaveEliminada(NaveNoOperable nave) throws NaveNoDestruidaError{
	/*Agrega una nave no operable a la lista de naves destruidas*/
		if(!nave.estadoActualDestruida()){
			throw new NaveNoDestruidaError("La nave aun no esta destruida");
		}
		this.listaNavesDestruidas.add(nave);
	}
		
	public void agregarArmaUsada(Arma arma) throws ArmaNoUsadaError {
		
		//Agrega un arma a la lista de armas usadas
		
		if ( !arma.estadoUsado() ) {
			throw new ArmaNoUsadaError("Esta municion no fue usada");
		}
		
		this.listaArmasUsadas.add( arma );
	}
	public Algo42 algo42() {
		
		//Devuelve el algo42 del plano
		
		return algo42;
	}
	
	public ArrayList<Arma> listaArmas(){
		/*Devuelve una lista con las municiones en juego*/

		return listaArmas;
	}
	
	public ArrayList<NaveNoOperable> listaNavesEliminadas(){
	/*Devuelve una lista con las naves eliminadas del juego*/

		return listaNavesDestruidas;
	}
		
	public ArrayList<NaveNoOperable> listaNaves(){
		/*Devuelve una lista con las naves no operables del juego*/
			return listaNaves;
		}
}