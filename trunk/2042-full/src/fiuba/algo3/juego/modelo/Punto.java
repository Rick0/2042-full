package fiuba.algo3.juego.modelo;
import fiuba.algo3.juego.modelo.Rectangulo;
import static java.lang.Math.sqrt;


/* Clase punto
 * Es un par ordenado de numeros (x,y) que designan, generalmente, una localizacion en un plano 2D
 */
public class Punto {

	double posicionX;
	double posicionY;


	/* Constructor del Punto, recibe su posicion en el espacio */
	public Punto(double x, double y) {
		posicionX = x;
		posicionY = y;
	}

	/* Devuelve la posicion en X */
	public double getX() {
		return posicionX;
	}

	/* Devuelve la posicion en Y */
	public double getY() {
		return posicionY;
	}

	/* Cambia la posicion en X */
	public void setX(double x) {
		posicionX = x;
	}

	/* Cambia la posicion en Y */
	public void setY(double y) {
		posicionY = y;
	}

	/* Distancia entre si mismo y otro punto */
	public double distanciaHastaPunto(Punto otroPunto) {
		double distancia = 0, distX, distY;

		distX = this.posicionX - otroPunto.getX();
		distY = this.posicionY - otroPunto.getY();
		distancia = sqrt((distX * distX) + (distY * distY));

		return distancia;
	}

	/* Se compara con otro Punto, retorna True si son iguales */
	public boolean esIgualA(Punto otroPunto) {
		return ((this.posicionX == otroPunto.getX()) && (this.posicionY == otroPunto.getY()));
	}

	/* Se fija si el Punto esta dentro del Rectangulo parametro */
	public boolean estaDentroDe(Rectangulo unRectangulo) {
		boolean limiteX, limiteY;

		limiteX = (this.posicionX >= unRectangulo.devolverPosicionX()) && (this.posicionX <= (unRectangulo.devolverPosicionX()+unRectangulo.devolverAncho()));
		limiteY = (this.posicionY >= unRectangulo.devolverPosicionY()) && (this.posicionY <= (unRectangulo.devolverPosicionY()+unRectangulo.devolverAltura()));
	
		return (limiteX && limiteY);
	}

}