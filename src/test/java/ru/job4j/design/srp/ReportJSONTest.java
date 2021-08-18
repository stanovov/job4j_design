package ru.job4j.design.srp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Test;

import java.util.Calendar;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ReportJSONTest {

    private final Gson gson = new GsonBuilder().create();

    @Test
    public void whenJSONGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report report = new ReportJSON(store);
        String expect = gson.toJson(List.of(worker));
        assertThat(report.generate(em -> true), is(expect));
    }

    @Test
    public void whenJSONGeneratedSeveralEmployees() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Petr", now, now, 150);
        Employee worker3 = new Employee("Semyon", now, now, 200);
        store.add(worker1);
        store.add(worker2);
        store.add(worker3);
        Report report = new ReportJSON(store);
        String expect = gson.toJson(
                List.of(worker1, worker2, worker3)
        );
        assertThat(report.generate(em -> true), is(expect));
    }

    @Test
    public void whenJSONGeneratedNoEmployees() {
        MemStore store = new MemStore();
        Report report = new ReportJSON(store);
        String expect = gson.toJson(List.of());
        assertThat(report.generate(em -> true), is(expect));
    }
}