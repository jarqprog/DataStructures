package com.jarq.trie;

public interface SearchTrie<T> {

    void add(T element);
    int occurrence(T element);
    boolean remove(T element);
    int size();
}
