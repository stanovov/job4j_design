package ru.job4j.ood.srp;

import java.util.List;

public class BankingService {

    public int calculateAmountAtTheEndOfThePeriod(Deposit deposit) {
        return 1;
    }

    public List<String> operationsHistory(Deposit deposit) {
        return List.of();
    }

    public Deposit openDeposit(User user) {
        return new Deposit();
    }

    public boolean sendOffer(User user) {
        return true;
    }

    public List<String> getCreditHistory(User user) {
        return List.of();
    }
}
