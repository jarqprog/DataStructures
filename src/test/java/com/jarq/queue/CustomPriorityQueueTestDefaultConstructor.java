package com.jarq.queue;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * test for queue with default comparator (min heap)
 */
public class CustomPriorityQueueTestDefaultConstructor {

    private ICustomQueue<Integer> priorityQueue = new CustomPriorityQueue<>();

    @Test
    public void size_with_empty_queue() {
        assertEquals(0, priorityQueue.size());
    }

    @Test
    public void isEmpty_with_empty_queue() {
        assertTrue(priorityQueue.isEmpty());
    }

    @Test(expected = IllegalStateException.class)
    public void peek_with_empty_queue() throws IllegalStateException {
        priorityQueue.peek();
    }

    @Test(expected = IllegalStateException.class)
    public void dequeue_with_empty_queue() throws IllegalStateException {
        priorityQueue.dequeue();
    }

    @Test
    public void enqueue_add_one_element() {
        assertTrue(priorityQueue.enqueue(1));
        assertEquals(1, priorityQueue.size());
        assertFalse(priorityQueue.isEmpty());
    }

    @Test
    public void peek_with_one_element() {
        Integer number = 1;
        priorityQueue.enqueue(number);

        Integer result = priorityQueue.peek();

        assertEquals(number, result);
    }

    @Test
    public void dequeue_with_one_element() {
        Integer number = 1;
        priorityQueue.enqueue(number);

        Integer result = priorityQueue.dequeue();

        assertEquals(0, priorityQueue.size());
        assertEquals(number, result);
    }

    @Test
    public void size_with_one_element() {
        priorityQueue.enqueue(2);

        assertEquals(1, priorityQueue.size());
    }

    @Test
    public void isEmpty_with_one_element() {
        priorityQueue.enqueue(2);

        assertFalse(priorityQueue.isEmpty());
    }

    @Test
    public void size() {
        priorityQueue.enqueue(2);
        priorityQueue.enqueue(1);
        priorityQueue.enqueue(3);
        priorityQueue.enqueue(10);

        System.out.println(priorityQueue);

        assertEquals(4, priorityQueue.size());
    }

    @Test
    public void dequeue_two_elements() {
        Integer first = 2;
        Integer second = 0;

        priorityQueue.enqueue(first);
        priorityQueue.enqueue(second);

        assertEquals(second, priorityQueue.dequeue());
        assertEquals(1, priorityQueue.size());

        assertEquals(first, priorityQueue.dequeue());
        assertEquals(0, priorityQueue.size());
    }

    @Test
    public void dequeue_multiple_elements() {
        Integer two = 2;
        Integer zero = 0;
        Integer ten = 10;
        Integer minusOne = -1;
        Integer minusTen = -10;
        Integer five = 5;
        Integer twenty = 20;

        priorityQueue.enqueue(two);
        priorityQueue.enqueue(ten);
        priorityQueue.enqueue(minusTen);
        priorityQueue.enqueue(minusOne);

        assertEquals(minusTen, priorityQueue.dequeue());
        assertEquals(3, priorityQueue.size());

        priorityQueue.enqueue(zero);
        priorityQueue.enqueue(five);
        priorityQueue.enqueue(minusOne);

        assertEquals(minusOne, priorityQueue.dequeue());
        assertEquals(5, priorityQueue.size());

        assertEquals(minusOne, priorityQueue.dequeue());
        assertEquals(4, priorityQueue.size());
        assertFalse(priorityQueue.isEmpty());

        priorityQueue.enqueue(twenty);
        priorityQueue.enqueue(twenty);

        assertEquals(zero, priorityQueue.dequeue());
        assertEquals(5, priorityQueue.size());

        assertEquals(two, priorityQueue.dequeue());
        assertEquals(4, priorityQueue.size());
        assertFalse(priorityQueue.isEmpty());

        assertEquals(five, priorityQueue.dequeue());
        assertEquals(3, priorityQueue.size());

        assertEquals(ten, priorityQueue.dequeue());
        assertEquals(2, priorityQueue.size());
        assertFalse(priorityQueue.isEmpty());

        assertEquals(twenty, priorityQueue.dequeue());
        assertEquals(1, priorityQueue.size());

        assertEquals(twenty, priorityQueue.dequeue());
        assertEquals(0, priorityQueue.size());
        assertTrue(priorityQueue.isEmpty());
    }
}