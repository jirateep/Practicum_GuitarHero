package com.mygdx.game;

//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class Score {

	public int score = 0;
	public int combo = 0;
	public int maxCombo = 0;
	public static final int PERFECT = 0;
	public static final int EXCELLENT = 1;
	public static final int GOOD = 2;
	public static final int BAD = 3;
	public static final int MISS = 4;
	public static final int NBOFCRITERIA = 5;
	public int [] scoreCriteria;
	public int [] distanceCriteria;
	public int [] countCombo;
	public String [] comboStrings;
	public String nowComboString = null;
	public int comboScore = 5;
	public boolean reachComboTime = false;
	private double comboTimeFactor = 2;
	private int reachComboTimeNumber = 100;
	private World world;
	
	public Score(World world) {
		scoreCriteria = new int [NBOFCRITERIA];
		scoreCriteria[PERFECT] = 100;
		scoreCriteria[EXCELLENT] = 80;
		scoreCriteria[GOOD] = 40;
		scoreCriteria[BAD] = 10;
		scoreCriteria[MISS] = 0;
		
		distanceCriteria = new int [NBOFCRITERIA];
		distanceCriteria[PERFECT] = 50;
		distanceCriteria[EXCELLENT] = 80;
		distanceCriteria[GOOD] = 150;
		distanceCriteria[BAD] = 300;
		distanceCriteria[MISS] = 1000;
		
		comboStrings = new String [NBOFCRITERIA];
		comboStrings[PERFECT] = "PERFECT";
		comboStrings[EXCELLENT] = "EXCELLENT";
		comboStrings[GOOD] = "GOOD";
		comboStrings[BAD] = "BAD";
		comboStrings[MISS] = "MISS";
		
		countCombo = new int [NBOFCRITERIA];
		this.world = world;
	}
	
	public void updateScore(float distance) {
		int nowCombo = cirteriaScore(distance);
		increaseScore(nowCombo);
		updateCombo(nowCombo,distance);
		//System.out.println("score: " + score);
		//System.out.println("combo: " + combo);
	}

	public void increaseScore(int nowCombo) {
		if(nowCombo >= PERFECT && nowCombo <= MISS) {
			if(reachComboTime) {
				score += (int)((scoreCriteria[nowCombo] + combo * comboScore) * comboTimeFactor);
			} else {
				score += (int)(scoreCriteria[nowCombo] + combo * comboScore);
			}
			nowComboString = comboStrings[nowCombo];
			world.timer.stayComboTimer = 0;
		}
	}
	
	public void updateCombo(int nowCombo,float distance) {
		//System.out.println("pass");
		if(nowCombo >= PERFECT && nowCombo <= GOOD) {
			combo++;
		} else if(distance != Float.MAX_VALUE){
			combo = 0;
		}
		maxCombo = Math.max(maxCombo, combo);
	}
	
	public int cirteriaScore(float distance) {
		int criteria = -1;
		//System.out.println("distance: " + distance);
		for(int i=0;i<distanceCriteria.length;i++) {
			if(distance <= distanceCriteria[i]) {
				criteria = i;
				countCombo[i]++;
				break;
			}
		}
		//System.out.println("this combo " + criteria);
		return criteria;
	}
	
	public void updateReachComboTime() {
		if(combo >= reachComboTimeNumber) {
			world.timer.reachComboTimeStart = true;
			reachComboTime = true;
		} else {
			world.timer.reachComboTimeStart = false;
		}
	}
}
