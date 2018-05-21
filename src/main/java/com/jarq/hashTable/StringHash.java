package com.jarq.hashTable;

import static java.lang.Math.abs;

public class StringHash implements Hash<String> {

	public int hash(String element) {
		int h = 7;
		
		for (int i = 0; i < element.length(); ++i) {
			h *= 31;
			h += Character.toLowerCase(element.charAt(i));
		}
		
		return abs(h);
	}
}
