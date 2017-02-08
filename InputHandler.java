package com.brian;


import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.Point;
import java.awt.Container;


public class InputHandler implements KeyListener, MouseListener{


	private Point insetsOffset;

	public InputHandler(Container window){
		window.addMouseListener(this);
		window.addKeyListener(this);
		//this is a mouthfull...
		insetsOffset = new Point(window.getInsets().left, window.getInsets().top);

		System.out.println("Made InputHandler");

	}

	public static void reset(){
		clickables = new ArrayList<Clickable>();
		keys = new ArrayList<Key>();
		keyHandlers = new ArrayList<KeyHandler>();
	}


	/**
	** These are the MOUSE events
	**/

	private static ArrayList<Clickable> clickables = new ArrayList<Clickable>();

	public static void registerClickable(Clickable c){
		clickables.add(c);
	}

	public void mouseExited(MouseEvent e){
		
	}
	public void mouseEntered(MouseEvent e){
		
	}
	public void mouseReleased(MouseEvent e){
		
	}
	public void mousePressed(MouseEvent e){
		
	}
	public void mouseClicked(MouseEvent e){
		Point pointCorrectedForInsets = new Point(e.getX() - insetsOffset.x, e.getY() - insetsOffset.y);
		
		for (Clickable c : clickables) {  
			c.tryClick(pointCorrectedForInsets);
		}
	}




	/**
	** These are the KEY events
	**/


	private static ArrayList<KeyHandler> keyHandlers = new ArrayList<KeyHandler>();

	public static void registerKeyHandler(KeyHandler kh){
		keyHandlers.add(kh);
	}

	public static boolean isDown(int keyNum){
		for(int i=0; i<keys.size(); i++){
			if(keys.get(i).code == keyNum){
				return true;
			}
		}
		return false;
	}

	private static ArrayList<Key> keys = new ArrayList<Key>();

	public void keyReleased(KeyEvent ev){
		setKey(ev.getKeyCode(), false);
	}
	public void keyPressed(KeyEvent ev){
		setKey(ev.getKeyCode(), true);	
		for(KeyHandler kh : keyHandlers){
			kh.pressed(ev);
		}
	}

	private static void setKey(int code, boolean down){
		for (Key c : keys) {
			if(c.code == code){
				c.down = down;
				return;
			}
		}
		Key c = new Key(code, down);
		keys.add(c);
	}

	//noop, required to implement KeyListener
	public void keyTyped(KeyEvent ev){}


	


	private static class Key{
		public int code;
		public boolean down;
		public Key(int c, boolean d){
			code=c;
			down=d;
		}
	}

}