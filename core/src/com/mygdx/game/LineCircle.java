package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class LineCircle {
	float x;
	public static final int MAXCIRCLEPERLINE = 10;
	public Circle [] circles = new Circle [MAXCIRCLEPERLINE];
	public int [] songTime;
	public Timer timer;
	public int pushButtonKey;
	public Texture pushUnPressButtonImg;
	public Texture pushPressButtonImg;
	public float buttonYPosition = 10;
	public boolean isPress = false;
	public boolean oldIsPress = false;
	public Texture circleImg;
	public int color;
	private boolean isCollected = false;
	
	public LineCircle(int x,int [] songTime,Timer timer,int pushButtonKey,Texture pushUnPressButtonImg,Texture pushPressButtonImg,Texture circleImg,int color) {
		this.x = x;
		this.songTime = songTime;
		this.timer = timer;
		this.pushButtonKey = pushButtonKey;
		this.pushUnPressButtonImg = pushUnPressButtonImg;
		this.pushPressButtonImg = pushPressButtonImg;
		this.circleImg = circleImg;
		this.color = color;
	}
	
	public void update() {
		runCircles();
		if(itsTimeToReserve(timer.getTime())) {
			reserveCircles();		
		}
		buttonPress();
	}
	
	public void buttonPress() {
		if(isButtonPress() && !oldIsPress) {
			float distance = removeNearestCircle();
			World.score.updateScore(distance);
		}
		oldIsPress = isPress;
	}

	private boolean isButtonPress() {
		if(Gdx.input.isKeyPressed(pushButtonKey) && !isCollected) {
			isPress = true;
			return true;
		}
		if(!Gdx.input.isKeyPressed(pushButtonKey)) {
			isCollected = false;
		}
		
		return false;
	}
	
	private float removeNearestCircle() {
		float distance;
		float minDistance = Float.MAX_VALUE;
		int position = -1;
		for(int i=0;i<circles.length;i++) {
			if(circles[i] != null) {
				distance = circles[i].position.y - buttonYPosition;
				//System.out.print("distance: " + distance);
				if(distance <= 200 && distance < minDistance) {
					minDistance = distance;
					position = i;
				}
			}
		}
		
		if(position != -1) {
			circles[position].removeThis = true;
			removeCircle(position);
			isCollected = true;
		}
		//System.out.println(minDistance);
		
		return minDistance;
	}
	
	private boolean itsTimeToReserve(int time) {
		for(int i=0;i<songTime.length;i++) {
			if(time == songTime[i]) {
				return true;
			}
		}
		return false;
	}
	
	private void runCircles() {
		for(int i=0;i<circles.length;i++) {
			if(circles[i] != null) {
				circles[i].update();
				removeCircle(i);
			}
		}
	}
	
	private void removeCircle(int position) {
		if(circles[position].removeThis) {
			circles[position] = null;
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
		//System.out.println(x);
		circles[pos] = new Circle(x,circleImg,color);
	}
}