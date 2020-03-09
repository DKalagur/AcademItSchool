package ru.academit.kalagur.range_main;

import ru.academit.kalagur.range.Range;

public class Main {
    public static void main(String[] args) {
        Range range1 = new Range(20, 48);
        Range range2 = new Range(12, 41);

        // вывод результата пересечения отрезков
        Range intersectionResult;
        if (range1.getIntersection(range2) == null) {
            System.out.println("Пересечений нет");
        } else {
            intersectionResult = range1.getIntersection(range2);
            System.out.println("Пересечение диапазонов:" + intersectionResult.toString());
        }

        // вывод результата объединения отрезков
        Range[] unionResult = range1.getUnion(range2);

        if (unionResult.length == 2) {
            System.out.println("Объединение диапазонов:" + unionResult[0].toString() + unionResult[1].toString());
        } else {
            System.out.println("Объединение диапазонов:" + unionResult[0].toString());
        }

        // вывод результата разности отрезков
        Range[] differenceResult = range1.getDifference(range2);

        if (differenceResult.length == 0) {
            System.out.println("Разность отрезков равна 0");
        } else if (differenceResult.length == 2) {
            System.out.println("Разность диапазонов:" + differenceResult[0].toString() + differenceResult[1].toString());
        } else {
            System.out.println("Разность диапазонов:" + differenceResult[0].toString());
        }
    }
}