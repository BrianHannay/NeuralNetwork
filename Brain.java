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



	public Brain(String filename, int neurons, int layers, int inputs, int outputs) throws FileNotFoundException{
        saveFile = new FileOutputStream(BRAIN_FOLDER + "/" + filename);
    	if(filename == DEFAULT_BRAIN_DATA){
    		String name = Main.getUserName();
    		File dir = new File(BRAIN_FOLDER + "/" + name);
    		dir.mkdir();
            saveFile = new FileOutputStream(BRAIN_FOLDER + "/" + filename);
    		readDictionary(new FileInputStream(DEFAULT_BRAIN_FOLDER + "/" + dictionary));

        }

        //get training data in input -> output format
        HashMap<Data, Data> trainingData = createTrainingData();
        trainRequired(0.00001, 500000L, 0.3, trainingData);

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
		this.process();
        return this.getOutput();
    }

    /**
    * Train the neural network to respond to x
    * 
    * @param errorThresholdStop Training ends when mean squared error of all output neurons reach this threshold  = 0.00001
    * @param trainingIterations Number of iterations on each training = 500000
    * @param learningRate Rate at which the network learns in each iteration = 0.3
    **/
    public void trainRequired(Double errorThreshold, Long trainingIterations, Double learningRate, HashMap<Data, Data> examples){
        for(; trainingIterations>0; trainingIterations--){
            for(Map.Entry<Data, Data> example: examples.entrySet()){
                //todo
            }
        }
    }


    public String getOutput(){
        ArrayList<Data> outputArray = new ArrayList<>();
        String output = "";
        for (Neuron n:outputNeurons) {
            ArrayList<Data> neuronOutput = n.getOutput();
            for(Data data: neuronOutput){
                Double location = data.getValue();
                if(location > Integer.MAX_VALUE){
                    continue;
                }
                output += " " + dictionary.get(location.intValue());
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