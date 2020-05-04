package ru.akademit.kalagur.lambda_main;

import ru.akademit.kalagur.person.Person;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String args[]) {
        List<Person> persons = new ArrayList<>(Arrays.asList(
                new Person(5, "Иван"),
                new Person(25, "Анна"),
                new Person(30, "Оля"),
                new Person(10, "Роман"),
                new Person(24, "Антон"),
                new Person(19, "Роман"),
                new Person(15, "Анна")
        ));

        // получить список уникальных имен
        List<String> uniqueNames = persons.stream()
                .map(Person::getName)
                .distinct()
                .collect(Collectors.toList());

        System.out.println(uniqueNames);

        // получить список людей младше 18, посчитать для них средний возраст
        List<String> youngPeoplesNames = persons.stream()
                .filter(person -> person.getAge() < 18)
                .map(Person::getName)
                .collect(Collectors.toList());

        System.out.println(youngPeoplesNames);

        double youngPeopleAverageAge = persons.stream()
                .filter(person -> person.getAge() < 18)
                .mapToDouble(Person::getAge)
                .average()
                .orElse(-1);

        System.out.println(youngPeopleAverageAge);

        // получить Map, в котором ключи - имена, а значения - средний возраст
        Map<String, Double> personsByAge = persons
                .stream()
                .collect(Collectors.groupingBy(Person::getName, Collectors.averagingDouble(Person::getAge)));

        System.out.println("Средний возраст по именам: " + personsByAge);

        // получить людей, возраст которых от 20 до 45 лет, вывести в консоль их имена в порядке убывания возраста
        List<String> names = persons.stream()
                .filter(p -> p.getAge() >= 20 && p.getAge() <= 45)
                .sorted((p1, p2) -> p2.getAge() - p1.getAge())
                .map(Person::getName)
                .collect(Collectors.toList());

        System.out.println(names);

        // Создать бесконечный поток корней чисел. С консоли прочитать число –
        // сколько элементов нужно вычислить, затем – распечатать эти элементы
        DoubleStream sqrt = DoubleStream
                .iterate(0, x -> x + 1)
                .map(Math::sqrt);

        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите количество выводимых элементов: ");
        int quantity = scanner.nextInt();

        sqrt.limit(quantity).forEach(System.out::println);

        // реализовать бесконечный поток чисел Фибоначчи
        Stream<Integer> fibonacciRow = Stream
                .iterate(new int[]{0, 1}, x -> new int[]{x[1], x[0] + x[1]})
                .map(x -> x[0]);
        fibonacciRow.limit(10).forEach(System.out::println);
    }
}