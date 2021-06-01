package ru.job4j.generics;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;


public class SimpleArrayTest {

    @Test
    public void whenGetInAllowedRange() {
        SimpleArray<Integer> sArray = new SimpleArray<>(3);
        sArray.add(1);
        sArray.add(2);
        sArray.add(3);
        assertThat(sArray.get(0), is(1));
        assertThat(sArray.get(1), is(2));
        assertThat(sArray.get(2), is(3));
    }

    @Test
    public void whenGetInAllowedRangeManyTimes() {
        SimpleArray<Integer> sArray = new SimpleArray<>(3);
        sArray.add(1);
        sArray.add(2);
        sArray.add(3);
        assertThat(sArray.get(0), is(1));
        assertThat(sArray.get(0), is(1));
        assertThat(sArray.get(0), is(1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetInEmptyArray() {
        SimpleArray<Integer> sArray = new SimpleArray<>(3);
        sArray.get(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddInUnacceptableRange() {
        SimpleArray<Integer> sArray = new SimpleArray<>(0);
        sArray.add(42);
    }

    @Test
    public void whenSetInAllowedRange() {
        SimpleArray<Integer> sArray = new SimpleArray<>(3);
        sArray.add(1);
        sArray.add(2);
        sArray.add(3);
        sArray.set(0, 42);
        assertThat(sArray.get(0), is(42));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenSetInUnacceptableRange() {
        SimpleArray<Integer> sArray = new SimpleArray<>(3);
        sArray.set(0, 42);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenRemoveFromEndOfTheArray() {
        SimpleArray<Integer> sArray = new SimpleArray<>(3);
        sArray.add(1);
        sArray.add(2);
        sArray.add(3);
        sArray.remove(2);
        assertThat(sArray.get(0), is(1));
        assertThat(sArray.get(1), is(2));
        sArray.get(2);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenRemoveFromStartOfTheArray() {
        SimpleArray<Integer> sArray = new SimpleArray<>(3);
        sArray.add(1);
        sArray.add(2);
        sArray.add(3);
        sArray.remove(0);
        assertThat(sArray.get(0), is(2));
        assertThat(sArray.get(1), is(3));
        sArray.get(2);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenRemoveFromMiddleOfTheArray() {
        SimpleArray<Integer> sArray = new SimpleArray<>(3);
        sArray.add(1);
        sArray.add(2);
        sArray.add(3);
        sArray.remove(1);
        assertThat(sArray.get(0), is(1));
        assertThat(sArray.get(1), is(3));
        sArray.get(2);
    }

    @Test
    public void whenUseIteratorsHasNextInEmptyArray() {
        SimpleArray<Integer> sArray = new SimpleArray<>(3);
        Iterator<Integer> it = sArray.iterator();
        assertThat(it.hasNext(), is(false));
        assertThat(it.hasNext(), is(false));
    }

    @Test
    public void whenUseIteratorsHasNextInNotEmptyArray() {
        SimpleArray<Integer> sArray = new SimpleArray<>(3);
        sArray.add(42);
        Iterator<Integer> it = sArray.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenUseIteratorsNextInEmptyArray() {
        SimpleArray<Integer> sArray = new SimpleArray<>(3);
        Iterator<Integer> it = sArray.iterator();
        it.next();
    }

    @Test
    public void whenUseIteratorsNextInNotEmptyArray() {
        SimpleArray<Integer> sArray = new SimpleArray<>(3);
        sArray.add(1);
        sArray.add(42);
        sArray.add(142);
        Iterator<Integer> it = sArray.iterator();
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(42));
        assertThat(it.next(), is(142));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenUseIteratorsNextInNotEmptyArrayAndOutOfBounds() {
        SimpleArray<Integer> sArray = new SimpleArray<>(1);
        sArray.add(42);
        Iterator<Integer> it = sArray.iterator();
        assertThat(it.next(), is(42));
        it.next();
    }
}