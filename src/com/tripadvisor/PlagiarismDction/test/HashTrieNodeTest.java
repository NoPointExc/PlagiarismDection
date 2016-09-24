package com.tripadvisor.PlagiarismDction.test;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import com.tripadvisor.PlagiarismDction.main.HashTrieNode;
import com.tripadvisor.PlagiarismDction.main.Synonyms;
import com.tripadvisor.PlagiarismDction.main.WordsSynonyms;


public class HashTrieNodeTest {

	@Test
	public void containsTest() {
		HashTrieNode<Integer> node =new HashTrieNode<Integer>();
		List<Integer> insertTuple0 = new LinkedList<>();
		for(int i=0;i<3;i++)
			insertTuple0.add(i);
		node.insert(insertTuple0);
		
		//contain
		assertTrue(node.contains(insertTuple0));
		insertTuple0.remove(0);
		assertFalse(node.contains(insertTuple0));
	
		List<Integer> insertTuple2 = new LinkedList<>();
		for(int i=10;i<15;i++)
			insertTuple2.add(i);
		node.insert(insertTuple2);
		
		//contain but not end
		List<Integer> insertTuple1 = new LinkedList<>();
		for(int i=10;i<13;i++)
			insertTuple1.add(i);
		assertFalse(node.contains(insertTuple1));
		
		//with end
		node.insert(insertTuple1);	
		assertTrue(node.contains(insertTuple1));
		
		//empty
		assertFalse( node.contains(new LinkedList<>()) );
		node.insert( new LinkedList<>() );	
		assertTrue( node.contains(new LinkedList<>()) );
		
		//synonyms
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
		
		List<List<String>> synonymsList = new LinkedList<List<String>> ();
		synonymsList.add(runWrods);
		Synonyms<String> wordSynonyms= new WordsSynonyms();
		wordSynonyms.loadSynonyms(synonymsList);
		HashTrieNode<String> node2 =new HashTrieNode<String>();
		node2.insert(goToRun);
		assertTrue(node2.contains(goToJog, wordSynonyms));
		
	}
	
	

}
