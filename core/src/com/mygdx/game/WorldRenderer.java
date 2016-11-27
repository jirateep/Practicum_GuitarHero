package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class WorldRenderer {
	
	SpriteBatch batch;
	World world;
	BitmapFont y_oFont;
	BitmapFont b_bFont;
	BitmapFont p_pFont;
	BitmapFont w_gFont;
	BitmapFont g_gFont;
	BitmapFont scoreFont;
	BitmapFont comboFont;
	BitmapFont comboStringFont;
	
	public WorldRenderer(World world) {
		batch = new SpriteBatch();
		this.world = world;
		
		y_oFont = new BitmapFont(Gdx.files.internal("yellowOrangeArcade.fnt"));
		b_bFont = new BitmapFont(Gdx.files.internal("blueDarkBlueArcade.fnt"));
		p_pFont = new BitmapFont(Gdx.files.internal("pinkPurpleArcade.fnt"));
		w_gFont = new BitmapFont(Gdx.files.internal("whiteGrayArcade.fnt"));
		g_gFont = new BitmapFont(Gdx.files.internal("greenDarkGreenArcade.fnt"));
		
		scoreFont = w_gFont;
		//comboFont = y_oFont;
	}
	
	public void render() {
		
		batch.begin();
		drawDotLine();
		drawNoteLines();
		drawScore();
		drawCombo();
		drawNowComboString();
		batch.end();
	}
	
	public void drawNowComboString() {
		if(World.score.nowComboString != null) {
			updateNowComboStringFont();
			String nowComboString = World.score.nowComboString;
			float width = getFontWidth(comboStringFont,nowComboString);
			float xPosition = getCenterXPosition(width);
			comboStringFont.draw(batch,nowComboString,xPosition,GuitarHeroGame.HEIGHT- 100);
		}
	}
	
	public void updateNowComboStringFont() {
		String nowComboString = World.score.nowComboString;
		switch(nowComboString) {
			case "PERFECT":
				comboStringFont = y_oFont;
				break;
			case "EXCELLENT":
				comboStringFont = b_bFont;
				break;
			case "GOOD":
				comboStringFont = g_gFont;
				break;
			case "BAD":
				comboStringFont = p_pFont;
				break;
			case "MISS":
				comboStringFont = p_pFont;
				break;	
		}
	}
	
	public void drawNoteLines() {
		for(int i=0;i<World.NBOFCOLOR;i++) {	
			drawPressButton(world.lines[i]);
			drawNoteLine(world.lines[i].notes);
		}
	}
	
	public void drawScore() {
		String scoreString = "score: " + World.score.score;
		float width = getFontWidth(scoreFont,scoreString);
		float xPosition = getRightXPosition(width) - 50;
		scoreFont.draw(batch,scoreString,xPosition,GuitarHeroGame.HEIGHT- 250);
	}
	
	public void drawCombo() { 
		if(World.score.combo > 0) {
			updateComboFont();
			String comboString = World.score.combo + " combo";
			float width = getFontWidth(comboFont,comboString);
			float xPosition = getCenterXPosition(width);
			comboFont.draw(batch,comboString,xPosition,GuitarHeroGame.HEIGHT- 150);
		}
	}
	
	public void updateComboFont() {
		int combo = World.score.combo;
		if(combo < 50) {
			comboFont = b_bFont;
		} else if(combo < 80) {
			comboFont = g_gFont;
		} else if(combo < 100) {
			comboFont = p_pFont;
		} else {
			comboFont = y_oFont;
		}
	}
	
	public float getRightXPosition(float objWidth) {
		return GuitarHeroGame.WIDTH - objWidth;
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
	
	public void drawNoteLine(Note [] notes) {
		for(int i=notes.length - 1;i >= 0;i--) {
			if(notes[i] != null && notes[i].position.y <= GuitarHeroGame.HEIGHT * 3 / 4) {
				float nowWidth = notes[i].nowNoteImg.getWidth() * notes[i].factor;
				float nowHeight = notes[i].nowNoteImg.getHeight() * notes[i].factor;
				batch.draw(notes[i].nowNoteImg, notes[i].position.x,notes[i].position.y, nowWidth, nowHeight);
			}
		}
	}
	
	public void drawPressButton(NoteLine lineCircle) {
		if(lineCircle.isPress) {
			batch.draw(lineCircle.pushPressButtonImg, lineCircle.x,lineCircle.buttonYPosition);
			lineCircle.isPress = false;
		} else {
			batch.draw(lineCircle.pushUnPressButtonImg, lineCircle.x,lineCircle.buttonYPosition);
		}
		
	}
}
