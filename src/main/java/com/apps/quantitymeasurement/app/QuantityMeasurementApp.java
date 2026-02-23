package com.apps.quantitymeasurement.app;
import java.util.Scanner;

import com.apps.quantitymeasurement.domain.Length;
import com.apps.quantitymeasurement.domain.LengthUnit;
import com.apps.quantitymeasurement.domain.Quantity;
public class QuantityMeasurementApp {
	
	public static boolean demonstrateLengthEquality(Length length1, Length length2) {
        return length1.equals(length2);
    }

    public static void demonstrateLengthComparison(double value1, Length.LengthUnit unit1,double value2, Length.LengthUnit unit2) {

        Length l1 = new Length(value1, unit1);
        Length l2 = new Length(value2, unit2);

        System.out.println("Input: " + l1 + " and " + l2);
        System.out.println("Output: Equal (" + demonstrateLengthEquality(l1, l2) + ")");
        System.out.println();
    }
	
	public static void main(String[] args) {

			demonstrateLengthComparison(1.0, Length.LengthUnit.YARDS,3.0, Length.LengthUnit.FEET);

			demonstrateLengthComparison(1.0, Length.LengthUnit.YARDS,36.0, Length.LengthUnit.INCHES);
			
			demonstrateLengthComparison(2.0, Length.LengthUnit.YARDS,2.0, Length.LengthUnit.YARDS);
			
			demonstrateLengthComparison(2.0, Length.LengthUnit.CENTIMETERS,2.0, Length.LengthUnit.CENTIMETERS);
			
			demonstrateLengthComparison(1.0, Length.LengthUnit.CENTIMETERS,0.393701, Length.LengthUnit.INCHES);

	}
}
