package com.brian;

import java.util.HashMap;
import java.net.URL;
import java.lang.RuntimeException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.ArrayList;

class TrainingSource {

	/**
	* validSources is an ArrarList of string url keys and associated Parsers.
	**/
	HashMap<URL, TrainingSourceParser> validSources = new ArrayList<>();
	TrainingSourceParser parser;

	//ignore sublime text's weird syntax highlighting on URL.
	public URL currentDownloadURL;
	public static URL RANDOM_REDDIT = new URL("https://reddit.com/r/random.json");

	public TrainingSource(URL url) {
		setDownloadURL(url);
		if(validSources.length() == 0){
			initialize();
		}
	}

	public void setDownloadURL(URL url){
		this.url = url;

		if(validSources.containsKey(url)){
			this.parser = validSources.get(url);
		} else {
			throw new IllegalArgumentException("url must be one of the urls specified in TrainingSource.java");
		}
	}


	public static void initialize(){

		//error handling
		if(TrainingSource.initialized){
			throw new RuntimeException("Initialize function should not be called twice");
		}

		//initialize sources
		validSources.add(
			new Pair<>(RANDOM_REDDIT, new TrainingSourceParser(){
				public ArrayList<String> parse(String fullPageData){
					Matcher inputMatcher = Pattern.compile("^").matcher();
				}
			})
		);

	}
	
	public HashMap.Entry<ArrayList<Data>, ArrayList<Data>> getInputOutputPair(){
		if(!parser.hasCachedPage()){
			//do date checking for download limits

		}

		parser.parse();
	}


	public abstract class TrainingSourceParser{


		public abstract ArrayList<String> parse(String fullPageData){

		}

	}
}