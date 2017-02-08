package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Font {
	private SpriteBatch batch;
	public BitmapFont y_oFont;
	public BitmapFont b_bFont;
	public BitmapFont p_pFont;
	public BitmapFont w_gFont;
	public BitmapFont g_gFont;
	public BitmapFont scoreFont;
	public BitmapFont comboFont;
	public BitmapFont comboStringFont;
	public BitmapFont endGameHeadFont;
	public BitmapFont endGameDetailFont;
	public BitmapFont [] endGameRankFont;
	public BitmapFont rankFont;
	public BitmapFont startGameHeadFont;
	
	public Font( SpriteBatch batch) {
		this.batch = batch;
		y_oFont = new BitmapFont(Gdx.files.internal("yellowOrangeArcade.fnt"));
		b_bFont = new BitmapFont(Gdx.files.internal("blueDarkBlueArcade.fnt"));
		p_pFont = new BitmapFont(Gdx.files.internal("pinkPurpleArcade.fnt"));
		w_gFont = new BitmapFont(Gdx.files.internal("whiteGrayArcade.fnt"));
		g_gFont = new BitmapFont(Gdx.files.internal("greenDarkGreenArcade.fnt"));
		scoreFont = new BitmapFont(Gdx.files.internal("scoreArcade.fnt"));
		
		endGameHeadFont = new BitmapFont(Gdx.files.internal("endHeadArcade.fnt"));
		endGameDetailFont = new BitmapFont(Gdx.files.internal("endDetailArcade.fnt"));
		startGameHeadFont = endGameHeadFont;
		
		endGameRankFont = new BitmapFont [6];
		endGameRankFont[0] = new BitmapFont(Gdx.files.internal("ssArcade.fnt"));
		endGameRankFont[1] = new BitmapFont(Gdx.files.internal("aArcade.fnt"));
		endGameRankFont[2] = new BitmapFont(Gdx.files.internal("bArcade.fnt"));
		endGameRankFont[3] = new BitmapFont(Gdx.files.internal("cArcade.fnt"));
		endGameRankFont[4] = new BitmapFont(Gdx.files.internal("dArcade.fnt"));
		rankFont = endGameRankFont[0];
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
	
	public void chooseRankFont(String rank) {
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
}
