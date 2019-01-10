package com.tutorial.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

/*
 * Ctrl + Shift + o : Auto-import packages
 * 1. P1 Create Back-End of Game [Generic part]
 * 2. Added Handler, ID, Player
 * 3. Key inputs
 * 4. Enemies, Health Bar
 * 5. Collision, Trail
 * 6. Levels, Spawner, FastEnemy
 * 7. Sticky Keys, AI
 * 8. Boss
 * 9. Menu
 * 10. Beautify Menu, Game endscreen
 */
public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = 7580815534084638412L;

	public static final int WIDTH = 640, HEIGHT = WIDTH/12 * 9;
	
	private Thread thread; //Entire game will run on this
	private boolean running = false;
	
	private Random r;
	private Handler handler;
	private HUD hud;
	private Spawn spawner;
	private Menu menu;
	
	public enum STATE {
		Menu,
		Help,
		Game,
		End,
	};
	
	public static STATE gameState = STATE.Menu;
	
	public Game() {
		handler = new Handler();
		hud = new HUD();
		menu = new Menu(this, handler, hud);
		this.addMouseListener(menu);
		this.addKeyListener(new KeyInput(handler));
		
		new Window(WIDTH, HEIGHT, "Let's Build a Game!", this);
		
		spawner = new Spawn(handler, hud);
		
		r = new Random();
		
		if(gameState == STATE.Game) {
			handler.addObject(new Player(WIDTH/2-32, HEIGHT/2-32, ID.Player,handler));
			handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
		}else {
			for(int i = 0; i < 10; i++) {
				handler.addObject(new MenuParticle(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.MenuParticle, handler));

			}
		}
	
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// Game loop that alot of people uses
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now -lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				tick();
				delta--;
			}
			
			if(running)
				render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				//System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}
	
	private void tick() {
		handler.tick();
		
		if(gameState == STATE.Game) {
			hud.tick();
			spawner.tick();
			
			if(HUD.HEALTH <= 0) {
				HUD.HEALTH = 100;
				gameState = STATE.End;
				handler.clearEnemys();
				for(int i = 0; i < 10; i++) {
					handler.addObject(new MenuParticle(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.MenuParticle, handler));

				}

			}
		}else if(gameState == STATE.Menu || gameState == STATE.End) {
			menu.tick();
		}
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3); //recommended 3
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		handler.render(g);
		
		if(gameState == STATE.Game) {
			hud.render(g);
		}else if(gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End) {
			menu.render(g);
		}
		
		g.dispose();
		bs.show();
	}
	
	public static float clamp(float var, float min, float max) {
		if(var >= max)
			return var = max;
		else if(var <= min)
			return var = min;
		else
			return var;
	}
	
	public static void main(String args[]) {
		new Game();
	}
	
	
}
