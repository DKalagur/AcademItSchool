package ru.academit.kalagur.model;

public class CelsiusTemperatureScale implements TemperatureScale {
    @Override
    public double convertToCelsius(double value) {
        return value;
    }

    @Override
    public double convertFromCelsius(double value) {
        return value;
    }

    @Override
    public String toString() {
        return "Цельсий";
    }
}