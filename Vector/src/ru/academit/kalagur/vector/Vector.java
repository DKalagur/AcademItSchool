package ru.academit.kalagur.vector;

public class Vector {
    private int n;
    private double[] coordinates;

    public Vector(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n должно быть больше 0");
        } else {
            this.n = n;
            coordinates = new double[n];
        }
    }

    public Vector(Vector vector) {
        this.n = vector.n;
        this.coordinates = new double[n];

        System.arraycopy(vector.coordinates, 0, this.coordinates, 0, vector.n);
    }

    public Vector(double[] array) {
        n = array.length;
        coordinates = new double[n];

        System.arraycopy(array, 0, this.coordinates, 0, n);
    }

    public Vector(int n, double[] array) {
        this(n);
        System.arraycopy(array, 0, this.coordinates, 0, n);

        if (array.length < n) {
            for (int i = array.length; i < n; ++i) {
                coordinates[i] = 0;
            }
        }
    }

    public int getSize() {
        return n;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");

        for (int i = 0; i < n - 1; ++i) {
            sb.append(coordinates[i]);
            sb.append(", ");
        }

        sb.append(coordinates[n - 1]);
        sb.append("}");
        return sb.toString();
    }

    public void addVector(Vector vector) {
        checkArraySize(vector);

        for (int i = 0; i < vector.n; ++i) {
            this.coordinates[i] += vector.coordinates[i];
        }
    }

    public void subVector(Vector vector) {
        checkArraySize(vector);

        for (int i = 0; i < vector.n; ++i) {
            this.coordinates[i] -= vector.coordinates[i];
        }
    }

    public void multipleScalar(double scalar) {
        for (int i = 0; i < n; ++i) {
            coordinates[i] *= scalar;
        }
    }

    public void turnVector() {
        multipleScalar(-1);
    }

    public double getVectorLength() {
        double VectorLength = 0;

        for (int i = 0; i < n; ++i) {
            VectorLength += Math.pow(coordinates[i], 2);
        }

        return Math.sqrt(VectorLength);
    }

    public double getElement(int number) {
        return coordinates[number];
    }

    public void setElement(int number, double value) {
        coordinates[number] = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector vector = (Vector) o;

        if (vector.n != n) {
            return false;
        }

        double epsilon = 1.0e-10;
        for (int i = 0; i < n; ++i) {
            if ((Math.abs(vector.coordinates[i] - coordinates[i])) > epsilon) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;
        hash = prime * hash + n;

        for (int i = 0; i < n; ++i) {
            hash = prime * hash + Double.hashCode(coordinates[i]);
        }

        return hash;
    }

    private void checkArraySize(Vector vector) {
        if (this.n < vector.n) {
            double[] tempArray = this.coordinates;
            this.coordinates = new double[vector.n];

            System.arraycopy(tempArray, 0, this.coordinates, 0, n);

            for (int i = this.n; i < vector.n; ++i) {
                this.coordinates[i] = 0;
            }

            this.n = vector.n;
        } else if (this.n > vector.n) {
            double[] tempArray = vector.coordinates;
            vector.coordinates = new double[this.n];

            System.arraycopy(tempArray, 0, vector.coordinates, 0, vector.n);

            for (int i = vector.n; i < this.n; ++i) {
                vector.coordinates[i] = 0;
            }

            vector.n = this.n;
        }
    }

    public Vector newVectorAddVector(Vector vector) {
        checkArraySize(vector);
        Vector newVectorAdd = new Vector(this.n);

        for (int i = 0; i < newVectorAdd.n; ++i) {
            newVectorAdd.coordinates[i] = this.coordinates[i] + vector.coordinates[i];
        }

        return newVectorAdd;
    }

    public Vector newVectorSubVector(Vector vector) {
        checkArraySize(vector);
        Vector newVectorSub = new Vector(this.n);

        for (int i = 0; i < newVectorSub.n; ++i) {
            newVectorSub.coordinates[i] = this.coordinates[i] - vector.coordinates[i];
        }

        return newVectorSub;
    }

    public Vector newVectorMultipleVector(Vector vector) {
        checkArraySize(vector);
        Vector newVectorMultiple = new Vector(this.n);

        for (int i = 0; i < newVectorMultiple.n; ++i) {
            newVectorMultiple.coordinates[i] = this.coordinates[i] * vector.coordinates[i];
        }

        return newVectorMultiple;
    }
}