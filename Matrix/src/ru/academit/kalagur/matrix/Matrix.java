package ru.academit.kalagur.matrix;

import ru.academit.kalagur.vector.Vector;

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
        if (array.length == 0 || array[0].length == 0) {
            throw new IllegalArgumentException("Количество строк и столбцов матрицы должно быть больше 0");
        }

        int maxLength = array[0].length;
        for (int i = 1; i < array.length; ++i) {
            if (array[i].length > maxLength) {
                maxLength = array[i].length;
            }
        }

        rows = new Vector[array.length];

        for (int i = 0; i < array.length; ++i) {
            rows[i] = new Vector(maxLength, array[i]);
        }
    }

    // конструктор4
    public Matrix(Vector[] vectors) {
        if (vectors.length == 0) {
            throw new IllegalArgumentException("Переданный массив векторов пуст (не содежит векторы)");
        }

        // делаю проверку: если все вектора null-кидаю исключение, если есть хотя бы один вектор не null- нахожу
        // не null вектор с максимальной длиной, а векторы null заменяю на векторы, заполненные нулями
        int maxLength = 0;
        boolean isAllVectorsNull = true;

        for (Vector e : vectors) {
            if (e != null) {
                if (e.getSize() > maxLength) {
                    maxLength = e.getSize();
                }
                isAllVectorsNull = false;
            }
        }

        if (isAllVectorsNull) {
            throw new IllegalArgumentException("Переданный массив содежит только пустые вектора (null)");
        }

        rows = new Vector[vectors.length];

        for (int i = 0; i < vectors.length; ++i) {
            if (vectors[i] != null) {
                rows[i] = new Vector(vectors[i]);
                rows[i].changeSize(maxLength);
            } else {
                rows[i] = new Vector(maxLength);
            }
        }
    }

    // получение размеров матрицы
    public int getRowsQuantity() {
        return rows.length;
    }

    public int getColumnsQuantity() {
        return rows[0].getSize();
    }

    public Vector getRow(int index) {
        if (index < 0 || index >= rows.length) {
            throw new IndexOutOfBoundsException("Введен недопустимый индекс");
        }

        return new Vector(rows[index]);
    }

    public void setRow(int index, Vector vector) {
        if (index < 0 || index >= rows.length) {
            throw new IndexOutOfBoundsException("Введен недопустимый индекс");
        }

        if (vector.getSize() != getColumnsQuantity()) {
            throw new IllegalArgumentException("Размер задаваемого вектора отличается от размера векторов матрицы");
        }

        rows[index] = new Vector(vector);
    }

    public Vector getColumn(int index) {
        if (index < 0 || index >= getColumnsQuantity()) {
            throw new IndexOutOfBoundsException("Введен недопустимый индекс");
        }

        Vector column = new Vector(rows.length);

        for (int i = 0; i < rows.length; ++i) {
            column.setElement(i, rows[i].getElement(index));
        }

        return column;
    }

    // транспонирование матрицы
    public void transpose() {
        Vector[] transpose = new Vector[getColumnsQuantity()];

        for (int i = 0; i < getColumnsQuantity(); ++i) {
            transpose[i] = getColumn(i);
        }

        rows = transpose;
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

        //копирование матрицы в массив
        double[][] matrix = new double[getColumnsQuantity()][getColumnsQuantity()];

        for (int i = 0; i < getColumnsQuantity(); ++i) {
            for (int j = 0; j < getColumnsQuantity(); ++j) {
                matrix[i][j] = rows[i].getElement(j);
            }
        }

        //приведение матрицы к треугольному типу
        int signCount = 0;
        final double epsilon = 1.0e-10;
        int rowLength = matrix[0].length;

        for (int currentRow = 0; currentRow < rowLength; ++currentRow) {
            for (int i = currentRow + 1; i < rowLength; ++i) {
                if (Math.abs(matrix[currentRow][currentRow]) <= epsilon) {
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

        //расчет определителя
        double determinant = 1;

        for (int i = 0; i < rowLength; ++i) {
            determinant *= matrix[i][i];
        }

        //учет знака матрицы
        if (signCount % 2 == 0) {
            return determinant;
        }

        return -determinant;
    }

    private static void changeRows(double[][] matrix, int currentRow) {
        final double epsilon = 1.0e-10;
        int rowLength = matrix[0].length;

        for (int i = currentRow + 1; i < rowLength; ++i) {
            if (Math.abs(matrix[i][currentRow]) > epsilon) {
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
            sb.append(rows[i]).append(", ");
        }

        sb.append(rows[rows.length - 1]).append("}");

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

        for (int i = 0; i < resultMatrix.getRowsQuantity(); ++i) {
            for (int j = 0; j < resultMatrix.getColumnsQuantity(); ++j) {
                double sum = 0;

                for (int k = 0; k < matrix1.getColumnsQuantity(); ++k) {
                    sum += matrix1.rows[i].getElement(k) * matrix2.rows[k].getElement(j);
                }

                resultMatrix.rows[i].setElement(j, sum);
            }
        }

        return resultMatrix;
    }
}