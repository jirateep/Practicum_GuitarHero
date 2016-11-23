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
		runNotes();
		if(itsTimeToReserve(timer.getTime())) {
			reserveNotes();		
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
		float distance;
		float minDistance = Float.MAX_VALUE;
		int position = -1;
		for(int i=0;i<notes.length;i++) {
			if(notes[i] != null) {
				distance = notes[i].position.y - buttonYPosition;
				//System.out.print("distance: " + distance);
				if(distance <= 200 && distance < minDistance) {
					minDistance = distance;
					position = i;
				}
			}
		}
		
		if(position != -1) {
			notes[position].removeThis = true;
			removeNote(position);
			isCollected = true;
		}
		//System.out.println(minDistance);
		
		return minDistance;
	}
	
	private boolean itsTimeToReserve(int time) {
		for(int i=0;i<songTime.length;i++) {
			if(time == songTime[i]) {
				return true;
			}
		}
		return false;
	}
	
	private void runNotes() {
		for(int i=0;i<notes.length;i++) {
			if(notes[i] != null) {
				notes[i].update();
				removeNote(i);
			}
		}
	}
	
	private void removeNote(int position) {
		if(notes[position].removeThis) {
			notes[position] = null;
		}
	}
	
	private void reserveNotes() {
		int pos = findExist();
		if(pos >= 0) {
			addNote(pos);
		}
	}
	
	private int findExist() {
		for(int i=0;i<notes.length;i++) {
			if(notes[i] == null)
				return i;
		}
		return -1;
	}
	
	private void addNote(int pos) {
		//System.out.println(x);
		notes[pos] = new Note(x,noteImg,noteComboImg,color);
	}
}