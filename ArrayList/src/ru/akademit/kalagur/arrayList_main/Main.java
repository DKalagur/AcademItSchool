package ru.akademit.kalagur.arrayList_main;

import ru.akademit.kalagur.arrayList.MyArrayList;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        MyArrayList<Integer> newList = new MyArrayList<>();
        newList.add(23);
        newList.add(12);
        newList.add(-4);
        System.out.println(newList);
        for (Integer tmp : newList) {
            System.out.println(tmp);
        }


        System.out.println("Размер списка: " + newList.size());
        Object[] a = newList.toArray();
        System.out.println("Массив а: " + Arrays.toString(a));
        // проверка remove
        newList.remove((Integer) 11);
        System.out.println(newList);

        // проверка AddAll
        ArrayList<Integer> c = new ArrayList<>(Arrays.asList(10, 63, 6));
        newList.addAll(c);
        System.out.println(newList);

        System.out.println(newList.get(0));
        newList.set(3, 56);
        System.out.println(newList);
        // проверка add
        newList.add(0, 666);
        System.out.println(newList);

        // проверка indexOf
        System.out.println(newList.indexOf(6));
        System.out.println(newList.lastIndexOf(6));

        // проверка containsAll
        System.out.println("Размер массива: " + newList.size());
        ArrayList<Integer> cc = new ArrayList<>(Arrays.asList(2, 3));
        System.out.println(newList.containsAll(cc));

        // проверка removeAll
        System.out.println(newList.removeAll(cc));
        System.out.println(newList);

        // проверка addAll (index)
        ArrayList<Integer> c1 = new ArrayList<>(Arrays.asList(99, 100, 101));
        System.out.println(newList.addAll(4, c1));
        System.out.println(newList);
        //newList.remove(2);
        //System.out.println(newList);

        ArrayList<Integer> c2 = new ArrayList<>(Arrays.asList(23, 101));
        System.out.println(newList.retainAll(c2));
        System.out.println(newList);

        System.out.println("Вместимость списка: " + newList.getCapacity());
        newList.trimToSize();
        System.out.println("Вместимость списка после trimToSize: " + newList.getCapacity());
        newList.ensureCapacity(10);
        System.out.println("Вместимость списка после ensureCapacity: " + newList.getCapacity());

        // проверка increaseCapacity
        newList.add(45);
        newList.add(41);
        System.out.println(newList);

        // проверка <T> T[] toArray(T[] a)
        Integer[] array = new Integer[]{4, 3, 3};
        //Integer[] c4 = newList.toArray(new Integer[5]);
        Integer[] c4 = newList.toArray(array);
        System.out.println("Передаем массив меньшего размера: " + Arrays.toString(c4));
    }
}
