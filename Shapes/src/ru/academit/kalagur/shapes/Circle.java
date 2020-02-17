package ru.academit.kalagur.shapes;

public class Circle implements Shape {
    public static final String shapeType = "Круг";

    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double getWidth() {
        return 2 * radius;
    }

    @Override
    public double getHeight() {
        return 2 * radius;
    }

    @Override
    public double getArea() {
        return radius * radius * Math.PI;
    }

    @Override
    public double getPerimeter() {
        return 2 * radius * Math.PI;
    }

    @Override
    public String toString() {
        return "\nТип фигуры: " + shapeType +
                "\nРадиус: " + radius +
                "\nПлощадь фигуры: " + getArea() +
                "\nПериметр фигуры: " + getPerimeter();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Circle circle = (Circle) o;

        return circle.radius == radius;
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;
        hash = prime * hash + Double.hashCode(radius);

        return hash;
    }
}
