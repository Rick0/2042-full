package fiuba.algo3.juego.programa;

import fiuba.algo3.juego.modelo.*;
import fiuba.algo3.juego.modelo.excepciones.*;
//import fiuba.algo3.juego.vista.*;
//import fiuba.algo3.titiritero.vista.*;


public class probandoRefYString {

	/**
	 * @param args
	 */
	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {

		Plano bbb = new Plano(444,444);
		Punto aaa = new Punto(22,22);
		Explorador unAvion = null;
		try {
			unAvion = new Explorador(aaa,4,bbb);
		} catch (SuperposicionNavesError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NaveDestruidaError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String nombre = unAvion.getClass().toString();
		System.out.println(nombre);
		
		int indice = nombre.lastIndexOf(".");
		int largo = nombre.length();
		System.out.println(largo);
		
		int rango = largo-(indice+1);
		System.out.println(rango);
		
		char[] nuevoNombre = new char[rango];
		nombre.getChars(indice+1, nombre.length(), nuevoNombre,0);
		
		String nombreResumido = new String(nuevoNombre);
		System.out.println(nombreResumido);
		
		String nombreClase = new String("fiuba.algo3.juego.vista.Vista"+nombreResumido);
		System.out.println(nombreClase);

		
		// instancio la vista
		Class claseInstanciar = null;
		Object vista = null;
		try {
			claseInstanciar = Class.forName(nombreClase);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			vista = claseInstanciar.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		System.out.println(vista.getClass());
	}

}
