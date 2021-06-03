package ru.job4j.collection.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        int index = indexFor(hash(key));
        if (table[index] != null) {
            return false;
        }
        MapEntry<K, V> pair = new MapEntry<>(key, value);
        table[index] = pair;
        modCount++;
        if (++count > (LOAD_FACTOR * capacity)) {
            expand();
        }
        return true;
    }

    private int hash(K key) {
        return (key == null) ? 0 : hash(key.hashCode());
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return (capacity - 1) & hash;
    }

    private void expand() {
        int oldCapacity = capacity;
        capacity = capacity << 1;
        MapEntry<K, V>[] oldTable = table;
        MapEntry<K, V>[] newTable = new MapEntry[capacity];
        for (int i = 0; i < oldCapacity; i++) {
            MapEntry<K, V> pair = oldTable[i];
            if (pair != null) {
                oldTable[i] = null;
                int hash = hash(pair.key);
                newTable[indexFor(hash)] = pair;
            }
        }
        table = newTable;
    }

    @Override
    public V get(K key) {
        int index = indexFor(hash(key));
        MapEntry<K, V> pair = table[index];
        return pair != null && Objects.equals(pair.key, key) ? pair.value : null;
    }

    @Override
    public boolean remove(K key) {
        boolean result = false;
        int index = indexFor(hash(key));
        MapEntry<K, V> pair = table[index];
        if (pair != null && Objects.equals(pair.key, key)) {
            result = true;
            table[index] = null;
            modCount++;
            count--;
        }
        return result;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {

            private int cursor = 0;

            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                for (int i = cursor; i < capacity; i++) {
                    if (table[i] != null) {
                        cursor = i;
                        return true;
                    }
                }
                return false;
            }

            @Override
            public K next() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[cursor++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

    }
}