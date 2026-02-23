package com.apps.quantitymeasurement.app;
import java.util.Scanner;

import com.apps.quantitymeasurement.domain.Length;
import com.apps.quantitymeasurement.domain.Length.LengthUnit;
import com.apps.quantitymeasurement.domain.Quantity;
public class QuantityMeasurementApp {
	
	public static void main(String[] args) {
		Quantity q1 = new Quantity(1.0, LengthUnit.FEET);
        Quantity q2 = new Quantity(12.0, LengthUnit.INCHES);

        System.out.println(
                q1.add(q2, LengthUnit.FEET)
        );

        System.out.println(
                q1.add(q2, LengthUnit.INCHES)
        );

        System.out.println(
                q1.add(q2, LengthUnit.YARDS)
        );
	}
}
