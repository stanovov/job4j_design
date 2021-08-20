package ru.job4j.ood.lsp;

public class LoyaltyProgramForEmployees extends LoyaltyProgram {
    public LoyaltyProgramForEmployees(int levels, double discount) {
        super(levels, discount);
    }

    @Override
    public void setLevels(int levels) {
        this.levels = levels;
        recalculateDiscountRules();
    }

    @Override
    public void setDiscount(double discount) {
        this.discount = discount;
        recalculateDiscountRules();
    }
}
