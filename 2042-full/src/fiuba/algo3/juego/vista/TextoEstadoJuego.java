package fiuba.algo3.juego.vista;


import fiuba.algo3.juego.modelo.Plano;
import fiuba.algo3.titiritero.ObjetoVivo;
import fiuba.algo3.titiritero.vista.ObjetoDeTexto;

public class TextoEstadoJuego implements ObjetoDeTexto, ObjetoVivo{
	
	private String Texto;
	private Plano Modelo;
	
	
	public TextoEstadoJuego(String texto, Plano plano) {
		this.setTexto(texto);
		this.Modelo = plano;
	}
	
	@Override
	public void vivir() {
		if (this.Modelo.estaJuegoGanado()) {
			this.setTexto("Juego Ganado");
		}
		if (this.Modelo.estaJuegoPerdido()) {
			this.setTexto("Juego Perdido");
		}
	}

	@Override
	public String getTexto() {
		
		return Texto;
	}
	
	public void setTexto(String texto) {
		this.Texto = texto;
	}

}
