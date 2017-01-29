package com.brian;

//ENFORCED
//


//
//MAIN
// TODO SCAN GITHUB/WIKIPEDIA/GOOGLE/ETC FRAGMENTS
// TODO GENETIC ALGORITHMS - WIKIPEDIA -< GITHUB  (maybe google -> SO / SE)


import java.util.Scanner;
import java.util.regex.Pattern;

public class Brain {
	final String BRAIN_FOLDER = "network";
	File userfile = null;

    public Brain(String filename, int neuron, int inputs, int outputs) {
    	File userFileIn = new FileInputStream(BRAIN_FOLDER + "/" +filename);	
    }

    public String getOutput(String input){

    }

    public static void main(String[] args) {
        Brain b = Brain.createDefault();
        while (true){
            Scanner s = new Scanner(System.in);
            String pattern = b.getPattern();
            String input = s.findInLine(Pattern.compile());
            listenForPrompts();
            System.out.println(b.getOutput(input));

        }
    }

    public static Brain createDefault(){    	
        //new brain with 10 neurons, 20 inputs, 4 outputs
        int neuronsRequired = 10;
		int inputs = 20;
		int outputs = 4;// (0 - 16); only 0-9 used
    	Brain b = new Brain(neuronsRequired, inputs, outputs);

        // Our character "images". Imagine `1`s as black pixels.
        ArrayList<ArrayList<boolean>> numbers = new ArrayList<ArrayList<boolean>>();
        numbers.add(new ArrayList<boolean>());
         = [[
        	0, 1, 1, 0,
            1, 0, 0, 1,
            1, 0, 0, 1,
            1, 0, 0, 1,
            0, 1, 1, 0
        ],
        [
		    0, 0, 1, 0,
		    0, 0, 1, 0,
		    0, 0, 1, 0,
		    0, 0, 1, 0,
	        0, 0, 1, 0
        ],
        [
	        0, 1, 1, 0,
	        1, 0, 0, 1,
	        0, 0, 1, 0,
	        0, 1, 0, 0,
	        1, 1, 1, 1
        ],
        [
	        1, 1, 1, 1,
	        0, 0, 0, 1,
	        0, 1, 1, 1,
	        0, 0, 0, 1,
	        1, 1, 1, 1
        ],
        [
        	1, 0, 1, 0,
            1, 0, 1, 0,
            1, 1, 1, 1,
            0, 0, 1, 0,
            0, 0, 1, 0
        ],
        [
        	0, 1, 1, 1,
            0, 1, 0, 0,
            0, 1, 1, 0,
            0, 0, 0, 1,
            1, 1, 1, 0
        ],
        [
        	0, 1, 1, 1,
            1, 0, 0, 0,
            1, 1, 1, 0,
            1, 0, 0, 1,
            1, 1, 1, 1
        ],
        [
	        1, 1, 1, 1,
	        0, 0, 0, 1,
	        0, 0, 1, 0,
	        0, 0, 1, 0,
	        0, 1, 0, 0
        ],
        [
        	0, 1, 1, 0,
            1, 0, 0, 1,
            0, 1, 1, 0,
            1, 0, 0, 1,
            0, 1, 1, 0
        ],
        [
	        0, 1, 1, 1,
            1, 0, 0, 1,
            0, 1, 1, 1,
            0, 0, 0, 1,
            0, 0, 0, 1
        ];

        int[][] expected = new int[9][4];

        for(int i=0; i<9; i++){
            expected[i] = new int[4];
            for(int shift = 0; shift < 4; shift++){
                expected[i][shift] = (i>>shift)%2 == 1? 1 : 0;
            }
        };

        b.train(int[][] expected, int[][] numbers){

        }
        return b;
    }

}