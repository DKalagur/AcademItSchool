package ru.academit.kalagur.main;

import ru.academit.kalagur.model.*;
import ru.academit.kalagur.view.View;

public class Main {
    public static void main(String[] args) {
        TemperatureScale[] scales = new TemperatureScale[]{new CelsiusTemperatureScale(), new KelvinTemperatureScale(), new FahrenheitTemperatureScale()};
        Model model = new Model(scales);
        new View(model);
    }
}