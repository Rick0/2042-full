package juego;

import excepciones.*;

public class TorpedoRastreador extends Arma {
	private Nave naveRastreada;
	
	public TorpedoRastreador (int x, int y, boolean origenAlgo,Plano plano ) {
		this.danio = -20;
		this.usada = false;
		this.rectangulo = (new Rectangulo(2 , 2, x, y ));
		this.determinarPlano(plano);
		try {
			this.plano.agregarArma( this );
		} catch (ArmaUsadaError e)
		{
			System.out.println("Se produjo un error irrecuperable");
		}
		this.InicializarOrigenAlgo42(origenAlgo);
		
	}
	
	public void determinarNaveRastreada(Nave unaNave) {
		//Guarda la nave a la cual rasteara el torpedo. 
		//Cuando se utilice el metodo mover, el torpedo se movera hacia esa nave

		this.naveRastreada = unaNave;
		
	}
	public void mover() {

		//"El torpedo rastreador se mueve de tal forma que se acerque a la nave que persigue" 
		
		 if (this.naveRastreada.posicionX() < this.posicionX()) {
			
			if (this.naveRastreada.posicionY() == this.posicionY() ) {  
				
				this.determinarPosicion( (this.posicionX() - 1) , this.posicionY() );
			}
			
			if ( (this.naveRastreada.posicionY() < this.posicionY() ) ) {  
				
				this.determinarPosicion( (this.posicionX() - 1) , (this.posicionY() - 1) );
					
			} else {
				if (this.naveRastreada.posicionY() > this.posicionY() ) {
						this.determinarPosicion( (this.posicionX() - 1), (this.posicionY() + 1) );
				}
			}
		} else {
	
				if ( this.naveRastreada.posicionX() == this.posicionX() ) {
					
					if (this.naveRastreada.posicionY() < this.posicionY() ) {
						this.determinarPosicion( this.posicionX() , (this.posicionY() + 1) );
					}
					} else {	
						if (this.naveRastreada.posicionY() == this.posicionY() ) {
						
							this.determinarPosicion( this.posicionX() , ( this.posicionY() +1 ) );
						}
					
						if (this.naveRastreada.posicionY() > this.posicionY() ) {
											this.determinarPosicion( this.posicionX() , this.posicionY() +1 );
						}
						if (this.naveRastreada.posicionY() == this.posicionY() ) {
											this.determinarPosicion( this.posicionX() + 1, this.posicionY() );
						}
						if (this.naveRastreada.posicionY() < this.posicionY() ) {
									this.determinarPosicion( this.posicionX() + 1, this.posicionY() - 1);
						} else {
											if (this.naveRastreada.posicionY() > this.posicionY() ) {
												this.determinarPosicion( this.posicionX() + 1, this.posicionY() + 1 );
											}
						}
					}
		}
		}	
}
