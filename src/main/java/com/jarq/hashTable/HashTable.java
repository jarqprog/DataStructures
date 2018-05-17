package com.jarq.hashTable;

public class HashTable {


	private Node[] table;
	private Hash<String> hash;
	private int tableSize;
	private int numberOfWords;

	public HashTable(int tableSize, Hash<String> hash) {

		this.table = new Node[tableSize];
		this.hash = hash;
		this.tableSize = tableSize;
	}

	public void add(String word) {

		if(lookup(word)) {
			return;
		}

		int index = gatherIndex(word);
		int hash = gatherHash(word);

		Node newNode = new Node(hash, word);

		numberOfWords++;
		if(checkIfPlaceIsEmpty(index)) {
			table[index] = newNode;

		} else {
			Node current = table[index];
			current.previous = newNode;
			newNode.next = current;
			table[index] = newNode;
		}
	}

	public boolean lookup(String word) {
		return (gatherNodeWithWord(word) != null);
	}

	public boolean remove(String word) {

		Node node = gatherNodeWithWord(word);

		if(node == null) {
			return false;
		}

		numberOfWords--;
		if(node.hasParent()) {
			node.previous = node.next;
		} else {
			int index = gatherIndex(word);
			table[index] = node.next;
		}
		return true;
	}

	public int size() {
		return numberOfWords;
	}

	private int gatherHash(String word) {
		return hash.hash(word);
	}

	private int gatherIndex(String word) {
		return hash.hash(word) % tableSize;
	}

	private boolean checkIfPlaceIsEmpty(int index) {

		try {
			return this.table[index] == null;

		} catch (IndexOutOfBoundsException notUsed) {
			return false;
		}
	}

	private Node gatherLasNodeUnderIndex(int index) {
		if(index >= tableSize) {
			return null;
		}
		Node node = table[index];

		while(node.next != null) {
			node = node.next;
		}
		return node;
	}

	private Node gatherNodeWithWord(String word) {

		int index = gatherIndex(word);

		if(checkIfPlaceIsEmpty(index)) {
			return null;
		}

		Node node = table[index];

		do {
			if(node.word.equals(word) ) {
				return node;
			}
			node = node.next;

		} while(node != null);
		return null;
	}


	/**
	 * private helper node class
	 */

	private class Node {

		private int hashcode;
		private String word;
		private Node next;
		private Node previous;

		private Node(int hashcode, String word) {
			this.hashcode = hashcode;
			this.word = word;
		}

		private boolean hashcodeMatches(int hashcode) {
			return this.hashcode == hashcode;
		}

		private boolean equals(Node node) {
			return this.word.equals(node.word);
		}

		private boolean hasChild() {
			return this.next != null;
		}

		private boolean hasParent() {
			return this.previous != null;
		}

		public String toString() {
			return "Node with hash: " + hashcode + " and word: " + word;
		}
	}
}
