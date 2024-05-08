package dataStructures;

import java.util.Queue;
import java.util.Stack;

public class QueueReverser {
    public void reverse(Queue<Integer> queue, int k) {
        if (k > queue.size() || k < 0)
            throw new IllegalArgumentException();

        Stack<Integer> s = new Stack<>();

        for (int i = 0; i < k; i++)
            s.push(queue.remove());

        while (!s.isEmpty())
            queue.add(s.pop());

        for (int i = k; i < queue.size(); i++)
            queue.add(queue.remove());
    }
}
