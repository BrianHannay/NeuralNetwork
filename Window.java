package com.brian;
/**
* This file starts the game -- program entry point
*
**/
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.IOException;
public class Window extends JFrame implements ActionListener
{
	static boolean isFullscreen = false;

	static Window window;

	static InputHandler ih;
	static Timer frameTimer;
	static Component currentView;



	public void init(){
		window = this;
		
		//Creating a menu adds it to the container it is passed
		//switchView(new MainMenu(this));
	}

	public static void main(String[] args){	

	}


	public static boolean threadLocked = false;
	public void actionPerformed(ActionEvent e){
		if(threadLocked){
			System.out.println("Ignoring repaint - already repainting.");
			return;
		}
		threadLocked = true;
		repaint();
		revalidate();
		threadLocked = false;
	}


	public void switchView(Component newOne){
		if(currentView != null){
			remove(currentView);
		}

		add(newOne);
		currentView = newOne;
	}

	public static void toggleFullscreen(){
		if(Window.isFullscreen){
		}
		else{
			window.setExtendedState(JFrame.MAXIMIZED_BOTH);
		}

		window.setUndecorated(Window.isFullscreen);
		Window.isFullscreen = !Window.isFullscreen;
	}
}