package ru.job4j.ood.ocp;

public class Messenger {

    private final String typeMessenger;

    public Messenger(String typeMessenger) {
        this.typeMessenger = typeMessenger;
    }

    public void send(String message, Client client) {
        if (typeMessenger.equals("sms")) {
            System.out.println("the message is sent by SMS");
        } else if (typeMessenger.equals("telegram")) {
            System.out.println("the message is sent by Telegram");
        } else if (typeMessenger.equals("email")) {
            System.out.println("the message is sent by Email");
        } else {
            throw new IllegalArgumentException("There is no such messenger");
        }
    }
}
