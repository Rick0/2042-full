package fiuba.algo3.juego.modelo;

import java.io.Serializable;
import fiuba.algo3.juego.modelo.excepciones.ItemUsadoError;


public class ArmaAbandonada extends Item implements Serializable{

	private static final long serialVersionUID = 4277112145617414325L;
	private int numeroTorpedos, numeroCohetes, numeroTorpedosV2, numeroCohetesV2;


	public ArmaAbandonada(Punto punto, Plano unPlano) {
		puntos = 10;
		fueUsado = false;
		numeroTorpedos   = 4;
		numeroCohetes    = 5;
		numeroTorpedosV2 = 9;
		numeroCohetesV2  = 2;
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
	
	/* Devuelve la cantidad de torpedosV2 del tanque */
	public int getNumeroTorpedosV2() {
		return numeroTorpedosV2;
	}

	/* Devuelve la cantidad de cohetesV2 del tanque */
	public int getNumeroCohetesV2() {
		return numeroCohetesV2;
	}

}
