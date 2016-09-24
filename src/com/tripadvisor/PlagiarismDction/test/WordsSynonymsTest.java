package com.tripadvisor.PlagiarismDction.test;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.tripadvisor.PlagiarismDction.main.Synonyms;
import com.tripadvisor.PlagiarismDction.main.WordsSynonyms;

public class WordsSynonymsTest {
	
	@Test
	public void testIsSimilar() {
		int upto =100;
		List<String> oddNum = getStringsofNumEvery(2,1,upto);
		List<String> evenNum = getStringsofNumEvery(2,0,upto);
		List<String> threeNum = getStringsofNumEvery(3,0,upto);
		List<String> fourNum = getStringsofNumEvery(4,0,upto);
		List<String> allNum = getStringsofNumEvery(1,0,upto);
		
		List<List<String>> synonymsList = new LinkedList<>();
		synonymsList.add(evenNum);
		synonymsList.add(threeNum); //6 in both site
		
		WordsSynonyms wordsSynonyms = new WordsSynonyms();
		wordsSynonyms.loadSynonyms(synonymsList);
		assertTrue( wordsSynonyms.isSimilar("6", "9") ); //both three something
		assertTrue( wordsSynonyms.isSimilar("6", "88") );//both even
		assertFalse( wordsSynonyms.isSimilar("88", "9") ); //not similar
		
		List<List<String>> synonymsList2 = new LinkedList<>();
		synonymsList2.add(allNum);
		wordsSynonyms.loadSynonymsWithoutClear(synonymsList2);
		assertTrue( wordsSynonyms.isSimilar("88", "9") ); //similar now
	}
	
	private static List<String> getStringsofNumEvery(int base,int from,int upto){
		List<String> synonyms = new LinkedList<>();
		for(int i=from;i<upto;i+=base){
			synonyms.add(i+"");
			
		}
		return synonyms;
	}

}
