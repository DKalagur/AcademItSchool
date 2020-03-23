package ru.academit.kalagur.matrix;

import ru.academit.kalagur.vector.Vector;

import java.util.Arrays;

public class Matrix {
    private Vector[] rows;

    // конструктор1
    public Matrix(int rowsQuantity, int columnsQuantity) {
        if (columnsQuantity <= 0 || rowsQuantity <= 0) {
            throw new IllegalArgumentException("Количество строк и столбцов матрицы должно быть больше 0");
        }

        rows = new Vector[rowsQuantity];
        for (int i = 0; i < rowsQuantity; ++i) {
            rows[i] = new Vector(columnsQuantity);
        }
    }

    // конструктор2
    public Matrix(Matrix matrix) {
        rows = new Vector[matrix.rows.length];

        for (int i = 0; i < matrix.rows.length; ++i) {
            rows[i] = new Vector(matrix.rows[i]);
        }
    }

    // конструктор3
    public Matrix(double[][] array) {
        if (array.length <= 0) {
            throw new IllegalArgumentException("Количество строк и столбцов матрицы должно быть больше 0");
        }

        int length = array[0].length;
        if (array.length > 1) {
            for (int i = 1; i < array.length; ++i) {
                if (array[i].length != length) {
                    throw new IllegalArgumentException("Массив должен содержать строки одинакового размера");
                }
            }
        }
        rows = new Vector[array.length];

        for (int i = 0; i < array.length; ++i) {
            rows[i] = new Vector(array[i]);
        }
    }

    // конструктор4
    public Matrix(Vector[] vectors) {
        int length = vectors[0].getSize();
        if (vectors.length > 1) {
            for (int i = 1; i < vectors.length; ++i) {
                if (vectors[i].getSize() != length) {
                    throw new IllegalArgumentException("Массив должен содержать векторы одинакового размера");
                }
            }
        }
        rows = new Vector[vectors.length];

        for (int i = 0; i < vectors.length; ++i) {
            rows[i] = new Vector(vectors[i]);
        }
    }

    // получение размеров матрицы
    public int getRowsQuantity() {
        return rows.length;
    }

    public int getColumnsQuantity() {
        return rows[0].getSize();
    }

    public String getMatrixDimensions() {
        return "Размер матрицы: " + getColumnsQuantity() + "x" + getRowsQuantity();
    }

    public Vector getRow(int index) {
        if (index < 0 || index >= rows.length) {
            throw new IllegalArgumentException("Введен недопустимый индекс");
        }

        return new Vector(rows[index]);
    }

    public void setRow(int index, Vector vector) {
        if (index < 0 || index >= rows.length) {
            throw new ArrayIndexOutOfBoundsException("Введен недопустимый индекс");
        }

        if (vector.getSize() != getColumnsQuantity()) {
            throw new IllegalArgumentException("Размер задаваемого вектора отличается от размера векторов матрицы");
        }

        rows[index] = new Vector(vector);
    }

    public Vector getColumnVector(int index) {
        if (index < 0 || index >= getColumnsQuantity()) {
            throw new IllegalArgumentException("Введен недопустимый индекс");
        }

        Vector column = new Vector(rows.length);

        for (int i = 0; i < rows.length; ++i) {
            column.setElement(i, rows[i].getElement(index));
        }

        return column;
    }

    // транспонирование матрицы
    public void transposeMatrix() {

        Matrix transposedMatrix = new Matrix(getColumnsQuantity(), getRowsQuantity());

        for (int i = 0; i < getColumnsQuantity(); ++i) {
            for (int j = 0; j < getRowsQuantity(); ++j) {
                transposedMatrix.rows[i].setElement(j, rows[j].getElement(i));
            }
        }
        rows = Arrays.copyOf(transposedMatrix.rows, transposedMatrix.rows.length);
    }

    // умножение матрицы на скаляр
    public void multiplyScalar(double n) {
        for (Vector e : rows) {
            e.multiplyByScalar(n);
        }
    }

