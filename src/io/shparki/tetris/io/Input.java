package io.shparki.tetris.io;

import io.shparki.tetris.util.Point2D;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class Input implements KeyListener, MouseListener, MouseMotionListener{

	private static ArrayList<Integer> keysDown = new ArrayList<Integer>();
	private static ArrayList<Integer> keysPressed = new ArrayList<Integer>();
	private static ArrayList<Integer> keysReleased = new ArrayList<Integer>();
	
	private static ArrayList<Integer> mouseDown = new ArrayList<Integer>();
	private static ArrayList<Integer> mousePressed = new ArrayList<Integer>();
	private static ArrayList<Integer> mouseReleased = new ArrayList<Integer>();
	
	private static Point2D mousePosition = new Point2D(0, 0);
	private static Point2D mousePressedPosition = null;
	private static Point2D mouseReleasedPosition = null;
	
	
	public static boolean isKeyDown(int keyCode) { return keysDown.contains(Integer.valueOf(keyCode)); }
	public static boolean isKeyPressed(int keyCode) { return keysPressed.contains(Integer.valueOf(keyCode)); }
	public static boolean isKeyReleased(int keyCode){ return keysReleased.contains(Integer.valueOf(keyCode)); }
	
	public static boolean isMouseDown(int button) { return mouseDown.contains(Integer.valueOf(button)); }
	public static boolean isMousePressed(int button) { return mousePressed.contains(Integer.valueOf(button)); }
	public static boolean isMouseReleased(int button) { return mouseReleased.contains(Integer.valueOf(button)); }
	
	public static Point2D getMousePosition() { return mousePosition.getClone(); }
	public static Point2D getMousePressedPosition() { return mousePressedPosition.getClone(); }
	public static Point2D getMouseReleasedPosition() { return mouseReleasedPosition.getClone(); }
	
	public static void update(){
		keysPressed.clear();
		keysReleased.clear();
		
		mousePressed.clear();
		mouseReleased.clear();
		
		mousePressedPosition = null;
		mouseReleasedPosition = null;
	}
	
	
	
	public void keyPressed(KeyEvent e){
		if (!isKeyDown(e.getKeyCode())){
			if (!isKeyPressed(e.getKeyCode())){
				keysPressed.add(Integer.valueOf(e.getKeyCode()));
			}
			keysDown.add(Integer.valueOf(e.getKeyCode()));
		}
	}
	public void keyReleased(KeyEvent e){
		if (isKeyDown(e.getKeyCode())){
			if (!isKeyReleased(e.getKeyCode())){
				keysReleased.add(Integer.valueOf(e.getKeyCode()));
			}
			keysDown.remove(Integer.valueOf(e.getKeyCode()));
		}
	}
	
	public void mousePressed(MouseEvent e) { 
		if (!isMouseDown(e.getButton())){
			if (!isMousePressed(e.getButton())){
				mousePressed.add(Integer.valueOf(e.getButton()));
				mousePressedPosition = new Point2D(e.getX(), e.getY());
			}
			mouseDown.add(Integer.valueOf(e.getButton()));
		}
	}
	public void mouseReleased(MouseEvent e) { 
		if (isMouseDown(e.getButton())){
			if (!isMouseReleased(e.getButton())){
				mouseReleased.add(Integer.valueOf(e.getButton()));
				mouseReleasedPosition = new Point2D(e.getX(), e.getY());
			}
			mouseDown.remove(Integer.valueOf(e.getButton()));
		}
	}
	
	public void mouseMoved(MouseEvent e) { mousePosition = new Point2D(e.getX(), e.getY()); }
	public void mouseDragged(MouseEvent e) { mousePosition = new Point2D(e.getX(), e.getY()); }

	
	
	public void keyTyped(KeyEvent e) {  }
	public void mouseClicked(MouseEvent e) {  }
	public void mouseEntered(MouseEvent e) {  }
	public void mouseExited(MouseEvent e) {  }
	
}
