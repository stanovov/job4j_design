package ru.job4j.io;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"),is("Petr Arsentev"));
    }

    @Test
    public void whenPairWithComment() {
        String path = "./data/pair_with_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"),is("Semyon Stanovov"));
    }

    @Test
    public void whenPairWithEmptyStrings() {
        String path = "./data/pair_with_empty_strings.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("city"),is("Tver'"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenWrongPattern() {
        String path = "./data/wrong_pattern.properties";
        Config config = new Config(path);
        config.load();
    }
}