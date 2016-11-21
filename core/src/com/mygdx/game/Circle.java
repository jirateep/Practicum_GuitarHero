package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Circle {

	public Vector2 position;
	public Texture circleImg;
	public boolean removeThis = false;
	public float initYPosition = GuitarHeroGame.HEIGHT;
	
	public Circle (float x,Texture circleImg) {
		this.circleImg = circleImg;
		position = new Vector2(x,initYPosition);
	}
	
	public void update() {
		playing();
		deleting();
	}
	
	private void playing() {
		position.y -= World.speed;
	}
	
	private void deleting() {
		if(position.y + circleImg.getHeight() < 0) {
			removeThis = true;
			World.score.updateCombo(-1);
		}
	}
}
