package dataStructures;

public class Heap {
    private int[] heap = new int[10];
    private int rootIndex;
    private int count;

    public void insert(int value) {
        resize();
        heap[count] = value;
        bubbleUpIfNeeded(value);
        count++;
    }

    private void resize() {
        if (count == heap.length) {
            var newHeap = new int[heap.length * 2];
            for (int i = 0; i < heap.length; i++)
                newHeap[i] = heap[i];
            heap = newHeap;
        }
    }

    private void bubbleUpIfNeeded(int value) {
        var parentIndex = (count - 1) / 2;

        if (parentIndex < 0)
            return;

        var currentChildIndex = count;

        while (value > heap[parentIndex]) {
            heap[currentChildIndex] = heap[parentIndex];
            heap[parentIndex] = value;
            currentChildIndex = parentIndex;
            parentIndex = (parentIndex - 1) / 2;
        }
    }

    public int remove() {
        if (isEmptyHeap())
            throw new IllegalStateException();

        var removedNode = heap[rootIndex];

        heap[rootIndex] = heap[--count];
        heap[count] = 0;

        bubbleDownIfNeeded();

        return removedNode;
    }

    private boolean isEmptyHeap() {
        return count == 0;
    }

    private void bubbleDownIfNeeded() {
        swapWithLeftChild(rootIndex);
        swapWithRightChild(rootIndex);
    }

    private void swapWithLeftChild(int currentParentIndex) {
        swapWithLeftChildIfNodeHasTwoChildren(currentParentIndex);
        swapWithLeftChildIfNodeHasOnlyLeftChild(currentParentIndex);
    }

    private void swapWithLeftChildIfNodeHasTwoChildren(int currentParentIndex) {
        if (currentParentIndex * 2 + 1 < count
                && currentParentIndex * 2 + 2 < count) {

            var currentLeftChildIndex = currentParentIndex * 2 + 1;
            var currentLeftChild = heap[currentLeftChildIndex];
            var currentRightChild = heap[currentParentIndex * 2 + 2];

            while (heap[currentLeftChildIndex] > heap[currentParentIndex]
                    && currentLeftChild > currentRightChild) {
                heap[currentLeftChildIndex] = heap[currentParentIndex];
                heap[currentParentIndex] = currentLeftChild;

                currentParentIndex = currentLeftChildIndex;
                if (currentParentIndex * 2 + 1 < count
                        && currentParentIndex * 2 + 2 < count && currentParentIndex * 2 + 1 < heap.length && currentParentIndex * 2 + 2 < heap.length) {
                    currentLeftChildIndex = currentParentIndex * 2 + 1;
                    currentLeftChild = heap[currentLeftChildIndex];
                    currentRightChild = heap[currentParentIndex * 2 + 2];
                }
                swapWithRightChild(currentParentIndex);
            }
        }
    }

    private void swapWithLeftChildIfNodeHasOnlyLeftChild(int currentParentIndex) {
        if (currentParentIndex * 2 + 1 < count) {
            var currentLeftChildIndex = currentParentIndex * 2 + 1;
            var currentLeftChild = heap[currentLeftChildIndex];

            while (heap[currentLeftChildIndex] > heap[currentParentIndex]) {
                heap[currentLeftChildIndex] = heap[currentParentIndex];
                heap[currentParentIndex] = currentLeftChild;

                currentParentIndex = currentLeftChildIndex;
                if (currentParentIndex * 2 + 1 < count && currentParentIndex * 2 + 1 < heap.length) {
                    currentLeftChildIndex = currentParentIndex * 2 + 1;
                    currentLeftChild = heap[currentLeftChildIndex];
                }
                swapWithRightChild(currentParentIndex);
            }
        }
    }

    private void swapWithRightChild(int currentParentIndex) {
        swapWithRightChildIfNodeHasTwoChildren(currentParentIndex);
        swapWithRightChildIfNodeHasOnlyRightChild(currentParentIndex);
    }

