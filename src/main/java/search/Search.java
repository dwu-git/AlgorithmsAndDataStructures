package search;

public class Search {

    // Linear Search--------------------------------------------------
    public static int linearSearch(int[] array, int target) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == target)
                return i;
        }
        return -1;
    }
    // Linear Search--------------------------------------------------

    //--------------------------Binary Search --------------------------------
    public static int binarySearchRec(int[] array, int target) {
        if (array.length == 0)
            return -1;

        sort(array);
        return binarySearchRec(array, 0, array.length - 1, target);
//        return binarySearchIterative(array, target);
    }

    public static int binarySearchIterative(int[] array, int target) {
        int left = 0;
        int right = array.length - 1;

        while (left <= right) {
            var middleIndex = (left + right) / 2;

            if (array[middleIndex] == target)
                return middleIndex;

            if (array[middleIndex] > target)
                right = middleIndex - 1;
            else
                left = middleIndex + 1;
        }

        return -1;
    }

    private static int binarySearchRec(int[] array, int left, int right, int target) {
        if (left > right)
            return -1;

        var middleIndex = (left + right) / 2;

        if (array[middleIndex] == target)
            return middleIndex;
        else if (array[middleIndex] > target)
            return binarySearchRec(array, left, middleIndex - 1, target);
        else
            return binarySearchRec(array, middleIndex + 1, array.length - 1, target);
    }

    private static void sort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    private static void quickSort(int[] array, int start, int end) {
        if (start >= end)
            return;

        var boundaryIndex = partition(array, start, end);

        quickSort(array, start, boundaryIndex - 1);
        quickSort(array, boundaryIndex + 1, end);
    }

    private static int partition(int[] array, int start, int end) {
        var pivot = array[end];
        var boundaryIndex = start - 1;

        for (int i = start; i <= end; i++)
            if (array[i] <= pivot)
                swap(array, ++boundaryIndex, i);

        return boundaryIndex;
    }

    private static void swap(int[] array, int index1, int index2) {
        var temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }
    //--------------------------Binary Search --------------------------------

    //--------------------------Ternary Search --------------------------------
    public static int ternarySearch(int[] array, int target) {
        sort(array);
        return ternarySearch(array, target, 0, array.length - 1);
    }

    private static int ternarySearch(int[] array, int target, int left, int right) {
        if (left > right)
            return -1;

        var partitionSize = (right - left) / 3;
        var mid1 = left + partitionSize;
        var mid2 = right - partitionSize;

        if (array[mid1] == target)
            return mid1;

        if (array[mid2] == target)
            return mid2;

        if (array[mid2] < target)
            return ternarySearch(array, target, mid2 + 1, right);

        if (array[mid1] > target)
            return ternarySearch(array, target, left, mid1 - 1);

        return ternarySearch(array, target, mid1 + 1, mid2 - 1);
    }
    //--------------------------Ternary Search --------------------------------

    //--------------------------Jump Search -----------------------------------
    public static int jumpSearch(int[] array, int target) {
        var blockSize = (int) Math.sqrt(array.length - 1);
        var start = 0;
        var next = blockSize;

        while (start < array.length && array[next - 1] < target) {
            start = next;
            next += blockSize;
            if (next > array.length)
                next = array.length;
        }

        for (int i = start; i < next; i++)
            if (array[i] == target)
                return i;

        return -1;
    }
    //--------------------------Jump Search -----------------------------------

    //--------------------------Exponential Search ----------------------------

    public static int exponentialSearch(int[] array, int target) {
        var bound = 1;
        while (bound < array.length && array[bound] < target)
            bound *= 2;

        int left = bound / 2;
        int right = Math.min(bound, array.length - 1);
        return binarySearchRec(array, left, right, target);
    }
}
