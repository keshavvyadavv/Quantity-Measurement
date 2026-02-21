package com.apps.quantitymeasurement.app;
import java.util.Scanner;
import com.apps.quantitymeasurement.domain.LengthUnit;
import com.apps.quantitymeasurement.domain.Quantity;
public class QuantityMeasurementApp {
	
	public static boolean checkEquality(double value1 , LengthUnit unit1, double value2 , LengthUnit unit2) {
		Quantity q1 = new Quantity(value1 , unit1);
		Quantity q2 = new Quantity(value2 , unit2);
		
		return q1.equals(q2);
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		try {
			
			System.out.println("Enter first value:");
            double value1 = Double.parseDouble(scanner.nextLine());

            System.out.println("Enter first unit (feet/inch):");
            LengthUnit unit1 = LengthUnit.fromString(scanner.nextLine());

            System.out.println("Enter second value:");
            double value2 = Double.parseDouble(scanner.nextLine());

            System.out.println("Enter second unit (feet/inch):");
            LengthUnit unit2 = LengthUnit.fromString(scanner.nextLine());

            Quantity q1 = new Quantity(value1, unit1);
            Quantity q2 = new Quantity(value2, unit2);

            boolean result = q1.equals(q2);

            System.out.println();
            System.out.println("Input: " + q1 + " and " + q2);
            System.out.println("Output: Equal (" + result + ")");
		} catch (NumberFormatException e) {
            System.out.println("Invalid number entered.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
	}
}
