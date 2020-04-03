package ru.akademit.kalagur.graph;

import java.util.LinkedList;

public class Graph {
    private int[][] array;
    private int root;

    public Graph(int[][] array, int root) {
        this.array = array;
        this.root = root;

        // проверка если матрица неквадратная
        int length = array[0].length;
        for (int[] e : array) {
            if (e.length != length) {
                throw new IllegalArgumentException("Матрица должна быть квадратной");
            }
        }

    }

    public void iterateInWidth() {
        boolean[] isVisited = new boolean[array.length];
        LinkedList<Integer> queue = new LinkedList<>();
        queue.addLast(root);

        visitInWidth(queue, isVisited);

        for (int i = 0; i < array.length; ++i) {
            if (!isVisited[i]) {
                queue.addLast(i);
                visitInWidth(queue, isVisited);
            }
        }
    }

    private void visitInWidth(LinkedList<Integer> queue, boolean isVisited[]) {
        int currentNode;

        while (!queue.isEmpty()) {
            currentNode = queue.remove();
            if (!isVisited[currentNode]) {
                System.out.print(currentNode + ", ");
                isVisited[currentNode] = true;
            }

            for (int i = 0; i < array.length; ++i) {
                if ((array[currentNode][i] == 1) && (!isVisited[i])) {
                    queue.addLast(i);
                }
            }
        }
    }

    public void iterateInDepth() {
        boolean[] isVisited = new boolean[array.length];
        LinkedList<Integer> stack = new LinkedList<>();
        stack.add(root);

        visitInDepth(stack, isVisited);

        for (int i = 0; i < array.length; ++i) {
            if (!isVisited[i]) {
                stack.addLast(i);
                visitInWidth(stack, isVisited);
            }
        }
    }

    private void visitInDepth(LinkedList<Integer> stack, boolean isVisited[]) {
        int currentNode;

        while (!stack.isEmpty()) {
            currentNode = stack.removeLast();

            if (!isVisited[currentNode]) {
                System.out.print(currentNode + ", ");
                isVisited[currentNode] = true;
            }

            for (int i = 0; i < array.length; ++i) {
                if ((array[currentNode][i] == 1) && (!isVisited[i])) {
                    stack.addLast(i);
                }
            }
        }
    }
}




