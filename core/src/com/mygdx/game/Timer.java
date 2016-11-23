package com.mygdx.game;

public class Timer {
	public int timer = 0;
	public boolean reachComboTimeStart = false;
	public int reachComboTimeTimer = 0;
	public int maxReachComboTimeTimer = 600;
	public void update() {
		timer++;
		//System.out.println(timer);
	}
	
	public int getTime() {
		return timer;
	}
	
	public void reachComboTimeTimerCount() {
		if(reachComboTimeStart) {
			reachComboTimeTimer = 0;
		}
		reachComboTimeTimer++;
		if(reachComboTimeTimer == maxReachComboTimeTimer) {
			World.score.reachComboTime = false;
		}
	}
}
