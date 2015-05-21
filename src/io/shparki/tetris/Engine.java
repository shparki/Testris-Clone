package io.shparki.tetris;

import io.shparki.tetris.gfx.Window;
import io.shparki.tetris.io.Input;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

public class Engine extends Canvas implements Runnable{
	
	public static final int WIDTH = 1200;
	public static final int HEIGHT = WIDTH / 16 * 9;
	public static final String TITLE = "Tetris Clone";
	
	public static final int TARGET_UPS = 60;
	public static final long SECOND = 1_000_000_000L;
	public static final double PERIOD = SECOND / TARGET_UPS;
	private static int currentUPS = 0, currentFPS = 0;
	public static int UPS = 0, FPS = 0;
	public static double deltaTime;
	public static double deltaUpdateTime;
	
	private Thread animator;
	private boolean running;
	
	private Game game;
	private BufferStrategy bs;
	private Graphics2D g2d;
	
	
	
	
	public Engine(){
		Window.create(WIDTH, HEIGHT, TITLE + " | FPS:000 UPS:00", this);
		start();
	}
	
	public void start(){
		if (!running || animator == null){
			animator = new Thread(this, "Animator");
			animator.start();
		}
	}
	public void stop(){
		running = false;
	}
	
	
	public void run(){
		running = true;
		long currentTime = System.nanoTime(), beforeTime = currentTime;
		long currentUpdateTime = System.nanoTime(), beforeUpdateTime = currentUpdateTime;
		long upsCounter = 0, fpsCounter = 0;
		
		init();
		
		while (running){
			beforeTime = currentTime;
			currentTime = System.nanoTime();
			deltaTime = currentTime - beforeTime;
			
			upsCounter += deltaTime;
			if (upsCounter >= PERIOD){
				beforeUpdateTime = currentUpdateTime;
				currentUpdateTime = System.nanoTime();
				deltaUpdateTime = currentUpdateTime - beforeUpdateTime;
				
				upsCounter -= PERIOD;
				update();
				currentUPS ++;
			}
			
			render();
			currentFPS ++;
			
			fpsCounter += deltaTime;
			if (fpsCounter >= SECOND){
				fpsCounter -= SECOND;
				UPS = currentUPS; FPS = currentFPS;
				currentUPS = 0; currentFPS = 0;
				Window.setTitle(TITLE + " | FPS:" + FPS + " UPS:" + UPS );
			}
		}
		System.exit(0);
	}
	
	
	public void init(){
		game = new Game();
	}
	public void update(){		
		game.update();
		Input.update();
	}
	public void render(){
		if (bs == null){
			bs = getBufferStrategy();
			if (bs == null){
				createBufferStrategy(3);
				return;
			}
		}
		
		g2d = (Graphics2D) bs.getDrawGraphics();
		
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, Window.getWidth(), Window.getHeight());
		
		game.render(g2d);
		
		bs.show();
		g2d.dispose();
	}
}
