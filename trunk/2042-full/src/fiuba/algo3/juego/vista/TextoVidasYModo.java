package fiuba.algo3.juego.vista;

import fiuba.algo3.juego.modelo.Algo42;
import fiuba.algo3.juego.modelo.Plano;
import fiuba.algo3.titiritero.ObjetoVivo;
import fiuba.algo3.titiritero.vista.ObjetoDeTexto;


public class TextoVidasYModo implements ObjetoDeTexto, ObjetoVivo {
	
	private String Texto;
	private Plano plano;
	private Algo42 algo42;
	
	public TextoVidasYModo(String texto, Plano modelo, Algo42 avionJugador) {
		this.setTexto(texto);
		this.plano = modelo;
		this.algo42 = avionJugador;
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
		String espacio = "   ";
		if (this.algo42.estadoSuperMode() == 0)
			this.setTexto("Vidas: " + this.plano.getVidas() + espacio + "Modo normal: inf");
		else if (this.algo42.estadoSuperMode() == 1)
			this.setTexto("Vidas: " + this.plano.getVidas() + espacio + "Modo super: " + this.algo42.tiempoSuperModeRestante() );
	}

}
