package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class MenuParticle extends GameObject{

	private Handler handler;
	
	Random r = new Random();

	private Color col;
	int dir = 0;
	
	public MenuParticle(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
		dir = r.nextInt(2);
		
		velX = (r.nextInt(5 - -5) + -5);
		velY = (r.nextInt(5 - -5) + -5);
		if(velX == 0) velX = 1;
		if(velY == 0) velY = 1;
		col = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
	}
	
	public void tick() {
		x += velX;
		y += velY;
		
		if(y <= 0 || y >= Game.HEIGHT - 48) velY *= -1;
		if(x <= 0 || x >= Game.WIDTH - 20) velX *= -1;
		
		handler.addObject(new Trail(x, y, ID.Trail,16, 16, 0.02f, col, handler));
	}

	
	public void render(Graphics g) {
		g.setColor(col);
		g.fillRect((int)x, (int)y, 16, 16);
	}


	public Rectangle getBounds() {
		return new Rectangle((int)x, (int) y, 16, 16);
	}

}
