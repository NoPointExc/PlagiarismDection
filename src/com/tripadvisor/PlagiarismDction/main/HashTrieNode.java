package com.tripadvisor.PlagiarismDction.main;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class HashTrieNode<T> implements TrieNode<T>{
	private final T value;
	private final HashMap<T, TrieNode<T>> children = new HashMap<T, TrieNode<T>>();
	private boolean isEnd;
	
	public HashTrieNode(){
		this.value  = null;
		this.isEnd = false;
	}
	
	public HashTrieNode(T value){
		this.value  = value;
		this.isEnd = false;
	}
	
	//these methods should implements in a more general TireNode class. And then extends by this class
	@Override
	public boolean isLeaf(){
		return children.isEmpty();
	}
	
	@Override
	public boolean isRoot(){
		return value == null;
	}
	
	@Override
	public boolean isEnd(){
		return isEnd;
	}
	
	@Override
	public HashMap<T,TrieNode<T>> getChildren(){
		return children;
	}
	
	private HashTrieNode<T> getChildren(T value){
		return (HashTrieNode<T>)children.get(value);
	}
	
	
	public boolean contains(List<T> tupleOfValue) {
		HashTrieNode<T> node = this;
		
		for(int i=0; i<tupleOfValue.size(); i++){
			if(node.getChildren(tupleOfValue.get(i))!=null){
				node = node.getChildren(tupleOfValue.get(i));
			}else{
				return false;
			}
		}
		
		return node!=null && node.isEnd();
	}
	
	//search each level agaist synonyms. 
	//should implement from another interface( an interface has nothing todo with Tire Tree)
	@Override
	public boolean contains(List<T> tupleOfValue, Synonyms<T> synonyms) {
		if(synonyms == null)
			return contains(tupleOfValue);
		
		HashTrieNode<T> node = this;
		
		for(int i=0; i<tupleOfValue.size(); i++){
			T nextValue = tupleOfValue.get(i);
			
			if(node.getChildren(nextValue)!=null){
			
				node = node.getChildren(nextValue);
			
			}else{
				
				List<T> synonymsWords = synonyms.getSynonymsOf( nextValue);
				HashTrieNode<T> synonymsNode = null;
				
				if(synonymsWords != null) {	
					
					for(T word:synonymsWords){
						if(node.getChildren(word)!=null){
							synonymsNode = node.getChildren(word);
							break;
						}
					}

				}
					
				if(synonymsNode == null)
					return false;
				node = synonymsNode;	
			}
		}
		
		return node!=null && node.isEnd();
	}
	
	@Override
	public void setEnd(boolean isEnd) {
		this.isEnd = isEnd;
	}
	
	@Override
	public void attach(T word) {
		children.put(word, new HashTrieNode<T>(word) );
	}

	@Override
	public void insert(final List<T> values) {
		HashTrieNode<T> node = this;
		int i = 0;		
		while(i<values.size()){
			if( node.getChildren(values.get(i))!=null ){
				node = node.getChildren(values.get(i));
				i++;
			}else{
				break;
			}
		}
		
		while(i<values.size()){
			node.attach(values.get(i));
			node = node.getChildren(values.get(i));
			i++;
		}
		
		node.setEnd(true);
	}
	
	public T getValue(){
		return this.value;
	}
	
}
