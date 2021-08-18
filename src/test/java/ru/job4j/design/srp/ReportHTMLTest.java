package ru.job4j.design.srp;

import org.junit.Test;

import java.util.Calendar;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ReportHTMLTest {

    @Test
    public void whenHTMLReportGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report report = new ReportHTML(store);
        StringBuilder expect = new StringBuilder()
                .append("<table border=\"1\">").append(System.lineSeparator())
                .append("<tr>").append(System.lineSeparator())
                .append("   <th>Name</th>").append(System.lineSeparator())
                .append("   <th>Hired</th>").append(System.lineSeparator())
                .append("   <th>Fired</th>").append(System.lineSeparator())
                .append("   <th>Salary</th>").append(System.lineSeparator())
                .append("</tr>").append(System.lineSeparator())
                .append("<tr>")
                .append("<td>").append(worker.getName()).append("</td>")
                .append("<td>").append(worker.getHired()).append("</td>")
                .append("<td>").append(worker.getFired()).append("</td>")
                .append("<td>").append(worker.getSalary()).append("</td>")
                .append("</tr>")
                .append(System.lineSeparator())
                .append("</table>").append(System.lineSeparator());
        assertThat(report.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenHTMLReportGeneratedSeveralEmployees() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Petr", now, now, 150);
        Employee worker3 = new Employee("Semyon", now, now, 200);
        store.add(worker1);
        store.add(worker2);
        store.add(worker3);
        Report report = new ReportHTML(store);
        StringBuilder expect = new StringBuilder()
                .append("<table border=\"1\">").append(System.lineSeparator())
                .append("<tr>").append(System.lineSeparator())
                .append("   <th>Name</th>").append(System.lineSeparator())
                .append("   <th>Hired</th>").append(System.lineSeparator())
                .append("   <th>Fired</th>").append(System.lineSeparator())
                .append("   <th>Salary</th>").append(System.lineSeparator())
                .append("</tr>").append(System.lineSeparator())
                .append("<tr>")
                .append("<td>").append(worker1.getName()).append("</td>")
                .append("<td>").append(worker1.getHired()).append("</td>")
                .append("<td>").append(worker1.getFired()).append("</td>")
                .append("<td>").append(worker1.getSalary()).append("</td>")
                .append("</tr>")
                .append(System.lineSeparator())
                .append("<tr>")
                .append("<td>").append(worker2.getName()).append("</td>")
                .append("<td>").append(worker2.getHired()).append("</td>")
                .append("<td>").append(worker2.getFired()).append("</td>")
                .append("<td>").append(worker2.getSalary()).append("</td>")
                .append("</tr>")
                .append(System.lineSeparator())
                .append("<tr>")
                .append("<td>").append(worker3.getName()).append("</td>")
                .append("<td>").append(worker3.getHired()).append("</td>")
                .append("<td>").append(worker3.getFired()).append("</td>")
                .append("<td>").append(worker3.getSalary()).append("</td>")
                .append("</tr>")
                .append(System.lineSeparator())
                .append("</table>").append(System.lineSeparator());
        assertThat(report.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenHTMLReportGeneratedNoEmployees() {
        MemStore store = new MemStore();
        Report report = new ReportHTML(store);
        StringBuilder expect = new StringBuilder()
                .append("<table border=\"1\">").append(System.lineSeparator())
                .append("<tr>").append(System.lineSeparator())
                .append("   <th>Name</th>").append(System.lineSeparator())
                .append("   <th>Hired</th>").append(System.lineSeparator())
                .append("   <th>Fired</th>").append(System.lineSeparator())
                .append("   <th>Salary</th>").append(System.lineSeparator())
                .append("</tr>").append(System.lineSeparator())
                .append("</table>").append(System.lineSeparator());
        assertThat(report.generate(em -> true), is(expect.toString()));
    }
}