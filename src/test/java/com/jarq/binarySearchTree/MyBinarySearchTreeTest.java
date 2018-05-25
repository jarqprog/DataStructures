package com.jarq.binarySearchTree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

public class MyBinarySearchTreeTest {

    private BinarySearchTree<Integer> bst = new MyBinarySearchTree<>();

    @Test
    public void size_with_empty_tree() {
        assertEquals(0, bst.size());
    }

    @Test
    public void add() {
        assertTrue(bst.add(10));
    }

    @Test
    public void size_with_one_element_in_tree() {

        bst.add(20);

        assertEquals(1, bst.size());
    }

    @Test
    public void size_with_many_element_in_tree() {

        fillTreeWithInts(50);

        assertEquals(50, bst.size());
    }

    @Test
    public void contains_with_empty_tree() {
        assertFalse(bst.contains(7));
    }

    @Test
    public void contains_using_value_which_is_not_in_tree() {

        bst.add(10);
        bst.add(12);
        bst.add(7);

        assertFalse(bst.contains(6));
    }

    @Test
    public void contains_if_one_element_in_tree() {

        bst.add(10);

        assertTrue(bst.contains(10));
    }

    @Test
    public void contains_using_with_three_elements_in_tree() {

        bst.add(10);
        bst.add(12);
        bst.add(7);

        assertTrue(bst.contains(12));
        assertTrue(bst.contains(10));
    }

    @Test
    public void contains_using_tree_with_many_elements() {

        fillTreeWithInts(40);

        assertTrue(bst.contains(12));
        assertTrue(bst.contains(9));
        assertTrue(bst.contains(30));
        assertTrue(bst.contains(35));
        assertTrue(bst.contains(2));
        assertTrue(bst.contains(19));

        assertFalse(bst.contains(-2));
        assertFalse(bst.contains(50));
        assertFalse(bst.contains(99));
    }


    @Test
    public void remove_with_empty_tree() {
        assertFalse(bst.remove(1));
    }

    @Test
    public void remove_element_from_root() {

        bst.add(5);

        assertTrue(bst.remove(5));
        assertEquals(0, bst.size());
    }

    @Test
    public void remove_element_from_root_two_times() {

        bst.add(5);
        bst.add(5);

        assertTrue(bst.remove(5));
        assertEquals(1, bst.size());

        assertTrue(bst.remove(5));
        assertEquals(0, bst.size());

        assertFalse(bst.remove(5));
        assertEquals(0, bst.size());
    }

    @Test
    public void remove_not_existing_element() {

        bst.add(5);
        bst.add(15);
        bst.add(20);

        assertFalse(bst.remove(3));
        assertFalse(bst.remove(6));
        assertFalse(bst.remove(9));
        assertEquals(3, bst.size());
    }

    @Test
    public void remove_existing_elements_in_small_tree1() {

        bst.add(1);
        bst.add(22);
        bst.add(0);
        bst.add(3);
        bst.add(26);
        bst.add(21);

        int size = bst.size();

        assertTrue(bst.remove(26));
        assertFalse(bst.contains(26));
        assertEquals(size-1, bst.size());

        assertTrue(bst.remove(1));
        assertFalse(bst.contains(1));
        assertEquals(size-2, bst.size());

        assertTrue(bst.remove(22));
        assertFalse(bst.contains(22));
        assertEquals(size-3, bst.size());

        assertTrue(bst.remove(0));
        assertFalse(bst.contains(0));
        assertEquals(size-4, bst.size());

        assertTrue(bst.remove(21));
        assertFalse(bst.contains(21));
        assertEquals(size-5, bst.size());
    }

    @Test
    public void remove_existing_elements_in_small_tree2() {

        bst.add(5);
        bst.add(5);
        bst.add(2);
        bst.add(5);
        bst.add(2);

        assertTrue(bst.remove(2));
        assertTrue(bst.remove(5));
        assertTrue(bst.remove(5));
        assertTrue(bst.remove(5));
        assertFalse(bst.contains(5));
        assertTrue(bst.remove(2));
        assertFalse(bst.contains(2));

        assertEquals(0, bst.size());
    }

    @Test
    public void remove_existing_element_multiple_times() {

        int element = 17;

        bst.add(1);
        bst.add(element);
        bst.add(element);
        bst.add(22);
        bst.add(3);
        bst.add(element);
        bst.add(-1);

        int size = bst.size();

        assertTrue(bst.remove(element));
        assertTrue(bst.remove(element));
        assertTrue(bst.remove(element));
        assertFalse(bst.remove(element));
        assertEquals(size-3, bst.size());
    }

    @Test
    public void remove_existing_element_multiple_times_if_element_in_root() {

        int element = 10;

        bst.add(element);
        bst.add(element);
        bst.add(22);
        bst.add(3);
        bst.add(element);
        bst.add(-1);
        bst.add(element);

        int size = bst.size();

        assertTrue(bst.remove(element));
        assertTrue(bst.remove(element));
        assertTrue(bst.remove(element));
        assertTrue(bst.remove(element));
        assertFalse(bst.remove(element));
        assertEquals(size-4, bst.size());
    }

    @Test
    public void remove_existing_elements_in_big_tree() {

        bst.add(1);
        bst.add(22);
        bst.add(0);
        bst.add(3);
        bst.add(26);
        bst.add(21);

        fillTreeWithInts(100);

        int size = bst.size();

        assertTrue(bst.remove(50));
        assertTrue(bst.remove(22));
        assertTrue(bst.remove(21));
        assertEquals(size-3, bst.size());
    }

    @Test
    public void findMinimum_with_empty_tree() {
        assertNull(bst.findMinimum());
    }

    @Test
    public void findMinimum_using_tree_with_five_elements() {

        int expected = -999;
        bst.add(10);
        bst.add(809);
        bst.add(expected);
        bst.add(0);
        bst.add(-332);

        assertEquals(expected, (long) bst.findMinimum());
    }

    @Test
    public void findMinimum_using_tree_with_many_elements() {

        fillTreeWithRandomElementsBetweenZeroAndGivenValue(500, 1000);
        long expected = 0;

        assertEquals(expected, (long) bst.findMinimum());
    }

    @Test
    public void findMaximum_with_empty_tree() {
        assertNull(bst.findMaximum());
    }

    @Test
    public void findMaximum_using_tree_with_five_elements() {

        int expected = 855;
        bst.add(776);
        bst.add(89);
        bst.add(expected);
        bst.add(0);
        bst.add(-332);

        assertEquals(expected, (long) bst.findMaximum());
    }

    @Test
    public void findMaximum_using_tree_with_many_elements() {

        int maxValue = 1000;
        fillTreeWithRandomElementsBetweenZeroAndGivenValue(500, maxValue);

        assertEquals(maxValue, (long) bst.findMaximum());
    }

    private void fillTreeWithInts(int numberOfElements) {
        int counter = 0;
        while(counter < numberOfElements) {
            bst.add(counter);
            counter++;
        }
    }

    private void fillTreeWithRandomElementsBetweenZeroAndGivenValue(int numberOfElements, int maxValue) {

        Random chaos = new Random();
        List<Integer> numbers = new ArrayList<>(numberOfElements);
        int counter = 0;
        while(counter < numberOfElements-2) {
            numbers.add(chaos.nextInt(maxValue));
            counter++;
        }
        numbers.add(0);
        numbers.add(maxValue);


        Collections.shuffle(numbers);
        for(int num : numbers) {
            bst.add(num);
        }
    }


}