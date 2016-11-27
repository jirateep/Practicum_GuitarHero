package com.mygdx.game;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class World {

	public static final int RED = 0;
	public static final int BLUE = 1;
	public static final int GREEN = 2;
	public static final int YELLOW = 3;
	public static final int NBOFCOLOR = 4;
	
	public static final int PRESS = 0;
	public static final int UNPRESS = 1;
	public static final int NOTE = 2;
	public static final int NOTECOMBO = 3;
	public static final int NBOFSTATE = 4;

	NoteLine [] lines;
	
	public Texture [][] eachLineImg;
	public int [] xPosition;
	public int [] keys = {Keys.D,Keys.F,Keys.G,Keys.H};
	public Sound song;
	public int [][] note;
	public int [] songRed = {100,200,300,400,500,600,700,800,900,1000,1010,1030,1090,1100,1120,1400,1420,1460,1500,1600,1700,1800,1880,1930,1950,1970,1990,2000};//{100,150,200,250,300,350,400,450,500,550,600,650,700,750,800,850,900,950,1000};
	public int [] songBlue = songRed;
	public int [] songGreen = {50,150,250,350,450,550,650,750,850,950,1050,1070,1080,1150,1180,1210,1230,1260,1270,1310,1430,1470,1490,1510,1540,1580,1670,1840,1890,1900,1940,1980};
	public int [] songYellow = songGreen;
	
	public Timer timer;
	public static int speed = 10;
	public Texture dotImg;
	public DotLine dotLine;
	
	public static Score score;
	
	public static int halfDistance = 50;//5
	public SongList songList;
	public Texture bgImg;
	public String name;
	
	
	public World() {
		
		songList = new SongList(this);
		songList.getSong();
		//note = new int [NBOFCOLOR][];
		//note[RED] = songRed;
		//note[BLUE] = songBlue;
		//note[GREEN] = songGreen;
		//note[YELLOW] = songYellow;
		song.play();
		eachLineImg = new Texture [NBOFCOLOR][NBOFSTATE];
		eachLineImg[RED][UNPRESS] = new Texture("redButtonFrame.png");
		eachLineImg[RED][PRESS] = new Texture("redButtonFramePress.png");
		eachLineImg[RED][NOTE] = new Texture("redCircle.png");
		eachLineImg[RED][NOTECOMBO] = new Texture("redCircleCombo.png");
		eachLineImg[BLUE][UNPRESS] = new Texture("blueButtonFrame.png");
		eachLineImg[BLUE][PRESS] = new Texture("blueButtonFramePress.png");
		eachLineImg[BLUE][NOTE] = new Texture("blueCircle.png");
		eachLineImg[BLUE][NOTECOMBO] = new Texture("blueCircleCombo.png");
		eachLineImg[GREEN][UNPRESS] = new Texture("greenButtonFrame.png");
		eachLineImg[GREEN][PRESS] = new Texture("greenButtonFramePress.png");
		eachLineImg[GREEN][NOTE] = new Texture("greenCircle.png");
		eachLineImg[GREEN][NOTECOMBO] = new Texture("greenCircleCombo.png");
		eachLineImg[YELLOW][UNPRESS] = new Texture("yellowButtonFrame.png");
		eachLineImg[YELLOW][PRESS] = new Texture("yellowButtonFramePress.png");
		eachLineImg[YELLOW][NOTE] = new Texture("yellowCircle.png");
		eachLineImg[YELLOW][NOTECOMBO] = new Texture("yellowCircleCombo.png");
		
		timer = new Timer();
		
		xPosition = new int [NBOFCOLOR];
		xPosition[RED] = GuitarHeroGame.WIDTH/2-2*eachLineImg[BLUE][UNPRESS].getWidth() - 3 * halfDistance;
		xPosition[BLUE] = GuitarHeroGame.WIDTH/2-eachLineImg[BLUE][UNPRESS].getWidth() - halfDistance;
		xPosition[GREEN] = GuitarHeroGame.WIDTH/2 + halfDistance;
		xPosition[YELLOW] = GuitarHeroGame.WIDTH/2 + 3 * halfDistance + eachLineImg[BLUE][UNPRESS].getWidth();

		lines = new NoteLine [NBOFCOLOR];
		for(int i=0;i<NBOFCOLOR;i++) {
			lines[i] = new NoteLine(xPosition[i],note[i],timer,keys[i],eachLineImg[i],i);
		}
		
		dotImg = new Texture("dotResize.png");
		dotLine = new DotLine(dotImg,this);
		
		score = new Score(this);
	}
	
	public void update(float delta) {
		for(int i=0;i<NBOFCOLOR;i++) {
			lines[i].update();
		}
		timer.update();
		dotLine.update();
		score.updateReachComboTime();
	}
}