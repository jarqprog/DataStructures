package com.jarq.linkedList;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CustomLinkedListTest {

    private ICustomLinkedList<Integer> linkedList = new CustomLinkedList<>();


    @Test
    public void testAdd() {

        Integer num = 10;

        assertTrue(linkedList.add(num));
        assertEquals(num, linkedList.get(0));

    }

    @Test
    public void testAdd_1() {

        Integer num = 10;

        assertTrue(linkedList.add(num));
        assertTrue(linkedList.add(1));
        assertEquals(num, linkedList.get(0));

    }

    @Test
    public void testAdd_by_index() {

        assertTrue(linkedList.add(10));
        assertTrue(linkedList.add(11));
        assertTrue(linkedList.add(12));

        Integer num = 5;
        assertTrue(linkedList.add(num, 1));

        assertEquals(num, linkedList.get(1));
    }

    @Test
    public void testAdd_by_index_if_empty() {

        Integer num = 5;
        assertTrue(linkedList.add(num, 0));

        assertEquals(num, linkedList.get(0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAdd_with_invalid_index() throws IndexOutOfBoundsException {

        linkedList.add(7, 2);
    }

    @Test
    public void testGet() {

        Integer num = 3;

        linkedList.add(0);
        linkedList.add(1);
        linkedList.add(num);

        assertEquals(num, linkedList.get(2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGet_if_empty() throws IndexOutOfBoundsException {
        linkedList.get(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGet_with_invalid_index() throws IndexOutOfBoundsException {
        linkedList.get(-5);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGet_with_invalid_index_1() throws IndexOutOfBoundsException {
        linkedList.add(1);
        linkedList.get(1);
    }

    @Test
    public void testSize() {

        linkedList.add(1);
        linkedList.add(2);

        assertEquals(2, linkedList.size());
    }

    @Test
    public void testSize_if_empty() {
        assertEquals(0, linkedList.size());
    }

    @Test
    public void testGetLast() {

        Integer num = 5;

        linkedList.add(0);
        linkedList.add(1);
        linkedList.add(num);

        assertEquals(num, linkedList.getLast());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetLast_if_empty() throws IndexOutOfBoundsException {
        linkedList.getLast();
    }

    @Test
    public void testGetFirst() {

        Integer num = 5;

        linkedList.add(num);
        linkedList.add(1);
        linkedList.add(2);

        assertEquals(num, linkedList.getFirst());
    }


}
