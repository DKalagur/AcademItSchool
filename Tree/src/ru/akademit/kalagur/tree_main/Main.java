package ru.akademit.kalagur.tree_main;

import ru.akademit.kalagur.tree.Tree;

import java.util.Comparator;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        Comparator<Integer> c = new IntegerComparator();
        Tree<Integer> tree = new Tree<>(10, c);
        Consumer<Integer> printer = x -> System.out.print(x + ", ");
        // tree.addNode(10);
        tree.addNode(20);
        tree.addNode(5);
        tree.addNode(null);
        tree.addNode(25);
        tree.addNode(21);
        tree.addNode(7);
        tree.addNode(4);
        tree.addNode(6);

        System.out.println("Сортировка в ширину: ");
        tree.iterateInWidth(printer);
        System.out.println();
        System.out.println();

        System.out.println("Сортировка в глубину(рекурсия): ");
        tree.iterateInDepthRecursion(printer);
        System.out.println();
        System.out.println();

        System.out.println("Сортировка в глубину: ");
        tree.iterateInDepth(printer);
        System.out.println();

        //проверка size()
        System.out.println();
        System.out.println("Размер дерева: " + tree.size());
        System.out.println();

        // проверка contains
        System.out.println("Список содержит узел: " + tree.contains(-1));

        // проверка remove
        System.out.println("Узел удален:" + tree.removeNode(2));
        System.out.println("После удаления: ");
        tree.iterateInWidth(printer);
        System.out.println();
        System.out.println();

        Tree<Integer> tree1 = new Tree<>();
        System.out.println("Элемент удален: " + tree1.removeNode(4));
        System.out.println("Граф содержит элемент: " + tree1.contains(4));

        tree1.addNode(20);
        tree1.addNode(25);
        tree1.addNode(2);
        tree1.addNode(18);
        tree1.addNode(null);
        tree1.addNode(10);
        tree1.addNode(4);
        tree1.addNode(14);
        tree1.addNode(12);
        tree1.addNode(16);
        tree1.iterateInDepth(printer);
        System.out.println();

        System.out.println("Удаление успешно: " + tree1.removeNode(2));
        tree1.iterateInDepth(printer);

        System.out.println();
        Tree<Integer> tree3 = new Tree<>();
        System.out.println();
        System.out.println("После удаления null: " + tree3.removeNode(null));

        //проверка работы конструктора с передачей компаратора
        /*Integer x = 7;
        Integer y = 6;
        Comparator<Integer> c = new IntegerComparator();
        System.out.println(c.compare(x, y));
        Tree<Integer> tree1 = new Tree<>(10, c);*/

       /* Vector vector = new Vector(5);
        Vector vector1 = new Vector(3);
        Tree vectorTree = new Tree();
        vectorTree.addNode(vector);
        vectorTree.addNode(vector1);*/
    }
}