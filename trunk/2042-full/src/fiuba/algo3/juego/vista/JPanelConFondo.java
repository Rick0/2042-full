package fiuba.algo3.juego.vista;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class JPanelConFondo extends JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String ubicacionImagen;
	
	public JPanelConFondo(String direccion){
		ubicacionImagen=direccion;
	}
	
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
           getClass().getResource(ubicacionImagen)).getImage();
        this.setImagen(imagenInterna);
    }

}