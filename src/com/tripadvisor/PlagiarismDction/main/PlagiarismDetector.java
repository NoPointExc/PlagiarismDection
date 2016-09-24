package com.tripadvisor.PlagiarismDction.main;


import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class PlagiarismDetector {
	private  Synonyms<String> synonyms;
	
	public PlagiarismDetector(Synonyms<String> Synonyms){
		this.synonyms = Synonyms;
	}
	
	public void setSynonyms(Synonyms<String> Synonyms) {
		this.synonyms = Synonyms;
	}
	
	//TODO: throws null expection when call this method
	public int countMatch(List<String> words1, List<String> words2, int size) throws IndexOutOfBoundsException{
		
		int count = 0;
		
		if(size<=0){
			throw new IndexOutOfBoundsException("size expect be a positive number");
		}
		
		if(words1.size()<size){
			throw new IndexOutOfBoundsException("word 1 size is "+words1.size()+" while tuple size="+size);
		}
		
		if(words2.size()<size){
			throw new IndexOutOfBoundsException("word 2 size is "+words2.size()+" while tuple size is "+size);
		}
		
		List<String> originWords = words1;
		List<String> againstWords = words2;
				
		TrieNode<String> trie = getTrie(againstWords,size);
		//System.out.println(trie.toString());
		
		LinkedList<String> tupleQueue = new LinkedList<String>();
		Iterator<String> originWordsIterator = originWords.iterator();
		
		for(int i=0; i<size-1 && originWordsIterator.hasNext();i++){
			tupleQueue.offer( originWordsIterator.next() );
		}
		
		//TODO: get level to jump unnecessay compare
		while( originWordsIterator.hasNext() ){			
			tupleQueue.offer( originWordsIterator.next() );
			System.out.println(tupleQueue);
			if( trie.contains(tupleQueue,synonyms) ){
				System.out.print("contain");
				count++;
			}else
				System.out.print("do not contain");
			tupleQueue.pop();
		}
		
		return count;
	}
	
	private TrieNode<String> getTrie(List<String> words, int size){
		//TODO: isRoot.
		TrieNode<String> root = new HashTrieNode<String>();
		TrieNode<String> triePointer = root;
		Iterator<String> wordIterator = words.iterator();
		
		LinkedList<String> tupleQueue = new LinkedList<String>();
		
		for(int i=0; i<size-1 && wordIterator.hasNext();i++){
			tupleQueue.offer( wordIterator.next() );
		}
		
		while( wordIterator.hasNext() ){			
			tupleQueue.offer( wordIterator.next() );	
			triePointer.insert(tupleQueue);
			tupleQueue.pop();
		}
		
		return root;
	}
}
