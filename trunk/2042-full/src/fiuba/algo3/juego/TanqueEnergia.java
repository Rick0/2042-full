package juego;

public class TanqueEnergia extends Item {

	private int aumentoEnergia;


	public TanqueEnergia(double d, double e) {
		usado = false;
		aumentoEnergia = 40;
		rectangulo = new Rectangulo(4, 3, d, e);
	}

	/* Recibe una nave tipo Algo42, y le suma energia */
	public void intentarEfectoEn(Algo42 algo42) {

		if ( algo42.coincidePosicionCon(this.rectangulo) && (this.usado == false) ) {
			algo42.modificarEnergia( this.aumentoEnergia );
			usado = true;
		}

	}

}
