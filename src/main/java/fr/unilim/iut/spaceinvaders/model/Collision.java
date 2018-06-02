package fr.unilim.iut.spaceinvaders.model;

public class Collision {
	private Sprite premierSprite;
	private Sprite deuxiemeSprite;
	
	public Collision(Sprite premierSprite, Sprite deuxiemeSprite) {
		this.premierSprite=premierSprite;
		this.deuxiemeSprite=deuxiemeSprite;
	}
	public boolean detecterCollision() {
		boolean estEnCollisionX = false;
		boolean estEnCollisionY = false;
		
		
		
		if(this.premierSprite.abscisseLaPlusAGauche()<=this.deuxiemeSprite.abscisseLaPlusAGauche() && 
				this.premierSprite.abscisseLaPlusADroite()>=this.deuxiemeSprite.abscisseLaPlusADroite()) {
			estEnCollisionX=true;
		}
		
		if(this.premierSprite.ordonneeLaPlusHaute()>=this.deuxiemeSprite.ordonneeLaPlusBasse() && 
				this.premierSprite.ordonneeLaPlusBasse()<=this.deuxiemeSprite.ordonneeLaPlusBasse()) {
			estEnCollisionY=true;
		}
		if(estEnCollisionX && estEnCollisionY) {
			return true;
		}else {
			return false;
		}
		
				
	}
}
