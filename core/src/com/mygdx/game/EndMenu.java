package com.mygdx.game;

public class EndMenu {
	private int finalScore;
	public String scoreString = "0";
	private World world;
	private int count = 0;
	public String rank = "";
	
	public int maxScore;
	public float [] criteria = {99,95,80,70,50};//SS S A B C D
	
	public EndMenu(World world) {
		this.world = world;
	}
	
	public void update() {
		finalScore = World.score.score;
		if(world.endingSong()) {
			if(count != finalScore) {
				//System.out.println(count);
				int now = (int)(Math.random()*1500)+2000;
				count+=now;
				if(count > finalScore) {
					count = finalScore;
				}
				scoreString = String.format("%5d",count);
			} else {
				calRank();
			}
		}
	}
	
	private void calRank() {
		maxScore = world.maxScore;
		float percent = ((float)finalScore)*100/maxScore;
		System.out.println(percent + " " + maxScore);
		if(percent >= criteria[0]) {
			rank = "SS";
		} else if(percent >= criteria[1]) {
			rank = "S";
		} else if(percent >= criteria[2]) {
			rank = "A";
		} else if(percent >= criteria[3]) {
			rank = "B";
		} else if(percent >= criteria[4]) {
			rank = "C";
		} else {
			rank = "D";
		}
	}
	
}
