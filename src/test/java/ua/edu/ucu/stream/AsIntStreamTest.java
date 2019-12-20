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
        assertEquals(AsIntStream.class, stream.getClass());
        assertEquals(AsIntStream.class, empty.getClass());
    }

    @Test(expected = IllegalArgumentException.class)
    public void average() {
        double rs = stream.average();
        assertEquals(3.85, stream.average(), 0.01);
        empty.average();
    }

    @Test(expected = IllegalArgumentException.class)
    public void max() {
        assertEquals(10, (long) stream.max());
        empty.max();
    }

    @Test(expected = IllegalArgumentException.class)
    public void min() {
//        int res = stream.min();
//        stream = new
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
        assertArrayEquals(new int[]{2, 10}, res.toArray());
    }

    @Test
    public void forEach() {
        stream.forEach(System.out::print);
        assertTrue(true);
    }

    @Test
    public void map() {
        IntStream res = stream.map(Math::abs);
        assertArrayEquals(new int[]{1, 2, 3, 5, 10, 9, 7}, res.toArray());
    }

    @Test
    public void flatMap() {
        IntStream res = stream.flatMap(x -> AsIntStream.of(x * x, x / 2, x % 3));
        assertArrayEquals(new int[]{1, 0, 1, 4, 1, 2, 9, 1, 0, 25, -2, -2, 100, 5, 1, 81, 4
                , 0, 49, 3, 1}, res.toArray());
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
        assertArrayEquals(new int[0], empty.toArray());
    }
}