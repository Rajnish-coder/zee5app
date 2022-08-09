package com.zee.zee5app;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.TreeMap;

public class SortMain {

	public static void main(String[] args)
	{
		LinkedHashMap<Integer,String> hashMap = new LinkedHashMap<>();
		
		hashMap.put(null, "raj");
		hashMap.put(5, "abhi");
		hashMap.put(-10, "abc");
		hashMap.put(null, "hello");
		hashMap.put(2, null);
		System.out.println(hashMap.get(2));
		
		for (Entry <Integer,String> entry : hashMap.entrySet()) {
			
			System.out.println(entry.getKey() + "  " + entry.getValue());
		}
		
		for (Entry<Integer, String> entry : hashMap.entrySet()) {
			Integer key = entry.getKey();
			String val = entry.getValue();
			
		}
		
		hashMap.forEach((k,v)->System.out.println(k+"  "+v));
		
		System.out.println(hashMap.keySet());
		
	}
}

