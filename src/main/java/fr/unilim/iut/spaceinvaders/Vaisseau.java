package fr.unilim.iut.spaceinvaders;

public class Vaisseau {
	int x;
	int y;
	
	public Vaisseau(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public boolean occupeLaPosition(int x2, int y2) {
		return(this.x==x2) && (this.y==y2);
	}

	public void seDeplacerVersLaDroite() {
		this.x=this.x+1;
		
	}

	public int abscisse() {
		return this.x;
	}

	public void seDeplacerVersLaGauche() {
		this.x=this.x-1;
		
	}
}

