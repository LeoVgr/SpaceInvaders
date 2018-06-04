package fr.unilim.iut.spaceinvaders.model;

import fr.unilim.iut.spaceinvaders.moteurjeu.Commande;
import fr.unilim.iut.spaceinvaders.moteurjeu.Jeu;
import fr.unilim.iut.spaceinvaders.utils.DebordementEspaceJeuException;
import fr.unilim.iut.spaceinvaders.utils.HorsEspaceJeuException;
import fr.unilim.iut.spaceinvaders.utils.MissileException;
import fr.unilim.spaceinvaders.Constante;

public class SpaceInvaders implements Jeu {

	private static final char MARQUE_FIN_LIGNE = '\n';
	private static final char MARQUE_VIDE = '.';
	private static final char MARQUE_VAISSEAU = 'V';

	int longueur;
	int hauteur;

	Vaisseau vaisseau;
	Missile missile;
	Envahisseur envahisseur;
	
	private boolean estTermine;

	public SpaceInvaders(int longueur, int hauteur) {
		this.longueur = longueur;
		this.hauteur = hauteur;
		this.estTermine=false;
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
		if (this.aUnVaisseauQuiOccupeLaPosition(x, y))
			marque = Constante.MARQUE_VAISSEAU;
		else if (this.aUnMissileQuiOccupeLaPosition(x, y))
			marque = Constante.MARQUE_MISSILE;
		else if(this.aUnEnvahisseurQuiOccupeLaPosition(x,y))
			marque = Constante.MARQUE_ENVAHISSEUR;
		else marque = Constante.MARQUE_VIDE;
		return marque;
	}

	private boolean aUnEnvahisseurQuiOccupeLaPosition(int x, int y) {
		return this.aUnEnvahisseur() && envahisseur.occupeLaPosition(x,y);
	}



	public boolean aUnEnvahisseur() {
		return envahisseur!=null;
	}



	private boolean aUnMissileQuiOccupeLaPosition(int x, int y) {
		return this.aUnMissile() && missile.occupeLaPosition(x,y);
	}



	public boolean aUnMissile() {
		return missile != null;
	}



	private boolean aUnVaisseauQuiOccupeLaPosition(int x, int y) {
		return this.aUnVaisseau() && vaisseau.occupeLaPosition(x, y);
	}

	public boolean aUnVaisseau() {
		return vaisseau != null;
	}

	public void positionnerUnNouveauVaisseau(Dimension dimension, Position position, int vitesse) {

		positionnerUnSprite(dimension,position);
		vaisseau = new Vaisseau(dimension,position,vitesse);
	}

	private boolean estDansEspaceJeu(int x, int y) {
		return ((x >= 0) && (x < longueur)) && ((y >= 0) && (y < hauteur));
	}

	public void deplacerVaisseauVersLaDroite() {
		if (vaisseau.abscisseLaPlusADroite() < (longueur - 1)) {
			vaisseau.deplacerHorizontalementVers(Direction.DROITE);
			if (!estDansEspaceJeu(vaisseau.abscisseLaPlusADroite(), vaisseau.ordonneeLaPlusHaute())) {
				vaisseau.positionner(longueur - vaisseau.longueur(), vaisseau.ordonneeLaPlusHaute());
			}
		}
	}


	public Vaisseau recupererVaisseau() {
		return this.vaisseau;
	}


	public void evoluer(Commande commandeUser) {

		if (commandeUser.gauche) {
			deplacerVaisseauVersLaGauche();
		}

		if (commandeUser.droite) {
			deplacerVaisseauVersLaDroite();
		}
		if(commandeUser.tir && !this.aUnMissile()) {
			tirerUnMissile(new Dimension(Constante.MISSILE_LONGUEUR,Constante.MISSILE_HAUTEUR),Constante.MISSILE_VITESSE);
		}
		if(this.aUnMissile()) {


			this.deplacerMissile();
		}
		if(this.aUnEnvahisseur()) {
			if(envahisseur.seDeplaceVersLaDroite()) {
				this.deplacerEnvahisseurVersLaDroite();
			}else {
				this.deplacerEnvahisseurVersLaGauche();
			}

		}

	}

	public boolean etreFini() {
		return false;

	}

	public void deplacerVaisseauVersLaGauche() {
		if (0 < vaisseau.abscisseLaPlusAGauche())
			vaisseau.deplacerHorizontalementVers(Direction.GAUCHE);
		if (!estDansEspaceJeu(vaisseau.abscisseLaPlusAGauche(), vaisseau.ordonneeLaPlusHaute())) {
			vaisseau.positionner(0, vaisseau.ordonneeLaPlusHaute());

		}
	}

