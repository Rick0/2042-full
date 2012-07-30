package fiuba.algo3.juego.vista;

import fiuba.algo3.titiritero.ObjetoVivo;
import fiuba.algo3.titiritero.vista.Imagen;


public class VistaAlgo42afterImage extends Imagen implements ObjetoVivo {

	int frameCont;
	static int velocidadCambio = 4;
	int velocidadCambioCont;


	public VistaAlgo42afterImage() {
		this.frameCont = 0;
		this.velocidadCambioCont = 0;
		this.setNombreArchivoImagen("recursos/Nave/Algo42/SuperMode/AvionCoolSuperMode0.png");
	}

	@Override
	/* Vivir de la vista Algo42 After-Image */
	public void vivir() {

		pasaUnTiempo();
		actualizarImagen();
	}

	/* Se actualiza la imagen de la After-Image */
	public void actualizarImagen() {

		if (velocidadCambioCont == velocidadCambio) {

			if (frameCont < 10) {
				frameCont++;
				this.setNombreArchivoImagen("recursos/Nave/Algo42/SuperMode/AvionCoolSuperMode"+frameCont+".png");
			}
			else {
				this.setNombreArchivoImagen("recursos/Nave/Algo42/SuperMode/AvionCoolSuperMode10.png");
			}

			velocidadCambioCont = 0;
		}
	}

	/* En cada instante, se actualiza el contador de velocidad de cambio de imagen */
	public void pasaUnTiempo() {

		if (velocidadCambioCont < velocidadCambio) {
			velocidadCambioCont++;
		}
	}

}
