package com.apps.quantitymeasurement.domain;

import java.util.Objects;

import java.util.Objects;

public class Quantity<U extends IMeasurable> {

    private static final double EPSILON = 1e-6;

    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Invalid numeric value");
        }
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }
    
    private double toBase() {
        return unit.toBase(value);
    }
    
    private void validate(Quantity<U> other) {
        if (other == null) {
            throw new IllegalArgumentException("Quantity cannot be null");
        }
        if (!unit.getClass().equals(other.unit.getClass())) {
            throw new IllegalArgumentException("Cross-category operation not allowed");
        }
    }

    private double round(double val) {
        return Math.round(val * 100.0) / 100.0;
    }

    public Quantity<U> convertTo(U targetUnit) {
        double baseValue = unit.toBase(value);
        double converted = targetUnit.fromBase(baseValue);
        return new Quantity<>(converted, targetUnit);
    }

    public Quantity<U> add(Quantity<U> other) {
        return add(other, this.unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {
        validate(other);
        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double resultBase = this.toBase() + other.toBase();
        double result = targetUnit.fromBase(resultBase);
        return new Quantity<>(round(result), targetUnit);
    }
    
    public Quantity<U> subtract(Quantity<U> other) {
        return subtract(other, this.unit);
    }

    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {
        validate(other);
        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double resultBase = this.toBase() - other.toBase();
        double result = targetUnit.fromBase(resultBase);
        return new Quantity<>(round(result), targetUnit);
    }


    public double divide(Quantity<U> other) {
        validate(other);

        if (Math.abs(other.toBase()) < EPSILON) {
            throw new ArithmeticException("Division by zero");
        }

        return this.toBase() / other.toBase();
    }
    
    

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Quantity<?> other)) return false;

        double base1 = unit.toBase(this.value);
        double base2 = ((IMeasurable) other.unit).toBase(other.value);

        return Math.abs(base1 - base2) < EPSILON;
    }
    
    @Override
    public String toString() {
        return String.format("%.2f %s", value, unit);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(unit.toBase(value));
    }
}