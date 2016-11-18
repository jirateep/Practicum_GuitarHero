package com.mygdx.game;

public class LineCircle {
	float x;
	Circle [] circles = new Circle [20];
	
	public LineCircle(int x) {
		this.x = x;
	}
	
	public void update() {
		runCircles();
		reserveCircles();		
	}
	
	private void runCircles() {
		for(int i=0;i<circles.length;i++) {
			if(circles[i] != null) {
				circles[i].update();
			}
		}
	}
	
	private void reserveCircles() {
		int pos = findExist();
		if(pos >= 0) {
			addCircles(pos);
		}
	}
	
	private int findExist() {
		for(int i=0;i<circles.length;i++) {
			if(circles[i] == null)
				return i;
		}
		return -1;
	}
	
	private void addCircles(int pos) {
		circles[pos] = new Circle(x);
	}
}