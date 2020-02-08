package ru.academit.kalagur.range;

public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    // геттеры
    public double getFrom() {
        return from;
    }

    public double getTo() {
        return to;
    }

    public Range getIntersection(Range range) {
        Range temp = new Range(0, 0);
        double epsilon = 1.0e-10;
        // интервалы не пересекаются
        if ((range.from - this.to > epsilon) || (this.from - range.to > epsilon)) {
            return null;
        }
        // интервалы пересекаются
        if ((this.from - range.from) > epsilon) {
            if ((this.to - range.to) > epsilon) {
                temp.from = this.from;
                temp.to = range.to;
            } else {
                temp.from = this.from;
                temp.to = this.to;
            }
        } else if ((this.to - range.to) > epsilon) {
            temp.from = range.from;
            temp.to = range.to;
        } else {
            temp.from = range.from;
            temp.to = this.to;
        }
        return temp;
    }

    public Range[] getJoin(Range range) {
        Range temp1 = new Range(0, 0);
        Range temp2 = new Range(0, 0);
        Range[] twoElementsTempArray = new Range[2];
        Range[] oneElementTempArray = new Range[1];
        double epsilon = 1.0e-10;

        // интервалы не пересекаются
        if ((range.from - this.to > epsilon) || (this.from - range.to > epsilon)) {
            temp1.from = range.from;
            temp1.to = range.to;
            temp2.from = this.from;
            temp2.to = this.to;
            twoElementsTempArray[0] = temp1;
            twoElementsTempArray[1] = temp2;
            return twoElementsTempArray;
        }

        // интервалы пересекаются
        if ((this.from - range.from) > epsilon) {
            if ((this.to - range.to) > epsilon) {
                temp1.from = range.from;
                temp1.to = this.to;
            } else {
                temp1.from = range.from;
                temp1.to = range.to;
            }
        } else if ((this.to - range.to) > epsilon) {
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
        Range temp1 = new Range(0, 0);
        Range temp2 = new Range(0, 0);
        Range[] oneElementTempArray = new Range[1];
        Range[] twoElementsTempArray = new Range[2];
        double epsilon = 1.0e-10;

        // интервалы не пересекаются
        if ((range.from - this.to > epsilon) || (this.from - range.to > epsilon)) {
            temp1.from = this.from;
            temp1.to = this.to;
            oneElementTempArray[0] = temp1;
            return oneElementTempArray;
        }

        // интервалы пересекаются
        if ((this.from - range.from) > epsilon) {
            if ((this.to - range.to) > epsilon) {
                temp1.from = range.to;
                temp1.to = this.to;
                oneElementTempArray[0] = temp1;
                return oneElementTempArray;
            }
            return null;
        }

        if ((this.to - range.to) > epsilon) {
            temp1.from = this.from;
            temp1.to = range.from;
            temp2.from = range.to;
            temp2.to = this.to;
            twoElementsTempArray[0] = temp1;
            twoElementsTempArray[1] = temp2;
            return twoElementsTempArray;
        }

        temp1.from = this.from;
        temp1.to = range.from;
        oneElementTempArray[0] = temp1;
        return oneElementTempArray;
    }
}