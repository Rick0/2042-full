package fiuba.algo3.juego.modelo;


/* Explosion de cualquier nave */
public class NaveExplosion extends ObjetoUbicable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5764960680716822713L;
	int tiempoDeVida = 22;


	public NaveExplosion(Punto punto, Plano unPlano) {

		this.rectangulo = new Rectangulo(33, 33, punto);
		this.determinarPlano(unPlano);
		this.plano.agregarObjetoNuevo(this);
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
