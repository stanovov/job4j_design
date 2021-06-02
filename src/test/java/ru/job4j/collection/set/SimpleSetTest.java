package ru.job4j.collection.set;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleSetTest {

    @Test
    public void whenAddNonNull() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(1));
        assertTrue(set.contains(1));
        assertFalse(set.add(1));
    }

    @Test
    public void whenAddNull() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(null));
        assertTrue(set.contains(null));
        assertFalse(set.add(null));
    }

    @Test
    public void whenAddNonNullAndNull() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(42));
        assertTrue(set.add(null));
        assertTrue(set.contains(42));
        assertTrue(set.contains(null));
        assertFalse(set.add(42));
        assertFalse(set.add(null));
    }

    @Test
    public void whenMultiplyAddSameElement() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(26));
        for (int i = 0; i < 10; i++) {
            assertFalse(set.add(26));
        }
    }

    @Test
    public void whenIterateOverSet() {
        Set<Integer> set = new SimpleSet<>();
        set.add(1);
        set.add(3);
        set.add(5);
        Iterator<Integer> it = set.iterator();
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(3));
        assertThat(it.next(), is(5));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenCorruptedIt() {
        Set<Integer> set = new SimpleSet<>();
        set.add(null);
        Iterator<Integer> it = set.iterator();
        set.add(42);
        it.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetEmptyFromIt() {
        Set<Integer> set = new SimpleSet<>();
        Iterator<Integer> it = set.iterator();
        it.next();
    }

}