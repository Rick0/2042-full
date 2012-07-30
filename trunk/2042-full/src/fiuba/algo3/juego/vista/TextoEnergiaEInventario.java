package fiuba.algo3.juego.vista;

import fiuba.algo3.juego.modelo.Algo42;
import fiuba.algo3.titiritero.ObjetoVivo;
import fiuba.algo3.titiritero.vista.ObjetoDeTexto;


public class TextoEnergiaEInventario implements ObjetoDeTexto, ObjetoVivo{
	
	private String Texto;
	private Algo42 algo42;


	public TextoEnergiaEInventario(String texto, Algo42 modelo) {
		this.setTexto(texto);
		this.algo42 = modelo;
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
		String energiaActual;
		if (algo42.devolverEnergia() < 10)
			energiaActual = "00" + Integer.toString( algo42.devolverEnergia() );
		else if (algo42.devolverEnergia() < 100)
			energiaActual =  "0" + Integer.toString( algo42.devolverEnergia() );
		else
			energiaActual = Integer.toString( algo42.devolverEnergia() );
		
		if (algo42.estadoSuperMode() == 0)
			this.setTexto("Energia: " + energiaActual +
							espacio + espacio + espacio + espacio + espacio +
						  "Misiles: " + algo42.getCohetes() +
						    espacio + espacio + espacio + espacio + espacio +
						  "Torpedos: " + algo42.getTorpedos() );
		else if (algo42.estadoSuperMode() == 1)
			this.setTexto("Energia: " + energiaActual + espacio + "Misiles Nucleares: " + algo42.getCohetes() + espacio + "Multi Torpedos: " + algo42.getTorpedosV2() );
	}

}
