package ua.edu.ucu.stream;

import ua.edu.ucu.function.*;
import ua.edu.ucu.iterators.FilterIterable;
import ua.edu.ucu.iterators.FlatMapIterable;
import ua.edu.ucu.iterators.MapIterable;
import ua.edu.ucu.iterators.StreamIterable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class AsIntStream implements IntStream {
    private Iterator<Integer> iterator;


    private AsIntStream(Iterator<Integer> iterator) {
        this.iterator = iterator;
    }

    private Iterable<Integer> toIterable() {
        return () -> iterator;
    }

    public static IntStream of(int... values) {
        return new AsIntStream(new StreamIterable(values));
    }

    private void checker() {
        if (iterator == null || !iterator.hasNext()) {
            throw new IllegalArgumentException("No arguments");
        }
    }

    @Override
    public Double average() {
        checker();
        double sum = 0;
        int len = 0;
        for (int elem : toIterable()) {
            sum += elem;
            len += 1;
        }
        return sum / len;
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer max() {
        checker();
        int maxVal = Integer.MIN_VALUE;
        for (int elem : toIterable()) {
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
        for (int elem : toIterable()) {
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
        for (int elem : toIterable()) {
            size++;
        }
        return size;
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer sum() {
        checker();
//        return reduce(0, Integer::sum);
        int sum = 0;
        for (int elem : toIterable()) {
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
        for (int elem : toIterable()) {
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
        for (int elem : toIterable()) {
            res = op.apply(res, elem);
        }
        return res;
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int[] toArray() {
        ArrayList<Integer> tmp_res = new ArrayList<>();
        for (int elem : toIterable()) {
            tmp_res.add(elem);
        }
        int[] res = new int[tmp_res.size()];
        int i = 0;
        for (int elem : tmp_res) {
            res[i++] = elem;
        }
        return res;
//        int[] res = new int[(int) count()];
//        int i = 0;
//        for (int elem : toIterable()) {
//            res[i++] = elem;
//        }
//        return res;
        //To change body of generated methods, choose Tools | Templates.
    }

    public Iterator<Integer> getIterator() {
        return iterator;
    }

//    public String toString() {
//        StringBuilder str = new StringBuilder();
//        for (int elem : toIterable()) {
//            str.append(elem).append(' ');
//        }
//        try {
//            return str.deleteCharAt(str.lastIndexOf(" ")).toString();
//        } catch (StringIndexOutOfBoundsException e) {
//            return str.toString();
//        }
//    }

    public static void main(String[] args) {
        int[] lst = new int[10];
        for (int i = 0; i < 10; i++) {
            lst[i] = i;
        }
        java.util.stream.IntStream stream = Arrays.stream(lst);
        System.out.println(stream.max());
        System.out.println(stream.count());
    }
}
