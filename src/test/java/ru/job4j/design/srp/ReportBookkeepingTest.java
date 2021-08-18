package ru.job4j.design.srp;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.Test;
import java.util.Calendar;
import java.util.Locale;

public class ReportBookkeepingTest {

    @Test
    public void whenBookkeepingReportGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report report = new ReportBookkeeping(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(String.format(Locale.US, "%.2f", worker.getSalary())).append(";")
                .append(System.lineSeparator());
        assertThat(report.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenBookkeepingReportGeneratedSeveralEmployees() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Petr", now, now, 150);
        Employee worker3 = new Employee("Semyon", now, now, 200);
        store.add(worker1);
        store.add(worker2);
        store.add(worker3);
        Report report = new ReportBookkeeping(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker1.getName()).append(";")
                .append(worker1.getHired()).append(";")
                .append(worker1.getFired()).append(";")
                .append(String.format(Locale.US, "%.2f", worker1.getSalary())).append(";")
                .append(System.lineSeparator())
                .append(worker2.getName()).append(";")
                .append(worker2.getHired()).append(";")
                .append(worker2.getFired()).append(";")
                .append(String.format(Locale.US, "%.2f", worker2.getSalary())).append(";")
                .append(System.lineSeparator())
                .append(worker3.getName()).append(";")
                .append(worker3.getHired()).append(";")
                .append(worker3.getFired()).append(";")
                .append(String.format(Locale.US, "%.2f", worker3.getSalary())).append(";")
                .append(System.lineSeparator());
        assertThat(report.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenBookkeepingReportGeneratedNoEmployees() {
        MemStore store = new MemStore();
        Report engine = new ReportBookkeeping(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }
}