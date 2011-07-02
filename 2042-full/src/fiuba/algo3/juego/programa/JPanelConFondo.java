package fiuba.algo3.juego.programa;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class JPanelConFondo extends JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Image imagen;

    @Override
    public void paint(Graphics g) {
    	cargarImagenes();
		g.drawImage(imagen, 0, 0, getWidth(), getHeight(),
                        this);

		setOpaque(false);
        super.paint(g);
    }
    
    private void setImagen(Image img){
    	imagen=img;
    }
    
    private void cargarImagenes() {
        Image imagenInterna = new ImageIcon(
           getClass().getResource("../vista/recursos/PantallaPrincipal/pantallaPrincipal.jpg")).getImage();
        this.setImagen(imagenInterna);
    }

}