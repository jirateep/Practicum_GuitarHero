package com.mygdx.game;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;

public class World {
	
	LineCircle redLine;
	//LineCircle circleBlue;
	//LineCircle circleGreen;
	//LineCircle circleYellow;
	public int [] songRed = {0,100,200,350,400,425,1000};
	public Timer timer;
	public static int speed = 3;
	public Texture redDefaultButtonImg = new Texture("redButtonFrame.png");
	public Texture redPressButtonImg = new Texture("redButtonFramePress.png");
	
	public World() {
		
		timer = new Timer();
		
		redLine = new LineCircle(0,songRed,timer,Keys.SPACE,redDefaultButtonImg,redPressButtonImg);
		//circleBlue = new LineCircle();
		//circleGreen = new LineCircle();
		//circleYellow = new LineCircle();
	}
	
	public void update(float delta) {
		redLine.update();
		timer.update();
	}
}
