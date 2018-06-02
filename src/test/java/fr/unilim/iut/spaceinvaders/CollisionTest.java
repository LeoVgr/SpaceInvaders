package fr.unilim.iut.spaceinvaders;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import fr.unilim.iut.spaceinvaders.model.Collision;
import fr.unilim.iut.spaceinvaders.model.Dimension;
import fr.unilim.iut.spaceinvaders.model.Direction;
import fr.unilim.iut.spaceinvaders.model.Envahisseur;
import fr.unilim.iut.spaceinvaders.model.Missile;
import fr.unilim.iut.spaceinvaders.model.Position;
import fr.unilim.iut.spaceinvaders.model.SpaceInvaders;
import fr.unilim.iut.spaceinvaders.model.Sprite;


public class CollisionTest {
	private Collision collision;
	private Missile missile;
	private Envahisseur envahisseur;

	@Before
	public void initialisation() {
		//collision = new Collision(new Sprite(new Dimension(5,2),new Position(5,5),1),new Sprite(new Dimension(1,1),new Position(5,7),1));
		
	}
	@Test
	public void testLaCollisionENtreMissileEtEnvahisseurLorsquePosXEtPosYdeMissileSontComprisEntreLesPosXEtYdeLenvahisseurAlorsCollision() {
		envahisseur= new Envahisseur(new Dimension(5,2),new Position(1,2),1);
		missile= new Missile(new Dimension(1,1),new Position(3,2),1);
		
		
		
		collision =new Collision(envahisseur,missile);
		
		assertEquals(true,collision.detecterCollision());
		
	}
	@Test
	public void testLaNonCollisionEntreDeuxSpriteQuiNePartagePasLesMêmePlagesDePosY() {
		envahisseur= new Envahisseur(new Dimension(5,2),new Position(5,5),1);
		missile= new Missile(new Dimension(1,1),new Position(5,7),1);
		
		collision = new Collision(envahisseur,missile);
		assertEquals(false,collision.detecterCollision());
	}
	@Test
	public void testLaNonCollisionEntreDeuxSpriteQuiNePartagePasLesMêmePlagesDePosX() {
		envahisseur= new Envahisseur(new Dimension(5,2),new Position(5,5),1);
		missile= new Missile(new Dimension(1,1),new Position(0,1),1);
		
		collision = new Collision(envahisseur,missile);
		assertEquals(false,collision.detecterCollision());
	}
	@Test
	public void testLaNonCollisionEntreDeuxSpriteQuiNePartagePasLesMêmePlagesDePosXEtPosY() {
		envahisseur= new Envahisseur(new Dimension(5,2),new Position(5,5),1);
		missile= new Missile(new Dimension(1,1),new Position(0,5),1);
		
		collision = new Collision(envahisseur,missile);
		assertEquals(false,collision.detecterCollision());
	}
}