	public void initialiserJeu() {
		Position positionVaisseau = new Position(this.longueur/2,this.hauteur-1);
		Dimension dimensionVaisseau = new Dimension(Constante.VAISSEAU_LONGUEUR, Constante.VAISSEAU_HAUTEUR);
		positionnerUnNouveauVaisseau(dimensionVaisseau, positionVaisseau, Constante.VAISSEAU_VITESSE);

		Position positionEnvahisseur = new Position(this.longueur/2,Constante.ENVAHISSEUR_HAUTEUR);
		Dimension dimensionEnvahisseur = new Dimension(Constante.ENVAHISSEUR_LONGUEUR, Constante.ENVAHISSEUR_HAUTEUR);
		positionnerUnNouvelEnvahisseur(dimensionEnvahisseur, positionEnvahisseur, Constante.ENVAHISSEUR_VITESSE);
	}



	public void tirerUnMissile(Dimension dimensionMissile, int vitesseMissile) {

		if ((vaisseau.hauteur()+ dimensionMissile.hauteur()) > this.hauteur )
			throw new MissileException("Pas assez de hauteur libre entre le vaisseau et le haut de l'espace jeu pour tirer le missile");

		this.missile = this.vaisseau.tirerUnMissile(dimensionMissile,vitesseMissile);
	}



	public Missile recupererMissile() {
		return this.missile;
	}



	public void deplacerMissile() {

		if(this.missile.ordonneeLaPlusHaute()+ Direction.HAUT_ECRAN.valeur() <= 0) {
			this.missile=null;
		}else {
			this.missile.deplacerVerticalementVers(Direction.HAUT_ECRAN);

			if(this.aUnEnvahisseur()) {
				if(new Collision(this.envahisseur,this.missile).detecterCollision()) {
					this.missile=null;
					this.envahisseur=null;
					this.terminerPartie();
				}
			}
		}



	}

	

	private void terminerPartie() {
		this.missile=null;
		this.envahisseur=null;
		this.estTermine=true;
		System.out.println("fin");
		

	}



	public void positionnerUnNouvelEnvahisseur(Dimension dimension, Position position, int vitesse) {
		positionnerUnSprite(dimension, position);

		envahisseur = new Envahisseur(dimension,position,vitesse);
	}



	private void positionnerUnSprite(Dimension dimension, Position position) {
		int x = position.abscisse();
		int y = position.ordonnee();

		if (!estDansEspaceJeu(x, y))
			throw new HorsEspaceJeuException("La position du sprite est en dehors de l'espace jeu");

		int longueurEnvahisseur = dimension.longueur(); 
		int hauteurEnvahisseur = dimension.hauteur();

		if (!estDansEspaceJeu(x + longueurEnvahisseur - 1, y))
			throw new DebordementEspaceJeuException(
					"Le sprite déborde de l'espace jeu vers la droite à cause de sa longueur");
		if (!estDansEspaceJeu(x, y - hauteurEnvahisseur + 1))
			throw new DebordementEspaceJeuException(
					"Le sprite déborde de l'espace jeu vers le bas à cause de sa hauteur");
	}




	public void deplacerEnvahisseurVersLaDroite() {


	

		if(this.aUnMissile()) {
			if(new Collision(this.envahisseur,this.missile).detecterCollision()) {
				this.missile=null;
				this.envahisseur=null;
				this.terminerPartie();
			}
		}


		if (envahisseur.abscisseLaPlusADroite() < (longueur - 1)) {
			envahisseur.deplacerHorizontalementVers(Direction.DROITE);
			if (!estDansEspaceJeu(envahisseur.abscisseLaPlusADroite(), envahisseur.ordonneeLaPlusHaute())) {

				envahisseur.positionner(longueur-((envahisseur.abscisseLaPlusADroite()+1-longueur)*2), envahisseur.ordonneeLaPlusHaute());
				envahisseur.changerDeplacement();


			}
		}else {
			this.deplacerEnvahisseurVersLaGauche();
			envahisseur.changerDeplacement();
		}

	}



	public void deplacerEnvahisseurVersLaGauche() {
		
		if (0 < envahisseur.abscisseLaPlusAGauche()) {
			envahisseur.deplacerHorizontalementVers(Direction.GAUCHE);
			if (!estDansEspaceJeu(envahisseur.abscisseLaPlusAGauche(), envahisseur.ordonneeLaPlusHaute())) {

				envahisseur.positionner((-1)*(envahisseur.abscisseLaPlusAGauche()+1)*2, envahisseur.ordonneeLaPlusHaute());
				envahisseur.changerDeplacement();
			}
		}else {
			this.deplacerEnvahisseurVersLaDroite();
			envahisseur.changerDeplacement();
		}
		if(this.aUnMissile()) {
			if(new Collision(this.envahisseur,this.missile).detecterCollision()) {
				this.terminerPartie();
			}
		}

	}

	public void deplacerEnvahisseur() {
		if(envahisseur.seDeplaceVersLaDroite()) {
			this.deplacerEnvahisseurVersLaDroite();
		}else {
			this.deplacerEnvahisseurVersLaGauche();
		}
	}

	public Envahisseur recupererEnvahisseur() {
		return this.envahisseur;
	}
	
	public boolean retourneEstTermine(){
		return this.estTermine;
	}
}