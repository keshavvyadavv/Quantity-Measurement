package com.apps.quantitymeasurement.domain;

import java.util.Objects;
import java.util.function.DoubleBinaryOperator;


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

    public Quantity<U> convertTo(U targetUnit) {
        double base = unit.toBase(value);
        double result = targetUnit.fromBase(base);
        
        return new Quantity<>(result, targetUnit);
    }
    
    private enum ArithmeticOperation {

        ADD((a, b) -> a + b),

        SUBTRACT((a, b) -> a - b),

        DIVIDE((a, b) -> {
            if (Math.abs(b) < EPSILON)
            	
                throw new ArithmeticException("Division by zero");
            return a / b;
        });

        private final DoubleBinaryOperator operator;

        ArithmeticOperation(DoubleBinaryOperator operator) {
            this.operator = operator;
        }

        double apply(double a, double b) {
            return operator.applyAsDouble(a, b);
        }
    }
    
    private double perform(Quantity<U> other, ArithmeticOperation op) {

        unit.validateOperationSupport(op.name());
        other.unit.validateOperationSupport(op.name());

        if (!unit.getClass().equals(other.unit.getClass()))
            throw new IllegalArgumentException("Cross-category operation not allowed");

        double base1 = unit.toBase(value);
        double base2 = other.unit.toBase(other.value);

        return op.apply(base1, base2);
    }
    
    public Quantity<U> add(Quantity<U> other) {
        double result = perform(other, ArithmeticOperation.ADD);
        
        return new Quantity<>(unit.fromBase(result), unit);
    }

    public Quantity<U> subtract(Quantity<U> other) {
    	
        double result = perform(other, ArithmeticOperation.SUBTRACT);
        return new Quantity<>(unit.fromBase(result), unit);
    }

    public double divide(Quantity<U> other) {
    	
        return perform(other, ArithmeticOperation.DIVIDE);
    }
    
        
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        
        if (!(obj instanceof Quantity<?> other)) return false;

        if (!unit.getClass().equals(other.unit.getClass()))
            return false;

        double base1 = unit.toBase(value);
        double base2 = other.unit.toBase(other.value);

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