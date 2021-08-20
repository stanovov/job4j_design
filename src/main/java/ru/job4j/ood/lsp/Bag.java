package ru.job4j.ood.lsp;

import java.util.ArrayList;
import java.util.List;

public class Bag {

    protected final List<Item> bag;

    protected final int maxSize;

    protected final int maxWeight;

    protected int weight;

    public Bag(int maxSize, int maxWeight) {
        this.maxSize = maxSize;
        this.maxWeight = maxWeight;
        this.bag = new ArrayList<>(maxSize);
    }

    public boolean add(Item item) {
        if (bag.size() == maxSize) {
            return false;
        }
        if ((item.getWeight() + weight) > maxWeight) {
            return false;
        }
        weight += item.getWeight();
        return bag.add(item);
    }
}

