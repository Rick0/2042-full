package fiuba.algo3.juego.controlador;

import fiuba.algo3.juego.modelo.*;
import fiuba.algo3.juego.vista.*;
import fiuba.algo3.titiritero.*;
import fiuba.algo3.titiritero.vista.*;
import fiuba.algo3.titiritero.audio.Reproductor;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


public class ControladorJuegoAlgo42full implements Runnable, Serializable {


	public ControladorJuegoAlgo42full(boolean activarReproductor, Plano unPlano) {
		this.objetosVivos = new ArrayList<ObjetoVivo>();
		this.dibujables = new ArrayList<Dibujable>();
		this.mouseClickObservadores = new ArrayList<MouseClickObservador>();
		this.keyPressedObservadores = new ArrayList<KeyPressedObservador>();
		this.estaReproductorActivo = activarReproductor;
		if(this.estaReproductorActivo)
			this.reproductor = new Reproductor();
		this.tablaDeVistas = new HashMap<ObjetoUbicable,Imagen>();
		this.generadorDeVista = new GeneradorDeVista();
		this.plano = unPlano;
	}

	public boolean estaEnEjecucion(){
		return this.estaEnEjecucion;
	}
	
	public void comenzarJuego(){
		estaEnEjecucion = true;
		try {
			while(estaEnEjecucion){
				simular();
				actualizarPlano();
				dibujar();
				Thread.sleep(intervaloSimulacion);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void comenzarJuegoAsyn() {
		Thread thread = new Thread(this);
		thread.start();
		if (this.estaReproductorActivo) {
			this.reproductor.encender();
			this.hiloAudio =  new Thread(this.reproductor);
			this.hiloAudio.start();
		}
	}

	/**
	 * Le da comienzo al juego poniendo en marcha el gameloop.
	 * @param cantidadDeCiclos cantidad de ciclos que debe correr el gameloop..  
	 */
	public void comenzarJuego(int cantidadDeCiclos){
		int contador = 0;
		estaEnEjecucion = true;
		try {
			while(contador < cantidadDeCiclos && estaEnEjecucion){
				simular();
				actualizarPlano();
				dibujar();
				Thread.sleep(intervaloSimulacion);
				contador++;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Detiene el juego provocando la salida del gameloop.
	 */
	public void detenerJuego(){
		this.estaEnEjecucion = false;
		if(reproductor!=null)
			this.reproductor.apagar();
	}
	
	public void agregarObjetoVivo(ObjetoVivo objetoVivo){
		objetosVivos.add(objetoVivo);
	}
	
	public void removerObjetoVivo(ObjetoVivo objetoVivo){
		objetosVivos.remove(objetoVivo);
	}

	public void agregarDibujable(Dibujable unDibujable){
		dibujables.add(unDibujable);
	}
	
	public void removerDibujable(Dibujable unDibujable){
		dibujables.remove(unDibujable);
	}
	
	public long getIntervaloSimulacion() {
		return intervaloSimulacion;
	}

	public void setIntervaloSimulacion(long intervaloSimulacion) {
		this.intervaloSimulacion = intervaloSimulacion;
	}
 
	private void dibujar() {
		Iterator<Dibujable> iterador = dibujables.iterator();
		while(iterador.hasNext()){
			Dibujable dibujable = iterador.next();
			dibujable.dibujar(this.superficieDeDibujo);
		}		
		this.superficieDeDibujo.actualizar();
	}
	
	/**
	 * Ejecuta la simulacion recorriendo la coleccion de objetivos vivos.
	 */
	private void simular() {
		this.superficieDeDibujo.limpiar();
		Iterator<ObjetoVivo> iterador = objetosVivos.iterator();
		while(iterador.hasNext()){
			iterador.next().vivir();
		}
	}

	/** Asigna vista a los objetos nuevos, como tambien los agrega a la lista de objetos vivos y dibujables.
	 *  Borra vistas de objetos destruidos, y los saca de la lista de objetos vivos y dibujables.
	 */
	public void actualizarPlano() {

	//	System.out.println(objetosVivos.toString());
		Plano planoDelJuego = this.plano;

		Iterator<ObjetoUbicable> iteradorObjetosAAgregar = planoDelJuego.devolverListaObjetosAAgregar().iterator();
		while (iteradorObjetosAAgregar.hasNext()) {
			ObjetoUbicable unObjeto = iteradorObjetosAAgregar.next();
			Imagen unaVista = this.asignarVista(unObjeto);
			this.tablaDeVistas.put(unObjeto,unaVista);

			if (unaVista.getClass().toString().equals("class fiuba.algo3.juego.vista.VistaNaveExplosion")) {
				this.agregarObjetoVivo((ObjetoVivo)unaVista);
			}

			this.agregarDibujable(unaVista);
			this.agregarObjetoVivo(unObjeto);
		}
		planoDelJuego.devolverListaObjetosAAgregar().clear();

		Iterator<ObjetoUbicable> iteradorObjetosABorrar = planoDelJuego.devolverListaObjetosABorrar().iterator();
		while (iteradorObjetosABorrar.hasNext()) {
			ObjetoUbicable unObjeto = iteradorObjetosABorrar.next();
			Imagen unaVista = this.tablaDeVistas.get(unObjeto);
			this.removerDibujable(unaVista);
			this.removerObjetoVivo(unObjeto);
			this.tablaDeVistas.remove(unObjeto);
		}
		planoDelJuego.devolverListaObjetosABorrar().clear();
	}

	/** Le asigna una vista correspondiente al objeto pedido */
	private Imagen asignarVista(ObjetoUbicable unObjeto) {

		Imagen nuevaVista = this.generadorDeVista.devolverVista(unObjeto);
		nuevaVista.setPosicionable(unObjeto);
		return nuevaVista;
	}

	public SuperficieDeDibujo getSuperficieDeDibujo() {
		return superficieDeDibujo;
	}

	public void setSuperficieDeDibujo(SuperficieDeDibujo superficieDeDibujo) {
		this.superficieDeDibujo = superficieDeDibujo;
	}
	
	/**
	 * Se encarga de derivar el manejo del evento click al objeto vista correspondiente
	 * @param x posicion horizontal del mouse
	 * @param y posicion vertial del mouse
	 */
	public void despacharMouseClick(int x, int y){
		MouseClickObservador mouseClickObservador;
		Iterator<MouseClickObservador> iterador = this.mouseClickObservadores.iterator();
		while(iterador.hasNext()){
			mouseClickObservador = iterador.next();
			mouseClickObservador.MouseClick(x, y);
		}
	}
	
	public void agregarMouseClickObservador(MouseClickObservador unMouseClickObservador){
		this.mouseClickObservadores.add(unMouseClickObservador);
	}
	
	public void removerMouseClickObservador(MouseClickObservador unMouseClickObservador){
		this.mouseClickObservadores.remove(unMouseClickObservador);
	}
	
	/**
	 * Se encarga de derivar el manejo del evento keyPress al objeto vista correspondiente
	 * @param KeyEvent evento
	 */
	public void despacharKeyPress(KeyEvent event){
		for (KeyPressedObservador observador : this.keyPressedObservadores){
			observador.keyPressed(event);
		}
	}
	
	public void agregarKeyPressObservador(KeyPressedObservador unMouseClickObservador){
		this.keyPressedObservadores.add(unMouseClickObservador);
	}
	
	public void removerKeyPressObservador(KeyPressedObservador unMouseClickObservador){
		this.keyPressedObservadores.remove(unMouseClickObservador);
	}
	
	public void persistir(String archivo) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream( archivo ));
			oos.writeObject(this);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static ControladorJuegoAlgo42full restaurar(String archivo) {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo));
			ControladorJuegoAlgo42full controladorAux = (ControladorJuegoAlgo42full) ois.readObject();
			return controladorAux;
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
	
	private long intervaloSimulacion;
	private boolean estaEnEjecucion;
	private List<ObjetoVivo> objetosVivos;
	private List<Dibujable> dibujables;
	private List<MouseClickObservador> mouseClickObservadores;
	private List<KeyPressedObservador> keyPressedObservadores;
	private SuperficieDeDibujo superficieDeDibujo;
	private Reproductor reproductor;
	private Thread hiloAudio;
	private boolean estaReproductorActivo;
	private HashMap<ObjetoUbicable,Imagen> tablaDeVistas;
	private GeneradorDeVista generadorDeVista;
	private Plano plano;
	private static final long serialVersionUID = -6401581783523170282L;


	public void run() {
		this.comenzarJuego();
	}

	public Reproductor getReproductorDeAudio() {
		if (!this.estaReproductorActivo)
			throw new OperacionNoValida();
		return this.reproductor;
	}

}
