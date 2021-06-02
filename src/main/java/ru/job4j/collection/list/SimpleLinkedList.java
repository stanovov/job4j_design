package ru.job4j.collection.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements List<E> {

    private Node<E> first;

    private Node<E> last;

    private int size = 0;

    private int modCount = 0;

    private static class Node<E> {
        E element;
        Node<E> next;
        Node<E> prev;

        Node(E element, Node<E> next, Node<E> prev) {
            this.element = element;
            this.next = next;
            this.prev = prev;
        }
    }

    @Override
    public void add(E value) {
        Node<E> lastTmp = last;
        Node<E> newNode = new Node<>(value, null, lastTmp);
        last = newNode;
        if (lastTmp == null) {
            first = newNode;
        } else {
            lastTmp.next = newNode;
        }
        modCount++;
        size++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> tmpNode = first;
        for (int i = 0; i < index; i++) {
            tmpNode = tmpNode.next;
        }
        return tmpNode.element;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {

            private Node<E> currentNode = first;

            private int currentIndex = 0;

            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }

            @Override
            public E next() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E result = currentNode.element;
                currentNode = currentNode.next;
                currentIndex++;
                return result;
            }
        };
    }
}