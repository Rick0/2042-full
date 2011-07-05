package fiuba.algo3.juego.vista;

import fiuba.algo3.titiritero.Posicionable;


public class PuntoEntero implements Posicionable{

	public PuntoEntero(int x, int y) {
		this.setX(x);
		this.setY(y);
	}
	
	@Override
	public int getX() {
		return X;
	}

	@Override
	public int getY() {
		return Y;
	}
	
	public void setX(int x) {
		X = x;
	}

	public void setY(int y) {
		this.Y = y;
	}

	private int X;
	private int Y;

}
