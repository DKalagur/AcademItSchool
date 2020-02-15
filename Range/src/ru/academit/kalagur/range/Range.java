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

    public double getTo() {
        return to;
    }

    public void setFrom(double from) {
        this.from = from;
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
        if ((range.from > this.to) || (this.from > range.to)) {
            return null;
        }

        // интервалы пересекаются
        Range temp = new Range(0, 0);

        if (this.from > range.from) {
            if (this.to > range.to) {
                temp.from = this.from;
                temp.to = range.to;
            } else {
                temp.from = this.from;
                temp.to = this.to;
            }
        } else if (this.to > range.to) {
            temp.from = range.from;
            temp.to = range.to;
        } else {
            temp.from = range.from;
            temp.to = this.to;
        }

        return temp;
    }

    public Range[] getJoin(Range range) {
        // интервалы не пересекаются
        if ((range.from > this.to) || (this.from > range.to)) {
            Range temp1 = new Range(0, 0);
            Range temp2 = new Range(0, 0);
            Range[] twoElementsTempArray = new Range[2];

            temp1.from = range.from;
            temp1.to = range.to;
            temp2.from = this.from;
            temp2.to = this.to;
            twoElementsTempArray[0] = temp1;
            twoElementsTempArray[1] = temp2;

            return twoElementsTempArray;
        }

        // интервалы пересекаются
        Range temp1 = new Range(0, 0);
        Range[] oneElementTempArray = new Range[1];

        if (this.from > range.from) {
            if (this.to > range.to) {
                temp1.from = range.from;
                temp1.to = this.to;
            } else {
                temp1.from = range.from;
                temp1.to = range.to;
            }
        } else if (this.to > range.to) {
            temp1.from = this.from;
            temp1.to = this.to;
        } else {
            temp1.from = this.from;
            temp1.to = range.to;
        }
        oneElementTempArray[0] = temp1;

        return oneElementTempArray;
    }

    public Range[] getDifference(Range range) {
        // интервалы не пересекаются
        if ((range.from > this.to) || (this.from > range.to)) {
            Range temp1 = new Range(0, 0);
            Range[] oneElementTempArray = new Range[1];

            temp1.from = this.from;
            temp1.to = this.to;
            oneElementTempArray[0] = temp1;

            return oneElementTempArray;
        }

        // интервалы пересекаются
        if (this.from > range.from) {
            if (this.to > range.to) {
                Range temp1 = new Range(0, 0);
                Range[] oneElementTempArray = new Range[1];

                temp1.from = range.to;
                temp1.to = this.to;
                oneElementTempArray[0] = temp1;

                return oneElementTempArray;
            }

            return new Range[0];
        }

        if (this.to > range.to) {
            Range temp1 = new Range(0, 0);
            Range temp2 = new Range(0, 0);
            Range[] twoElementsTempArray = new Range[2];

            temp1.from = this.from;
            temp1.to = range.from;
            temp2.from = range.to;
            temp2.to = this.to;
            twoElementsTempArray[0] = temp1;
            twoElementsTempArray[1] = temp2;

            return twoElementsTempArray;
        }

        Range temp1 = new Range(0, 0);
        Range[] oneElementTempArray = new Range[1];

        temp1.from = this.from;
        temp1.to = range.from;
        oneElementTempArray[0] = temp1;

        return oneElementTempArray;
    }
}