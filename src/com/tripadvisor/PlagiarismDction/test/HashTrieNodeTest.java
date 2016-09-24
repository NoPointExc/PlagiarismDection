package com.tripadvisor.PlagiarismDction.test;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import com.tripadvisor.PlagiarismDction.main.HashTrieNode;


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
	}
	
	

}
