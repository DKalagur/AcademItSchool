package ru.academit.kalagur.shapes;

public class Rectangle implements Shape {
    public static final String shapeType = "Прямоугольник";

    private double height;
    private double width;

    public Rectangle(double height, double width) {
        this.height = height;
        this.width = width;
    }

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public double getHeight() {
        return height;
    }

    @Override
    public double getArea() {
        return height * width;
    }

    @Override
    public double getPerimeter() {
        return 2 * (height + width);
    }

    @Override
    public String toString() {
        return "\nТип фигуры: " + shapeType +
                "\nШирина: " + width +
                "\nВысота: " + height +
                "\nПлощадь фигуры: " + getArea() +
                "\nПериметр фигуры: " + getPerimeter();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rectangle rectangle = (Rectangle) o;

        return rectangle.height == height && rectangle.width == width;
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;
        hash = prime * hash + Double.hashCode(height);
        hash = prime * hash + Double.hashCode(width);

        return hash;
    }
}