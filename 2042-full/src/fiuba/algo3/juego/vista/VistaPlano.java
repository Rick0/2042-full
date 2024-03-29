package fiuba.algo3.juego.vista;

import fiuba.algo3.titiritero.ObjetoVivo;
import fiuba.algo3.titiritero.vista.Imagen;


public class VistaPlano extends Imagen implements ObjetoVivo {

	//private static final long serialVersionUID = 7139934190560517966L;
	int frameCont;
	static int velocidadCambio = 7;
	int velocidadCambioCont;


	public VistaPlano() {
		this.frameCont = 0;
		this.velocidadCambioCont = velocidadCambio;
		this.setNombreArchivoImagen("recursos/Plano/Mar.png");
	}

	@Override
	/* Efecto plano dinamico */
	public void vivir() {

		pasaUnTiempo();
		actualizarImagen();
	}

	/* Se actualiza la imagen del plano, con los sucesivos cambios, se crea el efecto de un fondo dinamico */
	public void actualizarImagen() {

		if (velocidadCambioCont == velocidadCambio) {

			if (frameCont < 36) {
				frameCont++;
				this.setNombreArchivoImagen("recursos/Plano/PlanoDinamico/Mar"+frameCont+".png");
			}
			else if (frameCont == 36) {
				frameCont = 0;
				this.setNombreArchivoImagen("recursos/Plano/PlanoDinamico/Mar"+frameCont+".png");
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
