package io.shparki.tetris.util;

public class Point2D {
	
	private double x, y;
	
	public Point2D (double x, double y){
		this.x = x;
		this.y = y;
	}
	public Point2D() { this(0, 0); }
	
	public double getX() { return x; }
	public void setX(double x) { this.x = x; }
	public void incX(double i) { x += i; }
	
	public double getY() { return y; }
	public void setY(double y) { this.y = y; }
	public void incY(double i) { y += i; }
	
	public void add(Vector2D v){
		x += v.getX();
		y += v.getY();
	}
	public void subtract(Vector2D v){
		x -= v.getX();
		y -= v.getY();
	}
	
	
	
	
	public Point2D getClone() { return new Point2D(x, y); }
	public String toString() { return "(" + x + ", " + y + ")"; }
}
