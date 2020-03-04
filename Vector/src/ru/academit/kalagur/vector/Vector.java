package ru.academit.kalagur.vector;

import java.util.Arrays;

public class Vector {
    private double[] coordinates;

    public Vector(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n должно быть больше 0");
        }
        coordinates = new double[n];
    }

    public Vector(Vector vector) {
        coordinates = new double[vector.coordinates.length];
        coordinates = Arrays.copyOf(vector.coordinates, vector.coordinates.length);
    }

    public Vector(double[] array) {
        this(array.length);
        coordinates = Arrays.copyOf(array, array.length);
    }

    public Vector(int n, double[] array) {
        this(n);
        coordinates = Arrays.copyOf(array, array.length);
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
        double vectorLength = 0;

        for (double e : coordinates) {
            vectorLength += Math.pow(e, 2);
        }

        return Math.sqrt(vectorLength);
    }

    public double getElement(int number) {
        return coordinates[number];
    }

    public void setElement(int number, double value) {
        coordinates[number] = value;
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
            coordinates = new double[vector.coordinates.length];

            System.arraycopy(tempArray, 0, coordinates, 0, tempArray.length);
        }
    }

    private static Vector[] changeVector(Vector vector1, Vector vector2) {
        if (vector1.coordinates.length > vector2.coordinates.length) {
            Vector newVector2 = new Vector(vector1.coordinates.length);
            System.arraycopy(vector2.coordinates, 0, newVector2.coordinates, 0, vector2.coordinates.length);

            return new Vector[]{vector1, newVector2};
        }

        Vector newVector1 = new Vector(vector2.coordinates.length);
        System.arraycopy(vector1.coordinates, 0, newVector1.coordinates, 0, vector1.coordinates.length);

        return new Vector[]{newVector1, vector2};
    }

    public static Vector addTwoVectors(Vector vector1, Vector vector2) {
        Vector[] arrayVector = changeVector(vector1, vector2);
        Vector resultAddVector = new Vector(arrayVector[0].coordinates.length);

        for (int i = 0; i < resultAddVector.coordinates.length; ++i) {
            resultAddVector.coordinates[i] = arrayVector[0].coordinates[i] + arrayVector[1].coordinates[i];
        }

        return resultAddVector;
    }

    public static Vector subtractTwoVectors(Vector vector1, Vector vector2) {
        Vector[] arrayVector = changeVector(vector1, vector2);
        Vector resultSubtractionVector = new Vector(arrayVector[0].coordinates.length);

        for (int i = 0; i < resultSubtractionVector.coordinates.length; ++i) {
            resultSubtractionVector.coordinates[i] = arrayVector[0].coordinates[i] - arrayVector[1].coordinates[i];
        }

        return resultSubtractionVector;
    }

    public static double multiplyTwoVectors(Vector vector1, Vector vector2) {
        Vector[] arrayVector = changeVector(vector1, vector2);
        double sum = 0;

        for (int i = 0; i < arrayVector[0].coordinates.length; ++i) {
            sum += arrayVector[0].coordinates[i] * arrayVector[1].coordinates[i];
        }

        return sum;
    }
}