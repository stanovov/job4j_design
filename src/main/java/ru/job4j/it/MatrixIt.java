package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        boolean nextItemExists = false;
        if (data[row].length > column) {
            nextItemExists = data[row].length > column;
        } else {
            for (int i = 1; (row + i) < data.length; i++) {
                if (data[row + i].length > 0) {
                    column = 0;
                    row = row + i;
                    nextItemExists = true;
                    break;
                }
            }
        }
        return nextItemExists;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[row][column++];
    }

    public static void main(String[] args) {
        MatrixIt it = new MatrixIt(new int[][]{
                {1}, {2}, {3, 4}
        });
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}
