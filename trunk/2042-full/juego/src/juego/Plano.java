package juego;

public class Plano {
	/*Maneja el escenario del nivel, contiene listas con las naves, items
y armas en juego y se encarga de operar sobre ellas e iterarlas.*/
	
	int ancho;
	int altura;
	Algo42 algo42;
	
	public Plano(int dimensionX,int dimensionY ) {
		/*Constructor del plano, recibe sus dimensiones
		 * Crea sus listas de armas, naves e items, que se
		 * posicionaran en el plano */
			ancho=dimensionX;
			altura=dimensionY;
			
		}
	public void introducirAlgo(Algo42 algo){
		/*Introduce un algo42 al plano*/

		algo42=algo;
	}
	public void agregarNaveEliminada(Nave nave) {
	
		
	}
}