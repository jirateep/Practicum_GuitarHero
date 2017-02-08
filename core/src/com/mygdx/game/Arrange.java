package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

public class Arrange {
	public static float getRightXPosition(float objWidth) {
		return GuitarHeroGame.WIDTH - objWidth;
	}
	
	public static float getCenterXPosition(float objWidth) {
		return ( GuitarHeroGame.WIDTH - objWidth )/2;
	}
	
	public static float getTopYPosition(float objHeight) {
		return GuitarHeroGame.HEIGHT - objHeight;
	}
	
	public static float getCenterYPosition(float objHeight) {
		return ( GuitarHeroGame.HEIGHT - objHeight )/2;
	}
	
	public static float getFontWidth(BitmapFont font, String str) {
		GlyphLayout layout = new GlyphLayout(font, str);
		return layout.width;
	}
	
	public static float getFontHeight(BitmapFont font, String str) {
		GlyphLayout layout = new GlyphLayout(font, str);
		return layout.height;
	}
}
