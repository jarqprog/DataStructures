package com.jarq.hashTable;

import org.junit.Test;

import static org.junit.Assert.*;

public class HashTableTest {

    private HashTable hashTable = new HashTable(12, new StringHash());


    @Test
    public void size_if_empty() {

        assertEquals(0, hashTable.size());
    }

    @Test
    public void add_one_element() {

        hashTable.add("1");

        assertEquals(1, hashTable.size());
    }

    @Test
    public void look_up_with_one_element() {

        hashTable.add("1");

        assertTrue(hashTable.lookup("1"));
    }

    @Test
    public void size_with_three_element() {

        hashTable.add("1");
        hashTable.add("2");
        hashTable.add("3");

        assertEquals(3, hashTable.size());
    }

    @Test
    public void size_with_three_same_element() {

        hashTable.add("1");
        hashTable.add("1");
        hashTable.add("1");

        assertEquals(1, hashTable.size());
    }

    @Test
    public void add_with_multiple_elements() {

        String[] words = {"mama", "lol", "dad", "papa", "new", "constant", "road", "mark"};
        for(String word : words) {
            hashTable.add(word);
        }

        assertEquals(words.length, hashTable.size());
    }

    @Test
    public void add_with_huge_number_of_elements() {

        int quantity = 1000;

        int counter = 0;
        while(counter < quantity) {
            hashTable.add(String.valueOf(counter));
            counter++;
        }

        assertEquals(quantity, hashTable.size());
    }

    @Test
    public void lookup() {

        String[] words = {"mama", "lol", "dad", "papa", "new", "constant", "road", "mark"};
        for(String word : words) {
            hashTable.add(word);
        }

        assertTrue(hashTable.lookup(words[5]));
        assertTrue(hashTable.lookup(words[3]));
        assertTrue(hashTable.lookup(words[1]));

    }

    @Test
    public void remove_if_map_is_empty() {

        assertFalse(hashTable.remove("mummy"));

    }


    @Test
    public void remove_if_map_contains_one_element() {

        hashTable.add("mummy");

        assertTrue(hashTable.lookup("mummy"));

        assertTrue(hashTable.remove("mummy"));
        assertFalse(hashTable.lookup("mummy"));

    }


    @Test
    public void remove_not_existing_element() {

        hashTable.add("mummy");

        assertFalse(hashTable.remove("mummies"));

    }

    @Test
    public void remove_if_element_exists_in_table() {

        String[] words = {"mama", "lol", "dad", "papa", "new", "constant", "road", "mark"};
        for(String word : words) {
            hashTable.add(word);
        }

        assertEquals(words.length, hashTable.size());


        assertTrue(hashTable.remove(words[5]));
        assertTrue(hashTable.remove(words[3]));
        assertTrue(hashTable.remove(words[1]));

        assertEquals(words.length - 3, hashTable.size());
    }

}