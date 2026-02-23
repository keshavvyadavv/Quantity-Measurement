package com.apps.quantitymeasurement.app;
import java.util.Scanner;

import com.apps.quantitymeasurement.domain.LengthUnit;
import com.apps.quantitymeasurement.domain.Quantity;
import com.apps.quantitymeasurement.domain.VolumeUnit;
import com.apps.quantitymeasurement.domain.WeightUnit;
public class QuantityMeasurementApp {
	
	public static void main(String[] args) {
	
		Quantity<VolumeUnit> oneLitre = new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> thousandML = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        Quantity<VolumeUnit> oneGallon = new Quantity<>(1.0, VolumeUnit.GALLON);

        System.out.println(oneLitre.equals(thousandML));   
        System.out.println(oneGallon.equals(new Quantity<>(3.78541, VolumeUnit.LITRE))); 

        System.out.println(oneLitre.convertTo(VolumeUnit.MILLILITRE));
        System.out.println(oneGallon.convertTo(VolumeUnit.LITRE));

        System.out.println(oneLitre.add(thousandML));

        System.out.println(oneLitre.add(oneGallon, VolumeUnit.MILLILITRE));

        Quantity<LengthUnit> oneFoot = new Quantity<>(1.0, LengthUnit.FEET);

        System.out.println(oneLitre.equals(oneFoot)); 
		
	}
}
