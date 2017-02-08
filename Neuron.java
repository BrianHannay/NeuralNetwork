package com.brian;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

class Neuron{
	ArrayList<Neuron> inputs;
	ArrayList<Data> dataQueue = new ArrayList<>(); 
	ArrayList<Double> weights;
	Double bias;
	ArrayList<Data> dataHistory = new ArrayList<>();
	ArrayList<Data> outputHistory = new ArrayList<>();

	public Neuron(ArrayList<Neuron> inputs, long seed){
		init(inputs, seed);
	}

	//input neuron -- the first ones created will have no "inputs" because they are actually more like nerves which provide input
	public Neuron(){
		init(new ArrayList<Neuron>(), (new Random()).nextLong());
	}

	public void init(ArrayList<Neuron> inputs, long seed){
		this.inputs = inputs;
		Random r = new Random(seed);
		for(; weights.size()<inputs.size(); weights.add(r.nextDouble()));
		bias = r.nextDouble();

	}

	public void receiveData(Data datapacket){
		this.dataQueue.add(datapacket);
	}

	public ArrayList<Data> getOutput(){
		return new ArrayList<Data>(outputHistory);
	}

	public void sendData(Neuron to){
		for (Data packet : this.getOutput()) {		
			to.receiveData(packet);
		}
	}

	public ArrayList<Data> process() {
		for (Neuron input: inputs) {
			//get the input's data
			input.sendData(this);
		}

		Data processingData = dataQueue.remove(0);
		Double sum = new Double(bias);
		for (int i = 0; i < inputs.size(); i++) {
			ArrayList<Data> outputs = inputs.get(i).getOutput();
			for(int j=0; j < outputs.size(); j++){
			    sum += processingData.getValue() * weights.get(i);

			}
		}

		//do cool mathy stuff
		outputHistory.add(new Data(sigmoid(sum)));
		if(dataQueue.size() > 0){
			return process();
		} else{
			return outputHistory;
		}
	}

	private double sigmoid(double input){
		return 1 / (1 + Math.exp(-input));
	} 
}