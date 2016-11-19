package com.mygdx.game;

public class Timer {
	public int timer = 0;
	
	public void update() {
		timer++;
		//System.out.println(timer);
	}
	
	public int getTime() {
		return timer;
	}
}
