package com.jarq.trie;

import java.util.List;

public interface AutoCompletableWordTrie extends SearchTrie<String> {

    List<String> autoComplete(String prefix);

}
