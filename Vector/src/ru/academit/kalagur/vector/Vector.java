package ru.academit.kalagur.vector;

import java.util.Arrays;

public class Vector {
    private double[] coordinates;

    //Конструкторы
    public Vector(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n должно быть больше 0");
        }
        coordinates = new double[n];
    }

    public Vector(Vector vector) {
        coordinates = Arrays.copyOf(vector.coordinates, vector.coordinates.length);
    }

    public Vector(double[] array) {
        coordinates = Arrays.copyOf(array, array.length);
    }

    public Vector(int n, double[] array) {
        coordinates = Arrays.copyOf(array, n);
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
        checkArraySize(vector);

        for (int i = 0; i < vector.coordinates.length; ++i) {
            coordinates[i] += vector.coordinates[i];
        }
    }

    public void subtractVector(Vector vector) {
        checkArraySize(vector);

        for (int i = 0; i < vector.coordinates.length; ++i) {
            coordinates[i] -= vector.coordinates[i];
        }
    }

    public void multiplyScalar(double scalar) {
        for (int i = 0; i < coordinates.length; ++i) {
            coordinates[i] *= scalar;
        }
    }

    public void turnVector() {
        multiplyScalar(-1);
    }

    public double getVectorLength() {
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
            if (vector.coordinates[i] == coordinates[i]) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(coordinates);
    }

    private void checkArraySize(Vector vector) {
        if (coordinates.length < vector.coordinates.length) {
            double[] tempArray = coordinates;
            coordinates = Arrays.copyOf(tempArray, vector.coordinates.length);
        }
    }

    private static Vector[] changeVector(Vector vector1, Vector vector2) {
        if (vector1.coordinates.length > vector2.coordinates.length) {
            Vector newVector2 = new Vector(vector1.coordinates.length);
            newVector2.coordinates = Arrays.copyOf(vector2.coordinates, newVector2.coordinates.length);

            return new Vector[]{vector1, newVector2};
        }

        Vector newVector1 = new Vector(vector2.coordinates.length);
        newVector1.coordinates = Arrays.copyOf(vector1.coordinates, newVector1.coordinates.length);

        return new Vector[]{newVector1, vector2};
    }

    public static Vector getSum(Vector vector1, Vector vector2) {
        Vector[] arrayVector = changeVector(vector1, vector2);
        arrayVector[0].addVector(arrayVector[1]);

        return arrayVector[0];
    }

    public static Vector getSubtraction(Vector vector1, Vector vector2) {
        Vector[] arrayVector = changeVector(vector1, vector2);
        arrayVector[0].subtractVector(arrayVector[1]);

        return arrayVector[0];
    }

    public static double getMultiplication(Vector vector1, Vector vector2) {
        Vector[] arrayVector = changeVector(vector1, vector2);
        int length;

        if (vector1.coordinates.length > vector2.coordinates.length) {
            length = vector2.coordinates.length;
        } else {
            length = vector1.coordinates.length;
        }

        double sum = 0;

        for (int i = 0; i < length; ++i) {
            sum += arrayVector[0].coordinates[i] * arrayVector[1].coordinates[i];
        }

        return sum;
    }
}