package fiuba.algo3.juego.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Event;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ConcurrentModificationException;

import fiuba.algo3.juego.modelo.Algo42;
import fiuba.algo3.juego.modelo.excepciones.AreaInvalidaError;
import fiuba.algo3.titiritero.ControladorJuego;
import fiuba.algo3.titiritero.SuperficieDeDibujo;
import fiuba.algo3.titiritero.vista.Panel;


public class VentanaPrincipal extends Frame {

	private static final long serialVersionUID = 1L;
	private ControladorJuego controladorJuego;
	private Panel panel;


	public VentanaPrincipal(ControladorJuego unControladorJuego,final Algo42 algo) {

		this.controladorJuego = unControladorJuego;
		this.setTitle("Algo42-Full");
		this.setSize(500, 650);
		this.setResizable(false);
		panel = new Panel(500, 600,controladorJuego);
		this.add(panel,BorderLayout.CENTER);
		
		this.setBackground(Color.black);
		panel.setBackground(Color.black);


		this.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent arg0) {
				int opcion=arg0.getKeyCode();
				if(opcion==KeyEvent.VK_DOWN){
					//Abajo
					try {
						algo.moverAbajo();
					} catch (AreaInvalidaError e) {
						System.out.print("Area invalida");
						// Si hay error de area invalida, que no se mueva.
					}
				}
				if(opcion==KeyEvent.VK_UP){
				//Arriba
					try {
						algo.moverArriba();
					} catch (AreaInvalidaError e) {
						System.out.print("Area invalida");
						// Si hay error de area invalida, que no se mueva.
					}
				}
				if(opcion==KeyEvent.VK_LEFT){
					//izquierda
					try {
						algo.moverIzquierda();
					} catch (AreaInvalidaError e) {
						System.out.print("Area invalida");
						// Si hay error de area invalida, que no se mueva.
					}
				}
				if(opcion==KeyEvent.VK_RIGHT){
					//Derecha
					try {
						algo.moverDerecha();
					} catch (AreaInvalidaError e) {
						System.out.print("Area invalida");
						// Si hay error de area invalida, que no se mueva.
					}
				}
				if(opcion==KeyEvent.VK_SPACE){
					//Disparo
					try{
						algo.dispararLaser();
					}
					catch(ConcurrentModificationException e){
						algo.dispararLaser();
					}
				}
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
			}
		});

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
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