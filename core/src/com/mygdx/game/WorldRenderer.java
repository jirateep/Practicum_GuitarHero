package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class WorldRenderer {
	
	SpriteBatch batch;
	//Texture img;
	World world;
	
	public WorldRenderer(World world) {
		batch = new SpriteBatch();
		//img = new Texture("badlogic.jpg");
		this.world = world;

	}
	
	public void render() {
		
		batch.begin();
		drawCircleLine(world.circleRed.circles);
		//batch.draw(img, 0, 0);
		batch.end();
	}
	
	public void drawCircleLine(Circle [] circles) {
		for(int i=0;i < circles.length;i++) {
			if(circles[i] != null) {
				batch.draw(circles[i].circleImg, circles[i].position.x,circles[i].position.y);
			}
		}
	}
}
