package ru.academit.kalagur;

import java.util.Scanner;

public class RangeMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Range range = new Range(0, 20);

        // вычисление длины интервала
        System.out.println("Размер диапазона: "+ range.getLength());

        // проверка на вхождение в диапазон
        System.out.println("Введите число: ");
        double inputNumber = scanner.nextDouble();

        if (range.isInside(inputNumber)) {
            System.out.println("Введенное число входит в диапазон");
        } else {
            System.out.println("Введенное число не входит в диапазон");
        }
    }
}
