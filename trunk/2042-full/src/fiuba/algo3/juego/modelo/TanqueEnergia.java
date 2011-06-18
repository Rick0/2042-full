package fiuba.algo3.juego.modelo;


public class TanqueEnergia extends Item {

	private int aumentoEnergia;


	public TanqueEnergia(Punto punto, Plano unPlano) {
		fueUsado = false;
		aumentoEnergia = 40;
		rectangulo = new Rectangulo(30, 30, punto);
		plano = unPlano;
	}

	/* Recibe una nave tipo Algo42, y le suma energia */
	public void intentarChocar(Algo42 algo42) {
		if (algo42.coincidePosicionCon(this.rectangulo) && (this.fueUsado == false)) {
			algo42.chocarCon(this);
			this.chocarCon(algo42);
		}
	}

	/* Devuelve la energia que contiene el tanque */
	public int getEnergia() {
		return aumentoEnergia;
	}

}