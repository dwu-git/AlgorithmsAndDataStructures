package dataStructures;

import java.util.ArrayDeque;
import java.util.Queue;

public class StackWithTwoQueues {
    Queue<Integer> q1 = new ArrayDeque<>();
    Queue<Integer> q2 = new ArrayDeque<>();
    private int top;

    // O(1)
    public void push(int item) {
        q1.add(item);
        top = item;
    }

    // O(n)
    public int pop() {
        if (isEmpty())
            throw new IllegalStateException();

        while (q1.size() > 1) {
            top = q1.remove();
            q2.add(top);
        }

        var q1Copy = q1;
        q1 = q2;
        q2 = q1Copy;

        return q2.remove();
    }

    // O(1)
    public int peek() {
        if (isEmpty())
            throw new IllegalStateException();

        return top;
    }

    // O(1)
    public int size() {
        return q1.size();
    }

    // O(1)
    public boolean isEmpty() {
        return q1.isEmpty();
    }

    @Override
    public String toString() {
        return q1.toString();
    }
}
