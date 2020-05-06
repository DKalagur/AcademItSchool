package ru.akademit.kalagur.array_list_main;

import ru.akademit.kalagur.array_list.MyArrayList;

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
        newList.remove((Integer) (-4));
        System.out.println("После удаления: " + newList);

        // проверка AddAll
        ArrayList<Integer> c = new ArrayList<>(Arrays.asList(10, 63, 6));
        newList.addAll(c);
        System.out.println("После addAll: " + newList);

        System.out.println(newList.get(0));
        //newList.set(3, 56);
        System.out.println(newList);

        // проверка add
        newList.add(2, 666);
        System.out.println(newList);

        // проверка indexOf
        System.out.println(newList.indexOf(6));
        System.out.println(newList.lastIndexOf(7));

        // проверка contains
        System.out.println("Массив содержит объект: " + newList.contains(66));


        // проверка containsAll
        System.out.println("Размер массива: " + newList.size());
        ArrayList<Integer> cc = new ArrayList<>(Arrays.asList(56, 666, 56));
        System.out.println(newList.containsAll(cc));

        // проверка removeAll
        System.out.println(newList.removeAll(cc));
        System.out.println(newList);
/*
        // проверка addAll (index)
        ArrayList<Integer> c1 = new ArrayList<>(Arrays.asList(99, 100, 101));
        System.out.println(newList.addAll(4, c1));
        System.out.println(newList);
        //newList.remove(2);
        //System.out.println(newList);*/

        ArrayList<Integer> c2 = new ArrayList<>(Arrays.asList(6, 23, 90, 67));
        System.out.println("Проверка retainAll: " + newList.retainAll(c2));
        System.out.println("После retainAll: " + newList);

        newList.add(0, 1000);
        System.out.println("После add(index): " + newList);

// проверка addAll (index)
        System.out.println("Проверка addAll(index)");
        ArrayList<Integer> c1 = new ArrayList<>(Arrays.asList(99, 100, 101, 100));
        System.out.println(newList.addAll(3, c1));
        System.out.println(newList);

// проверка toArray
        Integer[] array2 = {100, 101, 102, 103, 104, 100, 100, 105, 106, 107};
        Integer[] result = newList.toArray(array2);
        ArrayList<Integer> list2 = new ArrayList<>(Arrays.asList(1, 7));
        System.out.println(newList.removeAll(list2));
        System.out.println("После removeAll:" + newList);
        System.out.println("Выдаваемый массив" + Arrays.asList(result));
        System.out.println("Входной массив" + Arrays.asList(array2));

        System.out.println("Проверка работы с null данными");
        MyArrayList<Integer> temp = new MyArrayList<>();
        temp.add(23);
        temp.add(12);
        temp.add(null);
        System.out.println(temp);
        System.out.println("Объект содержит null:" + temp.contains(null));
        System.out.println(temp.remove(null));
        System.out.println("Объект после remove null:" + temp);

        /*System.out.println("Вместимость списка: " + newList.getCapacity());
        newList.trimToSize();
        System.out.println("Вместимость списка после trimToSize: " + newList.getCapacity());
        newList.ensureCapacity(10);
        System.out.println("Вместимость списка после ensureCapacity: " + newList.getCapacity());*/
/*
        // проверка increaseCapacity
        newList.add(45);
        newList.add(41);
        System.out.println(newList);

        // проверка <T> T[] toArray(T[] a)
        Integer[] array = new Integer[]{4, 3, 3};
        //Integer[] c4 = newList.toArray(new Integer[5]);
        Integer[] c4 = newList.toArray(array);
        System.out.println("Передаем массив меньшего размера: " + Arrays.toString(c4));*/

        System.out.println("Создание нового списка");
        MyArrayList<Integer> ap = new MyArrayList<>(0);
        ap.add(34);
        System.out.println(ap);
        ap.add(1, 5);
        System.out.println(ap);
        ap.remove((Integer) 5);
        //System.out.println(ap);
        ap.add(null);
        ap.add(10);
        ap.add(11);
        ap.add(13);
        ap.add(16);
        ap.add(10);
        ap.add(100);
        ap.add(666);
        System.out.println(ap);
        ap.remove((Integer) 11);
        System.out.println(ap);
        System.out.println(ap.contains(13));
        System.out.println(ap.lastIndexOf(10));

        ArrayList<Integer> ccc = new ArrayList<>(Arrays.asList(-1, -2, -3, -4));
        ap.addAll(1, ccc);
        ap.clear();
        System.out.println(ap);

        ArrayList<Integer> c3 = new ArrayList<>(10);
        System.out.println(c3);
    }
}
