package fr.unilim.iut.spaceinvaders.model;

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

	public void positionnerUnNouveauVaisseau(int x, int y) {

		if (  !estDansEspaceJeu(x, y) ) {
			throw new HorsEspaceJeuException("Vous �tes en dehors de l'espace jeu");
		}


		vaisseau = new Vaisseau(x, y); 

	}

	private boolean estDansEspaceJeu(int x, int y) {
		return ((x >= 0) && (x < longueur)) && ((y >= 0) && (y < hauteur));
	}

	public void deplacerVaisseauVersLaDroite() {
		if(vaisseau.abscisse()<(longueur-1)) {
			vaisseau.seDeplacerVersLaDroite();
		}
		
		
	}
	public void deplacerVaisseauVersLaGauche() {
		if(vaisseau.abscisse()>ORIGINE_ABSCISSE_TERRAIN) {
			vaisseau.seDeplacerVersLaGauche();
		}
		
		
	}


}