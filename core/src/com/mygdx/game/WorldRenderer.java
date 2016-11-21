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
		drawDotLine();
		batch.draw(world.dotLine.dotImg, 885, 850);
		for(int i=0;i<World.NBOFCOLOR;i++) {	
			drawPressButton(world.lines[i]);
			drawCircleLine(world.lines[i].circles);
		}
		batch.end();
	}
	
	public void drawDotLine() {
		for(int i=0;i<DotLine.NBOFDOT;i++) {
			for(int j=0;j<DotLine.NBOFLINE;j++) {
				if(world.dotLine.dotLine[i][j] != null && world.dotLine.dotLine[i][j].y <= GuitarHeroGame.HEIGHT * 4 / 5) {
					batch.draw(world.dotLine.dotImg,world.dotLine.dotLine[i][j].x,world.dotLine.dotLine[i][j].y);
				}
			}
		}
	}
	
	public void drawCircleLine(Circle [] circles) {
		for(int i=circles.length - 1;i >= 0;i--) {
			if(circles[i] != null && circles[i].position.y <= GuitarHeroGame.HEIGHT * 4 / 5) {
				float nowWidth = circles[i].circleImg.getWidth() * circles[i].factor;
				float nowHeight = circles[i].circleImg.getHeight() * circles[i].factor;
				batch.draw(circles[i].circleImg, circles[i].position.x,circles[i].position.y, nowWidth, nowHeight);
				//batch.draw(circles[i].circleImg, circles[i].position.x,circles[i].position.y);
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
