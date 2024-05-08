package sorting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BucketSort {

    public static void sort(int[] array) {
        if (array.length == 0)
            return;

        var numberOfBuckets = findNumberOfBuckets(array, 1);
        var buckets = createBuckets(array, numberOfBuckets);
        sortBuckets(buckets);

        int i = 0;
        for (var bucket : buckets) {
            for (var number : bucket)
                array[i++] = number;
        }
    }

    private static int findMaxElement(int[] array) {
        var max = array[0];
        for (var number : array) {
            if (number > max)
                max = number;
        }
        return max;
    }

    private static int findNumberOfBuckets(int[] array, int numberOfBuckets) {
        var maxElement = findMaxElement(array);
        var maxBucketIndex = maxElement / numberOfBuckets;
        if (maxBucketIndex >= numberOfBuckets)
            numberOfBuckets = maxBucketIndex;

        return numberOfBuckets;
    }

    private static List<List<Integer>> createBuckets(int[] array, int numberOfBuckets) {
        List<List<Integer>> buckets = new ArrayList<>();
        for (var i = 0; i < numberOfBuckets; i++)
            buckets.add(new ArrayList<>());

        for (var item : array) {
            buckets.get(item / numberOfBuckets).add(item);
        }
        return buckets;
    }

    private static void sortBuckets(List<List<Integer>> buckets) {
        for (var bucket : buckets)
            Collections.sort(bucket);
    }
}
