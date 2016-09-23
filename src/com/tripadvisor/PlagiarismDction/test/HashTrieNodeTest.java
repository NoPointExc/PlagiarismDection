package com.tripadvisor.PlagiarismDction.test;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.tripadvisor.PlagiarismDction.main.HashTrieNode;
import com.tripadvisor.PlagiarismDction.main.PlagiarismDetector;
import com.tripadvisor.PlagiarismDction.util.FileParser;

public class HashTrieNodeTest {
	private static String CONFIG_PTH = "D:\\OneDrive\\GeekProjects\\PlagiarismDector\\src\\com\\tripadvisor\\PlagiarismDction\\config\\";
	private static String SYNONYMS_FILE_PTH =CONFIG_PTH+ "\\synonyms";
	private static String FILE_1_PTH = CONFIG_PTH+"file1";
	private static String FILE_2_PTH = CONFIG_PTH+"file2";
	
	PlagiarismDetector detector = null;
	List<String> wordsList1;
	List<String> wordsList2;
	@Before
	public void setUp() throws Exception {
		List<List<String>> synonymsList = FileParser.parseSynonyms(SYNONYMS_FILE_PTH);
		wordsList1 = FileParser.parseFile(FILE_1_PTH);
		wordsList2 = FileParser.parseFile(FILE_2_PTH);
		System.out.println("wordsList1="+wordsList1);
		System.out.println("wordsList2="+wordsList2);
		detector = new PlagiarismDetector(synonymsList);
		
	}

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
	}
	
	

}
