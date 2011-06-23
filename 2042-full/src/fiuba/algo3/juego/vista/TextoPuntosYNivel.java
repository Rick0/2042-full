package fiuba.algo3.juego.vista;

import fiuba.algo3.juego.modelo.Nivel;
import fiuba.algo3.titiritero.ObjetoVivo;
import fiuba.algo3.titiritero.vista.ObjetoDeTexto;

public class TextoPuntosYNivel implements ObjetoDeTexto, ObjetoVivo{
		
		private String Texto;
		private Nivel Modelo;
		
		public TextoPuntosYNivel(String texto, Nivel modelo) {
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
			this.setTexto("Nivel: "+Modelo.devolverNumeroNivel()+""+"   Puntos: "+
					Modelo.devolverPuntuacionTotal()+"");
			
		}

	}
