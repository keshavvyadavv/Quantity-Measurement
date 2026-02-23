package com.apps.quantitymeasurement.domain;

import java.util.Objects;

import java.util.Objects;

public class Quantity<U extends IMeasurable> {

    private static final double EPSILON = 1e-6;

    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }

    public Quantity<U> convertTo(U targetUnit) {
        double baseValue = unit.toBaseUnit(value);
        double converted = targetUnit.fromBaseUnit(baseValue);
        return new Quantity<>(converted, targetUnit);
    }

    public Quantity<U> add(Quantity<U> other) {
        double base1 = unit.toBaseUnit(this.value);
        double base2 = other.unit.toBaseUnit(other.value);
        double sumBase = base1 + base2;
        double result = unit.fromBaseUnit(sumBase);
        return new Quantity<>(result, unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {
        double base1 = unit.toBaseUnit(this.value);
        double base2 = other.unit.toBaseUnit(other.value);
        double sumBase = base1 + base2;
        double result = targetUnit.fromBaseUnit(sumBase);
        return new Quantity<>(result, targetUnit);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Quantity<?> other)) return false;

        double base1 = unit.toBaseUnit(this.value);
        double base2 = ((IMeasurable) other.unit).toBaseUnit(other.value);

        return Math.abs(base1 - base2) < EPSILON;
    }
    
    @Override
    public String toString() {
        return String.format("%.2f %s", value, unit);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(unit.toBaseUnit(value));
    }
}