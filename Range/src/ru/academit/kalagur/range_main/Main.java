package ru.academit.kalagur.range_main;
import ru.academit.kalagur.range.Range;

public class Main {
    public static void main(String[] args) {
        Range range1 = new Range(4, 45);
        Range range2 = new Range(42, 56);

        // вывод результата пересечения отрезков
        Range outputResult;
        if (range1.getIntersection(range2) == null) {
            System.out.println("Пересечений нет");
        } else {
            outputResult = range1.getIntersection(range2);
            double y = outputResult.getTo();
            double x = outputResult.getFrom();
            System.out.printf("Пересечение диапазонов: (%.1f; %.1f)%n", x, y);
        }

        // вывод результата объединения отрезков
        Range[] outputArrayResult = range1.getJoin(range2);
        if (outputArrayResult.length == 2) {
            double x1 = outputArrayResult[0].getFrom();
            double y1 = outputArrayResult[0].getTo();
            double x2 = outputArrayResult[1].getFrom();
            double y2 = outputArrayResult[1].getTo();
            System.out.printf("Объединение диапазонов: (%.1f; %.1f), (%.1f; %.1f)%n", x1, y1, x2, y2);
        } else {
            double x1 = outputArrayResult[0].getFrom();
            double y1 = outputArrayResult[0].getTo();
            System.out.printf("Объединение диапазонов: (%.1f; %.1f)%n", x1, y1);
        }

        // вывод результата разности отрезков
        outputArrayResult = range1.getDifference(range2);
        if (outputArrayResult == null) {
            System.out.println("Пересечений нет");
        } else if (outputArrayResult.length == 2) {
            double x1 = outputArrayResult[0].getFrom();
            double y1 = outputArrayResult[0].getTo();
            double x2 = outputArrayResult[1].getFrom();
            double y2 = outputArrayResult[1].getTo();
            System.out.printf("Разность диапазонов: (%.1f; %.1f), (%.1f; %.1f)%n", x1, y1, x2, y2);
        } else {
            double x1 = outputArrayResult[0].getFrom();
            double y1 = outputArrayResult[0].getTo();
            System.out.printf("Разность диапазонов: (%.1f; %.1f)%n", x1, y1);
        }
    }
}
