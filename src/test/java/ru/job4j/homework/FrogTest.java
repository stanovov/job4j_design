package ru.job4j.homework;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class FrogTest {

    @Test
    public void WhenFrogMadeItFinish() {
        Segment start = new Segment(11, 7);
        Segment finish = new Segment(9, 10);
        Set<Segment> trees = new HashSet<>(
                Set.of(
                        new Segment(14, 9),
                        new Segment(5, 8)
                )
        );
        Frog frog = new Frog(start, finish, trees);
        assertThat(frog.calculate(), is(7));
    }

    @Test
    public void WhenTreesGetInWayThenFrogGot() {
        Segment start = new Segment(11, 7);
        Segment finish = new Segment(9, 10);
        Set<Segment> trees = new HashSet<>(
                Set.of(
                        new Segment(5, 1),
                        new Segment(5, 2),
                        new Segment(5, 3),
                        new Segment(5, 5),
                        new Segment(5, 6),
                        new Segment(5, 7),
                        new Segment(5, 8),
                        new Segment(5, 9),
                        new Segment(5, 10)
                )
        );
        Frog frog = new Frog(start, finish, trees);
        assertThat(frog.calculate(), is(9));
    }

    @Test
    public void WhenTreesGetInWayThenFrogNotGot() {
        Segment start = new Segment(11, 7);
        Segment finish = new Segment(9, 10);
        Set<Segment> trees = new HashSet<>(
                Set.of(
                        new Segment(5, 1),
                        new Segment(5, 2),
                        new Segment(5, 3),
                        new Segment(5, 4),
                        new Segment(5, 5),
                        new Segment(5, 6),
                        new Segment(5, 7),
                        new Segment(5, 8),
                        new Segment(5, 9),
                        new Segment(5, 10)
                )
        );
        Frog frog = new Frog(start, finish, trees);
        assertThat(frog.calculate(), is(0));
    }


}