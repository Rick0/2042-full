package fiuba.algo3.juego.vista;

import fiuba.algo3.juego.modelo.Algo42;
import fiuba.algo3.titiritero.ObjetoVivo;
import fiuba.algo3.titiritero.vista.ObjetoDeTexto;

public class TextoVida implements ObjetoDeTexto, ObjetoVivo{
	
	private String Texto;
	private Algo42 Modelo;
	
	public TextoVida(String texto, Algo42 modelo) {
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
		this.setTexto(Modelo.devolverEnergia()+"");
		
	}

}
