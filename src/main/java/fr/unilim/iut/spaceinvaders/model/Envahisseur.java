package fr.unilim.iut.spaceinvaders.model;

public class Envahisseur extends Sprite{
	private boolean DeplacementVersLaDroite;
	private boolean DeplacementVersLaGauche;
	public Envahisseur(Dimension dimension, Position positionOrigine, int vitesse) {
		super(dimension, positionOrigine, vitesse);
		this.DeplacementVersLaDroite=false;
		this.DeplacementVersLaGauche=false;
	}
	
	public boolean seDeplaceVersLaDroite() {
		return this.DeplacementVersLaDroite;
	}
	public boolean seDeplaceVersLaGauche() {
		return this.DeplacementVersLaGauche;
	}
	public void changerDeplacement() {
		if(this.DeplacementVersLaDroite) {
			this.DeplacementVersLaDroite=false;
			this.DeplacementVersLaGauche=true;
		}else {
			this.DeplacementVersLaDroite=true;
			this.DeplacementVersLaGauche=false;
		}
	}
}
