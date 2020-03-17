package ru.academit.kalagur.list_main;

import ru.academit.kalagur.list.SinglyLinkedList;

public class Main {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        list.insertFirstElement(4);
        list.insertFirstElement(7);
        list.insertFirstElement(15);
        list.insertFirstElement(30);
        list.insertFirstElement(12);
        //получение размера списка
        System.out.println("Размер списка: " + list.getSize());
        //получение значения первого элемента
        System.out.println("Первый элемент списка: " + list.getFirstElement());
        System.out.println(list.toString());

        //получение/изменение значения по указанному индексу
        System.out.println("Размер списка:" + list.getSize());
        System.out.println("Элемент списка по индексу: " + list.getElement(3));
        System.out.println("Изменённый элемент: " + list.changeElement(3, 0));
        System.out.println(list.toString());

        //удаление элемента по индексу
        Integer x1 = list.removeElement(4);
        System.out.println("Удаленный элемент: " + x1);
        System.out.println(list.toString());

        //вставка элемента по индексу
        list.insertElement(0, 1000);
        System.out.println(list.toString());

        //удаление узла по значению
        boolean delElement = list.removeValue(11);
        System.out.println("После удаления элемента по значению: " + list.toString());
        System.out.println("Элемент удален: " + delElement);

        //удаление первого элемента
        Integer x3 = list.removeFirstElement();
        System.out.println(list.toString());
        System.out.println("Элемент удален: " + x3);

        //разворот списка
        SinglyLinkedList<Integer> invertedList = list.ternList();
        System.out.println("Развернутый массив:" + invertedList.toString());

        //копирование списка
        SinglyLinkedList<Integer> copiedList = list.copyList();
        System.out.println("Новый массив:" + copiedList.toString());
    }
}