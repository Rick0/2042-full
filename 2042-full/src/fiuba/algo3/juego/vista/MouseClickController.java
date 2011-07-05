package fiuba.algo3.juego.vista;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import fiuba.algo3.juego.controlador.ControladorJuegoAlgo42full;


public class MouseClickController extends MouseAdapter {

	private ControladorJuegoAlgo42full controlador; 
	
	public MouseClickController(ControladorJuegoAlgo42full unControlador) {
		this.controlador = unControlador;
	}
	
	public void mouseClicked(MouseEvent e){
		this.controlador.despacharMouseClick(e.getX(), e.getY());
	}
	
}
