package io.shparki.tetris.go;

import io.shparki.tetris.gfx.Window;
import io.shparki.tetris.util.Point2D;
import io.shparki.tetris.util.Vector2D;

import java.awt.Graphics2D;

public class Projectile {
	
	private Path path;
	private double speed;
	private Point2D position;
	private Vector2D velocity;
	private int time;
	private boolean alive = false;
	private boolean shouldRender = false;
	
	public Projectile(Path path, Point2D start, double speed){
		this.path = path;
		this.speed = speed;
		position = start.getClone();
		time = 0;
		alive = true;
		
		velocity = path.getNormalAtTime(time).getClone();
		velocity.multiply(speed);
	}
	public Projectile(Path path, double speed){
		this (path, path.getStart(), speed);
	}
	
	public Point2D getPosition() { return position; }
	public double getSpeed() { return speed; }
	public Vector2D getVelocity() { return velocity; }
	public boolean isAlive() { return alive; }
	
	public void update(){
		time ++;
		if (path.getFinalTime() > 0 && time > path.getFinalTime()){
			alive = false;
		}
		velocity = path.getNormalAtTime(time);
		velocity.multiply(speed);
		position.add(velocity);
		
		shouldRender = false;
		if (position.getX() < Window.getWidth() && position.getX() >= 0){
			if (position.getY() < Window.getHeight() && position.getY() >= 0){
				shouldRender = true;
			}
		}
	}
	public void render(Graphics2D g2d){
		if (shouldRender){
			g2d.setColor(path.getColor());
			
			velocity = path.getNormalAtTime(time);
			velocity.multiply(speed);
			
			velocity.multiply(2);
			int endX = (int)(position.getX() + velocity.getX());
			int endY = (int)(position.getY() + velocity.getY());
			velocity.divide(2);
			
			g2d.drawLine((int)position.getX() - 3, (int)position.getY() - 3, endX, endY);
			g2d.drawOval((int)position.getX() - 3, (int)position.getY() - 3, 6, 6);
		}
	}
}
