package ru.job4j.generics;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {

    private Object[] elementData;

    private int pointer;

    public SimpleArray(int size) {
        this.elementData = new Object[size];
    }

    public void add(T model) {
        elementData[pointer++] = model;
    }

    public void set(int index, T model) {
        Objects.checkIndex(index, pointer);
        elementData[index] = model;
    }

    public void remove(int index) {
        Objects.checkIndex(index, pointer);
        pointer--;
        if (pointer > index) {
            System.arraycopy(elementData, index + 1, elementData, index, pointer - index);
        }
        elementData[pointer] = null;
    }

    @SuppressWarnings("unchecked")
    public T get(int index) {
        Objects.checkIndex(index, pointer);
        return (T) elementData[index];
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int cursor;

            @Override
            public boolean hasNext() {
                return cursor < pointer;
            }

            @Override
            @SuppressWarnings("unchecked")
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) elementData[cursor++];
            }
        };
    }
}
