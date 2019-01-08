package com.tutorial.main;

import java.awt.Graphics;
import java.awt.Rectangle;

/*
 * Abstract that all GameObjects should have
 */
public abstract class GameObject {

	protected float x, y; //only accessed by inheritance 
	protected ID id;
	protected float velX, velY; //velocity
	
	public GameObject(float x, float y, ID id) {
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
	public float getX() {
		return x;
	}
	public float getY() {
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
	public float getVelX() {
		return velX;
	}
	public float getVelY() {
		return velY;
	}
}
