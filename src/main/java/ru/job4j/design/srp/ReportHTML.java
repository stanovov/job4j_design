package ru.job4j.design.srp;

import java.util.function.Predicate;

public class ReportHTML implements Report {

    private Store store;

    public ReportHTML(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("<table border=\"1\">").append(System.lineSeparator())
                .append("<tr>").append(System.lineSeparator())
                .append("   <th>Name</th>").append(System.lineSeparator())
                .append("   <th>Hired</th>").append(System.lineSeparator())
                .append("   <th>Fired</th>").append(System.lineSeparator())
                .append("   <th>Salary</th>").append(System.lineSeparator())
                .append("</tr>").append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append("<tr>")
                    .append("<td>").append(employee.getName()).append("</td>")
                    .append("<td>").append(employee.getHired()).append("</td>")
                    .append("<td>").append(employee.getFired()).append("</td>")
                    .append("<td>").append(employee.getSalary()).append("</td>")
                    .append("</tr>")
                    .append(System.lineSeparator());
        }
        text.append("</table>").append(System.lineSeparator());
        return text.toString();
    }
}
