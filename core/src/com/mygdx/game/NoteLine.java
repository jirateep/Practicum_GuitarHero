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
	
	
	public NoteLine(int x,int [] songTime,Timer timer,int pushButtonKey,Texture [] texture,int color) {
		this.x = x;
		this.songTime = songTime;
		this.timer = timer;
		this.pushButtonKey = pushButtonKey;
		this.pushUnPressButtonImg = texture[World.UNPRESS];
		this.pushPressButtonImg = texture[World.PRESS];
		this.noteImg = texture[World.NOTE];
		this.noteComboImg = texture[World.NOTECOMBO];
		this.color = color;
	}
	
	public void update() {
		//printNote();
		runNotes();
		if(itsTimeToReserve(timer.getTime())) {
			addNote();		
		}
		buttonPress();
	}
	
	public void buttonPress() {
		if(isButtonPress() && !oldIsPress) {
			float distance = removeNearestnote();
			World.score.updateScore(distance);
		}
		oldIsPress = isPress;
	}

	private boolean isButtonPress() {
		if(Gdx.input.isKeyPressed(pushButtonKey) && !isCollected) {
			isPress = true;
			return true;
		}
		if(!Gdx.input.isKeyPressed(pushButtonKey)) {
			isCollected = false;
		}
		
		return false;
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
	
	private void printNote() {
		System.out.print("[");
		for(int i=0;i<notes.length;i++) {
			if(notes[i]!=null) 
				System.out.print(i+",");
		}
		System.out.println("]");
	}
}