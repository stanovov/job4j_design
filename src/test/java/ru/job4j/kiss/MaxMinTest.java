package ru.job4j.kiss;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class MaxMinTest {

    @Test
    public void whenLookingForMaxNumberNotInAnEmptyList() {
        List<Integer> value = List.of(2, 1, 4, 3, 6, 5, 8, 7, 10, 9);
        Integer result =  new MaxMin().max(value, Integer::compare);
        Integer expect = 10;
        assertThat(result, is(expect));
    }

    @Test
    public void whenLookingForMinNumberNotInAnEmptyList() {
        List<Integer> value = List.of(2, 1, 4, 3, 6, 5, 8, 7, 10, 9);
        Integer result =  new MaxMin().min(value, Integer::compare);
        Integer expect = 1;
        assertThat(result, is(expect));
    }

    @Test
    public void whenLookingForMaxCharacterNotInAnEmptyList() {
        List<Character> value = List.of('a', 'b', 'c', 'x', 'y', 'z');
        Character result = new MaxMin().max(value, Character::compare);
        Character expect = 'z';
        assertThat(result, is(expect));
    }

    @Test
    public void whenLookingForMixCharacterNotInAnEmptyList() {
        List<Character> value = List.of('a', 'b', 'c', 'x', 'y', 'z');
        Character result = new MaxMin().min(value, Character::compare);
        Character expect = 'a';
        assertThat(result, is(expect));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenLookingForMaxNumberInAnEmptyList() {
        List<Integer> value = List.of();
        new MaxMin().max(value, Integer::compare);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenLookingForMinCharacterInAnEmptyList() {
        List<Character> value = List.of();
        new MaxMin().min(value, Character::compare);
    }

}