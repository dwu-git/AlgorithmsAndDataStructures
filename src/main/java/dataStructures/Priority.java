package dataStructures;

import java.util.Arrays;

public class Priority {
    private int[] items = new int[5];
    private int count;
    private int front;
    private int rear;

    public void add(int item) {
        if (isEmpty()) {
            items[count++] = item;
            return;
        }

//        resize();

        for (int i = rear; i >= 0; i--) {
            if (items[i] <= item) {
                items[i + 1] = item;
                rear = (rear + 1) % items.length;
                count++;
                return;
            }
            i = (rear + 1) % items.length;
            items[i + 1] = items[i];
        }
    }

    private void resize() {
        if (count == items.length) {
            var resized = new int[items.length * 2];
            for (int i = 0; i < items.length; i++)
                resized[i] = items[i];

            items = resized;
        }
    }

    public int remove() {
        if (isEmpty())
            throw new IllegalStateException();

        var removed = items[front];

        items[front] = 0;
        front = (front + 1) % items.length;
        count--;

        return removed;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public String toString() {
        return Arrays.toString(items);
    }
}
