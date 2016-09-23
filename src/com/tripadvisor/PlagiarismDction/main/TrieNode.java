package com.tripadvisor.PlagiarismDction.main;

import java.util.List;
import java.util.Map;

public interface TrieNode<T> {
	
	//get	
	public boolean isLeaf();
	public boolean isRoot();
	public Map <T, TrieNode<T>> getChildren();
	public boolean contains(List<T> tuple);
	public boolean isEnd();
	//set
	public void setEnd(boolean isEnd);
	//
	public void attach(T word);
	public void insert(final List<T> words);
	
}