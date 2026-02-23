package com.apps.quantitymeasurement.app;
import java.util.Scanner;

import com.apps.quantitymeasurement.domain.Length;
import com.apps.quantitymeasurement.domain.Length.LengthUnit;
public class QuantityMeasurementApp {
	
	public static void main(String[] args) {
		Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCHES);

        Length result = l1.add(l2);

        System.out.println("Input: " + l1 + " + " + l2);
        System.out.println("Output: " + result);

        System.out.println(new Length(12, LengthUnit.INCHES).add(new Length(1, LengthUnit.FEET)));

        System.out.println(new Length(1, LengthUnit.YARDS).add(new Length(3, LengthUnit.FEET)));

        System.out.println(new Length(5, LengthUnit.FEET).add(new Length(-2, LengthUnit.FEET)));
	}
}
