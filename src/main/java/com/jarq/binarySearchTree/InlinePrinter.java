package com.jarq.binarySearchTree;

public class InlinePrinter<T extends Comparable<T>> implements BinarySearchTreePrinter {

    // inspired by: https://www.geeksforgeeks.org/level-order-tree-traversal/

    // prints nodes inline

    final private BinarySearchTree<T> tree;

    public InlinePrinter(BinarySearchTree<T> tree) {
        this.tree = tree;
    }

    @Override
    public void print() {
        Node<T> node = tree.getRoot();
        int height = getTreeHeight(node);
        for (int i=1; i<=height; i++) {
            printLevel(node, i);
        }
    }

    private int getTreeHeight(Node<T> node) {
        if (node == null) {
            return 0;
        } else {
            int leftSubTreeLvl = getTreeHeight(node.getSmallChild());
            int rightSubTreeLvl = getTreeHeight(node.getBigChild());

            return leftSubTreeLvl > rightSubTreeLvl ? leftSubTreeLvl+1 : rightSubTreeLvl+1;
        }
    }

    private void printLevel(Node<T> node, int level) {
        if (node != null) {
            if (level == 1) {
                System.out.print(node.getData() + " ");
            }
            else if (level > 1) {
                printLevel(node.getSmallChild(), level - 1);
                printLevel(node.getBigChild(), level - 1);
            }
        }
    }
}
