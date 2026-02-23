package com.apps.quantitymeasurement.app;
import java.util.Scanner;

import com.apps.quantitymeasurement.domain.Length;
import com.apps.quantitymeasurement.domain.Length.LengthUnit;
public class QuantityMeasurementApp {
	
	public static double demonstrateLengthConversion(double value,LengthUnit from,LengthUnit to) {
		return Length.convert(value, from, to);
	}
	
	public static Length demonstrateLengthConversion(Length length,LengthUnit to) {
		return length.convertTo(to);
	}
	
	public static boolean demonstrateLengthEquality(Length l1, Length l2) {
		return l1.equals(l2);
	}
	
	public static void main(String[] args) {
		System.out.println(demonstrateLengthConversion(1.0,LengthUnit.FEET, LengthUnit.INCHES)); 
		
		System.out.println(demonstrateLengthConversion(3.0,LengthUnit.YARDS, LengthUnit.FEET)); 
		
		Length l = new Length(36, LengthUnit.INCHES);
		System.out.println(demonstrateLengthConversion(l,
		LengthUnit.YARDS)); 
	}
}
