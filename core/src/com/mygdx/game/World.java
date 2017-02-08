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
	
	public Timer timer;
	public static int speed;
	public Texture dotImg;
	public DotLine dotLine;
	
	public static Score score;
	
	public static int halfDistance = 50;//5
	public SongList songList;
	public Texture bgImg;
	public String name;
	
	public EndMenu endMenu;
	public HomeMenu homeMenu;
	
	public boolean startingSong = true;
	public boolean endingSong = false;
	
	public float percentSong = 0;
	
	public HardWare hardWare;
	
	public World(HardWare hardWare) {
	
		songList = new SongList(this);
		homeMenu = new HomeMenu(this);
		eachLineImg = new Texture [NBOFCOLOR][NBOFSTATE];
		addEachLine(RED,"red");
		addEachLine(BLUE,"blue");
		addEachLine(GREEN,"green");
		addEachLine(YELLOW,"yellow");
		
		timer = new Timer();
		
		xPosition = new int [NBOFCOLOR];
		xPosition[RED] = GuitarHeroGame.WIDTH/2-2*eachLineImg[BLUE][UNPRESS].getWidth() - 3 * halfDistance;
		xPosition[BLUE] = GuitarHeroGame.WIDTH/2-eachLineImg[BLUE][UNPRESS].getWidth() - halfDistance;
		xPosition[GREEN] = GuitarHeroGame.WIDTH/2 + halfDistance;
		xPosition[YELLOW] = GuitarHeroGame.WIDTH/2 + 3 * halfDistance + eachLineImg[BLUE][UNPRESS].getWidth();

		this.hardWare = hardWare;
		
		lines = new NoteLine [NBOFCOLOR];
		
		dotImg = new Texture("dotResize.png");
		dotLine = new DotLine(dotImg,this);
		
		score = new Score(this);
		endMenu = new EndMenu(this);
	}

	public void addEachLine(int colorCode, String color) {
		Texture [] eachline = new Texture[NBOFSTATE];
		eachLineImg[colorCode][UNPRESS] = new Texture(color + "ButtonFrame.png");
		eachLineImg[colorCode][PRESS] = new Texture(color + "ButtonFramePress.png");
		eachLineImg[colorCode][NOTE] = new Texture(color + "Circle.png");
		eachLineImg[colorCode][NOTECOMBO] = new Texture(color + "CircleCombo.png");
	}
	
	public void update(float delta) {
		hardWare.update();
		if(startingSong) {
			homeMenu.update();
		} else {
			if(!endingSong) {
				for(int i=0;i<NBOFCOLOR;i++) {
					lines[i].update();
				}
				timer.update();
				dotLine.update();
				score.updateReachComboTime();
				isEnd();
			} else {
				endMenu.update();
			}
		}
	}
	
	public void printPass() {
		for(int i=0;i<NBOFCOLOR;i++) {
			System.out.print(lines[i].passed + " " + lines[i].songTime.length + " ");
		}
		System.out.println();
	}
	
	public void isEnd() {
		int countEnd = 0;
		for(int i=0;i<NBOFCOLOR;i++) {
			if(lines[i] != null && lines[i].endNotes) {
				countEnd++;
			}
		}
		if(countEnd == NBOFCOLOR) {
			endingSong = true;
			return;
		}
		endingSong = false;
	}
}