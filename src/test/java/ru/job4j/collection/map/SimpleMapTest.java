package ru.job4j.collection.map;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class SimpleMapTest {

    @Test
    public void whenPutDifferentKeys() {
        SimpleMap<String, Object> map = new SimpleMap<>();
        assertTrue(map.put("A", new Object()));
        assertTrue(map.put("B", new Object()));
        assertTrue(map.put("C", new Object()));
    }

    @Test
    public void whenPutSameKeys() {
        SimpleMap<String, Object> map = new SimpleMap<>();
        assertTrue(map.put("A", new Object()));
        assertFalse(map.put("A", new Object()));
        assertFalse(map.put("A", new Object()));
    }

    @Test
    public void whenPutNullableKey() {
        SimpleMap<String, Object> map = new SimpleMap<>();
        assertTrue(map.put(null, new Object()));
        assertFalse(map.put(null, new Object()));
    }

    @Test
    public void whenPutNullableAndNotNullableKeys() {
        SimpleMap<String, Object> map = new SimpleMap<>();
        assertTrue(map.put("A", new Object()));
        assertTrue(map.put(null, new Object()));
        assertTrue(map.put("C", new Object()));
    }

    @Test
    public void whenPutThenGetNotNullable() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 3);
        assertThat(map.get("A"), is(1));
        assertThat(map.get("B"), is(2));
        assertThat(map.get("C"), is(3));
    }

    @Test
    public void whenPutNullableKeyThenGet() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        map.put(null, 42);
        assertThat(map.get(null), is(42));
    }

    @Test
    public void whenGetInEmptyMap() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        assertNull(map.get("test"));
    }

    @Test
    public void whenGetOnNonExistentKey() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 3);
        assertNull(map.get("test"));
    }

    @Test
    public void whenRemoveExistentPairs() {
        SimpleMap<Character, String> map = new SimpleMap<>();
        map.put('a', "AAA");
        map.put('b', "BBB");
        assertTrue(map.remove('a'));
        assertFalse(map.remove('a'));
        assertTrue(map.remove('b'));
        assertFalse(map.remove('b'));
    }

    @Test
    public void whenRemoveOnNonExistentKey() {
        SimpleMap<Character, String> map = new SimpleMap<>();
        map.put('a', "AAA");
        assertFalse(map.remove('b'));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetEmptyFromIt() {
        SimpleMap<Integer, Object> map = new SimpleMap<>();
        map.iterator().next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenCorruptedIt() {
        SimpleMap<Integer, Object> map = new SimpleMap<>();
        map.put(1, new Object());
        map.put(2, new Object());
        Iterator<Integer> it = map.iterator();
        map.put(3, new Object());
        it.next();
    }

    @Test
    public void checkingListExtensibility() {
        SimpleMap<Integer, Object> map = new SimpleMap<>();
        for (int i = 0; i < 20; i++) {
            map.put(i, new Object());
        }
        Iterator<Integer> it = map.iterator();
        int count = 0;
        while (it.hasNext()) {
            it.next();
            count++;
        }
        assertThat(count, is(20));
    }
}