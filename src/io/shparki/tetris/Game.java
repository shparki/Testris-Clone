package io.shparki.tetris;

import io.shparki.tetris.gfx.Window;
import io.shparki.tetris.go.CircularPath;
import io.shparki.tetris.go.LinearPath;
import io.shparki.tetris.go.Path;
import io.shparki.tetris.go.Projectile;
import io.shparki.tetris.io.Input;
import io.shparki.tetris.util.Point2D;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Game {
	
	private LinearPath lPath;
	private CircularPath cPath;
	private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	
	
	public Game(){
		lPath = new LinearPath(new Point2D(Window.getWidth() / 2, Window.getHeight() / 2), new Point2D(0, 0));
		cPath = new CircularPath(new Point2D(Window.getWidth() / 2, Window.getHeight() / 2), new Point2D(0, 0));
	}
	
	public void update(){
		if (Input.getMousePosition() != null){
			lPath.setEnd(Input.getMousePosition());
			cPath.setEnd(Input.getMousePosition());
		}
		lPath.update();
		cPath.update();
		
		if (Input.isMousePressed(MouseEvent.BUTTON1)) { 
			projectiles.add(new Projectile(lPath.getClone(), 5));
			projectiles.add(new Projectile(cPath.getClone(), cPath.getEnd(), 5));
		}
		if (Input.isMouseDown(MouseEvent.BUTTON3)) { 
			projectiles.add(new Projectile(lPath.getClone(), 5));
		}
		
		ArrayList<Projectile> tempProj = new ArrayList<Projectile>(projectiles);
		for (Projectile p : projectiles){
			p.update();
			if (!p.isAlive()) tempProj.remove(p);
		}
		projectiles = new ArrayList<Projectile>(tempProj);
		
		if (Input.isKeyPressed(KeyEvent.VK_C)){ projectiles.clear(); }
	}
	
	public void render(Graphics2D g2d){
		lPath.render(g2d);
		cPath.render(g2d);
		
		for (Projectile p : projectiles) p.render(g2d);
		
		if (projectiles.size() > 0)System.out.println(projectiles.size());
	}
}
