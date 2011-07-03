package fiuba.algo3.juego.controlador;

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

import fiuba.algo3.juego.modelo.Arma;
import fiuba.algo3.juego.modelo.Item;
import fiuba.algo3.juego.modelo.NaveNoOperable;
import fiuba.algo3.juego.modelo.ObjetoUbicable;
import fiuba.algo3.juego.modelo.Plano;
import fiuba.algo3.juego.vista.GeneradorDeVista;
import fiuba.algo3.juego.vista.PuntoEntero;
import fiuba.algo3.juego.vista.TextoEnergiaEInventario;
import fiuba.algo3.juego.vista.TextoEstadoJuego;
import fiuba.algo3.juego.vista.TextoPuntosYNivel;
import fiuba.algo3.juego.vista.TextoVidas;
import fiuba.algo3.juego.vista.VentanaPrincipal;
import fiuba.algo3.juego.vista.VistaAlgo42;
import fiuba.algo3.juego.vista.VistaEnergiaEInventario;
import fiuba.algo3.juego.vista.VistaEstadoJuego;
import fiuba.algo3.juego.vista.VistaPlano;
import fiuba.algo3.titiritero.Dibujable;
import fiuba.algo3.titiritero.KeyPressedObservador;
import fiuba.algo3.titiritero.MouseClickObservador;
import fiuba.algo3.titiritero.ObjetoVivo;
import fiuba.algo3.titiritero.OperacionNoValida;
import fiuba.algo3.titiritero.SuperficieDeDibujo;
import fiuba.algo3.titiritero.audio.Reproductor;
import fiuba.algo3.titiritero.vista.Imagen;


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

	public boolean estaEnEjecucion() {
		return this.estaEnEjecucion;
	}
	
	public void comenzarJuego() {
		estaEnEjecucion = true;
		try {
			while(estaEnEjecucion) {
				revisarEstadoJuego();
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
	public void comenzarJuego(int cantidadDeCiclos) {
		int contador = 0;
		estaEnEjecucion = true;
		try {
			while(contador < cantidadDeCiclos && estaEnEjecucion) {
				revisarEstadoJuego();
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
	public void detenerJuego() {
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
	private synchronized void actualizarPlano() {

	//	System.out.println(objetosVivos.toString());
		Plano planoDelJuego = this.plano;

		planoDelJuego.getAlgo42().estadoPuedeDisparar(false);

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

		planoDelJuego.getAlgo42().estadoPuedeDisparar(true);

		Iterator<ObjetoUbicable> iteradorObjetosABorrar = planoDelJuego.devolverListaObjetosABorrar().iterator();
		while (iteradorObjetosABorrar.hasNext()) {
			ObjetoUbicable unObjeto = iteradorObjetosABorrar.next();
			Imagen unaVista = this.tablaDeVistas.get(unObjeto);

			if (unaVista != null) {
				if (unaVista.getClass().toString().equals("class fiuba.algo3.juego.vista.VistaNaveExplosion")) {
					this.removerObjetoVivo((ObjetoVivo)unaVista);
				}
			}
			else {
				System.out.println("Vista apunta a null...");
			}

			this.removerDibujable(unaVista);
			this.removerObjetoVivo(unObjeto);
			this.tablaDeVistas.remove(unObjeto);
		}
		planoDelJuego.devolverListaObjetosABorrar().clear();
	}

	/**Revisa si se ha ganado o perdido el juego.
	 * Perder el juego tiene prioridad por sobre ganarlo, asi que se revisa primero.
	 */
	private void revisarEstadoJuego() {

		if (plano.devolverEstadoJuegoPerdido()) {
			estaEnEjecucion = false;
			// poner imagen o ventana de "Perdiste  -  y si tiene un nuevo highscore, poder guardarlo"
		}

		if (plano.devolverEstadoJuegoGanado()) {
			estaEnEjecucion = false;
			// por ahora la condicion de ganar es una arbitraria, llegar al nivel 15
		}
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
	public void despacharMouseClick(int x, int y) {
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
		this.detenerJuego();
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
	
	public void persistirPlano(String archivo) {
		this.plano.persistir(archivo);
	}
	
	public void restaurarPlano(String archivo) {
		this.plano = Plano.restaurar(archivo);
		Iterator <NaveNoOperable>i = plano.devolverListaNaves().iterator();
		while (i.hasNext()) {
			this.plano.agregarObjetoNuevo(i.next());
		}
		Iterator <Arma>j = plano.devolverListaArmas().iterator();
		while (j.hasNext()) {
			this.plano.agregarObjetoNuevo(j.next());
		}
		Iterator <Item>k = plano.devolverListaItems().iterator();
		while (k.hasNext()) {
			this.plano.agregarObjetoNuevo(k.next());
		}
		//Under construction
	}
	
	public Plano getPlano() {
		return this.plano;
	}
	
	public void restaurarJuegoAPartirDePlano(String archivo) {
		this.restaurarPlano(archivo);
		VistaPlano vistaPlano = new VistaPlano();
		vistaPlano.setPosicionable(this.plano);
		this.agregarDibujable(vistaPlano);
		this.agregarObjetoVivo(vistaPlano);

		VentanaPrincipal ventana = new VentanaPrincipal(this, this.plano.getAlgo42());
		this.setSuperficieDeDibujo(ventana.getSuperficieDeDibujo());
		ventana.setVisible(true);
		ventana.setAlwaysOnTop(true);
		ventana.setEnabled(true);
		ventana.enableInputMethods(true);

		VistaAlgo42 vistaAlgo42 = new VistaAlgo42();
		vistaAlgo42.setPosicionable(this.plano.getAlgo42());

		TextoEnergiaEInventario textoEnergiaEInventario = new TextoEnergiaEInventario("Energia:"+"100", this.plano.getAlgo42());
		VistaEnergiaEInventario energia = new VistaEnergiaEInventario(textoEnergiaEInventario);
		energia.setPosicionable(new PuntoEntero(75,560));
		
		TextoPuntosYNivel textoPuntosYNivel = new TextoPuntosYNivel("Nivel: 1 Puntos: 0", this.plano.devolverNivel() );
		VistaEnergiaEInventario vistaTextoPuntosYNivel = new VistaEnergiaEInventario(textoPuntosYNivel);
		vistaTextoPuntosYNivel.setPosicionable(new PuntoEntero(220,20));
		TextoVidas textoVidas = new TextoVidas("Vidas: 3",this.plano);
		VistaEnergiaEInventario vistaCantidadVidas = new VistaEnergiaEInventario(textoVidas);
		vistaCantidadVidas.setPosicionable(new PuntoEntero(130,20));
		TextoEstadoJuego textoEstadoJuego = new TextoEstadoJuego("", this.plano);
		VistaEstadoJuego vistaEstadoJuego = new VistaEstadoJuego(textoEstadoJuego);
		vistaEstadoJuego.setPosicionable(new PuntoEntero(this.plano.devolverAncho()/2 ,this.plano.devolverAltura()/2));
		
		this.agregarObjetoVivo(textoEstadoJuego);
		this.agregarDibujable(vistaEstadoJuego);
		this.agregarDibujable(energia);
		this.agregarDibujable(vistaCantidadVidas);
		this.agregarDibujable(vistaAlgo42);
		this.agregarDibujable(vistaTextoPuntosYNivel);
		this.agregarObjetoVivo(textoPuntosYNivel);
		this.agregarObjetoVivo(this.plano.getAlgo42());
		this.agregarObjetoVivo(textoEnergiaEInventario);
		this.agregarObjetoVivo(textoVidas);
		this.agregarObjetoVivo(this.plano);
		this.setIntervaloSimulacion(20);	
	}

	private static final long serialVersionUID = -6401581783523170282L;
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


	public synchronized void run() {
		this.comenzarJuego();
	}

	public Reproductor getReproductorDeAudio() {
		if (!this.estaReproductorActivo)
			throw new OperacionNoValida();
		return this.reproductor;
	}

}
