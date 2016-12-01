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
		songNote[ROOMEARAI][World.RED] =new int [] {424,705,719,1224,1602,1712,1728,2023,2317,2724,2821,2920,3521,3607,4126,4217,4308,4721,4805,4819,4903,5023,5626,5710,5724,5808,10103,10200,10228,10327,10509,10901,10913,11224,11307,11600,11700,11826,12022,12303,12400,12612,12808,12920,13002,13221,13318,13403,13505,13523,13705,13804,13929,14210,14420,14518,14728,15009,15023,15310,15604,15724,15808,20102,20313,20327,20509,20929,21006,21013,21126,21203,21210,21518,21602,21623,21911,22304,22318,22528,22611,23018,23116,23313,23509,23523,23620,23719,23929,24014,24028,24111,24329,24413,24428,24512,24718,24725,24801,24809,24815,24823,24900,24907,25022};
		songNote[ROOMEARAI][World.BLUE] =new int [] {817,1028,1111,1419,1503,1910,1924,2121,2218,2625,2708,3018,3116,3312,3326,3424,3803,3824,3916,4420,4503,4616,4700,4925,5304,5317,5401,5415,5906,10004,10425,10705,10719,11026,11111,11405,11503,11729,11925,12106,12205,12514,12710,13116,13200,13424,13817,14027,14125,14322,14616,14714,14819,14904,15114,15212,15324,15429,15513,15625,15710,15822,15906,15920,20004,20017,20116,20221,20425,20607,20614,20621,20803,20810,20817,21322,21404,21426,21714,21728,21812,21827,22009,22107,22121,22500,22514,22821,22920,23215,23425,23802,23816,23901,23917,24210,24308,24525,24609,24624,24707,24925};
		songNote[ROOMEARAI][World.GREEN] =new int [] {509,803,900,1125,1209,1322,1405,1811,1825,2303,2400,2527,2611,2905,3004,3214,3228,3628,3719,4322,4406,4518,4602,4911,5107,5121,5205,5219,10018,10116,10411,10621,10625,10803,10817,11321,11518,11715,11911,12218,12415,12625,12724,13018,13102,13410,13621,13901,13929,14308,14315,14406,14518,14813,14826,14925,15106,15121,15205,15219,15304,15401,15507,15703,15717,15829,15927,20110,20214,20229,20502,20705,20713,20719,20901,20908,20915,21329,21420,21503,21722,21806,21820,21905,22022,22402,22416,22724,22809,23003,23130,23327,23501,23606,23620,23705,23719,24014,24111,24322,24406,24420,24505,24911};
		songNote[ROOMEARAI][World.YELLOW] =new int[] {412,607,619,929,1013,1308,1517,1616,2009,2107,2204,2429,2513,2808,3102,3410,3508,3929,4020,4112,4714,4728,4812,4827,5009,5429,5513,5527,5611,5822,5920,10214,10312,10523,10929,11013,11126,11209,11420,11615,11813,12009,12120,12317,12500,12527,12822,12906,13214,13227,13312,13326,13509,13607,13804,14013,14125,14224,14311,14318,14603,14714,14911,15023,15317,15527,15612,15808,20025,20320,20411,20418,20523,21028,21106,21111,21225,21301,21308,21526,21616,21702,21925,22205,22218,22625,22710,22906,23101,23229,23411,23425,23523,23816,23917,24125,24210,24224,24308,24518,24602,24617,24629,24715,24721,24729,24805,24813,24819,24826,24903,25009};

	}
	
	public void getSong() {
		world.song = songSound[ROOMEARAI];
		world.note = genSong(ROOMEARAI);
		world.bgImg = songImg[ROOMEARAI];
		//System.out.println("hi");
		world.name = songName[ROOMEARAI];
		//System.out.println("hi");
	}
	
	public int[][] genSong(int songPosition) {
		int allCount = 0;
		int delay = 10;
		float changeFactor = 2.0f;//10.0f/9;
		int[][] nowSong = new int [World.NBOFCOLOR][];
		for(int i=0;i<World.NBOFCOLOR;i++) {
			int len = songNote[songPosition][i].length;
			nowSong[i] = new int [len];
			int add = 0;
			for(int j=0;j<len;j++) {
				//if(j==0 ||(j!=0 && songNote[songPosition][i][j]-songNote[songPosition][i][j-1]>4)) {
					int noteData = songNote[songPosition][i][j];
					int ms = noteData % 100;
					noteData /= 100;
					int s = noteData %100;
					noteData /=100;
					int m = noteData;
					int trueMiliSec = ms + s*30 + m*30*60;
					nowSong[i][add] = (int) (trueMiliSec*changeFactor-GuitarHeroGame.HEIGHT/World.speed+delay);
					add++;
					if(j!=0 && nowSong[i][j] <= nowSong[i][j-1]) {
						System.out.println("error wrongNote: "+i+" "+songNote[ROOMEARAI][i][j]);
					}
					allCount++;
					//System.out.println(trueMiliSec*changeFactor-GuitarHeroGame.HEIGHT/World.speed);
				//}
				
			}
		}
		world.maxScore = ((allCount-100)*100)*2+(100*100)+30*allCount;
		System.out.println(world.maxScore);;
		return nowSong;
	}
}
