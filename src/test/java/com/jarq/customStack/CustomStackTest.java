package com.jarq.customStack;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CustomStackTest {

    private int stackSize = 3;
    private ICustomStack<String> stack = new CustomStack<>(stackSize);

    @Test
    public void testPush() {
        assertTrue(stack.push("One"));
    }

    @Test(expected = StackOverflow.class)
    public void testPush_while_is_full() throws StackOverflow {
        stack.push("One");
        stack.push("Two");
        stack.push("Three");
        stack.push("Four");
    }

    @Test(expected = StackUnderflow.class)
    public void testPop_while_stack_is_empty() throws StackUnderflow {

        stack.pop();
    }

    @Test
    public void testPop() {

        String element = "Element";

        stack.push(element);

        assertEquals(element, stack.pop());
    }

    @Test
    public void testPop_second_element() {

        String one = "one";
        String two = "two";

        stack.push(one);
        stack.push(two);

        assertEquals(two, stack.pop());
    }

    @Test
    public void testPeek() {

        String one = "one";

        stack.push(one);

        assertEquals(one, stack.peek());

    }

    @Test(expected = StackUnderflow.class)
    public void testPeek_while_is_empty() throws StackUnderflow {
        stack.peek();
    }

    @Test
    public void testPeek_third_element() {

        String one = "one";
        String two = "two";
        String three = "three";

        stack.push(one);
        stack.push(two);
        stack.push(three);

        assertEquals(three, stack.peek());
    }

    @Test
    public void testSize() {

        assertEquals(stackSize, stack.size());

    }

    @Test
    public void testPlacesLeft_with_empty_stack() {

        assertEquals(stackSize, stack.size());

    }

    @Test
    public void testPlacesLeft_with_one_element() {

        int expected = stackSize - 1;

        stack.push("1");

        assertEquals(expected, stack.placesLeft());
    }

    @Test
    public void testPlacesLeft_with_two_elements() {

        int expected = stackSize - 2;

        stack.push("1");
        stack.push("2");

        assertEquals(expected, stack.placesLeft());
    }
}