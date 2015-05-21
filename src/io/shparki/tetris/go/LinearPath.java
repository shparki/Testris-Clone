package io.shparki.tetris.go;

import io.shparki.tetris.gfx.Window;
import io.shparki.tetris.util.Point2D;
import io.shparki.tetris.util.Vector2D;

import java.awt.Color;
import java.awt.Graphics2D;

public class LinearPath extends Path{
	public LinearPath(Point2D start, Point2D end){
		super(start, end);
		
		color = Color.MAGENTA;
	}
	
	public Point2D getPointAtTime(double time){
		Point2D point = null;
		
		if (time >= 0){
			double xAtTime = start.getX() + normalToEnd.getDirection().getX() * time;
			double yAtTime = start.getY() + normalToEnd.getDirection().getY() * time;
			point = new Point2D(xAtTime, yAtTime);
		}
		
		return point;
	}
	public double getFinalTime(){
		double finalXTime = 0, finalYTime = 0;
		double normalX = normalToEnd.getDirection().getX();
		double normalY = normalToEnd.getDirection().getY();
		
		if (normalX > 0){
			finalXTime = (Window.getWidth() - start.getX()) / normalX;
		} else {
			finalXTime = (0 - start.getX()) / normalX;
		}
		
		if (normalY > 0){
			finalYTime = (Window.getHeight() - start.getY()) / normalY;
		} else {
			finalYTime = (0 - start.getY()) / normalY;
		}
		
		
		if (finalXTime < 0) finalXTime *= -1;
		if (finalYTime < 0) finalYTime *= -1;
		
		if (finalXTime < finalYTime) return finalXTime;
		return finalYTime;
	}

	public void render(Graphics2D g2d){
		super.render(g2d);
		
	}
	
	public LinearPath getClone(){
		return new LinearPath(start.getClone(), end.getClone());
	}
	
}
