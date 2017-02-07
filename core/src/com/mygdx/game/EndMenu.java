package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class EndMenu {
	private int finalScore;
	public String scoreString = "0";
	private World world;
	private int count = 0;
	public String rank = "";
	public int countCanPress = 0;
	public int maxCountCanPress = 20;
	public int maxScore;
	public float [] criteria = {95,75,50};//SS S A B C D
	
	public EndMenu(World world) {
		this.world = world;
	}
	
	public void update() {
		finalScore = World.score.score;
		if(world.endingSong) {
			if(count < finalScore) {
				//System.out.println(count);
				int now = (int)(Math.random() * finalScore / 30.0);
				count+=now;
				if(count > finalScore) {
					count = finalScore;
				}
				scoreString = String.format("%5d",count);
			} else {
				//System.out.println("calRank");
				calRank();
				//System.out.println(rank);
			}
		}
		returnStarting();
	}
	
	private void returnStarting() {
		if(restartKey() && countCanPress > maxCountCanPress) {
			world.startingSong = true;
			world.endingSong = false;
			countCanPress = 0;
			world.homeMenu.previewSong.play();
		}
		countCanPress++;
	}
	
	public boolean updateSwitch(int color) {
		return world.hardWare.isSwitchPress[color];
	}
	
	private boolean restartKey() {
		return Gdx.input.isKeyPressed(Keys.D) ||
			   Gdx.input.isKeyPressed(Keys.F) ||
			   Gdx.input.isKeyPressed(Keys.G) ||
			   Gdx.input.isKeyPressed(Keys.H) ||
			   Gdx.input.isKeyPressed(Keys.UP) ||
			   Gdx.input.isKeyPressed(Keys.DOWN) ||
			   Gdx.input.isKeyPressed(Keys.LEFT) ||
			   Gdx.input.isKeyPressed(Keys.RIGHT) ||
			   Gdx.input.isKeyPressed(Keys.ENTER) ||
			   updateSwitch(World.RED) ||
			   updateSwitch(World.BLUE) ||
			   updateSwitch(World.GREEN) ||
			   updateSwitch(World.YELLOW);
	}
	
	private boolean isAllPerfect() {
		return World.score.countCombo[Score.PERFECT] == World.score.allCombo;
	}
	
	private boolean isAllCombo() {
		return World.score.countCombo[Score.BAD] == 0 && World.score.countCombo[Score.MISS] == 0;
	}
	
	private void calRank() {
		maxScore = World.score.maxScore;
		float percent = ((float)finalScore)*100/maxScore;
		//System.out.println(percent + " " + maxScore);
		if(isAllPerfect()) {
			rank = "SS";
		} else if(isAllCombo()) {
			rank = "S";
		} else if(percent >= criteria[0]) {
			rank = "A";
		} else if(percent >= criteria[1]) {
			rank = "B";
		} else if(percent >= criteria[2]) {
			rank = "C";
		} else {
			rank = "D";
		}
	}
	
}
