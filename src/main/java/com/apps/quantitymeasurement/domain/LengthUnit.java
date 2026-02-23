package com.apps.quantitymeasurement.domain;

public enum LengthUnit implements IMeasurable {

    FEET(0.3048),
    INCHES(0.0254),
    YARDS(0.9144),
    CENTIMETERS(0.01);

    private final double factor;

    LengthUnit(double factor) {
        this.factor = factor;
    }

    public double toBase(double value) {
        return value * factor;
    }

    public double fromBase(double baseValue) {
        return baseValue / factor;
    }
}