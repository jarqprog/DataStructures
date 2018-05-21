package com.jarq.trie;

import java.util.*;

public class AutoComplete implements AutoCompletableWordTrie {

    private TrieDataNode root;
    private int size = 0;

    public AutoComplete() {
        root = new TrieDataNode('-');
    }

    @Override
    public void add(String wordToAdd) {

        TrieDataNode node = root;
        for(int i=0; i<wordToAdd.length(); i++) {
            char letter = wordToAdd.charAt(i);

            if(! node.childrenContainLetter(letter)) {
                size++;
                node = node.addChild(letter);
            } else {
                node = node.getMatchingChild(letter);
            }
        }
        node.markAsWord();
    }

    @Override
    public int occurrence(String word) {

        TrieDataNode node = getNodeFromLastLetter(word, false);
        if (node == null) {
            return 0;
        }
        return node.getWordCounter();
    }

    @Override
    public List<String> autoComplete(String baseChars) {

        TrieDataNode node = getNodeFromLastLetter(baseChars, true);
        if(node != null) {
            return node.getWords();
        }
        return new LinkedList<>();
    }

    @Override
    public boolean remove(String wordToRemove) {
        TrieDataNode node = root;

        for(int i=0; i<wordToRemove.length(); i++) {
            char letter = wordToRemove.charAt(i);
            if(node != null) {
                node = node.getMatchingChild(letter);
            }
        }
        return handleRemoving(node);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        return String.format("Tree with size %s (number of nodes), " +
                "root with children: (%s) %s",
                size, root.getNumberOfChildren(), root.getChildrenAsString());
    }

    private boolean handleRemoving(TrieDataNode node) {
        if( node == null) {
            return false;
        }
        if( node.isLeaf() && node.getWordCounter() < 2) {
            node = node.getParent();
            char letter = ' ';
            while( node != null && ! node.isRoot() && ! node.hasMoreThanOneChild() && ! node.isWord() ) {
                node.removeOnlyChild();
                size--;
                letter = node.getData();
                node = node.getParent();
            }

            if(node != null) {
                node.removeChild(letter);
                size--;
            }
        }

        else if(node.isWord()) {
            node.unMarkAsWord();
        }
        return true;
    }

    private TrieDataNode getNodeFromLastLetter(String phrase, boolean caseInsensitive) {
        TrieDataNode node = root;

        for(int i=0; i<phrase.length(); i++) {
            char letter = phrase.charAt(i);
            if(node != null) {
                if(caseInsensitive) {
                    node = node.getMatchingChildCaseInsensitive(letter);
                } else {
                    node = node.getMatchingChild(letter);
                }
            }
        }
        return node;
    }
}
