package ru.academit.kalagur.model;

public class Model {
    // при добавлении новой шкалы добавить новый объект
    private static TemperatureScale[] scales = new TemperatureScale[]{new Celsius(), new Kelvin(), new Fahrenheit()};

    public static TemperatureScale[] getScales() {
        return scales;
    }

    public static double convertValue(TemperatureScale fromScale, TemperatureScale toScale, double value) {
        return toScale.convertFromCelsius(fromScale.convertToCelsius(value));
    }
}