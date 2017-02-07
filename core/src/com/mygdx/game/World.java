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

		this.hardWare = hardWare;
		
		lines = new NoteLine [NBOFCOLOR];
		
		dotImg = new Texture("dotResize.png");
		dotLine = new DotLine(dotImg,this);
		
		score = new Score(this);
		endMenu = new EndMenu(this);
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