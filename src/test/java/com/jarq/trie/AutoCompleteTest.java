package com.jarq.trie;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AutoCompleteTest {

    private AutoCompletableWordTrie ac = new AutoComplete();

    @Test
    public void size_with_one_word() {

        int expectedSize = 4;

        ac.add("mama");

        assertEquals(expectedSize, ac.size());
    }

    @Test
    public void size_with_two_words_using_different_base() {

        int expectedSize = 9;

        ac.add("mama");
        ac.add("daddy");

        assertEquals(expectedSize, ac.size());
    }

    @Test
    public void size_with_three_words_using_same_base() {

        int expectedSize = 11;

        ac.add("mama");
        ac.add("maia");
        ac.add("mark");
        ac.add("maroon");

        assertEquals(expectedSize, ac.size());
    }

    @Test
    public void autocomplete_case_insensitive() throws IndexOutOfBoundsException {

        ac.add("mAma");

        String output = ac.autoComplete("Ma").get(0);

        assertEquals("mAma", output);
    }

    @Test
    public void add_oneWord_fullSearch() {

        ac.add("test");

        List<String> results = ac.autoComplete("test");
        List<String> expected = Collections.singletonList("test");

        assertIterableEquals(expected, results);
    }

    @Test
    public void add_oneWord_partialSearch() {

        ac.add("aReallyLongWord");

        List<String> results = ac.autoComplete("aReally");
        List<String> expected = Collections.singletonList("aReallyLongWord");

        assertIterableEquals(expected, results);
    }

    @Test
    public void add_oneWord_wrongSearch() {

        ac.add("aReallyLongWord");

        List<String> results = ac.autoComplete("Word");

        assertEquals(0, results.size());
    }

    @Test
    public void add_couple_words() {

        String prefix = "spectrogra";
        String[] words = {  "spectrogram", "spectrograph", "spectrographic",
                "spectrographically", "spectrometric", "spectrophotometer",
                "spectroscope", "spectroscopic", "spectroscopy"};
        List<String> expectedOutput = Arrays.asList("spectrogram", "spectrograph", "spectrographic", "spectrographically");

        for(String word : words) {
            ac.add(word);
        }

        List<String> result = ac.autoComplete(prefix);

        assertIterableEquals(expectedOutput, result);
    }

    @Test
    public void add_oneWord_caseInsensitive() {

        ac.add("aReallyLongWord");

        List<String> results = ac.autoComplete("AREALLY");
        List<String> expected = Collections.singletonList("aReallyLongWord");

        assertIterableEquals(expected, results);
    }

    @Test
    public void add_lotsOfWords() throws IOException {
        Path worldListPath = new File("src/main/resources/wordlist.txt").toPath();
        List<String> wordList = Files.readAllLines(worldListPath);

        for (String str : wordList) {
            ac.add(str);
        }
        List<String> results = ac.autoComplete("spectro");
        List<String> expected = Arrays.asList("spectrogram", "spectrograph", "spectrographic",
                "spectrographically", "spectrometric", "spectrophotometer", "spectroscope",
                "spectroscopic", "spectroscopy");

        assertIterableEquals(expected, results);
    }

    @Test
    public void remove_existingWord() {

        ac.add("aReallyLongWord");

        assertTrue(ac.remove("aReallyLongWord"));
        assertEquals(0, ac.autoComplete("aReallyLongWord").size());
    }

    @Test
    public void remove_nonExistingWord() {

        ac.add("aReallyLongWord");

        assertFalse(ac.remove("LongWord"));
        assertEquals(1, ac.autoComplete("aReallyLongWord").size());
    }

    @Test
    public void wordOccurrence_on_empty_trie() {

        assertEquals(0, ac.occurrence("Hannah"));
    }

    @Test
    public void wordOccurrence_using_word_that_not_exist() {

        ac.add("Johny");
        ac.add("Mark");

        assertEquals(0, ac.occurrence("Hannah"));
    }

    @Test
    public void wordOccurrence_using_one_word() {

        ac.add("Johny");

        assertEquals(1, ac.occurrence("Johny"));
    }

    @Test
    public void wordOccurrence_using_ten_words() {

        String johny = "Johny";
        String mark = "Mark";
        int counter = 10;
        for(int i=0;i<counter;i++) {
            ac.add(johny);
            ac.add(mark);
        }

        assertEquals(10, ac.occurrence("Johny"));
    }

    @Test
    public void occurrence_mechanic_on_polluted_trie() {

        String johny = "Johny";
        String mark = "Mark";
        String pollution = "pollution";  // for pollution
        int counter = 100;
        for(int i=0;i<counter;i++) {
            ac.add(pollution);
            ac.add(johny);
            ac.add(pollution);
            ac.add(mark);
            ac.add(pollution);

        }

        assertEquals(counter, ac.occurrence(johny));
        assertEquals(counter, ac.occurrence(mark));

        counter = 50;
        for(int i=0;i<counter;i++) {
            ac.remove(johny);
            ac.remove(mark);
        }

        assertEquals(counter, ac.occurrence(johny));
        assertEquals(counter, ac.occurrence(mark));
        assertEquals(18, ac.size());
    }
}