package dataStructures;

import java.util.Arrays;

public class ArrayDeque {
    private int[] items;
    private int capacity;
    private int count;
    private int front;
    private int rear;

    public ArrayDeque(int capacity) {
        this.items = new int[capacity];
    }

    public void enqueue(int item) {
        if (count == items.length)
            throw new IllegalStateException();

        items[rear] = item;
        rear = (rear + 1) % items.length;
        count++;
    }

    public int dequeue() {
        if (isEmpty())
            throw new IllegalStateException();

        var removed = items[front];
        items[front] = 0;
        front = (front + 1) % items.length;
        count--;
        return removed;
    }

    public int peek() {
        if (isEmpty())
            return 0;

        return items[front];
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public boolean isFull() {
        return count == items.length;
    }

    @Override
    public String toString() {
        return Arrays.toString(items);
    }
}
