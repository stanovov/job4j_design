package ru.job4j.ood.lsp;

public class Backpack extends Bag {

    public Backpack(int maxSize, int maxWeight) {
        super(maxSize, maxWeight);
    }

    @Override
    public boolean add(Item item) {
        if (bag.size() == maxSize) {
            return false;
        }
        if ((item.getWeight() + weight) > (maxWeight / 2)) { // Предусловие усилино
            return false;
        }
        weight += item.getWeight();
        return bag.add(item);
    }
}
