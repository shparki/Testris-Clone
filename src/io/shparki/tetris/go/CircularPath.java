package io.shparki.tetris.go;

import io.shparki.tetris.util.Point2D;

import java.awt.Color;
import java.awt.Graphics2D;

public class CircularPath extends Path{
	
	
	private  double radius;
	
	public CircularPath(Point2D start, Point2D end) {
		super(start, end);
		radius = normalToEnd.getLength();
		
		color = Color.YELLOW;
	}
	
	public Point2D getPointAtTime(double time){
		double px = start.getX() + radius * Math.cos(Math.toRadians(time));
		double py = start.getY() + radius * Math.sin(Math.toRadians(time));
		return new Point2D(px, py);
	}
	public double getFinalTime() { return 2 * Math.PI * radius * PRECISSION; }
	public CircularPath getClone() { return new CircularPath(start.getClone(), end.getClone()); }
	
	public void update(){
		super.update();
		
		radius = normalToEnd.getLength();
	}
	public void render(Graphics2D g2d){
		super.render(g2d);
		
		g2d.drawLine((int)start.getX(), (int)start.getY(), (int)end.getX(), (int)end.getY());
		//g2d.drawOval((int)(start.getX() - radius), (int)(start.getY() - radius), (int)radius * 2, (int)radius * 2);
	}
}
