<<<<<<< HEAD
# 📏 QuantityMeasurement - Feet

A simple Java application to measure and compare quantities in **Feet** using clean design and unit testing.

---

## Features

- Immutable `Feet` class  
- Factory method `fromString()`  
- Custom exception `InvalidFeetException`  
- Proper `equals()` and `hashCode()` implementation  
- Unit testing using JUnit 5  
=======
# Quantity-Measurement

A scalable, generic measurement system built using Java Generics and interfaces.
Supports Length, Weight, Volume, and Temperature with unit conversion, equality comparison, and selective arithmetic operations.
Designed using SOLID principles, DRY architecture, functional interfaces, and type-safe generics for extensibility and maintainability.
>>>>>>> 1f7e55e86d67a1d4dd3aa662739f6710b465f975

---

```
## 📂 Folder Structure

Quantity-Measurement
│
├── .mvn/
│
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── apps/
│   │               └── quantitymeasurement/
│   │                   ├── app/
│   │                   │   └── QuantityMeasurementApp.java
│   │                   │
│   │                   └── domain/
<<<<<<< HEAD
│   │                       ├── Feet.java
│   │                       └── InvalidFeetException.java
=======
│   │                       ├── IMeasurable.java
│   │                       └── LengthUnit.java
│   │                       └── SupportsArithmetic.java
│   │                       └── TemperatureUnit.java
│   │                       └── VolumeUnit.java
│   │                       └── WeightUnit.java
│   │                       └── Quantity.java
>>>>>>> 1f7e55e86d67a1d4dd3aa662739f6710b465f975
│   │
│   └── test/
│       └── java/
│           └── com/
│               └── apps/
│                   └── quantitymeasurement/
│                       └── domain/
<<<<<<< HEAD
│                           └── FeetTest.java
=======
│                           └── QuantityTest.java
>>>>>>> 1f7e55e86d67a1d4dd3aa662739f6710b465f975
│
├── .gitignore
├── pom.xml
└── README.md

```

---

<<<<<<< HEAD
=======
# UC1-FeetEquality

## Features

- Immutable `Feet` class  
- Factory method `fromString()`  
- Custom exception `InvalidFeetException`  
- Proper `equals()` and `hashCode()` implementation  
- Unit testing using JUnit 5  

>>>>>>> 1f7e55e86d67a1d4dd3aa662739f6710b465f975
## Test Cases Covered

- Same value comparison  
- Different value comparison  
- Null comparison  
- Different type comparison  
- Valid string input  
- Invalid string input (Exception case)  

---

<<<<<<< HEAD
## Example Output

---

## Technologies Used

- Java  
- JUnit 5  

---
=======
## Technologies Used

- Java  
- JUnit 5

