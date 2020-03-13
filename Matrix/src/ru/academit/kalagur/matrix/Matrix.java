package ru.academit.kalagur.matrix;

import ru.academit.kalagur.vector.Vector;

public class Matrix {
    private Vector[] vectors;

    //Конструкторы
    public Matrix(int vectorsQuantity, int vectorsSize) {
        if (vectorsSize <= 0 || vectorsQuantity <= 0) {
            throw new IllegalArgumentException("Длина и ширина вектора должно быть больше 0");
        }

        vectors = new Vector[vectorsQuantity];
        for (int i = 0; i < vectorsQuantity; ++i) {
            vectors[i] = new Vector(vectorsSize);
        }
    }

    public Matrix(Matrix matrix) {
        vectors = new Vector[matrix.vectors.length];

        for (int i = 0; i < matrix.vectors.length; ++i) {
            this.vectors[i] = new Vector(matrix.vectors[i]);
        }
    }

    public Matrix(double[][] array) {
        vectors = new Vector[array.length];

        for (int i = 0; i < array.length; ++i) {
            vectors[i] = new Vector(array[i]);
        }
    }

    public Matrix(Vector[] vector) {
        vectors = new Vector[vector.length];

        for (int i = 0; i < vector.length; ++i) {
            vectors[i] = new Vector(vector[i]);
        }
    }

    //Получение размеров матрицы
    public int getRowsQuantity() {
        return vectors.length;
    }

    public int getColumnsQuantity() {
        return vectors[0].getSize();
    }

    public String getMatrixDimensions() {
        return "Размер матрицы: " + getColumnsQuantity() + "x" + getRowsQuantity();
    }

    public Vector getRowVector(int index) {
        if (index < 0 || index >= vectors.length) {
            throw new IllegalArgumentException("Введен недопустимый индекс");
        }

        return vectors[index];
    }

    public void setRowVector(int index, Vector vector) {
        if (index < 0 || index >= vectors.length) {
            throw new IllegalArgumentException("Введен недопустимый индекс");
        }

        if (vector.getSize() != getColumnsQuantity()) {
            throw new IllegalArgumentException("Размер задаваемого вектора отличается от размера векторов матрицы");
        }

        vectors[index] = vector;
    }

    public Vector getColumnVector(int index) {
        if (index < 0 || index >= getColumnsQuantity()) {
            throw new IllegalArgumentException("Введен недопустимый индекс");
        }

        Vector column = new Vector(vectors.length);

        for (int i = 0; i < vectors.length; ++i) {
            column.setElement(i, vectors[i].getElement(index));
        }

        return column;
    }

    // Транспонирование матрицы
    public Matrix transposeMatrix() {
        Matrix transposedMatrix = new Matrix(getColumnsQuantity(), getRowsQuantity());

        for (int i = 0; i < getColumnsQuantity(); ++i) {
            for (int j = 0; j < getRowsQuantity(); ++j) {
                transposedMatrix.vectors[i].setElement(j, vectors[j].getElement(i));
            }
        }

        return transposedMatrix;
    }

    //Умножение матрицы на скаляр
    public void multiplyScalar(double n) {
        for (Vector e : vectors) {
            e.multiplyScalar(n);
        }
    }

    //Нахождение определителя
    public double getDeterminant() {
        if (getColumnsQuantity() != getRowsQuantity()) {
            throw new IllegalArgumentException("Матрица должна быть квадратной");
        }

        //копирование матрицы в массив
        double[][] matrix = new double[getColumnsQuantity()][getColumnsQuantity()];

        for (int i = 0; i < getColumnsQuantity(); ++i) {
            for (int j = 0; j < getColumnsQuantity(); ++j) {
                matrix[i][j] = vectors[i].getElement(j);
            }
        }

        //приведение матрицы к треугольному типу
        int signCount = 0;
        for (int currentRow = 0; currentRow < matrix[0].length; ++currentRow) {
            for (int i = currentRow + 1; i < matrix[0].length; ++i) {
                if (matrix[currentRow][currentRow] == 0) {
                    changeRows(matrix, currentRow); //диагональные элементы должны быть не нулевые
                    --i;
                    ++signCount;
                } else {
                    double k = matrix[i][currentRow] / matrix[currentRow][currentRow];

                    for (int j = currentRow; j < matrix[0].length; ++j) {
                        matrix[i][j] = matrix[i][j] - matrix[currentRow][j] * k;
                    }
                }
            }
        }

        //расчет определителя
        double determinant = 1;

        for (int i = 0; i < matrix[0].length; ++i) {
            determinant *= matrix[i][i];
        }

        //учет знака матрицы
        if (signCount % 2 == 0) {
            return determinant;
        }

        return -determinant;
    }

