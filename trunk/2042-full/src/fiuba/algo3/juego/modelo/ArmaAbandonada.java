package fiuba.algo3.juego.modelo;


public class ArmaAbandonada extends Item {

	private int numeroTorpedos, numeroCohetes;


	public ArmaAbandonada(Punto punto, Plano unPlano) {
		fueUsado = false;
		numeroTorpedos = 5;
		numeroCohetes  = 5;
		rectangulo = new Rectangulo(30, 30, punto);
		plano = unPlano;
	}

	/* Recibe una nave tipo Algo42, y le suma las armas que dejo la nave enemiga */
	public void intentarChocar(Algo42 algo42) {
		if (algo42.coincidePosicionCon(this.rectangulo) && (this.fueUsado == false)) {
			algo42.chocarCon(this);
			this.chocarCon(algo42);
		}
	}

	/* Devuelve la cantidad de torpedos del tanque */
	public int getNumeroTorpedos() {
		return numeroTorpedos;
	}

	/* Devuelve la cantidad de cohetes del tanque */
	public int getNumeroCohetes() {
		return numeroCohetes;
	}

}
