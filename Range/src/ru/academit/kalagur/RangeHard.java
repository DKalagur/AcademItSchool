package ru.academit.kalagur;

public class RangeHard {
    private double from;
    private double to;

    public RangeHard(double from, double to) {
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


    public RangeHard getIntersection(RangeHard range) {
        RangeHard temp = new RangeHard(0, 0);
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

    public RangeHard[] getJoin(RangeHard range) {
        RangeHard temp1 = new RangeHard(0, 0);
        RangeHard temp2 = new RangeHard(0, 0);
        RangeHard[] twoElementsTempArray = new RangeHard[2];
        RangeHard[] oneElementTempArray = new RangeHard[1];
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

    public RangeHard[] getDifference(RangeHard range) {
        RangeHard temp1 = new RangeHard(0, 0);
        RangeHard temp2 = new RangeHard(0, 0);
        RangeHard[] oneElementTempArray = new RangeHard[1];
        RangeHard[] twoElementsTempArray = new RangeHard[2];
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

