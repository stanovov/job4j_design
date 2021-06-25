package ru.job4j.homework;

import java.util.*;

public class Frog {

    private static final int WIDTH = 16;

    private static final int HEIGHT = 10;

    private static final int LIMITING_LAPS = 1;

    private final Node mainNode;
    private final Segment finish;
    private final Set<Segment> obstacles;

    private int distance;
    private int lapCount;
    private int length;
    private int minPathLength;

    private ArrayList<ArrayList<Segment>> ways = new ArrayList<>();
    private boolean finished;

    private static class Node {
        final Segment segment;
        final Node parent;

        public Node(Segment segment, Node parent) {
            this.segment = segment;
            this.parent = parent;
        }
    }

    private enum Movement {
        LEFT(1),
        DIAGONALLY_LEFT(2),
        STRAIGHT(3),
        DIAGONALLY_RIGHT(2),
        RIGHT(1);

        int shiftX;
        Movement(int shiftX) {
            this.shiftX = shiftX;
        }
    }

    public Frog(Segment start, Segment finish, Set<Segment> obstacles) {
        Set<Segment> set = new HashSet<>(obstacles);
        set.add(start);
        set.add(finish);
        int validation = obstacles.size() + 2;
        if (set.size() != validation) {
            throw new IllegalArgumentException("Сегменты пересекаются по координатам!");
        }
        if (start.getX() <= 0 || start.getX() > WIDTH
                || start.getY() <= 0 || start.getY() > HEIGHT) {
            throw new IllegalArgumentException("Сегмент лягушки выходит за рамки поля");
        }
        if (finish.getX() <= 0 || finish.getX() > WIDTH
                || finish.getY() <= 0 || finish.getY() > HEIGHT) {
            throw new IllegalArgumentException("Сегмент конечной точки выходит за рамки поля");
        }
        for (Segment segment : obstacles) {
            if (segment.getX() <= 0 || segment.getX() > WIDTH
                    || segment.getY() <= 0 || segment.getY() > HEIGHT) {
                throw new IllegalArgumentException("Сегмент дерева выходит за рамки поля");
            }
        }
        this.mainNode = new Node(start, null);
        this.finish = finish;
        this.obstacles = obstacles;
        this.distance = (finish.getX() <= start.getX())
                ? WIDTH - start.getX() + finish.getX()
                : finish.getX() - start.getX();
    }

    public int calculate() {
        if (!finished) {
            createBranches(mainNode);
            finished = true;
        }
        showResult();
        return minPathLength;
    }

    private void showResult() {
        if (ways.size() == 0) {
            System.out.println("Невозможно добраться из исходного положения в конечное");
        } else {
            System.out.println("Длина пути составила " + minPathLength + " ходов.");
            System.out.println("Всего вариантов пути с таким количеством ходов - " + ways.size() + "");
            System.out.println("Начальные координаты: " + mainNode.segment);
            for (int i = 0; i < ways.size(); i++) {
                System.out.print(i + 1 + ": ");
                List<Segment> way = ways.get(i);
                for (int j = 0; j < way.size(); j++) {
                    System.out.print("ход " + (j + 1) + " - " + way.get(j) + "; ");
                }
                System.out.println();
            }
        }
    }

    private void createBranches(Node node) {
        length++;
        if (ways.size() > 0 && length > minPathLength) {
            length--;
            return;
        }
        mainLoop:
        for (Movement movement : Movement.values()) {
            List<Segment> jumping = getJumping(node.segment, movement);
            Segment lastSegment = jumping.get(2);
            if (lastSegment.getY() < 1 || lastSegment.getY() > 10) {
                continue;
            }
            for (Segment segment : jumping) {
                if (obstacles.contains(segment)) {
                    continue mainLoop;
                }
            }
            Node current = new Node(lastSegment, node);
            if (lastSegment.equals(finish)) {
                if (ways.size() == 0) {
                    minPathLength = length;
                } else {
                    if (minPathLength > length) {
                        minPathLength = length;
                        ways.clear();
                    }
                }
                ArrayList<Segment> way = new ArrayList<>();
                Node cursor = current;
                while (cursor.parent != null) {
                    way.add(cursor.segment);
                    cursor = cursor.parent;
                }
                Collections.reverse(way);
                ways.add(way);
                continue;
            }
            distance -= movement.shiftX;
            if (distance <= 0) {
                distance += WIDTH;
                lapCount++;
                if (lapCount >= LIMITING_LAPS) {
                    lapCount--;
                    distance -= WIDTH;
                    distance += movement.shiftX;
                    continue;
                }
            }
            createBranches(current);
            distance += movement.shiftX;
            if (distance > 16) {
                distance -= WIDTH;
                lapCount--;
            }
        }
        length--;
    }

    private List<Segment> getJumping(Segment start, Movement movement) {
        List<Segment> result = new ArrayList<>();
        if (movement == Movement.STRAIGHT) {
            result.add(new Segment(getX(start, 1), start.getY()));
            result.add(new Segment(getX(start, 2), start.getY()));
            result.add(new Segment(getX(start, 3), start.getY()));
        } else if (movement == Movement.DIAGONALLY_RIGHT) {
            result.add(new Segment(start.getX(), start.getY() - 1));
            result.add(new Segment(getX(start, 1), start.getY() - 1));
            result.add(new Segment(getX(start, 2), start.getY() - 1));
        } else if (movement == Movement.DIAGONALLY_LEFT) {
            result.add(new Segment(start.getX(), start.getY() + 1));
            result.add(new Segment(getX(start, 1), start.getY() + 1));
            result.add(new Segment(getX(start, 2), start.getY() + 1));
        } else if (movement == Movement.RIGHT) {
            result.add(new Segment(start.getX(), start.getY() - 1));
            result.add(new Segment(start.getX(), start.getY() - 2));
            result.add(new Segment(getX(start, 1), start.getY() - 2));
        } else if (movement == Movement.LEFT) {
            result.add(new Segment(start.getX(), start.getY() + 1));
            result.add(new Segment(start.getX(), start.getY() + 2));
            result.add(new Segment(getX(start, 1), start.getY() + 2));
        } else {
            throw new NullPointerException("Необходимо указать тип движения");
        }
        return result;
    }

    private int getX(Segment seg, int v) {
        return (seg.getX() + v > WIDTH) ? seg.getX() - WIDTH + v : seg.getX() + v;
    }

}