🔗 *Code Link:*  
👉 [UC-1](https://github.com/keshavvyadavv/Quantity-Measurement/tree/feature/UC1-FeetEquality)

---

# UC2 - Equality Comparison

This use case implements equality comparison for:

- Feet
- Inches

Objects are created using:
Feet.fromString("1.0");
Inches.fromString("1.0");

Invalid numeric input throws a custom exception.

## Equality Rules
- Same value → true
- Different value → false
- Null → false
- Same reference → true
- Different class → false

## Tech
Java, JUnit 5

🔗 *Code Link:*  
👉 [UC-2](https://github.com/keshavvyadavv/Quantity-Measurement/tree/feature/UC2-InchEquality)

---

# UC3 – Generic Quantity Class (DRY Principle)

## Description
UC3 refactors separate `Feet` and `Inches` classes into a single generic `Quantity` class using a `LengthUnit` enum.  
This eliminates code duplication and follows the DRY principle.

## How It Works
- User enters value and unit (feet/inch).
- Values are converted to a common base unit (feet).
- Equality is checked using value-based comparison.

## Key Concepts
- DRY Principle
- Enum Usage
- Encapsulation
- Abstraction
- Proper equals() contract
- Cross-unit comparison (1 foot = 12 inches)

🔗 *Code Link:*  
👉 [UC-3](https://github.com/keshavvyadavv/Quantity-Measurement/tree/feature/UC3-GenericLength/src)

---

# Quantity Measurement – UC4

## Supported Units

* Feet
* Inch
* Yard
* Centimeter

## Features

* Takes input from user
* Converts units internally
* Checks if two values are equal
* Supports cross-unit comparison

## Example

1 Feet = 12 Inch
3 Feet = 1 Yard
2.54 Centimeter = 1 Inch


🔗 *Code Link:*  
👉 [UC-4](https://github.com/keshavvyadavv/Quantity-Measurement/tree/feature/UC4-YardEquality/src)

---

# UC5 – Unit-to-Unit Conversion

UC5 adds direct conversion between length units using a common base unit.

## Supported Units

FEET, INCHES, YARDS, CENTIMETERS

## Features

* Static `convert(value, source, target)` method
* Instance `convertTo()` method
* Base unit normalization
* Input validation (null, NaN, infinite)
* Immutable value object

## Formula

```
result = value × (source.factor / target.factor)
```

## Example

* 1 FEET → INCHES = 12
* 3 YARDS → FEET = 9
* 36 INCHES → YARDS = 1

🔗 *Code Link:*  
👉 [UC-5](https://github.com/keshavvyadavv/Quantity-Measurement/tree/feature/UC5-UnitConversion/src)

---

# UC6 – Addition of Two Length Units

UC6 extends UC5 by adding support for **addition of two length measurements** (same category).

## Supported Units

FEET, INCHES, YARDS, CENTIMETERS

## Features

* Add two `Length` objects
* Automatic unit conversion before addition
* Result returned in unit of first operand
* Uses base unit normalization (FEET)
* Immutable design (returns new object)
* Input validation for null, NaN, infinite values

## Logic

1. Convert both lengths to base unit (FEET)
2. Add values
3. Convert sum back to first operand’s unit
4. Return new `Length` object

## Example

* 1 FEET + 2 FEET = 3 FEET
* 1 FEET + 12 INCHES = 2 FEET
* 12 INCHES + 1 FEET = 24 INCHES
* 1 YARD + 3 FEET = 2 YARDS

🔗 *Code Link:*  
👉 [UC-6](https://github.com/keshavvyadavv/Quantity-Measurement/tree/feature/UC6-UnitAddition/src)

---

# Quantity Measurement – UC7

## Description

This project performs **length addition and unit conversion** using OOP principles in Java.

## Supported Units

* FEET
* INCHES
* YARDS
* CENTIMETERS

## Features

* Add two quantities
* Automatic unit conversion
* Result in chosen target unit
* Equality comparison using base unit (FEET)
* Immutable design
* Input validation

## Example

```
1 FEET + 12 INCHES

Output:
2.0 feet
24.0 inches
0.6666666666666666 yards
```

## Design

* Base unit: FEET
* Uses conversion factors
* EPSILON-based equality comparison

🔗 *Code Link:*  
👉 [UC-7](https://github.com/keshavvyadavv/Quantity-Measurement/tree/feature/UC7-TargetUnitAddition/src)

---

# UC8 – Standalone LengthUnit Refactoring

## Description

UC8 refactors the design by extracting `LengthUnit` into a standalone enum and assigning it full responsibility for unit conversions.

## Key Improvements

* `LengthUnit` handles all conversion logic
* `Quantity` handles comparison and arithmetic
* Follows Single Responsibility Principle (SRP)
* Removes circular dependency risk
* Improves scalability for future units (Weight, Volume, etc.)
* Fully backward compatible with UC1–UC7

## Supported Units

* FEET
* INCHES
* YARDS
* CENTIMETERS

## Example Output

```
Convert 1 FEET to INCHES:
12.0 inches

Add 1 FEET + 12 INCHES (in FEET):
2.0 feet

Add 1 FEET + 12 INCHES (in YARDS):
0.6666666666666666 yards

36 INCHES equals 1 YARDS?
true
```

## Architecture

* Base Unit: FEET
* Conversion delegated to `LengthUnit`
* Equality uses epsilon comparison
* Immutable design

  🔗 *Code Link:*  
👉 [UC-8](https://github.com/keshavvyadavv/Quantity-Measurement/tree/feature/UC8-StandaloneUnit/src)

---

# UC9 – Weight Measurement

## Description
UC9 extends the Quantity Measurement Application to support **Weight Measurements** alongside Length.

Supported Units:
- **KILOGRAM (kg)** – Base unit
- **GRAM (g)** – 1 g = 0.001 kg
- **POUND (lb)** – 1 lb = 0.453592 kg

## Features
- Equality comparison (cross-unit supported)
- Unit conversion
- Addition (implicit & explicit target unit)
- Immutable design
- Type-safe (Weight ≠ Length)
- Uses epsilon (1e-6) for floating-point precision

## Conclusion
UC9 validates scalable architecture by supporting multiple measurement categories without affecting existing length functionality.

  🔗 *Code Link:*  
👉 [UC-9](https://github.com/keshavvyadavv/Quantity-Measurement/tree/feature/UC9-Weight-Measurement/src)

---

# UC10 – Generic Quantity

## Description
UC10 refactors UC9 into a single generic class:

`Quantity<U extends IMeasurable>`

This removes duplication and makes the system scalable and maintainable.

---

## Improvements
- Single generic `Quantity` class
- Common `IMeasurable` interface
- Supports multiple categories (Length, Weight)
- No duplicate logic (DRY)
- Type-safe and immutable

---

## Features
- Equality comparison
- Unit conversion
- Addition (with optional target unit)
- Prevents cross-category comparison

  🔗 *Code Link:*  
👉 [UC-10](https://github.com/keshavvyadavv/Quantity-Measurement/tree/feature/UC10-GenericQuantity/src)


---

# UC11 – Volume Measurement (Litre, Millilitre, Gallon)

## Description
UC11 extends the Generic Quantity system (UC10) to support **Volume measurements** using the existing:

---

## Supported Volume Units

- **LITRE (L)** – Base Unit  
- **MILLILITRE (mL)** → 1 L = 1000 mL  
- **GALLON (gal)** → 1 gal ≈ 3.78541 L  

---

## Features

- Equality comparison (cross-unit supported)
- Unit conversion
- Addition (implicit & explicit target unit)
- Cross-category comparison prevented
- Immutable design
- Fully backward compatible (UC1–UC10)

    🔗 *Code Link:*  
👉 [UC-11](https://github.com/keshavvyadavv/Quantity-Measurement/tree/feature/UC11-VolumeMeasurement/src)


---

# UC12 – Subtraction & Division on Quantity

## Description
UC12 extends the generic `Quantity<U extends IMeasurable>` system by adding:

- Subtraction → returns new `Quantity<U>`
- Division → returns dimensionless `double`

No architectural changes required.

## Features
- Cross-unit arithmetic (same category)
- Explicit & implicit target unit
- Cross-category prevention
- Division by zero handling
- Immutability preserved
- Works for Length, Weight, Volume

    🔗 *Code Link:*  
👉 [UC-12](https://github.com/keshavvyadavv/Quantity-Measurement/tree/feature/UC12-Subtraction-Division/src)

---

# UC13 – Centralized Arithmetic (Refactored)

## Description
UC13 refactors `Quantity<U extends IMeasurable>` to centralize arithmetic logic using enum-based dispatch and DRY principles.

## Features
- Generic Quantity class
- Centralized validation
- Centralized base conversion
- ADD, SUBTRACT → return Quantity
- DIVIDE → returns double
- Cross-category protection
- Division by zero handling
- Immutable design

## Example Output
9.50 FEET  
4.50 LITRE  
5.0  
0.9999999999999998  
Error: Division by zero  

## Conclusion
UC13 improves maintainability, removes duplication, and keeps full backward compatibility.

  🔗 *Code Link:*  
👉 [UC-13](https://github.com/keshavvyadavv/Quantity-Measurement/tree/feature/UC13-Arithmetic-DRY/src)

---

# UC14 – Temperature with Selective Arithmetic Support

## Description
UC14 adds **Temperature (Celsius, Fahrenheit, Kelvin)** to the Quantity system.

Temperature supports:
- Equality comparison
- Unit conversion

Temperature does NOT support:
- Addition
- Subtraction
- Division

## Key Changes
- Refactored `IMeasurable` using default methods
- Added `SupportsArithmetic` functional interface
- `TemperatureUnit` overrides arithmetic validation
- `Quantity` checks operation support before execution
- Fully backward compatible (UC1–UC13 unchanged)

  🔗 *Code Link:*  
👉 [UC-14](https://github.com/keshavvyadavv/Quantity-Measurement/tree/feature/UC14-TemperaturE-Measurement/src)
---
>>>>>>> 1f7e55e86d67a1d4dd3aa662739f6710b465f975
