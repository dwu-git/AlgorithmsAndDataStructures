package dataStructures;

import java.util.Arrays;

public class Stack {
    private int[] items = new int[5];
    private int count1;
    private int count2 = items.length;

    public void push1(int item) {
        if (isFull1())
            throw new StackOverflowError();

        items[count1++] = item;
    }

    public void push2(int item) {
        if (isFull2())
            throw new StackOverflowError();

        items[--count2] = item;
    }

    public int pop1() {
        if (isEmpty1())
            throw new IllegalStateException();

        var removedItem = items[count1 - 1];
        items[--count1] = 0;
        return removedItem;
    }

    public int pop2() {
        if (isEmpty2())
            throw new IllegalStateException();

        var removedItem = items[count2];
        items[count2++] = 0;
        return removedItem;
    }

    public int peek1() {
        if (isEmpty1())
            throw new IllegalStateException();

        return items[count1 - 1];
    }

    public int peek2() {
        if (isEmpty2())
            throw new IllegalStateException();

        return items[count2];
    }

    public boolean isEmpty1() {
        return count1 == 0;
    }

    public boolean isEmpty2() {
        return count2 == items.length;
    }

    public boolean isFull1() {
        return count1 == count2;
    }

    public boolean isFull2() {
        return count2 == count1;
    }

    public String toString() {
        return Arrays.toString(items);
    }
}
