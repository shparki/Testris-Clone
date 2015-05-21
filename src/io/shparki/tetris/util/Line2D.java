package io.shparki.tetris.util;

import java.awt.Graphics2D;

public class Line2D {
	
	private Point2D start, end;
	
	public Line2D(Point2D start, Point2D end){
		this.start = start.getClone();
		this.end = end.getClone();
	}
	
	public Point2D getStart() { return start.getClone(); }
	public void setStart(Point2D start) { this.start = start.getClone(); }
	
	public Point2D getEnd() { return end.getClone(); }
	public void setEnd(Point2D end) { this.end = end.getClone(); }
	
	public void render(Graphics2D g2d){
		g2d.drawLine((int)start.getX(), (int)start.getY(), (int)end.getX(), (int)end.getY());
	}
}
