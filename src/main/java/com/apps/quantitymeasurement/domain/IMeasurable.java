package com.apps.quantitymeasurement.domain;

public interface IMeasurable {

    double toBase(double value);
    double fromBase(double baseValue);
    
}