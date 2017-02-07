package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GuitarHeroGame extends Game {
	public static final int HEIGHT = 900;
	public static final int WIDTH = 1800;
	public SpriteBatch batch;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new GameScreen(this));
	}

	public void render (float delta) {
		System.out.println("hello " + delta);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
