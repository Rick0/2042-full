package fiuba.algo3.juego.vista;

import fiuba.algo3.juego.modelo.Plano;
import fiuba.algo3.titiritero.ObjetoVivo;
import fiuba.algo3.titiritero.vista.ObjetoDeTexto;


public class TextoVidas implements ObjetoDeTexto, ObjetoVivo{
	private String Texto;
	private Plano Modelo;
	
	public TextoVidas(String texto, Plano modelo) {
		this.setTexto(texto);
		this.Modelo = modelo;
	}
	
	@Override
	public String getTexto() {	
		return Texto;
	}
	
	public void setTexto(String texto) {
		this.Texto = texto;
	}

	@Override
	public void vivir() {
		this.setTexto("Vidas: "+this.Modelo.getVidas()+"");
		
	}


}
