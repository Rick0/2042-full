package fiuba.algo3.juego.vista;

import fiuba.algo3.titiritero.vista.Imagen;
import java.util.Random;


public class VistaBombardero extends Imagen {

	final String imagenPath = "recursos/Nave/NaveNoOperable/";


	public VistaBombardero() {
		Random generadorRandom = new Random();
		int i = generadorRandom.nextInt(4);

		if (i == 0)
			this.setNombreArchivoImagen(imagenPath+"Bombardero.png");
		else
			this.setNombreArchivoImagen(imagenPath+"Bombardero1.png");
	}

}
