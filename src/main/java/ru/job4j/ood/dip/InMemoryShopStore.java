package ru.job4j.ood.dip;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class InMemoryShopStore implements ShopStore {

    private HashMap<User, Set<Order>> store = new HashMap<>();

    @Override
    public boolean saveUser(User user) {
        if (store.containsKey(user)) {
            return false;
        }
        return store.put(user, new HashSet<>()) != null;
    }

    @Override
    public boolean saveOrder(User user, Order order) {
        Set<Order> orders = store.getOrDefault(user, Set.of());
        if (orders.isEmpty()) {
            return false;
        }
        return orders.add(order);
    }

    @Override
    public Set<Order> getOrders(User user) {
        return store.get(user);
    }

    @Override
    public Set<User> getUsers() {
        return store.keySet();
    }
}
