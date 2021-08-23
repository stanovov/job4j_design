package ru.job4j.ood.dip;

public class OrderProcessing {

    private XMLParser parser = new XMLParser();

    public boolean processing(String xml) {
        parser.parse(xml);
        return true;
    }
}
