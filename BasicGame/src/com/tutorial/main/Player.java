package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Player extends GameObject{

	Random r = new Random();
	
	public Player(int x, int y, ID id) {
		super(x ,y ,id);
		
	}
	
	public void tick() {
		y += velY;
		x += velX;
			
		x = Game.clamp(x, 0, Game.WIDTH - 38);
		y = Game.clamp(y, 0, Game.HEIGHT - 67);
		
	}
	
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x, y, 32, 32);
	}
}
