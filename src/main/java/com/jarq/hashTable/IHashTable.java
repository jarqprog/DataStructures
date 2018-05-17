package com.jarq.hashTable;

public interface IHashTable {

    void add(String word);

    boolean lookup(String word);

    boolean remove(String word);

    int size();

}
