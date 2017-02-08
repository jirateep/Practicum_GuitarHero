package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class DrawPlayingWindow {
	
	private SpriteBatch batch;
	private World world;
	private Font font;
	
	private Texture pathImg;
	private Texture loadBarImg;
	
	public DrawPlayingWindow(SpriteBatch batch, World world, Font font) {
		this.batch = batch;
		this.world = world;
		this.font = font;
		
		pathImg = new Texture("path.png");
		loadBarImg = new Texture("load.png");
	}
	
	public void draw() {
		drawBgImg();
		drawPath();
		drawLoadBar();
		drawSongName();
		drawDotLine();
		drawNoteLines();
		drawScore();
		drawCombo();
		drawNowComboString();
	}
	
	public void drawDotLine() {
		for(int i=0;i<DotLine.NBOFDOT;i++) {
			for(int j=0;j<DotLine.NBOFLINE;j++) {
				if(world.dotLine.dotLine[i][j] != null && world.dotLine.dotLine[i][j].y <= GuitarHeroGame.HEIGHT * 19 / 20) {
					batch.draw(world.dotLine.dotImg,world.dotLine.dotLine[i][j].x,world.dotLine.dotLine[i][j].y);
				}
			}
		}
	}
	
	public void drawNoteLines() {
		for(int i=0;i<World.NBOFCOLOR;i++) {	
			drawPressButton(world.lines[i]);
			drawNoteLine(world.lines[i].notes);
		}
	}
	
	public void drawPressButton(NoteLine lineCircle) {
		if(lineCircle.isPressing()) {
			batch.draw(lineCircle.pushPressButtonImg, lineCircle.x,lineCircle.buttonYPosition);
			lineCircle.isPress = false;
		} else {
			batch.draw(lineCircle.pushUnPressButtonImg, lineCircle.x,lineCircle.buttonYPosition);
		}
	}
	
	public void drawNoteLine(Note [] notes) {
		for(int i=notes.length - 1;i >= 0;i--) {
			if(notes[i] != null && notes[i].position.y <= GuitarHeroGame.HEIGHT * 19 / 20) {
				float nowWidth = notes[i].nowNoteImg.getWidth() * notes[i].factor;
				float nowHeight = notes[i].nowNoteImg.getHeight() * notes[i].factor;
				batch.draw(notes[i].nowNoteImg, notes[i].position.x,notes[i].position.y, nowWidth, nowHeight);
			}
		}
	}
	
	private void drawPath() {
		batch.draw(pathImg,-1,0);
	}
	
	private void drawLoadBar() {
		int height = 20;
		batch.draw(loadBarImg,0,GuitarHeroGame.HEIGHT-height,world.percentSong*GuitarHeroGame.WIDTH,height);
	}
	
	private void drawSongName() {
		float width = Arrange.getFontWidth(font.scoreFont,world.name);
		float xPosition = Arrange.getRightXPosition(width)-40;
		float height = Arrange.getFontHeight(font.scoreFont,world.name);
		float yPosition = Arrange.getTopYPosition(height)-40;
		font.scoreFont.draw(batch,world.name,xPosition,yPosition);
	}
	
	private void drawBgImg() {
		batch.draw(world.bgImg,0,0,GuitarHeroGame.WIDTH,GuitarHeroGame.HEIGHT);
	}
	
	public void drawScore() {
		String scoreString = String.format("%07d", World.score.score);
		float height = Arrange.getFontHeight(font.scoreFont,scoreString);
		float yPosition = Arrange.getTopYPosition(height) - 40;
		//float width = getFontWidth(scoreFont,scoreString);
		//float xPosition = getRightXPosition(width) - 50;
		font.scoreFont.draw(batch,scoreString,40,yPosition);
	}
	
	public void drawCombo() { 
		if(World.score.combo > 0) {
			font.updateComboFont();
			String comboString = World.score.combo + " combo";
			float width = Arrange.getFontWidth(font.comboFont,comboString);
			float xPosition = Arrange.getCenterXPosition(width);
			font.comboFont.draw(batch,comboString,xPosition,GuitarHeroGame.HEIGHT- 350);
		}
	}
	
	public void drawNowComboString() {
		if(World.score.nowComboString != null) {
			font.updateNowComboStringFont();
			String nowComboString = World.score.nowComboString;
			float width = Arrange.getFontWidth(font.comboStringFont,nowComboString);
			float xPosition = Arrange.getCenterXPosition(width);
			font.comboStringFont.draw(batch,nowComboString,xPosition,GuitarHeroGame.HEIGHT- 300);
		}
	}
}
