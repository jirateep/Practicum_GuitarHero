package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Circle {

	public Vector2 position;
	public Texture circleImg;
	public boolean removeThis = false;
	public float initYPosition = GuitarHeroGame.HEIGHT;
	public float factor;
	public int color;
	
	
	public Circle (float x,Texture circleImg,int color) {
		this.circleImg = circleImg;
		position = new Vector2(x,initYPosition);
		factor = (initYPosition-position.y)/initYPosition;
		this.color = color;
	}
	
	public void update() {
		updateFactor();
		playing();
		deleting();
	}
	
	private void updateFactor() {
		factor = (initYPosition-position.y)/initYPosition;
	}
	
	private void playing() {
		position.y -= World.speed;
		position.x = calXPosition(position.y);
	}
	
	private float calXPosition(float y) {
		float xPosition = 0;
		switch(color) {
			case World.RED:
				xPosition = GuitarHeroGame.WIDTH/2 - 15 - 2 * circleImg.getWidth()*factor;
				break;
			case World.BLUE:
				xPosition = GuitarHeroGame.WIDTH/2 - 5 - circleImg.getWidth()*factor;
				break;
			case World.GREEN:
				xPosition = GuitarHeroGame.WIDTH/2 + 5;
				break;
			case World.YELLOW:
				xPosition = GuitarHeroGame.WIDTH/2 + 15 + circleImg.getWidth()*factor;
				break;
			default:
				break;
		}
		return xPosition;
	}
	
	private void deleting() {
		if(position.y + circleImg.getHeight() < 0) {
			removeThis = true;
			World.score.updateCombo(-1);
		}
	}
}
