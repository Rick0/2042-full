package fiuba.algo3.juego.modelo;

import java.util.List;

import fiuba.algo3.juego.modelo.excepciones.NaveDestruidaError;
import fiuba.algo3.juego.modelo.excepciones.SuperposicionNavesError;


/*Nave guia muy simple. Ni siquiera tiene armas. Su movimiento consiste en ir hacia abajo, 
 * luego hacia arriba , y asi sucesivamente. Fue creada con el unico proposito de mostrar
 * el funcionamiento de una nave guia y probarlo. Sera usada en las pruebas del nivel. 
 * Solo tiene 10 puntos de energia, asi que es facilmente eliminable.
 */
public class Guia1 extends Guia {
	
	public Guia1(List<NaveNoOperable> listaNaves,Punto punto, Plano planoJuego) throws NaveDestruidaError{
		puntos=1002;
		energia=10;
		rectangulo= new Rectangulo (15,15,punto);
		operable=false;
		fueraDeJuego =false;
		destruida=false;
		this.determinarPlano(planoJuego);
		planoJuego.agregarNave(this);
		this.recibirFlota(listaNaves);
	}
	
	/*Si la nave guia esta en la posicion de algo42 lo choca. Lanza un laser*/
	public void intentarAccionSobre(Algo42 algo){

		this.intentarChocar(algo);
		this.dispararLaser();
	}

	/*Movimiento de la clase guia1. Se mueve hacia abajo*/
	public void mover() throws SuperposicionNavesError{
		Punto punto=new Punto(this.devolverPunto().getX(),this.devolverPunto().getY()-1);
		this.determinarPosicion(punto);
		if (this.seSuperponeConOtraNave()) {
			throw new SuperposicionNavesError("La posicion ya esta ocupada");
		}
		this.estaFueraDeArea();
	}
	
	/*Movimiento que se debe llevar a cabo si la funcion intentar
	 * movimiento comprueba que el movimiento por defecto provocaria 
	 * un choque entre naves del mismo tipo. Intenta moverse una 
	 * posicion hacia atras. Si no puede, se queda quieto."
	 */
	public void moverAlternativo() throws SuperposicionNavesError{
		Punto punto=new Punto(this.devolverPunto().getX(),this.devolverPunto().getY()+1);
		this.determinarPosicion(punto);
		if (this.seSuperponeConOtraNave()){
			throw new SuperposicionNavesError("La posicion ya esta ocupada");		}
		this.estaFueraDeArea();
	}
		
	/*Crea una instancia de laser en la posicion actual de la nave*/
	public void dispararLaser(){
		new Laser(this.devolverPunto(), false, plano);
	}
	
	/*Recibe una lista de naves, las mismas seran la flota de la guia1*/
	public void recibirFlota (List<NaveNoOperable> listaNaves){
		flota=listaNaves;
	}
}
