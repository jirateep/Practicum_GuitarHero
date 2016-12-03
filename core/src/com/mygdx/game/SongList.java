package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class SongList {
	
	public int [][][] songNote;
	public String [] songName;
	public Texture [] songImg;
	public Sound [] songSound;
	public int [] songSpeed;
	public static final int ROOMEARAI = 0;
	public static final int REDO = 1;
	public static final int NBOFSONG = 2;
	public static final int MAXCOLOR = 4;
	private World world;
	
	public SongList(World world) {
		this.world = world;
		songNote = new int [NBOFSONG][MAXCOLOR][];
		songName = new String [NBOFSONG];
		songImg = new Texture [NBOFSONG];
		songSound = new Sound [NBOFSONG];
		songSpeed = new int [NBOFSONG];
		initSong();
	}
	
	private void initSong() {
		songName[ROOMEARAI] = "ROOMEARAI";
		songImg[ROOMEARAI] = new Texture("roomearai.jpg");
		songSpeed[ROOMEARAI] = 10;
		songSound[ROOMEARAI] = Gdx.audio.newSound(Gdx.files.internal("roomearai.mp3"));
		songNote[ROOMEARAI][World.RED] =new int [] {424,705,719,1224,1602,1712,1728,2023,2317,2724,2821,2920,3521,3607,4126,4217,4308,4721,4805,4819,4903,5023,5626,5710,5724,5808,10103,10200,10228,10327,10509,10901,10913,11224,11307,11600,11700,11826,12022,12303,12400,12612,12808,12920,13002,13221,13318,13403,13505,13523,13705,13804,13929,14210,14420,14518,14728,15009,15023,15310,15604,15724,15808,20102,20313,20327,20509,20929,21006,21013,21126,21203,21210,21518,21602,21623,21911,22304,22318,22528,22611,23018,23116,23313,23509,23523,23620,23719,23929,24014,24028,24111,24329,24413,24428,24512,24718,24725,24801,24809,24815,24823,24900,24907,25022};
		songNote[ROOMEARAI][World.BLUE] =new int [] {817,1028,1111,1419,1503,1910,1924,2121,2218,2625,2708,3018,3116,3312,3326,3424,3803,3824,3916,4420,4503,4616,4700,4925,5304,5317,5401,5415,5906,10004,10425,10705,10719,11026,11111,11405,11503,11729,11925,12106,12205,12514,12710,13116,13200,13424,13817,14027,14125,14322,14616,14714,14819,14904,15114,15212,15324,15429,15513,15625,15710,15822,15906,15920,20004,20017,20116,20221,20425,20607,20614,20621,20803,20810,20817,21322,21404,21426,21714,21728,21812,21827,22009,22107,22121,22500,22514,22821,22920,23215,23425,23802,23816,23901,23917,24210,24308,24525,24609,24624,24707,24925};
		songNote[ROOMEARAI][World.GREEN] =new int [] {509,803,900,1125,1209,1322,1405,1811,1825,2303,2400,2527,2611,2905,3004,3214,3228,3628,3719,4322,4406,4518,4602,4911,5107,5121,5205,5219,10018,10116,10411,10621,10625,10803,10817,11321,11518,11715,11911,12218,12415,12625,12724,13018,13102,13410,13621,13901,13929,14308,14315,14406,14518,14813,14826,14925,15106,15121,15205,15219,15304,15401,15507,15703,15717,15829,15927,20110,20214,20229,20502,20705,20713,20719,20901,20908,20915,21329,21420,21503,21722,21806,21820,21905,22022,22402,22416,22724,22809,23003,23130,23327,23501,23606,23620,23705,23719,24014,24111,24322,24406,24420,24505,24911};
		songNote[ROOMEARAI][World.YELLOW] =new int[] {412,607,619,929,1013,1308,1517,1616,2009,2107,2204,2429,2513,2808,3102,3410,3508,3929,4020,4112,4714,4728,4812,4827,5009,5429,5513,5527,5611,5822,5920,10214,10312,10523,10929,11013,11126,11209,11420,11615,11813,12009,12120,12317,12500,12527,12822,12906,13214,13227,13312,13326,13509,13607,13804,14013,14125,14224,14311,14318,14603,14714,14911,15023,15317,15527,15612,15808,20025,20320,20411,20418,20523,21028,21106,21111,21225,21301,21308,21526,21616,21702,21925,22205,22218,22625,22710,22906,23101,23229,23411,23425,23523,23816,23917,24125,24210,24224,24308,24518,24602,24617,24629,24715,24721,24729,24805,24813,24819,24826,24903,25009};

		songName[REDO] = "REDO";
		songImg[REDO] = new Texture("redo.jpg");
		songSpeed[REDO] = 16;
		songSound[REDO] = Gdx.audio.newSound(Gdx.files.internal("redo.mp3"));
		songNote[REDO][World.RED] = new int [] {120,124,623,703,712,928,1303,1517,1525,1708,1818,2019,2028,2305,2313,2520,2529,2700,2712,2914,3023,3307,3321,3622,3810,3925,4015,4103,4113,4123,4310,4400,4527,4610,4712,4802,4919,5206,5211,5402,5414,5612,5702,5821,10004,10013,10207,10328,10412,10618,10707,10821,10927,11022,11500,11607,11709,11715,11819,11919,12403,12503,12610,12706,12809,13002,13012,13102,13308,13522,13527,13720,13729,13901,13911,14221,14301,14507,14517,14713,15000,15018,15116,15224,15501,15510,15712,15721,15908,15922,20319,20328,20507,20614,20800,20814,21124,21129,21203,21321,21423,21508,21625,21628,21823,21903,22103,22123,22310,22511,22610,22624,22817,22829,23105,23123,23310,23415,23509,24409,24604,24702,25010,25206,25216,25226,30318,30322,30427,30501,30604,30609,30712,30718,31409,31425,31502,31808,32000,32020,32119,32207,32227,32307,32315,32423,32427,32520,32717,32802,32904,32924,33113,33219,33327,33401,33612,33819,34022,34109,34409,34417,34612,34728,34802,35023,35123,35200,35211,35219,35300,35500,35600,35614,35801,35805,35917,40029,40128,40206,40216,40408,40413,40418,40427,40603,40613,40801,40903,40929,41008,41400,41526,41611,41627,41711,41725,42002,42021,42117,42305,42310};
		songNote[REDO][World.BLUE] = new int [] {228,302,729,810,828,1106,1214,1321,1501,1507,1605,1728,2000,2008,2217,2225,2322,2327,2609,2716,2816,2923,3110,3119,3524,3608,3710,3800,3820,3906,4023,4410,4518,4704,4821,4909,4929,5107,5117,5126,5304,5309,5504,5604,5622,10023,10123,10209,10308,10510,10609,10629,10905,10917,11104,11208,12000,12022,12118,12215,12521,12720,12801,12826,12904,12924,13200,13503,13701,13809,14026,14104,14418,14428,14505,14704,14812,14904,14914,15124,15313,15323,15413,15520,15628,15806,15823,20013,20121,20229,20310,20417,20516,20606,20720,20908,20913,20917,21016,21021,21026,21302,21312,21408,21522,21526,21616,21725,21729,21925,22025,22113,22422,22502,22626,22801,23001,23025,23115,23322,23406,23523,23628,23906,24025,24128,24202,24306,24400,24820,24920,25028,25126,30822,30826,30928,31002,31104,31110,31211,31217,31401,31509,31618,31701,31710,31728,31902,31920,32010,32028,32217,32404,32600,32708,32826,32915,33013,33103,33121,33300,33309,33317,33409,33416,33524,33604,33911,34011,34101,34117,34125,34205,34215,34225,34429,34526,34614,34714,34816,34914,35013,35112,35412,35422,35617,35717,35819,40011,40118,40220,40316,40328,40514,40519,40524,40711,40721,40815,40825,40909,40913,40917,41106,41116,41214,41224,41302,41312,41320,41715,41815,41900,41912,41927,42126,42206};
		songNote[REDO][World.GREEN] = new int [] {407,413,713,908,918,1008,1124,1401,1615,1724,1911,1919,2128,2207,2401,2429,2511,2618,2806,2826,2906,3013,3416,3500,3700,3820,3906,3925,4015,4203,4217,4400,4616,4712,4802,4821,4909,5008,5027,5220,5225,5402,5414,5502,5524,5706,5716,5725,5805,5815,10028,10202,10322,10406,10505,10528,10711,10721,10801,11208,11212,11310,11607,11709,11911,12110,12122,12206,12220,12315,12514,12521,12716,12725,12805,13022,13219,13228,13319,13417,13603,13817,13928,14006,14016,14204,14328,14408,14616,14723,14803,14822,15010,15026,15107,15213,15401,15415,15525,15618,15702,15806,15823,15922,20103,20211,20229,20310,20408,20421,20622,20711,20819,20927,21105,21214,21302,21312,21408,21423,21506,21616,21710,21714,21823,21903,21921,22015,22129,22206,22217,22226,22304,22516,22618,22811,22825,22925,23017,23129,23209,23219,23628,23703,23728,24025,24128,24418,24514,24812,24827,24910,24924,25028,25126,25300,30906,30914,30918,30922,31012,31020,31026,31100,31120,31124,31129,31202,31227,31307,31313,31608,31622,31724,31822,31912,32010,32028,32119,32207,32325,32409,32503,32520,32806,32904,32924,33013,33103,33201,33219,33424,33500,33524,33604,33909,34001,34113,34121,34201,34210,34221,34305,34502,34606,34808,34910,35000,35103,35309,35506,35610,35811,35905,35913,40017,40107,40224,40328,40427,40623,40627,40703,40810,40829,40929,41008,41018,41022,41028,41106,41116,41400,41519,41522,41601,41619,41621,41701,41715,41725,42200,42210};
		songNote[REDO][World.YELLOW] = new int[] {515,521,820,1016,1026,1114,1411,1421,1629,1714,1804,1812,1822,1902,2107,2117,2412,2422,2708,2718,3005,3103,3129,3213,3617,3710,3800,3915,4103,4113,4123,4418,4508,4618,4700,4810,4929,5107,5117,5126,5318,5323,5422,5514,5829,5918,10100,10111,10426,10520,10811,10917,11320,11500,11900,12324,12408,12423,12507,12610,12706,12914,13112,13121,13210,13329,13613,13906,13916,14114,14310,14319,14527,14601,14624,14910,14920,15205,15303,15726,15902,20004,20111,20219,20319,20328,20606,20822,20904,20929,21012,21108,21120,21215,21229,21321,21508,21522,21809,21813,21913,22006,22320,22407,22518,22600,22915,23009,23228,23406,23808,23906,24322,24525,24608,24623,24709,24722,24803,25010,25212,25220,30403,30413,30417,30421,30510,30519,30524,30529,30619,30629,30703,30708,30728,30806,30812,30816,31321,31407,31515,31716,31812,31902,31920,32109,32127,32227,32307,32315,32610,32628,32809,33003,33023,33121,33300,33309,33317,33510,33514,33900,33923,34309,34325,34506,34518,34813,34903,34923,35117,35126,35206,35215,35223,35312,35323,35508,35520,35815,35928,40122,40210,40222,40300,40603,40613,40711,40721,40821,41125,41200,41206,41214,41224,41312,41320,41411,41504,41805,41819,41820,41902,41908,41916,41922,42002,42021,42117,42222,42321,42329,42405,42411};
		
	}
	
	public void getSong(int name) {
		World.speed = songSpeed[name];
		world.song = songSound[name];
		genSong(name,1);
		//world.note = genSong(name);
		world.bgImg = songImg[name];
		world.name = songName[name];
	}
	
	public void genSong(int name,int b) {
		int allCount = 0;
		int delay = 8;
		float changeFactor = 2.0f;//10.0f/9;
		//int[][] nowSong = new int [World.NBOFCOLOR][];
		world.note = new int [World.NBOFCOLOR][];
		for(int i=0;i<World.NBOFCOLOR;i++) {
			int len = songNote[name][i].length;
			world.note[i] = new int [len];
			int add = 0;
			for(int j=0;j<len;j++) {
				if(j==0 ||(j!=0 && songNote[name][i][j]-songNote[name][i][j-1]>1)) {
					int noteData = songNote[name][i][j];
					int ms = noteData % 100;
					noteData /= 100;
					int s = noteData %100;
					noteData /=100;
					int m = noteData;
					int trueMiliSec = ms + s*30 + m*30*60;
					if(j!=0 && songNote[name][i][j] <= songNote[name][i][j-1]) {
						System.out.println("error wrongNote: "+i+" "+songNote[name][i][j]);
					} else {
						world.note[i][add] = (int) (trueMiliSec*changeFactor-GuitarHeroGame.HEIGHT/World.speed+delay);
						add++;
						allCount++;
					}
				} else {
					System.out.println("Error shortDistance: "+i+" "+songNote[name][i][j-1]+" "+songNote[name][i][j]);
				}
			}
		}
		world.maxScore = (allCount*100);//+(allCount*(allCount+1)*5);
		//System.out.println(world.maxScore);
	}
}