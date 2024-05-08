package dataStructures;

public class Array {
    private int[] items;
    private int count;

    public Array(int length) {
        items = new int[length];
    }

    public void insert(int item) {
        resize();
        items[count++] = item;
    }

    public void myInsertAt(int index, int item) {
        if (index < 0 || index >= count)
            throw new IllegalArgumentException();

        resize();

        for (int i = count - 1; i >= index; i--) {
            items[i + 1] = items[i];
        }

        items[index] = item;
        count++;
    }

    private void resize() {
        if (items.length == count) {
            int[] resized = new int[count * 2];

            for (int i = 0; i < count; i++)
                resized[i] = items[i];

            items = resized;
        }
    }

    public void insertAt(int item, int index) {
        if (index < 0 || index > count)
            throw new IllegalArgumentException();

        resizeIfRequired();

        for (int i = count - 1; i >= index; i--)
            items[i + 1] = items[i];

        items[index] = item;
        count++;
    }

    private void resizeIfRequired() {
        if (items.length == count) {
            int[] newItems = new int[count * 2];

            for (int i = 0; i < count; i++)
                newItems[i] = items[i];

            items = newItems;
        }
    }

    public int maxNumber() {
        int max = 0;
        for (int i = 0; i < count; i++)
            if (items[i] > max)
                max = items[i];

        return max;
    }

    public Array intersect(Array other) {
        Array intersection = new Array(count);

        for (var item : items)
            if (other.indexOf(item) >= 0)

                intersection.insert(item);

        return intersection;
    }

    public void reverse() {
        int j = 0;
        int[] reversed = new int[count];

        for (int i = count - 1; i >= 0; i--)
            reversed[j++] = items[i];

        items = reversed;
    }

    public void removeAt(int index) throws IllegalAccessException {
        if (index < 0 || index >= count)
            throw new IllegalAccessException();

        for (int i = index; i < count; i++)
            items[i] = items[i + 1];

        count--;
    }

    public int indexOf(int item) {
        for (int index = 0; index < items.length; index++)
            if (items[index] == item)
                return index;

        return -1;
    }

    public void print() {
        for (int i = 0; i < count; i++)
            System.out.println(items[i]);
    }
}
