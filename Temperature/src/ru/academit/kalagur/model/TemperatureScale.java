package ru.academit.kalagur.model;

public interface TemperatureScale {
    double convertToCelsius(double value);

    double convertFromCelsius(double value);
}
