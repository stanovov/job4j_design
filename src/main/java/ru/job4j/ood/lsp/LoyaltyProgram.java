package ru.job4j.ood.lsp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoyaltyProgram {

    /**
     * key - loyalty level
     * value - discount percent
     */
    protected Map<Integer, Double> rules = new HashMap<>();

    protected int levels;

    protected double discount;

    public LoyaltyProgram(int levels, double discount) {
        validate(levels, discount);
        this.levels = levels;
        this.discount = discount;
        recalculateDiscountRules();
    }

    public void setLevels(int levels) {
        validateLevels(levels);
        this.levels = levels;
        recalculateDiscountRules();
    }

    public void setDiscount(double discount) {
        validateDiscount(discount);
        this.discount = discount;
        recalculateDiscountRules();
    }

    public double calculateDiscount(int level, List<Product> products) {
        if (!rules.containsKey(level)) {
            throw new IllegalArgumentException("This discount level is not available!");
        }
        double percent = rules.get(level);
        double sum = 0;
        for (Product product : products) {
            sum += product.getPrice();
        }
        if (sum < 100_00) {
            return 0.0;
        }
        return roundOff((sum * percent) / 100.0);
    }

    protected void recalculateDiscountRules() {
        rules.clear();
        for (int i = 1; i <= levels; i++) {
            rules.put(i, roundOff(discount * i));
        }
    }

    protected double roundOff(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    private void validateLevels(int levels) {
        if (levels < 1) {
            throw new IllegalArgumentException("Invalid levels!");
        }
    }

    private void validateDiscount(double discount) {
        if (discount <= 0 || (discount * levels) >= 100) {
            throw new IllegalArgumentException("Invalid discount");
        }
    }

    private void validate(int levels, double discount) {
        validateLevels(levels);
        validateDiscount(discount);
    }

    public static void main(String[] args) {
        LoyaltyProgram program = new LoyaltyProgram(10, 2.5);
        List<Product> products = List.of(
                new Product("Телефон", 2310.1022)
        );
        System.out.println(program.calculateDiscount(4, products));
    }

}
