package ru.academit.kalagur.range;

public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    // геттеры и сеттеры
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
        this.to = to;
    }

    // метод расчеты длины диапазона
    public double getLength() {
        return to - from;
    }

    // метод расчета вхождения числа в диапазон
    public boolean isInside(double number) {
        return number >= from && number <= to;
    }

    public Range getIntersection(Range range) {
        // интервалы не пересекаются
        if ((range.from >= to) || (from >= range.to)) {
            return null;
        }

        // интервалы пересекаются
        return new Range(Math.max(from, range.from), Math.min(to, range.to));
    }

    public Range[] getUnion(Range range) {
        // интервалы не пересекаются
        if ((range.from > to) || (from > range.to)) {
            return new Range[]{new Range(range.from, range.to), new Range(from, to)};
        }

        // интервалы пересекаются
        return new Range[]{new Range(Math.min(from, range.from), Math.max(to, range.to))};
    }

    public Range[] getDifference(Range range) {
        // интервалы не пересекаются
        if ((range.from >= to) || (from >= range.to)) {
            return new Range[]{new Range(from, to)};
        }

        // интервалы пересекаются
        if (range.from <= from) {
            if (range.to >= to) {
                return new Range[0];
            }

            return new Range[]{new Range(range.to, to)};
        }

        if (range.to >= to) {
            return new Range[]{new Range(from, range.from)};
        }

        return new Range[]{new Range(from, range.from), new Range(range.to, to)};
    }

    @Override
    public String toString() {
        return "{" + from + "; " + to + "}";
    }
}