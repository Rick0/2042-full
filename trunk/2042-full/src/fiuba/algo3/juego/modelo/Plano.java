package fiuba.algo3.juego.modelo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import fiuba.algo3.juego.modelo.excepciones.ArmaNoUsadaError;
import fiuba.algo3.juego.modelo.excepciones.ArmaUsadaError;
import fiuba.algo3.juego.modelo.excepciones.ItemNoUsadoError;
import fiuba.algo3.juego.modelo.excepciones.ItemUsadoError;
import fiuba.algo3.juego.modelo.excepciones.NaveDestruidaError;
import fiuba.algo3.juego.modelo.excepciones.NaveNoDestruidaError;
import fiuba.algo3.titiritero.ObjetoVivo;
import fiuba.algo3.titiritero.Posicionable;


/* Maneja el escenario del nivel, contiene listas con las naves, items
 * y armas en juego y se encarga de operar sobre ellas e iterarlas
 */
public class Plano implements Posicionable, ObjetoVivo, Serializable {

	private static final long serialVersionUID = -6986081234340150184L;
	int ancho;
	int altura;
	int vidas;
	
	Algo42 algo42;
	ArrayList<NaveNoOperable> listaNavesAI = new ArrayList<NaveNoOperable>();
	ArrayList<NaveNoOperable> listaNaves = new ArrayList<NaveNoOperable>();
	ArrayList<Item> listaItems = new ArrayList<Item>();
	ArrayList<Arma> listaArmas = new ArrayList<Arma>();
	
	ArrayList<NaveNoOperable> listaNavesAIDestruidas = new ArrayList<NaveNoOperable>();
	ArrayList<NaveNoOperable> listaNavesDestruidas = new ArrayList<NaveNoOperable>();
	ArrayList<Item> listaItemsUsados = new ArrayList<Item>();
	ArrayList<Arma> listaArmasUsadas = new ArrayList<Arma>();
	
	Nivel nivel = new Nivel();
	ArrayList<ObjetoUbicable> listaObjetosAAgregar = new ArrayList<ObjetoUbicable>();
	ArrayList<ObjetoUbicable> listaObjetosABorrar  = new ArrayList<ObjetoUbicable>();
	boolean juegoPerdido;
	boolean juegoGanado;


	/* Constructor del plano, recibe sus dimensiones
	 * Crea sus listas de armas, naves e items, que se posicionaran en el plano
	 */
	public Plano(int dimensionX,int dimensionY) {
		ancho  = dimensionX;
		altura = dimensionY;
		this.setVidas(3);
		juegoPerdido = false;
		juegoGanado = false;
	}
	
