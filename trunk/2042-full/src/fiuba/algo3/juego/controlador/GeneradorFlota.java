package fiuba.algo3.juego.controlador;

import java.util.ArrayList;
import java.util.Random;
import java.util.HashMap;

import fiuba.algo3.juego.controlador.tiposDeFlota.GenerarFlota;
import fiuba.algo3.juego.controlador.tiposDeFlota.*;
import fiuba.algo3.juego.modelo.*;
import fiuba.algo3.titiritero.ObjetoVivo;


/* Clase encargada de generar las flotas del juego
 */
public class GeneradorFlota implements ObjetoVivo {

	Plano plano;
	int cantidadNaves;
	static int velocidadSpawnear = 250;
	int velocidadSpawnearCont;
	boolean tengoQueGenerar;
	int posEnY;
	boolean necesitoJefe;
	ArrayList<NaveNoOperable> lista;
	HashMap<Integer, GenerarFlota> tablaGeneradorFlotas;


	public GeneradorFlota(Plano planoJuego) {

		plano = planoJuego;
		velocidadSpawnearCont = velocidadSpawnear;
		tengoQueGenerar = true;
		posEnY = (this.plano.devolverAltura()-10);
		necesitoJefe = false;

		this.tablaGeneradorFlotas = new HashMap<Integer, GenerarFlota>();
		this.inicializarTablaGenerarFlota();

		this.actualizarCantidadNaves();
	}

	/* Vivir del generadorFlota */
	public void vivir() {

		this.pasaUnTiempo();
		this.actualizarCantidadNaves();


	/*	if (necesitoJefe && (velocidadSpawnearCont == velocidadSpawnear)) {
			this.generarJefe(lista);
			this.velocidadSpawnearCont = 0;
			necesitoJefe = false;
		}
	*/	if ((tengoQueGenerar) && (velocidadSpawnearCont == velocidadSpawnear) && (cantidadNaves < 11)) {

			Random generadorRandom = new Random();
			int i = generadorRandom.nextInt(tablaGeneradorFlotas.size());
			GenerarFlota unGeneradorFlota = tablaGeneradorFlotas.get(i);
		//	GenerarFlota unGeneradorFlota = tablaGeneradorFlotas.get(24);
			unGeneradorFlota.generar();

			this.velocidadSpawnearCont = 0;
			this.necesitoJefe = true;
		}
	}

/*	private Guia1 generarJefe(ArrayList<NaveNoOperable> lista) {
		Punto posNave = new Punto(150, this.posEnY-200);
		Guia1 guia = null;
		try {
			guia = new Guia1(lista, posNave,this.plano);
		} catch (NaveDestruidaError e) {
			System.out.print("entre");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return guia;		
	}*/

	/* En cada instante, se actualiza el contador de velocidad de generar flota */
	public void pasaUnTiempo() {

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
	public void actualizarCantidadNaves() {

		this.cantidadNaves = this.plano.devolverListaNaves().size();
	}

	/* Inicializa la tabla de las diferentes flotas que se pueden crear */
	public void inicializarTablaGenerarFlota() {

		if (tablaGeneradorFlotas.isEmpty()) {

			GenerarFlota generarFlotaAlfa = new GenerarFlotaAlfa(this.posEnY, this.plano);
			GenerarFlota generarFlotaBeta = new GenerarFlotaBeta(this.posEnY, this.plano);
			GenerarFlota generarFlotaGamma = new GenerarFlotaGamma(this.posEnY, this.plano);
		//	GenerarFlota generarFlotaDelta = new GenerarFlotaDelta(this.posEnY, this.plano);

			GenerarFlota generarFlotaOmega = new GenerarFlotaOmega(this.posEnY, this.plano);
			GenerarFlota generarFlotaEpsilon = new GenerarFlotaEpsilon(this.posEnY, this.plano);
			GenerarFlota generarFlotaUpsilon = new GenerarFlotaUpsilon(this.posEnY, this.plano);			
			GenerarFlota generarFlotaMu = new GenerarFlotaMu(this.posEnY, this.plano);
			GenerarFlota generarFlotaNu = new GenerarFlotaNu(this.posEnY, this.plano);

			GenerarFlota generarFlotaZeta = new GenerarFlotaZeta(this.posEnY, this.plano);

			GenerarFlota generarFlotaEta = new GenerarFlotaEta(this.posEnY, this.plano);
			GenerarFlota generarFlotaTheta = new GenerarFlotaTheta(this.posEnY, this.plano);

			GenerarFlota generarFlotaXi = new GenerarFlotaXi(this.posEnY, this.plano);
			GenerarFlota generarFlotaPi = new GenerarFlotaPi(this.posEnY, this.plano);
			GenerarFlota generarFlotaChi = new GenerarFlotaChi(this.posEnY, this.plano);
			GenerarFlota generarFlotaPhi = new GenerarFlotaPhi(this.posEnY, this.plano);


			int i = 0;
			tablaGeneradorFlotas.put(i, generarFlotaAlfa);	i++;
			tablaGeneradorFlotas.put(i, generarFlotaBeta);	i++;
			tablaGeneradorFlotas.put(i, generarFlotaGamma);	i++;
		//	tablaGeneradorFlotas.put(i, generarFlotaDelta);	i++;

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

			tablaGeneradorFlotas.put(i, generarFlotaZeta);	i++;

			tablaGeneradorFlotas.put(i, generarFlotaEta);	i++;
			tablaGeneradorFlotas.put(i, generarFlotaTheta);	i++;

			tablaGeneradorFlotas.put(i, generarFlotaXi);	i++;
			tablaGeneradorFlotas.put(i, generarFlotaPi);	i++;
			tablaGeneradorFlotas.put(i, generarFlotaChi);	i++;
			tablaGeneradorFlotas.put(i, generarFlotaPhi);	i++;
		}
	}

}
