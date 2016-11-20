package com.mygdx.game;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;

public class World {
	
	LineCircle redLine;
	LineCircle blueLine;
	//LineCircle greenLine;
	//LineCircle yellowLine;
	public int [] songRed = {0,100,200,350,400,425,1000};
	public int [] songBlue = songRed;
	public Timer timer;
	public static int speed = 10;
	public Texture redDefaultButtonImg = new Texture("redButtonFrame.png");
	public Texture redPressButtonImg = new Texture("redButtonFramePress.png");
	public Texture redCircleImg = new Texture("redCircle.png");
	public Texture blueDefaultButtonImg = new Texture("blueButtonFrame.png");
	public Texture bluePressButtonImg = new Texture("blueButtonFramePress.png");
	public Texture blueCircleImg = new Texture("blueCircle.png");
	
	
	public World() {
		
		timer = new Timer();
		
		redLine = new LineCircle(0,songRed,timer,Keys.D,redDefaultButtonImg,redPressButtonImg,redCircleImg);
		blueLine = new LineCircle(300,songBlue,timer,Keys.F,blueDefaultButtonImg,bluePressButtonImg,blueCircleImg);
		//greenLine = new LineCircle();
		//yellowLine = new LineCircle();
	}
	
	public void update(float delta) {
		redLine.update();
		blueLine.update();
		timer.update();
	}
}
