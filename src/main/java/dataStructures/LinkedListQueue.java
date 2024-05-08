package dataStructures;

import java.util.ArrayList;

public class LinkedListQueue {
    private class Node {
        private int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", next=" + next +
                    '}';
        }
    }

    private Node first;
    private Node last;
    private int size;

    // O(1)
    public void enqueue(int item) {
        var node = new Node(item);

        if (isEmpty()) {
            first = last = node;
        } else if (first == last) {
            first.next = node;
            last = node;
        } else {
            last.next = node;
            last = node;
        }
        size++;
    }

    // O(1)
    public Node dequeue() {
        if (isEmpty())
            throw new IllegalStateException();

        var removed = first;
        var second = first.next;

        if (first == last) {
            first = last = null;
        } else {
            first.next = null;
            first = second;
        }
        size--;
        return removed;
    }

    // O(1)
    public Node peek() {
        if (isEmpty())
            throw new IllegalStateException();

        return first;
    }

    // O(1)
    public int size() {
        return size;
    }

    // O(1)
    public boolean isEmpty() {
        return first == null;
    }

    // O(n)
    public String toString() {
        ArrayList<Integer> list = new ArrayList<>();
        var current = first;

        while (current != null) {
            list.add(current.value);
            current = current.next;
        }
        return list.toString();
    }
}
