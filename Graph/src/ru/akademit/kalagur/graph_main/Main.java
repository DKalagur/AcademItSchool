package ru.akademit.kalagur.graph_main;

import ru.akademit.kalagur.graph.Graph;

public class Main {
    public static void main(String args[]) {
        int[][] array = new int[][]{{0, 0, 0, 1, 1, 0, 0, 0}, {0, 0, 0, 0, 1, 0, 0, 0}, {0, 0, 0, 0, 0, 1, 0, 0}, {1, 0, 1, 0, 0, 1, 0, 0},
                {0, 1, 0, 0, 0, 0, 0, 0}, {0, 0, 1, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 1, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0}};

        Graph graph = new Graph(array, 0);
        System.out.println("Сортировка в ширину");
        graph.iterateInWidth();
        System.out.println();
        System.out.println("Сортировка в глубину");
        graph.iterateInDepth();
    }
}
