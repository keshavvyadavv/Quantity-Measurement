package com.apps.quantitymeasurement.app;
import java.util.Scanner;

import com.apps.quantitymeasurement.domain.Length;
import com.apps.quantitymeasurement.domain.LengthUnit;
import com.apps.quantitymeasurement.domain.Quantity;
public class QuantityMeasurementApp {
	
	public static void main(String[] args) {
		Quantity q1 = new Quantity(1.0, LengthUnit.FEET);
        Quantity q2 = new Quantity(12.0, LengthUnit.INCHES);
        System.out.println("Convert 1 FEET to INCHES:");
        System.out.println(q1.convertTo(LengthUnit.INCHES));
        System.out.println();
        
        System.out.println("Add 1 Feet + 12 INChES (in FEET):");
        System.out.println(q1.add(q2, LengthUnit.FEET));

        System.out.println();

        System.out.println("Add 1 FEET + 12 inches (in YARDS):");
        System.out.println(q1.add(q2, LengthUnit.YARDS));

        System.out.println();

        Quantity q3 = new Quantity(36.0, LengthUnit.INCHES);
        Quantity q4 = new Quantity(1.0, LengthUnit.YARDS);

        System.out.println("36 INCHES equals 1 yards?");
        System.out.println(q3.equals(q4));
	}
}
