package ru.job4j.ood.isp;

public interface CRUD {
    boolean create(Item item);
    Item retrieve(int id);
    boolean update(int id, Item item);
    boolean delete(int id);
}