    private void swapWithRightChildIfNodeHasTwoChildren(int currentParentIndex) {
        if (currentParentIndex * 2 + 1 < count
                && currentParentIndex * 2 + 2 < count && currentParentIndex * 2 + 1 < heap.length && currentParentIndex * 2 + 2 < heap.length) {

            var currentRightChildIndex = currentParentIndex * 2 + 2;
            var currentRightChild = heap[currentRightChildIndex];
            var currentLeftChild = heap[currentParentIndex * 2 + 1];

            while (heap[currentRightChildIndex] > heap[currentParentIndex]
                    && currentRightChild > currentLeftChild) {
                heap[currentRightChildIndex] = heap[currentParentIndex];
                heap[currentParentIndex] = currentRightChild;

                currentParentIndex = currentRightChildIndex;
                if (currentParentIndex * 2 + 1 < count
                        && currentParentIndex * 2 + 2 < count && currentParentIndex * 2 + 1 < heap.length && currentParentIndex * 2 + 2 < heap.length) {
                    currentRightChildIndex = currentParentIndex * 2 + 2;
                    currentRightChild = heap[currentRightChildIndex];
                    currentLeftChild = heap[currentParentIndex * 2 + 1];
                }
                swapWithLeftChild(currentParentIndex);
            }
        }
    }

    private void swapWithRightChildIfNodeHasOnlyRightChild(int currentParentIndex) {
        if (currentParentIndex * 2 + 2 < count) {

            var currentRightChildIndex = currentParentIndex * 2 + 2;
            var currentRightChild = heap[currentRightChildIndex];

            while (heap[currentRightChildIndex] > heap[currentParentIndex]) {
                heap[currentRightChildIndex] = heap[currentParentIndex];
                heap[currentParentIndex] = currentRightChild;

                currentParentIndex = currentRightChildIndex;
                if (currentParentIndex * 2 + 2 < count && currentParentIndex * 2 + 2 < heap.length) {
                    currentRightChildIndex = currentParentIndex * 2 + 2;
                    currentRightChild = heap[currentRightChildIndex];
                }
                swapWithLeftChild(currentParentIndex);
            }
        }
    }

    public static void heapify3(int[] array) {
        for (var i = array.length / 2 - 1; i >= 0; i--)
            heapify3(array, i);
    }

    private static void heapify3(int[] array, int index) {
        var largerIndex = index;

        var leftIndex = index * 2 + 1;
        if (leftIndex < array.length &&
                array[leftIndex] > array[largerIndex])
            largerIndex = leftIndex;

        var rightIndex = index * 2 + 2;
        if (rightIndex < array.length &&
                array[rightIndex] > array[largerIndex])
            largerIndex = rightIndex;

        if (index == largerIndex)
            return;

        swap3(array, index, largerIndex);
        heapify3(array, largerIndex);
    }

    private static void swap3(int[] array, int first, int second) {
        var temp = array[first];
        array[first] = array[second];
        array[second] = temp;
    }

    public void getKthLargest(int k, int[] array) {
        if (k < 1 || k > array.length)
            throw new IllegalArgumentException();

        var heap = new Heap();
        for (var number : array)
            heap.insert(number);

        for (int i = 0; i < k - 1; i++)
            heap.remove();

        System.out.println(heap.heap[rootIndex]);
    }

    public static int[] myHeapify2(int[] array) {
        if (array.length == 0)
            throw new IllegalStateException();


        for (int i = array.length / 2 - 1; i >= 0; i--) {
            if (!isValidNode2(i, array)) {
                var temp = array[i];
                var largerIndex = largerIndex2(i, array);
                array[i] = array[largerIndex];
                array[largerIndex] = temp;

                myHeapify2(array);
            }
        }
        return array;
    }

