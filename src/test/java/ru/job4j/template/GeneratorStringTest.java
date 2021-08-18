package ru.job4j.template;

import org.junit.Ignore;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class GeneratorStringTest {
    @Ignore
    @Test
    public void whenParametersAreCorrect() {
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> map = Map.of("name", "Semyon", "subject", "you");
        Generator generator = new GeneratorString();
        String rsl = generator.produce(template, map);
        assertThat(rsl, is("I am a Semyon, Who are you?"));
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenTemplateContainsKeysThatAreNotInMap() {
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> map = Map.of("name", "Semyon");
        Generator generator = new GeneratorString();
        generator.produce(template, map);
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenMapHasExtraKeys() {
        String template = "I am a ${name}?";
        Map<String, String> map = Map.of("name", "Semyon", "subject", "you");
        Generator generator = new GeneratorString();
        generator.produce(template, map);
    }
}