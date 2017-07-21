package com.taimeitech.pass.entity;

/**
 * Created by devin on 2017/4/27.
 */
public class RangeRandom {
    private int min;
    private int max;
    private int ranInt;
    private String description;

    public RangeRandom() {
    }

    public RangeRandom(int min, int max, int ranInt, String description) {
        this.min = min;
        this.max = max;
        this.ranInt = ranInt;
        this.description = description;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getRanInt() {
        return ranInt;
    }

    public void setRanInt(int ranInt) {
        this.ranInt = ranInt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
