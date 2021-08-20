package ru.job4j.ood.isp;

import java.util.Iterator;

/**
 * Всем деревьям придется реализовывать все обходы (либо наследоваться от абстрактного дерева которое это делает).
 * @param <T>
 */
public interface Traversable<T> {

    Iterator<T> preOrder();
    Iterator<T> inOrder();
    Iterator<T> postOrder();

    Iterator<T> bfs();
    Iterator<T> dfs();

}
