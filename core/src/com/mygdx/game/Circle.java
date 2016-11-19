package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Circle {

	Vector2 position;
	Texture circleImg;
	boolean removeThis = false;
	
	public Circle (float x) {
		circleImg = new Texture("redCircle.png");
		position = new Vector2(x,GuitarHeroGame.HEIGHT);
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
		}
	}
}
