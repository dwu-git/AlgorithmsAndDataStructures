package dataStructures;

import java.util.Arrays;
import java.util.Stack;

public class QueueUsingStack {
    Stack<Integer> stack1 = new Stack<>();
    Stack<Integer> stack2 = new Stack<>();

    public void add(int item) {
        stack1.add(item);
    }

    public int remove() {
        while (!stack1.isEmpty())
            stack2.push(stack1.pop());

        var removed = stack2.pop();

        while (!stack2.isEmpty())
            stack1.push(stack2.pop());

        return removed;
    }

    @Override
    public String toString() {
        return Arrays.toString(stack1.toArray());
    }
}
