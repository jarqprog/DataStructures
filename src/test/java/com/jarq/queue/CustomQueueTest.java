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
        assertTrue(queue.enqueue("one"));
        assertEquals("one", queue.peek());
    }

    @Test
    public void testDequeue_with_one_element() {
        assertTrue(queue.enqueue("one"));
        assertEquals("one", queue.dequeue());
    }

    @Test
    public void testQueueSize_if_empty() {
        assertEquals(0, queue.queueSize());
    }

    @Test
    public void testQueueSize_if_to_elements_inside() {
        queue.enqueue("one");
        queue.enqueue("two");
        assertEquals(2, queue.queueSize());
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