package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class WorldRenderer {
	
	SpriteBatch batch;
	Texture img;
	
	public WorldRenderer() {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");

	}
	
	public void render() {
		
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}
}
