package fiuba.algo3.juego.vista;

import fiuba.algo3.titiritero.vista.Imagen;
import java.util.Random;


public class VistaExplorador extends Imagen {

	final String imagenPath = "recursos/Nave/NaveNoOperable/";


	public VistaExplorador() {
		Random generadorRandom = new Random();
		int i = generadorRandom.nextInt(5);

		if (i == 0)
			this.setNombreArchivoImagen(imagenPath+"Explorador1.png");
		else
			this.setNombreArchivoImagen(imagenPath+"Explorador.png");
	}

}
