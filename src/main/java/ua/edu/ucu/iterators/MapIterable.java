package ua.edu.ucu.iterators;

import ua.edu.ucu.function.IntUnaryOperator;

import java.util.Iterator;

public class MapIterable implements Iterator<Integer> {
    private Iterator<Integer> previous;
    private IntUnaryOperator fun;

    public MapIterable(Iterator<Integer> iter, IntUnaryOperator oper) {
        previous = iter;
        fun = oper;
    }

    @Override
    public boolean hasNext() {
        return previous.hasNext();
    }

    @Override
    public Integer next() {
        return fun.apply(previous.next());
    }
}

//public class MapIterable extends StreamIterable {
//    private Iterable<Integer> previous;
//    private IntUnaryOperator fun;
//
//    public MapIterable(Iterable<Integer> iter, IntUnaryOperator oper) {
//        previous = iter;
//        fun = oper;
//    }
//
//    @Override
//    public Iterator<Integer> iterator() {
//        Iterator<Integer> itr = previous.iterator();
//        return new Iterator<Integer>() {
//
//            @Override
//            public boolean hasNext() {
//                return itr.hasNext();
//            }
//
//            @Override
//            public Integer next() {
//                return fun.apply(itr.next());
//            }
//        };
//    }
//}
