package com.eric.fourth;

import android.graphics.PorterDuff;

public class Data {
    private int color;
    private PorterDuff.Mode mode;

    public Data() {
    }

    public Data(int color, PorterDuff.Mode mode) {
        this.color = color;
        this.mode = mode;
    }

    public int getColor() {
        return color;
    }

    public PorterDuff.Mode getMode() {
        return mode;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setMode(PorterDuff.Mode mode) {
        this.mode = mode;
    }
}