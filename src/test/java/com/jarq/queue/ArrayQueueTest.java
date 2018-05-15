package com.jarq.queue;

import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayQueueTest {

    private ICustomQueue<String> queue = new ArrayQueue<>();

    @Test
    public void queueSize_if_empty() {
        assertEquals(0, queue.queueSize());
    }

    @Test
    public void isEmpty_if_empty() {
        assertTrue(queue.isEmpty());
    }

    @Test
    public void enqueue() {
        assertTrue(queue.enqueue("Ad"));
    }

    @Test
    public void isEmpty_if_not_empty() {

        queue.enqueue("Al");

        assertFalse(queue.isEmpty());
    }

    @Test(expected = QueueUnderflow.class)
    public void peek_with_empty_queue() throws QueueUnderflow {
        queue.peek();
    }

    @Test
    public void peek_with_one_element() {

        queue.enqueue("1");

        assertEquals("1", queue.peek());
        assertFalse(queue.isEmpty());
    }

    @Test
    public void enqueue_using_one_element() {

        queue.enqueue("Ad");

        assertEquals("Ad", queue.peek());
    }


    @Test
    public void enqueue_using_three_elements() {

        assertTrue(queue.enqueue("Ad"));
        assertTrue(queue.enqueue("Da"));
        assertTrue(queue.enqueue("An"));

        assertEquals(3, queue.queueSize());
        assertEquals("Ad", queue.peek());
    }

    @Test
    public void enqueue_using_fifty_elements() {

        Integer loopsNumber = 50;
        while(loopsNumber > 0) {
            queue.enqueue(loopsNumber.toString());
            loopsNumber--;
        }

        assertEquals(50, queue.queueSize());
        assertEquals("50", queue.peek());
    }

    @Test(expected = QueueUnderflow.class)
    public void dequeue_if_queue_is_empty() throws QueueUnderflow {
        queue.dequeue();
    }

    @Test
    public void dequeue_if_one_element_in_queue() {

        queue.enqueue("2");


        assertEquals("2", queue.dequeue());
        assertEquals(0, queue.queueSize());
        assertTrue(queue.isEmpty());
    }

    @Test
    public void dequeue_if_three_elements_in_queue() {

        queue.enqueue("1");
        queue.enqueue("2");
        queue.enqueue("3");


        assertEquals("1", queue.dequeue());
        assertEquals(2, queue.queueSize());
        assertFalse(queue.isEmpty());
    }

}