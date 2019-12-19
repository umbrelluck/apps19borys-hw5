package ua.edu.ucu.iterators;

import java.util.Iterator;

public class StreamIterable implements Iterable<Integer> {
    int[] array;

    public StreamIterable(int... values) {
        array = values;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            private int i = 0;

            @Override
            public boolean hasNext() {
                return i < array.length;
            }

            @Override
            public Integer next() {
                return array[i++];
            }
        };
    }
}