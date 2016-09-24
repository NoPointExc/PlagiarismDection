package com.tripadvisor.PlagiarismDction.main;

import java.util.List;

public interface Synonyms<T> {
	
	void loadSynonyms(List<List<T>> synonymsList);
	
	public  boolean isSimilar(T value1, T value2);	
	
	public List<T> getSynonymsOf(T value);
	
}
