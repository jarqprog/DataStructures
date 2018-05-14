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
    public void testSize() {

        linkedList.add(1);
        linkedList.add(2);

        assertEquals(2, linkedList.size());
    }

    @Test
    public void testSize_if_empty() {
        assertEquals(0, linkedList.size());
    }


}
