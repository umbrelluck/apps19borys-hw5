package ua.edu.ucu.iterators;

import ua.edu.ucu.function.IntToIntStreamFunction;
import ua.edu.ucu.stream.AsIntStream;

import java.util.Iterator;

public class FlatMapIterable implements Iterable<Integer> {
    private Iterable<Integer> previous;
    private IntToIntStreamFunction fun;

    public FlatMapIterable(Iterable<Integer> iter, IntToIntStreamFunction fn) {
        previous = iter;
        fun = fn;

    }

    @Override
    public Iterator<Integer> iterator() {
        Iterator<Integer> itr = previous.iterator();
        return new Iterator<Integer>() {
            private Iterator<Integer> newStream;

            public boolean hasNext() {
                if (newStream != null && newStream.hasNext()) {
                    return true;
                }
                if (itr.hasNext()) {
                    newStream =
                            ((AsIntStream) fun.applyAsIntStream(itr.next()))
                                    .getIterator();
                    return true;
                }
                return false;
            }

            public Integer next() {
                return newStream.next();
            }
        };
    }
}
