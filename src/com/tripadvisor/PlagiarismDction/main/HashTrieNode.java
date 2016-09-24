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
	
	//TODO: iterator
	
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
	
	@Override
	public boolean contains(List<T> tupleOfValue, Synonyms<T> synonyms) {
		if(synonyms == null)
			return contains(tupleOfValue);
		
		HashTrieNode<T> node = this;
		
		for(int i=0; i<tupleOfValue.size(); i++){
			if(node.getChildren(tupleOfValue.get(i))!=null){
				node = node.getChildren(tupleOfValue.get(i));
			}else{
				List<T> synonymsWords = synonyms.getSynonymsOf( node.getValue());
				HashTrieNode<T> similarNode = null;
				if(synonymsWords != null) {	
					for(T word:synonymsWords){
						if(node.getChildren(word)!=null){
							similarNode = node.getChildren(word);
						}
					}
				}
					
				if(similarNode == null)
					return false;
				node = similarNode;	
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
		//Iterator<T> valueIterator = values.iterator();
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
	
	@Override
	public String toString(){
		StringBuilder strBuilder = new StringBuilder();
		LinkedList<HashTrieNode<T>> queue = new LinkedList<HashTrieNode<T>>();
		queue.offer(this);
		
		while(queue.isEmpty()){
			HashTrieNode<T> node = queue.poll();
		
			strBuilder.append(   ((Object)(node.getValue())).hashCode()       );
			for(T key : node.getChildren().keySet()) {
				queue.offer((HashTrieNode<T>) node.getChildren().get(key));
			}
			
		}
		
		return strBuilder.toString();
	}
}
