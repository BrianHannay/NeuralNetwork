package com.brian;

/**
*ENFORCED
*MAIN 
IMPORTANT!  

TODO: add input neurons for 
    date, time,
    web forums (reddit)
    

**/


//TODO crawl the web for code and learn from it (sandboxed)

// TODO GENETIC ALGORITHMS - WIKIPEDIA -< GITHUB  (maybe google -> SO / SE)

import java.util.Scanner;
import java.util.regex.Pattern;
import java.io.*;

import javax.swing.*;

public class Main {
	File userfile = null;

    public static void main(String[] args) {
        Brain b = new Brain(getUserName(), 50, 4, 20, 1);
        /*
        TODO
        Window w = new Window();

        w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        w.setSize(600, 400);
        w.setResizable(false);
        w.setVisible(true);

        Window.ih = new InputHandler(w);

        w.init();

        //todo: if you need to speed up the game, remove these two lines.
        Window.frameTimer = new Timer(15, w);
        Window.frameTimer.start();
*/
        while (true){
            Scanner s = new Scanner(System.in);
            String input = s.nextLine();
            System.out.println(b.getOutput(input));
        }
    }

    public static String getUserName(){
    	System.out.println("Who should I summon?");
    	Scanner s = new Scanner(System.in);
    	s.close();
    	return s.nextLine();
    }
}