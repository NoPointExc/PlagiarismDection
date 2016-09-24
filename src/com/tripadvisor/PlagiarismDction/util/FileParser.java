package com.tripadvisor.PlagiarismDction.util;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class FileParser{

	//TODO: should throw IO Exception here
	private static void readFile(String filename, StringBuilder strBuilder) throws IOException{
	    File file = new File(filename); //for ex foo.txt
	    FileReader reader = null;
	    try{
	        reader = new FileReader(file);
	        char[] chars = new char[(int) file.length()];
	        reader.read(chars);
	        strBuilder.append(chars);
	        reader.close();
	   } finally {
	        if(reader !=null){
	        	reader.close();
	        }
	    }
	}
	
	public static List<String> parseFile(String filename) throws IOException{
		List<String> wordsList= new LinkedList<String>();
		StringBuilder strBuilder = new StringBuilder();
		readFile(filename,strBuilder);
		String[] wordsArr = strBuilder.toString().split(" ");
		
		for(String word:wordsArr){
			if(word.trim().length()!=0){
				wordsList.add(word.trim().toLowerCase());
			}
		}
		
		return wordsList;
	}
	
	public static List<List<String>> parseSynonyms(String filename) throws IOException{
		List<List<String>> synonymsList= new LinkedList<List<String>>();
		StringBuilder strBuilder = new StringBuilder();
		readFile(filename,strBuilder);	
		String[] lines = strBuilder.toString().split(System.getProperty("line.separator"));
		
		for(String line:lines){
			if(line.trim().length()!=0){
				String[] words = line.split(" ");
				List<String> wordsList = new LinkedList<String>();
				
				for(String word:words){
					wordsList.add(word.trim().toLowerCase());
				}
				
				synonymsList.add(wordsList);
			}
		}
		
		return synonymsList;
	}
	
}