package ru.job4j.ood.isp;

import java.util.ArrayList;
import java.util.List;

interface Input {
    void in(String data);
}

interface Output {
    void output();
}

interface Calculator {
    void calculate();
}

interface Internet {
    void connect();
}

class Computer implements Input, Output, Calculator {

    private String buffer;

    @Override
    public void in(String data) {
        this.buffer = data;
    }

    @Override
    public void calculate() {
        this.buffer = "Calc by computer: " + buffer;
    }

    @Override
    public void output() {
        System.out.println(buffer);
    }
}

class Server implements Calculator, Internet {
    @Override
    public void calculate() {
        System.out.println("Do some work!");
    }

    @Override
    public void connect() {
        System.out.println("Try connect ...");
    }
}

class TV implements Input, Output, Calculator {

    private String command;

    @Override
    public void in(String data) {
        this.command = data;
    }

    @Override
    public void calculate() {
        System.out.println("Execute: " + command);
    }

    @Override
    public void output() {
        System.out.println("Show TV program");
    }
}

public class Device {
    public static void main(String[] args) {
        List<Output> list = new ArrayList<>();
        list.add(new TV());
        list.add(new Computer());
        for (Output elem : list) {
            elem.output();
        }
    }
}
