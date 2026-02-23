# Quantity-Measurement

A simple Java application to measure and compare quantities in **Feet** using clean design and unit testing.

---

## Features

- Immutable `Feet` class  
- Factory method `fromString()`  
- Custom exception `InvalidFeetException`  
- Proper `equals()` and `hashCode()` implementation  
- Unit testing using JUnit 5  

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
│   │                       ├── Feet.java
│   │                       └── InvalidFeetException.java
│   │                       └── Inches.java
│   │                       └── Length.java
│   │                       └── LengthUnit.java
│   │                       └── Quantity.java
│   │
│   └── test/
│       └── java/
│           └── com/
│               └── apps/
│                   └── quantitymeasurement/
│                       └── domain/
│                           └── FeetTest.java
│                           └── InchesTest.java
│                           └── QuantityTest.java
│
├── .gitignore
├── pom.xml
└── README.md

```

---

## Test Cases Covered

- Same value comparison  
- Different value comparison  
- Null comparison  
- Different type comparison  
- Valid string input  
- Invalid string input (Exception case)  

---

## Example Output

---

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
