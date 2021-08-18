package ru.job4j.design.srp;

import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Calendar;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ReportXMLTest {

    @Test
    public void whenXMLGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report report = new ReportXML(store);
        String expect = getXML(List.of(worker));
        assertThat(report.generate(em -> true), is(expect));
    }

    @Test
    public void whenXMLGeneratedSeveralEmployees() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Petr", now, now, 150);
        Employee worker3 = new Employee("Semyon", now, now, 200);
        store.add(worker1);
        store.add(worker2);
        store.add(worker3);
        Report report = new ReportXML(store);
        String expect = getXML(List.of(worker1, worker2, worker3));
        assertThat(report.generate(em -> true), is(expect));
    }

    @Test
    public void whenXMLGeneratedNoEmployees() {
        MemStore store = new MemStore();
        Report report = new ReportXML(store);
        String expect = getXML(List.of());
        assertThat(report.generate(em -> true), is(expect));
    }

    private String getXML(List<Employee> employees) {
        try {
            JAXBContext context = JAXBContext.newInstance(Employees.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            try (StringWriter writer = new StringWriter()) {
                marshaller.marshal(new Employees(employees), writer);
                return writer.getBuffer().toString();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }
}