package com.tutorial.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import com.tutorial.main.Game.STATE;

public class Menu extends MouseAdapter{

	private Game game;
	private Handler handler;
	private Random r = new Random();
	
	public Menu(Game game, Handler handler) {
		this.game = game;
		this.handler = handler;
	}
	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		if(game.gameState == STATE.Menu) {
			//Play button
			if(mouseOver(mx, my, 210, 150, 200, 64)) {
				game.gameState = STATE.Game;
				handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player,handler));
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
			}
			
			//Help button
			if(mouseOver(mx, my, 210, 250, 200, 64)) {
				game.gameState = STATE.Help;
			}
			

			
			//Quit button
			if(mouseOver(mx, my, 210, 350, 200, 64)) {
				System.exit(1);
			}
		}

		//Back button for help
		if(game.gameState == STATE.Help) {
			if(mouseOver(mx, my, 210, 350, 200, 64)) {
				game.gameState = STATE.Menu;
				return;
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
		
		if(game.gameState == STATE.Menu) {

			g.setColor(Color.white);
			g.setFont(fnt);
			g.drawString("Menu", 245, 70);

			g.setFont(fnt2);
			g.drawRect(210, 150, 200, 64);
			g.drawString("Play", 280, 190);
			
			g.drawRect(210, 250, 200, 64);
			g.drawString("Help", 280, 290);
			
			g.drawRect(210, 350, 200, 64);
			g.drawString("Quit", 280, 390);
		}else if(game.gameState == STATE.Help) {	
			g.setColor(Color.white);
			g.setFont(fnt);
			g.drawString("Help", 250, 70);
			
			g.setFont(fnt2);
			g.drawString("Use WASD Keys for movement", 95, 140);
			
			g.setFont(fnt2);
			g.drawRect(210, 350, 200, 64);
			g.drawString("Back", 273, 390);
		}
	}
}
