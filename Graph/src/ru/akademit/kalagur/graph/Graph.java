package ru.akademit.kalagur.graph;

import java.util.LinkedList;
import java.util.function.Consumer;

public class Graph {
    private final int[][] array;

    public Graph(int[][] array) {
        // проверка если матрица неквадратная
        int length = array[0].length;

        if (length != array.length) {
            throw new IllegalArgumentException("Матрица должна быть квадратной");
        }

        for (int[] e : array) {
            if (e.length != length) {
                throw new IllegalArgumentException("Матрица должна быть квадратной");
            }
        }

        this.array = array;
    }

    public void iterateInWidth(Consumer<Integer> consumer) {
        boolean[] isVisited = new boolean[array.length];
        LinkedList<Integer> queue = new LinkedList<>();

        for (int i = 0; i < array.length; ++i) {
            if (!isVisited[i]) {
                queue.add(i);
                visitInWidth(queue, isVisited, consumer);
            }
        }
    }

    private void visitInWidth(LinkedList<Integer> queue, boolean[] isVisited, Consumer<Integer> consumer) {
        while (!queue.isEmpty()) {
            int currentNode = queue.remove();

            if (isVisited[currentNode]) {
                continue;
            } else {
                consumer.accept(currentNode);
                isVisited[currentNode] = true;
            }

            for (int i = 0; i < array.length; ++i) {
                if (array[currentNode][i] == 1 && !isVisited[i]) {
                    queue.add(i);
                }
            }
        }
    }

    public void iterateInDepth(Consumer<Integer> consumer) {
        boolean[] isVisited = new boolean[array.length];
        LinkedList<Integer> stack = new LinkedList<>();

        for (int i = 0; i < array.length; ++i) {
            if (!isVisited[i]) {
                stack.addLast(i);
                visitInDepth(stack, isVisited, consumer);
            }
        }
    }

    private void visitInDepth(LinkedList<Integer> stack, boolean[] isVisited, Consumer<Integer> consumer) {
        while (!stack.isEmpty()) {
            int currentNode = stack.removeLast();

            if (isVisited[currentNode]) {
                continue;
            } else {
                consumer.accept(currentNode);
                isVisited[currentNode] = true;
            }

            for (int i = array.length - 1; i >= 0; --i) {
                if (array[currentNode][i] == 1 && !isVisited[i]) {
                    stack.addLast(i);
                }
            }
        }
    }
}