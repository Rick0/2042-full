package fiuba.algo3.juego.vista;

import fiuba.algo3.titiritero.vista.Imagen;
import java.util.Random;


public class VistaAvioneta extends Imagen {

	final String imagenPath = "recursos/Nave/NaveNoOperable/";


	public VistaAvioneta() {
		Random generadorRandom = new Random();
		int i = generadorRandom.nextInt(4);

		if (i == 0)
			this.setNombreArchivoImagen(imagenPath+"Avioneta.png");
		else
			this.setNombreArchivoImagen(imagenPath+"Avioneta1.png");
	}

}
