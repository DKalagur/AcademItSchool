package ru.academit.kalagur.model;

public class Model {
    // при добавлении новой шкалы добавить новый объект
    private final TemperatureScale[] scales;

    public Model(TemperatureScale[] scales) {
        this.scales = scales;
    }

    public TemperatureScale[] getScales() {
        return scales;
    }

    public static double convertTemperature(TemperatureScale fromScale, TemperatureScale toScale, double value) {
        return toScale.convertFromCelsius(fromScale.convertToCelsius(value));
    }
}