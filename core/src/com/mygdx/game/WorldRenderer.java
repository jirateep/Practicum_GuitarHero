package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class WorldRenderer {
	
	private SpriteBatch batch;
	private World world;
	private Font font;
	private DrawPlayingWindow drawPlayingWindow;
	private int twinkle = 0;

	public WorldRenderer(World world) {
		batch = new SpriteBatch();
		this.world = world;
		font = new Font(batch);
		drawPlayingWindow = new DrawPlayingWindow(batch,world,font);
	}
	
	public void render() {		
		batch.begin();
		if(world.startingSong) {
			drawHomeMenu();
		}else {
			if(!world.endingSong) {
				drawPlayingWindow.draw();
			} else {
				drawEndGame();
			}
		}
		batch.end();
	}
	
	private void drawHomeMenu() {
		if(!world.homeMenu.isCreditsOn){
			drawLogo();
			drawSongNameForHome();
			drawButton();
		} else {
			drawCredits();
		}
	}
	
	private void drawCredits() {
		String [] credits = {"idea:","Guitar Hero Game","",
				             "notes:","Thapster","",
				             "image:","http://www.clipartbest.com/electric-guitar-vector-free","https://commons.wikimedia.org/wiki/File:The_red_button.png","",
				             "font:","Arcade","","","",
				             "    created by Jirateep Tantisuwankul,Peerapong Tawantaweekit"};
		
		for(int i=0;i<credits.length;i++) {
			font.w_gFont.draw(batch,credits[i],50,GuitarHeroGame.HEIGHT - 100 - i*50);
		}
	}
	
	private void drawLogo() {
		float xPosition = Arrange.getCenterXPosition(HomeMenu.logo.getWidth());
		batch.draw(HomeMenu.logo,xPosition,500);
	}
	
	private void drawButton() {
		batch.draw(HomeMenu.redButton,525,150,125,125);
		batch.draw(HomeMenu.blueButton,725,150,125,125);
		batch.draw(HomeMenu.greenButton,925,150,125,125);
		batch.draw(HomeMenu.yellowButton,1125,150,125,125);
	}
	
	private void drawSongNameForHome() {
		String songName = world.homeMenu.songList[world.homeMenu.selection];
		float width = Arrange.getFontWidth(font.startGameHeadFont,songName);
		float height = Arrange.getFontHeight(font.startGameHeadFont,songName);
		float xPosition = Arrange.getCenterXPosition(width);
		float yPosition = Arrange.getCenterYPosition(height);
		font.startGameHeadFont.draw(batch,songName,xPosition,yPosition);
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
			float width = Arrange.getFontWidth(font.endGameDetailFont,string);
			float xPosition = Arrange.getCenterXPosition(width);
			font.endGameDetailFont.draw(batch,string,xPosition,100);
		}
		twinkle++;
	}
	
	private void drawEndMaxCombo(float xPos,float yPos) {
		String maxCombo = "MAXCOMBO    " + World.score.maxCombo;
		font.endGameDetailFont.draw(batch,maxCombo,xPos,yPos);
	}
	
	private void drawEndCombo() {
		String [] numCombo = {"PERFECT     "+World.score.countCombo[0],
							  "EXCELLENT   "+World.score.countCombo[1],
							  "GOOD        "+World.score.countCombo[2],
							  "BAD         "+World.score.countCombo[3],
							  "MISS        "+World.score.countCombo[4]};
		int i = 0;
		float width = Arrange.getFontWidth(font.endGameDetailFont,numCombo[0]);
		float xPosition = Arrange.getCenterXPosition(width)-200;
		for(;i<numCombo.length;i++) {
			font.endGameDetailFont.draw(batch,numCombo[i],xPosition,GuitarHeroGame.HEIGHT-280-i*60);
		}
		drawEndMaxCombo(xPosition,GuitarHeroGame.HEIGHT-280 - i*60);
	}
	
	private void drawEndRank() {
		if(world.endMenu.rank != "") {
			String rank = world.endMenu.rank;
			font.chooseRankFont(rank);
			float width = Arrange.getFontWidth(font.rankFont,rank);
			float height = Arrange.getFontHeight(font.rankFont,rank);
			float xPosition = Arrange.getRightXPosition(width) - 450;
			float yPosition = Arrange.getTopYPosition(height) - 150;
			if(rank == "SS") {
				xPosition += 100;
			}
			font.rankFont.draw(batch,rank,xPosition,yPosition);
		}
	}
	
	private void drawEndScore() {
		String scoreString = "SCORE " + world.endMenu.scoreString;
		float width = Arrange.getFontWidth(font.endGameHeadFont,scoreString);
		float height = Arrange.getFontHeight(font.endGameHeadFont,scoreString);
		float xPosition = Arrange.getCenterXPosition(width)- 200;
		float yPosition = Arrange.getTopYPosition(height) - 150;
		font.endGameHeadFont.draw(batch,scoreString,xPosition,yPosition);
	}
}
