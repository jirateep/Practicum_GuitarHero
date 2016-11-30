package com.mygdx.game;

public class Timer {
	public int timer = 0;
	public boolean reachComboTimeStart = false;
	public int reachComboTimeTimer = 0;
	public int maxReachComboTimeTimer = 600;
	public int stayComboTimer = 0;
	public int maxStayComboTimer = 100;
	
	public void update() {
		timer++;
		//System.out.println(timer);
		reachComboTimeTimerCount();
		stayComboStringTimer();
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
	
	public void stayComboStringTimer () {
		if(World.score.nowComboString != null) {
			stayComboTimer++;
		}
		if(stayComboTimer == maxStayComboTimer) {
			stayComboTimer = 0;
			World.score.nowComboString = null;
		}
	}
}