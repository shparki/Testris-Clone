package io.shparki.tetris.util;

public class Vector2D {
	
	double x, y;
	
	public Vector2D(double x, double y){
		this.x = x;
		this.y = y;
	}
	public Vector2D(Point2D start, Point2D end){
		x = (end.getX() - start.getX());
		y = (end.getY() - start.getY());
	}
	public Vector2D(Vector2D direction, double magnitude){
		direction.multiply(magnitude);
		x = direction.getX();
		y = direction.getY();
	}
	
	public double getX() { return x; }
	public void setX(double x) { this.x = x; }
	public void incX(double i) { x += i; }
	
	public double getY() { return y; }
	public void setY(double y) { this.y = y; }
	public void incY(double i) { y += i; }
	
	
	public void add(double i){
		x += i;
		y += i;
	}
	public void add(Vector2D v){
		x += v.getX();
		y += v.getY();
	}
	public void subtract(double i){
		x -= i;
		y -= i;
	}
	public void subtract(Vector2D v){
		x -= v.getX();
		y -= v.getY();
	}
	public void multiply(double i){
		x *= i;
		y *= i;
	}
	public void multiply(Vector2D v){
		x *= v.getX();
		y *= v.getY();
	}
	public void divide(double i){
		x /= i;
		y /= i;
	}
	public void divide(Vector2D v){
		x /= v.getX();
		y /= v.getY();
	}
	
	public double getLength(){ return Math.sqrt(x * x + y * y); }
	public Vector2D getDirection(){ 
		Vector2D normal = this.getClone();
		normal.divide(normal.getLength());
		return normal;
	}
	
	public Vector2D getClone() { return new Vector2D(x, y); }
	public String toString() { return "<" + x + ", " + y + ">"; }
	
}