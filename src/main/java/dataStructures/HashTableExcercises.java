package dataStructures;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class HashTableExcercises {
    public int mostFrequent(int[] numbers) {
        Map<Integer, Integer> map = new HashMap<>();
        for (var number : numbers) {
            var count = map.containsKey(number) ? map.get(number) : 0;
            map.put(number, count + 1);
        }

        int max = -1;
        int result = numbers[0];

        for (var times : map.entrySet()) {
            if (max < times.getValue()) {
                max = times.getValue();
                result = times.getKey();
            }
        }
        return result;
    }

    public int countPairsWithDiff(int[] numbers, int k) {
        Set<Integer> set = new HashSet<>();
        for (var number : numbers)
            set.add(number);

        var count = 0;
        for (var number : numbers) {
            if (set.contains(number - k))
                count++;

            if (set.contains(number + k))
                count++;

            set.remove(number);
        }
        return count;
    }

    public Set<Integer> twoSum(int[] numbers, int target) {
        Set<Integer> indices = new HashSet<>();
        Set<Integer> setOfNumbers = new HashSet<>();

        for (var number : numbers)
            setOfNumbers.add(number);

        for (int i = 0; i < numbers.length; i++)
            if (setOfNumbers.contains(target - numbers[i]))
                indices.add(i);

        return indices;
    }


}
