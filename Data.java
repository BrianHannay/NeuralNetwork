package com.brian;
import java.util.*;


class Data{
	double data;
	public Data(int wordID){
		data = (double) wordID;
	}

	public Data(double wordID){
		data = wordID;        
	}

	public double getValue(){
		return data;
	}
}