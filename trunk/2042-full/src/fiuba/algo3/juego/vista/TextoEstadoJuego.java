package fiuba.algo3.juego.vista;

import fiuba.algo3.juego.modelo.Plano;
import fiuba.algo3.titiritero.ObjetoVivo;
import fiuba.algo3.titiritero.vista.ObjetoDeTexto;


public class TextoEstadoJuego implements ObjetoDeTexto, ObjetoVivo{
	
	private String texto;
	private Plano unPlano;


	public TextoEstadoJuego(String texto, Plano plano) {
		this.setTexto(texto);
		this.unPlano = plano;
	}
	
	@Override
	public void vivir() {
		if (this.unPlano.devolverEstadoJuegoGanado()) {
			this.setTexto("Juego Ganado");
		}
		if (this.unPlano.devolverEstadoJuegoPerdido()) {
			this.setTexto("Juego Perdido");
		}
	}

	@Override
	public String getTexto() {
		
		return texto;
	}
	
	public void setTexto(String texto) {
		this.texto = texto;
	}

}
