package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class SongList {
	
	public int [][][] songNote;
	public String [] songName;
	public Texture [] songImg;
	public Sound [] songSound;
	public int [] songSpeed;
	public static final int ROOMEARAI = 0;
	public static final int REDO = 1;
	public static int NBOFSONG = 2;
	public static final int MAXCOLOR = 4;
	private World world;
	
	private JSONObject data;
	private JSONArray listOfSong;
	public SongList(World world) {
		this.world = world;

		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(new FileReader("songlist.json"));
			data = (JSONObject) obj;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		listOfSong = (JSONArray) data.get("songlist");
		
		NBOFSONG = getNbofSong();
		songNote = new int [NBOFSONG][MAXCOLOR][];
		songName = getListSongName();
		songSound = getListSongSound();
	}
	
	private Sound [] getListSongSound() {
		Sound [] list = new Sound [NBOFSONG];
		int i = 0;
		for(String name : songName) {
			list[i] = Gdx.audio.newSound(Gdx.files.internal(name.toLowerCase()+".mp3"));
			i++;
		}
		return list;
	}
	
	private String [] getListSongName() {
		String [] list = new String [NBOFSONG];
		for(int i=0;i<NBOFSONG;i++) {
			JSONObject jsonObj = (JSONObject) listOfSong.get(i);
			list[i] = (String) jsonObj.get("name");
		}
		return list;
	}
	
	private int getNbofSong() {
		return listOfSong.size();
	}
	
	private int getSpeed(int name) {
		JSONObject jsonObj = (JSONObject) listOfSong.get(name);
		String speed = (String) jsonObj.get("speed");
		return Integer.valueOf(speed);
	}
	
	public String getSongName(int name) {
		return this.songName[name];
	}
	
	public Sound getSongSound(int name) {
		return songSound[name];
	}
	
	public Texture getSongBg(String name) {
		return new Texture(name.toLowerCase()+".jpg");
	}
	
	private int [][] copyNoteFromJSON(int name) {
		int [][] allNote = new int [World.NBOFCOLOR][];
		JSONObject jsonObj = (JSONObject) listOfSong.get(name);
		allNote[World.RED] = getNotes((String) jsonObj.get("redNote"));
		allNote[World.BLUE] = getNotes((String) jsonObj.get("blueNote"));
		allNote[World.GREEN] = getNotes((String) jsonObj.get("greenNote"));
		allNote[World.YELLOW] = getNotes((String) jsonObj.get("yellowNote"));
		return allNote;
	}
	
	private int [] getNotes (String allNoteString) {
		String [] stringArr = allNoteString.split(",");
		int [] intArr = new int [stringArr.length];
		for(int i=0;i<intArr.length;i++) {
			intArr[i] = Integer.parseInt(stringArr[i]);
		}
		return intArr;
	}
	public void getSong(int name) {
		String songName = getSongName(name);
		World.speed = getSpeed(name);
		world.song = getSongSound(name);
		genSong(name);
		world.bgImg = getSongBg(songName);
		world.name = songName;
	}
	
	public void genSong(int name) {
		int allCount = 0;
		int delay = 8;
		float changeFactor = 2.0f;//10.0f/9;
		int [][] nowNote = copyNoteFromJSON(name);
		world.note = new int [World.NBOFCOLOR][];
		for(int i=0;i<World.NBOFCOLOR;i++) {
			int len = nowNote[i].length;
			int [] notes = new int [len];
			int add = 0;
			for(int j=0;j<len;j++) {
				int now = nowNote[i][j];
				int last = 0;
				if(j > 0) {
					last = nowNote[i][j-1];
				}
				if(j==0 ||(j!=0 && now-last>2)) {
					if(j!=0 && now <= last) {
						System.out.println("error wrongNote: "+i+" "+now);
					} else {
						notes[add] = (int) (now*changeFactor-GuitarHeroGame.HEIGHT/World.speed+delay);
						add++;
						allCount++;
					}
				} else {
					System.out.println("Error shortDistance: "+i+" "+last+" "+now);
				}
			}
			world.note[i] = new int [add];
			for(int j=0;j<add;j++){
				world.note[i][j] = notes[j];
			}
		}
		world.score.maxScore = (allCount*100)+(allCount*(allCount+1)*5);
		world.score.allCombo = allCount;
	}
}