package strings;

import java.util.*;

public class StringUtils {
    public static int countVowels(String str) {
        if (str == null)
            return 0;

        int count = 0;
        char[] wordArray = str.toCharArray();
        char[] vowels = {'A', 'a', 'E', 'e', 'O', 'o', 'U', 'u', 'I', 'i'};

        for (char letter : wordArray)
            for (var vowel : vowels)
                if (letter == vowel)
                    count++;

        return count;
    }

    public static int countVowelsMosh(String str) {
        if (str == null)
            return 0;

        int count = 0;
        String vowels = "aeiou";
        for (var ch : str.toLowerCase().toCharArray())
            if (vowels.indexOf(ch) != -1)
                count++;

        return count;

    }

    public static String reverseWords(String sentence) {
        String[] words = sentence.split(" ");

        StringBuilder reversedWords = new StringBuilder();

        for (var i = words.length - 1; i >= 0; i--)
            reversedWords.append(words[i] + " ");

        return reversedWords.toString().trim();
    }

    public static String reverseWordsMosh(String sentence) {
        if (sentence == null)
            return "";

        String[] words = sentence.trim().split(" ");
        Collections.reverse(Arrays.asList(words));
        return String.join(" ", words);
    }

    public static boolean areRotations(String str1, String str2) {
        if (str1 == null || str2 == null)
            return false;

        return (str1.length() == str2.length() &&
                (str1 + str1).contains(str2));
    }

    public static String removeDuplicates(String str) {
        if (str == null || str.isEmpty())
            throw new IllegalArgumentException();

        StringBuilder output = new StringBuilder();
        Set<Character> seen = new HashSet<>();

        for (var ch : str.toCharArray()) {
            if (!seen.contains(ch)) {
                seen.add(ch);
                output.append(ch);
            }
        }
        return output.toString();
    }

    public static char mostRepeatedChar(String str) {
        if (str == null || str.isEmpty())
            throw new IllegalArgumentException();

        Map<Character, Integer> frequencies = new HashMap<>();
        for (var ch : str.toCharArray()) {
            if (!frequencies.containsKey(ch))
                frequencies.put(ch, 1);
            else
                frequencies.replace(ch, frequencies.get(ch) + 1);
        }

        char mostRepeated = str.charAt(0);
        for (var ch : frequencies.keySet())
            if (frequencies.get(mostRepeated) < frequencies.get(ch))
                mostRepeated = ch;

        return mostRepeated;
    }

    public static char mostRepeatedCharMosh(String str) {
        if (str == null || str.isEmpty())
            throw new IllegalArgumentException();

        final int ASCII_SIZE = 256;
        int[] frequencies = new int[ASCII_SIZE];
        for (var ch : str.toCharArray())
            frequencies[ch]++;

        int max = frequencies[0];
        char result = ' ';
        for (var i = 0; i < frequencies.length; i++)
            if (frequencies[i] > max) {
                max = frequencies[i];
                result = (char) i;
            }
        return result;
    }

    public static String capitalize(String sentence) {
        if (sentence == null || sentence.trim().isEmpty())
            return "";

        String[] words = sentence
                .trim()
                .replaceAll(" +", " ")
                .split(" ");
        for (var i = 0; i < words.length; i++) {
            words[i] = words[i].substring(0, 1).toUpperCase() +
                    words[i].substring(1).toLowerCase();
        }
        return String.join(" ", words);
    }

    public static boolean areAnagrams(String str1, String str2) {
        if (str1 == null || str2 == null)
            return false;

        var array1 = str1.toLowerCase().toCharArray();
        Arrays.sort(array1);

        var array2 = str2.toLowerCase().toCharArray();
        Arrays.sort(array2);

        return Arrays.equals(array1, array2);
    }

    public static boolean areAnagrams2(String str1, String str2) {
        if (str1 == null || str2 == null ||
                str1.length() != str2.length())
            return false;

        final int ENGLISH_ALPHABET = 26;
        int[] frequencies = new int[ENGLISH_ALPHABET];

        str1 = str1.toLowerCase();
        for (var i = 0; i < str1.length(); i++)
            frequencies[str1.charAt(i) - 'a']++;

        str2 = str2.toLowerCase();
        for (var i = 0; i < str2.length(); i++) {
            var index = str2.charAt(i) - 'a';
            if (frequencies[index] == 0)
                return false;

            frequencies[index]--;
        }
        return true;
    }

    public static boolean isPalindrome(String str) {
        if (str == null)
            return false;

        int left = 0;
        int right = str.length() - 1;

        while (left < right)
            if (str.charAt(left++) != str.charAt(right--))
                return false;

        return true;
    }
}
