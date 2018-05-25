package com.jarq.binarySearchTree;

import java.util.*;

public class BSTDemo {

    public static void main(String[] args) {

        List<Integer> numbers = generateRandomNumbers(500, 500);
        BinarySearchTree<Integer> tree = generateTreeWithNumbers(numbers);
        drawTree(tree);
        benchmark(numbers, tree);
    }


    private static void drawTree(BinarySearchTree<Integer> tree) {
        BinarySearchTreePrinter printer = new LevelsPrinter<>(tree);

        System.out.println("Drawing tree:\n");
        printer.print();
        System.out.println("******");
    }

    private static void benchmark(List<Integer> numbers, BinarySearchTree<Integer> tree) {

        System.out.println("Preparing test for list vs tree (size: " + tree.size() + ")");
        System.out.println("\t - test using elements:");

        int firstElement = numbers.get(0);
        int middleElement = numbers.get(numbers.size() / 2);
        int lastElement = numbers.get(numbers.size()-1);

        int[] elements = {300, 200, 100, 50, 12, firstElement, middleElement, lastElement, -1000, -5, -1};
        long startTime;
        long stopTime;
        System.out.println("\t" + Arrays.toString(elements));
        System.out.println("*******************");

        // CONTAINS:
        System.out.println("\n\t - test CONTAINS() elements:");
        // list test
        startTime = System.nanoTime();
        for(int el : elements) {
            numbers.contains(el);
        }
        stopTime = System.nanoTime() - startTime;
        System.out.println("List test (CONTAINS): total execution time (in nanos): " + stopTime);

        // tree test
        startTime = System.nanoTime();
        for(int el : elements) {
            tree.contains(el);
        }
        stopTime = System.nanoTime() - startTime;
        System.out.println("BinarySearchTree test (CONTAINS): total execution time (in nanos): " + stopTime);


        // ADD:
        System.out.println("\n\t - test ADD() elements:");
        // list test
        startTime = System.nanoTime();
        for(int el : elements) {
            numbers.add(el);
        }
        stopTime = System.nanoTime() - startTime;
        System.out.println("List test (ADD): total execution time (in nanos): " + stopTime);

        // tree test
        startTime = System.nanoTime();
        for(int el : elements) {
            tree.add(el);
        }
        stopTime = System.nanoTime() - startTime;
        System.out.println("BinarySearchTree test (ADD): total execution time (in nanos): " + stopTime);

        // REMOVE:
        System.out.println("\n\t - test REMOVE() elements:");
        // list test
        startTime = System.nanoTime();
        for(Integer el : elements) {
            boolean isDone = numbers.remove(el);  // use bool as return type, to not removing by index
        }
        stopTime = System.nanoTime() - startTime;
        System.out.println("List test (REMOVE): total execution time (in nanos): " + stopTime);

        // tree test
        startTime = System.nanoTime();
        for(Integer el : elements) {
            boolean isDone = tree.remove(el);
        }
        stopTime = System.nanoTime() - startTime;
        System.out.println("BinarySearchTree test (REMOVE): total execution time (in nanos): " + stopTime);
    }

    private static BinarySearchTree<Integer> generateTreeWithNumbers(List<Integer> numbers) {

        BinarySearchTree<Integer> tree = new MyBinarySearchTree<>();

        for(Integer number : numbers) {
            tree.add(number);
        }
        return tree;
    }

    private static List<Integer> generateRandomNumbers(int length, int maxValue) {
        Random chaos = new Random();
        List<Integer> numbers = new ArrayList<>(length);
        int counter = 0;
        while(counter < length) {
            numbers.add(chaos.nextInt(maxValue));
            counter++;
        }

        Collections.shuffle(numbers);
        return numbers;
    }
}
