package fiuba.algo3.juego.modelo;

import java.util.Iterator;
import java.util.Random;
import fiuba.algo3.juego.modelo.excepciones.GuiaNoDestruidaError;
import fiuba.algo3.juego.modelo.excepciones.NaveDestruidaError;
import fiuba.algo3.juego.modelo.excepciones.NaveNoDestruidaError;
import fiuba.algo3.juego.modelo.excepciones.SuperposicionNavesError;


public class NaveGuia extends NaveNoOperable {

	private static final long serialVersionUID = 3148574291086531899L;


	public NaveGuia(Punto punto, Plano planoJuego) throws SuperposicionNavesError, NaveDestruidaError{
		puntos = 100;
		energia = 100;
		rectangulo = new Rectangulo (112, 80, punto);
		esOperable = false;
		fueraDelPlano = false;
		estaDestruida = false;
		this.determinarPlano(planoJuego);

		if (this.seSuperponeConOtraNave()) {
			throw new SuperposicionNavesError("La posicion esta ocupada");
		}
		planoJuego.agregarNave(this);
		planoJuego.agregarObjetoNuevo(this);
	}

	/* La nave guia tiene una caracteristica especial: Cuando se destruye,
	 * llama a la funcion flotaRetroceder. De esa manera,
	 * las naves de la flota que perdieron su guia, huyen hacia atras
	 */
	public void destruirse() throws NaveNoDestruidaError {

		if (this.devolverEnergia() > 0) {
			throw new NaveNoDestruidaError("La nave aun tiene energia en su tanque");
		}
		else {
			estaDestruida = true;
			plano.agregarNaveEliminada(this);
			new NaveExplosion(this.devolverPunto(), this.plano);
			try {
				this.flotaRetroceder();
			} catch (GuiaNoDestruidaError error) {
				System.out.println("Rogamos que no use cheats");
			}
		}
	}

	/* Todas las naves de la flota actual retroceden. Esta funcion debe ser llamada solamente si
	 * la nave guia fue destruida. En caso contrario, levanta una excepcion
	 */
	public void flotaRetroceder() throws GuiaNoDestruidaError {

		if (estaDestruida) {

			Iterator<NaveNoOperable> iteradorNaves = this.plano.devolverListaNaves().iterator();
			while (iteradorNaves.hasNext()) {
				NaveNoOperable unaNave = iteradorNaves.next();
				unaNave.huir();
			}
		}
		else {
			throw new GuiaNoDestruidaError("La nave guia no fue destruida. La flota no tiene por que retirarse.");
		}
	}

	@Override
	/* Vivir de la nave guia
	 * Obviamente esta nave no se retira */
	public void vivir() {

		if (!estaDestruida) {

			this.intentarMover();
			this.intentarChocar(this.plano.devolverAlgo42());
			this.pasaUnTiempo();
			this.disparar();
		}
	}

	/* La nave guia puede disparar diferentes tipos de torpedos o cohete,
	 * con diferentes probabilidades para cada arma */
	public void disparar() {

		if (velocidadDisparoCont == velocidadDisparo) {

			Random generadorRandom = new Random();
			int i = generadorRandom.nextInt(10);

			if (i <= 2) {
				this.dispararCohete();
			} else if (i == 3) {
				this.dispararTorpedoRastreador();
			} else if (i <= 7) {
				this.dispararTorpedo();
			} else {
				this.dispararTorpedoAdaptable();
			}
			velocidadDisparoCont = 0;
		}
	}

	/* Movimiento de la nave guia. Se mueve hacia abajo linealmente */
	public void mover() throws SuperposicionNavesError {
		Punto punto = new Punto(this.devolverPunto().getX(), this.devolverPunto().getY()-1);
		this.cambiarPosicion(punto);
		if (this.seSuperponeConOtraNave())
			throw new SuperposicionNavesError("La posicion ya esta ocupada");
		this.estaFueraDelPlano();
	}
	
	/* Movimiento que se debe llevar a cabo si la funcion intentar
	 * movimiento comprueba que el movimiento por defecto provocaria 
	 * un choque entre naves del mismo tipo. Intenta moverse una 
	 * posicion hacia atras. Si no puede, se queda quieto
	 */
	public void moverAlternativo() throws SuperposicionNavesError {
		Punto punto = new Punto(this.devolverPunto().getX(), this.devolverPunto().getY()+1);
		this.cambiarPosicion(punto);
		if (this.seSuperponeConOtraNave())
			throw new SuperposicionNavesError("La posicion ya esta ocupada");
		this.estaFueraDelPlano();
	}

}
