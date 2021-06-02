package ru.job4j.iterator;

import org.hamcrest.core.Is;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThat;

public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(Arrays.asList(1, 2, 3), Is.is(input));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);
        assertThat(Arrays.asList(0, 1, 2, 3), Is.is(input));
    }

    @Test
    public void whenAddAfterFirst() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 0, 42);
        assertThat(Arrays.asList(0, 42, 1, 2), Is.is(input));
    }

    @Test
    public void whenRemoveIf() {
        List<Integer> input = new ArrayList<>(List.of(1, 2, 3, 4, 5));
        ListUtils.removeIf(input, value -> value % 2 == 0);
        assertThat(List.of(1, 3, 5), Is.is(input));
    }

    @Test
    public void whenRemoveIfEmptyList() {
        List<Integer> input = new ArrayList<>();
        ListUtils.removeIf(input, value -> value % 2 == 0);
        assertThat(List.of(), Is.is(input));
    }

    @Test
    public void whenReplaceIf() {
        List<Integer> input = new ArrayList<>(List.of(1, 2, 3, 4, 5));
        ListUtils.replaceIf(input, value -> value < 3, 42);
        assertThat(List.of(42, 42, 3, 4, 5), Is.is(input));
    }

    @Test
    public void whenRemoveAll() {
        List<Integer> input = new ArrayList<>(List.of(1, 2, 3, 4, 5));
        List<Integer> elements = List.of(1, 5);
        ListUtils.removeAll(input, elements);
        assertThat(List.of(2, 3, 4), Is.is(input));
    }

    @Test
    public void whenRemoveAllWithEmptyElements() {
        List<Integer> input = new ArrayList<>(List.of(1, 2, 3));
        List<Integer> elements = List.of();
        ListUtils.removeAll(input, elements);
        assertThat(List.of(1, 2, 3), Is.is(input));
    }

    @Test
    public void whenRemoveAllWithEmptyList() {
        List<Integer> input = new ArrayList<>();
        List<Integer> elements = List.of(1, 2, 3);
        ListUtils.removeAll(input, elements);
        assertThat(List.of(), Is.is(input));
    }

    @Test
    public void whenRemoveAllWithMultipleRepetitionsOfOneNumber() {
        List<Integer> input = new ArrayList<>(List.of(1, 1, 1, 2, 2, 2, 42));
        List<Integer> elements = List.of(1, 2);
        ListUtils.removeAll(input, elements);
        assertThat(List.of(42), Is.is(input));
    }
}