package com.tripadvisor.PlagiarismDction.main;


import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class PlagiarismDetector {
	//TODO: if "go" is synonyms of two or more set in same time. 
	HashMap<String,Integer>  synonymsMap = new HashMap<String,Integer>();
	
	public PlagiarismDetector(List<List<String>> synonymsList ){
		Iterator<List<String>> listIt = synonymsList.iterator();
		
		while(listIt.hasNext()){
			Iterator<String> wordIt =  listIt.next().iterator();
			int hashCode = -1;
			
			while(wordIt.hasNext()){
				String word = wordIt.next();
				if(hashCode == -1){
					hashCode = word.hashCode();
				}
				synonymsMap.put(word, hashCode);
			}
			
		}
		
	}
	
	private int getHashCode(String word){
		if(synonymsMap.containsKey(word))
			return synonymsMap.get(word); 
		return word.hashCode();
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
				
		TrieNode<Integer> trie = getTrie(againstWords,size);
		//System.out.println(trie.toString());
		
		LinkedList<Integer> tupleQueue = new LinkedList<Integer>();
		Iterator<String> originWordsIterator = originWords.iterator();
		
		for(int i=0; i<size-1 && originWordsIterator.hasNext();i++){
			tupleQueue.offer( getHashCode(originWordsIterator.next()) );
		}
		//System.out.println("tupleQueue="+tupleQueue);
		//TODO: get level to jump unnecessay compare
		while( originWordsIterator.hasNext() ){			
			tupleQueue.offer( getHashCode(originWordsIterator.next()) );
			//System.out.println("tupleQueue="+tupleQueue);
			if( trie.contains(tupleQueue) ){
				//System.out.println("found tupleQueue="+tupleQueue);
				count++;
				//System.out.println("========");
			}
			tupleQueue.pop();
		}
		
		return count;
	}
	
	private TrieNode<Integer> getTrie(List<String> words, int size){
		//TODO: isRoot.
		TrieNode<Integer> root = new HashTrieNode<Integer>();
		TrieNode<Integer> triePointer = root;
		Iterator<String> wordIterator = words.iterator();
		
		LinkedList<Integer> tupleQueue = new LinkedList<Integer>();
		
		for(int i=0; i<size-1 && wordIterator.hasNext();i++){
			tupleQueue.offer( getHashCode(wordIterator.next()) );
		}
		
		while( wordIterator.hasNext() ){			
			tupleQueue.offer( getHashCode(wordIterator.next()) );
			//System.out.println("tupleQueue=null?"+(tupleQueue==null)+" triePointer=null?"+(triePointer==null));
			//System.out.println("tupleQueue"+tupleQueue+" triePointer="+triePointer);			
			triePointer.insert(tupleQueue);
			//System.out.println("insert "+tupleQueue);
			tupleQueue.pop();
		}
		
		return root;
	}
}
