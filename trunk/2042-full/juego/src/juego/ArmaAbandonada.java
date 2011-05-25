package juego;

public class ArmaAbandonada extends Item {
	
	private int numeroTorpedos, numeroCohetes;
	
	void intentarEfectoEn(Algo42 algo42) {
		//Recibe una nave tipo Algo42; y le suma las armas que dejo la nave enemiga.
		if ( algo42.coincidePosicionCon(this.rectangulo) ) {
			algo42.aumentarArmas( this.numeroTorpedos, this.numeroCohetes );
			this.usado = true;
		}
		
	}

}
