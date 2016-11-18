package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TapsterGame extends Game {
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
