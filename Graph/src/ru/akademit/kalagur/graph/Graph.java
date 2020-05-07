package ru.akademit.kalagur.graph;

import java.util.LinkedList;
import java.util.function.Consumer;

public class Graph {
    private int[][] array;

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

    public void iterateInWidth(Consumer<Integer> printer) {
        boolean[] isVisited = new boolean[array.length];
        LinkedList<Integer> queue = new LinkedList<>();

        queue.addLast(0);

        visitInWidth(queue, isVisited, printer);

        for (int i = 0; i < array.length; ++i) {
            if (!isVisited[i]) {
                queue.addLast(i);
                visitInWidth(queue, isVisited, printer);
            }
        }
    }

    private void visitInWidth(LinkedList<Integer> queue, boolean[] isVisited, Consumer<Integer> printer) {
        while (!queue.isEmpty()) {
            int currentNode = queue.remove();

            if (!isVisited[currentNode]) {
                printer.accept(currentNode);
                isVisited[currentNode] = true;
            }

            for (int i = 0; i < array.length; ++i) {
                if (array[currentNode][i] == 1 && !isVisited[i]) {
                    queue.addLast(i);
                }
            }
        }
    }

    public void iterateInDepth(Consumer<Integer> printer) {
        boolean[] isVisited = new boolean[array.length];
        LinkedList<Integer> stack = new LinkedList<>();
        stack.add(0);

        visitInDepth(stack, isVisited, printer);

        for (int i = 0; i < array.length; ++i) {
            if (!isVisited[i]) {
                stack.addLast(i);
                visitInDepth(stack, isVisited, printer);
            }
        }
    }

    private void visitInDepth(LinkedList<Integer> stack, boolean[] isVisited, Consumer<Integer> printer) {
        while (!stack.isEmpty()) {
            int currentNode = stack.removeLast();

            if (!isVisited[currentNode]) {
                printer.accept(currentNode);
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