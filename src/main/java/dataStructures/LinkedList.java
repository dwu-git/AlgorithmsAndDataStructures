package dataStructures;

import java.util.NoSuchElementException;

public class LinkedList {
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
    private int size = 0;

    private boolean isEmpty() {
        return first == null;
    }

    public void addLast(int item) {
        var node = new Node(item);

        if (isEmpty()) {
            first = last = node;
        } else {
            last.next = node;
            last = node;
        }
        size++;
    }

    public void addFirst(int item) {
        var node = new Node(item);

        if (isEmpty()) {
            first = last = node;
        } else {
            node.next = first;
            first = node;
        }
        size++;
    }

    public int indexOf(int item) {
        int index = 0;
        var current = first;
        while (current != null) {
            if (current.value == item)
                return index;
            current = current.next;
            index++;
        }
        return -1;
    }

    public boolean contains(int item) {
        return indexOf(item) != -1;
    }

    public void removeFirst() {
        if (isEmpty())
            throw new NoSuchElementException();

        if (first == last)
            first = last = null;
        else {
            var second = first.next;
            first.next = null;
            first = second;
        }
        size--;
    }

    public void removeLast() {
        if (isEmpty())
            throw new NoSuchElementException();

        if (first == last)
            first = last = null;
        else {
//            last = last.previous;
//            last.next = null;
            var current = first;
            while (current != last) {
                current = current.next;
                if (current.next == last) {
                    current.next = null;
                    last = current;
                }
            }
        }
        size--;
    }

    public int size() {
        return size;
    }

    public int[] toArray() {
        int[] array = new int[size];
        var current = first;
        var index = 0;
//        if (isEmpty())
//            throw new NoSuchElementException();

        while (current != null) {
            array[index++] = current.value;
            current = current.next;
        }
        return array;
    }

//    private void updateNextLink() {
//        var current = last;
//        while (true) {
//            if (current.previous == null) {
//                current.next = null;
//                break;
//            }
//            current.next = current.previous;
//            current = current.previous;
//        }
//    }
//    private void updatePreviousLink() {
//        first.previous = null;
//        var current = first;
//
//        while (current.next != null) {
//            current.next.previous = current;
//            current = current.next;
//        }
//    }
//    public void reverse() {
//        var firsReplica = first;
//        if (isEmpty())
//            throw new NoSuchElementException();
//
//        updateNextLink();
//
//        first = last;
//        last = firsReplica;
//
//        updatePreviousLink();
//    }

    @Override
    public String toString() {
        return "LinkedList{" +
                "first=" + first +
                ", last=" + last +
                ", size=" + size +
                '}';
    }

    public void reverse22() {
        if (isEmpty()) return;

        var previous = first;
        var current = first.next;
        while (current != null) {
            var next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }

        last = first;
        last.next = null;
        first = previous;
    }

    public int getKthFromTheEnd(int k) {
        if (isEmpty())
            throw new IllegalStateException();

        var pointer1 = first;
        var pointer2 = first;


        for (int i = 0; i < k - 1; i++) {
            pointer2 = pointer2.next;
            if (pointer2 == null)
                throw new IllegalArgumentException();
        }

        while (pointer2 != last) {
            pointer2 = pointer2.next;
            pointer1 = pointer1.next;
        }
        return pointer1.value;
    }

    public void printMiddle() {
        var a = first;
        var b = first;

        if (isEmpty()) {
            throw new IllegalStateException();
        }

        while (b != last && b.next != last) {
            b = b.next.next;
            a = a.next;
        }

        if (b == last)
            System.out.println(a.value);
        else
            System.out.println(a.value + ", " + a.next.value);
    }

    public void addLoop() {
        if (isEmpty())
            throw new IllegalStateException();

        if (last == first) {
            last.next = first;
            return;
        }

        if (first.next.next == null) {
            last.next = last;
            return;
        }

        last.next = first.next.next;
    }

    public boolean hasLoop() {
        var fast = first;
        var slow = first;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            if (slow == fast)
                return true;
        }
        return false;
    }
}

