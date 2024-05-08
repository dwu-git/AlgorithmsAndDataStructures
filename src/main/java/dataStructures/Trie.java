package dataStructures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Trie {
    private class Node {
        private char value;
        private java.util.HashMap<Character, Node> children = new HashMap<>();
        private boolean isEndOfWord;

        public Node(char value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "value=" + value;
        }

        public boolean hasChild(char ch) {
            return children.containsKey(ch);
        }

        public void addChild(char ch) {
            children.put(ch, new Node(ch));
        }

        public Node getChild(char key) {
            return children.get(key);
        }

        public Node[] getChildren() {
            return children.values().toArray(new Node[0]);
        }

        public boolean hasChildren() {
            return !children.isEmpty();
        }

        public void removeChild(char ch) {
            children.remove(ch);
        }
    }

    private Node root = new Node(' ');
    private String foundResultInTrieFromScannedWord;

    public void insert(String word) {
        var current = root;
        for (var ch : word.toCharArray()) {
            if (!current.hasChild(ch))
                current.addChild(ch);
            current = current.getChild(ch);
        }
        current.isEndOfWord = true;
    }

    public boolean contains(String word) {
        if (word == null)
            return false;

        var current = root;
        for (var ch : word.toCharArray()) {
            if (!current.hasChild(ch))
                return false;
            current = current.getChild(ch);
        }
        return current.isEndOfWord;
    }

    public boolean containsRecursive(String word) {
        if (word == null)
            return false;
        return containsRecursive(root, word, 0);
    }

    private boolean containsRecursive(Node root, String word, int counter) {
        var current = root;
        for (int i = counter; i < word.toCharArray().length; i++) {
            if (current.getChild(word.toCharArray()[i]) == null
                    || counter == word.toCharArray().length && !current.isEndOfWord)
                return false;

            current = current.getChild(word.toCharArray()[i]);
            counter++;
            containsRecursive(current, word, counter);
        }
        return current.isEndOfWord;
    }

    private Node getCurrentLastLetterOfWord(Node node, String word, int currentWordLength, int currentLetterIndexInWord) {
        var currentNode = node;
        while (currentLetterIndexInWord != currentWordLength) {
            currentNode = currentNode.getChild(word.toCharArray()[currentLetterIndexInWord]);
            currentLetterIndexInWord++;
        }
        return currentNode;
    }

    public void remove(String word) {
        if (!contains(word))
            throw new IllegalArgumentException();

        var currentWordLength = word.toCharArray().length;
        var currentLastNode = getCurrentLastLetterOfWord(root, word, currentWordLength, 0);
        currentLastNode.isEndOfWord = false;

        if (currentLastNode.getChildren().length == 0) {
            currentWordLength--;

            while (currentWordLength != 0) {
                var previousNode = currentLastNode;
                currentLastNode = getCurrentLastLetterOfWord(root, word, currentWordLength, 0);
                if (currentLastNode.getChildren().length == 1 && !currentLastNode.isEndOfWord) {
                    currentLastNode.children = null;
                    currentWordLength--;
                    continue;
                }
                if (currentLastNode.hasChild(previousNode.value))
                    currentLastNode.children.remove(previousNode.value);
                break;
            }
            if (currentWordLength == 0)
                root.children.remove(word.toCharArray()[0]);
        }
    }

    public void remove2(String word) {
        if (word == null)
            return;

        remove3(root, word, 0);
    }

    private void remove3(Node root, String word, int index) {
        if (index == word.length()) {
            root.isEndOfWord = false;
            return;
        }

        var ch = word.charAt(index);
        var child = root.getChild(ch);
        if (child == null)
            return;

        remove3(child, word, index + 1);

        if (!child.hasChildren() && !child.isEndOfWord)
            root.removeChild(ch);
    }

    public String scannedWord() {
        var scanner = new Scanner(System.in);
        return scanner.next();
    }

    public void autoComplete() {
        List<String> words = new ArrayList<>();
        autoComplete(root, "", words, scannedWord(), 0);

        var arrayOfWords = words.toArray();
        for (var word : arrayOfWords)
            System.out.println(word);
    }

    private Node findLastNodeOfScannedWord(Node root, String word, List<String> words, String scannedWord, int index) {
        var current = root;
        var lastNodeOfScannedWord = current;
        if (current != null && index < scannedWord.toCharArray().length && current.getChild(scannedWord.charAt(index)) != null) {
            current = current.getChild(scannedWord.charAt(index));
            word += current.value;
            foundResultInTrieFromScannedWord = word;
            index += 1;
            lastNodeOfScannedWord = findLastNodeOfScannedWord(current, word, words, scannedWord, index);
        }
        if (current == null || index < scannedWord.toCharArray().length && current.getChild(scannedWord.charAt(index)) == null)
            return null;

        return lastNodeOfScannedWord;
    }

    private void createAutocompletedWords(Node currentNode, String word, List<String> words) {
        if (currentNode == null || currentNode.getChildren().length == 0)
            return;

        for (var child : currentNode.getChildren()) {
            if (child == null || currentNode.getChildren().length == 0)
                return;

            var copyWord = word;
            copyWord += child.value;
            if (child.isEndOfWord)
                words.add(copyWord);

            createAutocompletedWords(child, copyWord, words);
        }
    }

    private void autoComplete(Node root, String word, List<String> words, String scannedWord, int index) {
        var currentNode = findLastNodeOfScannedWord(root, word, words, scannedWord, index);
        if (currentNode == this.root || currentNode == null)
            return;
        if (currentNode.isEndOfWord)
            words.add(foundResultInTrieFromScannedWord);
        createAutocompletedWords(currentNode, foundResultInTrieFromScannedWord, words);
    }

    public List<String> findWords(String prefix) {
        List<String> words = new ArrayList<>();
        var lastNode = findLastNodeOf(prefix);
        findWords(lastNode, prefix, words);

        return words;
    }

    private void findWords(Node root, String prefix, List<String> words) {
        if (root == null)
            return;

        if (root.isEndOfWord)
            words.add(prefix);

        for (var child : root.getChildren())
            findWords(child, prefix + child.value, words);
    }

    private Node findLastNodeOf(String prefix) {
        if (prefix == null)
            return null;
        var current = root;
        for (var ch : prefix.toCharArray()) {
            var child = current.getChild(ch);
            if (child == null)
                return null;
            current = child;
        }
        return current;
    }

    public int countWords() {
        return countWords(root);
    }

    private int countWords(Node root) {
        var counter = 0;

        if (root.isEndOfWord)
            counter++;

        for (var node : root.getChildren()) {
            counter += countWords(node);
        }
        return counter;
    }

    public String longestCommonPrefixInTrie(String[] words) {
        if (words == null || words.length == 0
                || findCommonPrefixInWords(words).equals(""))
            return "";

        var commonPrefixInWords = findCommonPrefixInWords(words);
        if (myContains(commonPrefixInWords))
            return commonPrefixInWords;

        return null;
    }

    private String findShortestWord(String[] words) {
        var shortestWord = words[0];
        for (var word : words) {
            if (word.length() < shortestWord.length())
                shortestWord = word;
        }
        return shortestWord;
    }

    private String findCommonPrefixInWords(String[] words) {
        var shortestWord = findShortestWord(words).toCharArray();
        var prefix = "";
        List<String> similarWords = new ArrayList<>();

        for (var word : words) {
            prefix = "";
            int index = 0;
            for (var ch : word.toCharArray()) {
                if (index < shortestWord.length && ch == shortestWord[index])
                    prefix += ch;

                index++;

                if (index == shortestWord.length)
                    similarWords.add(prefix);
            }
        }
        if (similarWords.contains(""))
            return "";

        prefix = findCommonPrefixInSimilarWords(similarWords, words, findShortestWord(words));
        return prefix;
    }

    private String findCommonPrefixInSimilarWords(List<String> similarWords, String[] words, String prefix) {
        var shortestWord = findShortestWord(words);
        for (var word : similarWords) {
            int index = 0;
            var newPrefix = "";
            for (var ch : word.toCharArray()) {
                if (ch != shortestWord.toCharArray()[index++])
                    return newPrefix;

                newPrefix += ch;
            }
            if (word.length() < shortestWord.length())
                return word;
        }
        return prefix;
    }

    private boolean myContains(String word) {
        var current = root;

        for (var ch : word.toCharArray()) {
            if (current == null)
                return false;
            current = current.getChild(ch);
        }
        return true;
    }
}
