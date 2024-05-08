package dataStructures;

import java.util.Arrays;

public class HashMap {
    private class Entry {
        private int key;
        private String value;

        public Entry(int key, String value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    private Entry[] array = new Entry[5];
    private int count;

    public void put(int key, String value) {
        var entry = new Entry(key, value);
        var index = hash(key);

        while (true) {
            if (index == array.length)
                throw new IllegalStateException();

            if (array[index] == null) {
                array[index] = entry;
                count++;
                return;
            }

            if (array[index].key == key) {
                array[index] = entry;
                return;
            }
            index++;
        }
    }

    public String get(int key) {
        var index = hash(key);
        if (array[index] == null)
            return null;

        return array[index].value;
    }

    public void remove(int key) {
        var index = hash(key);
        if (array[index] == null)
            return;

        array[index] = null;
        count--;
    }

    public int size() {
        return count;
    }

    private int hash(int key) {
        return key % 5;
    }

    public String toString() {
        return Arrays.toString(array);
    }
}
