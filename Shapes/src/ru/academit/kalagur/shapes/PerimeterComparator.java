package ru.academit.kalagur.shapes;

import java.util.Comparator;

public final class PerimeterComparator implements Comparator<Shape> {
    @Override
    public int compare(Shape shape1, Shape shape2) {
        if (shape1.getPerimeter() == shape2.getPerimeter()) {
            return 0;
        }
        if (shape1.getPerimeter() > shape2.getPerimeter()) {
            return 1;
        }
        return -1;
    }
}
