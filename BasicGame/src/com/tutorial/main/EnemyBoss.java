package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class EnemyBoss extends GameObject{

	private Handler handler;
	private GameObject player;
	Random r = new Random();
	
	
	private int timer = 80;
	private int timer2 = 50;
	
	public EnemyBoss(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
		velX = 0;
		velY = 2;
		
		for(int i = 0; i < handler.object.size(); i++) {
			if(handler.object.get(i).getid() == ID.Player) player = handler.object.get(i);
		}
	}
	
	public void tick() {
		x += velX;
		y += velY;
		
		if(timer <= 0) velY = 0;
		else timer--;
		
		if(timer <= 0) timer2--;
		if(timer2 <= 0) {
			if(velX == 0) velX = 2;
			if(velX > 0) velX += 0.005f;
			if(velX < 0) velX -= 0.005f;
			
			velX = Game.clamp(velX, -10, 10);
			int spawn = r.nextInt(10);
			if(spawn == 0) handler.addObject(new EnemyBossBullet((int) x + 48, (int)y + 48, ID.BasicEnemy, handler));
		}
		
		if(x <= 0 || x >= Game.WIDTH - 100) velX *= -1;
		
		
		if(player.getY() < y) HUD.HEALTH--;
	}

	
	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect((int)x, (int)y, 96, 96);
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 96, 96);
	}

}
