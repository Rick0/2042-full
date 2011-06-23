package fiuba.algo3.juego.modelo;


/* Explosion de cualquier nave */
public class NaveExplosion extends ObjetoUbicable {

	int tiempoDeVida = 14;


	public NaveExplosion(Punto punto, Plano unPlano) {

		this.rectangulo = new Rectangulo(33, 33, punto);
		this.determinarPlano(plano);
		this.plano.agregarObjetoNuevo(this);
		System.out.println("explosion");
	}

	@Override
	public void vivir() {

		this.pasaUnTiempo();

		if (tiempoDeVida <= 0) {
			plano.agregarObjetoDestruido(this);
		}
	}

	/* En cada instante, se disminuye el tiempo de vida */
	public void pasaUnTiempo() {

		tiempoDeVida = tiempoDeVida - 1;
	}

}
//new NaveExplosion(this.devolverPunto(), this.plano);
