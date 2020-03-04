package ru.academit.kalagur.vector_main;

import ru.academit.kalagur.vector.Vector;

import static ru.academit.kalagur.vector.Vector.*;

public class Main {
    public static void main(String[] args) {
        //тестирование конструкторов
        System.out.println("Проверка работы 1-го конструктора");
        Vector vector1 = new Vector(3);
        System.out.println("Длина вектора: " + vector1.getSize());
        System.out.println("Координаты вектора: " + vector1.toString());

        System.out.println("Проверка работы 2-го конструктора");
        double[] array = {1.0, 2.0, -4.0, -90};
        //double[] array = {};
        Vector vector2 = new Vector(array);
        System.out.println("Длина вектора: " + vector2.getSize());
        System.out.println("Координаты вектора: " + vector2.toString());

        System.out.println("Проверка работы 3-го конструктора");
        Vector vector3 = new Vector(vector2);
        System.out.println("Длина вектора: " + vector3.getSize());
        System.out.println("Координаты вектора: " + vector3.toString());

        System.out.println("Проверка работы 4-го конструктора");
        double[] array2 = {11.0, 33.0, 40.0, 45, 56, 90, 99};
        double[] array3 = {1.0, 5.0, -1.0, 1, 1};
        Vector vector41 = new Vector(9, array2);
        System.out.println("Координаты вектора: " + vector41.toString());
        Vector vector42 = new Vector(5, array3);
        System.out.println("Координаты вектора: " + vector42.toString());

        System.out.println("Проверка работы сложения векторов");
        double[] arrayAdd1 = {11.0, 33.0, 40.0, 45};
        double[] arrayAdd2 = {1.0, 5.0};
        //double[] arrayAdd2 = {1.0, 5.0, 7, 12, 65, 1, 3};
        Vector vector51 = new Vector(arrayAdd1);
        Vector vector52 = new Vector(arrayAdd2);
        System.out.print(vector51.toString() + " + " + vector52.toString() + " = ");
        vector51.addVector(vector52);
        System.out.println(vector51.toString());

        System.out.println("Проверка работы вычитания векторов");
        double[] arraySub1 = {11.0, 33.0, 40.0, 45};
        //double[] arraySub2 = {1.0, 5.0};
        double[] arraySub2 = {1.0, 5.0, 7, 12, 65, 1, 3};
        Vector vector5 = new Vector(arraySub1);
        Vector vector6 = new Vector(arraySub2);
        System.out.print(vector5.toString() + " - " + vector6.toString() + " = ");
        vector5.subtractVector(vector6);
        System.out.println(vector5.toString());

        System.out.println("Проверка работы умножения вектора на скаляр");
        vector6.multiplyScalar(4);
        System.out.println(vector6.toString());
        System.out.println("Проверка разворота вектора");
        vector6.turnVector();
        System.out.println(vector6.toString());

        System.out.println("Проверка работы получения длины вектора");
        double[] arrayLength = {1, 3, 2};
        Vector vector7 = new Vector(arrayLength);
        System.out.println("Длина вектора: " + vector7.getVectorLength());

        System.out.println("Проверка работы получения и установки компоненты вектора по индексу");
        System.out.println("Получить элемент: " + vector7.getElement(1));
        vector7.setElement(2, 666);
        System.out.println(vector7.toString());

        System.out.println("Проверка equals и hashCode");
        Vector vector81 = new Vector(arrayLength);
        Vector vector82 = new Vector(arrayLength);
        System.out.println("Хэш-код вектора: " + vector81.hashCode());
        System.out.println("Хэш-код аналогичного вектора: " + vector82.hashCode());
        System.out.println("Хэш-код другого вектора: " + vector7.hashCode());

        if (vector81.equals(vector82)) {
            System.out.println("Векторы равны");
        }
        if (vector81.equals(vector7)) {
            System.out.println("Векторы равны");
        } else {
            System.out.println("Векторы не равны");
        }

        System.out.println("Проверка сложения, вычитания, умножения двух векторов с созданием нового вектора");
        double[] arrayAdd3 = {11.0, 33.0, 40.0, 45};
        //double[] arrayAdd4 = {1.0, 5.0};
        double[] arrayAdd4 = {1.0, 5.0, 7, 12, 65, 1, 3};
        Vector vector91 = new Vector(arrayAdd3);
        Vector vector92 = new Vector(arrayAdd4);

        Vector newVector = addTwoVectors(vector91, vector92);
        System.out.println(newVector.toString());

        Vector newVector2 = subtractTwoVectors(vector91, vector92);
        System.out.println(newVector2.toString());

        double newVector3 = multiplyTwoVectors(vector91, vector92);
        System.out.println(newVector3);
    }
}
