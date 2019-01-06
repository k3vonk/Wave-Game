package com.tutorial.main;

import java.awt.Graphics;
import java.awt.Rectangle;

/*
 * Abstract that all GameObjects should have
 */
public abstract class GameObject {

	protected int x, y; //only accessed by inheritance 
	protected ID id;
	protected int velX, velY; //velocity
	
	public GameObject(int x, int y, ID id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	// Must implement
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds(); //when two rectangles intersect it will return true [collision]
	
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void setid(ID id) {
		this.id = id;
	}
	public ID getid() {
		return id;
	}
	public void setVelX(int velX) {
		this.velX = velX;
	}
	public void setVelY(int velY) {
		this.velY = velY;
	}
	public int getVelX() {
		return velX;
	}
	public int getVelY() {
		return velY;
	}
}
