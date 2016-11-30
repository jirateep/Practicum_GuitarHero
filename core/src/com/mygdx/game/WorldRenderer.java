package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class WorldRenderer {
	
	private SpriteBatch batch;
	private World world;
	private BitmapFont y_oFont;
	private BitmapFont b_bFont;
	private BitmapFont p_pFont;
	private BitmapFont w_gFont;
	private BitmapFont g_gFont;
	private BitmapFont scoreFont;
	private BitmapFont comboFont;
	private BitmapFont comboStringFont;
	private BitmapFont endGameHeadFont;
	private BitmapFont endGameDetailFont;
	private BitmapFont [] endGameRankFont;
	private BitmapFont rankFont;
	private Texture pathImg;
	private int twinkle = 0;
	
	public WorldRenderer(World world) {
		batch = new SpriteBatch();
		this.world = world;
		
		pathImg = new Texture("path.png");
		y_oFont = new BitmapFont(Gdx.files.internal("yellowOrangeArcade.fnt"));
		b_bFont = new BitmapFont(Gdx.files.internal("blueDarkBlueArcade.fnt"));
		p_pFont = new BitmapFont(Gdx.files.internal("pinkPurpleArcade.fnt"));
		w_gFont = new BitmapFont(Gdx.files.internal("whiteGrayArcade.fnt"));
		g_gFont = new BitmapFont(Gdx.files.internal("greenDarkGreenArcade.fnt"));
		scoreFont = new BitmapFont(Gdx.files.internal("scoreArcade.fnt"));
		
		endGameHeadFont = new BitmapFont(Gdx.files.internal("endHeadArcade.fnt"));
		endGameDetailFont = new BitmapFont(Gdx.files.internal("endDetailArcade.fnt"));
		
		endGameRankFont = new BitmapFont [6];
		endGameRankFont[0] = new BitmapFont(Gdx.files.internal("ssArcade.fnt"));
		endGameRankFont[1] = new BitmapFont(Gdx.files.internal("aArcade.fnt"));
		endGameRankFont[2] = new BitmapFont(Gdx.files.internal("bArcade.fnt"));
		endGameRankFont[3] = new BitmapFont(Gdx.files.internal("cArcade.fnt"));
		endGameRankFont[4] = new BitmapFont(Gdx.files.internal("dArcade.fnt"));
		rankFont = endGameRankFont[0];
	}
	
	public void render() {
		
		batch.begin();
		if(!world.endingSong()) {
			drawBgImg();
			drawPath();
			drawSongName();
			drawDotLine();
			drawNoteLines();
			drawScore();
			drawCombo();
			drawNowComboString();
		} else {
			drawEndGame();
		}
		batch.end();
	}
	
	private void drawEndGame() {
		drawEndScore();
		drawEndRank();
		drawEndCombo();
		drawString();
	}
	
	private void drawString() {
		String string = "PRESS ANYKEYS TO CONTINUE";
		if(twinkle % 40 > 19) {
			float width = getFontWidth(endGameDetailFont,string);
			float xPosition = getCenterXPosition(width);
			endGameDetailFont.draw(batch,string,xPosition,100);
		}
		twinkle++;
	}
	
	private void drawEndMaxCombo(float xPos,float yPos) {
		String maxCombo = "MAXCOMBO    " + World.score.maxCombo;
		endGameDetailFont.draw(batch,maxCombo,xPos,yPos);
	}
	
	private void drawEndCombo() {
		String [] numCombo = {"PERFECT     "+World.score.countCombo[0],
							  "EXCELLENT   "+World.score.countCombo[1],
							  "GOOD        "+World.score.countCombo[2],
							  "BAD         "+World.score.countCombo[3],
							  "MISS        "+World.score.countCombo[4]};
		int i = 0;
		float width = getFontWidth(endGameDetailFont,numCombo[0]);
		float xPosition = getCenterXPosition(width)-200;
		for(;i<numCombo.length;i++) {
			endGameDetailFont.draw(batch,numCombo[i],xPosition,GuitarHeroGame.HEIGHT-280-i*60);
		}
		drawEndMaxCombo(xPosition,GuitarHeroGame.HEIGHT-280 - i*60);
	}
	
	private void drawEndRank() {
		if(world.endMenu.rank != "") {
			String rank = world.endMenu.rank;
			chooseRankFont(rank);
			float width = getFontWidth(rankFont,rank);
			float height = getFontHeight(rankFont,rank);
			float xPosition = getRightXPosition(width) - 450;
			float yPosition = getTopYPosition(height) - 150;
			rankFont.draw(batch,rank,xPosition,yPosition);
		}
	}
	
	private void chooseRankFont(String rank) {
		switch(rank) {
			case "SS":
			case "S":
				rankFont = endGameRankFont[0];
				break;
			case "A":
				rankFont = endGameRankFont[1];
				break;
			case "B":
				rankFont = endGameRankFont[2];
				break;
			case "C":
				rankFont = endGameRankFont[3];
				break;
			case "D":
				rankFont = endGameRankFont[4];
				break;
		}
	}
	
	private void drawEndScore() {
		String scoreString = "SCORE " + world.endMenu.scoreString;
		float width = getFontWidth(endGameHeadFont,scoreString);
		float height = getFontHeight(endGameHeadFont,scoreString);
		float xPosition = getCenterXPosition(width)- 200;
		float yPosition = getTopYPosition(height) - 150;
		endGameHeadFont.draw(batch,scoreString,xPosition,yPosition);
	}
	
	private void drawPath() {
		batch.draw(pathImg,354,0);
	}
	
	private void drawSongName() {
		float width = getFontWidth(scoreFont,world.name);
		float xPosition = getRightXPosition(width)-40;
		float height = getFontHeight(scoreFont,world.name);
		float yPosition = getTopYPosition(height)-40;
		scoreFont.draw(batch,world.name,xPosition,yPosition);
	}
	
	private void drawBgImg() {
		batch.draw(world.bgImg,0,0,GuitarHeroGame.WIDTH,GuitarHeroGame.HEIGHT);
	}
	
	public void drawNowComboString() {
		if(World.score.nowComboString != null) {
			updateNowComboStringFont();
			String nowComboString = World.score.nowComboString;
			float width = getFontWidth(comboStringFont,nowComboString);
			float xPosition = getCenterXPosition(width);
			comboStringFont.draw(batch,nowComboString,xPosition,GuitarHeroGame.HEIGHT- 300);
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
		String scoreString = String.format("%07d", World.score.score);
		float height = getFontHeight(scoreFont,scoreString);
		float yPosition = getTopYPosition(height) - 40;
		//float width = getFontWidth(scoreFont,scoreString);
		//float xPosition = getRightXPosition(width) - 50;
		scoreFont.draw(batch,scoreString,40,yPosition);
	}
	
	public void drawCombo() { 
		if(World.score.combo > 0) {
			updateComboFont();
			String comboString = World.score.combo + " combo";
			float width = getFontWidth(comboFont,comboString);
			float xPosition = getCenterXPosition(width);
			comboFont.draw(batch,comboString,xPosition,GuitarHeroGame.HEIGHT- 350);
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
	
	public float getTopYPosition(float objHeight) {
		return GuitarHeroGame.HEIGHT - objHeight;
	}
	
	public float getCenterYPosition(float objHeight) {
		return ( GuitarHeroGame.HEIGHT - objHeight )/2;
	}
	
	public float getFontWidth(BitmapFont font, String str) {
		GlyphLayout layout = new GlyphLayout(font, str);
		return layout.width;
	}
	
	public float getFontHeight(BitmapFont font, String str) {
		GlyphLayout layout = new GlyphLayout(font, str);
		return layout.height;
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
	
	public void drawNoteLine(Note [] notes) {
		for(int i=notes.length - 1;i >= 0;i--) {
			if(notes[i] != null && notes[i].position.y <= GuitarHeroGame.HEIGHT * 19 / 20) {
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
