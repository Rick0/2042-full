package fiuba.algo3.juego.modelo;

import java.util.HashMap;
import java.util.Random;
import fiuba.algo3.juego.modelo.tiposDeFlota.GenerarFlota;
import fiuba.algo3.juego.modelo.tiposDeFlota.GenerarFlotaAlfa;
import fiuba.algo3.juego.modelo.tiposDeFlota.GenerarFlotaBeta;
import fiuba.algo3.juego.modelo.tiposDeFlota.GenerarFlotaChi;
import fiuba.algo3.juego.modelo.tiposDeFlota.GenerarFlotaDelta;
import fiuba.algo3.juego.modelo.tiposDeFlota.GenerarFlotaEpsilon;
import fiuba.algo3.juego.modelo.tiposDeFlota.GenerarFlotaEta;
import fiuba.algo3.juego.modelo.tiposDeFlota.GenerarFlotaGamma;
import fiuba.algo3.juego.modelo.tiposDeFlota.GenerarFlotaMu;
import fiuba.algo3.juego.modelo.tiposDeFlota.GenerarFlotaNu;
import fiuba.algo3.juego.modelo.tiposDeFlota.GenerarFlotaOmega;
import fiuba.algo3.juego.modelo.tiposDeFlota.GenerarFlotaPhi;
import fiuba.algo3.juego.modelo.tiposDeFlota.GenerarFlotaPi;
import fiuba.algo3.juego.modelo.tiposDeFlota.GenerarFlotaSigma;
import fiuba.algo3.juego.modelo.tiposDeFlota.GenerarFlotaTheta;
import fiuba.algo3.juego.modelo.tiposDeFlota.GenerarFlotaUpsilon;
import fiuba.algo3.juego.modelo.tiposDeFlota.GenerarFlotaXi;
import fiuba.algo3.juego.modelo.tiposDeFlota.GenerarFlotaZeta;
import fiuba.algo3.titiritero.ObjetoVivo;


/* Clase encargada de generar las flotas del juego
 */
public class GeneradorFlota implements ObjetoVivo {

	Plano plano;
	int cantidadNaves;
	static int velocidadSpawnear = 222;
	int velocidadSpawnearCont;
	int posEnY;
	boolean necesitoNaveGuia;
	boolean tengoQueGenerar;
	HashMap<Integer, GenerarFlota> tablaGeneradorFlotas;


	public GeneradorFlota(Plano planoJuego) {

		plano = planoJuego;
		tengoQueGenerar = true;
		velocidadSpawnearCont = velocidadSpawnear;
		posEnY = (this.plano.devolverAltura()-10);
		necesitoNaveGuia = false;
		tengoQueGenerar = true;

		this.tablaGeneradorFlotas = new HashMap<Integer, GenerarFlota>();
		this.inicializarTablaGenerarFlota();

		this.actualizarCantidadNaves();
	}

	/* Vivir del generadorFlota */
	public void vivir() {

		this.actualizarCantidadNaves();
		this.revisarNavesHuir();
		this.pasaUnTiempo();

		if (this.necesitoNaveGuia && (velocidadSpawnearCont == velocidadSpawnear) && tengoQueGenerar) {
			GenerarFlota unGeneradorFlota = tablaGeneradorFlotas.get(0);
			unGeneradorFlota.generar();
			this.velocidadSpawnearCont = 0;
			this.necesitoNaveGuia = false;
		}
		else if ((cantidadNaves < 12) && (velocidadSpawnearCont == velocidadSpawnear) && tengoQueGenerar) {

			Random generadorRandom = new Random();
			int i = generadorRandom.nextInt(tablaGeneradorFlotas.size()-1);
			GenerarFlota unGeneradorFlota = tablaGeneradorFlotas.get(i+1);
		//	GenerarFlota unGeneradorFlota = tablaGeneradorFlotas.get(4);
			unGeneradorFlota.generar();

			this.velocidadSpawnearCont = 0;

			if (cantidadNaves >= 10)
				this.necesitoNaveGuia = true;
		}
	}

	/* Se fija si las naves estan huyendo
	 * Si lo estan, deja de crear naves hasta que se hayan retirado todas */
	private void revisarNavesHuir() {

		if (cantidadNaves > 0) {
			if (this.plano.devolverListaNaves().get(0).estaHuyendo())
				tengoQueGenerar = false;
		}
		else if (cantidadNaves == 0) {
			tengoQueGenerar = true;
		}
	}
	
	/* En cada instante, se actualiza el contador de velocidad de generar flota */
	private void pasaUnTiempo() {

		if (velocidadSpawnearCont < velocidadSpawnear) {
			velocidadSpawnearCont++;
		}
	}

	/* Devuelve true si la lista de naves no operables esta vacia, false caso contrario */
	public boolean noHayNavesEnemigas() {

		if (this.plano.devolverListaNaves().size() == 0) {
			return true;
		}
		return false;
	}

	/* Actualiza la cantidad de naves no operables en el plano, y lo guarda en la variable 'cantidadNaves' */
	private void actualizarCantidadNaves() {

		this.cantidadNaves = this.plano.devolverListaNaves().size();
	}

