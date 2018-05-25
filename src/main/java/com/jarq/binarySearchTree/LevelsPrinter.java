package com.jarq.binarySearchTree;

import java.util.*;

public class LevelsPrinter<T extends Comparable<T>> implements BinarySearchTreePrinter {

    // inspired by: https://codereview.stackexchange.com/questions/82150/printing-a-tree-level-by-level?utm_medium=organic&utm_source=google_rich_qa&utm_campaign=google_rich_qa

    // prints nodes level by level

    private final BinarySearchTree<T> tree;

    public LevelsPrinter(BinarySearchTree<T> tree) {
        this.tree = tree;
    }

    @Override
    public void print() {

        Node<T> root = tree.getRoot();

        List<List<Node<T>>> levels = traverseLevels(root);

        for (List<Node<T>> level : levels) {
            for (Node<T> node : level) {
                System.out.print(node.getData() + " ");
            }
            System.out.println();
        }
    }

    private List<List<Node<T>>> traverseLevels(Node<T> treeNode) {
        if (treeNode == null) {
            return Collections.emptyList();
        }
        List<List<Node<T>>> levels = new LinkedList<>();

        Queue<Node<T>> nodes = new LinkedList<>();
        nodes.add(treeNode);

        while (!nodes.isEmpty()) {
            List<Node<T>> level = new ArrayList<>(nodes.size());
            levels.add(level);

            for (Node<T> node : new ArrayList<>(nodes)) {
                level.add(node);
                if (node.getSmallChild() != null) {
                    nodes.add(node.getSmallChild());
                }
                if (node.getBigChild() != null) {
                    nodes.add(node.getBigChild());
                }
                nodes.poll();
            }
        }
        return levels;
    }
}
