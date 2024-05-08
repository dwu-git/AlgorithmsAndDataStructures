package sorting;

public class BubbleSort {
    public static void sort(int[] numbers) {
        while (!isSortedArray(numbers)) {
            for (int i = 0; i < numbers.length - 1; i++) {
                int j = i + 1;
                if (numbers[i] > numbers[j]) {
                    int numberCopy = numbers[i];
                    numbers[i] = numbers[j];
                    numbers[j] = numberCopy;
                }
            }
        }
    }

    private static boolean isSortedArray(int[] numbers) {
        for (int i = 0; i < numbers.length - 1; i++) {
            int j = i + 1;
            if (numbers[i] > numbers[j])
                return false;
        }
        return true;
    }
}
