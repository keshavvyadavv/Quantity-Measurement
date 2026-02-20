package com.apps.quantitymeasurement.app;
import com.apps.quantitymeasurement.domain.Feet;
import com.apps.quantitymeasurement.domain.Inches;
import com.apps.quantitymeasurement.domain.InvalidFeetException;
public class QuantityMeasurementApp {
	
	public static boolean checkFeetEquality(String first , String second) {
		Feet f1 = Feet.fromString(first);
		Feet f2 = Feet.fromString(second);
		return f1.equals(f2);
	}
	
	public static boolean checkInchesEquality(String first , String second) {
		Inches i1 = Inches.fromString(first);
		Inches i2 = Inches.fromString(second);
		return i1.equals(i2);
	}
	
	public static void main(String[] args) {
		
		try {
			System.out.println("Input: 1.0 inch and 1.0 inch");
			System.out.println("Output: Equal (" + checkInchesEquality("1.0" , "1.0") + ")");
			
			System.out.println("Input: 1.0 ft and 1.0 ft");
			System.out.println("Output: Equal (" + checkFeetEquality("1.0","1.0") + ")");

	    } catch (InvalidFeetException e) {
	        System.out.println("Error: " + e.getMessage());
	    }
	}
}
