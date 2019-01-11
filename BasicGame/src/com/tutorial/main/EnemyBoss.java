package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class EnemyBoss extends GameObject{

	private Handler handler;
	private GameObject player;
	private HUD hud;
	Random r = new Random();
	
	
	private int timer = 80;
	private int timer2 = 50;
	private int timer3 = 1100 + r.nextInt(600);
	
	public EnemyBoss(int x, int y, ID id, Handler handler, HUD hud) {
		super(x, y, id);
		this.handler = handler;
		this.hud = hud;
		
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
			int spawn = r.nextInt(8);
			if(spawn == 0) handler.addObject(new EnemyBossBullet((int) x + 48, (int)y + 48, ID.BasicEnemy, handler));
		}
		
		if(x <= 0 || x >= Game.WIDTH - 100) velX *= -1;
		
		
		if(player.getY() < y) HUD.HEALTH--;
		
		if(timer2 <= 0) timer3--;
		if(timer3 == 0) {
			velX = 0;
			velY = -2;
			HUD.scoreIncrement = true;
			hud.setScore(hud.getScore() + 1000);
			hud.setLevel(11);
			handler.clearEnemys();
			for(int i = 0; i < 7; i++) {
				handler.addObject(new MenuParticle((int) x + 10, (int) y , ID.MenuParticle, handler));
				handler.addObject(new MenuParticle((int) x - 10, (int) y, ID.MenuParticle, handler));
			}
		}
		
		
	}

	
	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect((int)x, (int)y, 96, 96);
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 96, 96);
	}

}
