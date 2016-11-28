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
		songName[ROOMEARAI] = "ROOMEARAI";
		songImg[ROOMEARAI] = new Texture("roomearai.jpg");
		songSound[ROOMEARAI] = Gdx.audio.newSound(Gdx.files.internal("roomearai.mp3"));
		songNote[ROOMEARAI][World.RED] =new int [] {611,618,625,709,716,723,807,814,1308,1311,1529,2022,2026,2124,2216,2313,2405,2503,2601,2629,2727,2807,2810,3028,3120,3218,3316,3414,3512,4013,4104,4122,4326,4402,4620,4627,5026,5114,5314,5422,5429,5520,5524,5528,5706,5829,6015};
		songNote[ROOMEARAI][World.BLUE] =new int [] {414,512,905,1003,1010,1017,1101,1220,1304,1408,1423,1500,1507,1522,1620,1718,1725,1802,1816,2019,2110,2117,2209,2223,2307,2321,2328,2517,2615,2713,2805,2825,2901,2909,2923,3000,3007,3021,3106,3112,3302,3400,3428,3526,3610,3701,3723,3807,3828,3918,3929,4209,4301,4319,4522,4529,4613,4718,4725,4802,4809,4816,4821,4827,4903,4910,4914,5012,5117,5127,5215,5401,5425,5608,5819,5823,5903,5817,5920,6001};
		songNote[ROOMEARAI][World.GREEN] =new int [] {428,828,1108,1115,1129,1206,1213,1301,1325,1402,1613,1823,1900,1914,2016,2124,2223,2321,2328,2419,2615,2629,2713,2720,2801,2909,3007,3106,3204,3323,3400,3414,3421,3428,3512,3526,3621,3817,3908,3926,4002,4023,4114,4129,4220,4311,4322,4424,4500,4508,4514,4606,4721,4729,4805,4813,4819,4824,4900,4907,4928,5110,5201,5204,5219,5327,5404,5510,5515,5622,5821,5906,5910};
		songNote[ROOMEARAI][World.YELLOW] =new int [] {821,1228,1606,1922,1928,2005,2013,2110,2209,2307,2405,2503,2510,2517,2601,2608,2706,2727,2825,2923,3021,3120,3218,3225,3302,3316,3505,3712,3800,4410,4417,4704,4711,5026,5117,5124,5412,5415,5517,5720,5805,5808,5910,6008};
	}
	
	public void getSong() {
		world.song = songSound[ROOMEARAI];
		world.note = genSong(ROOMEARAI);
		world.bgImg = songImg[ROOMEARAI];
		System.out.println("hi");
		world.name = songName[ROOMEARAI];
		System.out.println("hi");
	}
	
	public int[][] genSong(int songPosition) {
		//int delay = -2;
		float changeFactor = 2.032f;//10.0f/9;
		int[][] nowSong = new int [World.NBOFCOLOR][];
		for(int i=0;i<World.NBOFCOLOR;i++) {
			int len = songNote[songPosition][i].length;
			nowSong[i] = new int [len];
			int add = 0;
			for(int j=0;j<len;j++) {
				if(j==0 ||(j!=0 && songNote[songPosition][i][j]-songNote[songPosition][i][j-1]>4)) {
					int noteData = songNote[songPosition][i][j];
					int trueMiliSec = 30*(noteData/100)+noteData%100;
					nowSong[i][add] = (int) (trueMiliSec*changeFactor-GuitarHeroGame.HEIGHT/World.speed);
					add++;
					System.out.println(trueMiliSec*changeFactor-GuitarHeroGame.HEIGHT/World.speed);
				}
				
			}
		}
		return nowSong;
	}
	
}
