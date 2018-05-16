package com.jarq;

import com.jarq.binaryTree.IntBinarySearchTree;
import com.jarq.binaryTree.IntBinarySearchTreeImpl;
import com.jarq.dynamicArray.DynamicIntArray;
import com.jarq.dynamicArray.IDynamicIntArray;

public class App {
    public static void main( String[] args ) {

        benchmarkTreeVsDynamicArray();

    }

    private static void benchmarkTreeVsDynamicArray() {

        int numOfElements = 30;
        int elementToFind = 10;
        IDynamicIntArray array = buildArray(numOfElements);
        IntBinarySearchTree tree = buildTree(numOfElements);



        long startTreeTime = System.nanoTime();
        System.out.println(tree.containsValue(elementToFind));
        long treeBenchmark = System.nanoTime() - startTreeTime;

        System.out.println("tree benchmark: " + treeBenchmark);

        long startArrayTime = System.nanoTime();
        System.out.println(array.contains(elementToFind));
        long arrayBenchmark = System.nanoTime() - startArrayTime;

        System.out.println("array benchmark: " + arrayBenchmark);

        String benchmark = String.format("Result:\n running finding element %s in collection that " +
                "contains %s elements:\n\t-in array - " +
                "time execution: %d \n" +
                "\t-in tree - time execution: %d",
                elementToFind, numOfElements, arrayBenchmark, treeBenchmark);

        System.out.println(benchmark);
    }

    private static IDynamicIntArray buildArray(int numberOfElements) {

        IDynamicIntArray array = new DynamicIntArray();

        for(int i=numberOfElements; i>0; i--) {
            array.add(i);
        }

        return array;
    }

    private static IntBinarySearchTree buildTree(int numberOfElements) {

        IntBinarySearchTree tree = new IntBinarySearchTreeImpl();

        for(int i=numberOfElements; i>0; i--) {
            tree.add(i);
        }

        return tree;
    }

}
