package juego;

import excepciones.*;

public class Avioneta extends NaveNoOperable {
	public int puntosAtras, puntosAdelante;
	
	public Avioneta(int posicionX, int posicionY, Plano plano) throws SuperposicionNavesError{
		//"Inicializa una instancia de Avioneta"

		this.puntos = 20;
		energia = 20;
		puntosAdelante= 0;
		puntosAtras = 0;
		operable = false;
		rectangulo = new Rectangulo(6, 3, posicionX, posicionY);
		destruida = false;
		fueraDeJuego = false;
		this.determinarPlano(plano);
		if (this.seSuperponeConOtraNave()) {
			throw new SuperposicionNavesError("La posición esta ocupada");
		}
		plano.agregarNave(this);
	}
	
	@Override
	void IntentarAccionSobre(Algo42 algo42) {
		// TODO Auto-generated method stub

	}

}
