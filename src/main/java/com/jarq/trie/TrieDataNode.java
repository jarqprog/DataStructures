package com.jarq.trie;

import java.util.*;

class TrieDataNode {

    private char data;
    private TrieDataNode parent;
    private Map<Character, TrieDataNode> children;
    private int completeWordCounter = 0;

    TrieDataNode(char data) {
        this.data = data;
        this.children = new TreeMap<>();
    }

    private TrieDataNode(char data, TrieDataNode parent) {
        this(data);
        this.parent = parent;
    }

    TrieDataNode addChild(char character) {
        TrieDataNode node = new TrieDataNode(character, this);
        children.put(character, node);
        return node;
    }

    boolean removeChild(char letter) {
        children.remove(letter);
        return true;
    }

    char getData() {
        return data;
    }

    boolean removeOnlyChild() {
        if(children.size() > 1) {
            return false;
        }
        children.clear();
        return true;
    }

    boolean hasMoreThanOneChild() {
        return children.size() > 1;
    }

    boolean isChildless() {
        return children.size() == 0;
    }

    int getWordCounter() {
        return completeWordCounter;
    }

    boolean childrenContainLetter(char character) {
        return children.containsKey(character);
    }

    TrieDataNode getMatchingChild(char letter) {
        return children.get(letter);
    }

    TrieDataNode getMatchingChildCaseInsensitive(char letter) {
        TrieDataNode node;
        if( (node = children.get(Character.toLowerCase(letter) )) == null) {
            node = children.get(Character.toUpperCase(letter));
        }
        return node;
    }

    boolean isRoot() {
        return data == '-' && parent == null;
    }

    TrieDataNode getParent() {
        return parent;
    }

    void setParent(TrieDataNode node) {
        this.parent = node;
    }

    boolean isLeaf() {
        return children.size() == 0;
    }

    int getNumberOfChildren() {
        return children.size();
    }

    void markAsWord() {
        this.completeWordCounter++;
    }

    void unMarkAsWord() {
        this.completeWordCounter--;
        if(this.completeWordCounter < 0) {
            this.completeWordCounter = 0;
        }
    }

    boolean isWord() {
        return this.completeWordCounter > 0;
    }

    @Override
    public String toString() {

        String parentInfo;
        if(parent != null) {
            parentInfo = String.valueOf(parent.getData());
        } else {
            parentInfo = "no parent";
        }

        return "Node with character: " + Character.toString(data) +
                ", num of children: " + children.size() +
                ", parent: " + parentInfo +
                ", last letter in word: " + (isWord() || isLeaf());
    }

    List<String> getWords() {
        Set<TrieDataNode> exploitedNodes = new HashSet<>();
        List<String> words = new ArrayList<>();

        if( isWord() ) {
            words.add(gatherWord());
            exploitedNodes.add(this);
        } if (isLeaf() && ! exploitedNodes.contains(this)) {
            words.add(gatherWord());
            exploitedNodes.add(this);
        } else {
            for( TrieDataNode child : getChildrenAsArray() ) {
                words.addAll(child.getWords());
            }
        }
        return words;
    }

    private String gatherWord() {
        if (parent == null) {
            return "";
        } else {
            return parent.gatherWord() + Character.toString(data);
        }
    }

    String getChildrenAsString() {
        String[] letters = new String[children.size()];
        int counter = 0;
        for(Map.Entry<Character,TrieDataNode> pair : children.entrySet()) {
            letters[counter] = Character.toString(pair.getValue().data);
            counter++;
        }
        return String.join(" ", letters);
    }

    TrieDataNode[] getChildrenAsArray() {
        TrieDataNode[] childrenAsArray = new TrieDataNode[children.size()];
        int counter = 0;
        for(Map.Entry<Character,TrieDataNode> pair : children.entrySet()) {
            childrenAsArray[counter] = pair.getValue();
            counter++;
        }
        return childrenAsArray;
    }
}
