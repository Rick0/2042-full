package fiuba.algo3.juego.vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import fiuba.algo3.titiritero.SuperficieDeDibujo;
import fiuba.algo3.titiritero.vista.ObjetoDeTexto;
import fiuba.algo3.titiritero.vista.TextoDinamico;


public class VistaVidaTexto extends TextoDinamico {

	public VistaVidaTexto(ObjetoDeTexto objetoDeTexto) {
		super(objetoDeTexto);
	}

	@Override
	public void dibujar(SuperficieDeDibujo superfice) {
		Graphics grafico = (Graphics)superfice.getBuffer();
		grafico.setColor(Color.WHITE);
		grafico.setFont(fuente);
		grafico.drawString(getTexto(), this.getPosicionable().getX(), this.getPosicionable().getY());
	}


	private Font fuente = new Font("Arial",Font.BOLD,20);

}
