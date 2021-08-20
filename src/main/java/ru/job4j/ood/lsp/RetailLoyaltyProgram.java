package ru.job4j.ood.lsp;

import java.util.List;

public class RetailLoyaltyProgram extends LoyaltyProgram {

    public RetailLoyaltyProgram(int levels, double discount) {
        super(levels, discount);
    }

    @Override
    public double calculateDiscount(int level, List<Product> products) {
        if (!rules.containsKey(level)) {
            throw new IllegalArgumentException("This discount level is not available!");
        }
        double percent = rules.get(level);
        double sum = 0;
        for (Product product : products) {
            sum += product.getPrice();
        }
        return (sum * percent) / 100.0;
    }
}
