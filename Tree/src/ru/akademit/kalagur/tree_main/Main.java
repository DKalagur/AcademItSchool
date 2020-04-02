package ru.akademit.kalagur.tree_main;

import ru.akademit.kalagur.tree.Tree;

import java.util.Comparator;

public class Main {
    public static void main(String args[]) {
        Comparator<Integer> c = new IntegerComparator();
        Tree<Integer> tree = new Tree<>(10, c);

        // Tree<Integer> tree = new Tree<>();
        // tree.addNode(10);
        tree.addNode(20);
        tree.addNode(5);
        tree.addNode(25);
        tree.addNode(21);
        tree.addNode(7);
        tree.addNode(4);
        tree.addNode(6);
        tree.iterateInWidth();
        System.out.println();
        tree.iterateInDepthRecursion();
        System.out.println();
        tree.iterateInDepth();

        //проверка size()
        System.out.println();
        System.out.println("Размер дерева: " + tree.size());

        // проверка contains
        System.out.println();
        System.out.println("Список содержит узел: " + tree.contains(26));

        // проверка remove
        System.out.println("Узел удален:" + tree.removeNode(7));
        tree.iterateInWidth();
        System.out.println();

        //проверка работы конструктора с передачей компаратора
        /*Integer x = 7;
        Integer y = 6;
        Comparator<Integer> c = new IntegerComparator();
        System.out.println(c.compare(x, y));
        Tree<Integer> tree1 = new Tree<>(10, c);*/
    }
}
