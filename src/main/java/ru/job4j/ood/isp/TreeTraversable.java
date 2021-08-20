package ru.job4j.ood.isp;

import java.util.Iterator;

public interface TreeTraversable<T> {
    Iterator<T> preOrder();
    Iterator<T> inOrder();
    Iterator<T> postOrder();
}
