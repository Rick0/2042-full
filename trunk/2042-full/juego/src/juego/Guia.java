package juego;

import excepciones.*;

import java.util.Iterator;
import java.util.List;

public abstract class Guia extends NaveNoOperable{
	List<NaveNoOperable> flota; 
	
	public void destruirse() throws NaveNoDestruidaError {
	/*La nave guia tiene una caracteristica especial: Cuando sus puntos de energia bajan,
	si llegan a ser iguales o menores a cero, llama a la funcion flotaRetroceder; De esa manera,
	las naves de la flota que perdieron su guia, huyen hacia atras.*/
		
		if (this.devolverCantidadEnergia() > 0) {
			throw new NaveNoDestruidaError("La nave aun tiene energia en su tanque");
		} else {
			destruida = true;
			plano.agregarNaveEliminada(this);
			try {
				this.flotaRetroceder();
			} catch (GuiaNoDestruidaError error) {
				System.out.println("Rogamos que no use cheats");
			}
		}
	}
	
	public void flotaRetroceder() throws GuiaNoDestruidaError {
	/*Todas las naves de la flota actual retroceden. Esta funcion debe ser llamada solamente si
	la nave guia fue destruida; En caso contrario, levanta una excepcion."*/
		
		if (destruida) {
			Iterator<NaveNoOperable> cadaNave = flota.iterator();
			
			while (cadaNave.hasNext()) {
				cadaNave.next().retirarse();
			}
		} else { 
			throw new GuiaNoDestruidaError("La nave guia no fue destruida. La flota no tiene por que retirarse");
		}
	}
	
}
