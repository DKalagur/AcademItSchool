package ru.academit.kalagur;

public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    /*// геттеры и сеттеры
    public double getFrom() {
        return from;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public double getTo() {
        return to;
    }

    public void setTo(double to) {
        this.to = from;
    }*/

    // метод расчета длины интервала
    public double getLength() {
        return to - from;
    }

    // метод попадания в диапазон
    public boolean isInside(double x) {
        return x >= from && x <= to;
    }
}
