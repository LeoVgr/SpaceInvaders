package fr.unilim.iut.spaceinvaders.model;

import fr.unilim.iut.spaceinvaders.utils.DebordementEspaceJeuException;
import fr.unilim.iut.spaceinvaders.utils.HorsEspaceJeuException;

public class SpaceInvaders {
	private static final int ORIGINE_ABSCISSE_TERRAIN = 0;
	private static final char MARQUE_FIN_LIGNE = '\n';
	private static final char MARQUE_VIDE = '.';
	private static final char MARQUE_VAISSEAU = 'V';
	int longueur;
	int hauteur;
	Vaisseau vaisseau;

	public SpaceInvaders(int longeur, int hauteur) {
		this.longueur = longeur;
		this.hauteur = hauteur;
	}


	public String recupererEspaceJeuDansChaineASCII() {
		StringBuilder espaceDeJeu = new StringBuilder();


		for (int y = 0; y < hauteur; y++) {
			for (int x = 0; x < longueur; x++) {

				espaceDeJeu.append(recupererMarqueDeLaPosition(x, y));


			}
			espaceDeJeu.append(MARQUE_FIN_LIGNE);
		}
		return espaceDeJeu.toString();
	}

	private char recupererMarqueDeLaPosition(int x, int y) {
		char marque;
		if (this.aUnVaisseauQuiOccupeLaPosition(y, x)) {
			marque = MARQUE_VAISSEAU;
		}else {
			marque = MARQUE_VIDE;
		}
		return marque;
	}

	private boolean aUnVaisseauQuiOccupeLaPosition(int x, int y) {
		return this.aUnVaisseau() && vaisseau.occupeLaPosition(y, x);
	}

	private boolean aUnVaisseau() {
		return vaisseau!=null;
	}

	private boolean estDansEspaceJeu(int x, int y) {
		return ((x >= 0) && (x < longueur)) && ((y >= 0) && (y < hauteur));
	}

	public void deplacerVaisseauVersLaDroite() {
		if (vaisseau.abscisseLaPlusADroite() < (longueur - 1))
			vaisseau.seDeplacerVersLaDroite();
	}

		
	public void deplacerVaisseauVersLaGauche() {
		if(vaisseau.abscisseLaPlusAGauche()>ORIGINE_ABSCISSE_TERRAIN) {
			vaisseau.seDeplacerVersLaGauche();
		}
	}
	
	public void positionnerUnNouveauVaisseau(int longueur, int hauteur, int x, int y) {
		if (!estDansEspaceJeu(x, y))
		throw new HorsEspaceJeuException("La position du vaisseau est en dehors de l'espace jeu");

		if ( !estDansEspaceJeu(x+longueur-1,y))
		throw new DebordementEspaceJeuException("Le vaisseau déborde de l'espace jeu vers la droite à cause de sa longueur");
		if (!estDansEspaceJeu(x,y-hauteur+1))
		throw new DebordementEspaceJeuException("Le vaisseau déborde de l'espace jeu vers le bas à cause de sa hauteur");

		vaisseau = new Vaisseau(longueur, hauteur);
		vaisseau.positionner(x, y);
		}

	public void positionnerUnNouveauVaisseau(Dimension dimension, Position position) {
		positionnerUnNouveauVaisseau(dimension.longueur(), dimension.hauteur(),
		position.abscisse(), position.ordonnee());

		}

}