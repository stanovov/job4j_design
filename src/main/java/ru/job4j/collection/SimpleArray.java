package ru.job4j.collection;

import java.util.*;

public class SimpleArray<T> implements Iterable<T> {

    private Object[] container;

    private int size = 0;

    private int modCount = 0;

    public SimpleArray() {
        this.container = new Object[10];
    }

    public SimpleArray(int length) {
        this.container = new Object[length];
    }

    @SuppressWarnings("unchecked")
    public T get(int index) {
        Objects.checkIndex(index, size);
        return (T) container[index];
    }

    public void add(T model) {
        modCount++;
        if (container.length == size) {
            int newLength = size / 2 + size;
            Object[] newContainer = new Object[newLength];
            System.arraycopy(container, 0, newContainer, 0, container.length);
            container = newContainer;
        }
        container[size] = model;
        size++;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {

            private int cursor = 0;

            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                return cursor < size;
            }

            @Override
            @SuppressWarnings("unchecked")
            public T next() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) container[cursor++];
            }
        };
    }
}