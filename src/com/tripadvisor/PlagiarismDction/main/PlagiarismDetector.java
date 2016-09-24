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
	
	public double getSimilarityRate(List<String> originWords, List<String> againstWords, int size) throws IndexOutOfBoundsException{
		double rate = 0.0;
		int matchedWord = countMatch(originWords,againstWords,size);
		rate = (double)(matchedWord)/(double)(originWords.size()-size+1);
		return rate; 
	}

	public int countMatch(List<String> originWords, List<String> againstWords, int size) throws IndexOutOfBoundsException{
		int count = 0;
		
		if(size<=0)
			throw new IndexOutOfBoundsException("size expect be a positive number");
		if(originWords.size()<size)
			throw new IndexOutOfBoundsException("originWords size is "+originWords.size()+" while tuple size="+size);
		if(againstWords.size()<size)
			throw new IndexOutOfBoundsException("againstWords size is "+againstWords.size()+" while tuple size is "+size);
		if( againstWords.size()==0)
			throw new IndexOutOfBoundsException("againstWords should not be empty");
				
		TrieNode<String> trie = getTrie(againstWords,size);		
		LinkedList<String> tupleQueue = new LinkedList<String>();
		Iterator<String> originWordsIterator = originWords.iterator();
		
		for(int i=0; i<size-1 && originWordsIterator.hasNext();i++){
			tupleQueue.offer( originWordsIterator.next() );
		}
		
		while( originWordsIterator.hasNext() ){			
			tupleQueue.offer( originWordsIterator.next() );
			if( trie.contains(tupleQueue,synonyms) ){
				count++;
			}
			tupleQueue.pop();
		}
		
		return count;
	}
	
	private TrieNode<String> getTrie(List<String> words, int size){
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
