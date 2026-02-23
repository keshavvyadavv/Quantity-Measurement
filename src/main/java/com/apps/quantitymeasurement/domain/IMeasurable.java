package com.apps.quantitymeasurement.domain;

public interface IMeasurable {
    double toBaseUnit(double value);
    double fromBaseUnit(double baseValue);
}
