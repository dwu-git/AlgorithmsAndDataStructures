package dataStructures;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FirstRepeatedCharacter {
    public char findFirstRepeatedCharacter(String str) {
        Set<Character> uniqueNumbers = new HashSet<>();
        Map<Character, Integer> map = new HashMap<>();

        for (var ch : str.toCharArray()) {
            if (uniqueNumbers.contains(ch))
                map.put(ch, 1);

            uniqueNumbers.add(ch);
        }

        for (var ch : str.toCharArray())
            if (map.containsKey(ch))
                return ch;

        return Character.MIN_VALUE;
    }
}