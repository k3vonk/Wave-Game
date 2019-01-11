package com.tutorial.main;

import java.awt.Graphics;
import java.util.LinkedList;

import com.tutorial.main.Game.STATE;

/*
 * Maintain & Update 'render'
 */
public class Handler {

	LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	public int speed = 5;
	
	public void tick() {
		for(int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			
			tempObject.tick();
		}
	}
	
	public void render(Graphics g) {
		for(int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			
			tempObject.render(g);
		}
		
	}
	
	public void addObject(GameObject object) {
		this.object.add(object);
	}
	
	public void removeObject(GameObject object) {
		this.object.remove(object);
	}
	
	public void clearEnemys() {
		for (int i = 0; i < this.object.size(); i++) {
			GameObject tempObject = this.object.get(i);
	        if (tempObject.getid() != ID.Player || Game.gameState == STATE.End || Game.gameState == STATE.Finish) {
	            this.removeObject(tempObject);
	            i--;
	        }
		}
	}
}
