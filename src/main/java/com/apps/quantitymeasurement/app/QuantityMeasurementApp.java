package com.apps.quantitymeasurement.app;
import com.apps.quantitymeasurement.domain.Feet;
import com.apps.quantitymeasurement.domain.InvalidFeetException;
public class QuantityMeasurementApp {
	public static void main(String[] args) {
		
		try {
	        Feet first = Feet.fromString("1.0");
	        Feet second = Feet.fromString("hat"); 

	        boolean result = first.equals(second);
	        
	        System.out.println("Input: " + first + " and " + second);
            System.out.println("Output: Equal (" + result + ")");
	    } catch (InvalidFeetException e) {
	        System.out.println("Error: " + e.getMessage());
	    }
	}
}
