package com.wordroner.wordroner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordMap {
	private HashMap<String, Integer> wordMap;
	private static final String useless_list[] = {"a","an","the","which","that","what","is","are","am","was","were","be","at","in","on","i","we","you"};
	private static final HashMap<String, Integer> useless_map = new HashMap<String,Integer>();


    public WordMap() {
    }

    public WordMap(ArrayList<String> words){
		wordMap = new HashMap<String, Integer>();
		
		for(int i = 0; i<useless_list.length;i++)
			useless_map.put(useless_list[i],1);

		for(String word : words)
			InsertWords(word);
	}

	
	//insert word to wordMap
	//if already exists, just increase the counter
	//if word is useless, do nothing
	private void InsertWords(String word){
		if(useless_map.containsKey(word))
			return;
		
		if(wordMap.containsKey(word))
			wordMap.put(word, wordMap.get(word) + 1); //increase key value
		else
			wordMap.put(word, 1);
	}
	
	//return word list by descending order
	public List<Map.Entry<String, Integer>> ExtractWords(){
		List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(wordMap.entrySet());
		
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			  public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2)
			  {
				  if (e1.getValue() == e2.getValue())
					  return e1.getKey().compareTo(e2.getKey());
				  else
					  return e2.getValue().compareTo(e1.getValue());
			  }	
		});
		
		return list;
	}


}
