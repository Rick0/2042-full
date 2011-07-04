package fiuba.algo3.juego.modelo;

import java.io.Serializable;
import fiuba.algo3.juego.modelo.excepciones.ItemUsadoError;


public class TanqueEnergia extends Item implements Serializable{

	private static final long serialVersionUID = -3393360548199229151L;
	private int aumentoEnergia;


	public TanqueEnergia(Punto punto, Plano unPlano) {
		fueUsado = false;
		aumentoEnergia = 40;
		rectangulo = new Rectangulo(40, 40, punto);
		plano = unPlano;
		try {
			plano.agregarItem(this);
			plano.agregarObjetoNuevo(this);
		} catch (ItemUsadoError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
