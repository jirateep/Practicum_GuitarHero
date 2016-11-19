package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Circle {

	Vector2 position;
	Texture circleImg;
	
	public Circle (float x) {
		circleImg = new Texture("badlogic.jpg");
		position = new Vector2(x,GuitarHeroGame.HEIGHT);
	}
	
	public void update() {
		playing();
	}
	
	private void playing() {
		position.y--;
	}
}
