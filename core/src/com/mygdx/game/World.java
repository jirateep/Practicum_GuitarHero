package com.mygdx.game;

public class World {
	
	LineCircle circleRed;
	//LineCircle circleBlue;
	//LineCircle circleGreen;
	//LineCircle circleYellow;
	
	public World() {
		circleRed = new LineCircle(0);
		//circleBlue = new LineCircle();
		//circleGreen = new LineCircle();
		//circleYellow = new LineCircle();
	}
	
	public void update(float delta) {
		circleRed.update();
	}
}
