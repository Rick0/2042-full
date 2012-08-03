package fiuba.algo3.juego.vista;

import fiuba.algo3.titiritero.ObjetoVivo;
import fiuba.algo3.titiritero.vista.Imagen;


public class VistaGranExplosion extends Imagen implements ObjetoVivo {

	int frameCont;
	static int velocidadCambio = 4;
	int velocidadCambioCont;


	public VistaGranExplosion() {
		this.frameCont = 0;
		this.velocidadCambioCont = 0;
		this.setNombreArchivoImagen("recursos/Nave/Explosion/GranExplosion/Explosion0.png");
	}

	@Override
	/* Vivir de la vista gran explosion */
	public void vivir() {

		pasaUnTiempo();
		actualizarImagen();
	}

	/* Se actualiza la imagen de la gran explosion */
	public void actualizarImagen() {

		if (velocidadCambioCont == velocidadCambio) {

			if (frameCont < 7) {
				frameCont++;
				this.setNombreArchivoImagen("recursos/Nave/Explosion/GranExplosion/Explosion"+frameCont+".png");
			}
			else {
				this.setNombreArchivoImagen("recursos/Nave/Explosion/GranExplosion/Explosion6.png");
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
