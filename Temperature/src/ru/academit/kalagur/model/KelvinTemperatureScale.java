package ru.academit.kalagur.model;

public class KelvinTemperatureScale implements TemperatureScale {
    @Override
    public double convertToCelsius(double value) {
        return value - 273.15;
    }

    @Override
    public double convertFromCelsius(double value) {
        return value + 273.15;
    }

    @Override
    public String toString() {
        return "Кельвин";
    }
}