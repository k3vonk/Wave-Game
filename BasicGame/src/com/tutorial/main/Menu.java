package com.tutorial.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import com.tutorial.main.Game.STATE;

public class Menu extends MouseAdapter{

	private Handler handler;
	private HUD hud;
	private Random r = new Random();
	
	public Menu(Game game, Handler handler, HUD hud) {
		this.hud = hud;
		this.handler = handler;
	}
	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		if(Game.gameState == STATE.Menu) {
			//Play button
			if(mouseOver(mx, my, 210, 150, 200, 64)) {
				Game.gameState = STATE.Game;
				handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player,handler));
				handler.clearEnemys();
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
			}
			
			//Help button
			if(mouseOver(mx, my, 210, 250, 200, 64)) {
				Game.gameState = STATE.Help;
			}
			

			
			//Quit button
			if(mouseOver(mx, my, 210, 350, 200, 64)) {
				System.exit(1);
			}
		}

		//Back button for help
		if(Game.gameState == STATE.Help) {
			if(mouseOver(mx, my, 210, 350, 200, 64)) {
				Game.gameState = STATE.Menu;
				return;
			}
		}
		
		if(Game.gameState == STATE.End) {
			if(mouseOver(mx, my, 215, 350, 200, 64)) {
				Game.gameState = STATE.Game;
				hud.setLevel(1);
				hud.setScore(0);
				handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player,handler));
				handler.clearEnemys();
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
			}
		}
	}
	
	public void mouseReleased(MouseEvent e) {
		
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if(mx > x && mx < x + width) {
			if(my > y && my < y + height) {
				return true;
			}else return false;
		}else return false;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		Font fnt = new Font("aerial", 1, 50);
		Font fnt2 = new Font("aerial", 1, 30);
		
		if(Game.gameState == STATE.Menu) {

			g.setColor(Color.black);
			g.setFont(new Font("aerial", 2, 53));
			g.drawString("Wave", 245, 70);
			g.setColor(Color.white);
			g.setFont(fnt);
			g.drawString("Wave", 245, 70);

			g.setColor(Color.black);
			g.fillRect(210, 150, 200, 64);
			g.setColor(Color.white);
			g.setFont(fnt2);
			g.drawRect(210, 150, 200, 64);
			g.drawString("Play", 280, 190);
			
			g.setColor(Color.black);
			g.fillRect(210, 250, 200, 64);
			g.setColor(Color.white);
			g.drawRect(210, 250, 200, 64);
			g.drawString("Help", 280, 290);
			
			g.setColor(Color.black);
			g.fillRect(210, 350, 200, 64);
			g.setColor(Color.white);
			g.drawRect(210, 350, 200, 64);
			g.drawString("Quit", 280, 390);
		}else if(Game.gameState == STATE.Help) {	
			g.setColor(Color.white);
			g.setFont(fnt);
			g.drawString("Help", 250, 70);
			
			g.setFont(fnt2);
			g.drawString("Use WASD Keys for movement", 95, 140);
			
			g.setFont(fnt2);
			g.drawRect(210, 350, 200, 64);
			g.drawString("Back", 273, 390);
		}else if(Game.gameState == STATE.End) {	
			g.setColor(Color.RED);
			g.setFont(fnt);
			g.drawString("Game Over", 178, 70);
			
			g.setColor(Color.CYAN);
			g.setFont(fnt2);
			g.drawString("You lost with a score of: " + hud.getScore(), 100, 140);
			
			
			g.setColor(Color.black);
			g.fillRect(215, 350, 200, 64);
			g.setColor(Color.white);
			g.drawRect(215, 350, 200, 64);
			g.drawString("Try Again", 250, 390);
		}
	}
}
