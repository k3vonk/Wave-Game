package com.tutorial.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import org.lwjgl.openal.AL;

import com.tutorial.main.Game.STATE;

public class KeyInput extends KeyAdapter{

	private Handler handler;
	private boolean[] keyDown = new boolean[4]; //0 = w, 1 = s, 2 = d, 3 = a
	
	public KeyInput(Handler handler) {
		this.handler = handler;
		
		for(int i = 0; i < keyDown.length; i++) {
			keyDown[i] = false;
		}
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();   //number value correspnding to key input
		
		for(int i  = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getid() == ID.Player) {
				
				if(key == KeyEvent.VK_W) { tempObject.setVelY(-handler.speed); keyDown[0] = true;};
				if(key == KeyEvent.VK_S) { tempObject.setVelY(handler.speed); keyDown[1] = true;};
				if(key == KeyEvent.VK_D) { tempObject.setVelX(handler.speed); keyDown[2] = true;};
				if(key == KeyEvent.VK_A) { tempObject.setVelX(-handler.speed); keyDown[3] = true;};
			}
		}
		
		if(key == KeyEvent.VK_P && Game.gameState == STATE.Game) { //P to pause game
			if(Game.paused) Game.paused = false;
			else Game.paused = true;
		}
		
		if(key == KeyEvent.VK_M) { //M to stop music
			Game.pauseMusic = !Game.pauseMusic;
			if(Game.pauseMusic) {
				AudioPlayer.getMusic("music").pause();
			}else {
				AudioPlayer.getMusic("music").loop();
			}
		}
		
		if(key == KeyEvent.VK_ESCAPE) { //Escape to exit game
			AL.destroy(); 
			System.exit(1);
		}
		
		if(key == KeyEvent.VK_SPACE) { //Space to go to shop
			if(Game.gameState == STATE.Game) {
				Game.gameState = STATE.Shop;
			}else if(Game.gameState == STATE.Shop) {
				Game.gameState = STATE.Game;
			}
		}
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		for(int i  = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getid() == ID.Player) {
				//key events
				
				if(key == KeyEvent.VK_W) keyDown[0] = false;//tempObject.setVelY(0);
				if(key == KeyEvent.VK_S) keyDown[1] = false;//tempObject.setVelY(0);
				if(key == KeyEvent.VK_D) keyDown[2] = false;//tempObject.setVelX(0);
				if(key == KeyEvent.VK_A) keyDown[3] = false;//tempObject.setVelX(0);
			
				//vertical movement
				if(!keyDown[0] && !keyDown[1]) tempObject.setVelY(0);
				
				//horizontal movement
				if(!keyDown[2] && !keyDown[3]) tempObject.setVelX(0);
				else if(keyDown[2] && !keyDown[3]) tempObject.setVelX(5);
				else if(!keyDown[2] && keyDown[3]) tempObject.setVelX(-5);
			}
			

		}
	}
}
