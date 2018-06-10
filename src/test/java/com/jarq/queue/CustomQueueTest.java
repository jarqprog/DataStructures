package com.jarq.queue;


import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CustomQueueTest {

    private ICustomQueue<String> queue = new CustomQueue<>();

    @Test
    public void testEnqueue() {
        assertTrue(queue.enqueue("one"));
    }

    @Test
    public void testPeek_with_one_element() {
        queue.enqueue("one");
        assertEquals("one", queue.peek());
    }

    @Test
    public void testPeek_with_three_element() {
        queue.enqueue("one");
        queue.enqueue("two");
        queue.enqueue("three");
        assertEquals("one", queue.peek());
    }

    @Test
    public void testDequeue_with_one_element() {
        queue.enqueue("one");
        assertEquals("one", queue.dequeue());
    }

    @Test
    public void testDequeue_with_three_element() {
        queue.enqueue("one");
        queue.enqueue("two");
        queue.enqueue("three");
        assertEquals("one", queue.dequeue());
    }

    @Test
    public void testQueueSize_if_empty() {
        assertEquals(0, queue.size());
    }

    @Test
    public void testQueueSize_if_two_elements_inside() {
        queue.enqueue("one");
        queue.enqueue("two");
        assertEquals(2, queue.size());
    }

    @Test
    public void testIsEmpty_if_empty() {
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testIsEmpty_if_not_empty() {
        queue.enqueue("one");
        assertFalse(queue.isEmpty());
    }
}