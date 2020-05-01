package ru.academit.kalagur.vector;

import java.util.Arrays;

public class Vector {
    private double[] coordinates;

    public Vector(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Размер вектора должен быть больше 0");
        }

        coordinates = new double[size];
    }

    public Vector(Vector vector) {
        coordinates = Arrays.copyOf(vector.coordinates, vector.coordinates.length);
    }

    public Vector(double[] array) {
        if (array.length <= 0) {
            throw new IllegalArgumentException("Длина массива должна быть больше 0");
        }

        coordinates = Arrays.copyOf(array, array.length);
    }

    public Vector(int size, double[] array) {
        if (size <= 0) {
            throw new IllegalArgumentException("n должен быть больше 0");
        }

        coordinates = Arrays.copyOf(array, size);
    }

    public int getSize() {
        return coordinates.length;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");

        for (int i = 0; i < coordinates.length - 1; ++i) {
            sb.append(coordinates[i]);
            sb.append(", ");
        }

        sb.append(coordinates[coordinates.length - 1]);
        sb.append("}");

        return sb.toString();
    }

    public void addVector(Vector vector) {
        if (coordinates.length < vector.coordinates.length) {
            coordinates = Arrays.copyOf(coordinates, vector.coordinates.length);
        }

        for (int i = 0; i < vector.coordinates.length; ++i) {
            coordinates[i] += vector.coordinates[i];
        }
    }

    public void changeSize(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Размер вектора должен быть больше 0");
        }

        if (size == coordinates.length) {
            return;
        }

        coordinates = Arrays.copyOf(coordinates, size);
    }

    public void subtractVector(Vector vector) {
        if (coordinates.length < vector.coordinates.length) {
            coordinates = Arrays.copyOf(coordinates, vector.coordinates.length);
        }

        for (int i = 0; i < vector.coordinates.length; ++i) {
            coordinates[i] -= vector.coordinates[i];
        }
    }

    public void multiplyByScalar(double scalar) {
        for (int i = 0; i < coordinates.length; ++i) {
            coordinates[i] *= scalar;
        }
    }

    public void turn() {
        multiplyByScalar(-1);
    }

    public double getLength() {
        double sum = 0;

        for (double e : coordinates) {
            sum += Math.pow(e, 2);
        }

        return Math.sqrt(sum);
    }

    public double getElement(int index) {
        return coordinates[index];
    }

    public void setElement(int index, double value) {
        coordinates[index] = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Vector vector = (Vector) o;
        if (vector.coordinates.length != coordinates.length) {
            return false;
        }

        for (int i = 0; i < coordinates.length; ++i) {
            if (vector.coordinates[i] != coordinates[i]) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(coordinates);
    }

    public static Vector getSum(Vector vector1, Vector vector2) {
        Vector copyVector1 = new Vector(vector1);
        copyVector1.addVector(vector2);

        return copyVector1;
    }

    public static Vector getSubtraction(Vector vector1, Vector vector2) {
        Vector copyVector1 = new Vector(vector1);
        copyVector1.subtractVector(vector2);

        return copyVector1;
    }

    public static double getScalarMultiplication(Vector vector1, Vector vector2) {
        double sum = 0;

        int minLength = Math.min(vector1.coordinates.length, vector2.coordinates.length);
        for (int i = 0; i < minLength; ++i) {
            sum += vector1.coordinates[i] * vector2.coordinates[i];
        }

        return sum;
    }
}