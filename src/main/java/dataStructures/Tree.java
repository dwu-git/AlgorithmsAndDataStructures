package dataStructures;

import java.util.ArrayList;
import java.util.List;

public class Tree {

    private class Node {
        private int value;
        private Node leftChild;
        private Node rightChild;
        private int height;

        public Node(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node= " + value + "";
        }
    }

    private Node root;

    public void insert2(int value) {
        var node = new Node(value);

        if (root == null) {
            root = node;
            return;
        }

        var current = root;
        while (true) {
            if (value < current.value) {
                if (current.leftChild == null) {
                    current.leftChild = node;
                    break;
                }
                current = current.leftChild;
            } else {
                if (current.rightChild == null) {
                    current.rightChild = node;
                    break;
                }
                current = current.rightChild;
            }
        }
    }

    public boolean find(int value) {
        var current = root;

        while (current != null) {
            if (value > current.value)
                current = current.rightChild;
            else if (value < current.value)
                current = current.leftChild;
            else
                return true;
        }
        return false;
    }

    public void traversePreOrder() {
        traversePreOrder(root);
    }

    public void traverseInOrder() {
        traverseInOrder(root);
    }

    public void traversePostOrder() {
        traversePostOrder(root);
    }

    private void traversePreOrder(Node root) {
        if (root == null)
            return;

        System.out.println(root.value);
        traversePreOrder(root.leftChild);
        traversePreOrder(root.rightChild);
    }

    private void traverseInOrder(Node root) {
        if (root == null)
            return;

        traverseInOrder(root.leftChild);
        System.out.println(root.value);
        traverseInOrder(root.rightChild);
    }

    private void traversePostOrder(Node root) {
        if (root == null)
            return;

        traversePostOrder(root.leftChild);
        traversePostOrder(root.rightChild);
        System.out.println(root.value);
    }

    public void traverseLevelOrder() {
        for (var i = 0; i <= height(); i++) {
            for (var value : getListOfNodesAtDistance(i))
                System.out.println(value);
        }
    }

    public int min() { // Binary SEARCH Tree  O(log n);
        if (root == null)
            throw new IllegalStateException();

        var current = root;
        var last = current;
        while (current != null) {
            last = current;
            current = current.leftChild;
        }
        return last.value;
        // return min(root); Binary Tree
    }

    private int min(Node root) {
        if (isLeaf(root)) {
            return root.value;
        }

        var left = min(root.leftChild);
        var right = min(root.rightChild);

        return Math.min(Math.min(left, right), root.value);
    }

    private boolean isLeaf(Node root) {
        return root.leftChild == null && root.rightChild == null;
    }

    public boolean equals(Tree other) {
        if (other == null)
            return false;

        return equals(root, other.root);
    }

    private boolean equals(Node first, Node second) {
        if (first == null && second == null)
            return true;

        if (first != null && second != null)
            return first.value == second.value
                    && equals(first.leftChild, second.leftChild)
                    && equals(first.rightChild, second.rightChild);

        return false;
    }

    public void swapRoot() {
        var temp = root.leftChild;
        root.leftChild = root.rightChild;
        root.rightChild = temp;
    }

    public boolean isBinarySearchTree() {
        return isBinarySearchLeftSubTree(root) && isBinarySearchRightSubTree(root);
    }

    private boolean isBinarySearchLeftSubTree(Node root) {
        if (root == null
                || root.leftChild == null
                || root.leftChild.leftChild == null
                || root.leftChild.rightChild == null)
            return true;

        return root.leftChild.value < root.value
                && root.leftChild.value < root.leftChild.rightChild.value
                && root.leftChild.rightChild.value < root.value
                && isBinarySearchLeftSubTree(root.leftChild);
    }

    private boolean isBinarySearchRightSubTree(Node root) {
        if (root == null
                || root.rightChild == null
                || root.rightChild.rightChild == null
                || root.rightChild.leftChild == null)
            return true;

        return root.rightChild.value > root.value
                && root.rightChild.value > root.rightChild.leftChild.value
                && root.rightChild.leftChild.value > root.value
                && isBinarySearchRightSubTree(root.rightChild);
    }

    public ArrayList<Integer> getListOfNodesAtDistance(int k) {
        var list = new ArrayList<Integer>();
        getListOfNodesAtDistance(root, k, list);
        return list;
    }

    private void getListOfNodesAtDistance(Node root, int k, ArrayList<Integer> list) {
        if (root == null)
            return;

        if (k == 0) {
            list.add(root.value);
            return;
        }

        getListOfNodesAtDistance(root.leftChild, k - 1, list);
        getListOfNodesAtDistance(root.rightChild, k - 1, list);

    }

    public int size() {
        return size(root);
    }

    private int size(Node root) {
        if (root == null)
            return 0;

        if (isLeaf(root))
            return 1;

        return 1 + size(root.leftChild) + size(root.rightChild);
    }

    public int countLeaves() {
        return countLeaves(root);
    }

    private int countLeaves(Node root) {
        if (root == null)
            return 0;

        if (isLeaf(root))
            return 1;

        return countLeaves(root.leftChild) + countLeaves(root.rightChild);
    }

    public int max() {
        return max(root);
    }

    private int max(Node root) {
        if (root == null)
            throw new IllegalStateException();

        if (root.rightChild == null)
            return root.value;

        return max(root.rightChild);
    }

    public boolean contains(int value) {
        return contains(value, root);
    }

    private boolean contains(int value, Node root) {
        if (root == null)
            return false;

        if (root.value == value)
            return true;

        return contains(value, root.leftChild) || contains(value, root.rightChild);
    }

    public boolean areSiblings(int value1, int value2) {
        return areSiblings(root, value1, value2);
    }

    private boolean areSiblings(Node root, int value1, int value2) {
        if (root == null || root.rightChild == null || root.leftChild == null)
            return false;

        if (areSiblings1(root, value1, value2) || (areSiblings2(root, value1, value2)))
            return true;

        return areSiblings(root.leftChild, value1, value2) ||
                areSiblings(root.rightChild, value1, value2);
    }

    private boolean areSiblings1(Node root, int value1, int value2) {
        return root.leftChild.value == value1 && root.rightChild.value == value2;
    }

    private boolean areSiblings2(Node root, int value1, int value2) {
        return root.leftChild.value == value2 && root.rightChild.value == value1;
    }

    public List<Integer> getAncestors(int value) {
        var list = new ArrayList<Integer>();
        getAncestors(root, value, list);
        return list;
    }

    private boolean getAncestors(Node root, int value, List<Integer> list) {
        if (root == null)
            return false;

        if (root.value == value)
            return true;

        if (getAncestors(root.leftChild, value, list) ||
                getAncestors(root.rightChild, value, list)) {
            list.add(root.value);
            return true;
        }

        return false;
    }

    public int height() {
        return height(root);
    }

    private int height(Node root) {
        if (root == null)
            return -1;

        if (isLeaf(root))
            return 0;

        return 1 + Math.max(
                height(root.leftChild),
                height(root.rightChild));
    }

    public boolean isBalanced2() {
        return isBalanced(root);
    }

    private boolean isBalanced(Node node) {
        if (node == null)
            return true;

        var balanceFactor = height(node.leftChild) - height(node.rightChild);

        return Math.abs(balanceFactor) <= 1 &&
                isBalanced(node.leftChild) &&
                isBalanced(node.rightChild);
    }

    public boolean isPerfect2() {
        return size(root) == Math.pow(2, height() + 1) - 1;
    }
}
