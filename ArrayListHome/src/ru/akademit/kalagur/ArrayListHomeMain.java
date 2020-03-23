package ru.akademit.kalagur;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ArrayListHomeMain {

    //первая подзадача
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(new FileInputStream("текст.txt"), "windows-1251")) {
            ArrayList<String> lines = new ArrayList<>();

            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }

            System.out.println(lines);
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла");
        }

        //вторая подзадача
        ArrayList<Integer> intNumbers = new ArrayList<>(Arrays.asList(4, -1, 8, 3, 1, 0, 3));

        for (int i = 0; i < intNumbers.size(); ++i) {
            if (intNumbers.get(i) % 2 == 0) {
                intNumbers.remove(i);
                --i;
            }
        }

        System.out.println(intNumbers);

        //третья подзадача
        ArrayList<Integer> list1 = new ArrayList<>(Arrays.asList(4, -1, 1, 3, 1, 0, 3, 4, 0, 8, 8));
        ArrayList<Integer> list2 = new ArrayList<>(list1.size());

        for (Integer e : list1) {
            if (!list2.contains(e)) {
                list2.add(e);
            }
        }

        System.out.println(list2);
    }
}