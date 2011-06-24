package fiuba.algo3.juego.vista;

import fiuba.algo3.titiritero.vista.Imagen;
import fiuba.algo3.titiritero.*;


public class VistaNaveExplosion extends Imagen implements ObjetoVivo {

	int frameCont;
	static int velocidadCambio = 3;
	int velocidadCambioCont;


	public VistaNaveExplosion() {
		this.frameCont = 0;
		this.velocidadCambioCont = 0;
		this.setNombreArchivoImagen("recursos/Nave/Explosion/Explosion0.png");
	}

	@Override
	/* Vivir de la vista nave explosion */
	public void vivir() {

		pasaUnTiempo();
		actualizarImagen();
	}

	/* Se actualiza la imagen de la explosion */
	public void actualizarImagen() {

		if (velocidadCambioCont == velocidadCambio) {

			if (frameCont < 7) {
				frameCont++;
				this.setNombreArchivoImagen("recursos/Nave/Explosion/Explosion"+frameCont+".png");
			}
			else {
				this.setNombreArchivoImagen("recursos/Nave/Explosion/Explosion6.png");
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
