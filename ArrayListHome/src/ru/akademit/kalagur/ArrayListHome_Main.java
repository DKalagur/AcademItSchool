package ru.akademit.kalagur;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ArrayListHome_Main {

    //первая подзадача
    public static void main(String[] args) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new FileInputStream("C:\\Users\\TEMP\\Desktop\\текст.txt"), "windows-1251")) {
            ArrayList<String> lines = new ArrayList<>();

            while (scanner.hasNextLine()) {
                String x = scanner.nextLine();
                lines.add(x);
            }

            System.out.println(lines);
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
        ArrayList<Integer> list2 = new ArrayList<>();

        for (Integer e : list1) {
            if (!list2.contains(e)) {
                list2.add(e);
            }
        }

        System.out.println(list2);
    }
}