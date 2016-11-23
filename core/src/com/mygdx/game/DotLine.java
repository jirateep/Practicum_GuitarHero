package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class DotLine {
	public World world;
	public Vector2 [][] dotLine;
	public int [] pos;
	public static int NBOFDOT = 100;
	public static int NBOFLINE = 5;
	public Texture dotImg;
	
	public DotLine (Texture dotImg,World world) {
		dotLine = new Vector2[NBOFDOT][NBOFLINE];
		pos = new int[NBOFDOT];
		this.dotImg = dotImg;
		this.world = world;
	}
	
	public void update () {
		//if(world.timer.getTime() % 10 == 0) {
			reserveNewDot();
		//}
		runDot();
		deleteDot();
	}
	
	public void deleteDot() {
		for(int i=0;i<NBOFDOT;i++) {
			for(int j=0;j<NBOFLINE;j++) {
				if(dotLine[i][j]!=null) {
					if(dotLine[i][j].y < 0 - dotImg.getHeight()) {
						dotLine[i][j] = null;
					}
				}
			}
		}
	}
	
	public void runDot() {
		for(int i=0;i<NBOFDOT;i++) {
			for(int j=0;j<NBOFLINE;j++) {
				if(dotLine[i][j]!=null) {
					dotLine[i][j].y -= World.speed;
					dotLine[i][j].x = findXPosition(j,dotLine[i][j].y); 
				}
			}
		}
	}
	
	public void reserveNewDot () {
		int position = findExist();
		if(position >= 0) {
			for(int i=0;i<NBOFLINE;i++) {
				float yPosition = GuitarHeroGame.HEIGHT;
				float xPosition = findXPosition(i,yPosition);
				//System.out.println("x:"+xPosition+" y:"+yPosition);
				dotLine[position][i] = new Vector2 (xPosition,yPosition);
			}
		}
	}
	
	public float findXPosition(int line,float yPosition) {
		float factor = (GuitarHeroGame.HEIGHT-yPosition)/GuitarHeroGame.HEIGHT;
		switch(line) {
			case 0:
				return GuitarHeroGame.WIDTH/2 - 40 - 2 * world.eachLineImg[World.RED][World.NOTE].getWidth()*factor - dotImg.getWidth()/2;
			case 1:
				return GuitarHeroGame.WIDTH/2 - 20 - world.eachLineImg[World.RED][World.NOTE].getWidth()*factor - dotImg.getWidth()/2;
			case 2:
				return GuitarHeroGame.WIDTH/2 - dotImg.getWidth()/2;
			case 3:
				return GuitarHeroGame.WIDTH/2 + 20 + world.eachLineImg[World.RED][World.NOTE].getWidth()*factor - dotImg.getWidth()/2;
			case 4:
				return GuitarHeroGame.WIDTH/2 + 40 + 2 * world.eachLineImg[World.RED][World.NOTE].getWidth()*factor - dotImg.getWidth()/2;
			default:
				break;
		}
		return 0;
	}
	
	public int findExist() {
		for(int i=0;i<NBOFDOT;i++) {
			if(dotLine[i][0] == null) {
				return i;
			}
		}
		return -1;
	}
}
