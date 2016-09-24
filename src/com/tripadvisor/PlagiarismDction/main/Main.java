package com.tripadvisor.PlagiarismDction.main;


import java.util.*;
import java.io.*;
import java.io.IOException;

import com.tripadvisor.PlagiarismDction.util.FileParser;

public class Main{
	
	private static final double ROUND_BITS = 10000.0;
	private static final String PERCENT_STR = "%"; 
	private static String HELP_INFO ="help \n\trun with $make to compile and run with default input \n\trun with $./run.sh [synonyms_file_name] [file1_name] [file1_name] [tuple_size] \n\tfor more, go to READEME.md\n\t";
	private static String CONFIG_RES_PTH = "/res/";
	private static String SYNONYMS_FILE_PTH;
	private static String FILE_1_PTH;
	private static String FILE_2_PTH;
	private static final int DEFAULT_TUPLE_SIZE = 3;


	public static void main(String[] args){
		
		//handle user input
		if(args.length<3){
			System.out.println(HELP_INFO);
			System.exit(0);
		}
		String currentPath = new File("").getAbsolutePath();
		
		SYNONYMS_FILE_PTH = currentPath+CONFIG_RES_PTH+args[0];
		FILE_1_PTH = currentPath+CONFIG_RES_PTH+args[1];
		FILE_2_PTH = currentPath+CONFIG_RES_PTH+args[2];
		
		List<String> file1Words = null;
		List<String> file2Words = null;
		List<List<String>> synonymsList = null;

		//load files
		try{
			file1Words = FileParser.parseFile(FILE_1_PTH);
			file2Words = FileParser.parseFile(FILE_2_PTH);
			synonymsList = FileParser.parseSynonyms(SYNONYMS_FILE_PTH);
		}catch(FileNotFoundException e){
			System.out.println("SYNONYMS_FILE_PTH = "+SYNONYMS_FILE_PTH);
			System.out.println("FILE_1_PTH = "+FILE_1_PTH);
			System.out.println("FILE_2_PTH = "+FILE_2_PTH);
			exit("file not found.");
		}catch(IOException e){
			System.out.println("SYNONYMS_FILE_PTH = "+SYNONYMS_FILE_PTH);
			System.out.println("FILE_1_PTH = "+FILE_1_PTH);
			System.out.println("FILE_2_PTH = "+FILE_2_PTH);
			exit("file to read from:");
		}

		System.out.println("files read sucess");
		
		int tupleSize = DEFAULT_TUPLE_SIZE;
		// get tuple size
		try{
			if(args.length>=4){									
				tupleSize = Integer.parseInt(args[3]);
				if(tupleSize<=0)
					throw new NumberFormatException();
			}
			System.out.println("tupleSize set as "+tupleSize);
		}catch(NumberFormatException e){
				exit("tupleSize expect to be a positive int, while your input is "+tupleSize);
		}
		
		//run
		execute(file1Words, file2Words, synonymsList,tupleSize);

	}

	private static void execute(List<String> file1Words, List<String> file2Words, List<List<String>> synonymsList ,int tupleSize){
		//build synonyms
		Synonyms<String> wordSynonyms= new WordsSynonyms();
		wordSynonyms.loadSynonyms(synonymsList);
		//build dector
		PlagiarismDetector dector = new PlagiarismDetector(wordSynonyms);

		double similarityRate = dector.getSimilarityRate(file1Words, file2Words,tupleSize);
		System.out.printf("%.2f%s of tuples in file1 found in file2 \n",similarityRate*100,PERCENT_STR);
	}

	private static void exit(String errMsg){
		System.out.println("**"+errMsg+"**");
		System.out.println("**exit**");
		System.exit(0);
	}
	

}