package fiuba.algo3.juego.modelo;

public class TanqueEnergia extends Item {

	private int aumentoEnergia;


	public TanqueEnergia(Punto punto) {
		usado = false;
		aumentoEnergia = 40;
		rectangulo = new Rectangulo(4, 3,punto);
	}

	/* Recibe una nave tipo Algo42, y le suma energia */
	public void intentarEfectoEn(Algo42 algo42) {

		if ( algo42.coincidePosicionCon(this.rectangulo) && (this.usado == false) ) {
			algo42.modificarEnergia( this.aumentoEnergia );
			usado = true;
		}

	}

}
