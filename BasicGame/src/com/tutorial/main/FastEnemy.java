package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class FastEnemy extends GameObject{

	private Handler handler;
	private BufferedImage fast_image;
	public FastEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
		velX = 2;
		velY = 9;
		
		SpriteSheet ss = new SpriteSheet(Game.sprite_sheet);
		fast_image = ss.grabImage(1, 3, 16, 16);
	}
	
	public void tick() {
		x += velX;
		y += velY;
		
		if(y <= 0 || y >= Game.HEIGHT - 48) velY *= -1;
		if(x <= 0 || x >= Game.WIDTH - 20) velX *= -1;
		
		handler.addObject(new Trail(x, y, ID.Trail,16, 16, 0.02f, Color.CYAN, handler));
	}

	
	public void render(Graphics g) {
		//g.setColor(Color.CYAN);
		//g.fillRect((int)x, (int)y, 16, 16);
		g.drawImage(fast_image, (int) x, (int)y, null);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int) y, 16, 16);
	}

}
