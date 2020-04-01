package ru.akademit.kalagur.hashTable_main;

import ru.akademit.kalagur.hashTable.MyHashTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Main {
    public static void main(String args[]) {
        MyHashTable<Integer> newTable = new MyHashTable<>(8);
        // newTable.getElement(2);
        System.out.println("Список пуст: " + newTable.isEmpty());
        newTable.add(5);
        System.out.println("Емкость списка:" + newTable.getCapacity());
        newTable.add(7);
        newTable.add(0);
        System.out.println("Список пуст: " + newTable.isEmpty());
        System.out.println("Емкость списка:" + newTable.getCapacity());
        System.out.println("Количество элементов:" + newTable.size());
        System.out.println(newTable);

        // проверка remove
        System.out.println(newTable.remove(-1));
        System.out.println(newTable);
        /*MyHashTable<Integer> newTable1 = new MyHashTable<>(3);
        System.out.println(newTable.remove(-1));
        System.out.println(newTable1);*/

        // проверка contains
        System.out.println(newTable.contains(10));

        //проверка итератор
        System.out.println("Проверка итератора:");
        Iterator<Integer> iterator = newTable.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        // проверка toArray
        Object[] array = newTable.toArray();
        System.out.println("Скопированный массив" + Arrays.toString(array));

        MyHashTable<Integer> newTable1 = new MyHashTable<>(4);
        Object[] array1 = newTable1.toArray();
        System.out.println("Скопированный массив" + Arrays.toString(array1));

        // проверка toArray(T[] a)
        Object[] array4 = new Object[]{6, 7, 8, 9, 5};
        //Object[] array3 = newTable.toArray(new Integer [7]);
        Object[] array3 = newTable.toArray(array4);
        System.out.println("Скопированный массив" + Arrays.toString(array3));

        // проверка addAll
        List<Integer> list = new ArrayList<>(Arrays.asList(9, 10, 11, 12, 13));
        newTable.addAll(list);
        System.out.println("После добавления коллекции: " + newTable);
        newTable1.addAll(list);
        System.out.println("После добавления коллекции в пустой массив: " + newTable1);

        // проверка containsAll
        List<Integer> list1 = new ArrayList<>(Arrays.asList(9, 10, 11, 12, 13, 4));
        System.out.println("Список содержит все элементы коллекции: " + newTable.containsAll(list1));
        System.out.println("Содержит элемент:" + newTable.contains(12));

        // проверка removeAll
        List<Integer> list2 = new ArrayList<>(Arrays.asList(666, 4));
        System.out.println("Удален хотя бы один элемент: " + newTable.removeAll(list2));
        System.out.println("После удаления коллекции: " + newTable);

        // проверка retainAll
        List<Integer> list3 = new ArrayList<>(Arrays.asList(10, 9, 11, 12, 5, 13, 7, 1));
        System.out.println("Удален хотя бы один элемент (retainAll): " + newTable.retainAll(list3));
        System.out.println("После удаления коллекции: " + newTable);

        // проверка clear
        newTable.clear();
        System.out.println(newTable);

        // проверка конструкторов
        MyHashTable<Integer> table = new MyHashTable<>();
        System.out.println(table);
        MyHashTable<Integer> table1 = new MyHashTable<>(list3);
        System.out.println(table1);
    }
}
