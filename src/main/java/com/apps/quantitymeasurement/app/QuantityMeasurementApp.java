package com.apps.quantitymeasurement.app;


import com.apps.quantitymeasurement.domain.LengthUnit;
import com.apps.quantitymeasurement.domain.Quantity;
import com.apps.quantitymeasurement.domain.TemperatureUnit;
import com.apps.quantitymeasurement.domain.VolumeUnit;
import com.apps.quantitymeasurement.domain.WeightUnit;
public class QuantityMeasurementApp {
	
	public static void main(String[] args) {
		
        System.out.println( new Quantity<>(0.0, TemperatureUnit.CELSIUS).equals(new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT)));

        System.out.println( new Quantity<>(100.0, TemperatureUnit.CELSIUS).convertTo(TemperatureUnit.FAHRENHEIT));

        try {
            new Quantity<>(100.0, TemperatureUnit.CELSIUS).add(new Quantity<>(50.0, TemperatureUnit.CELSIUS));
        
        } catch (Exception e) {
        	
            System.out.println(e.getMessage());
        }
        
	}
}
