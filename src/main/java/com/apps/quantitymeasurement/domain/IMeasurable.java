package com.apps.quantitymeasurement.domain;

public interface IMeasurable {

    double toBase(double value);
    double fromBase(double baseValue);

    SupportsArithmetic supportsArithmetic = () -> true;

    default boolean supportsArithmetic() {
        return supportsArithmetic.isSupported();
    }

    default void validateOperationSupport(String operation) {
        
    }
}