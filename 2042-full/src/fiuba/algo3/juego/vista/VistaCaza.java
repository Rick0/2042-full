package fiuba.algo3.juego.vista;

import fiuba.algo3.titiritero.vista.Imagen;
import java.util.Random;


public class VistaCaza extends Imagen {

	final String imagenPath = "recursos/Nave/NaveNoOperable/";


	public VistaCaza() {
		Random generadorRandom = new Random();
		int i = generadorRandom.nextInt(4);

		if (i == 0)
			this.setNombreArchivoImagen(imagenPath+"Caza.png");
		else
			this.setNombreArchivoImagen(imagenPath+"Caza1.png");
	}

}
