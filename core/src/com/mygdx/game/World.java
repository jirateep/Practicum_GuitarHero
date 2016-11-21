package com.mygdx.game;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;

public class World {

	public static final int RED = 0;
	public static final int BLUE = 1;
	public static final int GREEN = 2;
	public static final int YELLOW = 3;
	public static final int NBOFCOLOR = 4;
	
	public static final int PRESS = 0;
	public static final int UNPRESS = 1;
	public static final int CIRCLE = 2;
	public static final int NBOFSTATE = 3;

	LineCircle [] lines;
	
	public Texture [][] eachLineImg;
	public int [] xPosition;
	public int [] keys = {Keys.D,Keys.F,Keys.G,Keys.H};
	public int [][] song;
	public int [] songRed = {100,200,300,400,500,600,700,800,900,1000};
	public int [] songBlue = songRed;
	public int [] songGreen = {50,150,250,350,450,550,650,750,850,950,1050};
	public int [] songYellow = songGreen;
	
	public Timer timer;
	public static int speed = 5;
	
	public static Score score;
	
	public World() {
		
		song = new int [NBOFCOLOR][];
		song[RED] = songRed;
		song[BLUE] = songBlue;
		song[GREEN] = songGreen;
		song[YELLOW] = songYellow;
		
		eachLineImg = new Texture [NBOFCOLOR][NBOFSTATE];
		eachLineImg[RED][UNPRESS] = new Texture("redButtonFrame.png");
		eachLineImg[RED][PRESS] = new Texture("redButtonFramePress.png");
		eachLineImg[RED][CIRCLE] = new Texture("redCircle.png");
		eachLineImg[BLUE][UNPRESS] = new Texture("blueButtonFrame.png");
		eachLineImg[BLUE][PRESS] = new Texture("blueButtonFramePress.png");
		eachLineImg[BLUE][CIRCLE] = new Texture("blueCircle.png");
		eachLineImg[GREEN][UNPRESS] = new Texture("greenButtonFrame.png");
		eachLineImg[GREEN][PRESS] = new Texture("greenButtonFramePress.png");
		eachLineImg[GREEN][CIRCLE] = new Texture("greenCircle.png");
		eachLineImg[YELLOW][UNPRESS] = new Texture("yellowButtonFrame.png");
		eachLineImg[YELLOW][PRESS] = new Texture("yellowButtonFramePress.png");
		eachLineImg[YELLOW][CIRCLE] = new Texture("yellowCircle.png");
		
		timer = new Timer();
		
		xPosition = new int [NBOFCOLOR];
		xPosition[RED] = GuitarHeroGame.WIDTH/2-2*eachLineImg[BLUE][UNPRESS].getWidth() - 15;
		xPosition[BLUE] = GuitarHeroGame.WIDTH/2-eachLineImg[BLUE][UNPRESS].getWidth() - 5;
		xPosition[GREEN] = GuitarHeroGame.WIDTH/2 + 5;
		xPosition[YELLOW] = GuitarHeroGame.WIDTH/2 + 15 + eachLineImg[BLUE][UNPRESS].getWidth();
		//for(int i=1;i<xPosition.length;i++) {
		//	xPosition[i] = xPosition[i-1] + 300;
		//}
		
		lines = new LineCircle [NBOFCOLOR];
		for(int i=0;i<NBOFCOLOR;i++) {
			lines[i] = new LineCircle(xPosition[i],song[i],timer,keys[i],eachLineImg[i][UNPRESS],eachLineImg[i][PRESS],eachLineImg[i][CIRCLE],i);
		}
		
		score = new Score();
	}
	
	public void update(float delta) {
		for(int i=0;i<NBOFCOLOR;i++) {
			lines[i].update();
		}
		timer.update();
	}
}
