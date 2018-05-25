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

        System.out.println("Tree:");
        printer.print();
        System.out.println("******");
    }

    private static void benchmark(List<Integer> numbers, BinarySearchTree<Integer> tree) {

        System.out.println("Preparing test for list vs tree (size: " + tree.size() + ")");

        int firstElement = numbers.get(0);
        int middleElement = numbers.get(numbers.size() / 2);
        int lastElement = numbers.get(numbers.size()-1);

        int[] elements = {300, 200, 100, 50, 12, firstElement, middleElement, lastElement, -1000, -5, -1};
        long startTime;
        long stopTime;

        // list test
        startTime = System.nanoTime();
        for(int el : elements) {
            numbers.contains(el);
        }
        stopTime = System.nanoTime() - startTime;
        System.out.println("List test: total execution time (in nanos): " + stopTime);

        // tree test
        startTime = System.nanoTime();
        for(int el : elements) {
            tree.contains(el);
        }
        stopTime = System.nanoTime() - startTime;
        System.out.println("BinarySearchTree test: total execution time (in nanos): " + stopTime);
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
