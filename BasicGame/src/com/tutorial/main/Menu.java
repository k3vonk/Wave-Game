package com.tutorial.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import org.lwjgl.openal.AL;

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
				Game.gameState = STATE.Select;
				AudioPlayer.getSound("menu_sound").play();
				return;
			}
			
			//Help button
			if(mouseOver(mx, my, 210, 250, 200, 64)) {
				Game.gameState = STATE.Help;
				AudioPlayer.getSound("menu_sound").play();
			}
			

			
			//Quit button
			if(mouseOver(mx, my, 210, 350, 200, 64)) {
				AudioPlayer.getSound("menu_sound").play();
				AL.destroy();
				
				System.exit(1);
				
			}
		}

		//Back button for help
		if(Game.gameState == STATE.Help) {
			if(mouseOver(mx, my, 210, 350, 200, 64)) {
				AudioPlayer.getSound("menu_sound").play();
				Game.gameState = STATE.Menu;
				return;
			}
		}
		
		if(Game.gameState == STATE.End) {
			if(mouseOver(mx, my, 215, 350, 200, 64)) {
				Game.gameState = STATE.Game;
				hud.setLevel(1);
				hud.setScore(0);
				Game.gameState = STATE.Select;
				AudioPlayer.getSound("menu_sound").play();
				return;
			}
		}
		
		if(Game.gameState == STATE.Select) {
			//Normal button
			if(mouseOver(mx, my, 210, 150, 200, 64)) {
				Game.gameState = STATE.Game;
				handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player,handler));
				handler.clearEnemys();
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
				
				Game.diff = 0;
				AudioPlayer.getSound("menu_sound").play();
			}
			
			//Hard button
			if(mouseOver(mx, my, 210, 250, 200, 64)) {
				Game.gameState = STATE.Game;
				handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player,handler));
				handler.clearEnemys();
				handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
				
				Game.diff = 1;
				AudioPlayer.getSound("menu_sound").play();
			}
			

			
			//Back button
			if(mouseOver(mx, my, 210, 350, 200, 64)) {
				AudioPlayer.getSound("menu_sound").play();
				Game.gameState = STATE.Menu;
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
		
		if(Game.gameState == STATE.Menu) { //Menu Screen

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
		}else if(Game.gameState == STATE.Help) { //Help Screen	
			g.setColor(Color.white);
			g.setFont(fnt);
			g.drawString("Help", 250, 70);
			
			g.setFont(fnt2);
			g.drawString("Use WASD Keys for movement", 95, 140);
			
			g.setFont(fnt2);
			g.drawString("Use P Key to pause", 95, 200);
			
			g.setFont(fnt2);
			g.drawString("Use ESC Key to quit", 95, 260);
			
			g.setFont(fnt2);
			g.drawRect(210, 350, 200, 64);
			g.drawString("Back", 273, 390);
		}else if(Game.gameState == STATE.End) {	 //Retry Screen
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
		}else if(Game.gameState == STATE.Select) { //Menu Screen

			g.setColor(Color.black);
			g.setFont(new Font("aerial", 2, 53));
			g.drawString("SELECT DIFFICULTY", 65, 70);
			g.setColor(Color.white);
			g.setFont(fnt);
			g.drawString("SELECT DIFFICULTY", 65, 70);

			g.setColor(Color.black);
			g.fillRect(210, 150, 200, 64);
			g.setColor(Color.white);
			g.setFont(fnt2);
			g.drawRect(210, 150, 200, 64);
			g.drawString("Normal", 260, 193);
			
			g.setColor(Color.black);
			g.fillRect(210, 250, 200, 64);
			g.setColor(Color.white);
			g.drawRect(210, 250, 200, 64);
			g.drawString("Hard", 275, 293);
			
			g.setColor(Color.black);
			g.fillRect(210, 350, 200, 64);
			g.setColor(Color.white);
			g.drawRect(210, 350, 200, 64);
			g.drawString("Back", 275, 393);
		}
	}
}
