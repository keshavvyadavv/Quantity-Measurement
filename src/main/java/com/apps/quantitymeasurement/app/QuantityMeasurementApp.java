package com.apps.quantitymeasurement.app;
import java.util.Scanner;

import com.apps.quantitymeasurement.domain.LengthUnit;
import com.apps.quantitymeasurement.domain.Quantity;
import com.apps.quantitymeasurement.domain.VolumeUnit;
import com.apps.quantitymeasurement.domain.WeightUnit;
public class QuantityMeasurementApp {
	
	public static void main(String[] args) {
		
        System.out.println(new Quantity<>(10.0, LengthUnit.FEET).subtract(new Quantity<>(6.0, LengthUnit.INCHES)));

        System.out.println(new Quantity<>(5.0, VolumeUnit.LITRE).subtract(new Quantity<>(500.0, VolumeUnit.MILLILITRE)));
        System.out.println(new Quantity<>(10.0, LengthUnit.FEET).divide(new Quantity<>(2.0, LengthUnit.FEET)));

        System.out.println(new Quantity<>(24.0, LengthUnit.INCHES).divide(new Quantity<>(2.0, LengthUnit.FEET)) );

       
        try {
            System.out.println(
                    new Quantity<>(10.0, LengthUnit.FEET)
                            .divide(new Quantity<>(0.0, LengthUnit.FEET))
            );
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        
	}
}
