package fiuba.algo3.juego.vista;

import fiuba.algo3.juego.modelo.Arma;
import fiuba.algo3.juego.modelo.ObjetoUbicable;
import fiuba.algo3.titiritero.vista.Imagen;


/* Devuelve una vista dependiendo del objeto ubicable que se le pase */
public class GeneradorDeVista {


	public GeneradorDeVista() {	}


	/* Devuelve una vista dependiendo del objeto ubicable que se le pase */
	@SuppressWarnings("unchecked")
	public Imagen devolverVista(ObjetoUbicable unObjeto) {

		String nombreClase = this.convertirANombreClase(unObjeto);
		Class claseVistaAInstanciar = null;
		Object nuevaVista = null;

		try {
			claseVistaAInstanciar = Class.forName(nombreClase);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			nuevaVista = claseVistaAInstanciar.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		return (Imagen)nuevaVista;
	}

	/* Este metodo obtiene el nombre de la clase del objeto que se le pase */
	public String convertirANombreClase(ObjetoUbicable unObjeto) {

		String nombreClaseRaw, nombreClaseCompleto;
		int indice, largo, cantidad;

		nombreClaseRaw = unObjeto.getClass().toString();
		indice = nombreClaseRaw.lastIndexOf(".");
		largo  = nombreClaseRaw.length();
		cantidad = largo - (indice+1);

		char[] nombreClase = new char[cantidad];
		nombreClaseRaw.getChars(indice+1, largo, nombreClase, 0);
		nombreClaseCompleto = new String(nombreClase);

		if ((nombreClaseCompleto.equals("Laser")) || (nombreClaseCompleto.equals("Cohete")) || (nombreClaseCompleto.equals("TorpedoRastreador"))) {
			if (((Arma) unObjeto).origenAlgo42()) {
				nombreClaseCompleto = ("fiuba.algo3.juego.vista.Vista" + nombreClaseCompleto + "HaciaArriba"); 
			}
			else { nombreClaseCompleto = ("fiuba.algo3.juego.vista.Vista" + nombreClaseCompleto + "HaciaAbajo"); }
		}
		else {
			nombreClaseCompleto = "fiuba.algo3.juego.vista.Vista" + nombreClaseCompleto;
		}

		return nombreClaseCompleto;
	}

}
