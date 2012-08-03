package fiuba.algo3.juego.modelo;


/* Explosion de cualquier nave */
public class GranExplosion extends ObjetoUbicable {

	private static final long serialVersionUID = -5764960680716822713L;
	int tiempoDeVida = 29;
	boolean desaparecio;


	public GranExplosion(Punto punto, int explosionTamanio, Plano unPlano) {

		this.rectangulo = new Rectangulo(explosionTamanio, explosionTamanio, punto);
		this.determinarPlano(unPlano);
		this.plano.agregarObjetoNuevo(this);
		this.desaparecio = false;
	}

	@Override
	public void vivir() {

		if (!this.desaparecio) {

			this.pasaUnTiempo();
			if (tiempoDeVida == 0) {
				this.desaparecio = true;
				plano.agregarObjetoDestruido(this);
			}
		}
	}

	/* En cada instante, se disminuye el tiempo de vida */
	public void pasaUnTiempo() {

		tiempoDeVida = tiempoDeVida - 1;
	}

}
