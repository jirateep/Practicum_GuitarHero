package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class SongList {
	
	public int [][][] songNote;
	public String [] songName;
	public Texture [] songImg;
	public Sound [] songSound;
	public static final int ROOMEARAI = 0;
	public static final int NBOFSONG = 1;
	public static final int MAXCOLOR = 4;
	private World world;
	
	public SongList(World world) {
		this.world = world;
		songNote = new int [NBOFSONG][MAXCOLOR][];
		songName = new String [NBOFSONG];
		songImg = new Texture [NBOFSONG];
		songSound = new Sound [NBOFSONG];
		initSong();
	}
	
	private void initSong() {
		songName[ROOMEARAI] = "รู้หมือไร่";
		songImg[ROOMEARAI] = new Texture("roomearai.png");
		songSound[ROOMEARAI] = Gdx.audio.newSound(Gdx.files.internal("roomearai.mp3"));
		songNote[ROOMEARAI][World.RED] =new int [] {474,779,824,1336,1388,1482,1569,1653,1752};
		songNote[ROOMEARAI][World.BLUE] =new int [] {511,864,911,1000,1290,1336,1435,1522,1610,1698};
		songNote[ROOMEARAI][World.GREEN] =new int [] {556,637,692,732,1196,1799};
		songNote[ROOMEARAI][World.YELLOW] =new int [] {1050,1103,1144,1243};
	}
	
	public void getSong() {
		world.song = songSound[ROOMEARAI];
		world.note = genSong(ROOMEARAI);
		world.bgImg = songImg[ROOMEARAI];
		world.name = songName[ROOMEARAI];
	}
	
	public int[][] genSong(int songPosition) {
		float changeFactor = 2.0f/5;
		int[][] nowSong = new int [World.NBOFCOLOR][];
		for(int i=0;i<World.NBOFCOLOR;i++) {
			int len = songNote[songPosition][i].length;
			nowSong[i] = new int [len];
			for(int j=0;j<len;j++) {
				nowSong[i][j] = (int) (songNote[songPosition][i][j]*changeFactor);
			}
		}
		return nowSong;
	}
	
}
