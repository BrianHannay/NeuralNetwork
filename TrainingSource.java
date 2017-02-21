package com.brian;

import java.util.HashMap;
import java.net.URL;
import java.lang.RuntimeException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.ArrayList;

class TrainingSource {


	public URL currentDownloadURL;
	public HashMap<URL, ValidSourceParser> validSources = new HashMap<>();
	public static URL RANDOM_REDDIT = new URL("https://reddit.com/r/random.json");
	
	private ValidSourceParser parser;

	public TrainingSource(URL url) {
		setDownloadURL(url);
		initialize();
	}

	public void setDownloadURL(URL url){
		this.currentDownloadURL = url;

		if(validSources.containsKey(url)){
			this.parser = validSources.get(url);
		} else {
			throw new IllegalArgumentException("url must be one of the urls specified in TrainingSource.java");
		}
	}


	public void initialize(){

		//error handling
		if(validSources.size() == 0){
			throw new RuntimeException("Initialize function should not be called twice");
		}

		//initialize sources
		validSources.put(RANDOM_REDDIT, 
			new ValidSourceParser(){
				public ArrayList<String> parse(String fullPageData){
					Pattern inputMatcher = Pattern.compile("^");
				}
			}
		);

	}
	
	public HashMap.Entry<ArrayList<Data>, ArrayList<Data>> getInputOutputPair(){
		if(!parser.hasCachedPage()){
			//do date checking for download limits
			parser.download();
		}

		parser.parse();
	}


	public abstract class ValidSourceParser{

		public ValidSourceParser(URL url){
			this.url = url;
		}

		public URL url;
		public String page = null;

		public void parse();

		public abstract HashMap<ArrayList<String>, ArrayList<String>> parse(String fullPageData);

		public boolean hasCachedPage(){
			return page != null;
		}

		public download(){

		}
	}
}