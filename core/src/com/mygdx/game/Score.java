package com.mygdx.game;

public class Score {

	public int score = 0;
	public int combo = 0;
	public static final int PERFECT = 0;
	public static final int EXCELLENT = 1;
	public static final int GOOD = 2;
	public static final int BAD = 3;
	public static final int MISS = 4;
	public static final int NBOFCRITERIA = 5;
	public int [] scoreCriteria;
	public int [] distanceCriteria;
	public int [] countCombo;
	
	public Score() {
		scoreCriteria = new int [NBOFCRITERIA];
		scoreCriteria[PERFECT] = 1000;
		scoreCriteria[EXCELLENT] = 500;
		scoreCriteria[GOOD] = 100;
		scoreCriteria[BAD] = 50;
		scoreCriteria[MISS] = 0;
		
		distanceCriteria = new int [NBOFCRITERIA];
		distanceCriteria[PERFECT] = 50;
		distanceCriteria[EXCELLENT] = 100;
		distanceCriteria[GOOD] = 200;
		distanceCriteria[BAD] = 500;
		distanceCriteria[MISS] = 1000;
		
		countCombo = new int [NBOFCRITERIA];
	}
	
	public void updateScore(float distance) {
		int nowCombo = cirteriaScore(distance);
		increaseScore(nowCombo);
		updateCombo(nowCombo);
		System.out.println("score: " + score);
		System.out.println("combo: " + combo);
	}
	
	public void increaseScore(int nowCombo) {
		if(nowCombo >= PERFECT && nowCombo <= MISS) {
			score += scoreCriteria[nowCombo] + combo * 100;
		}
	}
	
	public void updateCombo(int nowCombo) {
		System.out.println("pass");
		if(nowCombo >= PERFECT && nowCombo <= GOOD) {
			combo++;
		} else {
			combo = 0;
		}
	}
	
	public int cirteriaScore(float distance) {
		int criteria = -1;
		System.out.println("distance: " + distance);
		for(int i=0;i<distanceCriteria.length;i++) {
			if(distance <= distanceCriteria[i]) {
				criteria = i;
				countCombo[i]++;
				break;
			}
		}
		System.out.println("this combo " + criteria);
		return criteria;
	}
}
