package ru.academit.kalagur.model;

public class Model {
    private String fromScale;
    private String toScale;
    private double value;

 /*   public Model() {
    }*/

    public Model(String fromScale, String toScale, double value) {
        this.fromScale = fromScale;
        this.toScale = toScale;
        this.value = value;
    }

 /*   public Object getFromScale() {
        return fromScale;
    }

    public void setFromScale(Object fromScale) {
        this.fromScale = fromScale;
    }

    public Object getToScale() {
        return toScale;
    }

    public void setToScale(Object toScale) {
        this.toScale = toScale;
    }

    public double getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }*/

    public double convertValue(String fromScale, String toScale, double value) {
        if (fromScale.equals(toScale)) {
            return value;
        }

        if (fromScale.equals("Цельсий") && toScale.equals("Кельвин")) {
            return value + 273.15;
        }

        if (fromScale.equals("Цельсий") && toScale.equals("Фаренгейт")) {
            return value * 9 / 5 + 32;
        }

        if (fromScale.equals("Кельвин") && toScale.equals("Цельсий")) {
            return value - 273.15;
        }

        if (fromScale.equals("Кельвин") && toScale.equals("Фаренгейт")) {
            return (value - 273.15) * 9 / 5 + 32;
        }

        if (fromScale.equals("Фаренгейт") && toScale.equals("Цельсий")) {
            return (value - 32) * 5 / 9;
        }

        return (value - 32) * 5 / 9 + 273.15;
    }
}
