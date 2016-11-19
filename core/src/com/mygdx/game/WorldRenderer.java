package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class WorldRenderer {
	
	SpriteBatch batch;
	World world;
	
	public WorldRenderer(World world) {
		batch = new SpriteBatch();
		this.world = world;

	}
	
	public void render() {
		
		batch.begin();
		drawCircleLine(world.redLine.circles);
		drawPressButton(world.redLine);
		batch.end();
	}
	
	public void drawCircleLine(Circle [] circles) {
		for(int i=0;i < circles.length;i++) {
			if(circles[i] != null) {
				batch.draw(circles[i].circleImg, circles[i].position.x,circles[i].position.y);
			}
		}
	}
	
	public void drawPressButton(LineCircle lineCircle) {
		if(lineCircle.isPress) {
			batch.draw(lineCircle.pushPressButtonImg, lineCircle.x,lineCircle.buttonYPosition);
			lineCircle.isPress = false;
		} else {
			batch.draw(lineCircle.pushUnPressButtonImg, lineCircle.x,lineCircle.buttonYPosition);
		}
		
	}
}