	public Plano() {
		
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
	public void introducirAlgo42(Algo42 naveJugador) {
		this.algo42 = naveJugador;
	}

	/* Agrega una referencia a la nave destruida */
	public void agregarNaveAIEliminada(NaveNoOperable nave) throws NaveNoDestruidaError {

		if (!nave.estaDestruida) {
			throw new NaveNoDestruidaError("La nave aun no esta destruida");
		}
		listaNavesAIDestruidas.add(nave);
	}
	
	/* Agrega una referencia a la nave destruida */
	public void agregarNaveEliminada(Nave nave) throws NaveNoDestruidaError {

		if (((!nave.estaDestruida) && (!((NaveNoOperable)nave).fueraDelPlano))) {
			throw new NaveNoDestruidaError("La nave aun no esta destruida");
		}
		listaNavesDestruidas.add((NaveNoOperable) nave);
	}

	/* Agrega un arma a la lista de armas usadas */
	public void agregarArmaUsada(Arma arma) throws ArmaNoUsadaError {

		if ( !arma.fueUsado() ) {
			throw new ArmaNoUsadaError("Esta municion no fue usada");
		}
		this.listaArmasUsadas.add(arma);
	}

	/*Agrega un item del juego a la lista de items usados.*/
	public void agregarItemUsado(Item item) {

		if (!item.fueUsado()) {
			throw new ItemNoUsadoError();
		}
		listaItemsUsados.add(item);
	}

	/* Devuelve la unica nave operable, el Algo42 */
	public Algo42 devolverAlgo42() {
		return this.algo42;
	}

	/* Decrece una vida del juego, consecuencia de que se destruyo el Algo42 
	 * Si se pierden las 3 vidas, se pierde el juego
	 * Se borran todas las armas en el plano cuando muere Algo42
	 */
	public void perderUnaVida() {

		vidas = vidas - 1;

		Iterator<Arma> iteradorArmas = listaArmas.iterator();
		while(iteradorArmas.hasNext()) {
		    Arma unArma = iteradorArmas.next(); 
		    if (!unArma.fueUsado()) {
				unArma.pasaAEstarUsado();
				try {
					this.agregarArmaUsada(unArma);
				} catch (ArmaNoUsadaError e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		if ((vidas <= 0) && (!juegoGanado)) {
			juegoPerdido = true;
		}
	}

	/* Agrega una municion al plano */
	public void agregarArma(Arma arma) throws ArmaUsadaError {

		if ( arma.fueUsado() ) {
			throw new ArmaUsadaError("Esta municion ya fue usada");
		}
		this.listaArmas.add(arma);
	}

	/* Agrega una nave no operable a la lista de naves */
	public void agregarNave(NaveNoOperable unaNave) throws NaveDestruidaError {
		
		if ( unaNave.estadoActualDestruida() ) {
			throw new NaveDestruidaError("La nave esta destruida");
		}
		this.listaNaves.add( unaNave );
	}

	/* Agrega una nave (seguramente after-image) a la lista de naves jugador */
	public void agregarNaveAI(NaveNoOperable unaNave) throws NaveDestruidaError {
		
		if ( unaNave.estadoActualDestruida() ) {
			throw new NaveDestruidaError("La nave esta destruida");
		}
		this.listaNavesAI.add( unaNave );
	}

	/* Agrega un item al area de Juego */
	public void agregarItem(Item item) throws ItemUsadoError {
		
		if (item.fueUsado()) {
			throw new ItemUsadoError("Se trata de agregar al mapa un item usado");
		}
		this.listaItems.add(item);
	}

	/* Agrega un objeto nuevo a la lista de objetos nuevos */
	public void agregarObjetoNuevo(ObjetoUbicable objeto) {
		this.listaObjetosAAgregar.add(objeto);
	}

	/* Agrega un objeto destruido a la lista de objetos a borrar */
	public void agregarObjetoDestruido(ObjetoUbicable objeto) {
		this.listaObjetosABorrar.add(objeto);
	}

	public ArrayList<Arma> devolverListaArmas() {
		return listaArmas;
	}
	
	public ArrayList<Item> devolverListaItems() {
		return listaItems;
	}
	
	public ArrayList<NaveNoOperable> devolverListaNavesAI() {
		return this.listaNavesAI;
	}
	
	public ArrayList<NaveNoOperable> devolverListaNaves() {
		return this.listaNaves;
	}

	public ArrayList<NaveNoOperable> devolverListaNavesEliminades() {
		return this.listaNavesDestruidas;
	}

	public ArrayList<ObjetoUbicable> devolverListaObjetosAAgregar() {
		return this.listaObjetosAAgregar;
	}

	public ArrayList<ObjetoUbicable> devolverListaObjetosABorrar() {
		return this.listaObjetosABorrar;
	}

	public int devolverCantidadNaves() {
		return this.listaNaves.size();
	}
	
	public boolean hayNavesEnemigas() {

		if (listaNaves.size() > 0)
			return true;
		return false;
	}

	public int devolverNumeroDeNivel() {
		return (nivel.devolverNumeroNivel());
	}
	
	public Nivel devolverNivel() {
		return this.nivel;
	}
	
	public int getVidas() {
		return vidas;
	}

	public void setVidas(int vidas) {
		this.vidas = vidas;
	}

	public boolean devolverEstadoJuegoGanado() {
		return juegoGanado;
	}

	public boolean devolverEstadoJuegoPerdido() {
		return juegoPerdido;
	}

	public void persistir(String archivo) {

		try {
			ObjectOutputStream oos = new ObjectOutputStream(
			new FileOutputStream( archivo ));
			oos.writeObject(this);
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Plano restaurar(String archivo) {
		
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo));
			Plano planoAux = (Plano) ois.readObject();
			planoAux.listaObjetosAAgregar.clear();
			ois.close();
			return planoAux;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void vivir() {

		// Eliminacion de naves, armas e items que ya no estan vigentes en el area de juego.
		Iterator<Arma> iteradorArmasUsadas = listaArmasUsadas.iterator();
		Iterator<Item> iteradorItemsUsados = listaItemsUsados.iterator();
		Iterator<NaveNoOperable> iteradorNavesDestruidas = listaNavesDestruidas.iterator();
		Iterator<NaveNoOperable> iteradorNavesAIDestruidas = listaNavesAIDestruidas.iterator();

		while (iteradorArmasUsadas.hasNext()) {
			Arma elemento = iteradorArmasUsadas.next(); 
			listaArmas.remove(elemento);
			listaObjetosABorrar.add(elemento);
		}
		while (iteradorItemsUsados.hasNext()) {
			Item elemento = iteradorItemsUsados.next(); 
			listaItems.remove(elemento);
			listaObjetosABorrar.add(elemento);
		}
		while (iteradorNavesDestruidas.hasNext()) {
			NaveNoOperable elemento = iteradorNavesDestruidas.next(); 
			listaNaves.remove(elemento);
			listaObjetosABorrar.add(elemento);
		}
		while (iteradorNavesAIDestruidas.hasNext()) {
			NaveNoOperable elemento = iteradorNavesAIDestruidas.next(); 
			listaNavesAI.remove(elemento);
			listaObjetosABorrar.add(elemento);
		}

		this.nivel.actuarCon(listaNavesDestruidas, listaItemsUsados);
		if ((nivel.devolverNumeroNivel() >= 15) && (!juegoPerdido))
			juegoGanado = true;
		
		/// ---
		if (this.nivel.devolverPuntosActuales() > 10  &&  this.devolverAlgo42().superMode == 0)
			this.devolverAlgo42().entrarASuperMode();

		listaArmasUsadas.clear();
		listaItemsUsados.clear();
		listaNavesDestruidas.clear();
		listaNavesAIDestruidas.clear();
	}

	/* En cada turno, se debe invocar este metodo para revisar 
	 * las posiciones de las naves, las armas y los items, para 
	 * ver si hay alguna accion que realizar. Devuelve la lista 
	 * de naves que el algo42 elimino */
	public void revisarEventos() {

		Iterator<Item> iteradorItem = listaItems.iterator();
		Iterator<Arma> iteradorArmas = listaArmas.iterator();
		Iterator<NaveNoOperable> iteradorNaveEnemiga = listaNaves.iterator();
		Iterator<NaveNoOperable> iteradorNaveJugador = listaNavesAI.iterator();

		while (iteradorItem.hasNext()) {
		    Item elemento = iteradorItem.next(); 
		    elemento.vivir();
		}
		while (iteradorArmas.hasNext()) {
		    Arma elemento = iteradorArmas.next(); 
		    elemento.vivir();
		}
		while (iteradorNaveEnemiga.hasNext()) {
		    NaveNoOperable elemento = iteradorNaveEnemiga.next(); 
		    elemento.vivir();
		}
		while (iteradorNaveJugador.hasNext()) {
			NaveNoOperable elemento = iteradorNaveJugador.next(); 
		    elemento.vivir();
		}

		this.vivir();
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
