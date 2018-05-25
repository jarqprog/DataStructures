package com.jarq.binarySearchTree;

import java.util.*;

public class BSTDemo {

    public static void main(String[] args) {

        List<Integer> numbers = generateRandomNumbers(500, 500);
        BinarySearchTree<Integer> tree = generateTreeWithNumbers(numbers);
        drawTree(tree);

        // perform benchmark with different setup data:
        System.out.println("\nPreparing test for list vs tree - testing execution time for " +
                "methods: contains/add/remove. Results in nanoseconds:\n");
        Scanner sc = new Scanner(System.in);
        System.out.println("Type something & press <enter> to run tests.. ");
        sc.nextLine();
        int[] setupData = {100, 200, 300, 400, 500};
        for(int data : setupData ) {
            numbers = generateRandomNumbers(data, data);
            tree = generateTreeWithNumbers(numbers);
            benchmark(numbers, tree);
        }
    }

    private static void drawTree(BinarySearchTree<Integer> tree) {
        BinarySearchTreePrinter printer = new LevelsPrinter<>(tree);
        System.out.println("Drawing tree:\n");
        printer.print();
        System.out.println("******\n");
    }

    private static void benchmark(List<Integer> numbers, BinarySearchTree<Integer> tree) {
        int firstElement = numbers.get(0);
        int middleElement = numbers.get(numbers.size() / 2);
        int lastElement = numbers.get(numbers.size()-1);

        int[] elements = {300, 200, 100, 50, 12, firstElement, middleElement, lastElement, -1000, -5, -1};

        System.out.println("\n***********************************************");
        System.out.println("Data structures size: " + tree.size());
        System.out.println("\t - test using elements:");
        System.out.println("\t" + Arrays.toString(elements));

        containsTest(numbers, tree, elements);
        addTest(numbers, tree, elements);
        removeTest(numbers, tree, elements);
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

    private static void containsTest(List<Integer> numbers, BinarySearchTree<Integer> tree, int[] elements) {
        long startTime, stopTime;
        System.out.println("\n\t - test CONTAINS():");
        // list test
        startTime = System.nanoTime();
        for(int el : elements) {
            numbers.contains(el);
        }
        stopTime = System.nanoTime() - startTime;
        System.out.print("\tList: " + stopTime);

        // tree test
        startTime = System.nanoTime();
        for(int el : elements) {
            tree.contains(el);
        }
        stopTime = System.nanoTime() - startTime;
        System.out.print(" vs BinarySearchTree: " + stopTime);
    }

    private static void addTest(List<Integer> numbers, BinarySearchTree<Integer> tree, int[] elements) {
        long startTime, stopTime;
        System.out.println("\n\t - test ADD():");
        // list test
        startTime = System.nanoTime();
        for(int el : elements) {
            numbers.add(el);
        }
        stopTime = System.nanoTime() - startTime;
        System.out.print("\tList: " + stopTime);

        // tree test
        startTime = System.nanoTime();
        for(int el : elements) {
            tree.add(el);
        }
        stopTime = System.nanoTime() - startTime;
        System.out.print(" vs BinarySearchTree: " + stopTime);
    }

    private static void removeTest(List<Integer> numbers, BinarySearchTree<Integer> tree, int[] elements) {
        long startTime, stopTime;

        System.out.println("\n\t - test REMOVE():");
        // list test
        startTime = System.nanoTime();
        for(Integer el : elements) {
            boolean isDone = numbers.remove(el);  // use bool as return type, to not removing by index
        }
        stopTime = System.nanoTime() - startTime;
        System.out.print("\tList: " + stopTime);

        // tree test
        startTime = System.nanoTime();
        for(Integer el : elements) {
            boolean isDone = tree.remove(el);
        }
        stopTime = System.nanoTime() - startTime;
        System.out.print(" vs BinarySearchTree: " + stopTime);
    }
}
