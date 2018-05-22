package fr.unilim.iut.spaceinvaders;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import fr.unilim.iut.spaceinvaders.model.Dimension;
import fr.unilim.iut.spaceinvaders.model.Position;
import fr.unilim.iut.spaceinvaders.model.SpaceInvaders;
import fr.unilim.iut.spaceinvaders.utils.DebordementEspaceJeuException;
import fr.unilim.iut.spaceinvaders.utils.HorsEspaceJeuException;

public class SpaceInvadersTest {
	private SpaceInvaders spaceinvaders;

	@Before
	public void initialisation() {
		spaceinvaders = new SpaceInvaders(15,10);
	}
	@Test
	public void test_AuDebut_JeuSpaceInvaderEstVide() {

		assertEquals("" + 
				"...............\n" + 
				"...............\n" +
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}

	@Test
	public void test_unNouveauVaisseauEstCorrectementPositionneDansEspaceJeu() {

		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(1,1), new Position(7,9), 1);
		assertEquals("" + 
				"...............\n" + 
				"...............\n" +
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				"...............\n" + 
				".......V.......\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}
	@Test
	public void test_UnNouveauVaisseauPositionneHorsEspaceJeu_DoitLeverUneException() {


		try {
			spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(1,1), new Position(15,9), 1);
			fail("Position trop � droite : devrait d�clencher une exception HorsEspaceJeuException");
		} catch (final HorsEspaceJeuException e) {
		}


		try {
			spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(1,1), new Position(-1,9), 1);
			fail("Position trop � gauche : devrait d�clencher une exception HorsEspaceJeuException");
		} catch (final HorsEspaceJeuException e) {
		}


		try {
			spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(1,1), new Position(14,10), 1);
			fail("Position trop en bas : devrait d�clencher une exception HorsEspaceJeuException");
		} catch (final HorsEspaceJeuException e) {
		}


		try {
			spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(1,1), new Position(14,-1), 1);
			fail("Position trop � haut : devrait d�clencher une exception HorsEspaceJeuException");
		} catch (final HorsEspaceJeuException e) {
		}

	}
	
	
	 
	 @Test
	 public void test_UnNouveauVaisseauPositionneDansEspaceJeuMaisAvecDimensionTropGrande_DoitLeverUneExceptionDeDebordement() {
	 try {
		 spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(9,2), new Position(7,9), 1);
		 fail("D�passement du vaisseau � droite en raison de sa longueur trop importante : devrait d�clencher une exception DebordementEspaceJeuException");
	 	} catch (final DebordementEspaceJeuException e) {
	 		}
	 	try {
	 		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3,4), new Position(7,1), 1);
	 		fail("D�passement du vaisseau vers le haut en raison de sa hauteur trop importante : devrait d�clencher une exception DebordementEspaceJeuException");
	 	} catch (final DebordementEspaceJeuException e) {
	 	}
	 }
	 
	 @Test
	 public void test_VaisseauImmobile_DeplacerVaisseauVersLaDroite() {
	 spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3,2), new Position(12,9), 1);
	 spaceinvaders.deplacerVaisseauVersLaDroite();
	 assertEquals("" + 
	 "...............\n" + 
	 "...............\n" +
	 "...............\n" + 
	 "...............\n" + 
	 "...............\n" + 
	 "...............\n" + 
	 "...............\n" + 
	 "...............\n" + 
	 "............VVV\n" + 
	 "............VVV\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	 }

	 @Test
	 public void test_unNouveauVaisseauAvecDimensionEstCorrectementPositionneDansEspaceJeu() {
	 spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3,2), new Position(7,9), 1);
	 assertEquals("" + 
	 "...............\n" + 
	 "...............\n" +
	 "...............\n" + 
	 "...............\n" + 
	 "...............\n" + 
	 "...............\n" + 
	 "...............\n" + 
	 "...............\n" + 
	 ".......VVV.....\n" + 
	 ".......VVV.....\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	 }

	 public void test_VaisseauAvance_DeplacerVaisseauVersLaDroite() {

		 spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3,2),new Position(12,9), 3);
	       spaceinvaders.deplacerVaisseauVersLaDroite();
	       assertEquals("" + 
	       "...............\n" + 
	       "...............\n" +
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "..........VVV..\n" + 
	       "..........VVV..\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	   }

	 @Test
	    public void test_VaisseauAvancePartiellement_DeplacerVaisseauVersLaDroite() {

	       spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3,2),new Position(10,9),3);
	       spaceinvaders.deplacerVaisseauVersLaDroite();
	       assertEquals("" + 
	       "...............\n" + 
	       "...............\n" +
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "............VVV\n" + 
	       "............VVV\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	    }
	 
	 @Test
	    public void test_VaisseauAvance_DeplacerVaisseauVersLaGauche() {

	       spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3,2),new Position(7,9), 3);
	       spaceinvaders.deplacerVaisseauVersLaGauche();

	       assertEquals("" + 
	       "...............\n" + 
	       "...............\n" +
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "....VVV........\n" + 
	       "....VVV........\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	   }
	 
	 @Test
	    public void test_VaisseauImmobile_DeplacerVaisseauVersLaGauche() {

		   spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3,2),new Position(0,9), 3);
	       spaceinvaders.deplacerVaisseauVersLaGauche();

	       assertEquals("" + 
	       "...............\n" + 
	       "...............\n" +
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "VVV............\n" + 
	       "VVV............\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	     }
	 
	 @Test
	    public void test_VaisseauAvancePartiellement_DeplacerVaisseauVersLaGauche() {

	       spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3,2),new Position(1,9), 3);
	       spaceinvaders.deplacerVaisseauVersLaGauche();

	       assertEquals("" + 
	       "...............\n" + 
	       "...............\n" +
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "VVV............\n" + 
	       "VVV............\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	     }
	 
}