    private static void changeRows(double[][] matrix, int currentRow) {
        for (int i = currentRow + 1; i < matrix[0].length; ++i) {
            if (matrix[i][currentRow] != 0) {
                double[] temp = new double[matrix[0].length];

                for (int y = currentRow; y < matrix[0].length; ++y) {
                    temp[y] = matrix[currentRow][y];
                    matrix[currentRow][y] = matrix[currentRow + i][y];
                    matrix[currentRow + i][y] = temp[y];
                }
                return;
            }
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");

        for (int i = 0; i < vectors.length - 1; ++i) {
            sb.append(vectors[i].toString());
            sb.append(", ");
        }

        sb.append(vectors[vectors.length - 1].toString());
        sb.append("}");

        return sb.toString();
    }

    public Vector multiplyVector(Vector vector) {
        if (getColumnsQuantity() != vector.getSize()) {
            throw new IllegalArgumentException("Размер вектора должен совпадать с количеством столбцов матрицы");
        }

        Vector resultVector = new Vector(getRowsQuantity());
        for (int i = 0; i < getRowsQuantity(); ++i) {
            double sum = 0;
            for (int j = 0; j < getColumnsQuantity(); ++j) {
                sum += vectors[i].getElement(j) * vector.getElement(j);
            }
            resultVector.setElement(i, sum);
        }
        return resultVector;
    }

    public void addMatrix(Matrix matrix) {
        if (getColumnsQuantity() != matrix.getColumnsQuantity() || getRowsQuantity() != matrix.getRowsQuantity()) {
            throw new IllegalArgumentException("Размеры матриц должны совпадать");
        }

        for (int i = 0; i < getRowsQuantity(); ++i) {
            for (int j = 0; j < getColumnsQuantity(); ++j) {
                vectors[i].setElement(j, matrix.vectors[i].getElement(j) + vectors[i].getElement(j));
            }
        }
    }

    public void subtractMatrix(Matrix matrix) {
        if (getColumnsQuantity() != matrix.getColumnsQuantity() || getRowsQuantity() != matrix.getRowsQuantity()) {
            throw new IllegalArgumentException("Размеры матриц должны совпадать");
        }

        for (int i = 0; i < getRowsQuantity(); ++i) {
            for (int j = 0; j < getColumnsQuantity(); ++j) {
                double k = vectors[i].getElement(j);
                vectors[i].setElement(j, k - matrix.vectors[i].getElement(j));
            }
        }
    }

    public static Matrix getSumMatrix(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getColumnsQuantity() != matrix2.getColumnsQuantity() || matrix1.getRowsQuantity() != matrix2.getRowsQuantity()) {
            throw new IllegalArgumentException("Размеры матриц должны совпадать");
        }

        Matrix resultMatrix = new Matrix(matrix1.getRowsQuantity(), matrix1.getColumnsQuantity());

        for (int i = 0; i < resultMatrix.getRowsQuantity(); ++i) {
            for (int j = 0; j < resultMatrix.getColumnsQuantity(); ++j) {
                resultMatrix.vectors[i].setElement(j, matrix1.vectors[i].getElement(j) + matrix2.vectors[i].getElement(j));
            }
        }

        return resultMatrix;
    }

    public static Matrix getSubtractionMatrix(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getColumnsQuantity() != matrix2.getColumnsQuantity() || matrix1.getRowsQuantity() != matrix2.getRowsQuantity()) {
            throw new IllegalArgumentException("Размеры матриц должны совпадать");
        }

        Matrix resultMatrix = new Matrix(matrix1.getRowsQuantity(), matrix1.getColumnsQuantity());

        for (int i = 0; i < resultMatrix.getRowsQuantity(); ++i) {
            for (int j = 0; j < resultMatrix.getColumnsQuantity(); ++j) {
                resultMatrix.vectors[i].setElement(j, matrix1.vectors[i].getElement(j) - matrix2.vectors[i].getElement(j));
            }
        }

        return resultMatrix;
    }

    public static Matrix getMultiplicationMatrix(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getColumnsQuantity() != matrix2.getRowsQuantity()) {
            throw new IllegalArgumentException("Количество столбцов матрицы1 должно совпадать с количеством строк матрицы2");
        }

        Matrix resultMatrix = new Matrix(matrix1.getRowsQuantity(), matrix2.getColumnsQuantity());

        for (int z = 0; z < resultMatrix.getRowsQuantity(); ++z) {
            for (int k = 0; k < resultMatrix.getColumnsQuantity(); ++k) {
                double sum = 0;

                for (int j = 0; j < matrix1.getColumnsQuantity(); ++j) {
                    sum += matrix1.vectors[z].getElement(j) * matrix2.vectors[j].getElement(k);
                }
                resultMatrix.vectors[z].setElement(k, sum);
            }
        }

        return resultMatrix;
    }
}





