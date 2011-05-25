package juego;

public class TanqueEnergia extends Item {
	private int aumentoEnergia;
	
	public TanqueEnergia(int posicionX, int posicionY) {
		usado = false;
		aumentoEnergia = 40;
		rectangulo = new Rectangulo(4, 3, posicionX, posicionY);
	}
	
	void intentarEfectoEn(Algo42 algo42) {
		//Recibe una nave tipo Algo42; y le suma energia a su tanque
		if ( algo42.coincidePosicionCon(this.rectangulo) && (this.usado == false) ) {
			algo42.modificarEnergia( this.aumentoEnergia );
			usado = true;
		}

	}

}
