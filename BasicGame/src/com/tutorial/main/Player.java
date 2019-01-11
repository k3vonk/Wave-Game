package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Player extends GameObject{

	Random r = new Random();
	Handler handler;
	
	private BufferedImage player_image;
	
	public Player(int x, int y, ID id, Handler handler) {
		super(x ,y ,id);
		this.handler = handler;
		
		SpriteSheet ss = new SpriteSheet(Game.sprite_sheet);

		player_image = ss.grabImage(1, 1, 32, 32);
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int) x ,(int) y, 32, 32);
	}
	
	public void tick() {
		y += velY;
		x += velX;
			
		x = Game.clamp(x, 0, Game.WIDTH - 38);
		y = Game.clamp(y, 0, Game.HEIGHT - 67);
		
		collision();
		
	}
	
	private void collision() {
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if((tempObject.getid() == ID.BasicEnemy || 
					tempObject.getid() == ID.FastEnemy ||
					tempObject.getid() == ID.SmartEnemy ||
					tempObject.getid() == ID.EnemyBoss)
				&& getBounds().intersects(tempObject.getBounds())) {
					HUD.HEALTH -= 2;
				}
		}
	}
	
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.draw(getBounds());
		
		g.drawImage(player_image, (int) x, (int)y, null);
		
	}


}
