package ru.academit.kalagur.shapes_main;

import ru.academit.kalagur.shapes.*;

import java.util.Arrays;

public class Main {
    private static void searchMaxAreaShape(Shape[] shapes) {
        Arrays.sort(shapes, new AreaComparator());
        System.out.println("Фигура с наибольшей площадью:");
        System.out.println(shapes[shapes.length - 1]);
    }

    private static void searchSecondPerimeterShape(Shape[] shapes) {
        Arrays.sort(shapes, new PerimeterComparator());
        System.out.println("Фигура со вторым по величине периметром:");
        System.out.println(shapes[shapes.length - 2]);
    }

    public static void main(String[] args) {
        Square figure1 = new Square(5);
        Square figure2 = new Square(5);
        Circle figure3 = new Circle(10);
        Circle figure4 = new Circle(14);
        Triangle figure5 = new Triangle(0, 0, -3, -8, 6, 6);
        Triangle figure6 = new Triangle(0, 0, 10, 0, 0, 10);
        Rectangle figure7 = new Rectangle(18, 3);
        Rectangle figure8 = new Rectangle(6, 10);

        Shape[] shapes = new Shape[]{figure1, figure2, figure3, figure4, figure5, figure6, figure7, figure8};

        searchMaxAreaShape(shapes);
        searchSecondPerimeterShape(shapes);
    }
}