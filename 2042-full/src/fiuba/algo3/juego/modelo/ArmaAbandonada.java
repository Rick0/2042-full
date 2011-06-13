package fiuba.algo3.juego.modelo;

public class ArmaAbandonada extends Item {

	private int numeroTorpedos, numeroCohetes;


	public ArmaAbandonada(Punto punto) {
		usado = false;
		numeroTorpedos = 2;
		numeroCohetes = 3;
		rectangulo = new Rectangulo(3, 3, punto);
	}

	/* Recibe una nave tipo Algo42; y le suma las armas que dejo la nave enemiga */
	public void intentarEfectoEn(Algo42 algo42) {

		if ( algo42.coincidePosicionCon(this.rectangulo) ) {
			algo42.aumentarArmas( this.numeroTorpedos, this.numeroCohetes );
			this.usado = true;
		}

	}

}
