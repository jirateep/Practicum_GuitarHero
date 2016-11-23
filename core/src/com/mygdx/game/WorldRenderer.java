package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class WorldRenderer {
	
	SpriteBatch batch;
	World world;
	BitmapFont y_oFont;
	
	
	public WorldRenderer(World world) {
		batch = new SpriteBatch();
		this.world = world;
		y_oFont = new BitmapFont(Gdx.files.internal("yellowOrangeArcade.fnt"));
	}
	
	public void render() {
		
		batch.begin();
		drawDotLine();
		for(int i=0;i<World.NBOFCOLOR;i++) {	
			drawPressButton(world.lines[i]);
			drawCircleLine(world.lines[i].circles);
		}
		drawScore();
		drawCombo();
		batch.end();
	}
	
	public void drawScore() {
		String scoreString = "score: " + World.score.score;
		y_oFont.draw(batch,scoreString,GuitarHeroGame.WIDTH - 550,GuitarHeroGame.HEIGHT- 250);
	}
	
	public void drawCombo() { 
		if(World.score.combo > 0) {
			String comboString = World.score.combo + " combo";
			float width = getFontWidth(y_oFont,comboString);
			float xPosition = getCenterXPosition(width);
			y_oFont.draw(batch,comboString,xPosition,GuitarHeroGame.HEIGHT- 100);
		}
	}
	
	public float getCenterXPosition(float objWidth) {
		return ( GuitarHeroGame.WIDTH - objWidth )/2;
	}
	
	public float getFontWidth(BitmapFont font, String str) {
		GlyphLayout layout = new GlyphLayout(font, str);
		return layout.width;
	}
	
	public void drawDotLine() {
		for(int i=0;i<DotLine.NBOFDOT;i++) {
			for(int j=0;j<DotLine.NBOFLINE;j++) {
				if(world.dotLine.dotLine[i][j] != null && world.dotLine.dotLine[i][j].y <= GuitarHeroGame.HEIGHT * 3 / 4) {
					batch.draw(world.dotLine.dotImg,world.dotLine.dotLine[i][j].x,world.dotLine.dotLine[i][j].y);
				}
			}
		}
	}
	
	public void drawCircleLine(Circle [] circles) {
		for(int i=circles.length - 1;i >= 0;i--) {
			if(circles[i] != null && circles[i].position.y <= GuitarHeroGame.HEIGHT * 3 / 4) {
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
