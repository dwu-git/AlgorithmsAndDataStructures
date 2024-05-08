package dataStructures;

public class MinHeap {
    private class Node {
        private int key;
        private String value;

        public Node(int key, String value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "key=" + key +
                    ", value='" + value + '\'' +
                    '}';
        }
    }

    private Node[] nodes = new Node[10];
    private int count;
    private int rootIndex;

    public void insert(int key, String value) {
        resize();
        var node = new Node(key, value);
        nodes[count] = node;
        bubbleUpNode(node);
        count++;
    }

    private void resize() {
        if (count == nodes.length) {
            var newNodes = new Node[nodes.length * 2];
            for (int i = 0; i < nodes.length; i++)
                newNodes[i] = nodes[i];

            nodes = newNodes;
        }
    }

    private void bubbleUpNode(Node node) {
        var parentIndex = (count - 1) / 2;
        var childIndex = count;

        while (parentIndex >= 0 && node.key < nodes[parentIndex].key) {
            swapNodes(childIndex, parentIndex);
            childIndex = parentIndex;
            parentIndex = (parentIndex - 1) / 2;
        }
    }

    private void swapNodes(int childIndex, int parentIndex) {
        var temp = nodes[childIndex];
        nodes[childIndex] = nodes[parentIndex];
        nodes[parentIndex] = temp;
    }

    private boolean isEmptyHeap() {
        return count == 0;
    }

    public void remove() {
        if (isEmptyHeap())
            throw new IllegalStateException();

        nodes[rootIndex] = nodes[--count];
        nodes[count] = null;

        bubbleDownNode(rootIndex);
    }

    private int indexOfSmallerNode(int parentIndex) {
        var leftChildIndex = parentIndex * 2 + 1;
        var rightChildIndex = parentIndex * 2 + 2;

        if (rightChildIndex < count
                && nodes[leftChildIndex].key < nodes[rightChildIndex].key)
            return leftChildIndex;
        else if (rightChildIndex < count
                && nodes[leftChildIndex].key > nodes[rightChildIndex].key)
            return rightChildIndex;

        return parentIndex;
    }

    private void swapWhenNodeHasTwoChildren(int parentIndex) {
        var indexOfSmallerNode = indexOfSmallerNode(parentIndex);
        var rightChildIndex = parentIndex * 2 + 2;

        if (rightChildIndex >= count)
            return;

        if (nodes[indexOfSmallerNode].key < nodes[parentIndex].key) {

            var tempNode = nodes[indexOfSmallerNode];

            nodes[indexOfSmallerNode] = nodes[parentIndex];
            nodes[parentIndex] = tempNode;

            parentIndex = indexOfSmallerNode;
            swapWhenNodeHasTwoChildren(parentIndex);
        }
        swapWhenNodeHasOnlyLeftChild(parentIndex);
    }

    private void swapWhenNodeHasOnlyLeftChild(int parentIndex) {
        var leftChildIndex = parentIndex * 2 + 1;

        if (leftChildIndex >= count)
            return;

        if (nodes[leftChildIndex].key < nodes[parentIndex].key) {

            var tempNode = nodes[leftChildIndex];

            nodes[leftChildIndex] = nodes[parentIndex];
            nodes[parentIndex] = tempNode;
        }
    }

    private void bubbleDownNode(int parentIndex) {
        swapWhenNodeHasTwoChildren(parentIndex);
        swapWhenNodeHasOnlyLeftChild(parentIndex);
    }
}
