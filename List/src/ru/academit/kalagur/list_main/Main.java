package ru.academit.kalagur.list_main;

import ru.academit.kalagur.list.SinglyLinkedList;

import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        System.out.println(list);
        list.insertFirstElement(4);
        list.insertFirstElement(7);
        list.insertFirstElement(15);
        list.insertFirstElement(30);
        list.insertFirstElement(12);
        //получение размера списка
        System.out.println("Размер списка: " + list.getSize());
        //получение значения первого элемента
        System.out.println("Первый элемент списка: " + list.getFirstElement());
        System.out.println(list);

        //получение/изменение значения по указанному индексу
        System.out.println("Размер списка:" + list.getSize());
        System.out.println("Элемент списка по индексу: " + list.getElement(3));
        System.out.println("Изменённый элемент: " + list.setElement(3, 0));
        System.out.println(list);

        //удаление элемента по индексу
        Integer x1 = list.removeElement(0);
        System.out.println("Удаленный элемент: " + x1);
        System.out.println(list);

        //вставка элемента по индексу
        list.insertElement(0, 1000);
        System.out.println(list);

        //удаление узла по значению
        boolean delElement = list.remove(11);
        System.out.println("После удаления элемента по значению: " + list);
        System.out.println("Элемент удален: " + delElement);

        //удаление первого элемента
        Integer x3 = list.removeFirstElement();
        System.out.println(list);
        System.out.println("Элемент удален: " + x3);

        //разворот списка
        //list.removeElement(0);
        //list.removeElement(0);
        //list.removeElement(0);
        // list.removeElement(0);
        System.out.println("Исходный массив:" + list);
        list.turn();
        System.out.println("Развернутый массив:" + list);

        //копирование списка
        SinglyLinkedList<Integer> copiedList = list.copy();
        System.out.println("Скопированный массив:" + copiedList);

        // удадание первого элемента V2
        SinglyLinkedList<Integer> test = new SinglyLinkedList<>();
        test.insertFirstElement(100);
        System.out.println(test);
        test.removeFirstElement();
        System.out.println(test);

        // проверка remove null-данные
        //list.insertFirstElement(null);
        list.insertElement(1, null);
        list.insertFirstElement(9);
        list.insertFirstElement(10);
        list.insertElement(0, null);
        System.out.println(list);
        System.out.println(list.remove(null));
        System.out.println("После удаления элемента: " + list);
        Integer x = 1;
        Integer y = 1;
        boolean xy = Objects.equals(x, y);
        System.out.println(xy);
    }
}