    // нахождение определителя
    public double getDeterminant() {
        if (getColumnsQuantity() != getRowsQuantity()) {
            throw new IllegalArgumentException("Матрица должна быть квадратной");
        }

        // копирование матрицы в массив
        double[][] matrix = new double[getColumnsQuantity()][getColumnsQuantity()];

        for (int i = 0; i < getColumnsQuantity(); ++i) {
            for (int j = 0; j < getColumnsQuantity(); ++j) {
                matrix[i][j] = rows[i].getElement(j);
            }
        }

        // приведение матрицы к треугольному типу
        int signCount = 0;
        int rowLength = matrix[0].length;
        for (int currentRow = 0; currentRow < rowLength; ++currentRow) {
            double epsilon = 1.0e-10;
            for (int i = currentRow + 1; i < rowLength; ++i) {
                if (Math.abs(matrix[currentRow][currentRow]) > epsilon) {
                    changeRows(matrix, currentRow); //диагональные элементы должны быть не нулевые
                    --i;
                    ++signCount;
                } else {
                    double k = matrix[i][currentRow] / matrix[currentRow][currentRow];

                    for (int j = currentRow; j < rowLength; ++j) {
                        matrix[i][j] = matrix[i][j] - matrix[currentRow][j] * k;
                    }
                }
            }
        }

        // расчет определителя
        double determinant = 1;

        for (int i = 0; i < rowLength; ++i) {
            determinant *= matrix[i][i];
        }

        // учет знака матрицы
        if (signCount % 2 == 0) {
            return determinant;
        }

        return -determinant;
    }

    private static void changeRows(double[][] matrix, int currentRow) {
        int rowLength = matrix[0].length;
        for (int i = currentRow + 1; i < rowLength; ++i) {
            if (matrix[i][currentRow] != 0) {
                double[] temp = new double[rowLength];

                for (int y = currentRow; y < rowLength; ++y) {
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

        for (int i = 0; i < rows.length - 1; ++i) {
            sb.append(rows[i]);
            sb.append(", ");
        }

        sb.append(rows[rows.length - 1].toString());
        sb.append("}");

        return sb.toString();
    }

    public Vector multiplyVector(Vector vector) {
        if (getColumnsQuantity() != vector.getSize()) {
            throw new IllegalArgumentException("Размер вектора должен совпадать с количеством столбцов матрицы");
        }

        Vector resultVector = new Vector(getRowsQuantity());
        for (int i = 0; i < getRowsQuantity(); ++i) {
            resultVector.setElement(i, Vector.getScalarMultiplication(rows[i], vector));
        }

        return resultVector;
    }

    public void addMatrix(Matrix matrix) {
        if (getColumnsQuantity() != matrix.getColumnsQuantity() || getRowsQuantity() != matrix.getRowsQuantity()) {
            throw new IllegalArgumentException("Размеры матриц должны совпадать");
        }

        for (int i = 0; i < getRowsQuantity(); ++i) {
            rows[i].addVector(matrix.rows[i]);
        }
    }

    public void subtractMatrix(Matrix matrix) {
        if (getColumnsQuantity() != matrix.getColumnsQuantity() || getRowsQuantity() != matrix.getRowsQuantity()) {
            throw new IllegalArgumentException("Размеры матриц должны совпадать");
        }

        for (int i = 0; i < getRowsQuantity(); ++i) {
            rows[i].subtractVector(matrix.rows[i]);
        }
    }

    public static Matrix getSumMatrix(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getColumnsQuantity() != matrix2.getColumnsQuantity() || matrix1.getRowsQuantity() != matrix2.getRowsQuantity()) {
            throw new IllegalArgumentException("Размеры матриц должны совпадать");
        }

        Matrix matrix1Copy = new Matrix(matrix1);
        matrix1Copy.addMatrix(matrix2);

        return matrix1Copy;
    }

    public static Matrix getSubtractionMatrix(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getColumnsQuantity() != matrix2.getColumnsQuantity() || matrix1.getRowsQuantity() != matrix2.getRowsQuantity()) {
            throw new IllegalArgumentException("Размеры матриц должны совпадать");
        }

        Matrix matrix1Copy = new Matrix(matrix1);
        matrix1Copy.subtractMatrix(matrix2);

        return matrix1Copy;
    }

    public static Matrix getMultiplicationMatrix(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getColumnsQuantity() != matrix2.getRowsQuantity()) {
            throw new IllegalArgumentException("Количество столбцов матрицы1 должно совпадать с количеством строк матрицы2");
        }

        Matrix resultMatrix = new Matrix(matrix1.getRowsQuantity(), matrix2.getColumnsQuantity());

        for (int k = 0; k < resultMatrix.getRowsQuantity(); ++k) {
            for (int i = 0; i < resultMatrix.getColumnsQuantity(); ++i) {
                double sum = 0;

                for (int j = 0; j < matrix1.getColumnsQuantity(); ++j) {
                    sum += matrix1.rows[k].getElement(j) * matrix2.rows[j].getElement(i);
                }
                resultMatrix.rows[k].setElement(i, sum);
            }
        }

        return resultMatrix;
    }
}