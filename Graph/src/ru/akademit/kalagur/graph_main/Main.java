package ru.akademit.kalagur.graph_main;

import ru.akademit.kalagur.graph.Graph;

import java.util.function.Consumer;

public class Main {
    public static void main(String args[]) {
        int[][] array = new int[][]{{0, 0, 0, 1, 1, 0, 0, 0}, {0, 0, 0, 0, 1, 0, 0, 0}, {0, 0, 0, 0, 0, 1, 0, 0}, {1, 0, 1, 0, 0, 1, 0, 0},
                {0, 1, 0, 0, 0, 0, 0, 0}, {0, 0, 1, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 1, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0}};

        Graph graph = new Graph(array);
        Consumer<Integer> printer = x -> System.out.print(x + ", ");

        System.out.println("Обход в ширину: ");
        graph.iterateInWidth(printer);
        System.out.println();

        System.out.println();
        System.out.println("Обход в глубину: ");
        graph.iterateInDepth(printer);
    }
}