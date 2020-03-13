package ru.academit.kalagur.matrix_main;

import ru.academit.kalagur.matrix.Matrix;
import ru.academit.kalagur.vector.Vector;

public class Main {
    public static void main(String[] args) {
        //проверка работы конструкторов
        double[][] array = {{-1, -3, -9, -7}, {5, 3, 2, 0}};
        Matrix matrix = new Matrix(array);
        System.out.println(matrix.toString());

        Matrix matrix1 = new Matrix(matrix);
        System.out.println("Скопированная матрица: " + matrix1.toString());

        double[] arrayVector = {1, 5, 4};
        Vector[] vector3 = new Vector[]{new Vector(3), new Vector(arrayVector)};
        Matrix matrixVector = new Matrix(vector3);
        System.out.println("Скопированная матрица: " + matrixVector.toString());

        System.out.println(matrix.getRowsQuantity());
        System.out.println(matrix.getColumnsQuantity());

        Matrix matrix2 = new Matrix(5, 2);
        System.out.println(matrix2.getRowVector(4).toString());


        System.out.println(matrix.getMatrixDimensions());
        System.out.println(matrix.getRowVector(1).toString());

        double[] array2 = {4, 3, 7, 6};
        Vector vector = new Vector(array2);
        matrix.setRowVector(1, vector);
        System.out.println(matrix.getRowVector(1).toString());

        System.out.println("Столбец:" + matrix.getColumnVector(3).toString());


        double[][] array4 = new double[][]{{1, 4, 5, 90}, {8, 2, 10, 32}, {9, -1, 0, 11}};
        Matrix matrix3 = new Matrix(array4);
        System.out.println("Исходная матрица: " + matrix3.toString());
        Matrix matrix4 = matrix3.transposeMatrix();
        System.out.println("Транспонированная матрица: " + matrix4.toString());
        matrix4.multiplyScalar(0.5);
        System.out.println("Матрица после умножения на скаляр: " + matrix4.toString());

        double[][] array5 = {{0, 4, 5, 7}, {0, 2, 3, 3}, {0, 1, 5, 3}, {1, 31, 1, 2}};
        Matrix matrix5 = new Matrix(array5);
        double arrayArray = matrix5.getDeterminant();
        System.out.println("Определитель равен: " + arrayArray);
        //System.out.println(matrix5.toString());

        //проверка умножения на вектор

        double[] array33 = {4, 3, 7, 5};
        Vector vector33 = new Vector(array33);
        double[][] array55 = {{11, 3, 11, 0}, {-5, -2, 2, 3}, {4, 4, 0, 3}};
        Matrix matrix55 = new Matrix(array55);

        System.out.println("До умножения: " + matrix5.toString());
        Vector vector22 = new Vector(matrix55.multiplyVector(vector33));
        System.out.println("После умножения: " + vector22.toString());

        //проверка сложения/вычитания на матрицу
        double[][] array6 = {{2, 7, 3, 1, 4}, {6, 2, 1, 9, 4}, {-1, 5, 5, 3, 4}, {4, 7, 10, 4, 4}};
        Matrix matrix6 = new Matrix(array6);
        double[][] array7 = {{1, 2, 3, 4, 3}, {5, 5, 3, 2, 3}, {-1, -5, 0, 2, 3}, {1, 2, 1, 2, 3}};
        Matrix matrix7 = new Matrix(array7);

        //matrix6.addMatrix(matrix7);
        matrix6.subtractMatrix(matrix7);
        System.out.println("После сложения/вычитания: " + matrix6.toString());

        //проверка сложения/вычитания матриц (статические методы)
        double[][] array8 = {{2, 7, 3, 1, 4}, {6, 2, 1, 9, 4}, {-1, 5, 5, 3, 4}, {4, 7, 10, 4, 4}};
        Matrix matrix8 = new Matrix(array8);
        double[][] array9 = {{1, 2, 3, 4, 3}, {5, 5, 3, 2, 3}, {-1, -5, 0, 2, 3}, {1, 2, 1, 2, 3}};
        Matrix matrix9 = new Matrix(array9);

        Matrix matrix10 = new Matrix(Matrix.getSubtractionMatrix(matrix8, matrix9));
        //Matrix matrix10 = new Matrix(Matrix.getSumMatrix(matrix8,matrix9));
        System.out.println("После сложения/вычитания: " + matrix10.toString());

        //проверка умножения вектора
        double[][] array11 = {{2, -1}, {5, 3}};
        Matrix matrix11 = new Matrix(array11);
        double[][] array12 = {{3, 1, 0}, {2, -1, 5}};
        Matrix matrix12 = new Matrix(array12);
        Matrix matrix13 = new Matrix(Matrix.getMultiplicationMatrix(matrix11, matrix12));
        System.out.println("После умножения: " + matrix13.toString());
    }
}
