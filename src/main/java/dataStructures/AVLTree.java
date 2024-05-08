package dataStructures;

public class AVLTree {
    private class AVLNode {
        private int value;
        private AVLNode leftChild;
        private AVLNode rightChild;
        private int height;

        public AVLNode(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "AVLNode{" +
                    "value=" + value +
                    ", leftChild=" + leftChild +
                    ", rightChild=" + rightChild +
                    '}';
        }
    }

    private AVLNode root;

    public void insert(int value) {
        root = insert(root, value);
    }

    public int height() {
        if (root == null)
            throw new IllegalStateException();

        return root.height;
    }

    private AVLNode insert(AVLNode root, int value) {
        if (root == null)
            return new AVLNode(value);


        if (root.value > value)
            root.leftChild = insert(root.leftChild, value);
        else
            root.rightChild = insert(root.rightChild, value);

        root.height = Math.max(height(root.leftChild), height(root.rightChild)) + 1;

        return balanceNode(root);
    }

    private void resetHeight(AVLNode root, AVLNode newRoot) {
        root.height = Math.max(height(root.leftChild), height(root.rightChild)) + 1;
        newRoot.height = Math.max(height(newRoot.leftChild), height(newRoot.rightChild)) + 1;
    }

    private AVLNode balanceNode(AVLNode node) {
        if (isLeftHeavyNode(node))
            return balanceLeftHeavyNode(node);
        else if (isRightHeavyNode(node))
            return balanceRightHeavyNode(node);

        return node;
    }

    private boolean isLeftHeavyNode(AVLNode node) {
        return balanceFactor(node) > 1;
    }

    private boolean isRightHeavyNode(AVLNode node) {
        return balanceFactor(node) < -1;
    }

    private int balanceFactor(AVLNode node) {
        return (node == null) ? 0 : height(node.leftChild) - height(node.rightChild);
    }

    private int height(AVLNode node) {
        return (node == null) ? -1 : node.height;
    }

    private AVLNode balanceLeftHeavyNode(AVLNode node) {
        if (balanceFactor(node.leftChild) > 0)
            return rightRotate(node);
        else if (balanceFactor(node.leftChild) < 0) {
            node.leftChild = leftRotate(node.leftChild);
            return rightRotate(node);
        }

        return node;
    }

    private AVLNode balanceRightHeavyNode(AVLNode node) {
        if (balanceFactor(node.rightChild) < 0)
            return leftRotate(node);
        else if (balanceFactor(node.rightChild) > 0) {
            node.rightChild = rightRotate(node.rightChild);
            return leftRotate(node);
        }

        return node;
    }

    private AVLNode leftRotate(AVLNode node) {
        var newRoot = node.rightChild;
        node.rightChild = newRoot.leftChild;
        newRoot.leftChild = node;

        resetHeight(node, newRoot);

        return newRoot;
    }

    private AVLNode rightRotate(AVLNode node) {
        var newRoot = node.leftChild;
        node.leftChild = newRoot.rightChild;
        newRoot.rightChild = node;

        resetHeight(node, newRoot);

        return newRoot;
    }

    public boolean isBalanced() {
        return isBalanced(root);
    }

    private boolean isBalanced(AVLNode node) {
        if (node == null)
            return true;

        var balanceFactor = node.leftChild.height - node.rightChild.height;

        return Math.abs(balanceFactor) <= 1 &&
                isBalanced(node.leftChild) &&
                isBalanced(node.rightChild);
    }

    private boolean isLeaf(AVLNode root) {
        return root.leftChild == null && root.rightChild == null;
    }

    private int size(AVLNode root) {
        if (root == null)
            return 0;

        if (isLeaf(root))
            return 1;

        return 1 + size(root.leftChild) + size(root.rightChild);
    }

    public boolean isPerfect() {
        return size(root) == Math.pow(2, height() + 1) - 1;
    }
}
