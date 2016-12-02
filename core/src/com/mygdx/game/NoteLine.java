package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class NoteLine {
	float x;
	public static final int MAXNOTEPERLINE = 15;
	public Note [] notes = new Note [MAXNOTEPERLINE];
	public int [] songTime;
	public Timer timer;
	public int pushButtonKey;
	public Texture pushUnPressButtonImg;
	public Texture pushPressButtonImg;
	public float buttonYPosition = 10;
	public boolean isPress = false;
	public boolean oldIsPress = false;
	public Texture noteImg;
	public Texture noteComboImg;
	public int color;
	private boolean isCollected = false;
	private int count = 0;
	private int addPos = 0;
	private int delPos = 0;
	public boolean endNotes = false;
	public int passed = 0;
	private int countToEnd = 0;
	private int maxCountToEnd = 300;
	private HardWare hardWare;
	
	public NoteLine(int x,int [] songTime,Timer timer,int pushButtonKey,Texture [] texture,int color,HardWare hardWare) {
		this.x = x;
		this.songTime = songTime;
		//this.songTime = new int [] {100,120,130,140,170};
		this.timer = timer;
		this.pushButtonKey = pushButtonKey;
		this.pushUnPressButtonImg = texture[World.UNPRESS];
		this.pushPressButtonImg = texture[World.PRESS];
		this.noteImg = texture[World.NOTE];
		this.noteComboImg = texture[World.NOTECOMBO];
		this.color = color;
		this.hardWare = hardWare;
	}
	
	public void update() {
		runNotes();
		if(itsTimeToReserve(timer.getTime())) {
			addNote();		
		}
		buttonPress();
		isEnd();
	}
	
	public void isEnd() {
		//System.out.println(passed);
		//System.out.println("len:" + songTime.length);
		
		if(passed == songTime.length) {
			countToEnd++;
			if(countToEnd == maxCountToEnd) {
				endNotes = true;
			}
		}
	}
	
	public void buttonPress() {
		if(isButtonPress() && !oldIsPress) {
			float distance = removeNearestnote();
			World.score.updateScore(distance);
		}
		oldIsPress = isPress;
	}

	public boolean updateSwitch() {
		return hardWare.isSwitchPress[color];
	}
	
	private boolean isButtonPress() {
		if(isPressing() && !isCollected && !oldIsPress) {
			isPress = true;
			
			isCollected = true;
			return true;
		}
		if(!isPressing() && !oldIsPress) {
			isCollected = false;
		}
		return false;
	}

	private boolean isPressing() {
		return Gdx.input.isKeyPressed(pushButtonKey) || updateSwitch();
	}
	
	private float removeNearestnote() {
		float distance = Float.MAX_VALUE;
		if(notes[delPos] != null) {
			distance = notes[delPos].position.y - buttonYPosition;
			if(distance <= 200){
				notes[delPos].removeThis = true;
				removeNote();
				isCollected = true;
			}
		}
		return distance;
	}
	
	private boolean itsTimeToReserve(int time) {
		if(count < songTime.length) {
			//if(color == World.GREEN) {
			//	System.out.println("green"+" "+time+" "+songTime[count]);
			//}
			if(time == songTime[count]) {
				count++;
				return true;
			}
		}
		return false;
	}
	
	private void runNotes() {
		for(int i=0;i<notes.length;i++) {
			if(notes[i] != null) {
				notes[i].update();
				removeNote();
			}
		}
	}
	
	private void removeNote() {
		if(notes[delPos].removeThis) {
			notes[delPos] = null;
			delPos++;
			if(delPos >= notes.length) {
				delPos = 0;
			}
			passed++;
		}
		
	}
	
	private void addNote() {
		//System.out.println(x);
		notes[addPos] = new Note(x,noteImg,noteComboImg,color);
		addPos++;
		if(addPos >= notes.length) {
			addPos = 0;
		}
	}
}