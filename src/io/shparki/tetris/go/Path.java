package io.shparki.tetris.go;

import io.shparki.tetris.util.Point2D;
import io.shparki.tetris.util.Vector2D;

import java.awt.Color;
import java.awt.Graphics2D;

public abstract class Path {
	
	public static final double PRECISSION = 1;
	protected Color color = Color.WHITE;
	
	protected Point2D start, end;
	protected Vector2D normalToEnd;
	
	public Path(Point2D start, Point2D end){
		this.start = start.getClone();
		this.end = end.getClone();
		
		normalToEnd = new Vector2D(start, end);
	}
	public void update(){
		normalToEnd = new Vector2D(start, end);
	}
	public void render(Graphics2D g2d){
		g2d.setColor(color);
		
		for (int i = 0; i < getFinalTime(); i++){
			g2d.drawLine((int)getPointAtTime(i).getX(), (int)getPointAtTime(i).getY(), (int)getPointAtTime(i + PRECISSION).getX(), (int)getPointAtTime(i + PRECISSION).getY());
		}
		
		g2d.drawOval((int)start.getX() - 3, (int)start.getY() - 3, 6, 6);
		g2d.drawOval((int)end.getX() - 3, (int)end.getY() - 3, 6, 6);
	}
	
	public Point2D getStart() { return start.getClone(); }
	public void setStart(Point2D start) { this.start = start.getClone(); }
	
	public Point2D getEnd() { return end.getClone(); }
	public void setEnd(Point2D end) { this.end = end.getClone(); }
	
	public Vector2D getNormalToEnd() { return normalToEnd.getClone(); }
	public Color getColor() { return color; }
	
	public abstract Point2D getPointAtTime(double t);
	public abstract double getFinalTime();
	public abstract Path getClone();
	
	public Vector2D getNormalAtTime(double t){
		double vX = getPointAtTime(t + PRECISSION).getX() - getPointAtTime(t).getX();
		double vY = getPointAtTime(t + PRECISSION).getY() - getPointAtTime(t).getY();
		return new Vector2D(vX, vY);
	}

}
