package com.tripadvisor.PlagiarismDction.test;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.tripadvisor.PlagiarismDction.main.PlagiarismDetector;
import com.tripadvisor.PlagiarismDction.main.Synonyms;
import com.tripadvisor.PlagiarismDction.main.WordsSynonyms;
import com.tripadvisor.PlagiarismDction.util.FileParser;

public class PlagiarismDetectorTest {
	List<List<String>> synonymsList;
	PlagiarismDetector dector;
	int tupleSize = 3;
	
	@Test
	public void testCountMatch() {
		int upto = 100;
		
		List<String> goToRun = new LinkedList<String> ();
		goToRun.add("go");
		goToRun.add("for");
		goToRun.add("a");
		goToRun.add("run");
		
		List<String> goToJog = new LinkedList<String> ();
		goToJog.add("go");
		goToJog.add("for");
		goToJog.add("a");
		goToJog.add("jog");
		
		List<String> wentToJog = new LinkedList<String> ();
		wentToJog.add("went");
		wentToJog.add("for");
		wentToJog.add("a");
		wentToJog.add("jog");
		
		List<String> runWrods = new LinkedList<String> ();
		runWrods.add("run");
		runWrods.add("sprint");
		runWrods.add("jog");
		
		synonymsList = new LinkedList<List<String>> ();
		synonymsList.add(runWrods);
		Synonyms<String> wordSynonyms= new WordsSynonyms();
		wordSynonyms.loadSynonyms(synonymsList);
		dector = new PlagiarismDetector(wordSynonyms);
		
		System.out.println( dector.countMatch(goToRun, goToJog,3) );
		assertEquals(dector.countMatch(goToRun, goToJog, 3),2);
		assertEquals(dector.countMatch(goToRun, wentToJog, 3),1);
		
		List<String> oddNum = getStringsofNumEvery(2,1,upto);
		List<String> evenNum = getStringsofNumEvery(2,0,upto);
		List<String> allNum = getStringsofNumEvery(1,0,upto);
		
		synonymsList = new LinkedList<List<String>> ();
		synonymsList.add(oddNum);
		WordsSynonyms wordSynonyms2= new WordsSynonyms();
		wordSynonyms2.loadSynonyms(synonymsList);
		dector.setSynonyms(wordSynonyms2);
		
		tupleSize = 3;
		System.out.println( dector.countMatch(allNum, allNum, tupleSize) );
		assertEquals(dector.countMatch(allNum, allNum, tupleSize),allNum.size() - tupleSize+1);
		assertEquals(dector.countMatch(oddNum, evenNum, tupleSize),0);
		
		System.out.println( dector.countMatch(oddNum, allNum, 1) );		
		assertEquals(dector.countMatch(oddNum, allNum, 1),50);
		
		System.out.println( dector.countMatch(oddNum, allNum, 1) );	
		assertEquals(dector.countMatch(evenNum, allNum, 1),50);
		
		System.out.println( dector.countMatch(oddNum, allNum, 2) );	
		assertEquals(dector.countMatch(evenNum, allNum, 2),0);
		
		
		synonymsList = new LinkedList<List<String>> ();
		synonymsList.add(evenNum);
		wordSynonyms2.clearSynonyms();
		wordSynonyms2.loadSynonymsWithoutClear(synonymsList);
		dector.setSynonyms(wordSynonyms2);
		
		List<String> fourNum = getStringsofNumEvery(4,0,upto);
		System.out.println( dector.countMatch(fourNum, evenNum, 2) );	
		assertEquals(dector.countMatch(fourNum, evenNum, 2),fourNum.size()-1);
		
	}
	
	private static List<String> getStringsofNumEvery(int base,int from,int upto){
		List<String> synonyms = new LinkedList<>();
		for(int i=from;i<upto;i+=base){
			synonyms.add(i+"");
			
		}
		return synonyms;
	}

}
