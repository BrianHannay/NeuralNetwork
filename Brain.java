package com.brian;
//ENFORCED
//

//EDITABE

//
//MAIN
// TODO SCAN GITHUB/WIKIPEDIA/GOOGLE/ETC FRAGMENTS
// TODO GENETIC ALGORITHMS

// TODO


import java.util.Scanner;
import java.util.regex.Pattern;

public class Brain {


    public Brain(int neuron, int , int i2) {

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

        // Our character "images". Imagine `1`s as black pixels.
        boolean[][] numbers = [[
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
        ],

        int[][] expected = new int[9][4];

        for(int i=0; i<9; i++){
            expected[i] = new int[4];
            for(int shift = 0; shift < 4; shift++){
                expected[i][shift] = (i>>shift)%2 == 1? 1 : 0;
            }
        };




        b.train(int[][] expected, int[][] numbers){
        	
        }
        //new brain with 10 neurons, 20 inputs, 4 outputs
        return new Brain(10, 20, 4);
    }

}
