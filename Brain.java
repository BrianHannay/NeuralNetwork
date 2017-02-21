package com.brian;



import java.io.*;
import java.util.*;
public class Brain {


	final String DEFAULT_BRAIN_FOLDER = "network_default";
	final String DEFAULT_BRAIN_DATA = "default";
	final String BRAIN_FOLDER = "network_save";

    ArrayList<String> dictionary = new ArrayList<>();
	FileOutputStream saveFile = null;
    ArrayList<Neuron> inputNeurons = new ArrayList<>();		
	ArrayList<Neuron> neurons = new ArrayList<>();
	ArrayList<Data> referenceData = new ArrayList<>();
	ArrayList<Neuron> outputNeurons = new ArrayList<>();
	ArrayList<Layer> layers = new ArrayList<>(); 
    ArrayList<TrainingSource> trainingSources = new ArrayList();


	public Brain(String filename, int neurons, int layers, int inputs, int outputs, ArrayList<TrainingSource> trainingSources) throws FileNotFoundException{
        this.trainingSources = trainingSources;

        saveFile = new FileOutputStream(BRAIN_FOLDER + "/" + filename);
    	if(filename == DEFAULT_BRAIN_DATA){
    		String name = Main.getUserName();
    		File dir = new File(BRAIN_FOLDER + "/" + name);
    		dir.mkdir();
            saveFile = new FileOutputStream(BRAIN_FOLDER + "/" + filename);
    		readDictionary(new FileInputStream(DEFAULT_BRAIN_FOLDER + "/" + dictionary));
        }

        //get training data in input -> output format
        HashMap<ArrayList<Data>, ArrayList<Data>> trainingData = createTrainingData();
        train(0.00001, 500000L, 0.3, trainingData);

    	//add layers of neurons
    	for (int i=0; i<layers; i++) {
    		this.layers.add(new Layer(neurons, (i == 0? inputNeurons : this.layers.get(i-1).getNeurons())));
    	}

    	//attach inputs
    	for(int i = 0; i<neurons; i++){
    		inputNeurons.add(new Neuron());
    	}

    	//attach outputs
    	for (int i=0; i<outputs; i++){
    		this.outputNeurons.add(new Neuron());
    	}
    }

    public HashMap<ArrayList<Data>, ArrayList<Data>> createTrainingData(){
        HashMap<ArrayList<Data>, ArrayList<Data>> data = new HashMap<>();
        for (TrainingSource source : trainingSources) {
            HashMap.Entry<ArrayList<Data>, ArrayList<Data>> pair = source.getInputOutputPair();
            data.put(pair.getKey(), pair.getValue());
        }
        return data;
    }

    public String getOutput(String input){
		String[] split = input.split("\\s+");
		int i=0;
		for(String inputWord: split){
			if(dictionary.contains(inputWord)){
				Data inputData = new Data(dictionary.indexOf(inputWord));

				//send data to input neuron
				inputNeurons.get(i%inputNeurons.size()).receiveData(inputData);
			}
			i++;
		}
		process();

        String output = "";
        for (Data data: getOutput()) {
            Double location = data.getValue();
            if(location > Integer.MAX_VALUE){
                continue;
            }
            output += " " + dictionary.get(location.intValue());
        }

        return output;
    }

    /**
    * Train the neural network to respond to x
    * 
    * @param errorThresholdStop Training ends when mean squared error of all output neurons reach this threshold  = 0.00001
    * @param trainingIterations Number of iterations on each training = 500000
    * @param learningRate Rate at which the network learns in each iteration = 0.3
    * @return error How much error this training session produced.
    **/
    public void train(Double errorThreshold, Long trainingIterations, Double learningRate, HashMap<ArrayList<Data>, ArrayList<Data>> examples){
        for(; trainingIterations>0; trainingIterations--){

            ArrayList<Double> errors = new ArrayList<>();
            for(Map.Entry<ArrayList<Data>,ArrayList<Data>> example: examples.entrySet()){

                ArrayList<Data> key = example.getKey();
                ArrayList<Data> value = example.getValue();
                if(key.size() != inputNeurons.size() || value.size() != outputNeurons.size()){
                    throw new IllegalArgumentException();
                }

                //send input
                int i=0;
                for(Data input: key){
                    inputNeurons.get(i%inputNeurons.size()).receiveData(input);
                    i++;
                }
                
                process();


                for(int neuronIndex=0; neuronIndex<outputNeurons.size(); neuronIndex++){
                    Double error = 0d;
                    ArrayList<Data> neuronOutputData = outputNeurons.get(neuronIndex).getOutput();
                    for(Data output: neuronOutputData){
                        error += Math.abs(output.getValue() - value.get(neuronIndex).getValue());
                        error += Math.abs(neuronOutputData.size() - value.size());
                    }
                    
                    // Keep track of the error of each examples to determine when to stop training.
                    outputNeurons.get(neuronIndex).informError(key, error);
                }
            }

        }
    }


    public ArrayList<Data> getOutput(){
        ArrayList<Data> output = new ArrayList<>();
        for (Neuron neuron: outputNeurons) {
            for(Data data: neuron.getOutput()){
                output.add(data);
            }
        }
        return output;

        
    }

    public void process(){
        for(Neuron n: inputNeurons){
            n.process();
        }
        for(Layer l: layers){
            l.process();
        }
        for(Neuron n: outputNeurons){
            n.process();
        }
    }

    public void loadFile(String filename) throws FileNotFoundException{
		FileInputStream userFileIn = new FileInputStream(filename);
    	readDictionary(userFileIn);
    	try{
            userFileIn.close();
        } catch(IOException err){
            System.err.println("Whoops! Did someone close the userfile already?");
        }
    }

    public void readDictionary(FileInputStream inputFile){
        Scanner inputScanner = new Scanner(inputFile);
        while(inputScanner.hasNextLine()){
            this.dictionary.add(inputScanner.nextLine());
        }
    }

    public void saveUser(FileOutputStream userFileOut){
    	//todo
    }

}