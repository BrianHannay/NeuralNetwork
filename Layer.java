package com.brian;

import java.util.ArrayList;
import java.util.Random;
public class Layer{

	ArrayList<Neuron> neurons = new ArrayList<>();

	public Layer(int neurons, ArrayList<Neuron> previousLayer){

    	//add neurons
    	Random r = new Random();
    	for(int i = 0; i < neurons; i++){
    		this.neurons.add(new Neuron(previousLayer, r.nextLong()));
    	}
	}

	public void process(){
		for (Neuron n : neurons) {
			n.process();
		}
	}

	public ArrayList<Neuron> getNeurons(){
		return neurons;
	}
}