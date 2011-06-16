package fiuba.algo3.juego.controlador;
import java.awt.Event;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import fiuba.algo3.juego.modelo.Algo42;
import fiuba.algo3.juego.modelo.excepciones.AreaInvalidaError;
import fiuba.algo3.titiritero.ControladorJuego;
import fiuba.algo3.titiritero.KeyPressedObservador;



public class ControladorMovimientosAlgo extends Frame implements KeyPressedObservador{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ControladorMovimientosAlgo(ControladorJuego unControladorJuego,final Algo42 algo) {
		this.addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent arg0) {
				System.out.print(arg0.getKeyCode());
				int tecla=arg0.getKeyCode();
				switch(tecla)
				{
				case KeyEvent.VK_DOWN://Abajo
					try {
						algo.moverArriba();
					} catch (AreaInvalidaError e) {
						System.out.print("Area invalida");
						// Si hay error de area invalida, que no se mueva.
					}
				case KeyEvent.VK_UP://Arriba
					try {
						algo.moverAbajo();
					} catch (AreaInvalidaError e) {
						System.out.print("Area invalida");
						// Si hay error de area invalida, que no se mueva.
				}
				case 39: //Derecha
					try {
						algo.moverDerecha();
					} catch (AreaInvalidaError e) {
						System.out.print("Area invalida");
						// Si hay error de area invalida, que no se mueva.
					}
				case KeyEvent.VK_LEFT://IZQUIERDA
					try {
						algo.moverIzquierda();
					} catch (AreaInvalidaError e) {
						System.out.print("Area invalida");
						// Si hay error de area invalida, que no se mueva.
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
		});
	}
	public boolean keydown(Event evt, int x){
		System.out.print("tecla");
		return true;
	}
	@Override
	public void keyPressed(KeyEvent event) {
		// TODO Auto-generated method stub
		
	}
}

