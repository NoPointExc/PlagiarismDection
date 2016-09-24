package com.tripadvisor.PlagiarismDction.main;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class WordsSynonyms implements Synonyms<String> {
	
	private static  HashMap<String,List<String>> synonymsMap = new HashMap<String,List<String>>(); 
	
	@Override
	public void loadSynonyms(List<List<String>> synonymsList){
		clearSynonyms();
		loadSynonymsWithoutClear(synonymsList);
	}
	
	public void clearSynonyms(){
		synonymsMap = new HashMap<String,List<String>>();
	}
		
	public void loadSynonymsWithoutClear(List<List<String>> synonymsList){
		
		Iterator<List<String>> listIterator = synonymsList.iterator();
		System.out.println("synonymsList.size() =" + synonymsList.size());
		while(listIterator.hasNext()){
			List<String> synonyms =  listIterator.next();
			Iterator<String> wordIterator =  synonyms.iterator();			
			
			while(wordIterator.hasNext()){
				String word = wordIterator.next();
				if(!synonymsMap.containsKey(word)){
					synonymsMap.put(word, synonyms);
				}else{
					List<String> curSynonyms = synonymsMap.get(word);
					List<String>  newSynonyms = new LinkedList<String>(curSynonyms);
					newSynonyms.addAll(synonyms);
					synonymsMap.put(word,newSynonyms);
				}
			}
			
		}
		
		System.out.println(getSynonymsOf("run"));
		System.out.println(getSynonymsOf("jog"));
		System.out.println(getSynonymsOf("sprint"));
	}
	
	@Override
	public  boolean isSimilar(String value1, String value2) {
		if(value1.equals(value2))
			return true;
		List<String> value1Synonyms = synonymsMap.get(value1);
		if(value1Synonyms == null)
			return false;
		return value1Synonyms.contains(value2);
	}

	@Override
	public List<String> getSynonymsOf(String word) {		
		return synonymsMap.get(word);
	}
	
	

}
