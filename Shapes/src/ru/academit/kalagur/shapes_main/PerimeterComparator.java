package ru.academit.kalagur.shapes_main;

import ru.academit.kalagur.shapes.Shape;

import java.util.Comparator;

public final class PerimeterComparator implements Comparator<Shape> {
    @Override
    public int compare(Shape shape1, Shape shape2) {
        return Double.compare(shape1.getPerimeter(), shape2.getPerimeter());
    }
}