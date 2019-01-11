package com.tutorial.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.tutorial.main.Game.STATE;

public class Shop extends MouseAdapter{

	Handler handler;
	HUD hud;
	
	private int B1 = 800;
	private int B2 = 800;
	private int B3 = 500;
	
	public Shop(Handler handler, HUD hud) {
		this.handler = handler;
		this.hud = hud;
	}
	
	
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.setFont(new Font("arial", 1, 50));
		g.drawString("Shop", 255, 70);
		
		//Box1
		g.setFont(new Font("arial", 1, 14));
		g.drawString("Upgrade Health", 80, 190);
		g.drawString("Cost: " + B1, 100, 140);
		g.drawRect(75, 150, 117, 80);
		
		//Box2
		g.drawString("Upgrade Speed", 270, 190);
		g.drawString("Cost: " + B2, 285, 140);
		g.drawRect(265, 150, 117, 80);
		
		//Box3
		g.drawString("Refill Health", 465, 190);
		g.drawString("Cost: " + B3, 470, 140);
		g.drawRect(450, 150, 115, 80);
		
		//Score
		hud.render(g);
	}
	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		if(Game.gameState == STATE.Shop) {
			//Box 1
			if(Menu.mouseOver(mx, my, 75, 150, 117, 80)) {
				AudioPlayer.getSound("menu_sound").play();
				if(hud.getScore() >= B1) {
					hud.setScore(hud.getScore() - B1);
					B1 += 1000;
					hud.bounds += 20;
					HUD.HEALTH = (100 + (hud.bounds/2));
					return;
				}
			}
			
			//Box 2
			if(Menu.mouseOver(mx, my, 265, 150, 117, 80)) {
				AudioPlayer.getSound("menu_sound").play();
				if(hud.getScore() >= B2) {
					hud.setScore(hud.getScore() - B2);
					B2 += 1000;
					handler.speed++;
					return;
				}
			}
			
			//Box 3
			if(Menu.mouseOver(mx, my, 450, 150, 115, 80)) {
				AudioPlayer.getSound("menu_sound").play();
				if(hud.getScore() >= B3) {
					hud.setScore(hud.getScore() - B3);
					HUD.HEALTH = (100 + (hud.bounds/2));
					hud.tick();
					return;
				}
			}
		}

	}
	
}
