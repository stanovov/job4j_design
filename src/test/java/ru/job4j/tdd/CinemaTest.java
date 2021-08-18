package ru.job4j.tdd;

import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class CinemaTest {

    @Test
    public void buy() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 10, 23, 00);
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertThat(ticket, is(new Ticket3D()));
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenBuyTicketsForOneSeat() {
        Account account1 = new AccountCinema();
        Account account2 = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, 11, 20, 12, 10);
        cinema.buy(account1, 1, 1, date);
        cinema.buy(account2, 1, 1, date);
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenBuyTicketInThePast() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2018, 3, 20, 12, 10);
        cinema.buy(account, 1, 1, date);
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenBuyTicketWithIncorrectRow() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, 3, 20, 12, 10);
        cinema.buy(account, -99, 1, date);
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenBuyTicketWithIncorrectColumn() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, 3, 20, 12, 10);
        cinema.buy(account, 1, 100500, date);
    }

    @Test
    public void find() {
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        List<Session> sessions = cinema.find(session -> true);
        assertThat(sessions, is(Arrays.asList(new Session3D())));
    }

    @Ignore
    @Test
    public void whenCouldNotFind() {
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        List<Session> sessions = cinema.find(session -> false);
        assertThat(sessions, is(List.of()));
    }

    @Test
    public void add() {
        Cinema cinema = new Cinema3D();
        Session expect = new Session3D();
        cinema.add(expect);
        assertThat(expect, is(cinema.find(session -> true).get(0)));
    }
}