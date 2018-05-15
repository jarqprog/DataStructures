package com.jarq.intStack;

import com.jarq.customStack.StackOverflow;
import com.jarq.customStack.StackUnderflow;
import org.junit.Test;

import static org.junit.Assert.*;

public class IntStackImplTest {

    private IntStack intStack = new IntStackImpl(3);

    @Test
    public void pop() {

        intStack.push(1);
        intStack.push(2);
        intStack.push(3);

        assertEquals(3, intStack.pop());
        assertTrue(intStack.push(4));  // should add new element
    }

    @Test(expected = StackUnderflow.class)
    public void pop_if_stack_is_empty() throws StackUnderflow {

        intStack.pop();
    }

    @Test
    public void peek() {
        intStack.push(1);
        intStack.push(2);

        assertEquals(2, intStack.peek());
    }

    @Test(expected = StackUnderflow.class)
    public void peek_if_stack_is_empty() throws StackUnderflow {
        intStack.peek();
    }

    @Test
    public void push() {

        assertTrue(intStack.push(10));
        assertTrue(intStack.push(11));
        assertTrue(intStack.push(13));

        assertEquals(13, intStack.peek());
    }

    @Test(expected = StackOverflow.class)
    public void push_if_stack_is_full() throws StackOverflow {

        intStack.push(10);
        intStack.push(11);
        intStack.push(12);
        intStack.push(13);
    }
}