package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class HomeMenu {
	public String [] songList;
	public int selection;
	private int maxSelection;
	private int initSelection;
	private World world;
	private int oldKey = 0;
	public int countCanPress = 0;
	public int maxCountCanPress = 20;
	public Sound previewSong;
	public static Texture redButton = new Texture("red.png");
	public static Texture blueButton = new Texture("blue.png");
	public static Texture greenButton = new Texture("green.png");
	public static Texture yellowButton = new Texture("yellow.png");
	public static Texture logo = new Texture("logo.png");
	public boolean isCreditsOn = false;
	
	
	public HomeMenu(World world) {
		this.world = world;
		songList = world.songList.songName;
		initSelection = 0;
		maxSelection = songList.length - 1;
		selection = initSelection;
		previewSong = world.songList.getSongSound(selection);
		previewSong.play();
	}
	
	public void update() {
		if(countCanPress > maxCountCanPress) {
			selecting();
			selectSong();
			credits();
		}
		countCanPress++;
	}
	
	public void credits() {
		if(isCreditsKey()) {
			isCreditsOn = true;
		} else {
			isCreditsOn = false;
		}
	}
	
	public void selecting() {
		if(isBackKey()) {
			if(oldKey != -1) {
				selection--;
				if(selection<0)
					selection = maxSelection;
				previewSong.stop();
				previewSong = world.songList.getSongSound(selection);
				previewSong.play();
			}
			oldKey = -1;
		} else if(isNextKey()) {
			if(oldKey != 1) {
				selection++;
				if(selection>maxSelection)
					selection = initSelection;
				previewSong.stop();
				previewSong = world.songList.getSongSound(selection);
				previewSong.play();
			}
			oldKey = 1;
		} else {
			oldKey = 0;
		}
	}
	
	public void selectSong() {
		if(isEnterKey()) {
			world.songList.getSong(selection);
			for(int i=0;i<World.NBOFCOLOR;i++) {
				world.lines[i] = new NoteLine(world.xPosition[i],world.note[i],world.timer,world.keys[i],world.eachLineImg[i],i,world.hardWare);
			}
			selection = 0;
			countCanPress = 0;
			world.startingSong = false;
			world.timer.timer = 0;
			world.percentSong = 0;
			previewSong.stop();
			previewSong = world.songList.songSound[selection];
			world.song.play();
		}
	}
	
	public boolean updateSwitch(int color) {
		return world.hardWare.isSwitchPress[color];
	}
	
	public boolean isNextKey() {
		return Gdx.input.isKeyPressed(Keys.DOWN) || Gdx.input.isKeyPressed(Keys.RIGHT) ||
			   updateSwitch(World.YELLOW);
	}
	
	public boolean isBackKey() {
		return Gdx.input.isKeyPressed(Keys.UP) || Gdx.input.isKeyPressed(Keys.LEFT) ||
				updateSwitch(World.RED);
	}
	
	public boolean isEnterKey() {
		return Gdx.input.isKeyPressed(Keys.ENTER) || updateSwitch(World.BLUE);
	}
	
	public boolean isCreditsKey() {
		return updateSwitch(World.GREEN) || Gdx.input.isKeyPressed(Keys.C);
	}
}
