package com.tutorial.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class HUD {

	public int bounds = 0;
	public static int HEALTH = 100;
	
	private int greenValue = 255;
	private int fontSize = 18;
	
	private int score = 0;
	private int level = 1;
	public static boolean scoreIncrement = true;
	
	public void tick() {
		HEALTH = (int) Game.clamp(HEALTH, 0, 100 + (bounds));
		
		greenValue = 255*HEALTH/100;
		greenValue = (int) Game.clamp(greenValue, 0, 255);
		
		if(scoreIncrement) {
			score++;
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.gray);
		g.fillRect(15, 15, 200 + bounds, 32);
		g.setColor(new Color(150, greenValue, 32));
		g.fillRect(15, 15, HEALTH*2, 32);
		g.setColor(Color.white);
		g.drawRect(15, 15, 200 + bounds, 32);
		g.setFont(new Font(Font.MONOSPACED, Font.TYPE1_FONT, fontSize));
		g.drawString("Score: " + score, 15, 64);
		g.drawString("Level: " + level, 15, 80);
		g.drawString("Space -> Shop", 15, 96);
		
		g.setColor(Color.DARK_GRAY);
		g.drawString(Integer.toString(HEALTH) + "%", 100, 37);

	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public int getLevel() {
		return level;
	}
}
