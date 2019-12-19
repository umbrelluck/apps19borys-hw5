package ua.edu.ucu.stream;

import ua.edu.ucu.function.*;
import ua.edu.ucu.iterators.FilterIterable;
import ua.edu.ucu.iterators.FlatMapIterable;
import ua.edu.ucu.iterators.MapIterable;
import ua.edu.ucu.iterators.StreamIterable;

import java.util.Arrays;
import java.util.Iterator;

public class AsIntStream implements IntStream {
    private Iterable<Integer> iterator;

    private AsIntStream(Iterable<Integer> iterator) {
        this.iterator = iterator;
    }

    public static IntStream of(int... values) {
        return new AsIntStream(new StreamIterable(values));
    }

    private void checker() {
        if (count() == 0) {
            throw new IllegalArgumentException("No arguments");
        }
    }

    @Override
    public Double average() {
        checker();
        return (double) sum() / count();
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer max() {
        checker();
        int maxVal = Integer.MIN_VALUE;
        for (int elem : iterator) {
            if (elem > maxVal) {
                maxVal = elem;
            }
        }
        return maxVal;
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer min() {
        checker();
        int minVal = Integer.MAX_VALUE;
        for (int elem : iterator) {
            if (elem < minVal) {
                minVal = elem;
            }
        }
        return minVal;
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long count() {
        long size = 0;
        for (int elem : iterator) {
            size++;
        }
        return size;
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer sum() {
        checker();
        int sum = 0;
        for (int elem : iterator) {
            sum += elem;
        }
        return sum;
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IntStream filter(IntPredicate predicate) {
        return new AsIntStream(new FilterIterable(iterator, predicate));
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void forEach(IntConsumer action) {
        for (int elem : iterator) {
            action.accept(elem);
        }
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IntStream map(IntUnaryOperator mapper) {
        return new AsIntStream(new MapIterable(iterator, mapper));
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IntStream flatMap(IntToIntStreamFunction func) {
        return new AsIntStream(new FlatMapIterable(iterator, func));
    }

    @Override
    public int reduce(int identity, IntBinaryOperator op) {
        int res = identity;
        for (int elem : iterator) {
            res = op.apply(res, elem);
        }
        return res;
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int[] toArray() {
        int[] res = new int[(int) count()];
        int i = 0;
        for (int elem : iterator) {
            res[i++] = elem;
        }
        return res;
        //To change body of generated methods, choose Tools | Templates.
    }

    public Iterator<Integer> getIterator() {
        return iterator.iterator();
    }

    public String toString() {
        return Arrays.toString(toArray());
    }
}
