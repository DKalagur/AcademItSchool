package ru.academit.kalagur.model;

public class Model {
    private Object fromScale;
    private Object toScale;
    private int value;

    public Model() {
    }

    public Model(Object fromScale, Object toScale, int value) {
        this.fromScale = fromScale;
        this.toScale = toScale;
        this.value = value;
    }

    public Object getFromScale() {
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

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public double convertValue(Object fromScale, Object toScale, int value) {
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
