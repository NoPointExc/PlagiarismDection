package com.tripadvisor.PlagiarismDction.main;


import java.util.*;
import java.io.*;

import com.tripadvisor.PlagiarismDction.util.FileParser;

public class Main{
	private static String CONFIG_PTH = "D:\\OneDrive\\GeekProjects\\PlagiarismDector\\src\\com\\tripadvisor\\PlagiarismDction\\config\\";
	private static String SYNONYMS_FILE_PTH =CONFIG_PTH+ "\\synonyms";
	private static String FILE_1_PTH = CONFIG_PTH+"file1";
	private static String FILE_2_PTH = CONFIG_PTH+"file2";
	
	public static void main(String[] args){
		//PlagiarismDetector dector = new PlagiarismDetector(synonymsList);		
		
		List<List<String>> synonymsList = FileParser.parseSynonyms(SYNONYMS_FILE_PTH);
		List<String> wordsList1 = FileParser.parseFile(FILE_1_PTH);
		List<String> wordsList2 = FileParser.parseFile(FILE_2_PTH);
		System.out.println("wordsList1="+wordsList1);
		System.out.println("wordsList2="+wordsList2);
		HashTrieNode<Integer> node = new HashTrieNode<Integer>();
		
		PlagiarismDetector dector = new PlagiarismDetector(synonymsList);
		int rst = dector.countMatch(wordsList1, wordsList2, 3);
		System.out.println("rst="+rst);
	}
	

}