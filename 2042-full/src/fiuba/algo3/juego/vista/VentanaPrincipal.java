package fiuba.algo3.juego.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Event;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import fiuba.algo3.juego.modelo.Algo42;
import fiuba.algo3.juego.controlador.*;
import fiuba.algo3.titiritero.SuperficieDeDibujo;


public class VentanaPrincipal extends Frame {

	private static final long serialVersionUID = 1L;
	private ControladorJuegoAlgo42full controladorJuego;
	private Panel panel;


	public VentanaPrincipal(ControladorJuegoAlgo42full unControladorJuego,final Algo42 algo) {

		this.controladorJuego = unControladorJuego;
		this.setTitle("Algo42 - Full");
		this.setSize(550,600);
		this.setResizable(false);

		panel = new Panel(550, 570,controladorJuego);

		this.add(panel,BorderLayout.CENTER);
		this.setBackground(Color.black);
		panel.setBackground(Color.black);

		this.addKeyListener(new KeyListenerAlgo42(algo));
		this.addKeyListener(new KeyListnerOpciones(controladorJuego));
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {

				System.exit(0);
			}			
		} );
	}

	public boolean keydown(Event evt, int x){
		System.out.print("tecla");
		return true;
	}

	public void keyPressed(KeyEvent event) {
		// TODO Auto-generated method stub
	}

	public SuperficieDeDibujo getSuperficieDeDibujo() {
		return this.panel;
	}

}