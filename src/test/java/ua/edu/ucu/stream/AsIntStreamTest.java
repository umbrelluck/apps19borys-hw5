package ua.edu.ucu.stream;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AsIntStreamTest {

    IntStream empty;
    IntStream stream;

    @Before
    public void setUp() throws Exception {
        stream = AsIntStream.of(1, 2, 3, -5, 10, 9, 7);
        empty = AsIntStream.of();
    }

    @Test
    public void of() {
        assertEquals(IntStream.class, stream.getClass());
        assertEquals(IntStream.class, empty.getClass());
    }

    @Test(expected = IllegalArgumentException.class)
    public void average() {
        assertEquals(3.85, stream.average(), 0.001);
        empty.average();
    }

    @Test(expected = IllegalArgumentException.class)
    public void max() {
        assertEquals(10, (long) stream.max());
        empty.max();
    }

    @Test(expected = IllegalArgumentException.class)
    public void min() {
        assertEquals(-5, (long) stream.min());
        empty.min();
    }

    @Test
    public void count() {
        assertEquals(7, stream.count());
        assertEquals(0, empty.count());
    }

    @Test(expected = IllegalArgumentException.class)
    public void sum() {
        assertEquals(27, (long) stream.sum());
        empty.sum();
    }

    @Test
    public void filter() {
        IntStream res = stream.filter(x -> x % 2 == 0);
        assertArrayEquals(new int[]{1, 3, -5, 9, 7}, res.toArray());
    }

    @Test
    public void forEach() {
    }

    @Test
    public void map() {
        IntStream res = stream.map(Math::abs);
        assertArrayEquals(new int[]{1, 2, 3, 5, 10, 9, 7}, res.toArray());
    }

    @Test
    public void flatMap() {
    }

    @Test
    public void reduce() {
        int result = stream.reduce(0, (subtotal, elem)
                -> (subtotal + elem) / 5);
        assertEquals(1, result);
        result = empty.reduce(7, (subtotal, elem)
                -> subtotal + elem / 5);
        assertEquals(7, result);
    }

    @Test
    public void toArray() {
        assertArrayEquals(new int[]{1, 2, 3, -5, 10, 9, 7}, stream.toArray());
        assertArrayEquals(null, empty.toArray());
    }
}