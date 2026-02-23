package com.apps.quantitymeasurement.domain;

public enum LengthUnit implements IMeasurable {

    FEET(0.3048),
    INCHES(0.0254),
    YARDS(0.9144),
    CENTIMETERS(0.01);

    private final double toMeterFactor;

    LengthUnit(double toMeterFactor) {
        this.toMeterFactor = toMeterFactor;
    }

    @Override
    public double toBaseUnit(double value) {
        return value * toMeterFactor;
    }

    @Override
    public double fromBaseUnit(double baseValue) {
        return baseValue / toMeterFactor;
    }
}