	/* Inicializa la tabla de las diferentes flotas que se pueden crear */
	private void inicializarTablaGenerarFlota() {

		if (tablaGeneradorFlotas.isEmpty()) {

			GenerarFlota generarFlotaAlfa = new GenerarFlotaAlfa(this.posEnY, this.plano);
			GenerarFlota generarFlotaBeta = new GenerarFlotaBeta(this.posEnY, this.plano);
			GenerarFlota generarFlotaGamma = new GenerarFlotaGamma(this.posEnY, this.plano);
			GenerarFlota generarFlotaDelta = new GenerarFlotaDelta(this.posEnY, this.plano);

			GenerarFlota generarFlotaOmega = new GenerarFlotaOmega(this.posEnY, this.plano);
			GenerarFlota generarFlotaEpsilon = new GenerarFlotaEpsilon(this.posEnY, this.plano);
			GenerarFlota generarFlotaUpsilon = new GenerarFlotaUpsilon(this.posEnY, this.plano);			
			GenerarFlota generarFlotaMu = new GenerarFlotaMu(this.posEnY, this.plano);
			GenerarFlota generarFlotaNu = new GenerarFlotaNu(this.posEnY, this.plano);

			GenerarFlota generarFlotaEta = new GenerarFlotaEta(this.posEnY, this.plano);
			GenerarFlota generarFlotaTheta = new GenerarFlotaTheta(this.posEnY, this.plano);

			GenerarFlota generarFlotaXi = new GenerarFlotaXi(this.posEnY, this.plano);
			GenerarFlota generarFlotaPi = new GenerarFlotaPi(this.posEnY, this.plano);
			GenerarFlota generarFlotaChi = new GenerarFlotaChi(this.posEnY, this.plano);
			GenerarFlota generarFlotaPhi = new GenerarFlotaPhi(this.posEnY, this.plano);

			GenerarFlota generarFlotaZeta = new GenerarFlotaZeta(this.posEnY, this.plano);
			GenerarFlota generarFlotaSigma = new GenerarFlotaSigma(this.posEnY, this.plano);


			int i = 0;
			tablaGeneradorFlotas.put(i, generarFlotaSigma);	i++;

			tablaGeneradorFlotas.put(i, generarFlotaAlfa);	i++;
			tablaGeneradorFlotas.put(i, generarFlotaBeta);	i++;
			tablaGeneradorFlotas.put(i, generarFlotaGamma);	i++;
			tablaGeneradorFlotas.put(i, generarFlotaDelta);	i++;

			tablaGeneradorFlotas.put(i, generarFlotaOmega);	i++;
			tablaGeneradorFlotas.put(i, generarFlotaOmega);	i++;
			tablaGeneradorFlotas.put(i, generarFlotaOmega);	i++;
			tablaGeneradorFlotas.put(i, generarFlotaOmega);	i++;
			tablaGeneradorFlotas.put(i, generarFlotaOmega);	i++;
			tablaGeneradorFlotas.put(i, generarFlotaEpsilon);	i++;
			tablaGeneradorFlotas.put(i, generarFlotaEpsilon);	i++;
			tablaGeneradorFlotas.put(i, generarFlotaEpsilon);	i++;
			tablaGeneradorFlotas.put(i, generarFlotaUpsilon);	i++;

			tablaGeneradorFlotas.put(i, generarFlotaMu);	i++;
			tablaGeneradorFlotas.put(i, generarFlotaMu);	i++;
			tablaGeneradorFlotas.put(i, generarFlotaMu);	i++;
			tablaGeneradorFlotas.put(i, generarFlotaMu);	i++;
			tablaGeneradorFlotas.put(i, generarFlotaMu);	i++;
			tablaGeneradorFlotas.put(i, generarFlotaNu);	i++;
			tablaGeneradorFlotas.put(i, generarFlotaNu);	i++;
			tablaGeneradorFlotas.put(i, generarFlotaNu);	i++;
			tablaGeneradorFlotas.put(i, generarFlotaNu);	i++;
			tablaGeneradorFlotas.put(i, generarFlotaNu);	i++;

			tablaGeneradorFlotas.put(i, generarFlotaEta);	i++;
			tablaGeneradorFlotas.put(i, generarFlotaTheta);	i++;

			tablaGeneradorFlotas.put(i, generarFlotaXi);	i++;
			tablaGeneradorFlotas.put(i, generarFlotaXi);	i++;
			tablaGeneradorFlotas.put(i, generarFlotaPi);	i++;
			tablaGeneradorFlotas.put(i, generarFlotaPi);	i++;
			tablaGeneradorFlotas.put(i, generarFlotaChi);	i++;
			tablaGeneradorFlotas.put(i, generarFlotaChi);	i++;
			tablaGeneradorFlotas.put(i, generarFlotaPhi);	i++;
			tablaGeneradorFlotas.put(i, generarFlotaPhi);	i++;

			tablaGeneradorFlotas.put(i, generarFlotaZeta);	i++;

			tablaGeneradorFlotas.put(i, generarFlotaDelta);	i++;
			tablaGeneradorFlotas.put(i, generarFlotaDelta);	i++;
		}
	}

}