    private static boolean isValidNode2(int parentIndex, int[] array) {
        if (parentIndex * 2 + 2 < array.length)
            return array[parentIndex] >= array[parentIndex * 2 + 2]
                    && array[parentIndex] >= array[parentIndex * 2 + 1];
        else if (parentIndex * 2 + 1 < array.length)
            return array[parentIndex] >= array[parentIndex * 2 + 1];
        else if (parentIndex * 2 + 1 >= array.length
                || parentIndex * 2 + 2 >= array.length)
            return true;

        return false;
    }

    private static int largerIndex2(int parentIndex, int[] array) {
        if (parentIndex * 2 + 2 < array.length
                && array[parentIndex * 2 + 2] > array[parentIndex * 2 + 1])
            return parentIndex * 2 + 2;
        else if (parentIndex * 2 + 1 < array.length
                && array[parentIndex * 2 + 1] > array[parentIndex])
            return parentIndex * 2 + 1;

        return parentIndex;
    }

    public static int[] heapify(int[] array) {
        if (array.length == 0)
            throw new IllegalStateException();

        var lastParentIndex = array.length / 2 - 1;
        for (var i = lastParentIndex; i >= 0; i--) {
            if (!isValidNode(i, array)) {
                var temp = array[i];
                var largerChildIndex = largerChildIndex(i, array);
                array[i] = array[largerChildIndex];
                array[largerChildIndex] = temp;
                heapify(array);
            }
        }
        return array;
    }

    private static boolean isValidNode(int parentIndex, int[] array) {
        if (parentIndex * 2 + 1 >= array.length
                && parentIndex * 2 + 2 >= array.length)
            return true;
        else if (parentIndex * 2 + 1 < array.length
                && parentIndex * 2 + 2 >= array.length)
            return array[parentIndex] >= array[parentIndex * 2 + 1];
        else if (parentIndex * 2 + 1 >= array.length
                && parentIndex * 2 + 2 < array.length)
            return array[parentIndex] >= array[parentIndex * 2 + 2];
        else if (parentIndex * 2 + 1 < array.length
                && parentIndex * 2 + 2 < array.length)
            return array[parentIndex] >= array[parentIndex * 2 + 1]
                    && array[parentIndex] >= array[parentIndex * 2 + 2];

        return false;
    }

    private static int largerChildIndex(int parentIndex, int[] array) {
        if (parentIndex * 2 + 1 >= array.length
                && parentIndex * 2 + 2 < array.length) {
            return parentIndex * 2 + 2;
        } else if (parentIndex * 2 + 1 < array.length
                && parentIndex * 2 + 2 >= array.length) {
            return parentIndex * 2 + 1;
        } else if (parentIndex * 2 + 1 >= array.length
                && parentIndex * 2 + 2 >= array.length) {
            return parentIndex;
        } else if (parentIndex * 2 + 1 < array.length
                && parentIndex * 2 + 2 < array.length) {
            if (array[parentIndex * 2 + 1] > array[parentIndex * 2 + 2]) {
                return parentIndex * 2 + 1;
            } else {
                return parentIndex * 2 + 2;
            }
        }
        return -1;
    }

    public static boolean isMaxHeap(int[] array) {
        var parentIndex = 0;
        while (parentIndex < array.length) {
            if (!isMaxHeap(array, parentIndex))
                return false;

            parentIndex++;
        }
        return true;
    }

    private static boolean isMaxHeap(int[] array, int parentIndex) {
        if (array.length == 0)
            throw new IllegalStateException();

        var leftChildIndex = parentIndex * 2 + 1;
        var rightChildIndex = parentIndex * 2 + 2;

        if (rightChildIndex < array.length)
            return array[parentIndex] >= array[leftChildIndex]
                    && array[parentIndex] >= array[rightChildIndex];
        else if (leftChildIndex < array.length)
            return array[parentIndex] >= array[leftChildIndex];
        else if (rightChildIndex >= array.length && leftChildIndex >= array.length)
            return true;

        return false;
    }
}
