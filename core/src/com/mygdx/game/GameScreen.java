package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;

public class GameScreen extends ScreenAdapter {

	private GuitarHeroGame guitarHeroGame;
	private World world;
	private WorldRenderer worldRenderer;
	
	public GameScreen(GuitarHeroGame guitarHeroGame) {
		this.guitarHeroGame = guitarHeroGame;
		world = new World();
		worldRenderer = new WorldRenderer(world);
	}
	
	public void render(float delta) {
		world.update(delta);

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		worldRenderer.render();
	}
	
}