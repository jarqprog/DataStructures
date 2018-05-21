package com.jarq.trie;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class TrieMiniApp {

    public static void main(String[] args) throws IOException {

        Path worldListPath = new File("src/main/resources/wordlist.txt").toPath();
        List<String> wordList = Files.readAllLines(worldListPath);

        AutoCompletableWordTrie trie = new AutoComplete();

        long startTime = System.nanoTime();

        System.out.println("starting to build a tree using " + wordList.size() + " words..");
        for (String word : wordList) {
            trie.add(word);
        }

        System.out.println("Done. Building time (in nanoseconds): " + (System.nanoTime() - startTime) );
        System.out.println("Trie with " + trie.size() + " nodes is ready. Let's try " +
                "autocomplete mechanic..");

        String userChoice = "";
        Scanner scanner = new Scanner(System.in);
        String toQuit = "q";

        while(! userChoice.toLowerCase().equals(toQuit)) {

            System.out.println("\ntype a prefix (or 'q' to exit program): ");
            userChoice = scanner.nextLine();

            if(! userChoice.toLowerCase().equals(toQuit)) {
                System.out.println("My suggestions:");
                trie.autoComplete(userChoice).forEach(s -> System.out.print(s + "; "));
            }
        }

        System.out.println("Bye!");
    }
}