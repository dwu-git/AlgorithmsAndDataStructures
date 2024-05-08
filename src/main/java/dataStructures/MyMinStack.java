package dataStructures;

import java.util.Arrays;

public class MyMinStack {
    private int[] items = new int[4];
    private int count;
    private int min;

    public void push(int item) {
        if (count == items.length)
            throw new StackOverflowError();

        if (count == 0)
            min = item;

        if (item < min)
            min = item;

        items[count++] = item;
    }

    public int pop() {
        if (count == 0)
            throw new IllegalStateException();

        if (min == items[count - 1]) {
            min = items[0];
            for (int i = 1; i < count - 1; i++) {
                if (items[i] < min)
                    min = items[i];
            }
        }
        return items[--count];
    }

    public int min() {
        if (count == 0)
            throw new IllegalStateException();

        return min;
    }

    @Override
    public String toString() {
        var content = Arrays.copyOfRange(items, 0, count);
        return Arrays.toString(content);
    }
}
