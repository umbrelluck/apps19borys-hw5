package ua.edu.ucu.iterators;

import ua.edu.ucu.function.IntPredicate;

import java.util.Iterator;


public class FilterIterable implements Iterator<Integer> {
    private Iterator<Integer> previous;
    private IntPredicate predicate;
    private int val;

    public FilterIterable(Iterator<Integer> iter, IntPredicate pr) {
        previous = iter;
        predicate = pr;
    }

    @Override
    public boolean hasNext() {
        while (previous.hasNext()) {
            val = previous.next();
            if (predicate.test(val)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Integer next() {
        return val;
    }
}

//public class FilterIterable extends StreamIterable {
//    private Iterable<Integer> previous;
//    private IntPredicate predicate;
//
//    public FilterIterable(Iterable<Integer> iter, IntPredicate pr) {
//        previous = iter;
//        predicate = pr;
//    }
//
//    @Override
//    public Iterator<Integer> iterator() {
//        Iterator<Integer> itr = previous.iterator();
//        return new Iterator<Integer>() {
//            private int val;
//
//            @Override
//            public boolean hasNext() {
//                while (itr.hasNext()) {
//                    val = itr.next();
//                    if (predicate.test(val)) {
//                        return true;
//                    }
//                }
//                return false;
//            }
//
//            @Override
//            public Integer next() {
//                return val;
//            }
//        };
//    }
//}
