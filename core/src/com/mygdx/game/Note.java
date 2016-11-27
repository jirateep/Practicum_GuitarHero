package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Note {

	public Vector2 position;
	public Texture noteImg;
	public Texture noteComboImg;
	public Texture nowNoteImg;
	public boolean removeThis = false;
	public float initYPosition = GuitarHeroGame.HEIGHT;
	public float factor;
	public int color;
	
	public Note (float x,Texture noteImg, Texture noteComboImg, int color) {
		this.noteImg = noteImg;
		this.noteComboImg = noteComboImg;
		position = new Vector2(x,initYPosition);
		factor = (initYPosition-position.y)/initYPosition;
		this.color = color;
	}
	
	public void update() {
		updateNoteImg();
		updateFactor();
		playing();
		deleting();
	}
	
	private void updateNoteImg() {
		if(World.score.reachComboTime) {
			nowNoteImg = noteComboImg;
		} else {
			nowNoteImg = noteImg;
		}
	}
	
	private void updateFactor() {
		factor = (initYPosition-position.y)/initYPosition;
	}
	
	private void playing() {
		position.y -= World.speed;
		position.x = calXPosition(position.y);
	}
	
	private float calXPosition(float y) {
		float xPosition = 0;
		float halfWidth = GuitarHeroGame.WIDTH/2;
		float height = GuitarHeroGame.HEIGHT;
		float lastY = height -y;
		float lastX = World.halfDistance + nowNoteImg.getWidth()*factor/2;
		float halfNoteWidthWithFactor = nowNoteImg.getWidth()*factor/2;
		switch(color) {
			case World.RED:
				xPosition = halfWidth - 3*lastX*lastY/height - halfNoteWidthWithFactor;
				break;
			case World.BLUE:
				xPosition = halfWidth - lastX*lastY/height - halfNoteWidthWithFactor;
				break;
			case World.GREEN:
				xPosition = halfWidth + lastX*lastY/height - halfNoteWidthWithFactor;
				break;
			case World.YELLOW:
				xPosition = halfWidth + 3*lastX*lastY/height - halfNoteWidthWithFactor;
				break;
			default:
				break;
		}
		return xPosition;
	}
	
	private void deleting() {
		if(position.y + noteImg.getHeight() < 0) {
			World.score.nowComboString = World.score.comboStrings[Score.MISS];
			
			removeThis = true;
			World.score.updateCombo(-1);
		}
	}
}
