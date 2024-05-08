package sorting;

public class MergeSort {
    public static void sort(int[] array) {
        if (array.length < 2)
            return;

        var middleElementIndex = array.length / 2;

        int[] leftArray = new int[middleElementIndex];
        for (int i = 0; i < leftArray.length; i++)
            leftArray[i] = array[i];

        int[] rightArray = new int[array.length - middleElementIndex];
        for (int i = middleElementIndex; i < array.length; i++)
            rightArray[i - middleElementIndex] = array[i];

        sort(leftArray);
        sort(rightArray);

        merge(leftArray, rightArray, array);
    }

    private static void merge(int[] left, int[] right, int[] result) {
        int i = 0, j = 0, k = 0;

        while (i < left.length && j < right.length) {
            if (left[i] <= right[j])
                result[k++] = left[i++];
            else
                result[k++] = right[j++];
        }

        while (i < left.length)
            result[k++] = left[i++];

        while (j < right.length)
            result[k++] = right[j++];
    }
}
