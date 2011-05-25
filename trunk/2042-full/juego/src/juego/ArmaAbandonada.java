package juego;

public class ArmaAbandonada extends Item {
	
	private int numeroTorpedos, numeroCohetes;
	
	public ArmaAbandonada(int posicionX, int posicionY) {
		usado = false;
		numeroTorpedos = 2;
		numeroCohetes = 3;
		rectangulo = new Rectangulo(3, 3, posicionX, posicionY);
	}
	
	void intentarEfectoEn(Algo42 algo42) {
		//Recibe una nave tipo Algo42; y le suma las armas que dejo la nave enemiga.
		if ( algo42.coincidePosicionCon(this.rectangulo) ) {
			algo42.aumentarArmas( this.numeroTorpedos, this.numeroCohetes );
			this.usado = true;
		}
		
	}

}
